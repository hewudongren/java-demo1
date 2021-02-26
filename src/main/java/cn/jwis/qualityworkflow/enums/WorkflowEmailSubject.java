package cn.jwis.qualityworkflow.enums;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument;
import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-07-28 10:56
 * @since 0.1.0
 **/
@ApiModel("流程 对应的 邮件主题")
public enum WorkflowEmailSubject {

	/**
	 * ECN流程
	 */
	@ApiModelProperty("ECN流程")
	ECN(EcnInfo.class.getName(),"您有一单ECN单据待处理"),

	/**
	 * 外来文件流程
	 */
	@ApiModelProperty("外来文件流程")
	EXTERNAL_DOCUMENT(ExternalDocument.class.getName(), "您有一单外来文件单据待处理"),

	/**
	 * 返工程流程
	 */
	@ApiModelProperty("返工程流程")
	REWORK(ReworkInfo.class.getName(), "您有一单返工流程单据待处理"),

	/**
	 * APQP流程
	 */
	@ApiModelProperty("APQP流程")
	APQP(ApqpSubdocument.class.getName(), "您有一单APQP单据待处理");

	/**
	 * 流程名
	 */
	private String name;

	/**
	 * 主题
	 */
	private String subject;

	public static String getSubjectByName(String name) {
		if (name != null && !"".equals(name)) {
			for (WorkflowEmailSubject enums : WorkflowEmailSubject.values()) {
				if (enums.getName().equals(name)) {
					return enums.subject;
				}
			}
		}
		return null;
	}


	WorkflowEmailSubject(String name, String subject) {
		this.name = name;
		this.subject = subject;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
