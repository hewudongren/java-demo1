package cn.jwis.qualityworkflow.modules.esd.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description ESD周期性检验查询条件
 * @Author yuyangyang
 * @Date 2020/5/13 13:55
 */
public class EsdCycleSearch {
	 @ApiModelProperty(value = "样品名集合",notes = "样品名集合")
	 private List<String> sampleNames;
	 @ApiModelProperty(value = "状态集合",notes = "状态集合")
	 private List<String> status;
	 @ApiModelProperty(value = "结束时间",notes = "结束时间")
	 private String endTime;
	 @ApiModelProperty(value = "开始时间",notes = "开始时间")
	 private String startTime;
	 @ApiModelProperty(value = "用户名",notes = "用户名")
	 private String assignee;
	 @ApiModelProperty(value = "页码",notes = "页码")
	 private Integer page;
	 @ApiModelProperty(value = "每页的数据条数",notes = "每页的数据条数")
	 private Integer size;
	 //flag 为1是代办 为2是关闭 没有值是全部
	 @ApiModelProperty(value = "代办，已处理标识",notes = "代办，已处理标识")
	 private String flag;

	public List<String> getSampleNames() {
		return sampleNames;
	}

	public void setSampleNames(List<String> sampleNames) {
		this.sampleNames = sampleNames;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}