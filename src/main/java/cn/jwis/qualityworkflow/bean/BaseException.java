package cn.jwis.qualityworkflow.bean;

import org.springframework.util.StringUtils;

import java.sql.SQLException;

/**
 * 注：spring 对于 RuntimeException 异常才会进行事务回滚。
 * @author samuel
 *
 */
public class BaseException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	
	private String msg;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BaseException() {
		super();
	}
	
	public BaseException(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public BaseException(int code, String msg, Throwable e) {
		super(e);
		this.code = code;
		this.msg = msg;
	}
	
	public BaseException(int code) {
		super();
		this.code = code;
	}
	
	public BaseException(String msg) {
		super();
		this.code = -1;
		this.msg = msg;
	}

	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		if(!StringUtils.isEmpty(msg)){
			return msg;
		}
		Throwable cause2 = super.getCause();
		if(cause2 instanceof SQLException){
			this.code = 1021;
			return "database error!";
		}
		return super.getMessage();
	}
	
	
	
}
