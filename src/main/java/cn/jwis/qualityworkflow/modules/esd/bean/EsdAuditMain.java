package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description ESD稽核模板主表信息
 * @Author yuyangyang
 * @Date 2020/7/1 16:22
 */
public class EsdAuditMain {

	@ApiModelProperty(value = "id", name = "id  唯一主键")
	private  String id;

	@ApiModelProperty(value = "auditType", name = "稽核类型")
	private  String auditType;

	@ApiModelProperty(value = "documentNumber", name = "文件编号")
	private  String documentNumber;

	@ApiModelProperty(value = "documentName", name = "文件名")
	private  String documentName;

	@ApiModelProperty(value = "remark", name = "备注")
	private  String remark;

	@ApiModelProperty(value = "creator", name = "创建人")
	private  String creator;

	@ApiModelProperty(value = "createDate", name = "创建日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;

	@ApiModelProperty(value = "updateDate", name = "修改日期")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private  Date updateDate;

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

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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
}