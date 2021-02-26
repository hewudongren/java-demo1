package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

public class EsdAuditAbnormal implements Serializable {
    /**
     * id唯一主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     *  项目编码
     */
    private String itemNumber;

    /**
     * 稽核类型
     *
     * @mbggenerated
     */
    private String auditType;

    /**
     * 稽核日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date auditDate;

    /**
     * 班次
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * 区域/线体
     *
     * @mbggenerated
     */
    private String line;

    /**
     * 检验员
     *
     * @mbggenerated
     */
    private String auditer;

    /**
     * 问题分类
     *
     * @mbggenerated
     */
    private String problemClassification;

    /**
     * 问题等级
     *
     * @mbggenerated
     */
    private String gradeProblem;

    /**
     * 责任部门
     *
     * @mbggenerated
     */
    private String responsibleDepartment;

    /**
     * 责任人
     *
     * @mbggenerated
     */
    private String personLiable;

    /**
     * 问题描述
     *
     * @mbggenerated
     */
    private String problemDescription;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 原因分析
     *
     * @mbggenerated
     */
    private String causeAnalysis;

    /**
     * 原因分析人
     *
     * @mbggenerated
     */
    private String causalAnalyst;

    /**
     * 原因分析时间
     *
     * @mbggenerated
     */
    private Date causalAnalystDate;

    /**
     * 改善措施
     *
     * @mbggenerated
     */
    private String improvementMeasures;

    /**
     * 改善措施人
     *
     * @mbggenerated
     */
    private String improvementMeasuresor;

    /**
     * 改善措施时间
     *
     * @mbggenerated
     */
    private Date improvementMeasuresDate;

    /**
     * 效果验证
     *
     * @mbggenerated
     */
    private String effectVerification;

    /**
     * 效果验证人
     *
     * @mbggenerated
     */
    private String verifiedor;

    /**
     * 效果验证时间
     *
     * @mbggenerated
     */
    private Date verifiedDate;

    /**
     * 审批结果
     *
     * @mbggenerated
     */
    private String approvalResult;

    /**
     * 结案审批人
     *
     * @mbggenerated
     */
    private String caseClosingApprover;

    /**
     * 结案审批时间
     *
     * @mbggenerated
     */
    private Date caseClosingDate;

    /**
     * 当前处理人
     *
     * @mbggenerated
     */
    private String assigneer;

    /**
     * 当前处理部门
     *
     * @mbggenerated
     */
    private String assigneerDepartment;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;

    private static final long serialVersionUID = 1L;

    private String workflowBusinessId;
    private String processInstanceId;
    private String taskId;
    private String assignee;
    private String taskState;

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

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    public String getProblemClassification() {
        return problemClassification;
    }

    public void setProblemClassification(String problemClassification) {
        this.problemClassification = problemClassification;
    }

    public String getGradeProblem() {
        return gradeProblem;
    }

    public void setGradeProblem(String gradeProblem) {
        this.gradeProblem = gradeProblem;
    }

    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCauseAnalysis() {
        return causeAnalysis;
    }

    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }

    public String getCausalAnalyst() {
        return causalAnalyst;
    }

    public void setCausalAnalyst(String causalAnalyst) {
        this.causalAnalyst = causalAnalyst;
    }

    public Date getCausalAnalystDate() {
        return causalAnalystDate;
    }

    public void setCausalAnalystDate(Date causalAnalystDate) {
        this.causalAnalystDate = causalAnalystDate;
    }

    public String getImprovementMeasures() {
        return improvementMeasures;
    }

    public void setImprovementMeasures(String improvementMeasures) {
        this.improvementMeasures = improvementMeasures;
    }

    public String getImprovementMeasuresor() {
        return improvementMeasuresor;
    }

    public void setImprovementMeasuresor(String improvementMeasuresor) {
        this.improvementMeasuresor = improvementMeasuresor;
    }

    public Date getImprovementMeasuresDate() {
        return improvementMeasuresDate;
    }

    public void setImprovementMeasuresDate(Date improvementMeasuresDate) {
        this.improvementMeasuresDate = improvementMeasuresDate;
    }

    public String getEffectVerification() {
        return effectVerification;
    }

    public void setEffectVerification(String effectVerification) {
        this.effectVerification = effectVerification;
    }

    public String getVerifiedor() {
        return verifiedor;
    }

    public void setVerifiedor(String verifiedor) {
        this.verifiedor = verifiedor;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

    public String getCaseClosingApprover() {
        return caseClosingApprover;
    }

    public void setCaseClosingApprover(String caseClosingApprover) {
        this.caseClosingApprover = caseClosingApprover;
    }

    public Date getCaseClosingDate() {
        return caseClosingDate;
    }

    public void setCaseClosingDate(Date caseClosingDate) {
        this.caseClosingDate = caseClosingDate;
    }

    public String getAssigneer() {
        return assigneer;
    }

    public void setAssigneer(String assigneer) {
        this.assigneer = assigneer;
    }

    public String getAssigneerDepartment() {
        return assigneerDepartment;
    }

    public void setAssigneerDepartment(String assigneerDepartment) {
        this.assigneerDepartment = assigneerDepartment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getWorkflowBusinessId() {
        return workflowBusinessId;
    }

    public void setWorkflowBusinessId(String workflowBusinessId) {
        this.workflowBusinessId = workflowBusinessId;
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

    @Override
    public String toString() {
        return "EsdAuditAbnormal{" + "id='" + id + '\'' + ", itemNumber='" + itemNumber + '\'' + ", auditType='" + auditType + '\'' + ", auditDate=" + auditDate + ", frequency='" + frequency + '\'' + ", line='" + line + '\'' + ", auditer='" + auditer + '\'' + ", problemClassification='" + problemClassification + '\'' + ", gradeProblem='" + gradeProblem + '\'' + ", responsibleDepartment='" + responsibleDepartment + '\'' + ", personLiable='" + personLiable + '\'' + ", problemDescription='" + problemDescription + '\'' + ", status='" + status + '\'' + ", causeAnalysis='" + causeAnalysis + '\'' + ", causalAnalyst='" + causalAnalyst + '\'' + ", causalAnalystDate=" + causalAnalystDate + ", improvementMeasures='" + improvementMeasures + '\'' + ", improvementMeasuresor='" + improvementMeasuresor + '\'' + ", improvementMeasuresDate=" + improvementMeasuresDate + ", effectVerification='" + effectVerification + '\'' + ", verifiedor='" + verifiedor + '\'' + ", verifiedDate=" + verifiedDate + ", approvalResult='" + approvalResult + '\'' + ", caseClosingApprover='" + caseClosingApprover + '\'' + ", caseClosingDate=" + caseClosingDate + ", assigneer='" + assigneer + '\'' + ", assigneerDepartment='" + assigneerDepartment + '\'' + ", createDate=" + createDate + ", creator='" + creator + '\'' + ", workflowBusinessId='" + workflowBusinessId + '\'' + ", processInstanceId='" + processInstanceId + '\'' + ", taskId='" + taskId + '\'' + ", assignee='" + assignee + '\'' + ", taskState='" + taskState + '\'' + '}';
    }
}