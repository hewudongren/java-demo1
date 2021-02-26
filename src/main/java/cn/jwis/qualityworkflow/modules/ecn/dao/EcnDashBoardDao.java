package cn.jwis.qualityworkflow.modules.ecn.dao;

import cn.jwis.qualityworkflow.modules.ecn.bean.QueryDashboardBean;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-07-10 18:00
 * @since 0.1.0
 **/
@Mapper
public interface EcnDashBoardDao {
	/**
	 * 获取Ecn新增数量
	 * @param vo
	 * @return
	 */
	int getEcnAddCount(QueryDashboardBean vo);

	/**
	 * 获取外来文件新增数量
	 * @param vo
	 * @return
	 */
	int getExtAddCount(QueryDashboardBean vo);

	/**
	 * 获取Ecn已关闭数量
	 * @param vo
	 * @return
	 */
	int getEcnClosedCount(QueryDashboardBean vo);

	/**
	 * 获取外来文件已关闭数量
	 * @param vo
	 * @return
	 */
	int getExtCloseCount(QueryDashboardBean vo);

	/**
	 * 获取Ecn处理中数量
	 * @param vo
	 * @return
	 */
	int getEcnProcessingCount(QueryDashboardBean vo);

	/**
	 * 获取外来文件处理中数量
	 * @param vo
	 * @return
	 */
	int getExtProcessingCount(QueryDashboardBean vo);

	/**
	 * 获取Ecn超期数量
	 * @param vo
	 * @return
	 */
	int getEcnOverDueCount(QueryDashboardBean vo);

	/**	/**
	 * 获取外来文件超期数量
	 * @param vo
	 * @return
	 */
	int getExtOverDueCount(QueryDashboardBean vo);

	/**
	 * 获取Ecn 按月分类新增数量
	 * @param vo
	 * @return
	 */
	List<JSONObject> getEcnAddCountGroupByTime(QueryDashboardBean vo);

	/**
	 * 获取外来文件 按月分类新增数量
	 * @param vo
	 * @return
	 */
	List<JSONObject> getExtAddCountGroupByTime(QueryDashboardBean vo);

	/**
	 * 获取Ecn 按月分类72小时关闭数量
	 * @param vo
	 * @return
	 */
	List<JSONObject> getEcn72HourCloseNum(QueryDashboardBean vo);

	/**
	 * 获取外来文件 按月分类72小时内数量
	 * @param vo
	 * @return
	 */
	List<JSONObject> getExt72HourCloseNum(QueryDashboardBean vo);

	/**
	 * 获取Ecn 按月分类72小时关闭数量
	 * @param vo
	 * @return
	 */
	List<JSONObject> getEcnCloseNum(QueryDashboardBean vo);

	/**
	 * 获取外来文件 按月分类72小时内数量
	 * @param vo
	 * @return
	 */
	List<JSONObject> getExtCloseNum(QueryDashboardBean vo);

	/**
	 * 获取Ecn 按月分类24小时及时处置率
	 * @param vo
	 * @return
	 */
	List<JSONObject> getEcn24HourDisposalNum(QueryDashboardBean vo);

	/**
	 * 获取外来文件 按月分类24小时及时处置率
	 * @param vo
	 * @return
	 */
	List<JSONObject> getExt24HourDisposalNum(QueryDashboardBean vo);

}
