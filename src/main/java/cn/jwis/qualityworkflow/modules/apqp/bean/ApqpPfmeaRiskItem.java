package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.io.Serializable;
import java.util.Date;

public class ApqpPfmeaRiskItem implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * apqp子单据id
     *
     * @mbggenerated
     */
    private String subdocumentId;

    /**
     * 机型
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 工程段
     *
     * @mbggenerated
     */
    private String engPhase;

    /**
     * 风险描述
     *
     * @mbggenerated
     */
    private String riskDescription;

    /**
     * RPN
     *
     * @mbggenerated
     */
    private String rpn;

    /**
     * 操作步骤
     *
     * @mbggenerated
     */
    private String operationStep;

    /**
     * 潜在失效模式
     *
     * @mbggenerated
     */
    private String potentialFailureMode;

    /**
     * 潜在失效后果
     *
     * @mbggenerated
     */
    private String potentialFailureResult;

    /**
     * 潜在失效起因
     *
     * @mbggenerated
     */
    private String potentialFailureCause;

    /**
     * 严重度
     *
     * @mbggenerated
     */
    private String severity;

    /**
     * 频度 O
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * 可探测度
     *
     * @mbggenerated
     */
    private String detectability;

    /**
     * 现行控制
     *
     * @mbggenerated
     */
    private String currentControl;

    /**
     * PEMEA附件
     *
     * @mbggenerated
     */
    private String pemeaAttachment;

    /**
     * PEMEA附件提交时间
     *
     * @mbggenerated
     */
    private Date pemeaCommitTime;

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
    private Date createDate;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * 风险来源
     *
     * @mbggenerated
     */
    private String riskSource;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubdocumentId() {
        return subdocumentId;
    }

    public void setSubdocumentId(String subdocumentId) {
        this.subdocumentId = subdocumentId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngPhase() {
        return engPhase;
    }

    public void setEngPhase(String engPhase) {
        this.engPhase = engPhase;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public String getRpn() {
        return rpn;
    }

    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

    public String getOperationStep() {
        return operationStep;
    }

    public void setOperationStep(String operationStep) {
        this.operationStep = operationStep;
    }

    public String getPotentialFailureMode() {
        return potentialFailureMode;
    }

    public void setPotentialFailureMode(String potentialFailureMode) {
        this.potentialFailureMode = potentialFailureMode;
    }

    public String getPotentialFailureResult() {
        return potentialFailureResult;
    }

    public void setPotentialFailureResult(String potentialFailureResult) {
        this.potentialFailureResult = potentialFailureResult;
    }

    public String getPotentialFailureCause() {
        return potentialFailureCause;
    }

    public void setPotentialFailureCause(String potentialFailureCause) {
        this.potentialFailureCause = potentialFailureCause;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDetectability() {
        return detectability;
    }

    public void setDetectability(String detectability) {
        this.detectability = detectability;
    }

    public String getCurrentControl() {
        return currentControl;
    }

    public void setCurrentControl(String currentControl) {
        this.currentControl = currentControl;
    }

    public String getPemeaAttachment() {
        return pemeaAttachment;
    }

    public void setPemeaAttachment(String pemeaAttachment) {
        this.pemeaAttachment = pemeaAttachment;
    }

    public Date getPemeaCommitTime() {
        return pemeaCommitTime;
    }

    public void setPemeaCommitTime(Date pemeaCommitTime) {
        this.pemeaCommitTime = pemeaCommitTime;
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

    public String getRiskSource() {
        return riskSource;
    }

    public void setRiskSource(String riskSource) {
        this.riskSource = riskSource;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", subdocumentId=").append(subdocumentId);
        sb.append(", model=").append(model);
        sb.append(", engPhase=").append(engPhase);
        sb.append(", riskDescription=").append(riskDescription);
        sb.append(", rpn=").append(rpn);
        sb.append(", operationStep=").append(operationStep);
        sb.append(", potentialFailureMode=").append(potentialFailureMode);
        sb.append(", potentialFailureResult=").append(potentialFailureResult);
        sb.append(", potentialFailureCause=").append(potentialFailureCause);
        sb.append(", severity=").append(severity);
        sb.append(", frequency=").append(frequency);
        sb.append(", detectability=").append(detectability);
        sb.append(", currentControl=").append(currentControl);
        sb.append(", pemeaAttachment=").append(pemeaAttachment);
        sb.append(", pemeaCommitTime=").append(pemeaCommitTime);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", riskSource=").append(riskSource);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}