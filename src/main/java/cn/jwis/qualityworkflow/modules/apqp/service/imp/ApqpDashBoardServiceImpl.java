package cn.jwis.qualityworkflow.modules.apqp.service.imp;

import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.enums.APQPDashBoardType;
import cn.jwis.qualityworkflow.modules.apqp.bean.DashBoardCountBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryDashboardDetailVo;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpDashBoardMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.ApqpDashBoardService;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-30 17:45
 * @since 0.1.0
 **/
@Service
public class ApqpDashBoardServiceImpl implements ApqpDashBoardService {



	@Autowired
	ApqpDashBoardMapper dashBoardDao;

	@Override
	public int getAddCount(TimeBean bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		int addCount = dashBoardDao.getAddCount(bean);
		return addCount;
	}

	@Override
	public DashBoardCountBean getDashBoardCount(TimeBean bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		int addCount = dashBoardDao.getAddCount(bean);
		int closeCount = dashBoardDao.getCloseCount(bean);
		int processingCount = dashBoardDao.getProcessingCount(bean);
		int overdueCount = dashBoardDao.getOverdueCount(bean);
		// 获取Total数量
		TimeBean totalTimeBean = new TimeBean();
		int totalOverdue = this.getOverdueCount(totalTimeBean);
		int totalClose = this.getCloseCount(totalTimeBean);
		int totalProcessing = this.getProcessingCount(totalTimeBean);
		int totalAdd = this.getAddCount(totalTimeBean);
		// 拼装
		DashBoardCountBean result = new DashBoardCountBean();
		result.setAddCount(addCount);
		result.setTotalAddCount(totalAdd);
		result.setCloseCount(closeCount);
		result.setTotalCloseCount(totalClose);
		result.setOverdueCount(overdueCount);
		result.setTotalOverdueCount(totalOverdue);
		result.setProcessingCount(processingCount);
		result.setTotalProcessingCount(totalProcessing);
		return result;
	}

	@Override
	public JSONObject getSummaryGroupByItem(TimeBean bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		List<JSONObject> addCountGroupByItem = dashBoardDao.getAddCountGroupByItem(bean);
		List<JSONObject> closeCountGroupByItem = dashBoardDao.getCloseCountGroupByItem(bean);
		List<JSONObject> overdueCountGroupByItem = dashBoardDao.getOverdueCountGroupByItem(bean);
		List<JSONObject> processingCountGroupByItem = dashBoardDao.getProcessingCountGroupByItem(bean);
		// 以addCountGroupByItem(新增)的结果list size 作为result的size, 因为addCountGroupByItem是其他三个的总和
		JSONObject result = new JSONObject(addCountGroupByItem.size());
		addCountGroupByItem.stream().forEach(d -> {
			String item = d.getString("item");
			Object addCount = d.get("addCount");
			JSONObject object = new JSONObject();
			Long addNum = (addCount != null && addCount instanceof Long) ? (Long) addCount : 0;
			object.put("addCount", addNum);
			object.put("closeCount", 0);
			object.put("overdueCount", 0);
			object.put("processingCount", 0);
			result.put(item, object);
		});
		closeCountGroupByItem.stream().forEach(d -> {
			String item = d.getString("item");
			Object closeCount = d.get("closeCount");
			JSONObject object = (JSONObject)result.get(item);
			object.put("closeCount", closeCount);
		});
		overdueCountGroupByItem.stream().forEach(d -> {
			String item = d.getString("item");
			Object overdueCount = d.get("overdueCount");
			JSONObject object = (JSONObject)result.get(item);
			object.put("overdueCount", overdueCount);
		});
		processingCountGroupByItem.stream().forEach(d -> {
			String item = d.getString("item");
			Object processingCount = d.get("processingCount");
			JSONObject object = (JSONObject)result.get(item);
			object.put("processingCount", processingCount);
		});
		return result;
	}

	@Override
	public JSONObject getDashBoardDetail(QueryDashboardDetailVo bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		JSONObject result = new JSONObject();
		List<JSONObject> list = null;
		if (APQPDashBoardType.ADD.equals(bean.getStatus())) {
			list = dashBoardDao.getAddCountGroupBy(bean);
		} else if (APQPDashBoardType.CLOSE.equals(bean.getStatus())) {
			list = dashBoardDao.getCloseCountGroupBy(bean);
		} else if (APQPDashBoardType.PROCESSING.equals(bean.getStatus())) {
			list = dashBoardDao.getProcessingCountGroupBy(bean);
		} else if (APQPDashBoardType.OVERDUE.equals(bean.getStatus())) {
			list = dashBoardDao.getOverdueCountGroupBy(bean);
		}
		// 按照文件类型分类
		if (CollectionUtils.isNotEmpty(list)) {
			Map<String, List<JSONObject>> documentMap = list.stream().collect(Collectors.groupingBy(d -> d.getString("document")));
			for (Map.Entry<String, List<JSONObject>> entry : documentMap.entrySet()) {
				List<JSONObject> value = entry.getValue();
				if (CollectionUtils.isNotEmpty(value)) {
					JSONObject object = new JSONObject();
					//工程段map
					Map<String, List<JSONObject>> engPhaseMap = value.stream().collect(Collectors.groupingBy(d -> d.getString("eng_phase")));
					for (Map.Entry<String, List<JSONObject>> ent : engPhaseMap.entrySet()) {
						object.put(ent.getKey(), ent.getValue().get(0).getLong("num"));
					}
					result.put(entry.getKey(), object);
				}
			}
		}
		return result;
	}

	@Override
	public int getCloseCount(TimeBean bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		int closeCount = dashBoardDao.getCloseCount(bean);
		return closeCount;
	}

	@Override
	public int getProcessingCount(TimeBean bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		int processingCount = dashBoardDao.getProcessingCount(bean);
		return processingCount;
	}


	@Override
	public int getOverdueCount(TimeBean bean) throws Exception {
		ReflectUtil.preHandleTimeTnterval(bean);
		int overdueCount = dashBoardDao.getOverdueCount(bean);
		return overdueCount;
	}
}
