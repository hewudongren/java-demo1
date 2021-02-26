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
public class CreateQcChartRiskItem {

	@ApiModelProperty("子单据id")
	private String subdocumentId;

	@ApiModelProperty("工程段")
	@NotBlank(message = "工程段不能为空")
	private String engPhase;


	@ApiModelProperty("机型")
	@NotBlank(message = "机型不能为空")
	private String model;

	@ApiModelProperty("列表")
	private List<InnerClass> list;

	public static class InnerClass {
		/**
		 * 风险描述
		 *
		 * @mbggenerated
		 */
		private String riskDescription;

		/**
		 * 风险来源
		 */
		private String riskSource;

		/**
		 * 产品控制方式
		 *
		 * @mbggenerated
		 */
		private String productControlMode;

		/**
		 * 产品过程规范
		 *
		 * @mbggenerated
		 */
		private String productProcessSpecification;

		/**
		 * QC_Chart附件
		 *
		 * @mbggenerated
		 */
		private String qcChartAttachment;

		/**
		 * QC_Chart附件提交时间
		 *
		 * @mbggenerated
		 */
		private Date qcChartCommitTime;

		public InnerClass() {
		}


		public String getRiskSource() {
			return riskSource;
		}

		public void setRiskSource(String riskSource) {
			this.riskSource = riskSource;
		}

		public String getRiskDescription() {
			return riskDescription;
		}

		public void setRiskDescription(String riskDescription) {
			this.riskDescription = riskDescription;
		}

		public String getProductControlMode() {
			return productControlMode;
		}

		public void setProductControlMode(String productControlMode) {
			this.productControlMode = productControlMode;
		}

		public String getProductProcessSpecification() {
			return productProcessSpecification;
		}

		public void setProductProcessSpecification(String productProcessSpecification) {
			this.productProcessSpecification = productProcessSpecification;
		}

		public String getQcChartAttachment() {
			return qcChartAttachment;
		}

		public void setQcChartAttachment(String qcChartAttachment) {
			this.qcChartAttachment = qcChartAttachment;
		}

		public Date getQcChartCommitTime() {
			return qcChartCommitTime;
		}

		public void setQcChartCommitTime(Date qcChartCommitTime) {
			this.qcChartCommitTime = qcChartCommitTime;
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

	public List<InnerClass> getList() {
		return list;
	}

	public void setList(List<InnerClass> list) {
		this.list = list;
	}
}
