package cn.jwis.qualityworkflow.modules.qims.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 黑色问题看板批次问题发生率接口
 * @Author yuyangyang
 * @Date 2020/6/1 14:59
 */
public class BlackDashSearch {
	@ApiModelProperty(value = "产品类型集合",notes = "产品类型集合")
	private List<String> modelCategorys;

	@ApiModelProperty(value = "制造类型集合",notes = "制造类型集合")
	private List<String> manufactureTypes;

	@ApiModelProperty(value = "项目集合",notes = "项目集合")
	private List<String> items;

	@ApiModelProperty(value = "机型集合",notes="机型集合")
	private List<String> models;

	@ApiModelProperty(value = "段别集合",notes = "段别集合")
	private List<String> process;

	@ApiModelProperty(value = "缺陷类型集合",notes = "缺陷类型集合")
	private List<String> defectTypes;


	@ApiModelProperty(value = "开始时间",notes = "开始时间")

	private String startTime;


	@ApiModelProperty(value = "结束时间",notes = "结束时间")

	private String endTime;

	@ApiModelProperty(value = "周期标识",notes = "周期标识")
	private Integer cycle;

	@ApiModelProperty(value = "周期字段",notes = "周期字段")
	private  String temp;

	public List<String> getModelCategorys() {
		return modelCategorys;
	}

	public void setModelCategorys(List<String> modelCategorys) {
		this.modelCategorys = modelCategorys;
	}

	public List<String> getManufactureTypes() {
		return manufactureTypes;
	}

	public void setManufactureTypes(List<String> manufactureTypes) {
		this.manufactureTypes = manufactureTypes;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public List<String> getProcess() {
		return process;
	}

	public void setProcess(List<String> process) {
		this.process = process;
	}

	public List<String> getDefectTypes() {
		return defectTypes;
	}

	public void setDefectTypes(List<String> defectTypes) {
		this.defectTypes = defectTypes;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
}