package cn.jwis.qualityworkflow.modules.esd.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description ESD稽核模板查询的条件实体类
 * @Author yuyangyang
 * @Date 2020/7/1 16:47
 */
public class EsdAudiMainSearch {

	@ApiModelProperty(value = "auditTypes", name = "稽核类型集合")
	private List<String> auditTypes;

	@ApiModelProperty(value = "documentNumbers", name = "文件编号集合")
	private List<String> documentNumbers;

	@ApiModelProperty(value = "creators", name = "创建人集合")
	private List<String> creators;

	@ApiModelProperty(value = "page", name = "页码数量")
	private Integer page;

	@ApiModelProperty(value = "size", name = "一页展示的数量")
	private Integer size;

	public List<String> getAuditTypes() {
		return auditTypes;
	}

	public void setAuditTypes(List<String> auditTypes) {
		this.auditTypes = auditTypes;
	}

	public List<String> getDocumentNumbers() {
		return documentNumbers;
	}

	public void setDocumentNumbers(List<String> documentNumbers) {
		this.documentNumbers = documentNumbers;
	}

	public List<String> getCreators() {
		return creators;
	}

	public void setCreators(List<String> creators) {
		this.creators = creators;
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
}