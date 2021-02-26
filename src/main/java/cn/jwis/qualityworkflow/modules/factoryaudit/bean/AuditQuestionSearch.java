package cn.jwis.qualityworkflow.modules.factoryaudit.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AuditQuestionSearch {

	private List<String> auditTopics;
	private List<String> problemType;
	private List<String> occurrenceSite;
	private List<String> problemLevel;
	private List<String> problemKeywords;
	private List<String> auditType;
	private List<String> eventNumber;

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

	public List<String> getAuditTopics() {
		return auditTopics;
	}

	public void setAuditTopics(List<String> auditTopics) {
		this.auditTopics = auditTopics;
	}

	public List<String> getProblemType() {
		return problemType;
	}

	public void setProblemType(List<String> problemType) {
		this.problemType = problemType;
	}

	public List<String> getOccurrenceSite() {
		return occurrenceSite;
	}

	public void setOccurrenceSite(List<String> occurrenceSite) {
		this.occurrenceSite = occurrenceSite;
	}

	public List<String> getProblemLevel() {
		return problemLevel;
	}

	public void setProblemLevel(List<String> problemLevel) {
		this.problemLevel = problemLevel;
	}

	public List<String> getProblemKeywords() {
		return problemKeywords;
	}

	public void setProblemKeywords(List<String> problemKeywords) {
		this.problemKeywords = problemKeywords;
	}

	public List<String> getAuditType() {
		return auditType;
	}

	public void setAuditType(List<String> auditType) {
		this.auditType = auditType;
	}

	public List<String> getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(List<String> eventNumber) {
		this.eventNumber = eventNumber;
	}

}