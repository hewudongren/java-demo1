package cn.jwis.qualityworkflow.modules.apqp.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 获取机型 相互级联查询 vo
 * @create 2020-05-25 15:33
 * @since 0.1.0
 **/
public class QueryModelCascadeVo {
	/**
	 * 机型名称
	 *
	 * @mbggenerated
	 */
	private String model;

	/**
	 * 产品类别
	 *
	 * @mbggenerated
	 */
	private String modelCategory;

	/**
	 * 制造类型
	 *
	 * @mbggenerated
	 */
	private String manufactureType;

	/**
	 * 项目(ODM)
	 *
	 * @mbggenerated
	 */
	private String item;

	/**
	 * 产品系列
	 *
	 * @mbggenerated
	 */
	private String productSeries;

	@ApiModelProperty("下拉值属性")
	@NotBlank(message = "下拉值属性不能为空")
	private String paramter;


	public String getParamter() {
		return paramter;
	}

	public void setParamter(String paramter) {
		this.paramter = paramter;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getProductSeries() {
		return productSeries;
	}

	public void setProductSeries(String productSeries) {
		this.productSeries = productSeries;
	}
}
