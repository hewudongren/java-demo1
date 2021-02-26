package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description ESD稽核模板副表信息
 * @Author yuyangyang
 * @Date 2020/7/1 18:15
 */
public class EsdAuditSecondary {

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

	@ApiModelProperty(value = "createDate", name = "修改日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private  Date createDate;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}