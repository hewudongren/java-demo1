package cn.jwis.qualityworkflow.modules.ipqc.job;

import cn.jwis.qualityworkflow.modules.ipqc.service.AuditQualityProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/29 15:23
 */
@Component
public class ipqcMailJob {
	public static final Logger logger = LoggerFactory.getLogger(ipqcMailJob.class);

	public final static long FIVEMINUTE = 60 * 1000 * 5;

	@Autowired
	AuditQualityProblemService auditQualityProblemService;

	//每五分钟跑一次定时任务
	@Scheduled(fixedRate = FIVEMINUTE)
	public void qimsSendMail() {
		logger.info("查询IPQC超时任务");
		auditQualityProblemService.qimsSendMail();
		logger.info("邮件下发结束");
	}
}