package cn.jwis.qualityworkflow.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 查询历史记录Bean
 * @create 2020-04-30 16:08
 * @since 0.1.0
 **/
public class QueryHistoryProcessRecord {



	@ApiModelProperty("保存类型 保存或提交")
	private String Type;

	@ApiModelProperty("流程类型")
	private String workflowType;

	@ApiModelProperty("流程节点")
	private String workflowNode;


	@ApiModelProperty("流程ID id在新建保存的时候为空")
	private String workflowBusinessid;

	@ApiModelProperty("创建人")
	@NotBlank(message = "创建人不能为空")
	private String creator;

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
