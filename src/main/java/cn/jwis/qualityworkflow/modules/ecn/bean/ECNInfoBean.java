package cn.jwis.qualityworkflow.modules.ecn.bean;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ECNInfoBean {

	private String id;
	private String workshop;
	private String number;
	private String productName;
	private String orderNumber;
	private String batch;
	private String factory;
	private int abnormalTarget;
	private int abnormalQuantity;
	private String description;
	private String attachment;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endDate;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Date updateDate;

	private String state;
	private int interval;
	private String dateFormat = "%Y%m%d";

	private String processInstanceId;

	private String taskId;

	private String assignee;

	private String taskState;

	private int flag;

	private Map<String, Object> variables;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkshop() {
		return workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
		if (interval == 1) {
			this.dateFormat = "%x%v";
		} else if (interval == 2) {
			this.dateFormat = "%Y%m";
		} else if (interval == 3) {
			this.dateFormat = "%Y";
		}
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public int getAbnormalTarget() {
		return abnormalTarget;
	}

	public void setAbnormalTarget(int abnormalTarget) {
		this.abnormalTarget = abnormalTarget;
	}

	public int getAbnormalQuantity() {
		return abnormalQuantity;
	}

	public void setAbnormalQuantity(int abnormalQuantity) {
		this.abnormalQuantity = abnormalQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

}
