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
public class CreatePfmeaRiskItem {

	@ApiModelProperty("子单据id")
	private String subdocumentId;

	@ApiModelProperty("工程段")
	@NotBlank(message = "工程段不能为空")
	private String engPhase;


	@ApiModelProperty("机型")
	@NotBlank(message = "机型不能为空")
	private String model;

	@ApiModelProperty("列表")
	private List<PfmeaInner> list;

	public static class PfmeaInner {
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
		 * PEMEA附件提交时间
		 *
		 * @mbggenerated
		 */
		private Date pemeaCommitTime;

		/**
		 * RPN
		 *
		 * @mbggenerated
		 */
		private String rpn;

		/**
		 * 操作步骤
		 *
		 * @mbggenerated
		 */
		private String operationStep;

		/**
		 * 潜在失效模式
		 *
		 * @mbggenerated
		 */
		private String potentialFailureMode;

		/**
		 * 潜在失效后果
		 *
		 * @mbggenerated
		 */
		private String potentialFailureResult;

		/**
		 * 潜在失效起因
		 *
		 * @mbggenerated
		 */
		private String potentialFailureCause;

		/**
		 * 严重度
		 *
		 * @mbggenerated
		 */
		private String severity;

		/**
		 * 频度 O
		 *
		 * @mbggenerated
		 */
		private String frequency;

		/**
		 * 可探测度
		 *
		 * @mbggenerated
		 */
		private String detectability;

		/**
		 * 现行控制
		 *
		 * @mbggenerated
		 */
		private String currentControl;

		/**
		 * PEMEA附件
		 *
		 * @mbggenerated
		 */
		private String pemeaAttachment;

		public PfmeaInner() {
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


		public Date getPemeaCommitTime() {
			return pemeaCommitTime;
		}

		public void setPemeaCommitTime(Date pemeaCommitTime) {
			this.pemeaCommitTime = pemeaCommitTime;
		}

		public String getRpn() {
			return rpn;
		}

		public void setRpn(String rpn) {
			this.rpn = rpn;
		}

		public String getOperationStep() {
			return operationStep;
		}

		public void setOperationStep(String operationStep) {
			this.operationStep = operationStep;
		}

		public String getPotentialFailureMode() {
			return potentialFailureMode;
		}

		public void setPotentialFailureMode(String potentialFailureMode) {
			this.potentialFailureMode = potentialFailureMode;
		}

		public String getPotentialFailureResult() {
			return potentialFailureResult;
		}

		public void setPotentialFailureResult(String potentialFailureResult) {
			this.potentialFailureResult = potentialFailureResult;
		}

		public String getPotentialFailureCause() {
			return potentialFailureCause;
		}

		public void setPotentialFailureCause(String potentialFailureCause) {
			this.potentialFailureCause = potentialFailureCause;
		}

		public String getSeverity() {
			return severity;
		}

		public void setSeverity(String severity) {
			this.severity = severity;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}

		public String getDetectability() {
			return detectability;
		}

		public void setDetectability(String detectability) {
			this.detectability = detectability;
		}

		public String getCurrentControl() {
			return currentControl;
		}

		public void setCurrentControl(String currentControl) {
			this.currentControl = currentControl;
		}

		public String getPemeaAttachment() {
			return pemeaAttachment;
		}

		public void setPemeaAttachment(String pemeaAttachment) {
			this.pemeaAttachment = pemeaAttachment;
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

	public List<PfmeaInner> getList() {
		return list;
	}

	public void setList(List<PfmeaInner> list) {
		this.list = list;
	}
}
