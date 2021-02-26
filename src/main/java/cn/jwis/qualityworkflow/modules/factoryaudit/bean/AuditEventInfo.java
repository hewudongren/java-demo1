package cn.jwis.qualityworkflow.modules.factoryaudit.bean;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AuditEventInfo {

	private String id;
	private String auditTopics;
	private String eventNumber;
	private String auditType;
	private String auditSource;
	private String auditParty;
	private String model;
	private String auditRange;
	private String appendices;
	private JSONArray appendicesList;
	private String creator;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date auditStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date auditEndDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	private String auditRequirements;

	private String isSendEmail;

	private String notificationRange;

	private String status;
	
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuditTopics() {
		return auditTopics;
	}

	public void setAuditTopics(String auditTopics) {
		this.auditTopics = auditTopics;
	}

	public String getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getAuditSource() {
		return auditSource;
	}

	public void setAuditSource(String auditSource) {
		this.auditSource = auditSource;
	}

	public String getAuditParty() {
		return auditParty;
	}

	public void setAuditParty(String auditParty) {
		this.auditParty = auditParty;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAuditRange() {
		return auditRange;
	}

	public void setAuditRange(String auditRange) {
		this.auditRange = auditRange;
	}

	public String getAppendices() {
		return appendices;
	}

	public void setAppendices(String appendices) {
		this.appendices = appendices;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getAuditRequirements() {
		return auditRequirements;
	}

	public void setAuditRequirements(String auditRequirements) {
		this.auditRequirements = auditRequirements;
	}

	public String getIsSendEmail() {
		return isSendEmail;
	}

	public void setIsSendEmail(String isSendEmail) {
		this.isSendEmail = isSendEmail;
	}

	public String getNotificationRange() {
		return notificationRange;
	}

	public void setNotificationRange(String notificationRange) {
		this.notificationRange = notificationRange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JSONArray getAppendicesList() {
		return appendicesList;
	}

	public void setAppendicesList(JSONArray appendicesList) {
		this.appendicesList = appendicesList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}