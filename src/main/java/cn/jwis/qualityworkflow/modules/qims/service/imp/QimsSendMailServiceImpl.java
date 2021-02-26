package cn.jwis.qualityworkflow.modules.qims.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsSendMailMapper;
import cn.jwis.qualityworkflow.modules.qims.service.QimsSendMailService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/3 18:18
 */
@Service
public class QimsSendMailServiceImpl extends BaseClass implements QimsSendMailService {
	@Autowired
	QimsSendMailMapper qimsSendMailMapper;

	@Autowired
	EmailService emailService;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	TaskRecordMapper taskRecordMapper;

	@Value("${env.domamin.name}")
	private String domainName;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	UserMapper userMapper;


	@Override
	public void qimsSendMail(String type,List<String> holiday,List<String> workOvertimeList,JSONObject formatByKey) {
		List<JSONObject> list = new ArrayList<>();
		String flag ="CQA问题";
		if ("黑色问题".equals(type)){
			list = qimsSendMailMapper.getBlackList();
			flag = "黑色问题";
		}else {
			list = qimsSendMailMapper.getCqaList();
		}
		for (JSONObject temp:list) {
			Date createDate = temp.getDate("create_date");
			String taskName = temp.getString("task_name");
			String id = temp.getString("id");
			String assignee = temp.getString("assignee");
			String lt = DateUtil.getTl(createDate, new Date(), holiday, workOvertimeList);
			//判断是否发邮件
			String key = flag+taskName;
			String standard = formatByKey.getString(key);
			//如果LT超过对应的标准
			Double ltDouble = Double.valueOf(lt);
			Double standardDouble = Double.valueOf(standard);
			if (ltDouble > standardDouble){
				//获取不同的邮件人和下发邮件的标识
				JSONObject jsonObject = isSendEmail(standardDouble,ltDouble,id, assignee);
				//下发邮件并修改邮件记录的数据
				if (isNotNull(jsonObject)){
					Integer time = jsonObject.getInteger("time");
					String addressee = jsonObject.getString("addressee");
					if(isNotNull(addressee)){
						List<String> list1 = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("cc"));
						String[] cc = list1.toArray(new String[list1.size()] );
						TaskRecord taskRecord = taskRecordMapper.getById(id);
						//下发邮件
						sendEmailTohandler(taskRecord,addressee,cc);
						// 修改邮件历史记录的次数
						qimsSendMailMapper.updateById(id,time);
					}
				}
			}
		}
	}
    /**
     * @Author yuyangyang
     * @Description 判断是否要发邮件，并获取发送的人和抄送的人
     * @Date  2020/8/4  11:44
     * @Param
     * @return
     */
	private JSONObject isSendEmail(Double standardDouble,Double ltDouble,String id,String assignee){
		JSONObject result = new JSONObject();
		Integer time = qimsSendMailMapper.getTimeById(id);
		double v = ltDouble - standardDouble;
		Integer timeTemp = Integer.valueOf((int) Math.floor(v / 24));
		timeTemp = timeTemp +1;
		if (isNull(time)){
			//发送给处理人的上级，抄送给处理人
			String superior = userMapper.getSuperior(assignee,"QIMS");
			String handlerMail = emailService.getHandlerMail(assignee);
			String ccMail = emailService.getHandlerMail(superior);
			String[] cc = new String[]{ccMail};
			result.put("addressee",handlerMail);
			result.put("cc",cc);
			result.put("time",1);
		}else {
			if (timeTemp > time){
				//发送给处理人的部门经理，抄送给处理人和处理人上级
				String addressee = emailService.getHandlerMail(assignee);
				String superior = userMapper.getSuperior(assignee,"QIMS");
				String superiorManager = userMapper.getSuperiorManagerByUser(assignee,"QIMS");
				String ccMail = emailService.getHandlerMail(superiorManager);
				String ccMail2 = emailService.getHandlerMail(superior);
				String[] cc = new String[]{ccMail,ccMail2};
				result.put("addressee",addressee);
				result.put("cc",cc);
				result.put("time",time+1);
			}
		}
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 下发邮件
	 * @Date  2020/8/4  11:57
	 * @Param
	 * @return
	 */
	@Override
	public  void  sendEmailTohandler(TaskRecord taskRecord,String cqeHandlerMail,String[] cc){
		String templateKey = taskRecord.getTemplateKey();
		String itemNumber = taskRecord.getItemNumber();
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
		String content = getHandlerMailContent(taskRecord,templateName,url);
		String subject = templateName+itemNumber+"超时未处理";
		// 发送邮件
		emailService.sendHtmlMail(cqeHandlerMail, cc, subject, content);
	}

	/**
	 * @Author yuyangyang
	 * @Description  获取下发邮件内容信息
	 * @Date  2020/8/4  11:57
	 * @Param
	 * @return
	 */
	private String getHandlerMailContent(TaskRecord taskRecord,String templateKey,String urlTemp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processInstanceId = taskRecord.getProcessInstanceId();
		String workflowBusinessId = taskRecord.getWorkflowBusinessId();
		String taskId = taskRecord.getTaskId();
		String assignee = taskRecord.getAssignee();
		String taskName = taskRecord.getTaskName();
		String theme = taskRecord.getTheme();
		String node = taskName;
	    String itemNumber = taskRecord.getItemNumber();
		String creator = taskRecord.getCreator();
		Context context = new Context();
		context.setVariable("account",assignee);
		context.setVariable("questionNumber", itemNumber);
		context.setVariable("theme", theme);
		context.setVariable("submitter", creator);
		context.setVariable("handler", assignee);
		context.setVariable("node",node);
		context.setVariable("type",templateKey);
		context.setVariable("handlerDate",sdf.format(taskRecord.getCreateDate()));
		// 拼接url
		String url = domainName + "/#/"+urlTemp+"/step" + "/" + taskId + "/" + processInstanceId + "/"
				+ workflowBusinessId;
		if("IPQC稽核".equals(templateKey)){
			url = domainName + "/#/"+urlTemp+"/" + workflowBusinessId + "/"+taskName+"?taskId=" + taskId + "&processInstanceId="
					+ processInstanceId;
		}
		context.setVariable("url", url);
		String template = templateEngine.process("QimsEmailTemplate", context);
		return template;
	}
}