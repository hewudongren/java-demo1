package cn.jwis.qualityworkflow.modules.apqp.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ApqpMasterDocument  {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * APQP编号
     *
     * @mbggenerated
     */
    private String apqpNumber;

    /**
     * 主题
     *
     * @mbggenerated
     */
    private String theme;

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
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 需要完成NUDD/FX文档数量
     */
    private int dvt1Num;

    /**
     * 已经完成NUDD/FX文档数量
     */
    private int dvt1CompletedNum;

    /**
     * 需要完成PFMEA文件数量
     */
    private int pemeaNum;

    /**
     * 已经完成PFMEA文件数量
     */
    private int pemeaCompletedNum;

    /**
     * 需要完成QC-Chart文件数量
     */
    private int qcCharNum;

    /**
     * 已经完成QC-Chart文件数量
     */
    private int qcCharCompletedNum;


    /**
     * NUDD/FX文档进度
     */
    private String dvt1Schedule;

    /**
     * PFMEA文件进度
     */
    private String pemeaSchedule;

    /**
     * QC_Chart文件进度
     */
    private String qcChartSchedule;

    /**
     * QC_Chart文件进度
     */
    private String statusDisplay;

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

    public ApqpMasterDocument(){}

    public ApqpMasterDocument(CreateMasterDocument vo) {
        this.theme = vo.getTheme();
        this.modelCategory = vo.getModelCategory();
        this.manufactureType = vo.getManufactureType();
        this.item = vo.getItem();
        this.model = vo.getModel();
        this.previousProduct = vo.getPreviousProduct();
        this.productSeries = vo.getProductSeries();
    }

    public int getDvt1Num() {
        return dvt1Num;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApqpNumber() {
        return apqpNumber;
    }

    public void setApqpNumber(String apqpNumber) {
        this.apqpNumber = apqpNumber;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDvt1Num(int dvt1Num) {
        this.dvt1Num = dvt1Num;
    }

    public int getDvt1CompletedNum() {
        return dvt1CompletedNum;
    }

    public void setDvt1CompletedNum(int dvt1CompletedNum) {
        this.dvt1CompletedNum = dvt1CompletedNum;
    }

    public int getPemeaNum() {
        return pemeaNum;
    }

    public void setPemeaNum(int pemeaNum) {
        this.pemeaNum = pemeaNum;
    }

    public int getPemeaCompletedNum() {
        return pemeaCompletedNum;
    }

    public void setPemeaCompletedNum(int pemeaCompletedNum) {
        this.pemeaCompletedNum = pemeaCompletedNum;
    }

    public int getQcCharNum() {
        return qcCharNum;
    }

    public void setQcCharNum(int qcCharNum) {
        this.qcCharNum = qcCharNum;
    }

    public int getQcCharCompletedNum() {
        return qcCharCompletedNum;
    }

    public void setQcCharCompletedNum(int qcCharCompletedNum) {
        this.qcCharCompletedNum = qcCharCompletedNum;
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

    public String getDvt1Schedule() {
        return dvt1Schedule;
    }

    public void setDvt1Schedule(String dvt1Schedule) {
        this.dvt1Schedule = dvt1Schedule;
    }

    public String getPemeaSchedule() {
        return pemeaSchedule;
    }

    public void setPemeaSchedule(String pemeaSchedule) {
        this.pemeaSchedule = pemeaSchedule;
    }

    public String getQcChartSchedule() {
        return qcChartSchedule;
    }

    public void setQcChartSchedule(String qcChartSchedule) {
        this.qcChartSchedule = qcChartSchedule;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", apqpNumber=").append(apqpNumber);
        sb.append(", theme=").append(theme);
        sb.append(", modelCategory=").append(modelCategory);
        sb.append(", manufactureType=").append(manufactureType);
        sb.append(", item=").append(item);
        sb.append(", model=").append(model);
        sb.append(", previousProduct=").append(previousProduct);
        sb.append(", productSeries=").append(productSeries);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}