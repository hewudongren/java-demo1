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
 * @create 2020-07-27 15:07
 * @since 0.1.0
 **/
@ApiModel("流程 对应的 前端路由  流程邮件跳转使用")
public enum  WorkflowRoute {

	/**
	 * ECN流程
	 */
	@ApiModelProperty("ECN流程")
	ECN(EcnInfo.class.getName(),"/ecn/workflow/"),

	/**
	 * 外来文件流程
	 */
	@ApiModelProperty("外来文件流程")
	EXTERNAL_DOCUMENT(ExternalDocument.class.getName(), "/ecn/foreign-doc-workflow/"),

	/**
	 * 返工程
	 */
	@ApiModelProperty("返工程")
	REWORK(ReworkInfo.class.getName(), "/rework/form-management/"),

	/**
	 * APQP流程
	 */
	@ApiModelProperty("APQP流程")
	APQP(ApqpSubdocument.class.getName(), "/apqp/step/workflow/");


	private String name;

	private String route;

	WorkflowRoute(String name, String route) {
		this.name = name;
		this.route = route;
	}

	public String getName() {
		return name;
	}

	public String getRoute() {
		return route;
	}

	public static String getRouteByName(String name) {
		if (name != null && !"".equals(name)) {
			for (WorkflowRoute enums : WorkflowRoute.values()) {
				if (enums.getName().equals(name)) {
					return enums.route;
				}
			}
		}
		return null;
	}
}
