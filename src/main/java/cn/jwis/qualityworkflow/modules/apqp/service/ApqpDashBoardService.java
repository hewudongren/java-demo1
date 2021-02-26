package cn.jwis.qualityworkflow.modules.apqp.service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.DashBoardCountBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryDashboardDetailVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-06-02 18:41
 * @since 0.1.0
 **/

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-30 17:38
 * @since 0.1.0
 **/

public interface ApqpDashBoardService {
	/**
	 * @description: Dashboard 新增主单据
	 * @author: xujinbiao
	 * @date: 2020/5/30 17:42
	 * @return: int
	 **/
	int getOverdueCount(TimeBean bean) throws Exception;

	/**
	 * @description: Dashboard 已关闭主单据
	 * @author: xujinbiao
	 * @date: 2020/5/30 17:42
	 * @return: int
	 **/
	int getCloseCount(TimeBean bean) throws Exception;

	/**
	 * @description: Dashboard 处理中主单据
	 * @author: xujinbiao
	 * @date: 2020/5/30 17:42
	 * @return: int
	 **/
	int getProcessingCount(TimeBean bean) throws Exception;

	/**
	 * @description: Dashboard 新增主单据
	 * @author: xujinbiao
	 * @date: 2020/5/30 17:42
	 * @return: int
	 **/
	int getAddCount(TimeBean bean) throws Exception;


	/**
	 * @description: Dashboard 主单据数量
	 * @author: xujinbiao
	 * @date: 2020/5/30 17:42
	 * @return: int
	 **/
	DashBoardCountBean getDashBoardCount(TimeBean bean) throws Exception;

	/**
	 * @description: 获取项目 汇总数据
	 * @author: xujinbiao
	 * @date: 2020/6/3 10:38
	 * @param bean :
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	JSONObject getSummaryGroupByItem(TimeBean bean) throws Exception;

	/**
	 * @description: 获取详情 分类数据
	 * @author: xujinbiao
	 * @date: 2020/6/3 10:38
	 * @param bean :
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	JSONObject getDashBoardDetail(QueryDashboardDetailVo bean) throws Exception;
}
