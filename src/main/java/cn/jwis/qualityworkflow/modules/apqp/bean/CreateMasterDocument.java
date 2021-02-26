package cn.jwis.qualityworkflow.modules.apqp.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-26 14:51
 * @since 0.1.0
 **/
@ApiModel("主单据创建Vo")
public class CreateMasterDocument {

	@ApiModelProperty("主题")
	private String theme;


	@ApiModelProperty("产品类别")
	private String modelCategory;


	@ApiModelProperty("制造类型")
	private String manufactureType;

	@ApiModelProperty("项目(ODM)")
	private String item;

	@ApiModelProperty("机型名称")
	private String model;


	@ApiModelProperty("上一代产品")
	private String previousProduct;


	@ApiModelProperty("产品系列")
	private String productSeries;

	@ApiModelProperty("子单据List")
	List<CreateSubDocumentVo> list;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getModelCategory() {
		return modelCategory;
	}

	public void setModelCategory(String modelCategory) {
		this.modelCategory = modelCategory;
	}

	public String getManufactureType() {
		return manufactureType;
	}

	public void setManufactureType(String manufactureType) {
		this.manufactureType = manufactureType;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPreviousProduct() {
		return previousProduct;
	}

	public void setPreviousProduct(String previousProduct) {
		this.previousProduct = previousProduct;
	}

	public String getProductSeries() {
		return productSeries;
	}

	public void setProductSeries(String productSeries) {
		this.productSeries = productSeries;
	}

	public List<CreateSubDocumentVo> getList() {
		return list;
	}

	public void setList(List<CreateSubDocumentVo> list) {
		this.list = list;
	}
}
