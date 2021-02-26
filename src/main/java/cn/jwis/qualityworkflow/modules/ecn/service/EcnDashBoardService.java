package cn.jwis.qualityworkflow.modules.ecn.service;

import cn.jwis.qualityworkflow.modules.ecn.bean.QueryDashboardBean;
import com.alibaba.fastjson.JSONObject;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 看板接口
 * @create 2020-05-06 11:08
 * @since 0.1.0
 **/
public interface EcnDashBoardService {

	/**
	 * @description: 获取DashBoard新增的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:18
	 * @param vo:
	 * @return: long
	 **/
	long getAddCount(QueryDashboardBean vo) throws Exception;

	/**
	 * @description: 获取DashBoard已关闭的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:18
	 * @param vo:
	 * @return: long
	 **/
	long getClosedCount(QueryDashboardBean vo) throws Exception;

	/**
	 * @description: 获取DashBoard超期的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:18
	 * @param vo:
	 * @return: long
	 **/
	long getOverdueCount(QueryDashboardBean vo) throws Exception;

	/**
	 * @description: 获取DashBoard处理中的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:18
	 * @param vo:
	 * @return: long
	 **/
	long getProcessingCount(QueryDashboardBean vo) throws Exception;

	/**
	 * 获取DashBoard 新增 关闭 超期 处理中数量
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	JSONObject getDashBoardCount(QueryDashboardBean vo) throws Exception;


	/**
	 * 获取72小时关闭率
	 * - 72H关闭率=统计周期内的新建单据中，在72H内关闭的单据数/新建单据数
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	JSONObject get72HourCloseRate(QueryDashboardBean vo)throws Exception;

	/**
	 * 获取关闭率
	 * -关闭率=统计周期内，新建单据中关闭的单据数/新建单据数
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	JSONObject getCloseRate(QueryDashboardBean vo)throws Exception;

	/**
	 * 获取趋势图
	 * 获取 24小时处置率 72小时关闭率 获取关闭率
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	JSONObject getTrentChart(QueryDashboardBean vo)throws Exception;
}
