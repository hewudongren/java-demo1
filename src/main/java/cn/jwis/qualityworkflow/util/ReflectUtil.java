package cn.jwis.qualityworkflow.util;

import static cn.jwis.qualityworkflow.common.Constants.APQP_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.common.Constants.ECN_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.EXTERNAL_DOCUMNENT_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.common.Constants.REWORK_TEMPLATE_KEY;
import static java.time.temporal.ChronoUnit.DAYS;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.EmailInfo;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.dao.WorkflowVariableReflectMapper;
import cn.jwis.qualityworkflow.enums.WorkflowEmailSubject;
import cn.jwis.qualityworkflow.enums.WorkflowNodeReflect;
import cn.jwis.qualityworkflow.enums.WorkflowRoute;
import cn.jwis.qualityworkflow.exception.EmailException;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpMasterDocumentMapper;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpSubdocumentMapper;
import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument;
import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import cn.jwis.qualityworkflow.modules.ecn.dao.EcnInfoMapper;
import cn.jwis.qualityworkflow.modules.ecn.dao.ExternalDocumentMapper;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import cn.jwis.qualityworkflow.modules.rework.dao.ReworkInfoMapper;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.TaskRecordService;

import javax.mail.FetchProfile;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 反射工具类
 * 流程 通用的代码工具栏
 * @create 2020-05-08 17:25
 * @since 0.1.0
 **/
@Component
public class ReflectUtil implements InitializingBean {

	@Autowired
	private  WorkflowVariableReflectMapper variableReflectMapper;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@Autowired
	EcnInfoMapper ecnInfoDao;

	@Autowired
	ExternalDocumentMapper externalDocumentDao;

	@Autowired
	ReworkInfoMapper reworkDao;

	@Autowired
	ApqpMasterDocumentMapper apqpMasterDocumentDao;

	@Autowired
	WorkflowServer workflowServer;

	@Autowired
	TaskRecordService taskRecordService;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	ApqpSubdocumentMapper apqpSubdocumentDao;

	@Value("${env.domamin.name}")
	private String domainName;

	@Autowired
	UserMapper userMapper;


	@Autowired
	EmailService emailService;


	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	HistoryProcessRecordMapper historyProcessRecordDao;


	@Value("${qms.app.key}")
	private String tenantId;

	Map<String, Object> daoMap;


	public static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	// bean 属性注入后， 将对应的dao层注入进来对应的map
	@Override
	public void afterPropertiesSet() throws Exception {
		daoMap = new ConcurrentHashMap<>(3);
		daoMap.put(EcnInfo.class.getName(), ecnInfoDao);
		daoMap.put(ExternalDocument.class.getName(), externalDocumentDao);
		daoMap.put(ReworkInfo.class.getName(), reworkDao);
		daoMap.put(ApqpSubdocument.class.getName(), apqpSubdocumentDao);
	}

	/**
	 * 根据Taskid 和 流程类型获取 通用的邮件信息
	 * @param bean  流程类型
	 * @param taskRecord 任务
	 * @param <T>
	 * @return  EmailInfo
	 * @throws Exception
	 */
	public <T> EmailInfo getEmailInfo(T bean, TaskRecord taskRecord) throws EmailException {
		// 获取当前节点处理人 和邮箱
		String assignee = taskRecord.getAssignee();
		if (assignee == null) {
			throw new EmailException("处理人不能为空");
		}
		String handlerMail = emailService.getHandlerMail(taskRecord.getAssignee());
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setHandler(assignee);
		emailInfo.setCurrentNode(taskRecord.getTaskName());
		emailInfo.setHandleTime(DateUtil.formatDate(taskRecord.getCreateDate()));
		emailInfo.setEmail(handlerMail);
		// 获取处理链接
		String handleLink = getHandleLink(taskRecord, bean.getClass().getTypeName());
		emailInfo.setHandleLink(handleLink);
		// 获取基础信息
		try {
			// 获取 流程编号
			if (ReworkInfo.class.getTypeName().equalsIgnoreCase(bean.getClass().getTypeName())){
				Method getReworkNumberMethod = bean.getClass().getMethod("getReworkNumber");
				String reworkNumber = (String)getReworkNumberMethod.invoke(bean);
				emailInfo.setItemNumber(reworkNumber);
				emailInfo.setType("返工流程");
			} else {
				Method getItemNumberMethod = bean.getClass().getMethod("getItemNumber");
				Method getItemTypeMethod = bean.getClass().getMethod("getItemType");
				String itemNumber = (String)getItemNumberMethod.invoke(bean);
				String itemType = (String)getItemTypeMethod.invoke(bean);
				emailInfo.setItemNumber(itemNumber);
				emailInfo.setType(itemType);
			}
			Method getCreatorMethod = bean.getClass().getMethod("getCreator");
			String creator = (String)getCreatorMethod.invoke(bean);
			emailInfo.setCreator(creator);
		}catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException().getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return emailInfo;
	}

	public <T> void sendWorkflowEmail(Class<T> tClass, String workflowBusinessId, String status) {
		// 发送邮件列表
		try {
			if (workflowBusinessId == null) {
				return;
			}
			// 如果当前节点 与传入进来的节点相同 说明 处于 会签节点 会签节点不需要重复发送邮件
			String processCurrentState = taskRecordDao.getProcessCurrentState(workflowBusinessId);
			if (status != null && status.equals(processCurrentState)) {
				return;
			}
			// 发送邮件列表
			List<TaskRecord> unCloseTask = taskRecordDao.getUncloseTaskByBusinessId(workflowBusinessId);
			Object o = daoMap.get(tClass.getName());
			Method selectByPrimaryKeyMethod = o.getClass().getMethod("selectByPrimaryKey", String.class);
			Object bean = selectByPrimaryKeyMethod.invoke(o, workflowBusinessId);
			// 获取history 中的额外信息
			if (status == null) {
				status = "Apply";
			}
			HistoryProcessRecord record = historyProcessRecordDao
					.getNewlyRecordByIdAndNode(workflowBusinessId, status);
			Map<String, Object> map = new ConcurrentHashMap<>(2);
			if (record.getRejectReason() != null) {
				map.put("rejectReason", record.getRejectReason());
			}
			if (tClass != null && unCloseTask != null && unCloseTask.size() > 0) {
				for (TaskRecord taskRecord: unCloseTask) {
					ThreadUtil.getThreadPool().execute(() -> {
						sendTaskEmail(bean, taskRecord, map);
					});
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 流程 发送邮件方法
	 * @param bean 业务对象
	 * @param taskRecord 关联的任务数据
	 * @param map 额外信息
	 * @param <T>
	 */
	public <T> void sendTaskEmail(T bean, TaskRecord taskRecord, Map<String, Object> map) {
		try {
			// 生成邮件内容
			EmailInfo emailInfo = getEmailInfo(bean, taskRecord);
			// 获取发送邮箱
			String handlerMail = emailInfo.getEmail();
			if (handlerMail == null) {
				logger.info("workflow send email fail, cause: " + taskRecord.getAssignee() + " email is not exist");
				return;
			}
			Context context = new Context();
			context.setVariable("account", emailInfo.getHandler());
			context.setVariable("questionNumber", emailInfo.getItemNumber());
			context.setVariable("submitter", emailInfo.getCreator());
			context.setVariable("handler", emailInfo.getHandler());
			context.setVariable("node", WorkflowNodeReflect.getNodeReflect(bean.getClass().getTypeName(), emailInfo.getCurrentNode()));
			context.setVariable("type",emailInfo.getType());
			context.setVariable("handlerDate", emailInfo.getHandleTime());
			context.setVariable("url", emailInfo.getHandleLink());
			context.setVariables(map);
			String content = templateEngine.process("BlackEmailTemplate", context);
			// 获取主题
			String subject = WorkflowEmailSubject.getSubjectByName(bean.getClass().getTypeName());
			if (MapUtils.isNotEmpty(map)){
				subject = subject.replace("待处理", "被驳回");
			}
			// 发送邮件
			emailService.sendHtmlMail(handlerMail, null, subject, content);
		} catch (Exception e) {
			logger.error("workflow send email fail, cause: " + e.getMessage());
		}
	}

	/**
	 * 流程 发送超期邮件方法
	 * @param bean
	 * @param taskRecord
	 * @param map 额外信息
	 * @param <T>
	 */
	public <T> void sendOverdueTaskEmail(T bean, TaskRecord taskRecord, Map<String, Object> map) {
		try {
			// 生成邮件内容
			EmailInfo emailInfo = getEmailInfo(bean, taskRecord);
			String handlerMail = emailInfo.getEmail();
			if (handlerMail == null) {
				logger.info("workflow send email fail, cause: " + taskRecord.getAssignee() + " email is not exist");
				return;
			}
			Context context = new Context();
			context.setVariable("handlerDate", emailInfo.getHandleTime());
			context.setVariable("account", emailInfo.getHandler());
			context.setVariable("submitter", emailInfo.getCreator());
			context.setVariable("questionNumber", emailInfo.getItemNumber());
			context.setVariable("type",emailInfo.getType());
			context.setVariable("url", emailInfo.getHandleLink());
			context.setVariable("handler", emailInfo.getHandler());
			context.setVariable("node", WorkflowNodeReflect.getNodeReflect(bean.getClass().getTypeName(), emailInfo.getCurrentNode()));
			context.setVariables(map);
			String content = templateEngine.process("OverdueEmailTemplate", context);
			// 获取上级处理人邮箱
			String superior = userMapper.getSuperior(emailInfo.getHandler(),"COMMON");
			String[] superiorMails = null;
			if (superior == null) {
				logger.info( taskRecord.getAssignee() + " superior is not exist");
			} else {
				String superiorMail = emailService.getHandlerMail(superior);
				superiorMails = new String[]{superiorMail};
			}
			// 获取主题
			String subject = emailInfo.getType() + emailInfo.getItemNumber() + "超时未处理";
			// 发送邮件
			emailService.sendHtmlMail(handlerMail, superiorMails, subject, content);
		} catch (Exception e) {
			logger.error("workflow send email fail, cause: " + e.getMessage());
		}
	}

	/**
	 *
 	 * @param map
	 * @param taskRecordList
	 * @param <T>
	 */
	public <T> void sendScheduledEmail(Map<String, T> map, List<TaskRecord> taskRecordList) {
		Date now = new Date();
		Instant instant = now.toInstant().plus(-1, DAYS);
		// 将uncloseTask分为大于24小时 和小于等于24小时的
		List<TaskRecord> beforeTasks = taskRecordList.stream()
				.filter(d -> instant.isBefore(d.getCreateDate().toInstant())).collect(Collectors.toList());
		List<TaskRecord> afterTasks = taskRecordList.stream()
				.filter(d -> instant.isAfter(d.getCreateDate().toInstant())).collect(Collectors.toList());
		// 循环处理小于等于24小时
		for (TaskRecord taskRecord: beforeTasks) {
			T bean = map.get(taskRecord.getWorkflowBusinessId());
			if (bean == null) {
				continue;
			}
			// 追加 hoistory表中的信息
			HistoryProcessRecord newlyRecord = historyProcessRecordDao
					.getNewlyRecord(taskRecord.getWorkflowBusinessId(), taskRecord.getTaskName());
			Map<String, Object> infoMap = new ConcurrentHashMap<>(2);
			if (newlyRecord.getRejectReason() != null) {
				infoMap.put("rejectReason", newlyRecord.getRejectReason());
			}
			ThreadUtil.getThreadPool().execute(() -> {
				sendTaskEmail(bean, taskRecord, infoMap);
			});
		}
		// 循环处理 需要升级措施的
		for (TaskRecord taskRecord: afterTasks) {
			T bean = map.get(taskRecord.getWorkflowBusinessId());
			if (bean == null) {
				continue;
			}
			HistoryProcessRecord newlyRecord = historyProcessRecordDao
					.getNewlyRecord(taskRecord.getWorkflowBusinessId(), taskRecord.getTaskName());
			Map<String, Object> infoMap = new ConcurrentHashMap<>(2);
			if (newlyRecord.getRejectReason() != null) {
				infoMap.put("rejectReason", newlyRecord.getRejectReason());
			}
			ThreadUtil.getThreadPool().execute(() -> {
				// 发送邮件给主管
				sendOverdueTaskEmail(bean, taskRecord, infoMap);
			});
		}
	}

	/**
	 * 如果想要使用这个方法 必须 到本类中的 getTemplateKey 和 afterPropertiesSet 配置相应的代码
	 * @description: 更新 工作流流程进度
	 * @Version 1.0.1 去除掉检验TaskId 是否重复， 把这部分代码抽取成@CheckTaskState 注解，在Controller层进行AOP拦截校验
	 * @author: xujinbiao
	 * @date: 2020/5/22 15:34
	 * @return: null
	 **/
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public  <T> Map<String,Object> updateProcessInfo(T bean) throws Exception{
		Method getIdMethod = bean.getClass().getMethod("getId");
		Method getProcessInstanceIdMethod = bean.getClass().getMethod("getProcessInstanceId");
		Method getTaskIdMethod = bean.getClass().getMethod("getTaskId");
		Method setStatusMethod = bean.getClass().getMethod("setStatus", String.class);
		Method setAssigneeMethod = bean.getClass().getMethod("setAssignee", String.class);
		String id = (String) getIdMethod.invoke(bean);
		String processInstanceId = (String) getProcessInstanceIdMethod.invoke(bean);
		String taskId = (String) getTaskIdMethod.invoke(bean);
		// 保存历史记录
		historyProcessRecordService.save(generateHistoryProcessRecord(bean, HISOTORY_PROCESS_COMMIT));
		// 更新上个任务状态为关闭
		taskRecordDao.updateTaskRecord(id, taskId);
		// 获取通用信息Map
		String templateKey = getTemplateKey(bean);
		Map<String, Object> taskCommonInfo = appendTaskCommonInfo(id, templateKey);

		// 当前流程的所有任务
		Map<String, Object> map = taskRecordService.generateTaskRecord(processInstanceId, id, templateKey, taskCommonInfo);
		// map.size == 3 说明返回了下一个节点(主要是针对会签节点)
		boolean isProcessInstanceComplete = (boolean) map.get("isProcessInstanceComplete");
		if (map.size() == 3) {
			setStatusMethod.invoke(bean, String.valueOf(map.get("processState")));
			setAssigneeMethod.invoke(bean, String.valueOf(map.get("assignee")));
		}
		// 会签流程不更新处理人和节点状态
		// 如果是流程已经结束了
		else if (isProcessInstanceComplete) {
			//如果流程 结束 将 所有的流程 对应的task设为Close
			taskRecordDao.closeTaskByBusinessId(id);
			setStatusMethod.invoke(bean, CLOSE_TASK_STATE);
			setAssigneeMethod.invoke(bean, (String) null);
		}
		updateByPrimaryKeySelective(bean);
		return map;
	}

	/**
	 * 如果想要使用这个方法 必须 到本类中的 getTemplateKey 和 afterPropertiesSet 配置相应的代码
	 * @description: 通用的拟制单据阶段方法
	 * 基础表中插入信息 --
	 * 生成工作流实例 --
	 * 保存历史记录 --
	 * 工作流结束Apply阶段 --
	 * 生成Apply节点记录 --
	 * 生成对应的下一节点任务 --
	 * 更新基础信息表
	 * @author: xujinbiao
	 * @date: 2020/5/13 15:12
	 * @param bean:
	 * @return: java.lang.String
	 **/
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public  <T> String commitRecord(T bean) throws Exception{
		String templateKey = getTemplateKey(bean);
		// 生成基础记录
		insert(bean);
		// 启动异常品流程实例 ()
		String processInstanceId = startProcessInstance(templateKey);
		// 获取最新的Task,
		String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
		// 保存提交的历史记录
		// 设置bean的状态
		Method setStatusMethod = bean.getClass().getMethod("setStatus", String.class);
		setStatusMethod.invoke(bean, "Apply");

		Method getIdMethod = bean.getClass().getMethod("getId");
		Method getVariablesMethod = bean.getClass().getMethod("getVariables");
		Method getLocalVariablesMethod = bean.getClass().getMethod("getLocalVariables");
		String id = (String)getIdMethod.invoke(bean);
		JSONObject variables = (JSONObject) getVariablesMethod.invoke(bean);
		JSONObject localVariables = (JSONObject)getLocalVariablesMethod.invoke(bean);

		// 清除 拟制单据阶段的保存的单据（清除保存的草稿数据）
		historyProcessRecordService.deleteApplyRecord(templateKey);
		// 保存历史记录（可以查看流程所有的节点记录）
		historyProcessRecordService.save(generateHistoryProcessRecord(bean, HISOTORY_PROCESS_COMMIT));
		// 结束Apply节点，进入下一个节点
		workflowServer.finishTask(processInstanceId, taskId, variables, localVariables);
		// 获取通用信息Map
		Map<String, Object> taskCommonInfo = appendTaskCommonInfo(id, templateKey);
		// 保存Apply节点记录，状态为已完成（任务表只能查看当前流程最新的节点节流）
		taskRecordService.saveApplyTaskRecord(id, processInstanceId, taskId,  templateKey, taskCommonInfo);
		// 当前流程的所有任务(启动下一个任务)
		Map<String, Object> map = taskRecordService.generateTaskRecord(processInstanceId, id, templateKey, taskCommonInfo);
		String assignee = String.valueOf(map.get("assignee"));
		setStatusMethod.invoke(bean, String.valueOf(map.get("processState")));
		Method setAssigneeMethod = bean.getClass().getMethod("setAssignee", String.class);
		setAssigneeMethod.invoke(bean, assignee);
		updateByPrimaryKey(bean);
		return assignee;
	}

	public  Map<String, Object> appendTaskCommonInfo(String id, String templateKey) {
		Map<String, Object> map = new ConcurrentHashMap<>(4);
		String itemNumber = null;
		String creator = null;
		String theme = null;
		if (ECN_TEMPLATE_KEY.equals(templateKey)) {
			EcnInfo info = ecnInfoDao.selectByPrimaryKey(id);
			itemNumber = info.getItemNumber();
			creator = info.getCreator();
		} else if (EXTERNAL_DOCUMNENT_TEMPLATE_KEY.equals(templateKey)) {
			ExternalDocument externalDocument = externalDocumentDao.selectByPrimaryKey(id);
			itemNumber = externalDocument.getItemNumber();
			creator = externalDocument.getCreator();
		} else if (REWORK_TEMPLATE_KEY.equals(templateKey)) {
			ReworkInfo reworkInfo = reworkDao.selectByPrimaryKey(id);
			itemNumber = reworkInfo.getReworkNumber();
			creator = reworkInfo.getCreator();
			theme = reworkInfo.getReworkTheme();
		} else if (APQP_TEMPLATE_KEY.equals(templateKey)) {
			ApqpSubdocument apqpSubdocument = apqpSubdocumentDao.selectByPrimaryKey(id);
			itemNumber = apqpSubdocument.getApqpNumber();
			creator = apqpSubdocument.getCreator();
			ApqpMasterDocument masterDocument = apqpMasterDocumentDao
					.selectByPrimaryKey(apqpSubdocument.getMasterId());
			theme= masterDocument.getTheme();
		}
		map.put("itemNumber", itemNumber);
		map.put("creator", creator);
		if (theme != null) {
			map.put("theme", theme);
		}
		return map;
	}

	/**
	 * @description: 将ConfirmVo 中 的内容 转换为 对应的数据库 bean <T> 然后给 confirm方法使用
	 * 对应的 T 必须有 id processInstanceId taskId status localVariables 和  variables
	 * @author: xujinbiao
	 * @date: 2020/5/13 12:28
	 * @param bean : 传入的参数（包含private JSONObject variables;）
	 * @param tClass : 要转换的类对象
	 * @return: T 目标类对象
	 **/
	public   <T>  T  variablesToObject(ConfirmVo bean, Class<T> tClass) throws Exception{
		// 先对BeanUtils 的ConvertUtils 注册 一个DateTimeConverter
		DateTimeConverter dtConverter = new DateConverter();
		dtConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"});
		ConvertUtils.register(dtConverter, Date.class);
		// 新建一个实例 将localVariables 内容赋值给 T
		//创建一个实体类类型的对象（封装实体类数据）
		T t = tClass.newInstance();

		JSONObject localVariables = bean.getVariables();
		BeanUtils.populate(t, localVariables);

		//不能直接封装的通过反射的对象方法赋值
		// 设置 流程实例id
		if (bean.getProcessInstanceId() != null) {
			Method setProcessInstanceIdMethod = tClass.getMethod("setProcessInstanceId", String.class);
			setProcessInstanceIdMethod.invoke(t, bean.getProcessInstanceId());
		}
		// 设置任务id
		if (bean.getTaskId() != null) {
			Method setTaskIdMethod = tClass.getMethod("setTaskId", String.class);
			setTaskIdMethod.invoke(t, bean.getTaskId());
		}
		// 设置记录id
		if (bean.getId() != null) {
			Method setIdMethod = tClass.getMethod("setId", String.class);
			setIdMethod.invoke(t, bean.getId());
		}

		String status  = (bean.getStatus() != null) ? bean.getStatus() : "Apply";
		//获取全局变量（获取流程实例当前任务节点所需的变量（动态代理人+连线变量））
		List<String> variables = variableReflectMapper.getVariables(getTemplateKey(t), status);
		if (CollectionUtils.isNotEmpty(variables)) {
			JSONObject object = new JSONObject();
			variables.stream().forEach(key -> {
				Object value = localVariables.get(key.trim());
				object.put(key, value);
			});
			Method setVariablesMethod = tClass.getMethod("setVariables", JSONObject.class);
			setVariablesMethod.invoke(t, object);
		}
		// 系统自动保存提交人和提交时间
		localVariables.put("submitter",UserUtil.getCurrentUserName());
		localVariables.put("processTime", DateUtil.getCurrentDateTime());
		// 设置bean的局部变量
		Method setLocalVariablesMethod = tClass.getMethod("setLocalVariables", JSONObject.class);
		setLocalVariablesMethod.invoke(t, localVariables);
		// 设置bean的状态
		Method setStatusMethod = tClass.getMethod("setStatus", String.class);
		setStatusMethod.invoke(t, status);
		return t;
	}

	/**
	 * @description: 生成 历史记录
	 * 传入 的bean 必须要有  getId getStatus getLocalVariables
	 * @author: xujinbiao
	 * @date: 2020/4/30 17:28
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.HistoryProcessRecord
	 **/
	private <T> CreateHistoryProcessVo generateHistoryProcessRecord(T bean, String type) throws Exception{
		try {
			CreateHistoryProcessVo historyProcess = new CreateHistoryProcessVo();
			String templateKey = getTemplateKey(bean);
			historyProcess.setTemplateKey(templateKey);
			Method getIdMethod = bean.getClass().getMethod("getId");
			Method getStatusMethod = bean.getClass().getMethod("getStatus");
			Method getLocalVariablesMethod = bean.getClass().getMethod("getLocalVariables");
			String id = (String) getIdMethod.invoke(bean);
			String status = (String) getStatusMethod.invoke(bean);
			JSONObject content = (JSONObject) getLocalVariablesMethod.invoke(bean);
			historyProcess.setWorkflowBusinessid(id);
			historyProcess.setWorkflowNode(status);
			historyProcess.setType(type);
			historyProcess.setContent(content);
			historyProcessRecordService.appendHistoryProcess(historyProcess,content );
			return historyProcess;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(bean.getClass().getName() + " Class not support method: getId, getStatus, getLocalVariables");
		}
	}



	/**
	 * @description: 获取 对应 基础类 对应的流程模板key
	 * @author: xujinbiao
	 * @date: 2020/5/13 14:05
	 * @param bean:
	 * @return: java.lang.String
	 **/
	private <T> String getTemplateKey(T bean) throws Exception {
		if (bean instanceof EcnInfo) {
			return ECN_TEMPLATE_KEY;
		} else if (bean instanceof  ExternalDocument) {
			return EXTERNAL_DOCUMNENT_TEMPLATE_KEY;
		} else if (bean instanceof ReworkInfo) {
			return REWORK_TEMPLATE_KEY;
		} else if (bean instanceof ApqpSubdocument) {
			return APQP_TEMPLATE_KEY;
		}
		return null;
	}

	/**
	 * @description: 生成流程实例节点方法
	 * @author: xujinbiao
	 * @date: 2020/5/13 15:08
	 * @param templateKey:
	 * @return: java.lang.String
	 **/
	private String startProcessInstance(String templateKey) {
		JSONArray variables = new JSONArray();
		JSONObject variableJson = new JSONObject();
		String currentUserName = UserUtil.getCurrentUserName();
		variableJson.put("name", "creator");
		variableJson.put("value", currentUserName);
		variables.add(variableJson);
		return workflowServer.startProcessInstance(tenantId, templateKey, variables);
	}

	/**
	 * @description: 执行对应的dao层的insert方法
	 * @author: xujinbiao
	 * @date: 2020/5/13 14:54
	 * @param bean:
	 * @return: void
	 **/
	private <T> void insert(T bean) throws Exception {
		try {
			Object o = daoMap.get(bean.getClass().getName());
			Method insertMethod = o.getClass().getMethod("insert", bean.getClass());
			insertMethod.invoke(o, bean);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException().getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * @description:  执行对应的dao层的updateByPrimaryKey方法
	 * @author: xujinbiao
	 * @date: 2020/5/13 15:38
	 * @param bean:
	 * @return: void
	 **/
	private <T> void updateByPrimaryKey(T bean) throws Exception {
		try {
			Object o = daoMap.get(bean.getClass().getName());
			Method updateByPrimaryKeyMethod = o.getClass().getMethod("updateByPrimaryKey", bean.getClass());
			updateByPrimaryKeyMethod.invoke(o, bean);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException().getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * @description: //执行对应的dao层的updateByPrimaryKeySelective方法
	 * @author: xujinbiao
	 * @date: 2020/5/13 18:41
	 * @param bean:
	 * @return: void
	 **/
	private <T> void updateByPrimaryKeySelective(T bean) throws Exception {
		try {
			Object o = daoMap.get(bean.getClass().getName());
			Method updateByPrimaryKeySelectiveMethod = o.getClass().getMethod("updateByPrimaryKeySelective", bean.getClass());
			updateByPrimaryKeySelectiveMethod.invoke(o, bean);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getTargetException().getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 分页前求前的处理
	 * @param bean
	 * @param <T>
	 */
	public <T extends PageBean> void preHandledPageBean(T bean) {
		if (StringUtils.isNotEmpty(bean.getEndTime())) {
			String endTime = DateUtil.plusOneDay(bean.getEndTime());
			bean.setEndTime(endTime);
		}
		// 处理 分页和 排序
		PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
		PageHelper.orderBy(bean.getOrderBy() + " " + bean.getSortDirection());
	}

	/**
	 *
	 * @param bean
	 * @param <T>
	 */
	public static  <T extends TimeBean>  void preHandleTimeTnterval(T bean) throws Exception{
		// 给检验时间+1
		if (StringUtils.isNotEmpty(bean.getEndTime())) {
			String time = DateUtil.plusOneDay(bean.getEndTime());
			bean.setEndTime(time);
		}
	}

	public String getHandleLink(TaskRecord record, String type) throws EmailException{
		String routeByName = WorkflowRoute.getRouteByName(type);
		if (routeByName == null) {
			throw new EmailException("流程对应的前端跳转路由为空");
		}

		if (ReworkInfo.class.getName().equalsIgnoreCase(type)) {
			String url = domainName + "/#" + routeByName + record.getWorkflowBusinessId() + "/" + record.getTaskName()
					+ "?taskId=" + record.getTaskId() + "&processInstanceId=" + record.getProcessInstanceId();
			return url;
		}
		String url = domainName + "/#" + routeByName + record.getWorkflowBusinessId() + "/" + record.getTaskId() +
				"/" + record.getProcessInstanceId() + "/" + record.getTaskName();
		return url;
	}
}
