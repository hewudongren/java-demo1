package cn.jwis.qualityworkflow.modules.apqp.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.APQP_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.common.Constants.CN_LANGUAGE;
import static cn.jwis.qualityworkflow.common.Constants.NA;
import static cn.jwis.qualityworkflow.common.Constants.ONGOING_TASK_STATE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecordExample;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.WorkflowVariableReflectMapper;
import cn.jwis.qualityworkflow.enums.WorkflowRoute;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDocumentInfo;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDocumentInfoExample;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpPfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpQcChartRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateSubDocumentVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QuerySubDocumentListVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.UpdateDocumentInfoVo;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpDocumentInfoMapper;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpMasterDocumentMapper;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpSubdocumentMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.RiskItemService;
import cn.jwis.qualityworkflow.modules.apqp.service.SubDocumentService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ImportAndExportUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.UserUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-26 17:08
 * @since 0.1.0
 **/
@Service
public class SubDocumentServiceImpl implements SubDocumentService {


	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	ReflectUtil reflectUtil;

	@Autowired
	ApqpSubdocumentMapper documentDao;

	@Autowired
	ApqpMasterDocumentMapper masterDocumentDao;

	@Autowired
	WorkflowVariableReflectMapper variableReflectMapper;

	@Autowired
	ImportAndExportUtil importAndExportUtil;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;


	@Resource
	ApqpDocumentInfoMapper dcumentInfoDao;

	@Autowired
	RiskItemService riskItemService;

	@Resource
	HistoryProcessRecordMapper historyProcessRecordDao;

	@Autowired
	WorkflowServer workflowServer;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	EmailService emailService;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	UserServer userServer;

	@Value("${env.domamin.name}")
	private String domainName;

	public static final Logger logger = LoggerFactory.getLogger(SubDocumentServiceImpl.class);


	// 需要 进行判定更新主单据的节点
	private static final String[] UPDATEMASTERSTATENODE = new String[]{"DVT1_Audit", "PEMEA_Audit", "NQE"};

	// 多个文件的分隔符
	private static final String DOCUMENTSPLIT = ",";

	private static final String APQP_SUBDOCUMENT_TABLE_NAME = "apqp_subdocument";

	public static final Logger log = LoggerFactory.getLogger(SubDocumentServiceImpl.class);

	/**
	 * @description: 此处不建立事务，是因为如果有一个子单据发生异常 会导致整体回滚，所以，只需要commitRecord 中有事务即可
	 * @author: xujinbiao
	 * @date: 2020/5/26 17:59
	 * @param vo:
	 * @param apqpNumber:
	 * @return: void
	 **/
	@Override
	public void batchInsert(CreateMasterDocument vo, String apqpNumber, String masterId) throws Exception {
		List<CreateSubDocumentVo> list = vo.getList();
		// 循环创建子单据
		if (CollectionUtils.isNotEmpty(list)) {
			for (CreateSubDocumentVo bean: list) {
				// 如果 创建的单据 中三个文档都选了NA则 不创建单据
				if (checkNotAllDocunmentIsNA(bean)) {
					ApqpSubdocument subdocument = new ApqpSubdocument(bean);
					changeNullDocumentToNA(subdocument);
					// 设定 主单据的编号 和masterId
					subdocument.setApqpNumber(apqpNumber);
					subdocument.setMasterId(masterId);
					// 生成id和 creator
					long id = idGeneratorRunner.nextId();
					subdocument.setId(String.valueOf(id));
					String currentUserName = UserUtil.getCurrentUserName();
					subdocument.setCreator(currentUserName);
					// 填充主单据信息
					fillMasterParameter(vo, subdocument);
					subdocument.setStatus("Apply");
					// 设置工作流使用的 localVariables 和 variables
					JSONObject localVariables = JSONObjectUtil.toJSONObject(subdocument);
					List<String> variables = variableReflectMapper.getVariables(APQP_TEMPLATE_KEY, "Apply");
					// 插入全局变量
					if (CollectionUtils.isNotEmpty(variables)) {
						JSONObject object = new JSONObject();
						for (String key: variables) {
							Object value = localVariables.get(key);
							object.put(key, value);
						}
						subdocument.setVariables(object);
					}
					subdocument.setLocalVariables(localVariables);
					reflectUtil.commitRecord(subdocument);
					// 生成 文件单据
					generateDocument(bean, String.valueOf(id));
					sendMail(String.valueOf(id));
				}
			}
			// 开启定时任务
			apqpScheduleTask(list, masterId);
		}
	}


	/**
	 * 根据各节点最早的AlarmData 进行信息汇总统计 发送给QE 和 owner 和主单据创建人
	 * @param list
	 * @param masterId
	 */
	private void apqpScheduleTask(List<CreateSubDocumentVo> list, String masterId) {
		// 汇总出 Dvt1 pfmea qc_chart 对应的最早的AlarmDate;
		if(CollectionUtils.isNotEmpty(list)){
			Date dvt1AlarmDate = null;
			Date pfmeaAlarmDate = null;
			Date qcChartAlarmDate = null;
			for (CreateSubDocumentVo bean: list) {
				Date dvt1Date = bean.getDvt1AlarmDate();
				Date pfmeaDate =  bean.getPemeaAlarmDate();
				Date qcChartDate = bean.getQcChartAlarmDate();
				if (dvt1Date != null && (dvt1AlarmDate == null || dvt1Date.before(dvt1AlarmDate))) {
					dvt1AlarmDate = dvt1Date;
				}
				if (pfmeaDate != null && (pfmeaAlarmDate == null || pfmeaDate.before(pfmeaAlarmDate))) {
					pfmeaAlarmDate = pfmeaDate;
				}
				if (qcChartDate != null && (qcChartAlarmDate == null || qcChartDate.before(qcChartAlarmDate))) {
					qcChartAlarmDate = qcChartDate;
				}
			}
			// 如果对应的时间不为null 需要计算出 相应的秒数 并且开始定时任务
			executeScheduleTask(dvt1AlarmDate, masterId);
			executeScheduleTask(pfmeaAlarmDate, masterId);
			executeScheduleTask(qcChartAlarmDate, masterId);
		}
	}

	private void executeScheduleTask(Date alarmDate, String masterId) {
		Date now = new Date();
		// alarmDate为null 或者 早于当前时间 无需开启延时任务
		if (alarmDate == null || alarmDate.before(now)) {
			return;
		}
		// 所得两个日期相差的秒数
		long second = alarmDate.toInstant().getEpochSecond() - now.toInstant().getEpochSecond();
		logger.info("APQP will execute a schedule summary task in " + second + "later");
		ThreadUtil.getScheduledThreadPool().schedule(() ->{
			List<ApqpSubdocument> list = documentDao.getByMasterId(masterId);
			sendApqpMail(list);
		}, second, TimeUnit.SECONDS);
	}

	/**
	 * Apqp汇总邮件
	 * @param list
	 */
	public void sendApqpMail(List<ApqpSubdocument> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		// 主送主单据创建人，抄送每个子单据的 owner 和 qe
		// 抄送邮件人 最多 = 子单据数量 * 2
		String receiver = null;
		HashSet<String> ccSet = new HashSet<>(list.size() * 2);
		List<JSONObject> mailContentList = new ArrayList<>(list.size());
		// 因为TEST工程段比较特别所以另外存储
		List<JSONObject> testMailContentList = new ArrayList<>(list.size());
		String masterNumber = null;
		for (ApqpSubdocument apqpSubdocument: list) {
			// 处理需要发送的人
			if (receiver == null) {
				receiver = apqpSubdocument.getCreator();
			}
			ccSet.add(apqpSubdocument.getOwner());
			ccSet.add(apqpSubdocument.getQe());
			if (masterNumber == null) {
				masterNumber = apqpSubdocument.getApqpNumber();
			}
			// 根据子单据节点状态判断 追加文件上传状态
			JSONObject object = new JSONObject();
			object.put("engPhase", apqpSubdocument.getEngPhase());
			object.put("owner", apqpSubdocument.getOwner());
			object.put("qe", apqpSubdocument.getQe());
			appendApqpDocunemtStatus(object, apqpSubdocument.getStatus());
			appendApqpDocumentLink(object, apqpSubdocument);
			if (!"TEST".equals(apqpSubdocument.getEngPhase())) {
				mailContentList.add(object);
			} else {
				testMailContentList.add(object);
			}
		}
		// 获取邮箱
		String handlerMail = emailService.getHandlerMail(receiver);
		if (handlerMail == null) {
			logger.info("send APQP summary fail, cause mail receiver-" + receiver + " mail is not exist");
			return;
		}
		// 获取需要抄送的邮箱
		String[] ccMail = new String[ccSet.size()];
		int i = 0;
		for (String cc: ccSet) {
			if (cc == null) {
				return;
			}
			String mail = emailService.getHandlerMail(cc);
			if (mail != null && mail.contains("@")) {
				ccMail[i] = mail;
				i++;
			}
		}
		// 获取主题
		String subject = masterNumber + "文件状态统计!";
		// 生成邮件模板
		Context context = new Context();
		context.setVariable("resultList", mailContentList);
		context.setVariable("testList", testMailContentList);
		String content = templateEngine.process("ApqpSummaryTemplate", context);
		emailService.sendHtmlMail(handlerMail, ccMail, subject, content);
	}

	/**
	 * 根据子单据节点状态判断 追加文件上传状态
	 * @param object
	 * @param status
	 */
	public void appendApqpDocunemtStatus(JSONObject object, String status) {
		if ("DVT1".equals(status)) {
			object.put("dvt1Status", "未上传");
			object.put("pfmeaStatus", "未上传");
			object.put("qcChartStatus", "未上传");
		} else if ("DVT1_Audit".equals(status)) {
			object.put("dvt1Status", "已上传");
			object.put("pfmeaStatus", "未上传");
			object.put("qcChartStatus", "未上传");
		} else if ("PEMEA".equals(status)) {
			object.put("dvt1Status", "已审核");
			object.put("pfmeaStatus", "未上传");
			object.put("qcChartStatus", "未上传");
		} else if ("PEMEA_Audit".equals(status)) {
			object.put("dvt1Status", "已审核");
			object.put("pfmeaStatus", "已上传");
			object.put("qcChartStatus", "未上传");
		} else if ("QC_Chart".equals(status)) {
			object.put("dvt1Status", "已审核");
			object.put("pfmeaStatus", "已审核");
			object.put("qcChartStatus", "未上传");
		} else {
			object.put("dvt1Status", "已审核");
			object.put("pfmeaStatus", "已审核");
			object.put("qcChartStatus", "已上传");
		}
	}

	/**
	 * 新增处理连接 根据子单据id获取最新的OngGoing单据(只有一个) 拼接成链接
	 * @param objetc
	 * @param subdocument
	 */
	public void appendApqpDocumentLink(JSONObject objetc, ApqpSubdocument subdocument) {
		String id = subdocument.getId();
		String status = subdocument.getStatus();
		TaskRecord record = taskRecordDao.getByWorkFlowId(id);
		String url = domainName + "/#" +  WorkflowRoute.getRouteByName(ApqpSubdocument.class.getName()) + id
				+ "?taskId=" + record.getTaskId() + "&processInstanceId=" + record.getProcessInstanceId() + "&step=" + status;
		objetc.put("url", url);
	}



	/**
	 * 发送流程节点文件
	 * @param id
	 */
	private void sendMail(String id) {
		try{
			List<TaskRecord> taskList = taskRecordDao.getUncloseTaskByBusinessId(id);
			if(CollectionUtils.isEmpty(taskList)){
				return;
			}
			TaskRecord curTask = taskList.get(0);
			ApqpSubdocument apqpSubdocument = documentDao.selectByPrimaryKey(id);
			// 交给线程池执行
			ThreadUtil.getThreadPool().execute(() -> {
				String toMail = emailService.getHandlerMail(curTask.getAssignee());
				String subject = "您有一单APQP"+apqpSubdocument.getApqpNumber()+"单据待处理";
				String content = getAPQPMailContent(apqpSubdocument,curTask);
				emailService.sendHtmlMail(toMail,null,subject,content);
			});
		}catch (Exception e){
			log.error("SendMail Error",e);
		}
	}


	private static Map<String,Map<String,Object>> qpqpMap = new HashMap<>();

	static {
		Map<String,Object> dvtMap = new HashMap<>();
		dvtMap.put("engFile","NUDD/DFX");
		dvtMap.put("qeFile","QC-Chart");
		dvtMap.put("engStatus","未上传");
		dvtMap.put("qeStatus","未上传");
		qpqpMap.put("DVT1",dvtMap);

		Map<String,Object> dvtAuditMap = new HashMap<>();
		dvtAuditMap.put("engFile","NUDD/DFX");
		dvtAuditMap.put("qeFile","QC-Chart");
		dvtAuditMap.put("engStatus","待审批");
		dvtAuditMap.put("qeStatus","未上传");
		qpqpMap.put("DVT1_Audit",dvtAuditMap);

		Map<String,Object> pemeaMap = new HashMap<>();
		pemeaMap.put("engFile","PEMEA");
		pemeaMap.put("qeFile","QC-Chart");
		pemeaMap.put("engStatus","未上传");
		pemeaMap.put("qeStatus","未上传");
		qpqpMap.put("PEMEA",pemeaMap);

		Map<String,Object> pemeaAuditMap = new HashMap<>();
		pemeaAuditMap.put("engFile","PEMEA");
		pemeaAuditMap.put("qeFile","QC-Chart");
		pemeaAuditMap.put("engStatus","待审批");
		pemeaAuditMap.put("qeStatus","未上传");
		qpqpMap.put("PEMEA_Audit",pemeaAuditMap);

		Map<String,Object> chartMap = new HashMap<>();
		chartMap.put("engFile","NUDD/DFX/PEMEA");
		chartMap.put("qeFile","QC-Chart");
		chartMap.put("engStatus","已审批");
		chartMap.put("qeStatus","未上传");
		qpqpMap.put("QC_Chart",chartMap);

		Map<String,Object> chartAuditMap = new HashMap<>();
		chartAuditMap.put("engFile","NUDD/DFX/PEMEA");
		chartAuditMap.put("qeFile","QC-Chart");
		chartAuditMap.put("engStatus","已审批");
		chartAuditMap.put("qeStatus","待审批");
		qpqpMap.put("QC_Char_Audit",chartAuditMap);

		Map<String,Object> nqeMap = new HashMap<>();
		nqeMap.put("engFile","NUDD/DFX/PEMEA");
		nqeMap.put("qeFile","QC-Chart");
		nqeMap.put("engStatus","已审批");
		nqeMap.put("qeStatus","已审批");
		qpqpMap.put("NQE",nqeMap);
	}

	private String getAPQPMailContent(ApqpSubdocument apqpSubdocument,TaskRecord curTask) {
		Context context = new Context();
		String status = apqpSubdocument.getStatus();
		String url = domainName + "/#" +  WorkflowRoute.getRouteByName(ApqpSubdocument.class.getName()) + apqpSubdocument.getId()
				+ "?taskId=" + curTask.getTaskId() + "&processInstanceId=" + curTask.getProcessInstanceId() + "&step=" + status;
		context.setVariable("url", url);
		context.setVariable("account",curTask.getAssignee());
		context.setVariable("questionNumber",apqpSubdocument.getApqpNumber());
		context.setVariable("engPhase",apqpSubdocument.getEngPhase());

		context.setVariable("engOwner",apqpSubdocument.getOwner());
		context.setVariable("qeOwner",apqpSubdocument.getQe());

		context.setVariables(qpqpMap.get(status));
		return templateEngine.process("ApqpEmailTemplate", context);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(ConfirmVo bean) throws Exception {
		if (bean.getId() != null && bean.getTaskId() != null && bean.getProcessInstanceId() != null) {
			ApqpSubdocument info = reflectUtil.variablesToObject(bean, ApqpSubdocument.class);
			ApqpSubdocument apqpSubdocument = documentDao.selectByPrimaryKey(bean.getId());
			// 结束当前节点
			String taskId = bean.getTaskId();
			String processInstanceId = bean.getProcessInstanceId();
			JSONObject variables = info.getVariables();
			appendVariables(apqpSubdocument, variables);
			try {
				workflowServer.finishTask(processInstanceId, taskId, variables, info.getLocalVariables());
			} catch (Exception e) {
				log.info("APQP workflow taskId-" + taskId + " finish task error");
			}
			//带入 默认信息
			appendSubdocument(info, bean.getStatus());
			// 更新流程信息
			Map<String, Object> map = reflectUtil.updateProcessInfo(info);
			// 更新主单据完成信息
			updateMasterDocument(bean.getStatus(), map, apqpSubdocument.getMasterId());
			// 发送流程文件
			sendMail(bean.getId());
		} else {
			throw new RuntimeException("请求失败,存在id 或 taskId  或 processInstanceId 为null");
		}
	}

	@Override
	public List<Object> getPullDownValue(String parameter) throws Exception {
		String underlineParameter = JSONObjectUtil.camelToUnderline(parameter);
		List<Object> pullDownValue = documentDao.getPullDownValue(underlineParameter);
		return pullDownValue;
	}

	@Override
	public PageInfo<JSONObject> getInfoList(QuerySubDocumentListVo vo) throws Exception {
		// 给检验时间+1
		reflectUtil.preHandledPageBean(vo);
		String userItemInfos = userServer.getUserItemInfos("APQP-业务管理员");
		vo.setAssignee(userItemInfos);
		Page<ApqpSubdocument> list = documentDao.getInfo(vo);
		List<JSONObject> result = JSONObjectUtil.toJSONObjectList(list);
		importAndExportUtil.transferData(result,APQP_SUBDOCUMENT_TABLE_NAME , CN_LANGUAGE, false);
		PageInfo<JSONObject> page = new PageInfo<>(result);
		page.setTotal(list.getTotal());
		return page;
	}

	@Override
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, QuerySubDocumentListVo vo) throws Exception {
		// 调用查询接口 查出List<EcnInfo>
		vo.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<JSONObject> page = getInfoList(vo);
		List<JSONObject> list = page.getList();
		importAndExportUtil.export(response, request, list, APQP_SUBDOCUMENT_TABLE_NAME);
	}

	@Override
	public Map<String, Object> getDetailednessInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		ApqpSubdocument document = documentDao.selectByPrimaryKey(bean.getWorkflowBusinessId());
		detailedInfoMap.put("detailedInfo",document);
		//追加本流程追加的风险项信息
		String previousProduct = document.getPreviousProduct();
		String engPhase = document.getEngPhase();
		Date updateTime = (CLOSE_TASK_STATE.equals(document.getStatus())) ? document.getUpdateDate() : null;
		List<ApqpDvt1RiskItem> dvt1PreviousModelRiskList = riskItemService.getDvt1PreviousModelRiskList(previousProduct, engPhase, updateTime);
		List<ApqpPfmeaRiskItem> pfmeaPreviousModelRiskList = riskItemService.getPfmeaPreviousModelRiskList(previousProduct, engPhase, updateTime);
		List<ApqpQcChartRiskItem> qcChartPreviousModelRiskList = riskItemService.getQcChartPreviousModelRiskList(previousProduct, engPhase, updateTime);
		JSONObject 	previousModelRiskItem = new JSONObject();
		previousModelRiskItem.put("dvt1PreviousModelRiskList", dvt1PreviousModelRiskList);
		previousModelRiskItem.put("pfmeaPreviousModelRiskList", pfmeaPreviousModelRiskList);
		previousModelRiskItem.put("qcChartPreviousModelRiskList", qcChartPreviousModelRiskList);
		detailedInfoMap.put("previousModelRiskItem", previousModelRiskItem);
		// 追加主单据填写信息
		HistoryProcessRecordExample recordExample = new HistoryProcessRecordExample();
		HistoryProcessRecordExample.Criteria recordCriteria = recordExample.createCriteria();
		recordCriteria.andWorkflowBusinessidEqualTo(document.getMasterId());
		List<HistoryProcessRecord> list = historyProcessRecordDao.selectByExample(recordExample);
		if (CollectionUtils.isNotEmpty(list)) {
			HistoryProcessRecord record = list.get(0);
			JSONObject object = JSONObjectUtil.toJSONObject(record.getContent());
			detailedInfoMap.put("masterDocumentInfo", object);
		}
		return detailedInfoMap;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirmDocument(List<UpdateDocumentInfoVo> list) {
		// 更新文件状态 为Close 表示上传完毕， 自动追加审核时间
		Date now = new Date();
		for (UpdateDocumentInfoVo bean: list) {
			ApqpDocumentInfo info = new ApqpDocumentInfo(bean);
			info.setStatus(CLOSE_TASK_STATE);
			info.setAuditTime(now);
			dcumentInfoDao.updateByPrimaryKeySelective(info);
		}
	}

	@Override
	public List<ApqpDocumentInfo> getDocumentByIdAndType(String id, String type) throws SQLException {
		ApqpDocumentInfoExample example = new ApqpDocumentInfoExample();
		ApqpDocumentInfoExample.Criteria criteria = example.createCriteria();
		criteria.andSubdocumentIdEqualTo(id);
		criteria.andDocumentNodeEqualTo(type);
		List<ApqpDocumentInfo> list = dcumentInfoDao.selectByExample(example);
		return list;
	}


	/**
	 * 生成文件单据
	 */
	private void generateDocument(CreateSubDocumentVo vo, String subdocumentId) throws SQLException {
		String dvt1Document = vo.getDvt1Document();
		String pemeaDocument = vo.getPemeaDocument();
		String qcChartDocument = vo.getQcChartDocument();
		boolean dvt1IsNull = checkDocumentIsNA(dvt1Document);
		boolean pemeaIsNull = checkDocumentIsNA(pemeaDocument);
		boolean qcChartIsNull = checkDocumentIsNA(qcChartDocument);
		List<ApqpDocumentInfo> infos = new ArrayList<>();
		if (!dvt1IsNull) {
			// 分割可能存在的多个文件
			String[] split = dvt1Document.split(DOCUMENTSPLIT);
			for (String documentName :split) {
				ApqpDocumentInfo info = buildDocumentInfo(vo, subdocumentId);
				info.setDocument(documentName);
				info.setDeadline(vo.getDvt1Deadline());
				info.setDocumentNode("DVT1");
				infos.add(info);
			}
		}
		if (!pemeaIsNull) {
			String[] split = pemeaDocument.split(DOCUMENTSPLIT);
			for (String documentName :split) {
				ApqpDocumentInfo info = buildDocumentInfo(vo, subdocumentId);
				info.setDocument(documentName);
				info.setDeadline(vo.getPemeaDeadline());
				info.setDocumentNode("PEMEA");
				infos.add(info);
			}
		}
		if (!qcChartIsNull) {
			String[] split = qcChartDocument.split(DOCUMENTSPLIT);
			for (String documentName :split) {
				ApqpDocumentInfo info = buildDocumentInfo(vo, subdocumentId);
				info.setDocument(documentName);
				info.setDeadline(vo.getQcChartDeadline());
				info.setDocumentNode("QC_Chart");
				infos.add(info);
			}
		}
		if (infos.size() > 0) {
			dcumentInfoDao.batchInsert(infos);
		}
	}

	private ApqpDocumentInfo buildDocumentInfo(CreateSubDocumentVo vo, String subdocumentId) {
		ApqpDocumentInfo info = new ApqpDocumentInfo();
		info.setSubdocumentId(subdocumentId);
		info.setEngPhase(vo.getEngPhase());
		info.setStatus(ONGOING_TASK_STATE);
		long id = idGeneratorRunner.nextId();
		info.setId(String.valueOf(id));
		String currentUserName = UserUtil.getCurrentUserName();
		info.setCreator(currentUserName);
		return info;
	}
	/**
	 * @description: 不同的阶段追加不同的字段信息
	 * @author: xujinbiao
	 * @date: 2020/5/28 16:09
	 * @param document:
	 * @param status:
	 * @return: void
	 **/
	private void appendSubdocument(ApqpSubdocument document, String status) {
		String currentUserName = UserUtil.getCurrentUserName();
		Date now = new Date();
		if ("DVT1".equals(status)) {
			document.setDvt1Uploader(currentUserName);
			document.setDvt1UploadTime(now);
		} else if ("DVT1_Audit".equals(status)) {
			document.setDvt1Auditor(currentUserName);
			document.setDvt1AuditTime(now);
		} else if ("PEMEA".equals(status)) {
			document.setPemeaUploader(currentUserName);
			document.setPemeaUploadTime(now);
		} else if ("PEMEA_Audit".equals(status)) {
			document.setPemeaAuditor(currentUserName);
			document.setPemeaAuditTime(now);
		} else if ("QC_Chart".equals(status)) {
			document.setQcChartUploader(currentUserName);
			document.setQcChartUploadTime(now);
		} else if ("QC_Char_Audit".equals(status)) {
			document.setQcChartAuditor(currentUserName);
			document.setQcChartAuditTime(now);
		} else if ("NQE".equals(status)) {
			document.setNqeAuditTime(now);
		}
	}

	/**
	 * @description: 更新主单据文档
	 * @author: xujinbiao
	 * @date: 2020/5/27 18:24
	 * @param status:
	 * @param map:
	 * @param id:
	 * @return: void
	 **/
	private void updateMasterDocument(String status, Map<String, Object> map, String id) {
		// 如果当前 的节点 是 DVT1文件审核 或者 PEMEA文件审核 或者 QC_Chart
		if (status != null && checkStatus(status)){
			// 节点 是否 审核通过， 也就是被驳回才不会进行更新
			Object temp = map.get("processState");
			String processState = (temp != null) ? String.valueOf(temp) : null;
			if ("DVT1_Audit".equals(status)) {
				if (!"DVT1".equals(processState)) {
					masterDocumentDao.addDvt1CompleteNum(id);
				}
			}
			if ("PEMEA_Audit".equals(status)) {
				if (!"PEMEA".equals(processState)) {
					masterDocumentDao.addPemeaCompleteNum(id);
				}
			}
			if ("NQE".equals(status)) {
				if (!"QC_Chart".equals(processState)) {
					masterDocumentDao.addQcChartCompleteNum(id);
				}
			}
			// 判断 是否 更新主单据为结案状态
			boolean isProcessInstanceComplete = (boolean) map.get("isProcessInstanceComplete");
			if (isProcessInstanceComplete) {
				// 尝试更新主单据的状态
				masterDocumentDao.updateStatus(id);
			}
		}
	}


	/**
	 * @description: 判定节点状态 是否 是更新主单据的节点
	 * @author: xujinbiao
	 * @date: 2020/5/27 17:59
	 * @param status:
	 * @return: boolean
	 **/
	private boolean checkStatus(String status) {
		for (String node : UPDATEMASTERSTATENODE) {
			if (node.equals(status)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @description: 该流程 默认追加三个文档 和 工程段
	 * @author: xujinbiao
	 * @date: 2020/5/27 16:11
	 * @param document:
	 * @param variables:
	 * @return: void
	 **/
	private void appendVariables(ApqpSubdocument document, JSONObject variables) {
		variables.put("dvt1Document", document.getDvt1Document());
		variables.put("pemeaDocument", document.getPemeaDocument());
		variables.put("qcChartDocument", document.getQcChartDocument());
		variables.put("engPhase", document.getEngPhase());
	}

	/**
	 * @description: 填充主单据属性(产品类别,制作类型等)
	 * @author: xujinbiao
	 * @date: 2020/5/26 17:33
	 * @param vo:
	 * @return: void
	 **/
	private void fillMasterParameter(CreateMasterDocument vo,  ApqpSubdocument subdocument) throws Exception {
		subdocument.setModelCategory(vo.getModelCategory());
		subdocument.setManufactureType(vo.getManufactureType());
		subdocument.setItem(vo.getItem());
		subdocument.setProductSeries(vo.getProductSeries());
		subdocument.setPreviousProduct(vo.getPreviousProduct());
		subdocument.setModel(vo.getModel());
	}

	/**
	 * @description: 将
	 * @author: xujinbiao
	 * @date: 2020/5/27 15:37
	 * @param bean:
	 * @return: void
	 **/
	private void changeNullDocumentToNA(ApqpSubdocument bean) {
		if (StringUtils.isEmpty(bean.getDvt1Document())) {
			bean.setDvt1Document(NA);
		}
		if (StringUtils.isEmpty(bean.getPemeaDocument())) {
			bean.setPemeaDocument(NA);
		}
		if (StringUtils.isEmpty(bean.getQcChartDocument())) {
			bean.setQcChartDocument(NA);
		}
	}
	/**
	 * @description: 判断是否所有的文档都是NA, 按照需要如果都是的话，不创建该子单据
	 * @author: xujinbiao
	 * @date: 2020/5/27 14:56
	 * @param vo:
	 * @return: boolean
	 **/
	private boolean checkNotAllDocunmentIsNA(CreateSubDocumentVo vo) {
		String dvt1Document = vo.getDvt1Document();
		String pemeaDocument = vo.getPemeaDocument();
		String qcChartDocument = vo.getQcChartDocument();
		if (checkDocumentIsNA(dvt1Document) && checkDocumentIsNA(pemeaDocument) && checkDocumentIsNA(qcChartDocument)) {
			return false;
		}
		return true;
	}

	/**
	 * @description: 如果 一个文件 是null 或者 "" 或者 NA 都认为是空文件
	 * @author: xujinbiao
	 * @date: 2020/5/27 15:09
	 * @param document:
	 * @return: boolean
	 **/
	private boolean checkDocumentIsNA(String document) {
		if (StringUtils.isNotEmpty(document) && !(NA.equals(document))) {
			return false;
		}
		return true;
	}


}
