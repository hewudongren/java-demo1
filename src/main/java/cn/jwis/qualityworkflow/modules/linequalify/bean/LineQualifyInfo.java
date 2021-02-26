package cn.jwis.qualityworkflow.modules.linequalify.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LineQualifyInfo {
	private String id;
	private String processInstanceId;
	private String taskId;
	private String taskName;
	private String taskState;
	private String sn;
	private String subjectMatter;
	private String productCategory;
	private String manufactureType;
	private String item;
	private String model;
	private String processSection;
	private String line;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date deliveryTime;
	private String creator;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
	private String status;
	private String qeHandler;
	private String assignee;
	private String esdHandler;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSubjectMatter() {
		return subjectMatter;
	}

	public void setSubjectMatter(String subjectMatter) {
		this.subjectMatter = subjectMatter;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getManufactureType() {
		return manufactureType;
	}

	public void setManufactureType(String manufactureType) {
		this.manufactureType = manufactureType;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProcessSection() {
		return processSection;
	}

	public void setProcessSection(String processSection) {
		this.processSection = processSection;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getQeHandler() {
		return qeHandler;
	}

	public void setQeHandler(String qeHandler) {
		this.qeHandler = qeHandler;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getEsdHandler() {
		return esdHandler;
	}

	public void setEsdHandler(String esdHandler) {
		this.esdHandler = esdHandler;
	}
}