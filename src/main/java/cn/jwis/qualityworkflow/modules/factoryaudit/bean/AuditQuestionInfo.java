package cn.jwis.qualityworkflow.modules.factoryaudit.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuditQuestionInfo {
	private String id;
	private String processInstanceId;
	private String taskId;
	private String taskName;
	private String taskState;
	private String auditTopics;
	private String eventNumber;
	private String problemType;
	private String problemLevel;
	private String occurrenceSite;
	private String problemKeywords;
	private String problemPicture;
	private List<String> ProblemPictureList;
	private String remark;
	private String assignee;
	private String problemNumber;
	private String problemDescription;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
	private String creator;
	private String confirmDepartment;
	private String confirmer;
	private String ownerDepartment;
	private String amelioratingOwner;
	private String auditType;

	private String status;

	private String causeAnalysis;
	private String emergencyResponse;
	private String improvementMeasures;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date estimatedCompletionTime;
	private String improveResults;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date implementationDate;
	private String implementer;
	private String verificationResults;
	private String lt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
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

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getProblemLevel() {
		return problemLevel;
	}

	public void setProblemLevel(String problemLevel) {
		this.problemLevel = problemLevel;
	}

	public String getOccurrenceSite() {
		return occurrenceSite;
	}

	public void setOccurrenceSite(String occurrenceSite) {
		this.occurrenceSite = occurrenceSite;
	}

	public String getProblemKeywords() {
		return problemKeywords;
	}

	public void setProblemKeywords(String problemKeywords) {
		this.problemKeywords = problemKeywords;
	}

	public String getProblemPicture() {
		return problemPicture;
	}

	public void setProblemPicture(String problemPicture) {
		this.problemPicture = problemPicture;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}

	public String getAmelioratingOwner() {
		return amelioratingOwner;
	}

	public void setAmelioratingOwner(String amelioratingOwner) {
		this.amelioratingOwner = amelioratingOwner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProblemNumber() {
		return problemNumber;
	}

	public void setProblemNumber(String problemNumber) {
		this.problemNumber = problemNumber;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<String> getProblemPictureList() {
		return ProblemPictureList;
	}

	public void setProblemPictureList(List<String> problemPictureList) {
		ProblemPictureList = problemPictureList;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getConfirmDepartment() {
		return confirmDepartment;
	}

	public void setConfirmDepartment(String confirmDepartment) {
		this.confirmDepartment = confirmDepartment;
	}

	public String getOwnerDepartment() {
		return ownerDepartment;
	}

	public void setOwnerDepartment(String ownerDepartment) {
		this.ownerDepartment = ownerDepartment;
	}

	public String getCauseAnalysis() {
		return causeAnalysis;
	}

	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}

	public String getEmergencyResponse() {
		return emergencyResponse;
	}

	public void setEmergencyResponse(String emergencyResponse) {
		this.emergencyResponse = emergencyResponse;
	}

	public String getImprovementMeasures() {
		return improvementMeasures;
	}

	public void setImprovementMeasures(String improvementMeasures) {
		this.improvementMeasures = improvementMeasures;
	}

	public Date getEstimatedCompletionTime() {
		return estimatedCompletionTime;
	}

	public void setEstimatedCompletionTime(Date estimatedCompletionTime) {
		this.estimatedCompletionTime = estimatedCompletionTime;
	}

	public String getImproveResults() {
		return improveResults;
	}

	public void setImproveResults(String improveResults) {
		this.improveResults = improveResults;
	}

	public Date getImplementationDate() {
		return implementationDate;
	}

	public void setImplementationDate(Date implementationDate) {
		this.implementationDate = implementationDate;
	}

	public String getImplementer() {
		return implementer;
	}

	public void setImplementer(String implementer) {
		this.implementer = implementer;
	}

	public String getVerificationResults() {
		return verificationResults;
	}

	public void setVerificationResults(String verificationResults) {
		this.verificationResults = verificationResults;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

}