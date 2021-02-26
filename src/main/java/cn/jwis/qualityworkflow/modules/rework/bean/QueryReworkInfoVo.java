package cn.jwis.qualityworkflow.modules.rework.bean;

import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description	返工单信息查询Vo
 * @create 2020-05-15 15:12
 * @since 0.1.0
 **/
public class QueryReworkInfoVo extends PageBean {
	// 给父类的属性赋值
	public QueryReworkInfoVo() {
		super.setOrderBy("create_date");
		super.setTime("problem_time");
	}

	@ApiModelProperty("工艺段")
	private List<String> craftsSection;

	@ApiModelProperty("机型")
	private List<String> model;

	@ApiModelProperty("数据来源")
	private List<String> dataSource;

	public List<String> getCraftsSection() {
		return craftsSection;
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

	public List<String> getDataSource() {
		return dataSource;
	}

	public void setDataSource(List<String> dataSource) {
		this.dataSource = dataSource;
	}
}
