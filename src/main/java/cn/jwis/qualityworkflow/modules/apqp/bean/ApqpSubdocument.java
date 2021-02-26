package cn.jwis.qualityworkflow.modules.apqp.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class ApqpSubdocument  {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 主单据Id
     *
     * @mbggenerated
     */
    private String masterId;


    /**
     * APQP编号
     *
     * @mbggenerated
     */
    private String apqpNumber;

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
     * dvt1和pemea处理人
     *
     * @mbggenerated
     */
    private String owner;

    /**
     * QC_Chart处理人
     *
     * @mbggenerated
     */
    private String qe;

    /**
     * 频率
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * NUDD/FX文档
     *
     * @mbggenerated
     */
    private String dvt1Document;

    /**
     * NUDD/FX文档上传人
     *
     * @mbggenerated
     */
    private String dvt1Uploader;

    /**
     * NUDD/FX文档上传时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dvt1UploadTime;

    /**
     * NUDD/FX文档审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dvt1AuditTime;

    /**
     * NUDD/FX文档审核人
     *
     * @mbggenerated
     */
    private String dvt1Auditor;

    /**
     * NUDD/FX文档预警时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dvt1AlarmDate;

    /**
     * NUDD/FX文档截止日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dvt1Deadline;

    /**
     * PEMEA文档
     *
     * @mbggenerated
     */
    private String pemeaDocument;

    /**
     * PEMEA文档上传人
     *
     * @mbggenerated
     */
    private String pemeaUploader;

    /**
     * PEMEA文档上传时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pemeaUploadTime;

    /**
     * PEMEA文档审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pemeaAuditTime;

    /**
     * PEMEA文档审核人
     *
     * @mbggenerated
     */
    private String pemeaAuditor;

    /**
     * PEMEA预警日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pemeaAlarmDate;

    /**
     * PEMEA截止日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pemeaDeadline;

    /**
     * QC_Char文档
     *
     * @mbggenerated
     */
    private String qcChartDocument;


    /**
     * QC_Char文档上传人
     *
     * @mbggenerated
     */
    private String qcChartUploader;

    /**
     * QC_Char文档上传时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qcChartUploadTime;

    /**
     * QC_Char文档审核时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qcChartAuditTime;

    /**
     * QC_Char文档审核人
     *
     * @mbggenerated
     */
    private String qcChartAuditor;

    /**
     * QC_Char文档预警时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qcChartAlarmDate;

    /**
     * QC_Char文档截止日期
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qcChartDeadline;

    /**
     * NQE结案审批人
     *
     * @mbggenerated
     */
    private String nqeAuditor;

    /**
     * NQE结案时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date nqeAuditTime;


    private String statusDisplay;

    /**
     * 当前处理人
     *
     * @mbggenerated
     */
    private String assignee;

    /**
     * 工程段
     *
     * @mbggenerated
     */
    private String engPhase;

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


    //非持久化属性
    @Transient
    @ApiModelProperty("流程ID")
    private String processInstanceId;

    @Transient
    @ApiModelProperty("任务ID")
    private String taskId;

    @Transient
    @ApiModelProperty("传递给Workflow的全局变量")
    private JSONObject variables;


    @Transient
    @ApiModelProperty("传递给Workflow的局部变量")
    private JSONObject localVariables;

    public ApqpSubdocument(){}

    public ApqpSubdocument(CreateSubDocumentVo vo) {
        this.owner = vo.getOwner();
        this.qe = vo.getQe();
        this.frequency = vo.getFrequency();
        this.dvt1Document = vo.getDvt1Document();
        this.dvt1AlarmDate = vo.getDvt1AlarmDate();
        this.dvt1Deadline = vo.getDvt1Deadline();
        this.pemeaDocument = vo.getPemeaDocument();
        this.pemeaAlarmDate = vo.getPemeaAlarmDate();
        this.pemeaDeadline = vo.getPemeaDeadline();
        this.qcChartDocument = vo.getQcChartDocument();
        this.qcChartAlarmDate = vo.getQcChartAlarmDate();
        this.qcChartDeadline = vo.getQcChartDeadline();
        this.engPhase = vo.getEngPhase();
    }


    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
    
    public String getEngPhase() {
        return engPhase;
    }

    public void setEngPhase(String engPhase) {
        this.engPhase = engPhase;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getQe() {
        return qe;
    }

    public void setQe(String qe) {
        this.qe = qe;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    public String getDvt1Uploader() {
        return dvt1Uploader;
    }

    public void setDvt1Uploader(String dvt1Uploader) {
        this.dvt1Uploader = dvt1Uploader;
    }

    public Date getDvt1UploadTime() {
        return dvt1UploadTime;
    }

    public void setDvt1UploadTime(Date dvt1UploadTime) {
        this.dvt1UploadTime = dvt1UploadTime;
    }

    public Date getDvt1AuditTime() {
        return dvt1AuditTime;
    }

    public void setDvt1AuditTime(Date dvt1AuditTime) {
        this.dvt1AuditTime = dvt1AuditTime;
    }

    public String getDvt1Auditor() {
        return dvt1Auditor;
    }

    public void setDvt1Auditor(String dvt1Auditor) {
        this.dvt1Auditor = dvt1Auditor;
    }

    public Date getDvt1AlarmDate() {
        return dvt1AlarmDate;
    }

    public void setDvt1AlarmDate(Date dvt1AlarmDate) {
        this.dvt1AlarmDate = dvt1AlarmDate;
    }

    public Date getDvt1Deadline() {
        return dvt1Deadline;
    }

    public void setDvt1Deadline(Date dvt1Deadline) {
        this.dvt1Deadline = dvt1Deadline;
    }


    public String getPemeaUploader() {
        return pemeaUploader;
    }

    public void setPemeaUploader(String pemeaUploader) {
        this.pemeaUploader = pemeaUploader;
    }

    public Date getPemeaUploadTime() {
        return pemeaUploadTime;
    }

    public void setPemeaUploadTime(Date pemeaUploadTime) {
        this.pemeaUploadTime = pemeaUploadTime;
    }

    public Date getPemeaAuditTime() {
        return pemeaAuditTime;
    }

    public void setPemeaAuditTime(Date pemeaAuditTime) {
        this.pemeaAuditTime = pemeaAuditTime;
    }

    public String getPemeaAuditor() {
        return pemeaAuditor;
    }

    public void setPemeaAuditor(String pemeaAuditor) {
        this.pemeaAuditor = pemeaAuditor;
    }

    public Date getPemeaAlarmDate() {
        return pemeaAlarmDate;
    }

    public void setPemeaAlarmDate(Date pemeaAlarmDate) {
        this.pemeaAlarmDate = pemeaAlarmDate;
    }

    public Date getPemeaDeadline() {
        return pemeaDeadline;
    }

    public void setPemeaDeadline(Date pemeaDeadline) {
        this.pemeaDeadline = pemeaDeadline;
    }

    public String getDvt1Document() {
        return dvt1Document;
    }

    public void setDvt1Document(String dvt1Document) {
        this.dvt1Document = dvt1Document;
    }

    public String getPemeaDocument() {
        return pemeaDocument;
    }

    public void setPemeaDocument(String pemeaDocument) {
        this.pemeaDocument = pemeaDocument;
    }

    public String getQcChartDocument() {
        return qcChartDocument;
    }

    public void setQcChartDocument(String qcChartDocument) {
        this.qcChartDocument = qcChartDocument;
    }

    public String getQcChartUploader() {
        return qcChartUploader;
    }

    public void setQcChartUploader(String qcChartUploader) {
        this.qcChartUploader = qcChartUploader;
    }

    public Date getQcChartUploadTime() {
        return qcChartUploadTime;
    }

    public void setQcChartUploadTime(Date qcChartUploadTime) {
        this.qcChartUploadTime = qcChartUploadTime;
    }

    public Date getQcChartAuditTime() {
        return qcChartAuditTime;
    }

    public void setQcChartAuditTime(Date qcChartAuditTime) {
        this.qcChartAuditTime = qcChartAuditTime;
    }

    public String getQcChartAuditor() {
        return qcChartAuditor;
    }

    public void setQcChartAuditor(String qcChartAuditor) {
        this.qcChartAuditor = qcChartAuditor;
    }

    public Date getQcChartAlarmDate() {
        return qcChartAlarmDate;
    }

    public void setQcChartAlarmDate(Date qcChartAlarmDate) {
        this.qcChartAlarmDate = qcChartAlarmDate;
    }

    public Date getQcChartDeadline() {
        return qcChartDeadline;
    }

    public void setQcChartDeadline(Date qcChartDeadline) {
        this.qcChartDeadline = qcChartDeadline;
    }

    public String getNqeAuditor() {
        return nqeAuditor;
    }

    public void setNqeAuditor(String nqeAuditor) {
        this.nqeAuditor = nqeAuditor;
    }

    public Date getNqeAuditTime() {
        return nqeAuditTime;
    }

    public void setNqeAuditTime(Date nqeAuditTime) {
        this.nqeAuditTime = nqeAuditTime;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    public JSONObject getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(JSONObject localVariables) {
        this.localVariables = localVariables;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", masterId=").append(masterId);
        sb.append(", apqpNumber=").append(apqpNumber);
        sb.append(", modelCategory=").append(modelCategory);
        sb.append(", manufactureType=").append(manufactureType);
        sb.append(", item=").append(item);
        sb.append(", model=").append(model);
        sb.append(", previousProduct=").append(previousProduct);
        sb.append(", productSeries=").append(productSeries);
        sb.append(", status=").append(status);
        sb.append(", owner=").append(owner);
        sb.append(", qe=").append(qe);
        sb.append(", frequency=").append(frequency);
        sb.append(", dvt1Document=").append(dvt1Document);
        sb.append(", dvt1Uploader=").append(dvt1Uploader);
        sb.append(", dvt1UploadTime=").append(dvt1UploadTime);
        sb.append(", dvt1AuditTime=").append(dvt1AuditTime);
        sb.append(", dvt1Auditor=").append(dvt1Auditor);
        sb.append(", dvt1AlarmDate=").append(dvt1AlarmDate);
        sb.append(", dvt1Deadline=").append(dvt1Deadline);
        sb.append(", pemeaDocument=").append(pemeaDocument);
        sb.append(", pemeaUploader=").append(pemeaUploader);
        sb.append(", pemeaUploadTime=").append(pemeaUploadTime);
        sb.append(", pemeaAuditTime=").append(pemeaAuditTime);
        sb.append(", pemeaAuditor=").append(pemeaAuditor);
        sb.append(", pemeaAlarmDate=").append(pemeaAlarmDate);
        sb.append(", pemeaDeadline=").append(pemeaDeadline);
        sb.append(", qcChartDocument=").append(qcChartDocument);
        sb.append(", qcChartUploader=").append(qcChartUploader);
        sb.append(", qcChartUploadTime=").append(qcChartUploadTime);
        sb.append(", qcChartAuditTime=").append(qcChartAuditTime);
        sb.append(", qcChartAuditor=").append(qcChartAuditor);
        sb.append(", qcChartAlarmDate=").append(qcChartAlarmDate);
        sb.append(", qcChartDeadline=").append(qcChartDeadline);
        sb.append(", nqeAuditor=").append(nqeAuditor);
        sb.append(", nqeAuditTime=").append(nqeAuditTime);
        sb.append(", assignee=").append(assignee);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}