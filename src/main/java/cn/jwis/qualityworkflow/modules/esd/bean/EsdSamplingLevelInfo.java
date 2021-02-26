package cn.jwis.qualityworkflow.modules.esd.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EsdSamplingLevelInfo {
	@ApiModelProperty(value = "ID 唯一标识", name = "id")
	private String id;
	@ApiModelProperty(value = "样品名", name = "sampleName")
	private String sampleName;
	@ApiModelProperty(value = "抽样数量", name = "samplingQty")
	private Integer samplingQty;
	@ApiModelProperty(value = "抽样比率", name = "samplingQty")
	private Float samplingRate;
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

	public Integer getSamplingQty() {
		return samplingQty;
	}

	public void setSamplingQty(Integer samplingQty) {
		this.samplingQty = samplingQty;
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

	public Float getSamplingRate() {
		return samplingRate;
	}

	public void setSamplingRate(Float samplingRate) {
		this.samplingRate = samplingRate;
	}
}
