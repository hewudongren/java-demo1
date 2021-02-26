package cn.jwis.qualityworkflow.enums;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 节点状态
 * @create 2020-04-30 14:07
 * @since 0.1.0
 **/
public enum TaskState {
	/**
	 * 已关闭
	 */
	@ApiModelProperty("已关闭")
	CLOSE,

	/**
	 * 进行中
	 */
	@ApiModelProperty("进行中")
	ONGOING;
}
