package cn.jwis.qualityworkflow.modules.apqp.bean;

import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-29 15:41
 * @since 0.1.0
 **/
public class QuerySubDocumentListVo extends PageBean {
	// 给父类的属性赋值
	public QuerySubDocumentListVo() {
		super.setOrderBy("create_date");
		super.setTime("create_date");
	}


	@ApiModelProperty("项目")
	private List<String> items;

	@ApiModelProperty("产品系列")
	private List<String> productSeries;

	@ApiModelProperty("工程段")
	private List<String> engPhase;


	@ApiModelProperty(" 处理状态 0 是全部  1 是待办，  2是已处理")
	private int flag = 1;

	@ApiModelProperty("处理人")
	private String assignee;


	public List<String> getEngPhase() {
		return engPhase;
	}

	public void setEngPhase(List<String> engPhase) {
		this.engPhase = engPhase;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public List<String> getProductSeries() {
		return productSeries;
	}

	public void setProductSeries(List<String> productSeries) {
		this.productSeries = productSeries;
	}

}
