package cn.jwis.qualityworkflow.bean;

import java.util.Date;

public class TaskRecord  {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 流程模板Key
     *
     * @mbggenerated
     */
    private String templateKey;

    /**
     * 流程ID
     *
     * @mbggenerated
     */
    private String processInstanceId;

    /**
     * 任务处理人
     *
     * @mbggenerated
     */
    private String assignee;

    /**
     * 流程业务ID
     *
     * @mbggenerated
     */
    private String workflowBusinessId;

    /**
     * 任务状态
     *
     * @mbggenerated
     */
    private String taskState;

    /**
     * 流程状态
     *
     * @mbggenerated
     */
    private String state;

    /**
     * 流程任务名
     *
     * @mbggenerated
     */
    private String taskName;

    /**
     * 流程任务ID
     *
     * @mbggenerated
     */
    private String taskId;

    private Date createDate;

    private Date updateDate;

    private String department;

    private  String theme;

    private  String itemNumber;

    private  String creator;
    
    private int overTimeNum;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
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

    public String getWorkflowBusinessId() {
        return workflowBusinessId;
    }

    public void setWorkflowBusinessId(String workflowBusinessId) {
        this.workflowBusinessId = workflowBusinessId;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public int getOverTimeNum() {
		return overTimeNum;
	}

	public void setOverTimeNum(int overTimeNum) {
		this.overTimeNum = overTimeNum;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateKey=").append(templateKey);
        sb.append(", processInstanceId=").append(processInstanceId);
        sb.append(", assignee=").append(assignee);
        sb.append(", workflowBusinessId=").append(workflowBusinessId);
        sb.append(", taskState=").append(taskState);
        sb.append(", state=").append(state);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskId=").append(taskId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}