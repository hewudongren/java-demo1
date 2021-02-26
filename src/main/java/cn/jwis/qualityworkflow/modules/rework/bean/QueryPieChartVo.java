package cn.jwis.qualityworkflow.modules.rework.bean;

import cn.jwis.qualityworkflow.bean.TimeBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-19 17:32
 * @since 0.1.0
 **/
public class QueryPieChartVo extends TimeBean {


	@ApiModelProperty("工艺段")
	private List<String> craftsSection;

	@ApiModelProperty("机型")
	private List<String> model;

	@ApiModelProperty("根本原因责任方")
	private List<String> rootCauseResponsibility;

	@ApiModelProperty("分类字段")
	private String groupBy;

	// 给父类的属性赋值
	public QueryPieChartVo() {
		super.setTime("problem_time");
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public List<String> getCraftsSection() {
		return craftsSection;
	}

	public List<String> getRootCauseResponsibility() {
		return rootCauseResponsibility;
	}

	public void setRootCauseResponsibility(List<String> rootCauseResponsibility) {
		this.rootCauseResponsibility = rootCauseResponsibility;
	}

	public void setCraftsSection(List<String> craftsSection) {
		this.craftsSection = craftsSection;
	}

	public List<String> getModel() {
		return model;
	}

	public void setModel(List<String> model) {
		this.model = model;
	}
}
