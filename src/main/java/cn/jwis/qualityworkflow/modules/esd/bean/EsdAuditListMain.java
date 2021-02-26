package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description ESD稽核单主表实体类
 * @Author yuyangyang
 * @Date 2020/7/2 9:58
 */
public class EsdAuditListMain {

	@ApiModelProperty(value = "id", name = "id  唯一主键")
	private  String id;

	@ApiModelProperty(value = "auditType", name = "稽核类型")
	private  String auditType;

	@ApiModelProperty(value = "documentNumber", name = "文件编号")
	private  String documentNumber;

	@ApiModelProperty(value = "documentName", name = "文件名称")
	private  String documentName;

	@ApiModelProperty(value = "auditDate", name = "稽核日期")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date auditDate;

	@ApiModelProperty(value = "frequency", name = "班次")
	private String frequency;

	@ApiModelProperty(value = "line", name = "区域/线体")
	private String line;

	@ApiModelProperty(value = "auditer", name = "稽核员")
	private String auditer;

	@ApiModelProperty(value = "createDate", name = "创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;

	@ApiModelProperty(value = "auditNumber", name = "稽核数量")
	private Integer auditNumber;

	@ApiModelProperty(value = "qualifiedNumber", name = "合格数量")
	private Integer qualifiedNumber;

	@ApiModelProperty(value = "qualifiedRate", name = "合格率")
	private Float qualifiedRate;

	@ApiModelProperty(value = "remarks", name = "备注")
	private String remarks;

	@ApiModelProperty(value = "updateDate", name = "修改日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateDate;

	@ApiModelProperty(value = "creator", name = "创建人")
	private String creator;

	private Boolean enableHandleCurrentNode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getAuditNumber() {
		return auditNumber;
	}

	public void setAuditNumber(Integer auditNumber) {
		this.auditNumber = auditNumber;
	}

	public Integer getQualifiedNumber() {
		return qualifiedNumber;
	}

	public void setQualifiedNumber(Integer qualifiedNumber) {
		this.qualifiedNumber = qualifiedNumber;
	}

	public Float getQualifiedRate() {
		return qualifiedRate;
	}

	public void setQualifiedRate(Float qualifiedRate) {
		this.qualifiedRate = qualifiedRate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Boolean getEnableHandleCurrentNode() {
		return enableHandleCurrentNode;
	}

	public void setEnableHandleCurrentNode(Boolean enableHandleCurrentNode) {
		this.enableHandleCurrentNode = enableHandleCurrentNode;
	}
}