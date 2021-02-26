package cn.jwis.qualityworkflow.modules.apqp.bean;

import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-14 11:42
 * @since 0.1.0
 **/
public class QueryFileUpdateVo extends PageBean {
	// 给父类的属性赋值
	public QueryFileUpdateVo() {
		super.setOrderBy("create_date");
		super.setTime("create_date");
	}

	@ApiModelProperty("机型名称")
	private List<String> model;

	@ApiModelProperty("文件Owner")
	private List<String> fileOwner;

	public List<String> getModel() {
		return model;
	}

	public void setModel(List<String> model) {
		this.model = model;
	}

	public List<String> getFileOwner() {
		return fileOwner;
	}

	public void setFileOwner(List<String> fileOwner) {
		this.fileOwner = fileOwner;
	}
}
