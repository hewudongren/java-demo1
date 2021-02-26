package cn.jwis.qualityworkflow.modules.apqp.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-29 17:56
 * @since 0.1.0
 **/
public class CreateDvt1RiskItem {

	@ApiModelProperty("子单据id")
	private String subdocumentId;

	@ApiModelProperty("工程段")
	@NotBlank(message = "工程段不能为空")
	private String engPhase;


	@ApiModelProperty("机型")
	@NotBlank(message = "机型不能为空")
	private String model;

	@ApiModelProperty("列表")
	private List<Dvt1Inner> list;

	public static class Dvt1Inner {
		@ApiModelProperty("风险描述")
		private String riskDescription;

		@ApiModelProperty("风险来源")
		private String riskSource;

		@ApiModelProperty("风险影响")
		private String riskImpact;

		@ApiModelProperty("潜在原因")
		private String potentialCause;

		@ApiModelProperty("现行控制方式")
		private String currentControlMode;

		@ApiModelProperty("dvt1附件提交时间")
		private Date dvt1CommitTime;


		@ApiModelProperty("dvt1附件")
		private String dvt1Attachment;

		public String getRiskSource() {
			return riskSource;
		}

		public void setRiskSource(String riskSource) {
			this.riskSource = riskSource;
		}

		public String getRiskDescription() {
			return riskDescription;
		}

		public Date getDvt1CommitTime() {
			return dvt1CommitTime;
		}

		public void setDvt1CommitTime(Date dvt1CommitTime) {
			this.dvt1CommitTime = dvt1CommitTime;
		}

		public void setRiskDescription(String riskDescription) {
			this.riskDescription = riskDescription;
		}

		public String getRiskImpact() {
			return riskImpact;
		}

		public void setRiskImpact(String riskImpact) {
			this.riskImpact = riskImpact;
		}

		public String getPotentialCause() {
			return potentialCause;
		}

		public void setPotentialCause(String potentialCause) {
			this.potentialCause = potentialCause;
		}

		public String getCurrentControlMode() {
			return currentControlMode;
		}

		public void setCurrentControlMode(String currentControlMode) {
			this.currentControlMode = currentControlMode;
		}

		public String getDvt1Attachment() {
			return dvt1Attachment;
		}

		public void setDvt1Attachment(String dvt1Attachment) {
			this.dvt1Attachment = dvt1Attachment;
		}
	}

	public String getSubdocumentId() {
		return subdocumentId;
	}

	public void setSubdocumentId(String subdocumentId) {
		this.subdocumentId = subdocumentId;
	}

	public String getEngPhase() {
		return engPhase;
	}

	public void setEngPhase(String engPhase) {
		this.engPhase = engPhase;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Dvt1Inner> getList() {
		return list;
	}

	public void setList(List<Dvt1Inner> list) {
		this.list = list;
	}
}
