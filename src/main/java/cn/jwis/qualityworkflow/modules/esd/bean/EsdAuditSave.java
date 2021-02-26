package cn.jwis.qualityworkflow.modules.esd.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description ESD稽核模板保存时传数据
 * @Author yuyangyang
 * @Date 2020/7/1 18:20
 */
public class EsdAuditSave {
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

	@ApiModelProperty(value = "data", name = "副表信息")
	private  List<EsdAuditSecondary> data;

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

	public List<EsdAuditSecondary> getData() {
		return data;
	}

	public void setData(List<EsdAuditSecondary> data) {
		this.data = data;
	}
}