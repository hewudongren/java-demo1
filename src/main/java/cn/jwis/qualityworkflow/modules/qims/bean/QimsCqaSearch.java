package cn.jwis.qualityworkflow.modules.qims.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description CQA问题查询条件
 * @Author yuyangyang
 * @Date 2020/5/28 14:13
 */
public class QimsCqaSearch {
	@ApiModelProperty(value = "异常站点集合",notes = "异常站点集合")
	private List<String> anomalySites;
	@ApiModelProperty(value = "机型集合",notes = "机型集合")
	private List<String> models;
	@ApiModelProperty(value = "线体集合",notes = "线体集合")
	private List<String> lines;
	@ApiModelProperty(value = "排程号集合",notes = "排程号集合")
	private List<String> scheduleSns;
	@ApiModelProperty(value = "状态集合",notes = "状态集合")
	private List<String> status;
	@ApiModelProperty(value = " IMEL/Track ID集合",notes = " IMES/Track ID集合")
	private List<String>  imelTrackIDs;
	@ApiModelProperty(value = "问题编号集合",notes = "问题编号集合")
	private List<String> questionNumbers;
	@ApiModelProperty(value = "是否停线集合",notes = "是否停线集合")
	private List<String> isStop;
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
	//flag 为1是代办 为2是关闭 没有值是全部
	@ApiModelProperty(value = "代办，已处理标识",notes = "代办，已处理标识")
	private String flag;

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

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getScheduleSns() {
		return scheduleSns;
	}

	public void setScheduleSns(List<String> scheduleSns) {
		this.scheduleSns = scheduleSns;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<String> getImelTrackIDs() {
		return imelTrackIDs;
	}

	public void setImelTrackIDs(List<String> imelTrackIDs) {
		this.imelTrackIDs = imelTrackIDs;
	}

	public List<String> getQuestionNumbers() {
		return questionNumbers;
	}

	public void setQuestionNumbers(List<String> questionNumbers) {
		this.questionNumbers = questionNumbers;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<String> getIsStop() {
		return isStop;
	}

	public void setIsStop(List<String> isStop) {
		this.isStop = isStop;
	}
}