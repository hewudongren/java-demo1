package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-26 14:31
 * @since 0.1.0
 **/
@ApiModel("创建子单据vo")
public class CreateSubDocumentVo {

	@ApiModelProperty("NUDD/FX文档")
	private String dvt1Document;

	@ApiModelProperty("PEMEA文档")
	private String pemeaDocument;

	@ApiModelProperty("QC_Char文档")
	private String qcChartDocument;

	@ApiModelProperty("工程段")
	private String engPhase;

	@ApiModelProperty("Owner")
	private String owner;

	@ApiModelProperty("QE")
	private String qe;

	@ApiModelProperty("DVT1 Alarm Date")
	private Date dvt1AlarmDate;

	@ApiModelProperty("DVT1 Deadline")
	private Date dvt1Deadline;

	@ApiModelProperty("Ok2Pilot Alarm Date ")
	private Date pemeaAlarmDate;

	@ApiModelProperty("Ok2Pilot Deadline ")
	private Date pemeaDeadline;

	@ApiModelProperty("QC-Chart Alarm Date ")
	private Date qcChartAlarmDate;

	@ApiModelProperty("QC-Chart Deadline ")
	private Date qcChartDeadline;

	@ApiModelProperty(" Alarm (h/per) 邮件频率")
	private String frequency;

	public String getDvt1Document() {
		return dvt1Document;
	}

	public void setDvt1Document(String dvt1Document) {
		this.dvt1Document = dvt1Document;
	}

	public String getPemeaDocument() {
		return pemeaDocument;
	}

	public void setPemeaDocument(String pemeaDocument) {
		this.pemeaDocument = pemeaDocument;
	}

	public String getQcChartDocument() {
		return qcChartDocument;
	}

	public void setQcChartDocument(String qcChartDocument) {
		this.qcChartDocument = qcChartDocument;
	}

	public String getEngPhase() {
		return engPhase;
	}

	public void setEngPhase(String engPhase) {
		this.engPhase = engPhase;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getQe() {
		return qe;
	}

	public void setQe(String qe) {
		this.qe = qe;
	}

	public Date getDvt1AlarmDate() {
		return dvt1AlarmDate;
	}

	public void setDvt1AlarmDate(Date dvt1AlarmDate) {
		this.dvt1AlarmDate = dvt1AlarmDate;
	}

	public Date getDvt1Deadline() {
		return dvt1Deadline;
	}

	public void setDvt1Deadline(Date dvt1Deadline) {
		this.dvt1Deadline = dvt1Deadline;
	}

	public Date getPemeaAlarmDate() {
		return pemeaAlarmDate;
	}

	public void setPemeaAlarmDate(Date pemeaAlarmDate) {
		this.pemeaAlarmDate = pemeaAlarmDate;
	}

	public Date getPemeaDeadline() {
		return pemeaDeadline;
	}

	public void setPemeaDeadline(Date pemeaDeadline) {
		this.pemeaDeadline = pemeaDeadline;
	}

	public Date getQcChartAlarmDate() {
		return qcChartAlarmDate;
	}

	public void setQcChartAlarmDate(Date qcChartAlarmDate) {
		this.qcChartAlarmDate = qcChartAlarmDate;
	}

	public Date getQcChartDeadline() {
		return qcChartDeadline;
	}

	public void setQcChartDeadline(Date qcChartDeadline) {
		this.qcChartDeadline = qcChartDeadline;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}
