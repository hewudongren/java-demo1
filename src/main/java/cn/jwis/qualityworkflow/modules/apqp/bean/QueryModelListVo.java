package cn.jwis.qualityworkflow.modules.apqp.bean;

import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-22 17:17
 * @since 0.1.0
 **/
public class QueryModelListVo extends PageBean {
	// 给父类的属性赋值
	public QueryModelListVo() {
		super.setOrderBy("create_date");
		super.setTime("create_date");
	}

	@ApiModelProperty("机型名称")
	private List<String> model;

	@ApiModelProperty("产品系列")
	private List<String> productSeries;

	public List<String> getModel() {
		return model;
	}

	public void setModel(List<String> model) {
		this.model = model;
	}

	public List<String> getProductSeries() {
		return productSeries;
	}

	public void setProductSeries(List<String> productSeries) {
		this.productSeries = productSeries;
	}
}
