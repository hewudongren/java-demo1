package cn.jwis.qualityworkflow.modules.linequalify.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;


public class LineQualifySearch {

	@ApiModelProperty(value = "机型集合", notes = "机型集合")
	private List<String> models;
	@ApiModelProperty(value = "线体集合", notes = "线体集合")
	private List<String> lines;
	private List<String> process;
	

	private String assignee;
	@ApiModelProperty(value = "结束时间", notes = "结束时间")
	private String endTime;
	@ApiModelProperty(value = "开始时间", notes = "开始时间")
	private String startTime;
	@ApiModelProperty(value = "页码", notes = "页码")
	private Integer page;
	@ApiModelProperty(value = "每页的数据条数", notes = "每页的数据条数")
	private Integer size;
	// flag 为1是代办 为2是关闭 没有值是全部
	@ApiModelProperty(value = "代办，已处理标识", notes = "代办，已处理标识")
	private String flag;

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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

	public List<String> getProcess() {
		return process;
	}

	public void setProcess(List<String> process) {
		this.process = process;
	}
	
}