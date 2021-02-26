package cn.jwis.qualityworkflow.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-11 12:31
 * @since 0.1.0
 **/
public class CreateHistoryProcessVo {
	@ApiModelProperty("内容")
	private JSONObject content;
	@ApiModelProperty("类型 提交 或保存")
	private String type;
	@ApiModelProperty("业务Id")
	private String workflowBusinessid;
	@ApiModelProperty("模板key")
	private String templateKey;
	@ApiModelProperty("节点")
	private String workflowNode;

	@ApiModelProperty("处理意见")
	@JsonIgnore
	private String handleOpinion;

	@ApiModelProperty("驳回原因")
	@JsonIgnore
	private String rejectReason;

	private String department;

	public JSONObject getContent() {
		return content;
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWorkflowBusinessid() {
		return workflowBusinessid;
	}

	public void setWorkflowBusinessid(String workflowBusinessid) {
		this.workflowBusinessid = workflowBusinessid;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getWorkflowNode() {
		return workflowNode;
	}

	public void setWorkflowNode(String workflowNode) {
		this.workflowNode = workflowNode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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
}
