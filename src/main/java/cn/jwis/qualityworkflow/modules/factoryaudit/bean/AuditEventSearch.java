package cn.jwis.qualityworkflow.modules.factoryaudit.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class AuditEventSearch {

	private List<String> auditType;
	private List<String> auditSource;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date auditStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date auditEndDate;

	private String creator;
	@ApiModelProperty(value = "结束时间", notes = "结束时间")
	private String endTime;
	@ApiModelProperty(value = "开始时间", notes = "开始时间")
	private String startTime;
	@ApiModelProperty(value = "页码", notes = "页码")
	private Integer page;
	@ApiModelProperty(value = "每页的数据条数", notes = "每页的数据条数")
	private Integer size;
	public List<String> getAuditType() {
		return auditType;
	}
	public void setAuditType(List<String> auditType) {
		this.auditType = auditType;
	}
	public List<String> getAuditSource() {
		return auditSource;
	}
	public void setAuditSource(List<String> auditSource) {
		this.auditSource = auditSource;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
	public Date getAuditStartDate() {
		return auditStartDate;
	}
	public void setAuditStartDate(Date auditStartDate) {
		this.auditStartDate = auditStartDate;
	}
	public Date getAuditEndDate() {
		return auditEndDate;
	}
	public void setAuditEndDate(Date auditEndDate) {
		this.auditEndDate = auditEndDate;
	}
	
}