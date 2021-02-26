package cn.jwis.qualityworkflow.modules.qims.job;

import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsSendMailMapper;
import cn.jwis.qualityworkflow.modules.qims.service.QimsSendMailService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static cn.jwis.qualityworkflow.util.JSONObjectUtil.formatByKey;

/**
 * @Description QIMS超过LT的邮件任务
 * @Author yuyangyang
 * @Date 2020/8/3 16:58
 */
@Component
public class qimsMailJob {

	public static final Logger logger = LoggerFactory.getLogger(qimsMailJob.class);

	public final static long FIVEMINUTE = 60 * 1000 * 5;

	@Autowired
	QimsSendMailService qimsSendMailService;
	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;
	@Autowired
	QimsSendMailMapper qimsSendMailMapper;

	//每五分钟跑一次定时任务
	@Scheduled(fixedRate = FIVEMINUTE)
	public void qimsSendMail() {
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		//查询tl的标准
		List<String> keyList = new ArrayList<>();
		keyList.add("type");keyList.add("node");
		List<JSONObject> ltStandard = qimsSendMailMapper.getLtStandard();
		JSONObject formatByKey = formatByKey(ltStandard, keyList, "standard");
		logger.info("查询黑色问题超过LT的信息");
		//查询黑色问题超过LT的信息
		qimsSendMailService.qimsSendMail("黑色问题",holiday,workOvertimeList,formatByKey);
		logger.info("查询CQA问题超过LT的信息");
		//查询CQA问题超过LT的信息
		qimsSendMailService.qimsSendMail("CQA问题",holiday,workOvertimeList,formatByKey);
		logger.info("邮件下发结束");
	}
}