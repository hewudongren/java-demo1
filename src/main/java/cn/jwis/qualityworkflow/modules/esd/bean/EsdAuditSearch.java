package cn.jwis.qualityworkflow.modules.esd.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/2 14:19
 */
public class EsdAuditSearch {

	@ApiModelProperty(value = "auditTypes", name = "稽核类型集合")
	private List<String> auditTypes;

	@ApiModelProperty(value = "documentNumbers", name = "文件编号集合")
	private List<String> documentNumbers;

	@ApiModelProperty(value = "frequencys", name = "班次集合")
	private List<String> frequencys;

	@ApiModelProperty(value = "lines", name = "线体集合")
	private List<String> lines;

	@ApiModelProperty(value = "auditers", name = "稽核员集合")
	private List<String> auditers;

	@ApiModelProperty(value = "startTime", name = "开始时间")
	private String startTime;


	@ApiModelProperty(value = "endTime", name = "结束时间")
	private String endTime;

	@ApiModelProperty(value = "page", name = "页码")
	private Integer page;

	@ApiModelProperty(value = "size", name = "每页展示的数据数")
	private Integer size;

	@ApiModelProperty(value = "assignee",name = "用户信息")
	private String assignee;

	public List<String> getAuditTypes() {
		return auditTypes;
	}

	public void setAuditTypes(List<String> auditTypes) {
		this.auditTypes = auditTypes;
	}

	public List<String> getDocumentNumbers() {
		return documentNumbers;
	}

	public void setDocumentNumbers(List<String> documentNumbers) {
		this.documentNumbers = documentNumbers;
	}

	public List<String> getFrequencys() {
		return frequencys;
	}

	public void setFrequencys(List<String> frequencys) {
		this.frequencys = frequencys;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getAuditers() {
		return auditers;
	}

	public void setAuditers(List<String> auditers) {
		this.auditers = auditers;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}