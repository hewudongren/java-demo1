package cn.jwis.qualityworkflow.bean;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-07-27 14:15
 * @since 0.1.0
 **/
public class EmailInfo {

	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("表单类型")
	private String type;

	@ApiModelProperty("单据号")
	private String itemNumber;

	@ApiModelProperty("当前节点")
	private String currentNode;

	@ApiModelProperty("创建人")
	private String creator;

	@ApiModelProperty("邮件处理人")
	private String handler;

	@ApiModelProperty("是否被驳回 true 表示是 false表示 否")
	private boolean reject = false;

	@ApiModelProperty("处理连接")
	private String handleLink;

	@ApiModelProperty("当前节点处理时间")
	private String handleTime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public boolean isReject() {
		return reject;
	}

	public void setReject(boolean reject) {
		this.reject = reject;
	}

	public String getHandleLink() {
		return handleLink;
	}

	public void setHandleLink(String handleLink) {
		this.handleLink = handleLink;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EmailInfo emailInfo = (EmailInfo) o;
		return reject == emailInfo.reject &&
				email.equals(emailInfo.email) &&
				type.equals(emailInfo.type) &&
				itemNumber.equals(emailInfo.itemNumber) &&
				currentNode.equals(emailInfo.currentNode) &&
				creator.equals(emailInfo.creator) &&
				handler.equals(emailInfo.handler) &&
				handleLink.equals(emailInfo.handleLink) &&
				handleTime.equals(emailInfo.handleTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, type, itemNumber, currentNode, creator, handler, reject, handleLink, handleTime);
	}
}
