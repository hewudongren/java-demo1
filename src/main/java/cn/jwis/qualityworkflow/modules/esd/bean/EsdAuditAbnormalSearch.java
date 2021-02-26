package cn.jwis.qualityworkflow.modules.esd.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/3 17:14
 */
public class EsdAuditAbnormalSearch {

	@ApiModelProperty(value = "auditTypes", name = "稽核类型集合")
	private List<String> auditTypes;

	@ApiModelProperty(value = "lines", name = "线体集合")
	private List<String> lines;

	@ApiModelProperty(value = "problemClassifications", name = "问题类型集合")
	private List<String> problemClassifications;

	@ApiModelProperty(value = "responsibleDepartments", name = "责任部门集合")
	private List<String> responsibleDepartments;

	@ApiModelProperty(value = "status", name = "状态集合")
	private List<String> status;

	@ApiModelProperty(value = "page", name = "页码")
	private Integer page;

	@ApiModelProperty(value = "size", name = "每页的数据条目")
	private Integer size;

	@ApiModelProperty(value = "startTime", name = "开始时间")
	private String startTime;

	@ApiModelProperty(value = "endTime", name = "结束时间")
	private String endTime;

	@ApiModelProperty(value = "assignee", name = "处理人")
	private String assignee;

	public List<String> getAuditTypes() {
		return auditTypes;
	}

	public void setAuditTypes(List<String> auditTypes) {
		this.auditTypes = auditTypes;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getProblemClassifications() {
		return problemClassifications;
	}

	public void setProblemClassifications(List<String> problemClassifications) {
		this.problemClassifications = problemClassifications;
	}

	public List<String> getResponsibleDepartments() {
		return responsibleDepartments;
	}

	public void setResponsibleDepartments(List<String> responsibleDepartments) {
		this.responsibleDepartments = responsibleDepartments;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}