package cn.jwis.qualityworkflow.modules.ecn.bean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.Objects;

public class ExternalDocument {
	private String id;

	/**
	 * 项目编号
	 *
	 * @mbggenerated
	 */
	private String itemNumber;

	/**
	 * 项目类型
	 *
	 * @mbggenerated
	 */
	private String itemType = "外来文件";

	/**
	 * 文件名称
	 *
	 * @mbggenerated
	 */
	private String fileName;

	/**
	 * 发文部门
	 *
	 * @mbggenerated
	 */
	private String senderSide;

	/**
	 * 文件发起人
	 *
	 * @mbggenerated
	 */
	private String documentOriginator;

	/**
	 * 机型名称
	 *
	 * @mbggenerated
	 */
	private String modelName;

	/**
	 * 文件类型
	 *
	 * @mbggenerated
	 */
	private String fileType;

	/**
	 * 发文时间
	 *
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date postingTime;

	/**
	 * 工程段
	 *
	 * @mbggenerated
	 */
	private String worksSection;

	/**
	 * 负责人
	 *
	 * @mbggenerated
	 */
	private String inCharge;

	/**
	 * 状态
	 *
	 * @mbggenerated
	 */
	private String status;

	/**
	 * 状态展示名
	 *
	 * @mbggenerated
	 */
	private String statusDisplay;

	/**
	 * 处理人
	 *
	 * @mbggenerated
	 */
	private String assignee;

	/**
	 * 文件处理方式
	 *
	 * @mbggenerated
	 */
//    private String fileProcessingMode;
	private String handleMethod;

	/**
	 * 确认转化后的文件名称
	 *
	 * @mbggenerated
	 */
	private String transmutesFileName;

	/**
	 * 确认转化后的文件名称
	 *
	 * @mbggenerated
	 */
	private String transmutesFileNumber;

	/**
	 * 确认新文件开始使用时间
	 *
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date fileUsageTime;

	/**
	 * IPQC验证结果
	 *
	 * @mbggenerated
	 */
	private String ipqcVerificationResults;

	/**
	 * IPQC验证结果
	 *
	 * @mbggenerated
	 */
	private String ipqcVerificationReason;

	/**
	 * IPQC验证人
	 *
	 * @mbggenerated
	 */
	private String ipqcVerifier;

	/**
	 * IPQC验证时间
	 *
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date ipqcVerificationTime;

	/**
	 * 流程使用时长
	 *
	 * @mbggenerated
	 */
	@JSONField(name = "LT")
	private String LT;

	/**
	 * 创建人
	 *
	 * @mbggenerated
	 */
	private String creator;

	/**
	 * 创建时间
	 *
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	/**
	 * 更新时间
	 *
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateDate;

	// 非持久化属性
	@Transient
	@ApiModelProperty("流程ID")
	private String processInstanceId;

	@Transient
	@ApiModelProperty("任务ID")
	private String taskId;

	@Transient
	@ApiModelProperty("传递给Workflow的全局变量")
	private JSONObject variables;

	@Transient
	@ApiModelProperty("传递给Workflow的局部变量")
	private JSONObject localVariables;

	public JSONObject getLocalVariables() {
		return localVariables;
	}

	public void setLocalVariables(JSONObject localVariables) {
		this.localVariables = localVariables;
	}

	public String getLT() {
		return LT;
	}

	public void setLT(String LT) {
		this.LT = LT;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemType() {
		return itemType;
	}

	public String getWorksSection() {
		return worksSection;
	}

	public void setWorksSection(String worksSection) {
		this.worksSection = worksSection;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSenderSide() {
		return senderSide;
	}

	public void setSenderSide(String senderSide) {
		this.senderSide = senderSide;
	}

	public String getDocumentOriginator() {
		return documentOriginator;
	}

	public void setDocumentOriginator(String documentOriginator) {
		this.documentOriginator = documentOriginator;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Date postingTime) {
		this.postingTime = postingTime;
	}

	public String getInCharge() {
		return inCharge;
	}

	public void setInCharge(String inCharge) {
		this.inCharge = inCharge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

//    public String getFileProcessingMode() {
//        return fileProcessingMode;
//    }
//
//    public void setFileProcessingMode(String fileProcessingMode) {
//        this.fileProcessingMode = fileProcessingMode;
//    }

	public String getTransmutesFileName() {
		return transmutesFileName;
	}

	public void setTransmutesFileName(String transmutesFileName) {
		this.transmutesFileName = transmutesFileName;
	}

	public Date getFileUsageTime() {
		return fileUsageTime;
	}

	public void setFileUsageTime(Date fileUsageTime) {
		this.fileUsageTime = fileUsageTime;
	}

	public String getIpqcVerificationResults() {
		return ipqcVerificationResults;
	}

	public void setIpqcVerificationResults(String ipqcVerificationResults) {
		this.ipqcVerificationResults = ipqcVerificationResults;
	}

	public String getIpqcVerificationReason() {
		return ipqcVerificationReason;
	}

	public void setIpqcVerificationReason(String ipqcVerificationReason) {
		this.ipqcVerificationReason = ipqcVerificationReason;
	}

	public String getIpqcVerifier() {
		return ipqcVerifier;
	}

	public void setIpqcVerifier(String ipqcVerifier) {
		this.ipqcVerifier = ipqcVerifier;
	}

	public Date getIpqcVerificationTime() {
		return ipqcVerificationTime;
	}

	public void setIpqcVerificationTime(Date ipqcVerificationTime) {
		this.ipqcVerificationTime = ipqcVerificationTime;
	}

	public String getHandleMethod() {
		return handleMethod;
	}

	public void setHandleMethod(String handleMethod) {
		this.handleMethod = handleMethod;
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

	public String getStatusDisplay() {
		return statusDisplay;
	}

	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	public String getTransmutesFileNumber() {
		return transmutesFileNumber;
	}

	public void setTransmutesFileNumber(String transmutesFileNumber) {
		this.transmutesFileNumber = transmutesFileNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExternalDocument that = (ExternalDocument) o;
		return Objects.equals(id, that.id) && Objects.equals(itemNumber, that.itemNumber)
				&& Objects.equals(itemType, that.itemType) && Objects.equals(fileName, that.fileName)
				&& Objects.equals(senderSide, that.senderSide)
				&& Objects.equals(documentOriginator, that.documentOriginator)
				&& Objects.equals(modelName, that.modelName) && Objects.equals(fileType, that.fileType)
				&& Objects.equals(postingTime, that.postingTime) && Objects.equals(worksSection, that.worksSection)
				&& Objects.equals(inCharge, that.inCharge) && Objects.equals(status, that.status)
				&& Objects.equals(statusDisplay, that.statusDisplay) && Objects.equals(assignee, that.assignee) &&
//                Objects.equals(fileProcessingMode, that.fileProcessingMode) &&
				Objects.equals(handleMethod, that.handleMethod)
				&& Objects.equals(transmutesFileName, that.transmutesFileName)
				&& Objects.equals(transmutesFileNumber, that.transmutesFileNumber)
				&& Objects.equals(fileUsageTime, that.fileUsageTime)
				&& Objects.equals(ipqcVerificationResults, that.ipqcVerificationResults)
				&& Objects.equals(ipqcVerificationReason, that.ipqcVerificationReason)
				&& Objects.equals(ipqcVerifier, that.ipqcVerifier)
				&& Objects.equals(ipqcVerificationTime, that.ipqcVerificationTime) && Objects.equals(LT, that.LT)
				&& Objects.equals(creator, that.creator) && Objects.equals(createDate, that.createDate)
				&& Objects.equals(updateDate, that.updateDate)
				&& Objects.equals(processInstanceId, that.processInstanceId) && Objects.equals(taskId, that.taskId)
				&& Objects.equals(variables, that.variables) && Objects.equals(localVariables, that.localVariables);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemNumber, itemType, fileName, senderSide, documentOriginator, modelName, fileType,
				postingTime, worksSection, inCharge, status, statusDisplay, assignee, handleMethod, transmutesFileName,
				transmutesFileNumber, fileUsageTime, ipqcVerificationResults, ipqcVerificationReason, ipqcVerifier,
				ipqcVerificationTime, LT, creator, createDate, updateDate, processInstanceId, taskId, variables,
				localVariables);
	}
}