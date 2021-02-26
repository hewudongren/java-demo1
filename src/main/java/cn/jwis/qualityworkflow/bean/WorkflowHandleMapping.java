package cn.jwis.qualityworkflow.bean;

import java.io.Serializable;
import java.util.Date;

public class WorkflowHandleMapping implements Serializable {
    private Integer id;

    /**
     * 流程类型
     *
     * @mbggenerated
     */
    private String workflowType;

    /**
     * 流程节点
     *
     * @mbggenerated
     */
    private String workflowNode;

    /**
     * 处理意见对应字段
     *
     * @mbggenerated
     */
    private String handleOpinion;

    /**
     * 驳回原因对应字段
     *
     * @mbggenerated
     */
    private String rejectReason;

    private Date createDate;

    private Date updateDate;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }

    public String getWorkflowNode() {
        return workflowNode;
    }

    public void setWorkflowNode(String workflowNode) {
        this.workflowNode = workflowNode;
    }

    public String getHandleOpinion() {
        return handleOpinion;
    }

    public void setHandleOpinion(String handleOpinion) {
        this.handleOpinion = handleOpinion;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", workflowType=").append(workflowType);
        sb.append(", workflowNode=").append(workflowNode);
        sb.append(", handleOpinion=").append(handleOpinion);
        sb.append(", rejectReason=").append(rejectReason);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", creator=").append(creator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}