package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;

public class TraceabilityEnvironmentConfiguration implements Serializable {
    private String id;

    /**
     * 线体
     *
     * @mbggenerated
     */
    private String line;

    /**
     * 探头编号
     *
     * @mbggenerated
     */
    private String probeNumber;

    /**
     * 规格下限值
     *
     * @mbggenerated
     */
    private String specificationFloorValue;

    /**
     * 规格上限值
     *
     * @mbggenerated
     */
    private String specificationUpValue;

    /**
     * 规格中心值
     *
     * @mbggenerated
     */
    private String specificationCentreValue;
    /**
     * 单位
     *
     * @mbggenerated
     */
    private String unit;
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
    private String createDate;
    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;
    /**
     * 参数
     *
     * @mbggenerated
     */
    private String parameter;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getProbeNumber() {
        return probeNumber;
    }

    public void setProbeNumber(String probeNumber) {
        this.probeNumber = probeNumber;
    }

    public String getSpecificationFloorValue() {
        return specificationFloorValue;
    }

    public void setSpecificationFloorValue(String specificationFloorValue) {
        this.specificationFloorValue = specificationFloorValue;
    }

    public String getSpecificationUpValue() {
        return specificationUpValue;
    }

    public void setSpecificationUpValue(String specificationUpValue) {
        this.specificationUpValue = specificationUpValue;
    }

    public String getSpecificationCentreValue() {
        return specificationCentreValue;
    }

    public void setSpecificationCentreValue(String specificationCentreValue) {
        this.specificationCentreValue = specificationCentreValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", line=").append(line);
        sb.append(", probeNumber=").append(probeNumber);
        sb.append(", specificationFloorValue=").append(specificationFloorValue);
        sb.append(", specificationUpValue=").append(specificationUpValue);
        sb.append(", specificationCentreValue=").append(specificationCentreValue);
        sb.append(", unit=").append(unit);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", remark=").append(remark);
        sb.append(", parameter=").append(parameter);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}