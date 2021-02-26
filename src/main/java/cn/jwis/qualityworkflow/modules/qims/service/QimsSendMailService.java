package cn.jwis.qualityworkflow.modules.qims.service;

import cn.jwis.qualityworkflow.bean.TaskRecord;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/3 18:17
 */
@Service
public interface QimsSendMailService {

	/**
	 * @Author yuyangyang
	 * @Description 超时邮件任务
	 * @Date  2020/9/7  13:59
	 * @Param
	 * @return
	 */
	void  qimsSendMail(String type, List<String> holiday, List<String> workOvertimeList,JSONObject formatByKey);

	/**
	 * @Author yuyangyang
	 * @Description 下发邮件的具体实现方法
	 * @Date  2020/9/7  13:59
	 * @Param
	 * @return
	 */
	void  sendEmailTohandler(TaskRecord taskRecord, String cqeHandlerMail, String[] cc);
}