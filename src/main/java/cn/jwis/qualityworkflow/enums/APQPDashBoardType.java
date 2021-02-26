package cn.jwis.qualityworkflow.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-06-04 18:16
 * @since 0.1.0
 **/
public enum APQPDashBoardType {
	/**
	 * 已关闭
	 */
	@ApiModelProperty("已关闭")
	CLOSE("CLOSE"),

	/**
	 * 新增
	 */
	@ApiModelProperty("新增")
	ADD("ADD"),

	/**
	 * 超期
	 */
	@ApiModelProperty("超期")
	OVERDUE("OVERDUE"),

	/**
	 * 处理中
	 */
	@ApiModelProperty("处理中")
	PROCESSING("PROCESSING");


	private String value;

	APQPDashBoardType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}
}
