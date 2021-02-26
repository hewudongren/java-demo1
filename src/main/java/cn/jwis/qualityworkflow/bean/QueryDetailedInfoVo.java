package cn.jwis.qualityworkflow.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询 流程实例详细Vo")
public class QueryDetailedInfoVo {

	@ApiModelProperty("任务Id")
	private String taskId;

	@ApiModelProperty("流程实例Id")
	private String processInstanceId;

	@ApiModelProperty("记录Id")
	private String workflowBusinessId;
	
	private String workflowType;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getWorkflowBusinessId() {
		return workflowBusinessId;
	}

	public void setWorkflowBusinessId(String workflowBusinessId) {
		this.workflowBusinessId = workflowBusinessId;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}
}
