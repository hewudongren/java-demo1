package cn.jwis.qualityworkflow.exception;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-04 14:24
 * @since 0.1.0
 **/
public class EmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailException(String msg) {
		super(msg);
	}
}
