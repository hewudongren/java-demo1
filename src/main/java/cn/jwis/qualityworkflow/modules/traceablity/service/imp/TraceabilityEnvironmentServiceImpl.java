package cn.jwis.qualityworkflow.modules.traceablity.service.imp;

import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.TraceabilityEnvironmentInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.TraceabilityEnvironmentInfoExcel;
import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.TraceabilityEnvironmentInfoTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityEnvironmentVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentConfiguration;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentData;
import cn.jwis.qualityworkflow.modules.traceablity.dao.TraceabilityEnvironmentDataMapper;
import cn.jwis.qualityworkflow.modules.traceablity.dao.TraceablityEnvironmentMappingMapper;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityEnvironmentService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

@Service
public class TraceabilityEnvironmentServiceImpl implements TraceabilityEnvironmentService {

	@Autowired
	TraceabilityEnvironmentDataMapper traceabilityEnvironmentDataMapper;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	TraceablityEnvironmentMappingMapper traceablityEnvironmentMappingMapper;

	private static SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public JSONObject getTraceabilityEnvironmentData(QueryTraceabilityEnvironmentVo bean) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String line = bean.getLine();
		String probeNumber = bean.getProbeNumber();
		if (StringUtils.isBlank(line) || StringUtils.isBlank(probeNumber)) {
			return jsonObject;
		}
		Map<String, String> paramMap = getParamMap();
		// 通过探头编号查询配置表，获取可见参数
		List<String> configurationParameter = traceablityEnvironmentMappingMapper.getConfigurationParameter(line,
				probeNumber);
		if (CollectionUtils.isEmpty(configurationParameter)) {
			return jsonObject;
		}
		Set<Entry<String, String>> entrySet = paramMap.entrySet();
		JSONObject dataObject = null;
		List<Map<String, Object>> paramDataList = null;
		for (Entry<String, String> entry : entrySet) {
			String value = entry.getValue();
			if (!configurationParameter.contains(value)) {
				continue;
			}
			String paramName = entry.getKey();
			bean.setParameter(paramName);
			paramDataList = traceabilityEnvironmentDataMapper.getParamData(bean);
			bean.setParameter(value);
			dataObject = new JSONObject();
			dataObject.put("average", paramDataList);
			dataObject = setEnvironmentConfiguration(bean, dataObject);
			jsonObject.put(value, dataObject);
		}
		return jsonObject;
	}

	private Map<String, String> getParamMap() {
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		paramMap.put("temperature", "温度");
		paramMap.put("humidity", "湿度");
		paramMap.put("illuminance", "光照度");
		paramMap.put("cleanliness", "洁净度");
		return paramMap;
	}

	private JSONObject setEnvironmentConfiguration(QueryTraceabilityEnvironmentVo bean, JSONObject jsonObject) {
		TraceabilityEnvironmentConfiguration List = traceabilityEnvironmentDataMapper.getEnvironmentConfiguration(bean);
		if (List != null) {
			jsonObject.put("specificationFloorValue", List.getSpecificationFloorValue());
			jsonObject.put("specificationUpValue", List.getSpecificationUpValue());
			jsonObject.put("specificationCentreValue", List.getSpecificationCentreValue());
		} else {
			jsonObject.put("specificationFloorValue", "0");
			jsonObject.put("specificationUpValue", "0");
			jsonObject.put("specificationCentreValue", "0");
		}
		return jsonObject;
	}

	@Override
	public void insertTraceabilityEnvironment(TraceabilityEnvironmentData bean) {
		bean.setId(String.valueOf(idGeneratorRunner.nextId()));
		bean.setCreateDate(timeDateFormat.format(new Date()));
		traceabilityEnvironmentDataMapper.insertTraceabilityEnvironment(bean);
	}

	@Override
	public void exportTraceabilityEnvironmentData(HttpServletResponse response, HttpServletRequest request,
			QueryTraceabilityEnvironmentVo bean) {
		List<TraceabilityEnvironmentData> list = traceabilityEnvironmentDataMapper
				.exportTraceabilityEnvironmentData(bean);
		// 将List<PCNListBean> 转换为 List<JSONObject>
		List<JSONObject> jsonObjects = new ArrayList<>(list.size());
		list.stream().forEach(d -> {
			JSONObject object = JSONObjectUtil.toJSONObject(d);
			jsonObjects.add(object);
		});
		String[] title = TraceabilityEnvironmentInfoExcel;
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			title = TraceabilityEnvironmentInfoDbCamel;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, TraceabilityEnvironmentInfoDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, TraceabilityEnvironmentInfoTable);
	}

	@Override
	public JSONObject getTraEnvironmentPullDownInfo() {
		List<TraceabilityEnvironmentConfiguration> list = traceabilityEnvironmentDataMapper
				.getTraEnvironmentPullDownInfo();
		JSONObject jsonObject = new JSONObject();
		List<String> list1 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			TraceabilityEnvironmentConfiguration bean = list.get(i);
			if (bean.getLine() != null && bean.getLine() != "" && !list1.contains(bean.getLine())) {
				list1.add(bean.getLine());
			}
		}
		jsonObject.put("LineList", list1);
		return jsonObject;
	}

	@Override
	public List<String> getProbeNumber(JSONObject jsonObject) {
		List<String> list = traceabilityEnvironmentDataMapper.getProbeNumber(jsonObject);
		return list;
	}

	@Override
	public List<TraceabilityEnvironmentConfiguration> getEnvConfiguration(QueryTraceabilityEnvironmentVo bean) {
		List<TraceabilityEnvironmentConfiguration> resultList = null;
		if (bean.getPageNum() != null && bean.getPageSize() != null) {
			bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
		}
		resultList = traceablityEnvironmentMappingMapper.getEnvConfiguration(bean);
		return resultList;
	}

	@Override
	public Long getEnvConfigurationCount(QueryTraceabilityEnvironmentVo bean) {
		Long count = traceablityEnvironmentMappingMapper.getEnvConfigurationCount(bean);
		return count;
	}

	@Override
	public int insertEnvConfiguration(TraceabilityEnvironmentConfiguration bean) {
		int code = 0;
		if (bean.getId() != "" && bean.getId() != null) {
			traceablityEnvironmentMappingMapper.updateEnvConfiguration(bean);
		} else {
			TraceabilityEnvironmentConfiguration configuration = traceablityEnvironmentMappingMapper
					.getConfiguration(bean);
			if (configuration != null) {
				code = -1;
			} else {
				bean.setId(String.valueOf(idGeneratorRunner.nextId()));
				bean.setCreator(getCurrentUserName());
				bean.setCreateDate(timeDateFormat.format(new Date()));
				traceablityEnvironmentMappingMapper.insertEnvConfiguration(bean);
			}

		}
		return code;
	}

	@Override
	public JSONObject getEnvConfigurationById(JSONObject jsonObject) {

		return traceablityEnvironmentMappingMapper.getEnvConfigurationById(jsonObject);
	}

	@Override
	public void deleteEnvConfiguration(JSONObject jsonObject) {
		traceablityEnvironmentMappingMapper.deleteEnvConfiguration(jsonObject);
	}

	@Override
	public JSONObject getEnvConfigurationPullDownInfo() {
		List<TraceabilityEnvironmentConfiguration> list = traceablityEnvironmentMappingMapper
				.getEnvConfigurationPullDownInfo();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				TraceabilityEnvironmentConfiguration bean = list.get(i);
				if (bean.getProbeNumber() != "" && bean.getProbeNumber() != null
						&& !list1.contains(bean.getProbeNumber())) {
					list1.add(bean.getProbeNumber());
				}
				if (bean.getParameter() != "" && bean.getParameter() != null && !list2.contains(bean.getParameter())) {
					list2.add(bean.getParameter());
				}

			}
			jsonObject.put("probeNumberList", list1);
			jsonObject.put("parameter", list2);
		}

		return jsonObject;
	}

	private String getCurrentUserName() {
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String currentUserName = currentUser.getAccount();
		return currentUserName;
	}
}
