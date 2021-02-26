package cn.jwis.qualityworkflow.enums;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-19 17:14
 * @since 0.1.0
 **/
public enum DateType {
	/**
	 * 日
	 */
	@ApiModelProperty("日")
	DAY,

	/**
	 * 周
	 */
	@ApiModelProperty("周")
	WEEK,

	/**
	 * 月
	 */
	@ApiModelProperty("月")
	MONTH,

	/**
	 * 年
	 */
	@ApiModelProperty("年")
	YEAR,
}
