package cn.jwis.qualityworkflow.modules.qims.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class QimsBlackInfo implements Serializable {
    private String id;

    /**
     * 问题编号
     *
     * @mbggenerated
     */
    private String questionNumber;

    /**
     * 报告主题
     *
     * @mbggenerated
     */
    private String reportSubject;

    /**
     * 异常站点
     *
     * @mbggenerated
     */
    private String anomalySite;

    /**
     * 异常报告人
     *
     * @mbggenerated
     */
    private String anomalyReporter;

    /**
     * 发生时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date reportingTime;

    /**
     * 段别
     *
     * @mbggenerated
     */
    private String paragraphParting;

    /**
     * 线体
     *
     * @mbggenerated
     */
    private String line;

    /**
     * 机型
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 班次
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * 订单号
     *
     * @mbggenerated
     */
    private String orderSn;

    /**
     * 订单数量
     *
     * @mbggenerated
     */
    private Integer orderQty;

    /**
     * 检查数
     *
     * @mbggenerated
     */
    private Integer inspectionQty;

    /**
     * 故障数
     *
     * @mbggenerated
     */
    private Integer failuresNumber;

    /**
     * 故障率
     *
     * @mbggenerated
     */
    private Float failureRate;

    /**
     * 故障现象
     *
     * @mbggenerated
     */
    private String failurePhenomenon;

    /**
     * 问题描述
     *
     * @mbggenerated
     */
    private String problemDescription;

    /**
     * 异常责任人
     *
     * @mbggenerated
     */
    private String abnormalResponsiblePerson;

    /**
     * 是否停线
     *
     * @mbggenerated
     */
    private String isStop;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 问题编码的标识
     *
     * @mbggenerated
     */
    private String itemNumber;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 组长
     *
     * @mbggenerated
     */
    private String groupLeader;

    /**
     * 组员
     *
     * @mbggenerated
     */
    private String crew;

    /**
     * 评估结果
     *
     * @mbggenerated
     */
    private String assessmentResult;

    /**
     * 评估依据
     *
     * @mbggenerated
     */
    private String basisForAssessment;

    /**
     * 初步发生原因
     *
     * @mbggenerated
     */
    private String primaryCause;

    /**
     * 初步分析结论
     *
     * @mbggenerated
     */
    private String preliminaryAnalysisConclusion;

    /**
     * 停线原因
     *
     * @mbggenerated
     */
    private String stopLineBecause;

    /**
     * 临时处理措施
     *
     * @mbggenerated
     */
    private String temporaryDisposalMeasures;

    /**
     * 临时处理意见
     *
     * @mbggenerated
     */
    private String interimDisposalAdvice;

    /**
     * 根本原因分析责任部门
     *
     * @mbggenerated
     */
    private String rootCauseDepartment;

    /**
     * 根本原因分析人
     *
     * @mbggenerated
     */
    private String rootCauseAnalyst;

    /**
     * 初步原因分析人
     *
     * @mbggenerated
     */
    private String causeAnalysisPerson;

    /**
     * 初步原因分析提交时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date causeAnalysisTime;

    /**
     * 根本产生原因
     *
     * @mbggenerated
     */
    private String rootCause;

    /**
     * 根本漏出原因
     *
     * @mbggenerated
     */
    private String rootCausesLeakOut;

    /**
     * 根本分析结论
     *
     * @mbggenerated
     */
    private String fundamentalAnalysisConclusion;

    /**
     * 长期处理措施1
     *
     * @mbggenerated
     */
    private String longTermTreatment1;

    /**
     * 长期处理措施2
     *
     * @mbggenerated
     */
    private String longTermTreatment2;

    /**
     * 长期处理措施3
     *
     * @mbggenerated
     */
    private String longTermTreatment3;

    /**
     * 长期处理措施4
     *
     * @mbggenerated
     */
    private String longTermTreatment4;

    /**
     * 长期处理措施5
     *
     * @mbggenerated
     */
    private String longTermTreatment5;

    /**
     * 根本原因分析提交时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date rootCauseTime;

    /**
     * 根本原因分析审核结果
     *
     * @mbggenerated
     */
    private String handlingComments;

    /**
     * 根本原因分析审核意见
     *
     * @mbggenerated
     */
    private String rootCauseAnalysisAuditOpinion;

    /**
     * 根本原因分析审核人
     *
     * @mbggenerated
     */
    private String rootCauseAuditor;

    /**
     * 根本原因分析审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date rootCauseAnalysisTime;

    /**
     * 效果验证结果
     *
     * @mbggenerated
     */
    private String effectVerificationResults;

    /**
     * 效果验证人
     *
     * @mbggenerated
     */
    private String effectVerifier;

    /**
     * 效果验证时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date effectVerificationTime;

    /**
     * 效果审核结果
     *
     * @mbggenerated
     */
    private String auditResults;

    /**
     * 效果审核意见
     *
     * @mbggenerated
     */
    private String effectAuditOpinion;

    /**
     * 效果审核人
     *
     * @mbggenerated
     */
    private String effectReviewer;

    /**
     * 效果审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date effectReviewTime;

    /**
     * 结案审批结果
     *
     * @mbggenerated
     */
    private String caseClosingResult;

    /**
     * 结案审批意见
     *
     * @mbggenerated
     */
    private String caseClosingOpinion;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date caseClosingDate;

    /**
     * 初步原因分析LT
     *
     * @mbggenerated
     */
    private String preliminaryCauseAnalysisLt;

    /**
     * 根本原因分析LT
     *
     * @mbggenerated
     */
    private String rootCauseAnalysisLt;

    /**
     * 根本原因审核LT
     *
     * @mbggenerated
     */
    private String rootCauseAuditLt;

    /**
     * 数据源的标识
     *
     * @mbggenerated
     */
    private String dataSources;

    /**
     * 问题类型
     *
     * @mbggenerated
     */
    private String problemType;

    /**
     * 当前TL
     *
     * @mbggenerated
     */
    private String currentLt;

    /**
     * 结案及时性
     *
     * @mbggenerated
     */
    private String conclusionOfCase;

    /**
     * 异常责任人部门
     */
    private String abnormalResponsibleDepartment;

    private String defectType;

    private String workflowBusinessId;
    private String processInstanceId;
    private String taskId;
    private String assignee;
    private String taskState;

    private String assigneer;

    private String assigneerDepartment;

    private String businessDatetime;

    private String modelCategory;

    private String manufactureType;

    private  String item;

    private  String businessModel;

    private static final long serialVersionUID = 1L;

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getReportSubject() {
        return reportSubject;
    }

    public void setReportSubject(String reportSubject) {
        this.reportSubject = reportSubject;
    }

    public String getAnomalySite() {
        return anomalySite;
    }

    public void setAnomalySite(String anomalySite) {
        this.anomalySite = anomalySite;
    }

    public String getAnomalyReporter() {
        return anomalyReporter;
    }

    public void setAnomalyReporter(String anomalyReporter) {
        this.anomalyReporter = anomalyReporter;
    }

    public Date getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(Date reportingTime) {
        this.reportingTime = reportingTime;
    }

    public String getParagraphParting() {
        return paragraphParting;
    }

    public void setParagraphParting(String paragraphParting) {
        this.paragraphParting = paragraphParting;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getInspectionQty() {
        return inspectionQty;
    }

    public void setInspectionQty(Integer inspectionQty) {
        this.inspectionQty = inspectionQty;
    }

    public Integer getFailuresNumber() {
        return failuresNumber;
    }

    public void setFailuresNumber(Integer failuresNumber) {
        this.failuresNumber = failuresNumber;
    }

    public Float getFailureRate() {
        return failureRate;
    }

    public void setFailureRate(Float failureRate) {
        this.failureRate = failureRate;
    }

    public String getFailurePhenomenon() {
        return failurePhenomenon;
    }

    public void setFailurePhenomenon(String failurePhenomenon) {
        this.failurePhenomenon = failurePhenomenon;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getAbnormalResponsiblePerson() {
        return abnormalResponsiblePerson;
    }

    public void setAbnormalResponsiblePerson(String abnormalResponsiblePerson) {
        this.abnormalResponsiblePerson = abnormalResponsiblePerson;
    }

    public String getIsStop() {
        return isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
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

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getAssessmentResult() {
        return assessmentResult;
    }

    public void setAssessmentResult(String assessmentResult) {
        this.assessmentResult = assessmentResult;
    }

    public String getBasisForAssessment() {
        return basisForAssessment;
    }

    public void setBasisForAssessment(String basisForAssessment) {
        this.basisForAssessment = basisForAssessment;
    }

    public String getPrimaryCause() {
        return primaryCause;
    }

    public void setPrimaryCause(String primaryCause) {
        this.primaryCause = primaryCause;
    }

    public String getPreliminaryAnalysisConclusion() {
        return preliminaryAnalysisConclusion;
    }

    public void setPreliminaryAnalysisConclusion(String preliminaryAnalysisConclusion) {
        this.preliminaryAnalysisConclusion = preliminaryAnalysisConclusion;
    }

    public String getStopLineBecause() {
        return stopLineBecause;
    }

    public void setStopLineBecause(String stopLineBecause) {
        this.stopLineBecause = stopLineBecause;
    }

    public String getTemporaryDisposalMeasures() {
        return temporaryDisposalMeasures;
    }

    public void setTemporaryDisposalMeasures(String temporaryDisposalMeasures) {
        this.temporaryDisposalMeasures = temporaryDisposalMeasures;
    }

    public String getInterimDisposalAdvice() {
        return interimDisposalAdvice;
    }

    public void setInterimDisposalAdvice(String interimDisposalAdvice) {
        this.interimDisposalAdvice = interimDisposalAdvice;
    }

    public String getRootCauseDepartment() {
        return rootCauseDepartment;
    }

    public void setRootCauseDepartment(String rootCauseDepartment) {
        this.rootCauseDepartment = rootCauseDepartment;
    }

    public String getRootCauseAnalyst() {
        return rootCauseAnalyst;
    }

    public void setRootCauseAnalyst(String rootCauseAnalyst) {
        this.rootCauseAnalyst = rootCauseAnalyst;
    }

    public String getCauseAnalysisPerson() {
        return causeAnalysisPerson;
    }

    public void setCauseAnalysisPerson(String causeAnalysisPerson) {
        this.causeAnalysisPerson = causeAnalysisPerson;
    }

    public Date getCauseAnalysisTime() {
        return causeAnalysisTime;
    }

    public void setCauseAnalysisTime(Date causeAnalysisTime) {
        this.causeAnalysisTime = causeAnalysisTime;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getRootCausesLeakOut() {
        return rootCausesLeakOut;
    }

    public void setRootCausesLeakOut(String rootCausesLeakOut) {
        this.rootCausesLeakOut = rootCausesLeakOut;
    }

    public String getFundamentalAnalysisConclusion() {
        return fundamentalAnalysisConclusion;
    }

    public void setFundamentalAnalysisConclusion(String fundamentalAnalysisConclusion) {
        this.fundamentalAnalysisConclusion = fundamentalAnalysisConclusion;
    }

    public String getLongTermTreatment1() {
        return longTermTreatment1;
    }

    public void setLongTermTreatment1(String longTermTreatment1) {
        this.longTermTreatment1 = longTermTreatment1;
    }

    public String getLongTermTreatment2() {
        return longTermTreatment2;
    }

    public void setLongTermTreatment2(String longTermTreatment2) {
        this.longTermTreatment2 = longTermTreatment2;
    }

    public String getLongTermTreatment3() {
        return longTermTreatment3;
    }

    public void setLongTermTreatment3(String longTermTreatment3) {
        this.longTermTreatment3 = longTermTreatment3;
    }

    public String getLongTermTreatment4() {
        return longTermTreatment4;
    }

    public void setLongTermTreatment4(String longTermTreatment4) {
        this.longTermTreatment4 = longTermTreatment4;
    }

    public String getLongTermTreatment5() {
        return longTermTreatment5;
    }

    public void setLongTermTreatment5(String longTermTreatment5) {
        this.longTermTreatment5 = longTermTreatment5;
    }

    public Date getRootCauseTime() {
        return rootCauseTime;
    }

    public void setRootCauseTime(Date rootCauseTime) {
        this.rootCauseTime = rootCauseTime;
    }

    public String getHandlingComments() {
        return handlingComments;
    }

    public void setHandlingComments(String handlingComments) {
        this.handlingComments = handlingComments;
    }

    public String getRootCauseAnalysisAuditOpinion() {
        return rootCauseAnalysisAuditOpinion;
    }

    public void setRootCauseAnalysisAuditOpinion(String rootCauseAnalysisAuditOpinion) {
        this.rootCauseAnalysisAuditOpinion = rootCauseAnalysisAuditOpinion;
    }

    public String getRootCauseAuditor() {
        return rootCauseAuditor;
    }

    public void setRootCauseAuditor(String rootCauseAuditor) {
        this.rootCauseAuditor = rootCauseAuditor;
    }

    public Date getRootCauseAnalysisTime() {
        return rootCauseAnalysisTime;
    }

    public void setRootCauseAnalysisTime(Date rootCauseAnalysisTime) {
        this.rootCauseAnalysisTime = rootCauseAnalysisTime;
    }

    public String getEffectVerificationResults() {
        return effectVerificationResults;
    }

    public void setEffectVerificationResults(String effectVerificationResults) {
        this.effectVerificationResults = effectVerificationResults;
    }

    public String getEffectVerifier() {
        return effectVerifier;
    }

    public void setEffectVerifier(String effectVerifier) {
        this.effectVerifier = effectVerifier;
    }

    public Date getEffectVerificationTime() {
        return effectVerificationTime;
    }

    public void setEffectVerificationTime(Date effectVerificationTime) {
        this.effectVerificationTime = effectVerificationTime;
    }

    public String getAuditResults() {
        return auditResults;
    }

    public void setAuditResults(String auditResults) {
        this.auditResults = auditResults;
    }

    public String getEffectAuditOpinion() {
        return effectAuditOpinion;
    }

    public void setEffectAuditOpinion(String effectAuditOpinion) {
        this.effectAuditOpinion = effectAuditOpinion;
    }

    public String getEffectReviewer() {
        return effectReviewer;
    }

    public void setEffectReviewer(String effectReviewer) {
        this.effectReviewer = effectReviewer;
    }

    public Date getEffectReviewTime() {
        return effectReviewTime;
    }

    public void setEffectReviewTime(Date effectReviewTime) {
        this.effectReviewTime = effectReviewTime;
    }

    public String getCaseClosingResult() {
        return caseClosingResult;
    }

    public void setCaseClosingResult(String caseClosingResult) {
        this.caseClosingResult = caseClosingResult;
    }

    public String getCaseClosingOpinion() {
        return caseClosingOpinion;
    }

    public void setCaseClosingOpinion(String caseClosingOpinion) {
        this.caseClosingOpinion = caseClosingOpinion;
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

    public String getPreliminaryCauseAnalysisLt() {
        return preliminaryCauseAnalysisLt;
    }

    public void setPreliminaryCauseAnalysisLt(String preliminaryCauseAnalysisLt) {
        this.preliminaryCauseAnalysisLt = preliminaryCauseAnalysisLt;
    }

    public String getRootCauseAnalysisLt() {
        return rootCauseAnalysisLt;
    }

    public void setRootCauseAnalysisLt(String rootCauseAnalysisLt) {
        this.rootCauseAnalysisLt = rootCauseAnalysisLt;
    }

    public String getRootCauseAuditLt() {
        return rootCauseAuditLt;
    }

    public void setRootCauseAuditLt(String rootCauseAuditLt) {
        this.rootCauseAuditLt = rootCauseAuditLt;
    }

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getCurrentLt() {
        return currentLt;
    }

    public void setCurrentLt(String currentLt) {
        this.currentLt = currentLt;
    }

    public String getConclusionOfCase() {
        return conclusionOfCase;
    }

    public void setConclusionOfCase(String conclusionOfCase) {
        this.conclusionOfCase = conclusionOfCase;
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

    public String getBusinessDatetime() {
        return businessDatetime;
    }

    public void setBusinessDatetime(String businessDatetime) {
        this.businessDatetime = businessDatetime;
    }

    public String getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(String modelCategory) {
        this.modelCategory = modelCategory;
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

    public String getAbnormalResponsibleDepartment() {
        return abnormalResponsibleDepartment;
    }

    public void setAbnormalResponsibleDepartment(String abnormalResponsibleDepartment) {
        this.abnormalResponsibleDepartment = abnormalResponsibleDepartment;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", questionNumber=").append(questionNumber);
        sb.append(", reportSubject=").append(reportSubject);
        sb.append(", anomalySite=").append(anomalySite);
        sb.append(", anomalyReporter=").append(anomalyReporter);
        sb.append(", reportingTime=").append(reportingTime);
        sb.append(", paragraphParting=").append(paragraphParting);
        sb.append(", line=").append(line);
        sb.append(", model=").append(model);
        sb.append(", frequency=").append(frequency);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", orderQty=").append(orderQty);
        sb.append(", inspectionQty=").append(inspectionQty);
        sb.append(", failuresNumber=").append(failuresNumber);
        sb.append(", failureRate=").append(failureRate);
        sb.append(", failurePhenomenon=").append(failurePhenomenon);
        sb.append(", problemDescription=").append(problemDescription);
        sb.append(", abnormalResponsiblePerson=").append(abnormalResponsiblePerson);
        sb.append(", isStop=").append(isStop);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", itemNumber=").append(itemNumber);
        sb.append(", status=").append(status);
        sb.append(", groupLeader=").append(groupLeader);
        sb.append(", crew=").append(crew);
        sb.append(", assessmentResult=").append(assessmentResult);
        sb.append(", basisForAssessment=").append(basisForAssessment);
        sb.append(", primaryCause=").append(primaryCause);
        sb.append(", preliminaryAnalysisConclusion=").append(preliminaryAnalysisConclusion);
        sb.append(", stopLineBecause=").append(stopLineBecause);
        sb.append(", temporaryDisposalMeasures=").append(temporaryDisposalMeasures);
        sb.append(", interimDisposalAdvice=").append(interimDisposalAdvice);
        sb.append(", rootCauseDepartment=").append(rootCauseDepartment);
        sb.append(", rootCauseAnalyst=").append(rootCauseAnalyst);
        sb.append(", causeAnalysisPerson=").append(causeAnalysisPerson);
        sb.append(", causeAnalysisTime=").append(causeAnalysisTime);
        sb.append(", rootCause=").append(rootCause);
        sb.append(", rootCausesLeakOut=").append(rootCausesLeakOut);
        sb.append(", fundamentalAnalysisConclusion=").append(fundamentalAnalysisConclusion);
        sb.append(", longTermTreatment1=").append(longTermTreatment1);
        sb.append(", longTermTreatment2=").append(longTermTreatment2);
        sb.append(", longTermTreatment3=").append(longTermTreatment3);
        sb.append(", longTermTreatment4=").append(longTermTreatment4);
        sb.append(", longTermTreatment5=").append(longTermTreatment5);
        sb.append(", rootCauseTime=").append(rootCauseTime);
        sb.append(", handlingComments=").append(handlingComments);
        sb.append(", rootCauseAnalysisAuditOpinion=").append(rootCauseAnalysisAuditOpinion);
        sb.append(", rootCauseAuditor=").append(rootCauseAuditor);
        sb.append(", rootCauseAnalysisTime=").append(rootCauseAnalysisTime);
        sb.append(", effectVerificationResults=").append(effectVerificationResults);
        sb.append(", effectVerifier=").append(effectVerifier);
        sb.append(", effectVerificationTime=").append(effectVerificationTime);
        sb.append(", auditResults=").append(auditResults);
        sb.append(", effectAuditOpinion=").append(effectAuditOpinion);
        sb.append(", effectReviewer=").append(effectReviewer);
        sb.append(", effectReviewTime=").append(effectReviewTime);
        sb.append(", caseClosingResult=").append(caseClosingResult);
        sb.append(", caseClosingOpinion=").append(caseClosingOpinion);
        sb.append(", caseClosingApprover=").append(caseClosingApprover);
        sb.append(", caseClosingDate=").append(caseClosingDate);
        sb.append(", preliminaryCauseAnalysisLt=").append(preliminaryCauseAnalysisLt);
        sb.append(", rootCauseAnalysisLt=").append(rootCauseAnalysisLt);
        sb.append(", rootCauseAuditLt=").append(rootCauseAuditLt);
        sb.append(", dataSources=").append(dataSources);
        sb.append(", problemType=").append(problemType);
        sb.append(", currentLt=").append(currentLt);
        sb.append(", conclusionOfCase=").append(conclusionOfCase);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}