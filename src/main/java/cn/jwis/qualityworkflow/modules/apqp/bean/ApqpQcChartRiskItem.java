package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.io.Serializable;
import java.util.Date;

public class ApqpQcChartRiskItem implements Serializable {
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
     * 风险来源
     *
     * @mbggenerated
     */
    private String riskSource;

    /**
     * 产品控制方式
     *
     * @mbggenerated
     */
    private String productControlMode;

    /**
     * 产品过程规范
     *
     * @mbggenerated
     */
    private String productProcessSpecification;

    /**
     * QC_Chart附件
     *
     * @mbggenerated
     */
    private String qcChartAttachment;

    /**
     * QC_Chart附件提交时间
     *
     * @mbggenerated
     */
    private Date qcChartCommitTime;

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

    public String getRiskSource() {
        return riskSource;
    }

    public void setRiskSource(String riskSource) {
        this.riskSource = riskSource;
    }

    public String getProductControlMode() {
        return productControlMode;
    }

    public void setProductControlMode(String productControlMode) {
        this.productControlMode = productControlMode;
    }

    public String getProductProcessSpecification() {
        return productProcessSpecification;
    }

    public void setProductProcessSpecification(String productProcessSpecification) {
        this.productProcessSpecification = productProcessSpecification;
    }

    public String getQcChartAttachment() {
        return qcChartAttachment;
    }

    public void setQcChartAttachment(String qcChartAttachment) {
        this.qcChartAttachment = qcChartAttachment;
    }

    public Date getQcChartCommitTime() {
        return qcChartCommitTime;
    }

    public void setQcChartCommitTime(Date qcChartCommitTime) {
        this.qcChartCommitTime = qcChartCommitTime;
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
        sb.append(", subdocumentId=").append(subdocumentId);
        sb.append(", model=").append(model);
        sb.append(", engPhase=").append(engPhase);
        sb.append(", riskDescription=").append(riskDescription);
        sb.append(", riskSource=").append(riskSource);
        sb.append(", productControlMode=").append(productControlMode);
        sb.append(", productProcessSpecification=").append(productProcessSpecification);
        sb.append(", qcChartAttachment=").append(qcChartAttachment);
        sb.append(", qcChartCommitTime=").append(qcChartCommitTime);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}