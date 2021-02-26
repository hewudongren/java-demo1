package cn.jwis.qualityworkflow.modules.ecn.bean;

public class ECNTaskRecordBean {

	private String id;
	private String processInstanceId;
	private String ecnId;
	private String assignee;
	private String taskState;
	private String taskName;
	private String taskId;

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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getEcnId() {
		return ecnId;
	}

	public void setEcnId(String ecnId) {
		this.ecnId = ecnId;
	}

}
