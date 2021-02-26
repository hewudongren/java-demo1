package cn.jwis.qualityworkflow.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 继承此类判断非空
 * 
 * @author lizong
 *
 */
public abstract class BaseClass {

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(String s) {
		if (s != null && (!"".equals(s.trim()))) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(JSONObject s) {
		if (s != null && (s.size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 所有字段都为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isAllNull(JSONObject s) {
		if (s != null && (s.size() > 0)) {
			for (String key : s.keySet()) {
				if (isNotNull(s.getString(key))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(JSONArray s) {
		if (s != null && (s.size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(@SuppressWarnings("rawtypes") List s) {
		if (s != null && (s.size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(@SuppressWarnings("rawtypes") Map s) {
		if (s != null && (s.size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(Class<Object>[] s) {
		if (s != null && (s.length > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(String[] s) {
		if (s != null && (s.length > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(Object s) {
		if (s != null) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(Long s) {
		if (s != null && s > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(Set<?> s) {
		if (s != null && s.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 非空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNotNull(Object[] s) {
		if (s != null && (s.length > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNull(String s) {
		if (s == null || ("".equals(s))) {
			return true;
		}
		return false;
	}

	/**
	 * 为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNull(Object s) {
		if (s == null) {
			return true;
		}
		return false;
	}

	/**
	 * 为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNull(JSONObject s) {
		if (s == null || (s.size() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNull(JSONArray s) {
		if (s == null || (s.size() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNull(List<?> s) {
		if (s == null || (s.size() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isNull(Map<String, ?> s) {
		if (s == null || (s.size() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 去掉整数最后一位0和.
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s !=null && s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	public List<String> arrayStringToList(String[] s) {
		List<String> list = new ArrayList<>();
		if (isNotNull(s)) {
			for (int i = 0; i < s.length; i++) {
				list.add(s[i]);
			}
		}
		return list;
	}

}
