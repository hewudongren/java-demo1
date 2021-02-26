package cn.jwis.qualityworkflow.interceptor;

import cn.jwis.qualityworkflow.bean.UserInfo;

public class SessionHelper {
	private static ThreadLocal<UserInfo> session = new ThreadLocal<UserInfo>() {
		@Override
		protected UserInfo initialValue() {
			return super.initialValue();
		}
	};

	public static void addCurrentUser(UserInfo userInfo) {
		session.set(userInfo);
	}

	public static UserInfo getCurrentUser() {
		return session.get();
	}

	private static ThreadLocal<String> token = new ThreadLocal<String>();

	public static String getToken() {
		return token.get();
	}

	public static void setToken(String tokenInfo) {
		try {
			token.set(tokenInfo);
		}catch (Exception e){
			token.remove();
		}
	}

	private static ThreadLocal<String> accesstoken = new ThreadLocal<String>();

	public static String getAccessToken() {
		return accesstoken.get();
	}

	public static void setAccessToken(String accessToken) {
		try {
			accesstoken.set(accessToken);
		}catch (Exception e){
			accesstoken.remove();
		}
	}
	
	private static ThreadLocal<String> appNames = new ThreadLocal<String>();

	public static String getAppName() {
		return appNames.get();
	}

	public static void setAppName(String appName) {
		try {
			appNames.set(appName);
		}catch (Exception e){
			appNames.remove();
		}
	}
}
