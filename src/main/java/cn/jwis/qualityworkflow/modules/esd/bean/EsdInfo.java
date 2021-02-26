package cn.jwis.qualityworkflow.modules.esd.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description ESD检验单信息
 * @Author yuyangyang
 * @Date 2020/5/11 14:17
 */
public class EsdInfo {
	@ApiModelProperty(value = "id", name = "id  唯一主键")
	private String id;
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
	@ApiModelProperty(value = "终端合格率",name = "终端合格率")
	private Float finalAcceptanceRate;
	@ApiModelProperty(value = "不合格处置率",name = "不合格处置率")
	private Float nonconformingDisposalRate;
	@ApiModelProperty(value = "状态",name = "状态")
	private String status;
	@ApiModelProperty(value = "单据编号",name = "单据编号")
	private String itemNumber;

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

	public Float getNonconformingDisposalRate() {
		return nonconformingDisposalRate;
	}

	public void setNonconformingDisposalRate(Float nonconformingDisposalRate) {
		this.nonconformingDisposalRate = nonconformingDisposalRate;
	}

	public Integer getUnqualifiedQty() {
		return unqualifiedQty;
	}

	public void setUnqualifiedQty(Integer unqualifiedQty) {
		this.unqualifiedQty = unqualifiedQty;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
}