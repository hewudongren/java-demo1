package cn.jwis.qualityworkflow.modules.esd.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/17 10:35
 */
public class EsdSpeciaManageSearch {

	@ApiModelProperty(value = "样品集合",notes = "样品集合")
	private List<String> sampleNames;

	@ApiModelProperty(value = "供应商集合",notes = "供应商集合")
	private List<String>  suppliers;

	@ApiModelProperty(value = "机型集合",notes = "机型集合")
	private List<String> models;

	@ApiModelProperty(value = "料号集合",notes = "料号集合")
	private List<String> materials;

	@ApiModelProperty(value = "处理方式集合",notes = "处理方式集合")
	private List<String> handlingMethods;

	@ApiModelProperty(value = "需求部门集合",notes = "需求部门集合")
	private List<String> demandDepartments;

	@ApiModelProperty(value = "结束时间",notes = "结束时间")
	private String endTime;

	@ApiModelProperty(value = "开始时间",notes = "开始时间")
	private String startTime;

	@ApiModelProperty(value = "页码",notes = "页码")
	private Integer page;

	@ApiModelProperty(value = "每页的数据条数",notes = "每页的数据条数")
	private Integer size;

	@ApiModelProperty(value = "当前用户",notes = "当前用户")
	private String assignee;


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

	public List<String> getHandlingMethods() {
		return handlingMethods;
	}

	public void setHandlingMethods(List<String> handlingMethods) {
		this.handlingMethods = handlingMethods;
	}

	public List<String> getDemandDepartments() {
		return demandDepartments;
	}

	public void setDemandDepartments(List<String> demandDepartments) {
		this.demandDepartments = demandDepartments;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}