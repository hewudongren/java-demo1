package cn.jwis.qualityworkflow.modules.esd.job;

import cn.jwis.qualityworkflow.modules.esd.service.EsdDashBoardService;
import cn.jwis.qualityworkflow.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/28 19:24
 */
@Component
public class EsdDataJob {
	@Resource
	EsdDashBoardService esdDashBoardService;

	public static final Logger logger = LoggerFactory.getLogger(EsdDataJob.class);

	@Scheduled(cron = "0 30 8 * * ?")
	public void getEsdData() throws Exception{
		String endTime  = DateUtil.getEndTime(getCurrentDate());
		String startTime = DateUtil.getStartTimeBefore(endTime);
		logger.info("开始生成总体KPI的数据");
		esdDashBoardService.getEsdData(startTime,endTime);
		logger.info("生成总体KPI的数据结束");
	}
}