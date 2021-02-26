package cn.jwis.qualityworkflow.modules.ecn.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 查询Ecn/外来文件看板vo
 * @create 2020-05-06 10:53
 * @since 0.1.0
 **/
public class QueryDashboardBean {

	@ApiModelProperty("项目类型")
	private List<String> itemType;

	@ApiModelProperty("发文方")
	private List<String> senderSide;

	@ApiModelProperty("工程段")
	private List<String> worksSection;

	@ApiModelProperty("开始时间")
	private String startTime;

	@ApiModelProperty("结束时间")
	private String endTime;

	@ApiModelProperty("时间")  //动态变量查询字段
	@JsonIgnore
	String time = "create_date";

	@ApiModelProperty("分类时间 默认按创建时间 月分类")
	@JsonIgnore
	String groupTime = "DATE_FORMAT(create_date,'%Y-%m')";

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<String> getItemType() {
		return itemType;
	}

	public void setItemType(List<String> itemType) {
		this.itemType = itemType;
	}

	public List<String> getSenderSide() {
		return senderSide;
	}

	public void setSenderSide(List<String> senderSide) {
		this.senderSide = senderSide;
	}

	public List<String> getWorksSection() {
		return worksSection;
	}

	public void setWorksSection(List<String> worksSection) {
		this.worksSection = worksSection;
	}

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
}
