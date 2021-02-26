package cn.jwis.qualityworkflow.modules.factoryaudit.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;
import static org.apache.poi.common.usermodel.HyperlinkType.URL;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.common.Constants;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionSearch;
import cn.jwis.qualityworkflow.modules.factoryaudit.dao.AuditQuestionMapper;
import cn.jwis.qualityworkflow.modules.factoryaudit.service.AuditQuestionService;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsSendMailMapper;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.TaskRecordService;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FormatUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public class AuditQuestionServiceImpl extends BaseClass implements AuditQuestionService {

	private static final String FACTORY_AUDIT_BUSINESS_ADMIN = "Factory Audit-业务管理员";

	private static final String FICTITIOUS_DOCUMENTS = "拟制单据";

	private static final String FACTORY_AUDIT_PROCESS = "FactoryAuditProcess";
	@Autowired
	IDGeneratorRunner idGeneratorRunner;
	@Autowired
	WorkFlowUtil workFlowUtil;

	@Autowired
	UserMapper userMapper;

	@Autowired
	TaskRecordMapper taskRecordMapper;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	TaskRecordService taskRecordService;

	@Autowired
	AuditQuestionMapper auditQuestionMapper;

	@Autowired
	HistoryProcessRecordService historyProcessRecord;
	@Autowired
	WorkflowServer workflowServer;
	@Value("${qms.app.key}")
	private String tenantId;

	@Autowired
	EmailService emailService;

	@Value("${env.domamin.name}")
	private String domainName;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	QimsSendMailMapper qimsSendMailMapper;

	@Autowired
	UserServer userServer;

	@Value("${dfs.download.url}")
	private String downloadUrl;

	@Override
	@Transactional
	public void saveAuditQuestionInfo(AuditQuestionInfo bean) throws Exception {
		// 获取业务数据ID
		String lineQualifyId = bean.getId();
		String taskId = bean.getTaskId();
		// 获取流程ID
		String processInstanceId = bean.getProcessInstanceId();
		List<String> problemPictureList = bean.getProblemPictureList();
		if (CollectionUtils.isNotEmpty(problemPictureList)) {
			bean.setProblemPicture(spliceString(problemPictureList));
		}
		// 清除对应用户拟制单据阶段保存的信息
		historyProcessRecord.deleteRecord(FACTORY_AUDIT_PROCESS, FICTITIOUS_DOCUMENTS, lineQualifyId);
		JSONObject variables = new JSONObject();
		String amelioratingOwner = bean.getAmelioratingOwner();
		variables.put("amelioratingOwner", amelioratingOwner);
		// 判断流程ID是否存在,如果存在就是驳回的数据,不需要新起流程
		if ("0".equals(lineQualifyId)) {
			// 自动生成ID
			lineQualifyId = String.valueOf(idGeneratorRunner.nextId());
			bean.setId(lineQualifyId);
			UserInfo currentUser = SessionHelper.getCurrentUser();
			String account = currentUser.getAccount();
			bean.setCreator(account);
			String itemNumber = auditQuestionMapper.getItemNumber();
			auditQuestionMapper.saveAuditQuestionInfo(bean);
			// 启动流程
			processInstanceId = workFlowUtil.startProcessInstance("creator", account, tenantId, FACTORY_AUDIT_PROCESS);
			// 获取最新的Task,
			taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
			workflowServer.finishTask(processInstanceId, taskId, variables, null);
			// 任务记录表，保存申请Task记录
			TaskRecord taskRecordBean = new TaskRecord();
			taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
			taskRecordBean.setWorkflowBusinessId(lineQualifyId);
			String currentUserName = getCurrentUserName();
			taskRecordBean.setAssignee(currentUserName);
			taskRecordBean.setProcessInstanceId(processInstanceId);
			taskRecordBean.setTaskId(taskId);
			taskRecordBean.setTaskName(FICTITIOUS_DOCUMENTS);
			taskRecordBean.setTaskState(CLOSE_TASK_STATE);
			taskRecordBean.setTemplateKey(FACTORY_AUDIT_PROCESS);
			taskRecordBean.setCreator(currentUserName);
			taskRecordBean.setItemNumber(itemNumber);
			taskRecordBean.setTheme(bean.getAuditTopics());
			taskRecordDao.insertSelective(taskRecordBean);
		} else {
			workflowServer.finishTask(processInstanceId, taskId, variables, null);
			taskRecordDao.updateTaskRecord(lineQualifyId, taskId);
		}
		// 保存处理记录
		JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(bean).toString());
		historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject, Constants.HISOTORY_PROCESS_COMMIT,
				FACTORY_AUDIT_PROCESS, bean.getId(), FICTITIOUS_DOCUMENTS));
		// 查询当前流程的所有任务,获取当前流程状态
		AuditQuestionInfo auditQuestionInfo = auditQuestionMapper.getAuditQuestionInfo(lineQualifyId);
		String creator = auditQuestionInfo.getCreator();
		String theme = auditQuestionInfo.getAuditTopics();
		String itemNumber = auditQuestionInfo.getProblemNumber();
		String status = workFlowUtil.nextNode(processInstanceId, lineQualifyId, FACTORY_AUDIT_PROCESS, lineQualifyId,
				creator, theme, itemNumber);
		// 修改基础表信息.
		bean.setStatus(status);
		// 更新流程状态
		auditQuestionMapper.updateById(bean);
	}

	private String spliceString(List<String> list) {
		String string = "";
		int flag = 0;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (flag > 0) {
				string += ",";
			}
			string += list.get(i);
			flag++;
		}
		return string;
	}

	@Override
	@Transactional
	public void confirm(JSONObject bean) throws Exception {
		String workflowBusinessId = bean.getString("workflow_business_id");
		AuditQuestionInfo auditQuestionInfo = auditQuestionMapper.getAuditQuestionInfo(workflowBusinessId);
		String creator = auditQuestionInfo.getCreator();
		String theme = auditQuestionInfo.getAuditTopics();
		String itemNumber = auditQuestionInfo.getProblemNumber();
		// 调用接口，完成任务
		// 不同的节点做不同的处理
		String tableName = "audit_problem_info";
		workFlowUtil.confirm(bean, FACTORY_AUDIT_PROCESS, tableName, creator, theme, itemNumber);
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		return null;
	}

	@Override
	public List<AuditQuestionInfo> getAuditQuestionInfoList(AuditQuestionSearch auditQuestionSearch) {
		List<AuditQuestionInfo> result = new ArrayList<>();
		String account = userServer.getUserItemInfos(FACTORY_AUDIT_BUSINESS_ADMIN);
		auditQuestionSearch.setAssignee(account);
		Integer page = auditQuestionSearch.getPage();
		Integer size = auditQuestionSearch.getSize();
		auditQuestionSearch.setPage((page - 1) * size);
		result = auditQuestionMapper.getAuditQuestionInfoList(auditQuestionSearch);
		return result;
	}

	@Override
	public Long getAuditQuestionInfoCount(AuditQuestionSearch auditQuestionSearch) {
		String account = userServer.getUserItemInfos(FACTORY_AUDIT_BUSINESS_ADMIN);
		auditQuestionSearch.setAssignee(account);
		Long count = auditQuestionMapper.getAuditQuestionInfoCount(auditQuestionSearch);
		return count;
	}

	@Override
	public Map<String, Object> getAuditQuestionDetailednessInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, List<JSONObject>> detailedInfoMaps = historyProcessRecord.getDetailedInfoMaps(bean);
		AuditQuestionInfo qimsCqaInfo = auditQuestionMapper.getAuditQuestionDetailednessInfo(bean.getTaskId());
		resultMap.putAll(detailedInfoMaps);
		resultMap.put("detailednessInfo", qimsCqaInfo);
		return resultMap;
	}

	@Override
	public void exportAuditQuestionList(HttpServletResponse response, HttpServletRequest request,
			AuditQuestionSearch auditQuestionSearch) {
//		UserInfo currentUser = SessionHelper.getCurrentUser();
//		String account = currentUser.getAccount();
		String account = userServer.getUserItemInfos(FACTORY_AUDIT_BUSINESS_ADMIN);
		auditQuestionSearch.setAssignee(account);
		List<AuditQuestionInfo> auditQuestionInfoList = auditQuestionMapper
				.getAuditQuestionInfoList(auditQuestionSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (AuditQuestionInfo auditQuestionInfo : auditQuestionInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(auditQuestionInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, "审核问题清单.xlsx");
		String[] Title = new String[] { "事件编号", "审核主题", "状态", "问题编号", "问题类别", "发生地点", "问题等级", "问题关键字", "问题描述", "问题图片",
				"确认部门", "确认人", "责任部门", "责任人", "备注信息", "处理人", "创建人", "创建时间", "原因分析", "应急对策", "改善措施", "预计改善完成日期", "改善结果",
				"实施时间", "实施人", "验证结果", "LT" };
		String[] strings = new String[] { "eventNumber", "auditTopics", "status", "problemNumber", "problemType",
				"occurrenceSite", "problemLevel", "problemKeywords", "problemDescription", "problemPicture",
				"confirmDepartment", "confirmer", "ownerDepartment", "amelioratingOwner", "remark", "assignee",
				"creator", "createDate", "causeAnalysis", "emergencyResponse", "improvementMeasures",
				"estimatedCompletionTime", "improveResults", "implementationDate", "implementer", "verificationResults",
				"lt" };
		Workbook workbook = exporSimple(jsonObjects, Title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 简单导出(Excel)(xlsx格式)
	 */
	public Workbook exporSimple(List<JSONObject> list, String[] title, String[] strings) {
		// 创建workbook工作
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		// 创建sheet页
		SXSSFSheet sheet = workbook.createSheet();
		// 写入标题行
		SXSSFRow firstRow = sheet.createRow(0);
		for (int i = 0; i < title.length; i++) {
			Cell cell = firstRow.createCell(i);
			cell.setCellValue(title[i]);
			// cell.setCellStyle(titleStyle);
		}
		// 写入数据行
		int size = 0;
		int length = 0;
		if (list != null) {
			size = list.size();
			length = strings.length;
		}
		CreationHelper createHelper = workbook.getCreationHelper();
		if (list != null && size > 0) {
			for (int i = 0; i < size; i++) {
				JSONObject temp = list.get(i);
				SXSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < length; j++) {
					Cell cell = row.createCell(j);
					String key = strings[j];
					String cellValue = subZeroAndDot(JSONObjectUtil.getValue(temp, key));
					if (StringUtils.isNotBlank(cellValue) && "problemPicture".equals(key)) {
						String[] fileInfo = cellValue.split("#");
						String fileOid = fileInfo[0];
						String fileName = fileInfo[2];
						String url = downloadUrl + fileOid;
						XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(URL);
						link.setAddress(url);
						cell.setHyperlink(link);
						cellValue = fileName;
					}
					cell.setCellValue(cellValue);
				}
			}
		}
		return workbook;
	}

	@Override
	public void exportTemplate(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) {
		ExcelUtil.setResponseHeader(response, "审核问题导入模板.xlsx");
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		String[] Title = new String[] { "问题类别", "发生地点", "问题等级", "问题描述", "问题关键字", "确认部门", "确认人", "责任部门", "责任人", "备注信息" };
		String[] strings = new String[] {};
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, Title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getAuditQuestionTrends(AuditQuestionSearch auditQuestionSearch) {
		// 方法传bean,Dao层传bean以上代码都可以删掉
		List<Map<String, Object>> annualTcrrDatas = auditQuestionMapper.getAuditQuestionTrends(auditQuestionSearch);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Set<String> betweenDate = DateUtil.getBetweenDate(5, auditQuestionSearch.getStartTime(),
				auditQuestionSearch.getEndTime());
		for (Map<String, Object> map : annualTcrrDatas) {
			map2.put(map.get("abscissa").toString(), map.get("closeRate"));
			map3.put(map.get("abscissa").toString(), map.get("isTimeRate"));
		}
		map1.put("关闭率", map2);
		map1.put("及时处置率", map3);
		resultMap.put("chartData", map1);
		resultMap.put("abscissa", betweenDate);
		return resultMap;
	}

	@Override
	public JSONObject getKeywordsPlato(AuditQuestionSearch auditQuestionSearch) {
		List<JSONObject> list = auditQuestionMapper.getKeywordsPlato(auditQuestionSearch);
		long count = list.stream().mapToLong(d -> d.getLong("num")).sum();
		JSONObject result = parsePlatoData(count, list);
		return result;
	}

	private JSONObject parsePlatoData(long count, List<JSONObject> list) {
		// 追加累计比率
		List<String> abscissa = new ArrayList<String>();
		JSONObject timeData = new JSONObject(new LinkedHashMap<>());
		if (count != 0) {
			long cumalativeNum = 0L;
			for (JSONObject object : list) {
				long num = object.getIntValue("num");
				cumalativeNum += num;
				String value = FormatUtil.divisionFormat(cumalativeNum, count);
				object.put("rate", Float.valueOf(value));
				// 将结果将入timeData中
				String key = object.getString("problemKeywords");
				abscissa.add(key);
				timeData.put(key, object);
			}
		}
		JSONObject result = new JSONObject();
		result.put("abscissa", abscissa);
		result.put("data", timeData);
		return result;
	}

	@Override
	public void sendOverTimeMail() {
		// 查询所有需要超时发邮件的数据
		List<TaskRecord> overTimeTaskRecord = taskRecordDao.getOverTimeTaskRecord("FactoryAuditProcess", "部门审核", 24);
		for (TaskRecord taskRecord : overTimeTaskRecord) {
			String id = taskRecord.getId();
			Integer time = qimsSendMailMapper.getTimeById(id);
			int overTimeNum = taskRecord.getOverTimeNum();
			String assignee = taskRecord.getAssignee();
			Integer timeTemp = Integer.valueOf((int) Math.floor(overTimeNum / 24));
			timeTemp = timeTemp + 1;
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
				sendEmailTohandler(taskRecord, addressee, cc);
				// 修改邮件历史记录的次数
				qimsSendMailMapper.updateById(id, time);
			}
		}
	}

	private void sendEmailTohandler(TaskRecord taskRecord, String cqeHandlerMail, String[] cc) {
		String templateKey = taskRecord.getTemplateKey();
		String itemNumber = taskRecord.getItemNumber();
		List<JSONObject> keyToName = commonMapper.getKeyToName();
		JSONObject result = new JSONObject();
		for (JSONObject temp : keyToName) {
			String templateKey1 = temp.getString("template_key");
			result.put(templateKey1, temp);
		}
		JSONObject jsonObject = result.getJSONObject(templateKey);
		String templateName = jsonObject.getString("template_name");
		String url = jsonObject.getString("url");
		// 生成内容
		String content = getHandlerMailContent(taskRecord, templateName, url);
		String subject = templateName + itemNumber + "超时未处理";
		// 发送邮件
		emailService.sendHtmlMail(cqeHandlerMail, cc, subject, content);
	}

	public String getHandlerMailContent(TaskRecord taskRecord, String templateKey, String urlTemp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processInstanceId = taskRecord.getProcessInstanceId();
		String workflowBusinessId = taskRecord.getWorkflowBusinessId();
		String taskId = taskRecord.getTaskId();
		String assignee = taskRecord.getAssignee();
		String taskName = taskRecord.getTaskName();
		String itemNumber = taskRecord.getItemNumber();
		String node = taskName;
		String creator = taskRecord.getCreator();
		Context context = new Context();
		context.setVariable("account", assignee);
		context.setVariable("questionNumber", itemNumber);
		context.setVariable("submitter", creator);
		context.setVariable("handler", assignee);
		context.setVariable("node", node);
		context.setVariable("type", templateKey);
		context.setVariable("handlerDate", sdf.format(new Date()));
		String url = domainName + "/#/AuditQuestionList/step" + "/" + taskId + "/" + processInstanceId + "/"
				+ workflowBusinessId;
		context.setVariable("url", url);
		String template = templateEngine.process("BlackEmailTemplate", context);
		return template;
	}
}