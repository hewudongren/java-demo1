package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 检验标准维护BEAN
 */
public class EsdInStanInfo {
	@ApiModelProperty(value = "id", name = "id")
	private String id;
	@ApiModelProperty(value = "样品名", name = "sampleName")
	private String sampleName;
	@ApiModelProperty(value = "检验项目", name = "detectionItem")
	private String detectionItem;
	@ApiModelProperty(value = "单位", name = "unit")
	private String unit;
	@ApiModelProperty(value = "下限值", name = "lowerLimit")
	private String lowerLimit;
	@ApiModelProperty(value = "上限值", name = "upperLimit")
	private String upperLimit;
	@ApiModelProperty(value = "检验工具", name = "detectionTool")
	private String detectionTool;
	@ApiModelProperty(value = "备注", name = "remark")
	private String remark;
	@ApiModelProperty(value = "开始时间", name = "createDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
	@ApiModelProperty(value = "结束时间", name = "updateDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateDate;
	@ApiModelProperty(value = "创建人", name = "creator")
	private String creator;
	@ApiModelProperty(value = "类型",name = "category")
	private String category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getDetectionItem() {
		return detectionItem;
	}

	public void setDetectionItem(String detectionItem) {
		this.detectionItem = detectionItem;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getDetectionTool() {
		return detectionTool;
	}

	public void setDetectionTool(String detectionTool) {
		this.detectionTool = detectionTool;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
