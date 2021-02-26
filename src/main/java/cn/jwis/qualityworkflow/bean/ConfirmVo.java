package cn.jwis.qualityworkflow.bean;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 标准的pojo
 * @create 2020-05-08 16:43
 * @since 0.1.0
 **/
public class ConfirmVo {
	@ApiModelProperty("业务ID")
	private String id;

	@ApiModelProperty("流程ID")
	private String processInstanceId;

	@ApiModelProperty("任务ID")
	private String taskId;

	@ApiModelProperty("流程当前状态 流程当前的任务名称")
	private String status;

	@ApiModelProperty("传递给Workflow的变量 业务变量+处理人变量")
	private JSONObject variables;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public JSONObject getVariables() {
		return variables;
	}

	public void setVariables(JSONObject variables) {
		this.variables = variables;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
