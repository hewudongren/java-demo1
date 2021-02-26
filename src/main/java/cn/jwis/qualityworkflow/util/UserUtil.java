package cn.jwis.qualityworkflow.util;

import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-04-30 16:16
 * @since 0.1.0
 **/
public class UserUtil {

	public static String getCurrentUserName() {
		// 获取当前用户
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String currentUserName = "hualanlan";
//		String currentUserName = "dz_gyb1";
		if (currentUser != null) {
			currentUserName = currentUser.getAccount();
		}
		return currentUserName;
	}

	public static String aa() {
		// 获取当前用户
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String currentUserName = "codisan";
		if (currentUser != null) {
			currentUserName = currentUser.getAccount();
		}
		return currentUserName;
	}

}
