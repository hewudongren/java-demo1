package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description ESD稽核单据副表信息
 * @Author yuyangyang
 * @Date 2020/7/2 16:29
 */
public class EsdAuditListSecondary {

	@ApiModelProperty(value = "id", name = "ID唯一主键")
	private  String id;

	@ApiModelProperty(value = "auditObject", name = "稽核对象")
	private  String auditObject;

	@ApiModelProperty(value = "specificRequirement", name = "具体要求")
	private  String specificRequirement;

	@ApiModelProperty(value = "problemClassification", name = "问题分类")
	private  String problemClassification;

	@ApiModelProperty(value = "gradeProblem", name = "问题等级")
	private  String gradeProblem;

	@ApiModelProperty(value = "hsfnc", name = "不符合处理意见")
	private  String hsfnc;

	@ApiModelProperty(value = "mainId", name = "主表ID")
	private  String mainId;

	@ApiModelProperty(value = "inspectionResults", name = "检查结果")
	private  String inspectionResults;

	@ApiModelProperty(value = "responsibleDepartment", name = "责任部门")
	private  String responsibleDepartment;

	@ApiModelProperty(value = "personLiable", name = "责任人")
	private  String personLiable;

	@ApiModelProperty(value = "problemAttributes", name = "问题属性")
	private  String problemAttributes;

	@ApiModelProperty(value = "problemDescription",name = "问题描述")
	private  String problemDescription;

	@ApiModelProperty(value = "finalResult",name = "最终结果")
	private  String finalResult;

	@ApiModelProperty(value = "warranty",name = "是否保修")
	private  String warranty;

	@ApiModelProperty(value = "createDate", name = "创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;

	@ApiModelProperty(value = "updateDate", name = "修改日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateDate;

	private String creator;
	private String remarks;
	private String flag;
	private String billsFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuditObject() {
		return auditObject;
	}

	public void setAuditObject(String auditObject) {
		this.auditObject = auditObject;
	}

	public String getSpecificRequirement() {
		return specificRequirement;
	}

	public void setSpecificRequirement(String specificRequirement) {
		this.specificRequirement = specificRequirement;
	}

	public String getProblemClassification() {
		return problemClassification;
	}

	public void setProblemClassification(String problemClassification) {
		this.problemClassification = problemClassification;
	}

	public String getGradeProblem() {
		return gradeProblem;
	}

	public void setGradeProblem(String gradeProblem) {
		this.gradeProblem = gradeProblem;
	}

	public String getHsfnc() {
		return hsfnc;
	}

	public void setHsfnc(String hsfnc) {
		this.hsfnc = hsfnc;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getInspectionResults() {
		return inspectionResults;
	}

	public void setInspectionResults(String inspectionResults) {
		this.inspectionResults = inspectionResults;
	}

	public String getResponsibleDepartment() {
		return responsibleDepartment;
	}

	public void setResponsibleDepartment(String responsibleDepartment) {
		this.responsibleDepartment = responsibleDepartment;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getProblemAttributes() {
		return problemAttributes;
	}

	public void setProblemAttributes(String problemAttributes) {
		this.problemAttributes = problemAttributes;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBillsFlag() {
		return billsFlag;
	}

	public void setBillsFlag(String billsFlag) {
		this.billsFlag = billsFlag;
	}
}