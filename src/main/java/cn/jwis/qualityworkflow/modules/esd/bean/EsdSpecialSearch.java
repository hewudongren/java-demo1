package cn.jwis.qualityworkflow.modules.esd.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/9 15:09
 */
public class EsdSpecialSearch {

	@ApiModelProperty(value = "样品名集合",notes = "样品名集合")
	private List<String> sampleNames;

	@ApiModelProperty(value = "供应商集合",notes = "供应商集合")
	private List<String> suppliers;

	@ApiModelProperty(value = "机型集合",notes = "机型集合")
	private List<String> models;

	@ApiModelProperty(value = "料号集合",notes = "料号集合")
	private List<String> materials;

	@ApiModelProperty(value = "需求部门集合",notes = "需求部门集合")
	private List<String> demandDepartments;

	@ApiModelProperty(value = "处理结论集合",notes = "处理结论集合")
	private List<String> treatmentConclusions;

	@ApiModelProperty(value = "结束时间",notes = "结束时间")
	private String endTime;

	@ApiModelProperty(value = "开始时间",notes = "开始时间")
	private String startTime;

	@ApiModelProperty(value = "用户名",notes = "用户名")
	private String assignee;

	@ApiModelProperty(value = "页码",notes = "页码")
	private Integer page;

	@ApiModelProperty(value = "每页的数据条数",notes = "每页的数据条数")
	private Integer size;

	public List<String> getSampleNames() {
		return sampleNames;
	}

	public void setSampleNames(List<String> sampleNames) {
		this.sampleNames = sampleNames;
	}

	public List<String> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<String> suppliers) {
		this.suppliers = suppliers;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public List<String> getMaterials() {
		return materials;
	}

	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}

	public List<String> getDemandDepartments() {
		return demandDepartments;
	}

	public void setDemandDepartments(List<String> demandDepartments) {
		this.demandDepartments = demandDepartments;
	}

	public List<String> getTreatmentConclusions() {
		return treatmentConclusions;
	}

	public void setTreatmentConclusions(List<String> treatmentConclusions) {
		this.treatmentConclusions = treatmentConclusions;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "EsdSpecialSearch{" + "sampleNames=" + sampleNames + ", suppliers=" + suppliers + ", models=" + models + ", materials=" + materials + ", demandDepartments=" + demandDepartments + ", treatmentConclusions=" + treatmentConclusions + ", endTime='" + endTime + '\'' + ", startTime='" + startTime + '\'' + ", assignee='" + assignee + '\'' + ", page=" + page + ", size=" + size + '}';
	}
}