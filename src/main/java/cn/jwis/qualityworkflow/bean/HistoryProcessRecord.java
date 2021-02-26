package cn.jwis.qualityworkflow.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class HistoryProcessRecord implements Serializable {
	/**
	 * 主键
	 *
	 * @mbggenerated
	 */
	private String id;

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
	 * 流程ID
	 *
	 * @mbggenerated
	 */
	private String workflowBusinessid;

	/**
	 * 内容
	 *
	 * @mbggenerated
	 */
	private String content;

	private JSONObject aa;

	public JSONObject getAa() {
		return aa;
	}

	public void setAa(JSONObject aa) {
		this.aa = aa;
	}

	/**
	 * type(保存或提交)
	 *
	 * @mbggenerated
	 */
	private String type;

	/**
	 * 创建人
	 *
	 * @mbggenerated
	 */
	private String creator;

	private Date createDate;

	private Date updateDate;

	private String department;


	private String handleOpinion;


	private String rejectReason;

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getWorkflowBusinessid() {
		return workflowBusinessid;
	}

	public void setWorkflowBusinessid(String workflowBusinessid) {
		this.workflowBusinessid = workflowBusinessid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", workflowType=").append(workflowType);
		sb.append(", workflowNode=").append(workflowNode);
		sb.append(", workflowBusinessid=").append(workflowBusinessid);
		sb.append(", content=").append(content);
		sb.append(", type=").append(type);
		sb.append(", creator=").append(creator);
		sb.append(", createDate=").append(createDate);
		sb.append(", updateDate=").append(updateDate);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}