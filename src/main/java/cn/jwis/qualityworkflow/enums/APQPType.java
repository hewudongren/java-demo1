package cn.jwis.qualityworkflow.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-06-04 11:41
 * @since 0.1.0
 **/
public enum  APQPType {
	/**
	 * DVT1
	 */
	@ApiModelProperty("DVT1")
	DVT1("DVT1"),

	/**
	 * PEMEA
	 */
	@ApiModelProperty("PEMEA")
	PEMEA("PEMEA"),

	/**
	 * QC_CHART
	 */
	@ApiModelProperty("QC_CHART")
	QC_CHART("QC_CHART");

	private String value;

	APQPType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}
}
