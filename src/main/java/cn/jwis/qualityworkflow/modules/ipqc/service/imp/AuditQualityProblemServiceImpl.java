package cn.jwis.qualityworkflow.modules.ipqc.service.imp;

import static cn.jwis.qualityworkflow.modules.ipqc.bean.AuditConstants.Constants.IPQCCLOSETASKSTATE;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.AuditConstants.Constants.IPQCHISOTORYPROCESSCOMMIT;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.AuditConstants.Constants.IPQCTEMPLATEKEY;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditQualityProblemDbCamel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditQualityProblemExcel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditQualityProblemInfo;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditQualityProblemInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditQualityProblemInfoExcel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditQualityProblemInfoTable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdDashBoardMapper;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditQualityProblemInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryAuditQualityProblem;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditQualityProblemInfoMapper;
import cn.jwis.qualityworkflow.modules.ipqc.dao.PatrolProblemRecordMapper;
import cn.jwis.qualityworkflow.modules.ipqc.service.AuditQualityProblemService;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsSendMailMapper;
import cn.jwis.qualityworkflow.modules.qims.service.QimsSendMailService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

@Service
public class AuditQualityProblemServiceImpl extends BaseClass implements AuditQualityProblemService {

	public static final Logger logger = LoggerFactory.getLogger(AuditQualityProblemServiceImpl.class);

	@Autowired
	AuditQualityProblemInfoMapper auditQualityProblemInfoMapper;

	@Autowired
	WorkflowServer workflowServer;

	@Value("${qms.app.key}")
	private String tenantId;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	PatrolProblemRecordMapper patrolProblemRecordMapper;
	@Autowired
	WorkFlowUtil workFlowUtil;

	@Autowired
	EsdDashBoardMapper esdDashBoardMapper;

	@Autowired
	QimsSendMailMapper qimsSendMailMapper;

	@Autowired
	UserServer userServer;

	@Autowired
	EmailService emailService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	QimsSendMailService qimsSendMailService;

	@Autowired
	PatrolProblemRecordServiceImpl patrolProblemRecordServiceImpl;

	@Autowired
	TaskRecordMapper taskRecordMapper;

	@Override
	public List<AuditQualityProblemInfo> getAuditQualityProblemInfo(QueryAuditQualityProblem bean) {
		List<AuditQualityProblemInfo> resultList = null;
		String userItemInfos = userServer.getUserItemInfos("IPQC-业务管理员");
		bean.setAssignee(userItemInfos);
		if (bean.getPageNum() != null && bean.getPageSize() != null) {
			bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
		}
		resultList = auditQualityProblemInfoMapper.getAuditQualityProblemInfo(bean);
		return resultList;
	}

	@Override
	public Long getAuditQualityProblemCount(QueryAuditQualityProblem bean) {
		String userItemInfos = userServer.getUserItemInfos("IPQC-业务管理员");
		bean.setAssignee(userItemInfos);
		Long count = auditQualityProblemInfoMapper.getAuditQualityProblemCount(bean);
		return count;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertAuditQualityProblemInfo(AuditQualityProblemInfo bean) throws Exception {
		bean.setId(String.valueOf(idGeneratorRunner.nextId()));
		if (bean.getProblemPictureList() != null && bean.getProblemPictureList().size() > 0) {
			List<String> list = bean.getProblemPictureList();
			bean.setProblemPicture(spliceString(list));
		}
		if (bean.getCreator() == "" || bean.getCreator() == null) {
			bean.setCreator(getCurrentUserName());
		}
		if (bean.getCreateDate() == null) {
			bean.setCreateDate(new Date());
		}

		if (bean.getCumulativeFrequency() == null) {
			bean.setCumulativeFrequency(1);
		}
		auditQualityProblemInfoMapper.insertAuditQualityProblemInfo(bean);
		auditQualityProblemInfoMapper.deleteAuditQualityProblemInfo(bean.getCreator());
		JSONObject jsonObject = JSONObjectUtil.toJSONObject(bean);
		bean.setStatus("CreateIPQC");
		historyProcessRecordService.save(BeanUtil.generateHistoryProcessRecord(jsonObject, IPQCHISOTORYPROCESSCOMMIT,
				IPQCTEMPLATEKEY, bean.getId(), bean.getStatus()));
		// 1.启动IPQC流程实例，
		String processInstanceId = workFlowUtil.startProcessInstance("creator", bean.getCreator(), tenantId,
				IPQCTEMPLATEKEY);
		// 获取最新的Task,
		String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
		String owner = bean.getOwner();
		workFlowUtil.finishTask(processInstanceId, taskId, "causeAnalyze", owner);
		String auditId = bean.getId();
		workFlowUtil.saveEcnApplyTaskRecord(auditId, processInstanceId, taskId, bean.getCreator(), "CreateIPQC",
				IPQCCLOSETASKSTATE, IPQCTEMPLATEKEY, "", "");
		String status = workFlowUtil.nextNode(processInstanceId, auditId, IPQCTEMPLATEKEY, auditId, bean.getCreator(),
				"", "");
		bean.setStatus(status);
		auditQualityProblemInfoMapper.updateAuditQualityProblemInfo(bean);
	}

	@Override
	public void confirm(JSONObject jsonObject) throws Exception {
		JSONObject variables = additionalFieldInfo(jsonObject);
		jsonObject.put("variables", variables);
		String creator = auditQualityProblemInfoMapper.getCreator(jsonObject.getString("workflow_business_id"));
		workFlowUtil.confirm(jsonObject, IPQCTEMPLATEKEY, "audit_quality_problem_info", creator, "", "");

	}

	@Override
	public void exportAuditQualityProblem(HttpServletResponse response, HttpServletRequest request,
			QueryAuditQualityProblem bean) {
		String userItemInfos = userServer.getUserItemInfos("IPQC-业务管理员");
		bean.setAssignee(userItemInfos);
		List<AuditQualityProblemInfo> list = auditQualityProblemInfoMapper.exportAuditQualityProblem(bean);
		// 将List<AuditListInfo> 转换为 List<JSONObject>
		List<JSONObject> jsonObjects = new ArrayList<>(list.size());
		for (int i = 0; i < list.size(); i++) {
			Object obj = JSONObject.toJSONStringWithDateFormat(list.get(i), "yyyy-MM-dd HH:mm:ss",
					SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
			JSONObject object = JSONObjectUtil.toJSONObject(obj);
			AuditQualityProblemInfo auditQualityProblemInfo = list.get(i);
			object.put("auditDate", auditQualityProblemInfo.getAuditDate());
			String ipqcAuditResults = transition(auditQualityProblemInfo.getIpqcAuditResults());
			object.put("ipqcAuditResults", ipqcAuditResults);
			String qeAuditResults = transition(auditQualityProblemInfo.getQeAuditResults());
			object.put("qeAuditResults", qeAuditResults);
			String odmAuditResults = transition(auditQualityProblemInfo.getOdmAuditResults());
			object.put("odmAuditResults", odmAuditResults);
			String status = transition(auditQualityProblemInfo.getStatus());
			object.put("status", status);
			jsonObjects.add(object);
		}
		String[] title = AuditQualityProblemInfoExcel;
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			title = AuditQualityProblemInfoDbCamel;
		}
		Workbook workbook = patrolProblemRecordServiceImpl.exporSimple(jsonObjects, title,
				AuditQualityProblemInfoDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, AuditQualityProblemInfoTable);

	}

	@Override
	public Map<String, Object> getDetailednessInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		AuditQualityProblemInfo auditQualityProblemInfo = auditQualityProblemInfoMapper
				.getInfoByTaskId(bean.getTaskId());
		detailedInfoMap.put("detailedInfo", auditQualityProblemInfo);
		return detailedInfoMap;
	}

	@Override
	public List<JSONObject> importAuditQualityProblem(MultipartFile file, HttpServletRequest request) throws Exception {
		File file1 = null;
		String language = request.getHeader("Language");
		file1 = FileUtil.multipartFileToFile(file);
		ArrayList<String> title = new ArrayList<>(Arrays.asList(AuditQualityProblemDbCamel));
		String[] strings = AuditQualityProblemExcel;
		if ("en-US".equals(language)) {
			strings = AuditQualityProblemDbCamel;
		}
		List<JSONObject> list = ExcelUtil.readExcel(file1, title, strings);
		return list;
	}

	@Override
	public void insertAuditQualityProblemInfoList(List<AuditQualityProblemInfo> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			AuditQualityProblemInfo auditQualityProblemInfo = list.get(i);
			auditQualityProblemInfo.setProblemPictureList(null);
			insertAuditQualityProblemInfo(auditQualityProblemInfo);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void getCountAuditEsdEos(String startTime, String endTime) {
		List<AuditQualityProblemInfo> list = auditQualityProblemInfoMapper.getIPQCAuditCount(startTime, endTime);
		int esdCount = 0;
		int eosCount = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AuditQualityProblemInfo auditQualityProblemInfo = list.get(i);
				String faultType = auditQualityProblemInfo.getFaultType();
				if ("工作台ESD接地不良".equals(faultType)) {
					esdCount++;
				} else {
					eosCount++;
				}
			}
		}
		String id = String.valueOf(idGeneratorRunner.nextId());
		String substring = startTime.substring(0, 10);
		String ids = String.valueOf(idGeneratorRunner.nextId());
		List<AuditQualityProblemInfo> odmList = auditQualityProblemInfoMapper.getODMAuditCount(startTime, endTime);
		if (odmList != null && odmList.size() > 0) {
			for (int i = 0; i < odmList.size(); i++) {
				AuditQualityProblemInfo auditQualityProblemInfo = odmList.get(i);
				String faultType = auditQualityProblemInfo.getFaultType();
				if ("工作台ESD接地不良".equals(faultType)) {
					esdCount++;
				} else {
					eosCount++;
				}
			}
		}

		esdDashBoardMapper.insert(id, "E-AuditService", "ESD类", esdCount, substring);
		esdDashBoardMapper.insert(ids, "E-AuditService", "EOS类", eosCount, substring);
	}

	@Override
	public void qimsSendMail() {
		// 查询所有需要超时发邮件的数据
		List<TaskRecord> overTimeTaskRecord = taskRecordMapper.getOverTimeTaskRecord2("IPQCAuditProcess", 24);
		for (TaskRecord taskRecord : overTimeTaskRecord) {
			String id = taskRecord.getId();
			Integer time = qimsSendMailMapper.getTimeById(id);
			int overTimeNum = taskRecord.getOverTimeNum();
			Integer timeTemp = Integer.valueOf((int) Math.floor(overTimeNum / 24));
			timeTemp = timeTemp + 1;
			String assignee = taskRecord.getAssignee();
			String addressee = null;
			String[] cc = null;
			if (isNull(time)) {
				// 发送给处理人
				addressee = emailService.getHandlerMail(assignee);
				time = 1;
			} else {
				if (timeTemp > time) {
					// 发送给处理人的部门经理，抄送给处理人和处理人上级
					addressee = emailService.getHandlerMail(assignee);
					String superior = userMapper.getSuperior(assignee, "COMMON");
					String ccMail = emailService.getHandlerMail(superior);
					cc = new String[] { ccMail };
					time++;
				}
			}
			if (isNotNull(addressee)) {
				// 下发邮件
				qimsSendMailService.sendEmailTohandler(taskRecord, addressee, cc);
				// 修改邮件历史记录的次数
				qimsSendMailMapper.updateById(id, time);
			}
		}
	}

	@Override
	public void downLoadAuditQualityProblem(HttpServletResponse response, HttpServletRequest request,
			List<AuditQualityProblemInfo> list) {
		// 将List<AuditTemplateInfo> 转换为 List<JSONObject>
		List<JSONObject> jsonObjects = new ArrayList<>(list.size());
		list.stream().forEach(d -> {
			JSONObject object = JSONObjectUtil.toJSONObject(d);

			jsonObjects.add(object);
		});
		String[] title = AuditQualityProblemExcel;
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			title = AuditQualityProblemDbCamel;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, AuditQualityProblemDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, AuditQualityProblemInfo);
	}

	private JSONObject additionalFieldInfo(JSONObject jsonObject) {
		String state = jsonObject.getString("state");
		JSONObject variables = jsonObject.getJSONObject("variables");
		SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("CauseAnalyze".equals(state)) {
			variables.put("causalAnalyst", getCurrentUserName());
			String workflowBusinessId = jsonObject.getString("workflow_business_id");
			String auditType = auditQualityProblemInfoMapper.getAuditType(workflowBusinessId);
			variables.put("item", auditType);
		} else if ("IPQCAudit".equals(state)) {
			variables.put("ipqcAuditor", getCurrentUserName());
			variables.put("ipqcConfirmer", getCurrentUserName());
			variables.put("ipqcAuditDate", timeDateFormat.format(new Date()));
		} else if ("QEAudit".equals(state)) {
			variables.put("qeAuditor", getCurrentUserName());
			variables.put("qeAuditDate", timeDateFormat.format(new Date()));
		} else {
			variables.put("odmAuditor", getCurrentUserName());
			variables.put("odmAuditDate", timeDateFormat.format(new Date()));
		}
		variables.put("createDate", timeDateFormat.format(new Date()));
		variables.put("creator", getCurrentUserName());
		return variables;
	}

	private String getCurrentUserName() {
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String currentUserName = currentUser.getAccount();
		return currentUserName;
	}

	private String spliceString(List<String> list) {
		String string = "";
		int flag = 0;
		for (int i = 0; i < list.size(); i++) {
			if (flag > 0) {
				string += ",";
			}
			string += list.get(i);
			flag++;
		}

		return string;
	}

	private String transition(String string) {
		String str = "";
		if (string != "" && string != null) {
			switch (string) {
			case "0":
				str += "通过";
				break;
			case "1":
				str += "不通过";
				break;
			case "ODMAudit":
				str += "ODM审核";
				break;
			case "IPQCAudit":
				str += "IPQC审核";
				break;
			case "QEAudit":
				str += "QE审核";
				break;
			case "CauseAnalyze":
				str += "原因分析/质量改善";
				break;
			case "结案":
				str += "结案";
				break;
			default:
				str = "";
				break;
			}
		}
		return str;
	}
}
