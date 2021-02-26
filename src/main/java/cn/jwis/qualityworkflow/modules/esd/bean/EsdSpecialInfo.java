package cn.jwis.qualityworkflow.modules.esd.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EsdSpecialInfo implements Serializable {
    /**
     * 唯一主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 样品名
     *
     * @mbggenerated
     */
    private String sampleName;

    /**
     * 供应商
     *
     * @mbggenerated
     */
    private String supplier;

    /**
     * 机型
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 料号
     *
     * @mbggenerated
     */
    private String materials;

    /**
     * 抽样方式
     *
     * @mbggenerated
     */
    private String samplingMethod;

    /**
     * 抽样比率
     *
     * @mbggenerated
     */
    private String samplingRate;

    /**
     * 环境温度
     *
     * @mbggenerated
     */
    private String ambientTemperature;

    /**
     * 检验人
     *
     * @mbggenerated
     */
    private String checker;

    /**
     * 环境湿度
     *
     * @mbggenerated
     */
    private String ambientHumidity;

    /**
     * 处理方式
     *
     * @mbggenerated
     */
    private String handlingMethod;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 处理结论
     *
     * @mbggenerated
     */
    private String treatmentConclusion;

    /**
     * 处置结果验证
     *
     * @mbggenerated
     */
    private String disposalResults;


    /**
     * 到货数量
     *
     * @mbggenerated
     */
    private Integer deliveredQty;

    /**
     * 抽样数量
     *
     * @mbggenerated
     */
    private Integer samplingQty;

    /**
     * 检验日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date inspectionDate;

    /**
     * 规格型号
     *
     * @mbggenerated
     */
    private String specificationModel;

    /**
     * 合格率
     *
     * @mbggenerated
     */
    private Float acceptanceRate;

    /**
     * 特采单号
     *
     * @mbggenerated
     */
    private String specialMiningNo;

    /**
     * 需求部门
     *
     * @mbggenerated
     */
    private String demandDepartment;

    /**
     * 需求提出人
     *
     * @mbggenerated
     */
    private String demandProposer;

    /**
     * 需求日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date demandDate;

    /**
     * 特采措施
     *
     * @mbggenerated
     */
    private String specialMiningMeasures;

    /**
     * 异常描述
     *
     * @mbggenerated
     */
    private String exceptionDescription;

    /**
     * 特采理由
     *
     * @mbggenerated
     */
    private String specialReasons;

    /**
     * 短期处理措施
     *
     * @mbggenerated
     */
    private String shortTreatmentMeasures;

    /**
     * 长期处理措施
     *
     * @mbggenerated
     */
    private String longTreatmentMeasures;

    /**
     * 申请部门处理措施
     *
     * @mbggenerated
     */
    private String departmentTreatmentMeasures;

    /**
     * 长期改善措施
     *
     * @mbggenerated
     */
    private String longImprovementMeasures;
    /**
     *  特采申请人
     */
    private String specialApplicant;
    /**
     * 部门审核人
     *
     * @mbggenerated
     */
    private String departmentAuditor;

    /**
     * ESD处理人
     *
     * @mbggenerated
     */
    private String esdHandler;

    /**
     * ESD委员会处理意见
     *
     * @mbggenerated
     */
    private String esdHandlingComments;
    /**
     * 流程附件信息
     */
    private List<JSONObject> annex;

    /**
     * ESD协调员
     *
     * @mbggenerated
     */
    private String esdCoordinator;

    /**
     * ESD处理时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date esdHandlingDate;

    /**
     * ESD协调审核结果
     *
     * @mbggenerated
     */
    private String esdCoordinatResult;

    /**
     * ESD协调审核意见
     *
     * @mbggenerated
     */
    private String esdCoordinatOpinion;

    /**
     * ESD委员会审核人
     *
     * @mbggenerated
     */
    private String committeeAuditor;

    /**
     * ESD协调审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date esdCoordinatDate;

    /**
     * 部门审核结果
     *
     * @mbggenerated
     */
    private String handlingComments;

    /**
     * 部门审核意见
     *
     * @mbggenerated
     */
    private String departmentAuditOpinion;

    /**
     * 部门审核日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date departmentAuditDate;

    /**
     * ESD委员会审核结果
     *
     * @mbggenerated
     */
    private String auditResults;

    /**
     * ESD委员会审核意见
     *
     * @mbggenerated
     */
    private String committeeAuditOpinion;

    /**
     * ESD委员会审核日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date committeeAuditDate;

    /**
     * 特采处理结果验证记录
     *
     * @mbggenerated
     */
    private String verificationOfResults;

    /**
     * ESD特采验证人
     *
     * @mbggenerated
     */
    private String specialProcurementVerifier;

    /**
     * ESD特采验证日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date specialProcurementDate;

    /**
     *  当前处理人
     */
    private  String assigneer;
    /**
     * 当前处理部门
     */
    private  String assigneerDepartment;

    /**
     * 合格数量
     */
    private  Integer qualifiedQty;

    /**
     * 最终结果
     */
    private String finalResult;
    private String workflowBusinessId;
    private String processInstanceId;
    private String taskId;
    private String assignee;
    private String taskState;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getSamplingMethod() {
        return samplingMethod;
    }

    public void setSamplingMethod(String samplingMethod) {
        this.samplingMethod = samplingMethod;
    }

    public String getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(String samplingRate) {
        this.samplingRate = samplingRate;
    }

    public String getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(String ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getAmbientHumidity() {
        return ambientHumidity;
    }

    public void setAmbientHumidity(String ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }

    public String getHandlingMethod() {
        return handlingMethod;
    }

    public void setHandlingMethod(String handlingMethod) {
        this.handlingMethod = handlingMethod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTreatmentConclusion() {
        return treatmentConclusion;
    }

    public void setTreatmentConclusion(String treatmentConclusion) {
        this.treatmentConclusion = treatmentConclusion;
    }

    public String getDisposalResults() {
        return disposalResults;
    }

    public void setDisposalResults(String disposalResults) {
        this.disposalResults = disposalResults;
    }

    public Integer getDeliveredQty() {
        return deliveredQty;
    }

    public void setDeliveredQty(Integer deliveredQty) {
        this.deliveredQty = deliveredQty;
    }

    public Integer getSamplingQty() {
        return samplingQty;
    }

    public void setSamplingQty(Integer samplingQty) {
        this.samplingQty = samplingQty;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getSpecificationModel() {
        return specificationModel;
    }

    public void setSpecificationModel(String specificationModel) {
        this.specificationModel = specificationModel;
    }

    public Float getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(Float acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public String getSpecialMiningNo() {
        return specialMiningNo;
    }

    public void setSpecialMiningNo(String specialMiningNo) {
        this.specialMiningNo = specialMiningNo;
    }

    public String getDemandDepartment() {
        return demandDepartment;
    }

    public void setDemandDepartment(String demandDepartment) {
        this.demandDepartment = demandDepartment;
    }

    public String getDemandProposer() {
        return demandProposer;
    }

    public void setDemandProposer(String demandProposer) {
        this.demandProposer = demandProposer;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public String getSpecialMiningMeasures() {
        return specialMiningMeasures;
    }

    public void setSpecialMiningMeasures(String specialMiningMeasures) {
        this.specialMiningMeasures = specialMiningMeasures;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public String getSpecialReasons() {
        return specialReasons;
    }

    public void setSpecialReasons(String specialReasons) {
        this.specialReasons = specialReasons;
    }

    public String getShortTreatmentMeasures() {
        return shortTreatmentMeasures;
    }

    public void setShortTreatmentMeasures(String shortTreatmentMeasures) {
        this.shortTreatmentMeasures = shortTreatmentMeasures;
    }

    public String getLongTreatmentMeasures() {
        return longTreatmentMeasures;
    }

    public void setLongTreatmentMeasures(String longTreatmentMeasures) {
        this.longTreatmentMeasures = longTreatmentMeasures;
    }

    public String getDepartmentTreatmentMeasures() {
        return departmentTreatmentMeasures;
    }

    public void setDepartmentTreatmentMeasures(String departmentTreatmentMeasures) {
        this.departmentTreatmentMeasures = departmentTreatmentMeasures;
    }

    public String getLongImprovementMeasures() {
        return longImprovementMeasures;
    }

    public void setLongImprovementMeasures(String longImprovementMeasures) {
        this.longImprovementMeasures = longImprovementMeasures;
    }

    public String getDepartmentAuditor() {
        return departmentAuditor;
    }

    public void setDepartmentAuditor(String departmentAuditor) {
        this.departmentAuditor = departmentAuditor;
    }

    public String getEsdHandler() {
        return esdHandler;
    }

    public void setEsdHandler(String esdHandler) {
        this.esdHandler = esdHandler;
    }

    public String getEsdHandlingComments() {
        return esdHandlingComments;
    }

    public void setEsdHandlingComments(String esdHandlingComments) {
        this.esdHandlingComments = esdHandlingComments;
    }

    public String getEsdCoordinator() {
        return esdCoordinator;
    }

    public void setEsdCoordinator(String esdCoordinator) {
        this.esdCoordinator = esdCoordinator;
    }

    public Date getEsdHandlingDate() {
        return esdHandlingDate;
    }

    public void setEsdHandlingDate(Date esdHandlingDate) {
        this.esdHandlingDate = esdHandlingDate;
    }

    public String getEsdCoordinatResult() {
        return esdCoordinatResult;
    }

    public void setEsdCoordinatResult(String esdCoordinatResult) {
        this.esdCoordinatResult = esdCoordinatResult;
    }

    public String getEsdCoordinatOpinion() {
        return esdCoordinatOpinion;
    }

    public void setEsdCoordinatOpinion(String esdCoordinatOpinion) {
        this.esdCoordinatOpinion = esdCoordinatOpinion;
    }

    public String getCommitteeAuditor() {
        return committeeAuditor;
    }

    public void setCommitteeAuditor(String committeeAuditor) {
        this.committeeAuditor = committeeAuditor;
    }

    public Date getEsdCoordinatDate() {
        return esdCoordinatDate;
    }

    public void setEsdCoordinatDate(Date esdCoordinatDate) {
        this.esdCoordinatDate = esdCoordinatDate;
    }

    public String getHandlingComments() {
        return handlingComments;
    }

    public void setHandlingComments(String handlingComments) {
        this.handlingComments = handlingComments;
    }

    public String getDepartmentAuditOpinion() {
        return departmentAuditOpinion;
    }

    public void setDepartmentAuditOpinion(String departmentAuditOpinion) {
        this.departmentAuditOpinion = departmentAuditOpinion;
    }

    public Date getDepartmentAuditDate() {
        return departmentAuditDate;
    }

    public void setDepartmentAuditDate(Date departmentAuditDate) {
        this.departmentAuditDate = departmentAuditDate;
    }

    public String getAuditResults() {
        return auditResults;
    }

    public void setAuditResults(String auditResults) {
        this.auditResults = auditResults;
    }

    public String getCommitteeAuditOpinion() {
        return committeeAuditOpinion;
    }

    public void setCommitteeAuditOpinion(String committeeAuditOpinion) {
        this.committeeAuditOpinion = committeeAuditOpinion;
    }

    public Date getCommitteeAuditDate() {
        return committeeAuditDate;
    }

    public void setCommitteeAuditDate(Date committeeAuditDate) {
        this.committeeAuditDate = committeeAuditDate;
    }

    public String getVerificationOfResults() {
        return verificationOfResults;
    }

    public void setVerificationOfResults(String verificationOfResults) {
        this.verificationOfResults = verificationOfResults;
    }

    public String getSpecialProcurementVerifier() {
        return specialProcurementVerifier;
    }

    public void setSpecialProcurementVerifier(String specialProcurementVerifier) {
        this.specialProcurementVerifier = specialProcurementVerifier;
    }

    public Date getSpecialProcurementDate() {
        return specialProcurementDate;
    }

    public void setSpecialProcurementDate(Date specialProcurementDate) {
        this.specialProcurementDate = specialProcurementDate;
    }

    public String getSpecialApplicant() {
        return specialApplicant;
    }

    public void setSpecialApplicant(String specialApplicant) {
        this.specialApplicant = specialApplicant;
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

    public Integer getQualifiedQty() {
        return qualifiedQty;
    }

    public void setQualifiedQty(Integer qualifiedQty) {
        this.qualifiedQty = qualifiedQty;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public List<JSONObject> getAnnex() {
        return annex;
    }

    public void setAnnex(List<JSONObject> annex) {
        this.annex = annex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sampleName=").append(sampleName);
        sb.append(", supplier=").append(supplier);
        sb.append(", model=").append(model);
        sb.append(", materials=").append(materials);
        sb.append(", samplingMethod=").append(samplingMethod);
        sb.append(", samplingRate=").append(samplingRate);
        sb.append(", ambientTemperature=").append(ambientTemperature);
        sb.append(", checker=").append(checker);
        sb.append(", ambientHumidity=").append(ambientHumidity);
        sb.append(", handlingMethod=").append(handlingMethod);
        sb.append(", remarks=").append(remarks);
        sb.append(", status=").append(status);
        sb.append(", treatmentConclusion=").append(treatmentConclusion);
        sb.append(", disposalResults=").append(disposalResults);
        sb.append(", deliveredQty=").append(deliveredQty);
        sb.append(", samplingQty=").append(samplingQty);
        sb.append(", inspectionDate=").append(inspectionDate);
        sb.append(", specificationModel=").append(specificationModel);
        sb.append(", acceptanceRate=").append(acceptanceRate);
        sb.append(", specialMiningNo=").append(specialMiningNo);
        sb.append(", demandDepartment=").append(demandDepartment);
        sb.append(", demandProposer=").append(demandProposer);
        sb.append(", demandDate=").append(demandDate);
        sb.append(", specialMiningMeasures=").append(specialMiningMeasures);
        sb.append(", exceptionDescription=").append(exceptionDescription);
        sb.append(", specialReasons=").append(specialReasons);
        sb.append(", shortTreatmentMeasures=").append(shortTreatmentMeasures);
        sb.append(", longTreatmentMeasures=").append(longTreatmentMeasures);
        sb.append(", departmentTreatmentMeasures=").append(departmentTreatmentMeasures);
        sb.append(", longImprovementMeasures=").append(longImprovementMeasures);
        sb.append(", departmentAuditor=").append(departmentAuditor);
        sb.append(", esdHandler=").append(esdHandler);
        sb.append(", esdHandlingComments=").append(esdHandlingComments);
        sb.append(", esdCoordinator=").append(esdCoordinator);
        sb.append(", esdHandlingDate=").append(esdHandlingDate);
        sb.append(", esdCoordinatResult=").append(esdCoordinatResult);
        sb.append(", esdCoordinatOpinion=").append(esdCoordinatOpinion);
        sb.append(", committeeAuditor=").append(committeeAuditor);
        sb.append(", esdCoordinatDate=").append(esdCoordinatDate);
        sb.append(", handlingComments=").append(handlingComments);
        sb.append(", departmentAuditOpinion=").append(departmentAuditOpinion);
        sb.append(", departmentAuditDate=").append(departmentAuditDate);
        sb.append(", auditResults=").append(auditResults);
        sb.append(", committeeAuditOpinion=").append(committeeAuditOpinion);
        sb.append(", committeeAuditDate=").append(committeeAuditDate);
        sb.append(", verificationOfResults=").append(verificationOfResults);
        sb.append(", specialProcurementVerifier=").append(specialProcurementVerifier);
        sb.append(", specialProcurementDate=").append(specialProcurementDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}