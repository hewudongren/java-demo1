package cn.jwis.qualityworkflow.modules.pcn.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PCNListBean {
    private String id;
    private String itemNumber;
    private String itemType;
    private String changeName;
    private String model;
    private String item;
    private String changeType;
    private String presentationDepartment;
    private String worksSection;
    private String changeTrigger;
    private String status;
    private String applicant;
    private String creator;
    private String taskId;
    private String taskState;
    private String processInstanceId;
    private String leadTime;
    private String others;
    private String What;
    private String Where;
    private String Why;
    private String Who;
    private String When;
    private String How;
    private String testPlan;
    private String deliveryList;
    private String departmentAuditor;
    private String departmentAuditResults;
    private String departmentAuditOpinion;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date departmentAuditTime;
    private String qeAuditor;
    private String qeAuditResults;
    private String qeAuditOpinion;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qeAuditTime;
    private String odmAuditor;
    private String odmAuditResults;
    private String odmAuditOpinion;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date odmAuditTime;
    private String importMode;
    private String switchDate;
    private String approveResult;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approveTime;




    private String date;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getPresentationDepartment() {
        return presentationDepartment;
    }

    public void setPresentationDepartment(String presentationDepartment) {
        this.presentationDepartment = presentationDepartment;
    }

    public String getWorksSection() {
        return worksSection;
    }

    public void setWorksSection(String worksSection) {
        this.worksSection = worksSection;
    }

    public String getChangeTrigger() {
        return changeTrigger;
    }

    public void setChangeTrigger(String changeTrigger) {
        this.changeTrigger = changeTrigger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getWhat() {
        return What;
    }

    public void setWhat(String what) {
        What = what;
    }

    public String getWhere() {
        return Where;
    }

    public void setWhere(String where) {
        Where = where;
    }

    public String getWhy() {
        return Why;
    }

    public void setWhy(String why) {
        Why = why;
    }

    public String getWho() {
        return Who;
    }

    public void setWho(String who) {
        Who = who;
    }

    public String getWhen() {
        return When;
    }

    public void setWhen(String when) {
        When = when;
    }

    public String getHow() {
        return How;
    }

    public void setHow(String how) {
        How = how;
    }

    public String getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(String testPlan) {
        this.testPlan = testPlan;
    }

    public String getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(String deliveryList) {
        this.deliveryList = deliveryList;
    }

    public String getDepartmentAuditor() {
        return departmentAuditor;
    }

    public void setDepartmentAuditor(String departmentAuditor) {
        this.departmentAuditor = departmentAuditor;
    }

    public String getDepartmentAuditResults() {
        return departmentAuditResults;
    }

    public void setDepartmentAuditResults(String departmentAuditResults) {
        this.departmentAuditResults = departmentAuditResults;
    }

    public String getDepartmentAuditOpinion() {
        return departmentAuditOpinion;
    }

    public void setDepartmentAuditOpinion(String departmentAuditOpinion) {
        this.departmentAuditOpinion = departmentAuditOpinion;
    }

    public Date getDepartmentAuditTime() {
        return departmentAuditTime;
    }

    public void setDepartmentAuditTime(Date departmentAuditTime) {
        this.departmentAuditTime = departmentAuditTime;
    }

    public String getQeAuditor() {
        return qeAuditor;
    }

    public void setQeAuditor(String qeAuditor) {
        this.qeAuditor = qeAuditor;
    }

    public String getQeAuditResults() {
        return qeAuditResults;
    }

    public void setQeAuditResults(String qeAuditResults) {
        this.qeAuditResults = qeAuditResults;
    }

    public String getQeAuditOpinion() {
        return qeAuditOpinion;
    }

    public void setQeAuditOpinion(String qeAuditOpinion) {
        this.qeAuditOpinion = qeAuditOpinion;
    }

    public Date getQeAuditTime() {
        return qeAuditTime;
    }

    public void setQeAuditTime(Date qeAuditTime) {
        this.qeAuditTime = qeAuditTime;
    }

    public String getOdmAuditor() {
        return odmAuditor;
    }

    public void setOdmAuditor(String odmAuditor) {
        this.odmAuditor = odmAuditor;
    }

    public String getOdmAuditResults() {
        return odmAuditResults;
    }

    public void setOdmAuditResults(String odmAuditResults) {
        this.odmAuditResults = odmAuditResults;
    }

    public String getOdmAuditOpinion() {
        return odmAuditOpinion;
    }

    public void setOdmAuditOpinion(String odmAuditOpinion) {
        this.odmAuditOpinion = odmAuditOpinion;
    }

    public Date getOdmAuditTime() {
        return odmAuditTime;
    }

    public void setOdmAuditTime(Date odmAuditTime) {
        this.odmAuditTime = odmAuditTime;
    }

    public String getImportMode() {
        return importMode;
    }

    public void setImportMode(String importMode) {
        this.importMode = importMode;
    }

    public String getSwitchDate() {
        return switchDate;
    }

    public void setSwitchDate(String switchDate) {
        this.switchDate = switchDate;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }
}
