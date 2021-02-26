package cn.jwis.qualityworkflow.modules.factoryaudit.service.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventSearch;
import cn.jwis.qualityworkflow.modules.factoryaudit.dao.AuditEventMapper;
import cn.jwis.qualityworkflow.modules.factoryaudit.service.AuditEventService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public class AuditEventServiceImpl extends BaseClass implements AuditEventService {

	private static final String AUDIT_EVENT_EXCEL_NAME = "审核事件清单.xlsx";

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	AuditEventMapper auditEventMapper;

	@Autowired
	RestTemplate restTemplate;

	@Value("${platform.gateway.address}")
	private String gatewayAddress;

	@Override
	public void saveAuditEvents(AuditEventInfo auditEventInfo) {
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String account = currentUser.getAccount();
		auditEventInfo.setCreator(account);
		String id = auditEventInfo.getId();
		String type = auditEventInfo.getType();
		if ("Save".equals(type)) {
			auditEventInfo.setStatus("草稿");
		} else {
			auditEventInfo.setStatus("进行中");
		}

		JSONArray list = auditEventInfo.getAppendicesList();
		if (CollectionUtils.isNotEmpty(list)) {
			auditEventInfo.setAppendices(list.toString());
		}
		if (StringUtils.isEmpty(id)) {
			auditEventInfo.setId(String.valueOf(idGeneratorRunner.nextId()));
			auditEventMapper.saveAuditEvents(auditEventInfo);
			// 获取邮箱,发送邮件
		} else {
			auditEventMapper.updateById(auditEventInfo);
		}
		String isSendEmail = auditEventInfo.getIsSendEmail();
		if ("是".equals(isSendEmail) && "Submit".equals(type)) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("accesstoken", SessionHelper.getAccessToken());
			headers.set("appId", SessionHelper.getAppName());
			headers.setContentType(MediaType.APPLICATION_JSON);
			// 发送邮件
			JSONObject paramJson = new JSONObject();
			// 邮件模板名
			paramJson.put("emailTemplateName", "AuditEvent");
			// 抄送 多人;分隔
			// paramJson.put("ccList", "");
			// 邮件模板中动态参数
			JSONObject contextParams = new JSONObject();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String auditStartDate = format.format(auditEventInfo.getAuditStartDate());
			String auditEndDate = format.format(auditEventInfo.getAuditEndDate());
			contextParams.put("auditStartDate", auditStartDate);
			contextParams.put("auditEndDate", auditEndDate);
			contextParams.put("auditTopics", auditEventInfo.getAuditTopics());
			contextParams.put("auditType", auditEventInfo.getAuditType());
			contextParams.put("auditSource", auditEventInfo.getAuditSource());
			contextParams.put("auditParty", auditEventInfo.getAuditParty());
			contextParams.put("model", auditEventInfo.getModel());
			contextParams.put("auditRange", auditEventInfo.getAuditRange());
			contextParams.put("auditRequirements", auditEventInfo.getAuditRequirements());
			paramJson.put("contextParams", contextParams);
			// 主送 多人;分隔
			paramJson.put("toList", auditEventInfo.getNotificationRange());
			HttpEntity<JSONObject> entity1 = new HttpEntity<JSONObject>(paramJson, headers);
			restTemplate.exchange(gatewayAddress + "/foundation-pqm/notification/email/sendEmailByTemplate",
					HttpMethod.POST, entity1, Map.class);
		}

	}

	@Override
	public List<AuditEventInfo> getAuditEventList(AuditEventSearch auditEventSearch) {
		List<AuditEventInfo> result = new ArrayList<>();
		Integer page = auditEventSearch.getPage();
		Integer size = auditEventSearch.getSize();
		auditEventSearch.setPage((page - 1) * size);
		result = auditEventMapper.getAuditEventList(auditEventSearch);
		for (AuditEventInfo auditEventInfo : result) {
			String appendices = auditEventInfo.getAppendices();
			auditEventInfo.setAppendicesList(JSONArray.parseArray(appendices));
		}
		return result;
	}

	@Override
	public void exportAuditEventList(HttpServletResponse response, HttpServletRequest request,
			AuditEventSearch auditEventSearch) {
		List<AuditEventInfo> auditQuestionInfoList = auditEventMapper.getAuditEventList(auditEventSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (AuditEventInfo auditQuestionInfo : auditQuestionInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(auditQuestionInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, AUDIT_EVENT_EXCEL_NAME);
//		String[] Title = QimsCQAExcel;
//		String[] strings = QimsCQADB;
//		if ("en-US".equals(language)) {
//			Title = QimsCQAExcelUS;
//		}
		String[] title = new String[] { "事件编号", "审核主题", "审核类型", "审核来源", "审核开始时间", "审核结束时间", "审核方", "审核涉及机型", "审核区域范围",
				"审核要求/注意事项", "创建人", "创建时间", "是否发送邮件", "通知范围" };
		String[] strings = new String[] { "eventNumber", "auditTopics", "auditType", "auditSource", "auditStartDate",
				"auditEndDate", "auditParty", "model", "auditRange", "auditRequirements", "creator", "createDate",
				"isSendEmail", "notificationRange" };
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long getAuditEventCount(AuditEventSearch auditEventSearch) {
		// set用户
		Long count = auditEventMapper.getAuditEventCount(auditEventSearch);
		return count;
	}

	@Override
	public AuditEventInfo getAuditEventById(String id) {
		AuditEventInfo auditEventById = auditEventMapper.getAuditEventById(id);
		String appendices = auditEventById.getAppendices();
		auditEventById.setAppendicesList(JSONArray.parseArray(appendices));
		return auditEventById;
	}

	@Override
	public List<AuditEventInfo> getAuditEventByTopics(String topics) {
		return auditEventMapper.getAuditEventByTopics(topics);
	}

	@Override
	public List<AuditEventInfo> getAuditCalendarInfo(AuditEventSearch auditEventSearch) {
		List<AuditEventInfo> result = auditEventMapper.getAuditEventList(auditEventSearch);
		return result;
	}

	@Override
	public List<JSONObject> getYearAuditCalendarInfo(AuditEventSearch auditEventSearch) {
		List<AuditEventInfo> result = auditEventMapper.getAuditEventList(auditEventSearch);
		Set<String> betweenDate = DateUtil.getBetweenDate(3, auditEventSearch.getAuditStartDate(),
				auditEventSearch.getAuditEndDate());
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (String string : betweenDate) {
			JSONObject json = null;
			for (AuditEventInfo auditEventInfo : result) {
				Date between = DateUtil.parseStringtoDate(string);
				Date auditStartDate = auditEventInfo.getAuditStartDate();
				Date auditEndDate = auditEventInfo.getAuditEndDate();
				if (auditStartDate != null && auditEndDate != null) {
					if ((auditStartDate.before(between) || auditStartDate.compareTo(between) == 0)
							&& (auditEndDate.after(between) || auditEndDate.compareTo(between) == 0)) {
						json = new JSONObject();
						json.put("auditType", auditEventInfo.getAuditType());
						json.put("date", string);
						list.add(json);
						break;
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<String> getAuditEventTopics() {
		List<String> resultList = auditEventMapper.getAuditEventTopics();
		return resultList;
	}

}