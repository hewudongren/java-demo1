package cn.jwis.qualityworkflow.modules.ecn.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-07 13:59
 * @since 0.1.0
 **/
public class QueryExternalDocumentVo extends PageBean{
	// 给父类的属性赋值
	public QueryExternalDocumentVo() {
		super.orderBy = "create_date";
		super.time = "posting_time";
	}

	@ApiModelProperty(" 处理状态 0 是全部  1 是待办，  2是已处理")
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

	@ApiModelProperty("文件类型")
	private List<String> fileType;

	@ApiModelProperty("状态")
	private List<String> status;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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

	public List<String> getFileType() {
		return fileType;
	}

	public void setFileType(List<String> fileType) {
		this.fileType = fileType;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}
}
