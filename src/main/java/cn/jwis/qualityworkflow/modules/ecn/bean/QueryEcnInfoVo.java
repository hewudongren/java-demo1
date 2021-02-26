package cn.jwis.qualityworkflow.modules.ecn.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class QueryEcnInfoVo  extends PageBean{

	// 给父类的属性赋值
	public QueryEcnInfoVo() {
		super.orderBy = "create_date";
		super.time = "posting_time";
	}

	@ApiModelProperty(" 处理状态 0 是全选  1 是未结案，  2是待办")
	private int flag = 1;

	@ApiModelProperty("处理人")
	private String assignee;

	@ApiModelProperty("文件名称")
	private List<String> fileName;

	@ApiModelProperty("发文方")
	private List<String> senderSide;

	@ApiModelProperty("文件发起人")
	private List<String> documentOriginator;

	@ApiModelProperty("机型名称")
	private List<String> modelName;

	@ApiModelProperty("变更类型")
	private List<String> changeType;

	@ApiModelProperty("工程段")
	private List<String> worksSection;


	@ApiModelProperty("工程负责人")
	private List<String> worksHead;

	@ApiModelProperty("质量负责人")
	private List<String> qualityHead;

	@ApiModelProperty("状态")
	private List<String> status;

	//因为mybatis if标签 对于boolean类型不友好 所以用 0代表 false 1代表 true
	@ApiModelProperty("是否为管理员")
	@JsonIgnore
	private int admin = 0;


	public List<String> getWorksSection() {
		return worksSection;
	}

	public void setWorksSection(List<String> worksSection) {
		this.worksSection = worksSection;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int isAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getSenderSide() {
		return senderSide;
	}

	public void setSenderSide(List<String> senderSide) {
		this.senderSide = senderSide;
	}

	public List<String> getDocumentOriginator() {
		return documentOriginator;
	}

	public void setDocumentOriginator(List<String> documentOriginator) {
		this.documentOriginator = documentOriginator;
	}

	public List<String> getModelName() {
		return modelName;
	}

	public void setModelName(List<String> modelName) {
		this.modelName = modelName;
	}

	public List<String> getChangeType() {
		return changeType;
	}

	public void setChangeType(List<String> changeType) {
		this.changeType = changeType;
	}

	public List<String> getWorksHead() {
		return worksHead;
	}

	public void setWorksHead(List<String> worksHead) {
		this.worksHead = worksHead;
	}

	public List<String> getQualityHead() {
		return qualityHead;
	}

	public void setQualityHead(List<String> qualityHead) {
		this.qualityHead = qualityHead;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}
