package cn.jwis.qualityworkflow.modules.apqp.bean;

import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-22 16:02
 * @since 0.1.0
 **/
public class QueryMasterListVo  extends PageBean {
	// 给父类的属性赋值
	public QueryMasterListVo() {
		super.setOrderBy("create_date");
		super.setTime("create_date");
	}

	@ApiModelProperty("项目")
	private List<String> items;

	@ApiModelProperty("产品系列")
	private List<String> productSeries;

	@ApiModelProperty("创建者")
	private String creator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItem(List<String> items) {
		this.items = items;
	}

	public List<String> getProductSeries() {
		return productSeries;
	}

	public void setProductSeries(List<String> productSeries) {
		this.productSeries = productSeries;
	}
}
