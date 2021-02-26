package cn.jwis.qualityworkflow.bean;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-20 11:16
 * @since 0.1.0
 **/
public class CretaeHistoryProcessBean {
	@ApiModelProperty("内容")
	private JSONObject content;
	@ApiModelProperty("业务Id")
	private String workflowBusinessid;
	@ApiModelProperty("节点")
	private String workflowNode;

	public JSONObject getContent() {
		return content;
	}

	public String getWorkflowBusinessid() {
		return workflowBusinessid;
	}

	public void setWorkflowBusinessid(String workflowBusinessid) {
		this.workflowBusinessid = workflowBusinessid;
	}

	public String getWorkflowNode() {
		return workflowNode;
	}

	public void setWorkflowNode(String workflowNode) {
		this.workflowNode = workflowNode;
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}
}
