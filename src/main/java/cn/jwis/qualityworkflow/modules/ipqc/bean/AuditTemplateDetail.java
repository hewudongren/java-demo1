package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AuditTemplateDetail implements Serializable {
    private String id;

    /**
     * 稽核模板ID
     *
     * @mbggenerated
     */
    private String auditTemplateId;

    /**
     * 稽核类别
     *
     * @mbggenerated
     */
    private String auditType;

    /**
     * 具体要求
     *
     * @mbggenerated
     */
    private String specificRequirement;

    /**
     * 不符合处理意见
     *
     * @mbggenerated
     */
    private String notConformityOpinion;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    /**
     * 时间段
     *
     * @mbggenerated
     */
    private String timeInterval;


    private Boolean save;
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditTemplateId() {
        return auditTemplateId;
    }

    public void setAuditTemplateId(String auditTemplateId) {
        this.auditTemplateId = auditTemplateId;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getSpecificRequirement() {
        return specificRequirement;
    }

    public void setSpecificRequirement(String specificRequirement) {
        this.specificRequirement = specificRequirement;
    }

    public String getNotConformityOpinion() {
        return notConformityOpinion;
    }

    public void setNotConformityOpinion(String notConformityOpinion) {
        this.notConformityOpinion = notConformityOpinion;
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

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Boolean getSave() {
        return save;
    }

    public void setSave(Boolean save) {
        this.save = save;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", auditTemplateId=").append(auditTemplateId);
        sb.append(", auditType=").append(auditType);
        sb.append(", specificRequirement=").append(specificRequirement);
        sb.append(", notConformityOpinion=").append(notConformityOpinion);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}