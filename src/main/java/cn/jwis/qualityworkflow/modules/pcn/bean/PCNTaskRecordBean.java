package cn.jwis.qualityworkflow.modules.pcn.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PCNTaskRecordBean {
    private String id;
    private String processInstanceId;
    private String assignee;
    private String pcnId;
    private String taskState;
    private String state;
    private String taskName;
    private String taskId;
    private String templateKey;
    private String department;

    private  String theme;

    private  String itemNumber;

    private  String creator;
    @JsonFormat (pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat (pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;


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

    public String getPcnId() {
        return pcnId;
    }

    public void setPcnId(String pcnId) {
        this.pcnId = pcnId;
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

    public String getTemplateKey() {
        return templateKey;
    }

    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
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
}
