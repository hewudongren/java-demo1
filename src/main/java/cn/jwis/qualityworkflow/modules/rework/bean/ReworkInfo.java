package cn.jwis.qualityworkflow.modules.rework.bean;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class ReworkInfo  {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 数据来源
     *
     * @mbggenerated
     */
    private String dataSource;

    /**
     * 返工编号
     *
     * @mbggenerated
     */
    private String reworkNumber;

    /**
     * 返工主题
     *
     * @mbggenerated
     */
    private String reworkTheme;

    /**
     * 返工原因
     *
     * @mbggenerated
     */
    private String reworkReason;

    /**
     * 返工数量
     *
     * @mbggenerated
     */
    private String reworkQuantity;

    /**
     * 返工料号
     *
     * @mbggenerated
     */
    private String reworkMaterialNumber;

    /**
     * 返工确认结果
     *
     * @mbggenerated
     */
    private String reworkConfirmResult;

    /**
     * 返工结果确认人
     *
     * @mbggenerated
     */
    private String reworkConfirmor;

    /**
     * 返工结果确认时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reworkConfirmTime;

    /**
     * 工艺段
     *
     * @mbggenerated
     */
    private String craftsSection;

    /**
     * 机型
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 问题类型
     *
     * @mbggenerated
     */
    private String problemType;

    /**
     * 问题发生时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date problemTime;

    /**
     * 根本原因责任方
     *
     * @mbggenerated
     */
    private String rootCauseResponsibility;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String statusDisplay;

    /**
     * 审核人
     *
     * @mbggenerated
     */
    private String auditor;

    /**
     * 审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date auditTime;

    /**
     * 工程处理人
     *
     * @mbggenerated
     */
    private String projectHandler;
    /**
     * 计划处理人
     *
     * @mbggenerated
     */
    private String planHandler;


    /**
     * 生产处理人
     *
     * @mbggenerated
     */
    private String productionHandler;

    /**
     * 工程处理提交时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectHandleTime;

    /**
     * 计划处理提交时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planHandleTime;

    /**
     * 生产处理提交时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date productionExecutionTime;

    /**
     * 当前处理人
     *
     * @mbggenerated
     */
    private String assignee;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    //非持久化属性
    @Transient
    @ApiModelProperty("流程ID")
    private String processInstanceId;

    @Transient
    @ApiModelProperty("任务ID")
    private String taskId;

    @Transient
    @ApiModelProperty("传递给Workflow的全局变量")
    private JSONObject variables;


    @Transient
    @ApiModelProperty("传递给Workflow的局部变量")
    private JSONObject localVariables;

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
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

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    public JSONObject getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(JSONObject localVariables) {
        this.localVariables = localVariables;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getReworkNumber() {
        return reworkNumber;
    }

    public void setReworkNumber(String reworkNumber) {
        this.reworkNumber = reworkNumber;
    }

    public String getReworkTheme() {
        return reworkTheme;
    }

    public void setReworkTheme(String reworkTheme) {
        this.reworkTheme = reworkTheme;
    }

    public String getReworkReason() {
        return reworkReason;
    }

    public void setReworkReason(String reworkReason) {
        this.reworkReason = reworkReason;
    }

    public String getReworkQuantity() {
        return reworkQuantity;
    }

    public void setReworkQuantity(String reworkQuantity) {
        this.reworkQuantity = reworkQuantity;
    }

    public String getReworkMaterialNumber() {
        return reworkMaterialNumber;
    }

    public void setReworkMaterialNumber(String reworkMaterialNumber) {
        this.reworkMaterialNumber = reworkMaterialNumber;
    }

    public String getReworkConfirmResult() {
        return reworkConfirmResult;
    }

    public void setReworkConfirmResult(String reworkConfirmResult) {
        this.reworkConfirmResult = reworkConfirmResult;
    }

    public String getReworkConfirmor() {
        return reworkConfirmor;
    }

    public void setReworkConfirmor(String reworkConfirmor) {
        this.reworkConfirmor = reworkConfirmor;
    }

    public Date getReworkConfirmTime() {
        return reworkConfirmTime;
    }

    public void setReworkConfirmTime(Date reworkConfirmTime) {
        this.reworkConfirmTime = reworkConfirmTime;
    }

    public String getCraftsSection() {
        return craftsSection;
    }

    public void setCraftsSection(String craftsSection) {
        this.craftsSection = craftsSection;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public Date getProblemTime() {
        return problemTime;
    }

    public void setProblemTime(Date problemTime) {
        this.problemTime = problemTime;
    }

    public String getRootCauseResponsibility() {
        return rootCauseResponsibility;
    }

    public void setRootCauseResponsibility(String rootCauseResponsibility) {
        this.rootCauseResponsibility = rootCauseResponsibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getProjectHandler() {
        return projectHandler;
    }

    public void setProjectHandler(String projectHandler) {
        this.projectHandler = projectHandler;
    }

    public Date getProjectHandleTime() {
        return projectHandleTime;
    }

    public void setProjectHandleTime(Date projectHandleTime) {
        this.projectHandleTime = projectHandleTime;
    }

    public Date getPlanHandleTime() {
        return planHandleTime;
    }

    public void setPlanHandleTime(Date planHandleTime) {
        this.planHandleTime = planHandleTime;
    }

    public Date getProductionExecutionTime() {
        return productionExecutionTime;
    }

    public void setProductionExecutionTime(Date productionExecutionTime) {
        this.productionExecutionTime = productionExecutionTime;
    }

    public String getPlanHandler() {
        return planHandler;
    }

    public void setPlanHandler(String planHandler) {
        this.planHandler = planHandler;
    }

    public String getProductionHandler() {
        return productionHandler;
    }

    public void setProductionHandler(String productionHandler) {
        this.productionHandler = productionHandler;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", reworkNumber=").append(reworkNumber);
        sb.append(", reworkTheme=").append(reworkTheme);
        sb.append(", reworkReason=").append(reworkReason);
        sb.append(", reworkQuantity=").append(reworkQuantity);
        sb.append(", reworkMaterialNumber=").append(reworkMaterialNumber);
        sb.append(", reworkConfirmResult=").append(reworkConfirmResult);
        sb.append(", reworkConfirmor=").append(reworkConfirmor);
        sb.append(", reworkConfirmTime=").append(reworkConfirmTime);
        sb.append(", craftsSection=").append(craftsSection);
        sb.append(", model=").append(model);
        sb.append(", problemType=").append(problemType);
        sb.append(", problemTime=").append(problemTime);
        sb.append(", rootCauseResponsibility=").append(rootCauseResponsibility);
        sb.append(", status=").append(status);
        sb.append(", statusDisplay=").append(statusDisplay);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", projectHandler=").append(projectHandler);
        sb.append(", planHandler=").append(planHandler);
        sb.append(", productionHandler=").append(productionHandler);
        sb.append(", projectHandleTime=").append(projectHandleTime);
        sb.append(", planHandleTime=").append(planHandleTime);
        sb.append(", productionExecutionTime=").append(productionExecutionTime);
        sb.append(", assignee=").append(assignee);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}