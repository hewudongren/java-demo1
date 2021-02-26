package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.io.Serializable;
import java.util.Date;

public class ApqpDvt1RiskItem implements Serializable {
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
     * 风险影响
     *
     * @mbggenerated
     */
    private String riskImpact;

    /**
     * 潜在原因
     *
     * @mbggenerated
     */
    private String potentialCause;

    /**
     * 现行控制方式
     *
     * @mbggenerated
     */
    private String currentControlMode;

    /**
     * dvt1附件
     *
     * @mbggenerated
     */
    private String dvt1Attachment;

    /**
     * dvt1附件提交时间
     *
     * @mbggenerated
     */
    private Date dvt1CommitTime;

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

    public String getRiskImpact() {
        return riskImpact;
    }

    public void setRiskImpact(String riskImpact) {
        this.riskImpact = riskImpact;
    }

    public String getPotentialCause() {
        return potentialCause;
    }

    public void setPotentialCause(String potentialCause) {
        this.potentialCause = potentialCause;
    }

    public String getCurrentControlMode() {
        return currentControlMode;
    }

    public void setCurrentControlMode(String currentControlMode) {
        this.currentControlMode = currentControlMode;
    }

    public String getDvt1Attachment() {
        return dvt1Attachment;
    }

    public void setDvt1Attachment(String dvt1Attachment) {
        this.dvt1Attachment = dvt1Attachment;
    }

    public Date getDvt1CommitTime() {
        return dvt1CommitTime;
    }

    public void setDvt1CommitTime(Date dvt1CommitTime) {
        this.dvt1CommitTime = dvt1CommitTime;
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
        sb.append(", riskImpact=").append(riskImpact);
        sb.append(", potentialCause=").append(potentialCause);
        sb.append(", currentControlMode=").append(currentControlMode);
        sb.append(", dvt1Attachment=").append(dvt1Attachment);
        sb.append(", dvt1CommitTime=").append(dvt1CommitTime);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", riskSource=").append(riskSource);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}