package cn.jwis.qualityworkflow.modules.esd.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EsdSpecialManageInfo implements Serializable {
    /**
     * 唯一主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 单据编号
     */
    private String itemNumber;

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
     * 规格型号
     *
     * @mbggenerated
     */
    private String specificationModel;

    /**
     * 料号
     *
     * @mbggenerated
     */
    private String materials;

    /**
     * 到货数量
     *
     * @mbggenerated
     */
    private Integer deliveredQty;

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
     * 抽样数量
     *
     * @mbggenerated
     */
    private Integer samplingQty;

    /**
     * 环境温度
     *
     * @mbggenerated
     */
    private String ambientTemperature;

    /**
     * 环境湿度
     *
     * @mbggenerated
     */
    private String ambientHumidity;

    /**
     * 检验日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inspectionDate;

    /**
     * 检验人
     *
     * @mbggenerated
     */
    private String checker;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date demandDate;

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
     * 合格数量
     *
     * @mbggenerated
     */
    private Integer qualifiedQty;

    /**
     * 最终结果
     *
     * @mbggenerated
     */
    private String finalResult;

    /**
     * 合格率
     *
     * @mbggenerated
     */
    private Float acceptanceRate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 副表的数据
     */
    List<JSONObject> data;

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

    public Integer getDeliveredQty() {
        return deliveredQty;
    }

    public void setDeliveredQty(Integer deliveredQty) {
        this.deliveredQty = deliveredQty;
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

    public Integer getSamplingQty() {
        return samplingQty;
    }

    public void setSamplingQty(Integer samplingQty) {
        this.samplingQty = samplingQty;
    }

    public String getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(String ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public String getAmbientHumidity() {
        return ambientHumidity;
    }

    public void setAmbientHumidity(String ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
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

    public Float getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(Float acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public String getSpecificationModel() {
        return specificationModel;
    }

    public void setSpecificationModel(String specificationModel) {
        this.specificationModel = specificationModel;
    }

    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
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
        sb.append(", specificationModel=").append(specificationModel);
        sb.append(", materials=").append(materials);
        sb.append(", deliveredQty=").append(deliveredQty);
        sb.append(", samplingMethod=").append(samplingMethod);
        sb.append(", samplingRate=").append(samplingRate);
        sb.append(", samplingQty=").append(samplingQty);
        sb.append(", ambientTemperature=").append(ambientTemperature);
        sb.append(", ambientHumidity=").append(ambientHumidity);
        sb.append(", inspectionDate=").append(inspectionDate);
        sb.append(", checker=").append(checker);
        sb.append(", demandDepartment=").append(demandDepartment);
        sb.append(", demandProposer=").append(demandProposer);
        sb.append(", demandDate=").append(demandDate);
        sb.append(", handlingMethod=").append(handlingMethod);
        sb.append(", remarks=").append(remarks);
        sb.append(", qualifiedQty=").append(qualifiedQty);
        sb.append(", finalResult=").append(finalResult);
        sb.append(", acceptanceRate=").append(acceptanceRate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}