package cn.jwis.qualityworkflow.bean;

import java.util.Map;

public class PageResult {
	private Object data;
	private Long count;
	private int currentPage;
	private int pageSize;
	private Map<String, String> titles;

	public PageResult(Long count, int currentPage, int pageSize, Object data) {
		this.count = count;
		this.data = data;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	public PageResult(Long count, int currentPage, int pageSize, Object data, Map<String, String> titles) {
		this.count = count;
		this.data = data;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.titles = titles;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, String> getTitles() {
		return titles;
	}

	public void setTitles(Map<String, String> titles) {
		this.titles = titles;
	}
}
