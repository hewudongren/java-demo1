package cn.jwis.qualityworkflow.modules.esd.bean;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/11 17:40
 */
public class EsdCycleInfo {
	@ApiModelProperty(value = "id", name = "id  唯一主键")
	private String id;
	@ApiModelProperty(value = "itemNumber", name = "单据编号")
	private String itemNumber;
	@ApiModelProperty(value = "样品名", name = "样品名")
	private String sampleName;
	@ApiModelProperty(value = "检验月份", name = "检验月份")
	private String detectionMonth;
	@ApiModelProperty(value = "抽样数",name = "抽样数")
	private Integer samplingQty;
	@ApiModelProperty(value = "检测数",name = "检测数")
	private Integer detectionQty;
	@ApiModelProperty(value = "合格数",name = "合格数")
	private Integer qualifiedQty;
	@ApiModelProperty(value = "不合格数",name = "不合格数")
	private Integer unqualifiedQty;
	@ApiModelProperty(value = "合格率",name = "合格率")
	private Float acceptanceRate;
	@ApiModelProperty(value = "不合格处置率",name = "不合格处置率")
	private Float nonconformingDisposalRate;
	@ApiModelProperty(value = "终端合格率",name = "终端合格率")
	private Float finalAcceptanceRate;
	@ApiModelProperty(value = "状态",name = "状态")
	private String status;
	@ApiModelProperty(value = "创建人",name = "创建人")
	private String creator;
    @ApiModelProperty(value = "创建日期",name = "创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;
	@ApiModelProperty(value = "修改日期",name = "修改日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateDate;
	@ApiModelProperty(value = "原因分析人部门",name = "原因分析人部门")
	private String causalAnalystDepartment;
	@ApiModelProperty(value = "原因分析人",name = "原因分析人")
	private String causalAnalyst;
	@ApiModelProperty(value = "原因分析",name = "原因分析")
	private String causeAnalysis;
	@ApiModelProperty(value = "原因分析时间",name = "原因分析时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date  causeAnalysisDate;
	@ApiModelProperty(value = "改善措施",name = "改善措施")
	private String improvementMeasures;
	@ApiModelProperty(value = "改善措施制定人",name = "改善措施制定人")
	private String improvementMeasuresor;
    @ApiModelProperty(value = "改善措施制定时间",name = "改善措施制定时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private  Date improvementMeasuresDate;
    @ApiModelProperty(value = "效果验证",name = "效果验证")
	private  String effectVerification;
    @ApiModelProperty(value ="验证人",name = "验证人")
	private String verifiedor;
    @ApiModelProperty(value = "验证时间",name = "验证时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date verifiedDate;
    @ApiModelProperty(value = "审批结果",name = "审批结果")
	private String approvalResult;
	@ApiModelProperty(value = "结案审批人",name = "结案审批人")
	private String caseClosingApprover;
	@ApiModelProperty(value = "结案审批时间",name = "结案审批时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date caseClosingDate;

	private String workflowBusinessId;
	private String processInstanceId;
	private String taskId;
	private String assignee;
	private String taskState;

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

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getDetectionMonth() {
		return detectionMonth;
	}

	public void setDetectionMonth(String detectionMonth) {
		this.detectionMonth = detectionMonth;
	}

	public Integer getSamplingQty() {
		return samplingQty;
	}

	public void setSamplingQty(Integer samplingQty) {
		this.samplingQty = samplingQty;
	}

	public Integer getDetectionQty() {
		return detectionQty;
	}

	public void setDetectionQty(Integer detectionQty) {
		this.detectionQty = detectionQty;
	}

	public Integer getQualifiedQty() {
		return qualifiedQty;
	}

	public void setQualifiedQty(Integer qualifiedQty) {
		this.qualifiedQty = qualifiedQty;
	}

	public Float getAcceptanceRate() {
		return acceptanceRate;
	}

	public void setAcceptanceRate(Float acceptanceRate) {
		this.acceptanceRate = acceptanceRate;
	}

	public Float getFinalAcceptanceRate() {
		return finalAcceptanceRate;
	}

	public void setFinalAcceptanceRate(Float finalAcceptanceRate) {
		this.finalAcceptanceRate = finalAcceptanceRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCausalAnalyst() {
		return causalAnalyst;
	}

	public void setCausalAnalyst(String causalAnalyst) {
		this.causalAnalyst = causalAnalyst;
	}

	public String getCauseAnalysis() {
		return causeAnalysis;
	}

	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}

	public Date getCauseAnalysisDate() {
		return causeAnalysisDate;
	}

	public void setCauseAnalysisDate(Date causeAnalysisDate) {
		this.causeAnalysisDate = causeAnalysisDate;
	}

	public String getImprovementMeasures() {
		return improvementMeasures;
	}

	public void setImprovementMeasures(String improvementMeasures) {
		this.improvementMeasures = improvementMeasures;
	}

	public String getImprovementMeasuresor() {
		return improvementMeasuresor;
	}

	public void setImprovementMeasuresor(String improvementMeasuresor) {
		this.improvementMeasuresor = improvementMeasuresor;
	}

	public Date getImprovementMeasuresDate() {
		return improvementMeasuresDate;
	}

	public void setImprovementMeasuresDate(Date improvementMeasuresDate) {
		this.improvementMeasuresDate = improvementMeasuresDate;
	}

	public String getEffectVerification() {
		return effectVerification;
	}

	public void setEffectVerification(String effectVerification) {
		this.effectVerification = effectVerification;
	}

	public String getVerifiedor() {
		return verifiedor;
	}

	public void setVerifiedor(String verifiedor) {
		this.verifiedor = verifiedor;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getCaseClosingApprover() {
		return caseClosingApprover;
	}

	public void setCaseClosingApprover(String caseClosingApprover) {
		this.caseClosingApprover = caseClosingApprover;
	}

	public Date getCaseClosingDate() {
		return caseClosingDate;
	}

	public void setCaseClosingDate(Date caseClosingDate) {
		this.caseClosingDate = caseClosingDate;
	}

	public String getWorkflowBusinessId() {
		return workflowBusinessId;
	}

	public void setWorkflowBusinessId(String workflowBusinessId) {
		this.workflowBusinessId = workflowBusinessId;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public Integer getUnqualifiedQty() {
		return unqualifiedQty;
	}

	public void setUnqualifiedQty(Integer unqualifiedQty) {
		this.unqualifiedQty = unqualifiedQty;
	}

	public Float getNonconformingDisposalRate() {
		return nonconformingDisposalRate;
	}

	public void setNonconformingDisposalRate(Float nonconformingDisposalRate) {
		this.nonconformingDisposalRate = nonconformingDisposalRate;
	}

	public String getCausalAnalystDepartment() {
		return causalAnalystDepartment;
	}

	public void setCausalAnalystDepartment(String causalAnalystDepartment) {
		this.causalAnalystDepartment = causalAnalystDepartment;
	}
}