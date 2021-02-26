package cn.jwis.qualityworkflow.modules.linequalify.bean;

public class ESDTemplateInfo {
	private String id;
	private String item;
	private String auditCategory;
	private String module;
	private String specificRequirements;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getAuditCategory() {
		return auditCategory;
	}

	public void setAuditCategory(String auditCategory) {
		this.auditCategory = auditCategory;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSpecificRequirements() {
		return specificRequirements;
	}

	public void setSpecificRequirements(String specificRequirements) {
		this.specificRequirements = specificRequirements;
	}

}