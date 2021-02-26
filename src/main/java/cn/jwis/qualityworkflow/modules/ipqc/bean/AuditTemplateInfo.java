package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AuditTemplateInfo implements Serializable {
    private String id;

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
    private String process;

    /**
     * 文件编号
     *
     * @mbggenerated
     */
    private String fileNumber;

    /**
     * 模板编号
     *
     * @mbggenerated
     */
    private String templateNumber;

    /**
     * 模板名称
     *
     * @mbggenerated
     */
    private String templateName;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 版本
     *
     * @mbggenerated
     */
    private String version;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private String updatePerson;

    private List<AuditTemplateDetail> templateDetail;

    private String itemNumber;

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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public List<AuditTemplateDetail> getTemplateDetail() {
        return templateDetail;
    }

    public void setTemplateDetail(List<AuditTemplateDetail> templateDetail) {
        this.templateDetail = templateDetail;
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
        sb.append(", model=").append(model);
        sb.append(", process=").append(process);
        sb.append(", fileNumber=").append(fileNumber);
        sb.append(", templateNumber=").append(templateNumber);
        sb.append(", templateName=").append(templateName);
        sb.append(", remark=").append(remark);
        sb.append(", version=").append(version);
        sb.append(", createDate=").append(createDate);
        sb.append(", creator=").append(creator);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}