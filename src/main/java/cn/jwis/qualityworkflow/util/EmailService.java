package cn.jwis.qualityworkflow.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.jwis.qualityworkflow.exception.EmailException;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;

//发送邮件的工具类
@Service
public class EmailService {

	public  static  final Logger log = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean
	@Value("${spring.mail.username}")
	private String Sender; // 读取配置文件中的参数

	@Autowired
	RestTemplate restTemplate;


	@Value("${platform.gateway.address}")
	private String gatewayAddress;

	/**
	 *
	 * @param receiveMail  收件人
	 * @param Cc  抄送组
	 * @param subject  标题
	 * @param content  内容
	 */
	public void sendHtmlMail(String receiveMail, String[] cc, String subject, String content) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			//设置发件人
			helper.setFrom(Sender);
			//设置收件人
			helper.setTo(receiveMail);
			if (StringUtils.isNotEmpty(subject)){
				helper.setSubject(subject);
			}
			helper.setText(content, true);

			String[] ccMail = replaceNull(cc);
			if (ccMail != null && ccMail.length > 0) {
				helper.setCc(ccMail);
				log.info(Sender + " send a Mail to " + receiveMail + " and make a copy for " + Arrays.asList(ccMail));
			} else {
				log.info(Sender + " send a Mail to " + receiveMail);
			}
		} catch (Exception e) {
			log.info(Sender + " send a Mail to " + receiveMail + " fail, because " + e.getMessage());
		}
		mailSender.send(message);
	}

	/**
	 *
	 * @param receiveMail  收件人
	 * @param Cc  抄送组
	 * @param subject  标题
	 * @param content  内容
	 */
	public void sendHtmlMail(String[] receiveMail, String[] cc, String subject, String content) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(receiveMail);
			if (StringUtils.isNotEmpty(subject)){
				helper.setSubject(subject);
			}
			helper.setText(content, true);
			if (cc != null && cc.length > 0) {
				helper.setCc(cc);
				log.info(Sender + " send a Mail to " + Arrays.asList(receiveMail) + " and make a copy for " + Arrays.asList(cc));
			}
			log.info(Sender + " send a Mail to " + Arrays.asList(receiveMail));
		} catch (Exception e) {
			log.info(Sender + " send a Mail to " + Arrays.asList(receiveMail) + " fail, because " + e.getMessage());
		}
		mailSender.send(message);
	}
	/**
	 * 获取CQE处理人邮箱
	 *
	 * @param handler 处理人名
	 * @return
	 */
	public String getHandlerMail(String handler) throws EmailException {
		if (handler == null) {
			return null;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		String url = gatewayAddress + "/platform/sys/user/internal-read?name=" + handler;
		String email = null;
		try {
			ResponseEntity<Map> map = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
			Map<String, Object> result = (Map<String, Object>) map.getBody().get("result");
			if (result != null) {
				Object rows = result.get("rows");
				if (rows != null && rows instanceof List) {
					List<Object> rowsList = (List) rows;
					if (rowsList != null && rowsList.size() > 0) {
						for (Object object : rowsList) {
							Map rowMap =(Map<String, Object>)object;
							String account = rowMap.get("account").toString();
						    if(account.equals(handler)) {
						    	email = (String) rowMap.get("email");
						    	break;
						    }
						}
					}
				}
			}
			log.info(handler + " email is " + email);
		} catch (Exception e) {
			log.debug("get handler mail from " + url + " happen error cause:" + e.getMessage());
		}
		return email;
	}

	//删除String数组中的空值
	private String[] replaceNull(String[] str){
		//用StringBuffer来存放数组中的非空元素，用“;”分隔
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<str.length; i++) {
			if(str[i] == null || "".equals(str[i])) {
				continue;
			}
			sb.append(str[i]);
			if(i != str.length - 1) {
				sb.append(";");
			}
		}
		String s = sb.toString();
		if (s == null || "".equals(s)) {
			return null;
		}
		//用String的split方法分割，得到数组
		str = s.split(";");
		return str;
	}
}
