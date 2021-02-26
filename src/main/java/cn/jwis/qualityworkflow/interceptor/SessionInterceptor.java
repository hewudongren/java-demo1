package cn.jwis.qualityworkflow.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.bean.UserInfo;

@Configuration
@Service
public class SessionInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取用户信息
		UserInfo currentUser = new UserInfo();
		currentUser.setAccount("hualanlan");
		String result = request.getHeader("user");
		if (!StringUtils.isEmpty(result)) {
			String user = URLDecoder.decode(result, "utf-8");
			currentUser = JSON.parseObject(user, UserInfo.class);
		}
		SessionHelper.addCurrentUser(currentUser);
		// 获取token信息
		String token = request.getHeader("token");
		SessionHelper.setToken(token);
		// 获取token信息
		String accesstoken = request.getHeader("accesstoken");
		SessionHelper.setAccessToken(accesstoken);
		
		String appName = request.getHeader("appName");
		SessionHelper.setAppName(appName);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			ResultBean re = new ResultBean();
			re.setCode(-1);
			re.setMessage(ex.getMessage());
			response.getOutputStream().flush();
			response.getOutputStream().print(JSONObject.toJSONString(re));
			response.getOutputStream().close();
		}
	}

}
