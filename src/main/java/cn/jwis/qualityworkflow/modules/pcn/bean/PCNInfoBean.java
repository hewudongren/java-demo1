package cn.jwis.qualityworkflow.modules.pcn.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PCNInfoBean {

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
    private String what;
    private String where;
    private String why;
    private String who;
    private String when;
    private String how;
    private String appendices;
    private String targetContent;
    private JSONArray content;
    private String deliveryList;
    private String departmentAuditor;
    private String qeAuditor;
    private String testPlan;
    private String assignee;
    private String state;
    private String leadTime;
    private String date;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approveDate;
    private String taskId;
    private String taskState;
    private String processInstanceId;
    private JSONObject variables;
    private Map<String, Object> localVariables;


    private Integer page;
    private Integer size;
    private int flag;
    private String dataSupport;
    private String others;
    private String switchDate;
    private String importMode;
    private JSONArray verifyResult;
    private JSONArray resultList;
    private JSONArray deliverableAffirm;
    private JSONArray changeIssueApply;
    private JSONArray changeList;
    private JSONArray changeReview;
    private JSONArray changeApply;
    private List<String> modelList;
    private  List<String> appendicesList;


    private String departmentAuditResults;
    private String departmentAuditOpinion;
    private String qeAuditResults;
    private String qeAuditOpinion;
    private String odmAuditor;
    private String odmAuditResults;
    private String odmAuditOpinion;
    private String approveResult;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String approveTime;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String departmentAuditTime;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String qeAuditTime;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String odmAuditTime;

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

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getAppendices() {
        return appendices;
    }

    public void setAppendices(String appendices) {
        this.appendices = appendices;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }

    public JSONArray getContent() {
        return content;
    }

    public void setContent(JSONArray content) {
        this.content = content;
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

    public String getQeAuditor() {
        return qeAuditor;
    }

    public void setQeAuditor(String qeAuditor) {
        this.qeAuditor = qeAuditor;
    }

    public String getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(String testPlan) {
        this.testPlan = testPlan;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
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

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    public Map<String, Object> getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(Map<String, Object> localVariables) {
        this.localVariables = localVariables;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDataSupport() {
        return dataSupport;
    }

    public void setDataSupport(String dataSupport) {
        this.dataSupport = dataSupport;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getSwitchDate() {
        return switchDate;
    }

    public void setSwitchDate(String switchDate) {
        this.switchDate = switchDate;
    }

    public String getImportMode() {
        return importMode;
    }

    public void setImportMode(String importMode) {
        this.importMode = importMode;
    }

    public JSONArray getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(JSONArray verifyResult) {
        this.verifyResult = verifyResult;
    }

    public JSONArray getResultList() {
        return resultList;
    }

    public void setResultList(JSONArray resultList) {
        this.resultList = resultList;
    }

    public JSONArray getDeliverableAffirm() {
        return deliverableAffirm;
    }

    public void setDeliverableAffirm(JSONArray deliverableAffirm) {
        this.deliverableAffirm = deliverableAffirm;
    }

    public JSONArray getChangeIssueApply() {
        return changeIssueApply;
    }

    public void setChangeIssueApply(JSONArray changeIssueApply) {
        this.changeIssueApply = changeIssueApply;
    }

    public JSONArray getChangeList() {
        return changeList;
    }

    public void setChangeList(JSONArray changeList) {
        this.changeList = changeList;
    }

    public JSONArray getChangeReview() {
        return changeReview;
    }

    public void setChangeReview(JSONArray changeReview) {
        this.changeReview = changeReview;
    }

    public JSONArray getChangeApply() {
        return changeApply;
    }

    public void setChangeApply(JSONArray changeApply) {
        this.changeApply = changeApply;
    }

    public List<String> getModelList() {
        return modelList;
    }

    public void setModelList(List<String> modelList) {
        this.modelList = modelList;
    }

    public List<String> getAppendicesList() {
        return appendicesList;
    }

    public void setAppendicesList(List<String> appendicesList) {
        this.appendicesList = appendicesList;
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

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getDepartmentAuditTime() {
        return departmentAuditTime;
    }

    public void setDepartmentAuditTime(String departmentAuditTime) {
        this.departmentAuditTime = departmentAuditTime;
    }

    public String getQeAuditTime() {
        return qeAuditTime;
    }

    public void setQeAuditTime(String qeAuditTime) {
        this.qeAuditTime = qeAuditTime;
    }

    public String getOdmAuditTime() {
        return odmAuditTime;
    }

    public void setOdmAuditTime(String odmAuditTime) {
        this.odmAuditTime = odmAuditTime;
    }
}
