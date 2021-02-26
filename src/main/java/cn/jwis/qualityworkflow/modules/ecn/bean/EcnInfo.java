package cn.jwis.qualityworkflow.modules.ecn.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class EcnInfo  {
    /**
     * 主键
     *
     * @mbggenerated
     */
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
    private String itemType = "ECN";

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
     * 类型
     *
     * @mbggenerated
     */
    private String changeType;

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
     * 工程负责人
     *
     * @mbggenerated
     */
    private String worksHead;

    /**
     * 质量负责人
     *
     * @mbggenerated
     */
    private String qualityHead;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 涉及区域
     *
     * @mbggenerated
     */
    private String involvedArea;

    /**
     * 变更导入方案
     *
     * @mbggenerated
     */
    private String changeImportScheme;

    /**
     * 变更导入时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date changeImportTime;

    /**
     * 变更导入方式
     *
     * @mbggenerated
     */
    private String changeImportMode;

    /**
     * IPQC处理人(PQE处理人)
     *
     * @mbggenerated
     */
    private String ipqcHandler;

    /**
     * IPQC验证结果
     *
     * @mbggenerated
     */
    private String ipqcVerificationResults;

    /**
     * IPQC验证理由
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
     * 工程审批结果
     *
     * @mbggenerated
     */
    private String projectApprovalResult;

    /**
     * 工程审批意见
     *
     * @mbggenerated
     */
    private String projectApprovalOpinion;

    /**
     * 工程审批人
     *
     * @mbggenerated
     */
    private String projectApprover;

    /**
     * 工程审批时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectApproverTime;

    /**
     * ODM审批结果
     *
     * @mbggenerated
     */
    private String odmApprovalResult;

    /**
     * ODM审批意见
     *
     * @mbggenerated
     */
    private String odmApprovalOpinion;

    /**
     * ODM审批人
     *
     * @mbggenerated
     */
    private String odmApprover;

    /**
     * ODM审批时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date odmApproverTime;

    /**
     * SQE审批结果
     *
     * @mbggenerated
     */
    private String sqeApprovalResult;

    /**
     * SQE审批意见
     *
     * @mbggenerated
     */
    private String sqeApprovalOpinion;

    /**
     * SQE审批人
     *
     * @mbggenerated
     */
    private String sqeApprover;

    /**
     * SQE审批时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sqeApproverTime;

    /**
     * 文件地址
     *
     * @mbggenerated
     */
    private String fileAddress;

    /**
     * 文件oid
     *
     * @mbggenerated
     */
    private String fileOid;

    /**
     * 当前处理人
     *
     * @mbggenerated
     */
    private String assignee;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 状态展示名
     */
    private String statusDisplay;
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

    //非持久化属性
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

    @Transient
    @ApiModelProperty("任务状态")
    private String taskState;

    public JSONObject getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(JSONObject localVariables) {
        this.localVariables = localVariables;
    }

    public JSONObject getVariables() {
		return variables;
	}

	public void setVariables(JSONObject variables) {
		this.variables = variables;
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

    public String getFileOid() {
        return fileOid;
    }

    public void setFileOid(String fileOid) {
        this.fileOid = fileOid;
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

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Date getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(Date postingTime) {
        this.postingTime = postingTime;
    }

    public String getWorksSection() {
        return worksSection;
    }

    public void setWorksSection(String worksSection) {
        this.worksSection = worksSection;
    }

    public String getWorksHead() {
        return worksHead;
    }

    public void setWorksHead(String worksHead) {
        this.worksHead = worksHead;
    }

    public String getQualityHead() {
        return qualityHead;
    }

    public void setQualityHead(String qualityHead) {
        this.qualityHead = qualityHead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvolvedArea() {
        return involvedArea;
    }

    public void setInvolvedArea(String involvedArea) {
        this.involvedArea = involvedArea;
    }

    public String getChangeImportScheme() {
        return changeImportScheme;
    }

    public void setChangeImportScheme(String changeImportScheme) {
        this.changeImportScheme = changeImportScheme;
    }

    public Date getChangeImportTime() {
        return changeImportTime;
    }

    public void setChangeImportTime(Date changeImportTime) {
        this.changeImportTime = changeImportTime;
    }

    public String getChangeImportMode() {
        return changeImportMode;
    }

    public void setChangeImportMode(String changeImportMode) {
        this.changeImportMode = changeImportMode;
    }

    public String getIpqcHandler() {
        return ipqcHandler;
    }

    public void setIpqcHandler(String ipqcHandler) {
        this.ipqcHandler = ipqcHandler;
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

    public String getProjectApprovalResult() {
        return projectApprovalResult;
    }

    public void setProjectApprovalResult(String projectApprovalResult) {
        this.projectApprovalResult = projectApprovalResult;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getProjectApprovalOpinion() {
        return projectApprovalOpinion;
    }

    public void setProjectApprovalOpinion(String projectApprovalOpinion) {
        this.projectApprovalOpinion = projectApprovalOpinion;
    }

    public String getProjectApprover() {
        return projectApprover;
    }

    public void setProjectApprover(String projectApprover) {
        this.projectApprover = projectApprover;
    }

    public Date getProjectApproverTime() {
        return projectApproverTime;
    }

    public void setProjectApproverTime(Date projectApproverTime) {
        this.projectApproverTime = projectApproverTime;
    }

    public String getOdmApprovalResult() {
        return odmApprovalResult;
    }

    public void setOdmApprovalResult(String odmApprovalResult) {
        this.odmApprovalResult = odmApprovalResult;
    }

    public String getOdmApprovalOpinion() {
        return odmApprovalOpinion;
    }

    public void setOdmApprovalOpinion(String odmApprovalOpinion) {
        this.odmApprovalOpinion = odmApprovalOpinion;
    }

    public String getOdmApprover() {
        return odmApprover;
    }

    public void setOdmApprover(String odmApprover) {
        this.odmApprover = odmApprover;
    }

    public Date getOdmApproverTime() {
        return odmApproverTime;
    }

    public void setOdmApproverTime(Date odmApproverTime) {
        this.odmApproverTime = odmApproverTime;
    }

    public String getSqeApprovalResult() {
        return sqeApprovalResult;
    }

    public void setSqeApprovalResult(String sqeApprovalResult) {
        this.sqeApprovalResult = sqeApprovalResult;
    }

    public String getSqeApprovalOpinion() {
        return sqeApprovalOpinion;
    }

    public void setSqeApprovalOpinion(String sqeApprovalOpinion) {
        this.sqeApprovalOpinion = sqeApprovalOpinion;
    }

    public String getSqeApprover() {
        return sqeApprover;
    }

    public void setSqeApprover(String sqeApprover) {
        this.sqeApprover = sqeApprover;
    }

    public Date getSqeApproverTime() {
        return sqeApproverTime;
    }

    public void setSqeApproverTime(Date sqeApproverTime) {
        this.sqeApproverTime = sqeApproverTime;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemNumber=").append(itemNumber);
        sb.append(", itemType=").append(itemType);
        sb.append(", fileName=").append(fileName);
        sb.append(", senderSide=").append(senderSide);
        sb.append(", documentOriginator=").append(documentOriginator);
        sb.append(", modelName=").append(modelName);
        sb.append(", changeType=").append(changeType);
        sb.append(", postingTime=").append(postingTime);
        sb.append(", worksSection=").append(worksSection);
        sb.append(", worksHead=").append(worksHead);
        sb.append(", qualityHead=").append(qualityHead);
        sb.append(", status=").append(status);
        sb.append(", involvedArea=").append(involvedArea);
        sb.append(", changeImportScheme=").append(changeImportScheme);
        sb.append(", changeImportTime=").append(changeImportTime);
        sb.append(", changeImportMode=").append(changeImportMode);
        sb.append(", ipqcHandler=").append(ipqcHandler);
        sb.append(", ipqcVerificationResults=").append(ipqcVerificationResults);
        sb.append(", ipqcVerificationReason=").append(ipqcVerificationReason);
        sb.append(", ipqcVerifier=").append(ipqcVerifier);
        sb.append(", ipqcVerificationTime=").append(ipqcVerificationTime);
        sb.append(", projectApprovalResult=").append(projectApprovalResult);
        sb.append(", projectApprovalOpinion=").append(projectApprovalOpinion);
        sb.append(", projectApprover=").append(projectApprover);
        sb.append(", projectApproverTime=").append(projectApproverTime);
        sb.append(", odmApprovalResult=").append(odmApprovalResult);
        sb.append(", odmApprovalOpinion=").append(odmApprovalOpinion);
        sb.append(", odmApprover=").append(odmApprover);
        sb.append(", odmApproverTime=").append(odmApproverTime);
        sb.append(", sqeApprovalResult=").append(sqeApprovalResult);
        sb.append(", sqeApprovalOpinion=").append(sqeApprovalOpinion);
        sb.append(", sqeApprover=").append(sqeApprover);
        sb.append(", sqeApproverTime=").append(sqeApproverTime);
        sb.append(", fileAddress=").append(fileAddress);
        sb.append(", assignee=").append(assignee);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}