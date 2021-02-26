package cn.jwis.qualityworkflow.modules.ipqc.bean;

import java.io.Serializable;
import java.util.List;

public class AuditListInfo implements Serializable {
    private String id;
    /**
     * 稽核日期
     *
     * @mbggenerated
     */
    private String auditDate;

    /**
     * 文件编号
     *
     * @mbggenerated
     */
    private String fileNumber;

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
     * 线体
     *
     * @mbggenerated
     */
    private String line;
    /**
     * 班次
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * 检验员
     *
     * @mbggenerated
     */
    private String inspector;

    /**
     * 时间段
     *
     * @mbggenerated
     */
    private String timeInterval;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private String updatePerson;
    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;
    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;
    private String updateDate;

    private String auditTemplateId;

    private String templateName;

    private String templateNumber;
    private List<AuditListDetail> listDetail;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<AuditListDetail> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<AuditListDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getAuditTemplateId() {
        return auditTemplateId;
    }

    public void setAuditTemplateId(String auditTemplateId) {
        this.auditTemplateId = auditTemplateId;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateName=").append(templateName);
        sb.append(", fileNumber=").append(fileNumber);
        sb.append(", model=").append(model);
        sb.append(", process=").append(process);
        sb.append(", line=").append(line);
        sb.append(", auditDate=").append(auditDate);
        sb.append(", frequency=").append(frequency);
        sb.append(", inspector=").append(inspector);
        sb.append(", timeInterval=").append(timeInterval);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}