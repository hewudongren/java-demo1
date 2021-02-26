package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class AuditListDetail implements Serializable {
    private String id;

    /**
     * 稽核清单ID
     *
     * @mbggenerated
     */
    private String auditListId;

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
    private String createDate;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateDate;

    private String content;

    private JSONObject cont;

    private String auditListDetailId;

    private String auditTemplateDetailId;
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditListId() {
        return auditListId;
    }

    public void setAuditListId(String auditListId) {
        this.auditListId = auditListId;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JSONObject getCont() {
        return cont;
    }

    public void setCont(JSONObject cont) {
        this.cont = cont;
    }

    public String getAuditListDetailId() {
        return auditListDetailId;
    }

    public void setAuditListDetailId(String auditListDetailId) {
        this.auditListDetailId = auditListDetailId;
    }

    public String getAuditTemplateDetailId() {
        return auditTemplateDetailId;
    }

    public void setAuditTemplateDetailId(String auditTemplateDetailId) {
        this.auditTemplateDetailId = auditTemplateDetailId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", auditListId=").append(auditListId);
        sb.append(", auditType=").append(auditType);
        sb.append(", specificRequirement=").append(specificRequirement);
        sb.append(", notConformityOpinion=").append(notConformityOpinion);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}