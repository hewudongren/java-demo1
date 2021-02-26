package cn.jwis.qualityworkflow.modules.apqp.bean;

import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.enums.APQPDashBoardType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-06-04 14:10
 * @since 0.1.0
 **/
@ApiModel("查询DashBoard Detail页面Vo")
public class QueryDashboardDetailVo extends TimeBean {

	@ApiModelProperty("四种状态 已关闭,处理中,新增，超期")
	private APQPDashBoardType status;

	public APQPDashBoardType getStatus() {
		return status;
	}

	public void setStatus(APQPDashBoardType status) {
		this.status = status;
	}
}
