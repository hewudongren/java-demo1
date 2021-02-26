package cn.jwis.qualityworkflow.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-19 17:28
 * @since 0.1.0
 **/
public class TimeBean {
	@ApiModelProperty("开始时间")
	String startTime;

	@ApiModelProperty("结束时间")
	String endTime;

	@ApiModelProperty("时间字段")
	String time = "update_date";

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
