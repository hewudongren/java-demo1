package cn.jwis.qualityworkflow.modules.linequalify.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.util.Title.QimsCQADB;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.common.Constants;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.dao.WorkflowVariableReflectMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifyInfo;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifySearch;
import cn.jwis.qualityworkflow.modules.linequalify.dao.LineQualifyMapper;
import cn.jwis.qualityworkflow.modules.linequalify.service.LineQualifyService;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsSendMailMapper;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.TaskRecordService;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.DFSServer;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public class LineQualifyServiceImpl extends BaseClass implements LineQualifyService {
	@Autowired
	IDGeneratorRunner idGeneratorRunner;
	@Autowired
	WorkFlowUtil workFlowUtil;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	TaskRecordService taskRecordService;

	@Autowired
	LineQualifyMapper lineQualifyMapper;
	@Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;

	@Autowired
	WorkflowVariableReflectMapper variableReflectMapper;

	@Autowired
	HistoryProcessRecordService historyProcessRecord;
	@Autowired
	WorkflowServer workflowServer;

	@Autowired
	DFSServer dfsSersver;
	@Value("${qms.app.key}")
	private String tenantId;

	@Value("${env.domamin.name}")
	private String domainName;

	@Autowired
	EmailService emailService;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	QimsSendMailMapper qimsSendMailMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserServer userServer;

	@Override
	@Transactional
	public void saveLineQualifyInfo(LineQualifyInfo bean) throws Exception {
		// 获取业务数据ID
		String lineQualifyId = bean.getId();
		// 获取流程ID
		String processInstanceId = bean.getProcessInstanceId();
		String taskId = bean.getTaskId();
		JSONObject variables = new JSONObject();
		String item = bean.getItem();
		variables.put("item", item);
		String qeHandler = bean.getQeHandler();
		variables.put("qeHandler", qeHandler);
		String esdHandler = bean.getEsdHandler();
		if (StringUtils.isNotEmpty(esdHandler)) {
			variables.put("esdHandler", esdHandler);
		}
		// 清除对应用户拟制单据阶段保存的信息
		historyProcessRecord.deleteRecord("Line Qualify", "拟制单据", lineQualifyId);
		// 判断流程ID是否存在,如果存在就是驳回的数据,不需要新起流程
		if ("0".equals(lineQualifyId)) {
			// 自动生成ID 全局的趋势递增ID
			lineQualifyId = String.valueOf(idGeneratorRunner.nextId());
			bean.setId(lineQualifyId);
			UserInfo currentUser = SessionHelper.getCurrentUser();
			String account = currentUser.getAccount();
			bean.setCreator(account);
			//使用自定义函数获取编号
			String itemNumber = lineQualifyMapper.getItemNumber();
			//这里将业务数据保存到了数据库，
			lineQualifyMapper.saveLineQualifyInfo(bean);
			// 启动流程
			processInstanceId = workFlowUtil.startProcessInstance("creator", account, tenantId, "LineQualifyProcess");
			// 获取最新的Task,
			taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);

			workflowServer.finishTask(processInstanceId, taskId, variables, null);
			// 任务记录表，保存申请Task记录 完成任务后将该任务信息保存到任务表，状态为close
			TaskRecord taskRecordBean = new TaskRecord();
			taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
			taskRecordBean.setWorkflowBusinessId(lineQualifyId);
			String currentUserName = getCurrentUserName();
			taskRecordBean.setAssignee(currentUserName);
			taskRecordBean.setProcessInstanceId(processInstanceId);
			taskRecordBean.setTaskId(taskId);
			taskRecordBean.setTaskName("拟制单据");
			taskRecordBean.setTaskState(CLOSE_TASK_STATE);
			taskRecordBean.setTemplateKey("Line Qualify");
			taskRecordBean.setCreator(currentUserName);
			taskRecordBean.setTheme(bean.getSubjectMatter());
			taskRecordBean.setItemNumber(itemNumber);
			taskRecordDao.insertSelective(taskRecordBean);
		} else {
			lineQualifyMapper.updateLineQualifyById(bean);
			workflowServer.finishTask(processInstanceId, taskId, variables, null);
			taskRecordDao.updateTaskRecord(lineQualifyId, taskId);
		}

		// 保存处理记录
		//将java对象转换成json对象，先将其转换成json字符串
		String jsonString = JSONObject.toJSONString(bean, SerializerFeature.WriteDateUseDateFormat);
		JSONObject jsonObject = JSONObject.parseObject(jsonString);

		historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject, Constants.HISOTORY_PROCESS_COMMIT,
				"Line Qualify", bean.getId(), "拟制单据"));
		// 通过ID获取主题，编号，创建人的信息

		LineQualifyInfo lineQualifyInfo = lineQualifyMapper.getlineQualifyInfo(lineQualifyId);
		String creator = lineQualifyInfo.getCreator();
		String theme = lineQualifyInfo.getSubjectMatter();
		//sn是在插入数据时生成的，这里调接口获取
		String itemNumber = lineQualifyInfo.getSn();
		// 查询当前流程的所有任务,获取当前流程状态
		String status = workFlowUtil.nextNode(processInstanceId, lineQualifyId, "Line Qualify", lineQualifyId, creator,
				theme, itemNumber);
		// 修改基础表信息.
		bean.setStatus(status);
		// 更新流程状态
		lineQualifyMapper.updateById(bean);
	}

	@Override
	@Transactional
	public void confirm(JSONObject bean) throws Exception {
		String workflowBusinessId = bean.getString("workflow_business_id");
		String taskId = bean.getString("task_id");
		String templateKey = bean.getString("templateKey");
		templateKey = "Line Qualify";
		String processInstanceId = bean.getString("process_instance_id");
		String state = bean.getString("state");

		// 通过ID获取主题，编号，创建人的信息
		LineQualifyInfo lineQualifyInfo = lineQualifyMapper.getlineQualifyInfo(workflowBusinessId);
		String creator = lineQualifyInfo.getCreator();
		String theme = lineQualifyInfo.getSubjectMatter();
		String itemNumber = lineQualifyInfo.getSn();

		JSONObject localVariables = bean.getJSONObject("variables");
		if ("文件收集".equals(state)) {
			String department = localVariables.getString("department");
			// 如果是ESD,获取提交的记录
			if ("ESD".equals(department)) {
				// 生成excel
				List<Map<String, String>> fileList = generateEsdExcelFile(localVariables);
				localVariables.put("appendices", fileList);
			}
			// 完成任务,修改任务状态为Close
			taskRecordDao.updateTaskRecord(workflowBusinessId, taskId);
			// 增加一条文件收集审批任务记录
			TaskRecord taskRecordBean = new TaskRecord();
			taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
			taskRecordBean.setWorkflowBusinessId(workflowBusinessId);
			String qeHandler = localVariables.getString("qeHandler");

			taskRecordBean.setAssignee(qeHandler);
			taskRecordBean.setProcessInstanceId(processInstanceId);
			UserInfo currentUser = SessionHelper.getCurrentUser();
			taskRecordBean.setTaskId(taskId + "-" + department + ',' + currentUser.getAccount());
			taskRecordBean.setTaskName("文件收集审核");
			// taskRecordBean.setTaskState(CLOSE_TASK_STATE);
			taskRecordBean.setTemplateKey(templateKey);
			taskRecordBean.setCreator(creator);
			taskRecordBean.setItemNumber(itemNumber);
			taskRecordBean.setTheme(theme);
			taskRecordDao.insertSelective(taskRecordBean);
			ThreadUtil.getThreadPool().execute(() -> {
				workFlowUtil.sendEmailTohandler(itemNumber, taskRecordBean, null);
			});
			historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(localVariables, HISOTORY_PROCESS_COMMIT,
					templateKey, workflowBusinessId, state));
		} else if ("文件收集审核".equals(state)) {
			String fileTaskId = null;
			if (taskId.contains("-")) {
				String[] split = taskId.split("-");
				fileTaskId = split[0];
				bean.put("task_id", fileTaskId);
			}
			// auditResults
			String auditResults = localVariables.getString("auditResults");
//			// 获取前端的审核结果
//			// 判断审核结果
			if ("通过".equals(auditResults)) {
//			// 判断当前节点是否是会签节点最后一个节点
				int nrOfInstances = 0;
				int nrOfCompletedInstances = 0;
				List<Map<String, Object>> processVariables = workflowServer.getProcessVariables(processInstanceId);
				for (Map<String, Object> map : processVariables) {
					Object object2 = map.get("name");
					if (object2 != null) {
						String name = String.valueOf(object2);
						if ("nrOfInstances".equals(name)) {
							Object object3 = map.get("value");
							nrOfInstances = Integer.parseInt(String.valueOf(object3));
						} else if ("nrOfCompletedInstances".equals(name)) {
							Object object4 = map.get("value");
							nrOfCompletedInstances = Integer.parseInt(String.valueOf(object4));
						}
					}
				}
				//会签节点如何指定下一个节点的处理任务 （每一个会签节点都指定吗？？？）
				if (nrOfInstances - nrOfCompletedInstances == 1) {
					// 将该任务状态修改为Close
					taskRecordDao.updateTaskRecord(workflowBusinessId, taskId);
					workFlowUtil.confirm(bean, "Line Qualify", "line_qualify_info", creator, theme, itemNumber);
				} else {
					// 通过完成任务
					// 获取局部变量的值
					workflowServer.finishTask(processInstanceId, fileTaskId, null, localVariables);
					taskRecordDao.updateTaskRecord(workflowBusinessId, taskId);
					CreateHistoryProcessVo generateHistoryProcessRecord = BeanUtil.generateHistoryProcessRecord(
							localVariables, HISOTORY_PROCESS_COMMIT, templateKey, workflowBusinessId, state);
					historyProcessRecord.appendHistoryProcess(generateHistoryProcessRecord, localVariables);
					historyProcessRecord.save(generateHistoryProcessRecord);
				}
			} else {
				// 不通过
				// 修改当前TaskId 文件收集任务状态为null
				taskRecordDao.updateTaskRecordState(workflowBusinessId, fileTaskId);
				// 删除当前TaskId 文件收集审核节点.
				taskRecordDao.deleteTaskRecord(workflowBusinessId, taskId);
				CreateHistoryProcessVo generateHistoryProcessRecord = BeanUtil.generateHistoryProcessRecord(
						localVariables, HISOTORY_PROCESS_COMMIT, templateKey, workflowBusinessId, state);
				historyProcessRecord.appendHistoryProcess(generateHistoryProcessRecord, localVariables);
				historyProcessRecord.save(generateHistoryProcessRecord);
			}
		} else {
			// 调用接口，完成任务
			// 不同的节点做不同的处理
			workFlowUtil.confirm(bean, "Line Qualify", "line_qualify_info", creator, theme, itemNumber);
			if ("QE硬件验收".equals(state)) {
				String approvalResult = localVariables.getString("acceptanceResult");
				if ("不通过".equals(approvalResult)) {
					// 清空流程保存记录
					historyProcessRecord.deleteRejectBeforeRecord("Line Qualify", "拟制单据", workflowBusinessId);
				}
			}
			if ("客户人员验收".equals(state)) {
				String approvalResult = localVariables.getString("approvalResult");
				if ("不通过".equals(approvalResult)) {
					return;
				}
				LineQualifyInfo lineQualify = lineQualifyMapper.getLineQualifyDetailednessInfo(taskId);
				// 获取工序段
				String processSection = lineQualify.getProcessSection();
				String esdEmailList = commonMapper.getEsdEmailList(processSection);
				if (StringUtils.isEmpty(esdEmailList)) {
					return;
				}
				// 获取邮件群组
				String[] cqeHandlerMail = esdEmailList.split(";");
				// 查询群组获取人
				// 抄送人
				String[] cc = null;
				String sn = lineQualify.getSn();
				String line = lineQualify.getLine();
				String model = lineQualify.getModel();
				// 生成内容
				String content = getHandlerMailContent(lineQualify, approvalResult);
				String subject = "Line Qualify" + "-" + sn + "-" + line + "-" + model + "-" + approvalResult;
				// 发送邮件
				emailService.sendHtmlMail(cqeHandlerMail, cc, subject, content);
			}
		}
	}

	private List<Map<String, String>> generateEsdExcelFile(JSONObject localVariables)
			throws IOException, FileNotFoundException {
		String model = localVariables.getString(("model"));
		String line = localVariables.getString(("line"));
		String certificationResult = localVariables.getString(("certificationResult"));
		JSONArray list = localVariables.getJSONArray("fileData");
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		// 使用模版的格式
		// 创建sheet页
		SXSSFSheet sheet = workbook.createSheet();
		SXSSFRow firstRow = sheet.createRow(0);
		SXSSFCell createCell = firstRow.createCell(0);
		createCell.setCellValue("线体: ");
		SXSSFCell createCell1 = firstRow.createCell(1);
		createCell1.setCellValue(line);

		SXSSFCell createCell2 = firstRow.createCell(3);
		createCell2.setCellValue("机型: ");
		SXSSFCell createCell3 = firstRow.createCell(4);
		createCell3.setCellValue(model);

		SXSSFCell createCell4 = firstRow.createCell(6);
		createCell4.setCellValue("认证结果: ");
		SXSSFCell createCell5 = firstRow.createCell(7);
		createCell5.setCellValue(certificationResult);

		String[] title = new String[] { "项目", "功能/模块", "具体要求", "判定", "稽核说明", "改善验证" };
		String[] strings = new String[] { "item", "module", "specificRequirements", "judgment", "auditInstructions",
				"improvedCondition" };
		// 写入标题行
		SXSSFRow titleRow = sheet.createRow(1);
		for (int i = 0; i < title.length; i++) {
			Cell cell = titleRow.createCell(i);
			cell.setCellValue(title[i]);
		}
		// 写入数据行
		int size = 0;
		int length = 0;
		if (list != null) {
			size = list.size();
			length = strings.length;
		}
		if (list != null && size > 0) {
			for (int i = 0; i < size; i++) {
				JSONObject temp = list.getJSONObject(i);
				SXSSFRow row = sheet.createRow(i + 2);
				for (int j = 0; j < length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(subZeroAndDot(JSONObjectUtil.getValue(temp, strings[j])));
				}
			}
		}
		String fileName = line + "线体" + model + "机型ESD验证单.xlsx";
		File file = FileUtil.newFile(fileName);
		OutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		JSONObject uploadFile2DFS = dfsSersver.uploadFile2DFS(file);
		workbook.close();
		fos.close();
		FileUtil.deleteFile(fileName);
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		Map<String, String> fileMap = new HashMap<String, String>();
		// 上传文件
		// 获取文件名和OID,按照附件的格式进行封装
		// 将文件put到流程变量中去
		String fileOid = uploadFile2DFS.getString("oid");
		String name = uploadFile2DFS.getString("fileName");
		fileMap.put("oid", fileOid);
		fileMap.put("name", name);
		fileList.add(fileMap);
		return fileList;
	}

	public String getHandlerMailContent(LineQualifyInfo lineQualify, String approvalResult) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sn = lineQualify.getSn();
		String line = lineQualify.getLine();
		String model = lineQualify.getModel();
		String creator = lineQualify.getCreator();
		Context context = new Context();
		context.setVariable("questionNumber", sn);
		context.setVariable("approvalResult", approvalResult);
		context.setVariable("line", line);
		context.setVariable("model", model);
		context.setVariable("submitter", creator);
		context.setVariable("handlerDate", sdf.format(new Date()));
		String template = templateEngine.process("LineQualifyGroupTemplate", context);
		return template;
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		return null;
	}

	@Override
	public List<LineQualifyInfo> getLineQualifyInfoList(LineQualifySearch lineQualifySearch) {
		List<LineQualifyInfo> result = new ArrayList<>();
//		UserInfo currentUser = SessionHelper.getCurrentUser();
//		String account = currentUser.getAccount();
		String account = userServer.getUserItemInfos("Line Qualify-业务管理员");
		lineQualifySearch.setAssignee(account);
		// pageChange(auditQuestionSearch);
		Integer page = lineQualifySearch.getPage();
		Integer size = lineQualifySearch.getSize();
		lineQualifySearch.setPage((page - 1) * size);
		// 测试用
		result = lineQualifyMapper.getLineQualifyInfoList(lineQualifySearch);
		return result;
	}

	@Override
	public Long getLineQualifyInfoCount(LineQualifySearch lineQualifySearch) {
		// set用户
//		UserInfo currentUser = SessionHelper.getCurrentUser();
//		String account = currentUser.getAccount();
		String account = userServer.getUserItemInfos("Line Qualify-业务管理员");
		lineQualifySearch.setAssignee(account);
		// 测试用
		Long count = lineQualifyMapper.getLineQualifyInfoCount(lineQualifySearch);
		return count;
	}

	@Override
	public void exportLineQualifyInfoList(HttpServletResponse response, HttpServletRequest request,
			LineQualifySearch lineQualifySearch) {
//		UserInfo currentUser = SessionHelper.getCurrentUser();
//		String account = currentUser.getAccount();
		String account = userServer.getUserItemInfos("Line Qualify-业务管理员");
		lineQualifySearch.setAssignee(account);
		List<LineQualifyInfo> qimsBlackInfoList = lineQualifyMapper.getLineQualifyInfoList(lineQualifySearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (LineQualifyInfo qimsCqaInfo : qimsBlackInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsCqaInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, "LineQualify清单.xlsx");
		String[] title = new String[] { "编号", "主题", "产品类别", "制造类型", "项目", "机型", "工艺段", "线体", "交付时间", "处理人", "创建人",
				"创建日期", "状态" };
		String[] strings = new String[] { "sn", "subjectMatter", "productCategory", "manufactureType", "item", "model",
				"processSection", "line", "deliveryTime", "assignee", "creator", "createDate", "status" };
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getLineQualifyDetailednessInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, List<JSONObject>> detailedInfoMaps = historyProcessRecord.getDetailedInfoMaps(bean);
		LineQualifyInfo qimsCqaInfo = lineQualifyMapper.getLineQualifyDetailednessInfo(bean.getTaskId());
		// resultMap.putAll(detailedInfoMaps);
		resultMap.putAll(detailedInfoMaps);
		resultMap.put("detailednessInfo", qimsCqaInfo);
		return resultMap;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		Map<String, String> title = Title.getTitle(request, QimsCQADB, QimsCQAExcel, QimsCQAExcelUS);
		return title;
	}

	@Override
	public List<LineQualifyInfo> getCertificationList(LineQualifySearch lineQualifySearch) {
		List<LineQualifyInfo> result = new ArrayList<>();
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String account = currentUser.getAccount();
		lineQualifySearch.setAssignee(account);
		Integer page = lineQualifySearch.getPage();
		Integer size = lineQualifySearch.getSize();
		lineQualifySearch.setPage((page - 1) * size);
		// 测试用
		result = lineQualifyMapper.getCertificationList(lineQualifySearch);
		return result;
	}

	@Override
	public Long getCertificationCount(LineQualifySearch lineQualifySearch) {
		// set用户
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String account = currentUser.getAccount();
		lineQualifySearch.setAssignee(account);
		// 测试用
		Long count = lineQualifyMapper.getCertificationCount(lineQualifySearch);
		return count;
	}

	@Override
	public void exportCertificationList(HttpServletResponse response, HttpServletRequest request,
			LineQualifySearch lineQualifySearch) {
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String account = currentUser.getAccount();
		lineQualifySearch.setAssignee(account);
		List<LineQualifyInfo> qimsBlackInfoList = lineQualifyMapper.getCertificationList(lineQualifySearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (LineQualifyInfo qimsCqaInfo : qimsBlackInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsCqaInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, "线体认证记录.xlsx");
		String[] title = new String[] { "编号", "主题", "产品类别", "制造类型", "项目", "机型", "工艺段", "线体", "交付时间", "创建人", "创建日期",
				"状态" };
		String[] strings = new String[] { "sn", "subjectMatter", "productCategory", "manufactureType", "item", "model",
				"processSection", "line", "deliveryTime", "creator", "createDate", "status" };
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendOverTimeMail() {
		// 查询所有需要超时发邮件的数据
		List<TaskRecord> overTimeTaskRecord = taskRecordDao.getOverTimeTaskRecord("Line Qualify", "文件收集", 2);
		for (TaskRecord taskRecord : overTimeTaskRecord) {
			String id = taskRecord.getId();
			Integer time = qimsSendMailMapper.getTimeById(id);
			int overTimeNum = taskRecord.getOverTimeNum();
			Integer timeTemp = Integer.valueOf((int) Math.floor(overTimeNum / 2));
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
		LineQualifyInfo lineQualify = lineQualifyMapper.getLineQualifyDetailednessInfo(taskId);
		String line = lineQualify.getLine();
		String model = lineQualify.getModel();
		Context context = new Context();
		context.setVariable("account", assignee);
		context.setVariable("questionNumber", itemNumber);
		context.setVariable("submitter", creator);
		context.setVariable("handler", assignee);
		context.setVariable("node", node);
		context.setVariable("type", templateKey);
		context.setVariable("line", line);
		context.setVariable("model", model);
		context.setVariable("handlerDate", sdf.format(new Date()));
		String url = domainName + "/#/LineCertificationList/step" + "/" + taskId + "/" + processInstanceId + "/"
				+ workflowBusinessId;
		context.setVariable("url", url);
		String template = templateEngine.process("LineQualifyEmailTemplate", context);
		return template;
	}

	@Override
	public JSONObject getProgress(String id) {
		List<JSONObject> resultList = lineQualifyMapper.getProgress(id);
		JSONObject json = new JSONObject();
		for (JSONObject jsonObject : resultList) {
			json.put(jsonObject.getString("task_name"), jsonObject);
		}
		return json;
	}

}