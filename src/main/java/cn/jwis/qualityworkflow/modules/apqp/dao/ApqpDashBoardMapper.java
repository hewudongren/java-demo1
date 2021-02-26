package cn.jwis.qualityworkflow.modules.apqp.dao;

import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryDashboardDetailVo;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-06-02 14:57
 * @since 0.1.0
 **/
@Mapper
@Component
public interface ApqpDashBoardMapper {

	int getAddCount(TimeBean bean);

	int getCloseCount(TimeBean bean);

	int getProcessingCount(TimeBean bean);

	int getOverdueCount(TimeBean bean);

	List<JSONObject> getAddCountGroupByItem(TimeBean bean);

	List<JSONObject> getCloseCountGroupByItem(TimeBean bean);

	List<JSONObject> getProcessingCountGroupByItem(TimeBean bean);

	List<JSONObject> getOverdueCountGroupByItem(TimeBean bean);

	List<JSONObject> getAddCountGroupBy(QueryDashboardDetailVo bean);

	List<JSONObject> getCloseCountGroupBy(QueryDashboardDetailVo bean);

	List<JSONObject> getProcessingCountGroupBy(QueryDashboardDetailVo bean);

	List<JSONObject> getOverdueCountGroupBy(QueryDashboardDetailVo bean);
}
