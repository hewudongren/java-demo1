package cn.jwis.qualityworkflow.modules.esd.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/9 11:13
 */
public class EsdInStanSearch {
	@ApiModelProperty(value = "样品名集合", name = "sampleNameList")
	private List<String> sampleNameList;
	@ApiModelProperty(value = "检验项目集合", name = "detectionItemList")
	private List<String> detectionItemList;
	@ApiModelProperty(value = "开始时间", name = "startDate")
	private String startDate;
	@ApiModelProperty(value = "结束时间", name = "endDate")
	private String endDate;
	@ApiModelProperty(value = "页码", name = "page")
	private Integer page;
	@ApiModelProperty(value = "每页的数量", name = "size")
	private Integer size;
	@ApiModelProperty(value = "类型",name = "category")
	private String category;

	public List<String> getSampleNameList() {
		return sampleNameList;
	}

	public void setSampleNameList(List<String> sampleNameList) {
		this.sampleNameList = sampleNameList;
	}

	public List<String> getDetectionItemList() {
		return detectionItemList;
	}

	public void setDetectionItemList(List<String> detectionItemList) {
		this.detectionItemList = detectionItemList;
	}


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}