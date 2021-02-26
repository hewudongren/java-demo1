package cn.jwis.qualityworkflow.bean;

import java.util.Map;

public class ResultBean {

	private int code = 0; // 响应码 0:成功,非0:失败,2:参数重复

	private String message = "操作成功";// 响应信息描述

	private Object data = null; // 响应数据

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultBean success(Object data) {
		ResultBean resultBean = new ResultBean();
		resultBean.setCode(0);
		resultBean.setData(data);
		return resultBean;
	}

	public static ResultBean success() {
		ResultBean resultBean = new ResultBean();
		resultBean.setCode(0);
		return resultBean;
	}

	public static ResultBean fail() {
		ResultBean resultBean = new ResultBean();
		resultBean.setCode(-1);
		resultBean.setMessage("操作失败");
		return resultBean;
	}


	public static ResultBean fail(String message) {
		ResultBean resultBean = new ResultBean();
		resultBean.setCode(-1);
		resultBean.setMessage(message);
		return resultBean;
	}

	public static ResultBean pagination(Long count, int currentPage, int pageSize, Object data) {
		PageResult page = new PageResult(count, currentPage, pageSize, data);
		ResultBean ret = new ResultBean(0, page);
		return ret;
	}

	public static ResultBean pagination(Long count, int currentPage, int pageSize, Object data, Map<String, String> titles) {
		PageResult page = new PageResult(count, currentPage, pageSize, data, titles);
		ResultBean ret = new ResultBean(0, page);
		return ret;
	}

	public ResultBean(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultBean(int code, Object data) {
		this.code = code;
		this.data = data;
	}

	public ResultBean(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}



	public ResultBean() {
	}
}

