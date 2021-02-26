package cn.jwis.qualityworkflow.modules.ecn.bean;

import cn.jwis.qualityworkflow.enums.SortDirection;
import io.swagger.annotations.ApiModelProperty;

public class PageBean {
	@ApiModelProperty("每页大小")
	int pageSize = 10;

	@ApiModelProperty("页码")
	int pageNum = 1;


	@ApiModelProperty("开始时间")
	String startTime;

	@ApiModelProperty("结束时间")
	String endTime;

	@ApiModelProperty("时间字段")
	String time = "update_date";

	@ApiModelProperty("排序字段")
	String orderBy = "update_date";

	@ApiModelProperty("排序顺序")
	SortDirection sortDirection = SortDirection.DESC;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
}
