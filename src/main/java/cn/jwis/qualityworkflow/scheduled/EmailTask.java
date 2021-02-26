package cn.jwis.qualityworkflow.scheduled;

import static cn.jwis.qualityworkflow.common.Constants.ECN_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.EXTERNAL_DOCUMNENT_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.REWORK_TEMPLATE_KEY;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpSubdocumentMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.imp.SubDocumentServiceImpl;
import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument;
import cn.jwis.qualityworkflow.modules.ecn.dao.EcnInfoMapper;
import cn.jwis.qualityworkflow.modules.ecn.dao.ExternalDocumentMapper;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import cn.jwis.qualityworkflow.modules.rework.dao.ReworkInfoMapper;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;

/**
 * 邮件处理规则 每天早8点 向所有未处理的节点 发送待处理邮件
 * 分两种情况， 1. 第一次发送 也就是 小于等于 24小时的，发送给对应处理人人
 * 				2. 后续的发送 也就是 超过24小时的 发送给对应处理人 以及 处理人对应的上级领导
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-04 10:47
 * @since 0.1.0
 **/

@Component
public class EmailTask {



	public static final Logger logger = LoggerFactory.getLogger(EmailTask.class);

	@Autowired
	EcnInfoMapper ecnDao;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	ExternalDocumentMapper externalDocumentDao;

	@Autowired
	ReworkInfoMapper reworkInfoDao;

	@Autowired
	ApqpSubdocumentMapper apqpSubdocumentDao;

	@Autowired
	ReflectUtil reflectUtil;

	@Autowired
	EmailService emailService;

	@Autowired
	SubDocumentServiceImpl subDocumentService;

	/**
	 * 每天 8点 开启定时任务 ECN  流程
	 */
	@Scheduled(cron = "0 0 8 ? * *")
//	@Scheduled(cron = "0 5 * * * *")
	public void ecnAlarmTask() {
		List<TaskRecord> uncloseTasks = taskRecordDao.getUncloseTaskByTemplateKey(ECN_TEMPLATE_KEY);
		// 获取对应的 单据 并且转成Map
		List<String> workflowIds = uncloseTasks.stream()
				.map(d -> d.getWorkflowBusinessId()).distinct().collect(Collectors.toList());
		List<EcnInfo> ecnInfos = ecnDao.selectByPrimaryKeys(workflowIds);
		Map<String, EcnInfo> ecnMaps = new ConcurrentHashMap<>(ecnInfos.size());
		for (EcnInfo info: ecnInfos) {
			ecnMaps.put(info.getId(), info);
		}
		reflectUtil.sendScheduledEmail(ecnMaps, uncloseTasks);
	}

	/**
	 * 每天 8点 开启定时任务 外来文件  流程
	 */
	@Scheduled(cron = "0 0 8 ? * *")
	public void externalAlarmTask() {
		//查找任务表里该类型的任务状态还没有关闭的任务
		List<TaskRecord> taskRecords = taskRecordDao.getUncloseTaskByTemplateKey(EXTERNAL_DOCUMNENT_TEMPLATE_KEY);
		// 获取对应的 单据 并且转成Map
		List<String> workflowIds = taskRecords.stream()
				.map(d -> d.getWorkflowBusinessId()).distinct().collect(Collectors.toList());
		List<ExternalDocument> externalDocuments = externalDocumentDao.selectByPrimaryKeys(workflowIds);
		Map<String, ExternalDocument> externalDocumentMap = new ConcurrentHashMap<>(externalDocuments.size());
		for (ExternalDocument info: externalDocuments) {
			externalDocumentMap.put(info.getId(), info);
		}
		reflectUtil.sendScheduledEmail(externalDocumentMap, taskRecords);
	}

	/**
	 * 每天 8点 开启定时任务 返工流程
	 */
	@Scheduled(cron = "0 0 8 ? * *")
	public void reworkAlarmTask() {
		List<TaskRecord> taskRecords = taskRecordDao.getUncloseTaskByTemplateKey(REWORK_TEMPLATE_KEY);
		// 获取对应的 单据 并且转成Map
		List<String> ids = taskRecords.stream()
				.map(d -> d.getWorkflowBusinessId()).distinct().collect(Collectors.toList());
		List<ReworkInfo> reworkInfos = reworkInfoDao.selectByPrimaryKeys(ids);
		Map<String, ReworkInfo> reworkInfoMap = new ConcurrentHashMap<>(reworkInfos.size());
		for (ReworkInfo info: reworkInfos) {
			reworkInfoMap.put(info.getId(), info);
		}
		reflectUtil.sendScheduledEmail(reworkInfoMap, taskRecords);
	}

	/**
	 * 每天8点 开启定时任务  发送APQP主单据汇总邮件
	 */
	@Scheduled(cron = "0 0 8 ? * *")
	public void apqpSummaryTask() {
		List<ApqpSubdocument> summaryList = apqpSubdocumentDao.getSummaryList();
		Map<String, List<ApqpSubdocument>> map = summaryList.stream().collect(Collectors.groupingBy(d -> d.getMasterId()));
		// map.size 代表需要发送的邮件数量
		for (Map.Entry<String, List<ApqpSubdocument>> entry: map.entrySet()) {
			List<ApqpSubdocument> list = entry.getValue();
			ThreadUtil.getThreadPool().execute(() ->{
				subDocumentService.sendApqpMail(list);
			});
		}
	}



}
