package cn.jwis.qualityworkflow.modules.factoryaudit.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.jwis.qualityworkflow.modules.factoryaudit.service.AuditQuestionService;

/**
 * @Description QIMS超过LT的邮件任务
 * @Author yuyangyang
 * @Date 2020/8/3 16:58
 */
@Component
public class AuditQuestionMailJob {

	public static final Logger logger = LoggerFactory.getLogger(AuditQuestionMailJob.class);

	public final static long FIVEMINUTE = 60 * 1000 * 5;

	@Autowired
	AuditQuestionService auditQuestionService;

	// 每五分钟跑一次定时任务
	@Scheduled(fixedRate = FIVEMINUTE)
	public void sendOverTimeMail() {
		// 查询Factory 问题
		auditQuestionService.sendOverTimeMail();
		logger.info("邮件下发结束");
	}
}