package cn.jwis.qualityworkflow.util;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.util.JSONObjectUtil.camelToUnderline;
import static cn.jwis.qualityworkflow.util.JSONObjectUtil.separate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.dao.WorkflowVariableReflectMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditQualityProblemInfo;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditQualityProblemInfoMapper;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNTaskRecordBean;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;


/**
 * @Description 流程中可以通用使用的方法
 * @Author yuyangyang
 * @Date 2020/5/20 14:46
 */
@Component
public class WorkFlowUtil {

	@Autowired
	WorkflowServer workflowServer;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	TaskRecordMapper taskRecordDao;
	
	@Autowired
	WorkflowVariableReflectMapper variableReflectMapper;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	UserMapper userMapper;

	@Autowired
	CommonMapper commonMapper;
	@Autowired
	HistoryProcessRecordService historyProcessRecordService;
	@Value("${env.domamin.name}")
	private String domainName;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	EmailService emailService;

	@Autowired
	AuditQualityProblemInfoMapper auditQualityProblemInfoMapper;



	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 启动流程实例
	 * @Date 2020/5/20  14:51
	 * @Param
	 */
	public String startProcessInstance(String name, String value, String tenantId, String processDefinitionKey) {
		JSONArray variables = new JSONArray();
		JSONObject variableJson = new JSONObject();
		variableJson.put("name", name);
		variableJson.put("value", value);
		variables.add(variableJson);
		return workflowServer.startProcessInstance(tenantId, processDefinitionKey, variables);
	}

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 创建单据第一次节点
	 * @Date 2020/5/20  15:28
	 * @Param
	 */
	public void finishTask(String processInstanceId, String taskId, String key, String value) {
		JSONObject variables = new JSONObject();
		variables.put(key, value);
		workflowServer.finishTask(processInstanceId, taskId, variables, null);
	}

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 保存到记录表中
	 * @Date 2020/5/20  15:29
	 * @Param
	 */
	public void saveEcnApplyTaskRecord(String id, String processInstanceId, String taskId, String assignee, String taskName, String taskState, String templateKey,String theme,String itemNumber ) {
		TaskRecord taskRecordBean = new TaskRecord();
		taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
		taskRecordBean.setWorkflowBusinessId(id);
		taskRecordBean.setAssignee(assignee);
	    String type = getType(templateKey);
		taskRecordBean.setDepartment(getDepartment(assignee,type));
		taskRecordBean.setProcessInstanceId(processInstanceId);
		taskRecordBean.setTaskId(taskId);
		taskRecordBean.setTaskName(taskName);
		taskRecordBean.setTaskState(taskState);
		taskRecordBean.setTemplateKey(templateKey);
		taskRecordBean.setCreator(assignee);
		taskRecordBean.setTheme(theme);
		taskRecordBean.setItemNumber(itemNumber);
		taskRecordDao.insert(taskRecordBean);
	}

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 第一次创建单据进入下个节点
	 * @Date 2020/5/20  15:43
	 * @Param
	 */
	public String nextNode(String processInstanceId, String id, String templateKey, String workflowId,String creator,String theme,String itemNumber) {
		List<Map<String, Object>> historyTaskList = workflowServer.getProcessHistoryTasks(processInstanceId);
		String processState = "Close";
		String assignee = null;
		TaskRecord taskRecordBean = null;
		// for 循环，如果某个任务的deleteReason字段为null,那它就是当前最新任务，取出任务名称，这就是当前流程的状态。
		for (Map<String, Object> historyTask : historyTaskList) {
			Object deleteReasonObj = historyTask.get("deleteReason");
			if (null == deleteReasonObj) {
				Object nameObj = historyTask.get("name");
				String taskName = String.valueOf(nameObj);
				//任务id有为null的情况吗？？？
				Object idObj = historyTask.get("id");
				if (null == idObj) {
					continue;
				}
				String idTemp = String.valueOf(idObj);
				//任务处理任务有为null的情况吗？？？
				Object assigneeObj = historyTask.get("assignee");
				if (null == assigneeObj) {
					continue;
				}
				assignee = String.valueOf(assigneeObj);
				processState = taskName;
				//需要修改 （将最新任务插入到任务表，状态为null）
				taskRecordBean = new TaskRecord();
				taskRecordBean.setId(id);
				taskRecordBean.setAssignee(assignee);
				String type = getType(templateKey);
				taskRecordBean.setDepartment(getDepartment(assignee,type));
				taskRecordBean.setProcessInstanceId(processInstanceId);
				taskRecordBean.setTaskId(idTemp);
				taskRecordBean.setTaskName(processState);
				taskRecordBean.setTemplateKey(templateKey);
				taskRecordBean.setWorkflowBusinessId(workflowId);
				taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
				taskRecordBean.setCreator(creator);
				taskRecordBean.setTheme(theme);
				taskRecordBean.setItemNumber(itemNumber);
				// 保存任务记录 流程Id assignee ecnId taskId 状态 taskName
				//需要修改
				taskRecordDao.insert(taskRecordBean);
				TaskRecord finalTaskRecordBean = taskRecordBean;
				ThreadUtil.getThreadPool().execute(() -> {
					sendEmailTohandler(itemNumber, finalTaskRecordBean,null);
				});
			}
		}
		return processState;
	}
    /**
     * @Author yuyangyang
     * @Description 流程提交方式
     * @Date  2020/5/20  16:42
     * @Param 
     * @return 
     */
	public void confirm(JSONObject bean,String templateKey,String tableName,String creator,String theme,String itemNumber) {
		// 获取前台传来的TaskId
		String taskId = bean.getString("task_id");
		//获取前台传来的流程模板id
		String processInstanceId = bean.getString("process_instance_id");
		// 获取前天传来的流程ID
		String workflowBusinessId = bean.getString("workflow_business_id");
		// 获取当前节点（用于映射出）
		String  state = bean.getString("state");
		//获取局部变量的值
		JSONObject localVariables = bean.getJSONObject("variables");
		//获取全局变量 (流程变量)
		JSONObject variables = null;
		variables = getVariables(localVariables,templateKey,state);
		// 调用接口，完成任务
		workflowServer.finishTask(processInstanceId, taskId, variables, localVariables);
		// 保存历史数据
		CreateHistoryProcessVo createHistoryProcessVo = BeanUtil.generateHistoryProcessRecord(localVariables, HISOTORY_PROCESS_COMMIT, templateKey, workflowBusinessId, state);
		String rejectReason = null;
		try {
			rejectReason  = historyProcessRecordService.appendHistoryProcess(createHistoryProcessVo, localVariables);
		} catch (Exception e) {
			throw new BaseException("Error adding history field");
		}
		historyProcessRecord.save(createHistoryProcessVo);
		//获取追加字段
		JSONObject jsonObject = appendField(localVariables,state,workflowBusinessId,templateKey);
		updateProcessInfo(jsonObject,processInstanceId,taskId,workflowBusinessId,templateKey,tableName,creator,theme,itemNumber,rejectReason);
	}
   /**
    * @Author yuyangyang
    * @Description 获取当前节点的全局变量
    * @Date  2020/5/20  16:49
    * @Param 
    * @return 
    */
	private JSONObject getVariables(JSONObject localVariables,String templateKey,String state){
		JSONObject variables = new JSONObject();
		boolean flag = false;
		String key = templateKey+state;
		if("ESDAUDIT新建ESD异常单".equals(key)){
			variables.put("causal_analyst",localVariables.get("personLiable"));
			return variables;
		}
		List<String> keyList =  Arrays.asList("QIMSBLACK问题描述","QIMSCQACQA问题描述","ESDACCEPT新建特采单","ESDProcess新建ESD异常单");
		if (keyList.contains(key)){
			flag = true;
		}
		List<String> list = variableReflectMapper.getVariables(templateKey, state);
		Set<String> keySet = localVariables.keySet();
		for (String str :list){
			if (keySet.contains(str)){
				if (flag){
					variables.put(camelToUnderline(str),localVariables.get(str));
				}else {
					variables.put(str,localVariables.get(str));
				}
			}
		}
		return variables;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取当前节点需要追加的变量
	 * @Date  2020/5/20  16:51
	 * @Param
	 * @return
	 */
	private JSONObject appendField(JSONObject localVariables,String state,String workflowBusinessId,String templateKey){
		JSONObject appendField = new JSONObject();
		appendField.put("id",workflowBusinessId);
		List<JSONObject> list = variableReflectMapper.appendField(templateKey, state);
		Set<String> keySet = localVariables.keySet();
		for (JSONObject jsonObject :list){
			String key = jsonObject.getString("key");
			String value = jsonObject.getString("value");
			if (keySet.contains(key)){
				appendField.put(value,localVariables.getString(key));
			}
		}
		return appendField;
	}
    /**
     * @Author yuyangyang
     * @Description 提交，审核改变基础表数据
     * @Date  2020/5/20  16:51
     * @Param
     * @return
     */
	private void updateProcessInfo(JSONObject bean,String processInstanceId,String taskId,String workflowBusinessId,String templateKey,String tableName,String creator,String theme,String itemNumber,String rejectReason) {
		// 更新上个任务状态为关闭
		taskRecordDao.updateTaskRecord(workflowBusinessId,taskId);
		// 当前流程的所有任务
		List<Map<String, Object>> historyTaskList = workflowServer.getProcessHistoryTasks(processInstanceId);
		String processState = "结案";
		String assignee = null;
		TaskRecord taskRecordBean = null;
		// for 循环，如果某个任务的deleteReason字段为null,那它就是当前最新任务，取出任务名称，这就是流程的状态。
		for (Map<String, Object> historyTask : historyTaskList) {
			Object deleteReasonObj = historyTask.get("deleteReason");
			if (null == deleteReasonObj) {
				Object nameObj = historyTask.get("name");
				String taskName = String.valueOf(nameObj);
				Object idObj = historyTask.get("id");
				String id = String.valueOf(idObj);
				Object assigneeObj = historyTask.get("assignee");
				assignee = String.valueOf(assigneeObj);
				processState = taskName;
				taskRecordBean = new TaskRecord();
				taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
				taskRecordBean.setWorkflowBusinessId(workflowBusinessId);
				taskRecordBean.setAssignee(assignee);
				String type = getType(templateKey);
				String department = getDepartment(assignee,type);
				taskRecordBean.setDepartment(department);
				taskRecordBean.setProcessInstanceId(processInstanceId);
				taskRecordBean.setTaskId(id);
				taskRecordBean.setTaskName(processState);
				taskRecordBean.setTemplateKey(templateKey);
				taskRecordBean.setCreator(creator);
				taskRecordBean.setTheme(theme);
				taskRecordBean.setItemNumber(itemNumber);
				// 保存任务记录 流程Id assignee ecnId taskId 状态 taskName
				taskRecordDao.insert(taskRecordBean);
				bean.put("assigneer",assignee);
				bean.put("assigneer_department",department);
				//下发邮件
				TaskRecord finalTaskRecordBean = taskRecordBean;
				ThreadUtil.getThreadPool().execute(() -> {
					sendEmailTohandler(itemNumber, finalTaskRecordBean,rejectReason);
				});
			}
		}
		//会签节点任务的状态值都相同
		bean.put("status",processState);
		List<JSONObject> list = separate(bean);
		commonMapper.update(list,tableName,bean.getString("id"));
	}
	/**
	 * @Author yuyangyang
	 * @Description 通过用户获取部门信息
	 * @Date  2020/6/3  16:38
	 * @Param
	 * @return
	 */
	public String getDepartment(String user,String type){
		return  userMapper.getDepartment(user,type);
	}
    /**
     * @Author yuyangyang
     * @Description 驳回到拟制单据单据节点获取JSON对象
     * @Date  2020/7/8  15:52
     * @Param
     * @return
     */
    public <T> JSONObject getConfirmJson(String id, T bean, String state){
		TaskRecord taskRecord = taskRecordDao.getByTaskWorkFlowId(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("task_id",taskRecord.getTaskId());
		jsonObject.put("process_instance_id",taskRecord.getProcessInstanceId());
		jsonObject.put("workflow_business_id",taskRecord.getWorkflowBusinessId());
		jsonObject.put("state",state);
		JSONObject variables = JSONObjectUtil.toJSONObject(bean);
		jsonObject.put("variables",variables);
		return jsonObject;
	}
    /**
     * @Author yuyangyang
     * @Description 下发邮件
     * @Date  2020/7/28  13:49
     * @Param
     * @return
     */
	public  void  sendEmailTohandler(String itemNumber, TaskRecord taskRecord,String rejectReason){
		String templateKey = taskRecord.getTemplateKey();
		String assignee = taskRecord.getAssignee();
		List<JSONObject> keyToName = commonMapper.getKeyToName();
		JSONObject result = new JSONObject();
		for (JSONObject temp:keyToName) {
			String templateKey1 = temp.getString("template_key");
			result.put(templateKey1,temp);
		}
		JSONObject jsonObject = result.getJSONObject(templateKey);
		String templateName = jsonObject.getString("template_name");
		String url = jsonObject.getString("url");
		// 生成内容
		String content = getHandlerMailContent(itemNumber,taskRecord,templateName,url,rejectReason);
		String cqeHandlerMail = emailService.getHandlerMail(assignee);
		String subject = "您有一单"+templateName+"单据待处理";
		if(StringUtils.isNotEmpty(rejectReason)){
			subject = subject.replace("待处理","被驳回");
		}
		// 发送邮件
		if (StringUtils.isNotEmpty(cqeHandlerMail)){
			String [] cc = null;
			//IPQC的ODM审核节点驳回时会抄送QE的审核人
			List<String> ccAddress = getCcAddress(taskRecord, rejectReason);
			if (CollectionUtils.isNotEmpty(ccAddress)){
				cc = ccAddress.toArray(new String[ccAddress.size()]);
			}
			emailService.sendHtmlMail(cqeHandlerMail, cc, subject, content);
		}

	}
    /**
     * @Author yuyangyang
     * @Description 获取邮件的具体信息
     * @Date  2020/7/28  13:49
     * @Param
     * @return
     */
	public String getHandlerMailContent(String itemNumber,TaskRecord taskRecord,String templateKey,String urlTemp,String rejectReason) {
		List<String> strings = new ArrayList<>();
		strings.add("拟制单据");strings.add("ESD文件收集");strings.add("QE审核");strings.add("IPQC验证");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processInstanceId = taskRecord.getProcessInstanceId();
		String workflowBusinessId = taskRecord.getWorkflowBusinessId();
		String taskId = taskRecord.getTaskId();
		String assignee = taskRecord.getAssignee();
		String taskName = taskRecord.getTaskName();
		String node = taskName;
		String name = commonMapper.getNameByNode(taskName);
		if (StringUtils.isNotEmpty(name)){
			node = name;
		}
		String creator = taskRecord.getCreator();
		Context context = new Context();
		context.setVariable("account", assignee);
		context.setVariable("questionNumber", itemNumber);
		context.setVariable("submitter", creator);
		context.setVariable("handler", assignee);
		context.setVariable("node",node);
		context.setVariable("type",templateKey);
		context.setVariable("handlerDate",sdf.format(new Date()));
		if (StringUtils.isNotEmpty(rejectReason)){
			context.setVariable("rejectReason",rejectReason);
		}
		// 拼接url
		String url = domainName + "/#/"+urlTemp+"/step" + "/" + taskId + "/" + processInstanceId + "/"
				+ workflowBusinessId;
		if ("ESD稽核检验".equals(templateKey)){
			url = domainName + "/#/"+urlTemp+"/step" + "/" + workflowBusinessId + "/" + taskId + "/"
					+ processInstanceId+"/"+taskName;
		}
		if("IPQC稽核".equals(templateKey)){
			url = domainName + "/#/"+urlTemp+"/" + workflowBusinessId + "/"+taskName+"?taskId=" + taskId + "&processInstanceId="
					+ processInstanceId;
		}
		if ("Line Qualify".equals(templateKey) && strings.contains(taskName) ){
			url = domainName + "/#/LineCertificationList/copyStep" + "/" + taskId + "/" + processInstanceId + "/"
					+ workflowBusinessId;
		}

		context.setVariable("url", url);
		String template = templateEngine.process("BlackEmailTemplate", context);
		return template;
	}

	/**
	 * @Author yuyangyang
	 * @Description PCN邮件下发
	 * @Date  2020/7/29  15:42
	 * @Param
	 * @return
	 */
	public  void  sendPcnEmailTohandler(String itemNumber, PCNTaskRecordBean taskRecord,String creator){
		String assignee = taskRecord.getAssignee();
		String taskName = taskRecord.getTaskName();
		// 生成内容
		String content = getPcnHandlerMailContent(itemNumber,taskRecord,taskName,creator);
		String cqeHandlerMail = emailService.getHandlerMail(assignee);
		String subject = "您有一单PCN问题单据待处理";
		// 发送邮件
		if (StringUtils.isNotEmpty(cqeHandlerMail)){
			emailService.sendHtmlMail(cqeHandlerMail, null, subject, content);
		}
	}
	public String getPcnHandlerMailContent(String itemNumber,PCNTaskRecordBean taskRecord,String taskName,String creator) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processInstanceId = taskRecord.getProcessInstanceId();
		String workflowBusinessId = taskRecord.getPcnId();
		String taskId = taskRecord.getTaskId();
		String assignee = taskRecord.getAssignee();
		String node = taskName;
		String name = commonMapper.getNameByNode(taskName);
		if (StringUtils.isNotEmpty(name)){
			node = name;
		}
		Context context = new Context();
		context.setVariable("account", assignee);
		context.setVariable("questionNumber", itemNumber);
		context.setVariable("submitter", creator);
		context.setVariable("handler", assignee);
		context.setVariable("node",node);
		context.setVariable("type","PCN问题");
		context.setVariable("handlerDate",sdf.format(new Date()));
		// 拼接url
		String url = domainName + "/#/pcn/form-management/" + workflowBusinessId + "/"+taskName+"?taskId=" + taskId + "&processInstanceId="
				+ processInstanceId;
		context.setVariable("url", url);
		String template = templateEngine.process("BlackEmailTemplate", context);
		return template;
	}

	/**
	 * @Author yuyangyang
	 * @Description 用户部门表中获取正确的类型
	 * @Date  2020/8/14  10:43
	 * @Param
	 * @return
	 */
	private  String getType(String templateKey){
		String type = "COMMON";
		if ("QIMSBLACK".equals(templateKey)||"QIMSCQA".equals(templateKey)){
			type = "QIMS";
		}
		return type;
	}

	/**
	 * @Author yuyangyang
	 * @Description 判断是否获取QE审核人
	 * @Date  2020/8/29  14:31
	 * @Param
	 * @return
	 */
	private List<String> getCcAddress(TaskRecord taskRecord,String rejectReason){
		List<String> result = new ArrayList<>();
		if (StringUtils.isNotEmpty(rejectReason)){
			String templateKey = taskRecord.getTemplateKey();
			String taskName = taskRecord.getTaskName();
			String taskId = taskRecord.getTaskId();
			if ("IPQCAuditProcess".equals(templateKey) && "CauseAnalyze".equals(taskName)){
				AuditQualityProblemInfo auditQualityProblemInfo = auditQualityProblemInfoMapper.getInfoByTaskId(taskId);
				String qeAuditor = auditQualityProblemInfo.getQeAuditor();
				String handlerMail = emailService.getHandlerMail(qeAuditor);
				if (StringUtils.isNotEmpty(handlerMail)){
					result.add(handlerMail);
				}
			}
		}
		return result;
	}
}