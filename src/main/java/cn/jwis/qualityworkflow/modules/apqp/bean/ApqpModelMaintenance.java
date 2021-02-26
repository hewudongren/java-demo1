package cn.jwis.qualityworkflow.modules.apqp.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ApqpModelMaintenance implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 机型名称
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 上一代产品
     *
     * @mbggenerated
     */
    private String previousProduct;

    /**
     * 产品系列
     *
     * @mbggenerated
     */
    private String productSeries;

    /**
     * 产品类别
     *
     * @mbggenerated
     */
    private String modelCategory;

    /**
     * 制造类型
     *
     * @mbggenerated
     */
    private String manufactureType;

    /**
     * 项目(ODM)
     *
     * @mbggenerated
     */
    private String item;

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

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPreviousProduct() {
        return previousProduct;
    }

    public void setPreviousProduct(String previousProduct) {
        this.previousProduct = previousProduct;
    }

    public String getProductSeries() {
        return productSeries;
    }

    public void setProductSeries(String productSeries) {
        this.productSeries = productSeries;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", model=").append(model);
        sb.append(", previousProduct=").append(previousProduct);
        sb.append(", productSeries=").append(productSeries);
        sb.append(", modelCategory=").append(modelCategory);
        sb.append(", manufactureType=").append(manufactureType);
        sb.append(", item=").append(item);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}