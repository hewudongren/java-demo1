package cn.jwis.qualityworkflow.modules.qims.bean;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/22 10:14
 */
public class QimsBlackSearch {
	@ApiModelProperty(value = "段别集合",notes = "段别集合")
	private List<String> paragraphPartings;
	@ApiModelProperty(value = "线体集合",notes = "线体集合")
	private List<String> lines;
	@ApiModelProperty(value = "异常站点集合",notes = "异常站点集合")
	private List<String> anomalySites;
	@ApiModelProperty(value = "机型集合",notes = "机型集合")
	private List<String> models;
	@ApiModelProperty(value = "班次集合",notes = "班次集合")
	private List<String> frequencys;
	@ApiModelProperty(value = "订单号集合",notes = "订单号集合")
	private List<String> orderSns;
	@ApiModelProperty(value = "状态",notes = "状态")
	private List<String> status;
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
	@ApiModelProperty(value = "是否停线集合",notes = "是否停线集合")
	private List<String> isStop;
	//flag 为1是代办 为2是关闭 没有值是全部
	@ApiModelProperty(value = "代办，已处理标识",notes = "代办，已处理标识")
	private String flag;

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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

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

	public List<String> getAnomalySites() {
		return anomalySites;
	}

	public void setAnomalySites(List<String> anomalySites) {
		this.anomalySites = anomalySites;
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

	public List<String> getOrderSns() {
		return orderSns;
	}

	public void setOrderSns(List<String> orderSns) {
		this.orderSns = orderSns;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<String> getIsStop() {
		return isStop;
	}

	public void setIsStop(List<String> isStop) {
		this.isStop = isStop;
	}
}