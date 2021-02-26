package cn.jwis.qualityworkflow.modules.qims.bean;

import java.util.List;

/**
 * @Description 灰色问题
 * @Author yuyangyang
 * @Date 2020/5/26 11:17
 */
public class GrayProblemSearch {
	private List<String> paragraphPartings;
	private List<String> lines;
	private List<String> occurrenceSites;
	private List<String> models;
	private List<String> frequencys;
	private List<String> isTimelys;
	private List<String> status;
	private String name;
	private  Integer page;
	private  Integer size;
	private String startTime;
	private String endTime;

	public List<String> getParagraphPartings() {
		return paragraphPartings;
	}

	public void setParagraphPartings(List<String> paragraphPartings) {
		this.paragraphPartings = paragraphPartings;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getOccurrenceSites() {
		return occurrenceSites;
	}

	public void setOccurrenceSites(List<String> occurrenceSites) {
		this.occurrenceSites = occurrenceSites;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public List<String> getFrequencys() {
		return frequencys;
	}

	public void setFrequencys(List<String> frequencys) {
		this.frequencys = frequencys;
	}

	public List<String> getIsTimelys() {
		return isTimelys;
	}

	public void setIsTimelys(List<String> isTimelys) {
		this.isTimelys = isTimelys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}
}