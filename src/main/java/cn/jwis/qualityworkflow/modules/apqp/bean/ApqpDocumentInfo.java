package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.Date;

public class ApqpDocumentInfo {
    private String id;

    /**
     * 文件所属流程阶段
     *
     * @mbggenerated
     */
    private String documentNode;

    /**
     * 子单据id
     *
     * @mbggenerated
     */
    private String subdocumentId;

    /**
     * 文件
     *
     * @mbggenerated
     */
    private String document;

    /**
     * 文件oid
     *
     * @mbggenerated
     */
    private String documentOid;

    /**
     * 工程段
     *
     * @mbggenerated
     */
    private String engPhase;


    private String documentName;

    /**
     * 截止时间（超期判断依据）
     *
     * @mbggenerated
     */
    private Date deadline;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 提交时间
     *
     * @mbggenerated
     */
    private Date summitTime;

    /**
     * 审核时间
     *
     * @mbggenerated
     */
    private Date auditTime;

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


    public ApqpDocumentInfo() {
    }

    public ApqpDocumentInfo(UpdateDocumentInfoVo vo) {
        this.id = vo.getId();
        this.documentName = vo.getDocumentName();
        this.documentOid = vo.getDocumentOid();
        this.summitTime = vo.getSummitTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentNode() {
        return documentNode;
    }

    public void setDocumentNode(String documentNode) {
        this.documentNode = documentNode;
    }

    public String getSubdocumentId() {
        return subdocumentId;
    }

    public void setSubdocumentId(String subdocumentId) {
        this.subdocumentId = subdocumentId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentOid() {
        return documentOid;
    }

    public void setDocumentOid(String documentOid) {
        this.documentOid = documentOid;
    }

    public String getEngPhase() {
        return engPhase;
    }

    public void setEngPhase(String engPhase) {
        this.engPhase = engPhase;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSummitTime() {
        return summitTime;
    }

    public void setSummitTime(Date summitTime) {
        this.summitTime = summitTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", documentNode=").append(documentNode);
        sb.append(", subdocumentId=").append(subdocumentId);
        sb.append(", document=").append(document);
        sb.append(", documentOid=").append(documentOid);
        sb.append(", documentName=").append(documentName);
        sb.append(", engPhase=").append(engPhase);
        sb.append(", deadline=").append(deadline);
        sb.append(", status=").append(status);
        sb.append(", summitTime=").append(summitTime);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}