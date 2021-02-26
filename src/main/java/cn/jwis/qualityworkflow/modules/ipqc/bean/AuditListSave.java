package cn.jwis.qualityworkflow.modules.ipqc.bean;

public class AuditListSave {

    private String id;
    private String status;
    private String creator;
    private String content;
    private String auditListId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuditListId() {
        return auditListId;
    }

    public void setAuditListId(String auditListId) {
        this.auditListId = auditListId;
    }
}
