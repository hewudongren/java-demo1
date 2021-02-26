package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-06-04 11:27
 * @since 0.1.0
 **/
public class UpdateDocumentInfoVo {
	@ApiModelProperty("文件id")
	@NotBlank(message = "文件id不能为空")
	private String id;

	@ApiModelProperty("文件DFS oid")
	@NotBlank(message = "文件DFS oid不能为空")
	private String documentOid;

	@ApiModelProperty("文件名")
	private String documentName;

	@ApiModelProperty("文件提交时间")
	private Date summitTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocumentOid() {
		return documentOid;
	}

	public void setDocumentOid(String documentOid) {
		this.documentOid = documentOid;
	}

	public Date getSummitTime() {
		return summitTime;
	}

	public void setSummitTime(Date summitTime) {
		this.summitTime = summitTime;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
}
