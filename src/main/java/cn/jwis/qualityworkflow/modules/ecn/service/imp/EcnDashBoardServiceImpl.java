package cn.jwis.qualityworkflow.modules.ecn.service.imp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ecn.bean.QueryDashboardBean;
import cn.jwis.qualityworkflow.modules.ecn.dao.EcnDashBoardDao;
import cn.jwis.qualityworkflow.modules.ecn.service.EcnDashBoardService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.FormatUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description Ecn看板实现类
 * @create 2020-05-06 11:20
 * @since 0.1.0
 **/
@Service
public class EcnDashBoardServiceImpl implements EcnDashBoardService {

	private final static String UPDATEDATE = "update_date";

	private static final String ECN = "ECN";

	private static final String EXTERNAL_DOCUMENT = "外来文件";

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Resource
	EcnDashBoardDao dashBoardDao;

	@Override
	public long getAddCount(QueryDashboardBean vo) throws Exception {
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				return dashBoardDao.getEcnAddCount(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				return dashBoardDao.getExtAddCount(vo);
			}
		}
		return dashBoardDao.getEcnAddCount(vo) + dashBoardDao.getExtAddCount(vo);
	}

	@Override
	public long getClosedCount(QueryDashboardBean vo) throws Exception {
		// 已处理的为单据关闭的问题数量，以创建时间及状态为结案来过滤
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				return dashBoardDao.getEcnClosedCount(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				return dashBoardDao.getExtCloseCount(vo);
			}
		}
		return dashBoardDao.getEcnClosedCount(vo) + dashBoardDao.getExtCloseCount(vo);
	}

	@Override
	public long getOverdueCount(QueryDashboardBean vo) throws Exception {
		// 已处理的为单据关闭的问题数量，以创建时间及状态为结案来过滤
		vo.setTime(UPDATEDATE);
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				return dashBoardDao.getEcnOverDueCount(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				return dashBoardDao.getExtOverDueCount(vo);
			}
		}
		return dashBoardDao.getEcnOverDueCount(vo) + dashBoardDao.getExtOverDueCount(vo);
	}

	@Override
	public long getProcessingCount(QueryDashboardBean vo) throws Exception {
		// 处理中的数量为，单据为未关闭的问题数量，创建时间过滤
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				return dashBoardDao.getEcnProcessingCount(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				return dashBoardDao.getExtProcessingCount(vo);
			}
		}
		return dashBoardDao.getEcnProcessingCount(vo) + dashBoardDao.getExtProcessingCount(vo);
	}

	@Override
	public JSONObject getDashBoardCount(QueryDashboardBean vo) throws Exception {
		long addNum = 0;
		long closeNum = 0;
		long processingNum = 0;
		long overdueNum = 0;
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				addNum = dashBoardDao.getEcnAddCount(vo);
				closeNum = dashBoardDao.getEcnClosedCount(vo);
				processingNum = dashBoardDao.getEcnProcessingCount(vo);
				overdueNum = dashBoardDao.getEcnOverDueCount(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				addNum = dashBoardDao.getExtAddCount(vo);
				closeNum = dashBoardDao.getExtCloseCount(vo);
				processingNum = dashBoardDao.getExtProcessingCount(vo);
				overdueNum = dashBoardDao.getExtOverDueCount(vo);
			}
		} else {
			addNum = dashBoardDao.getEcnAddCount(vo) + dashBoardDao.getExtAddCount(vo);
			closeNum = dashBoardDao.getEcnClosedCount(vo) + dashBoardDao.getExtCloseCount(vo);
			processingNum = dashBoardDao.getEcnProcessingCount(vo) + dashBoardDao.getExtProcessingCount(vo);
			overdueNum = dashBoardDao.getEcnOverDueCount(vo) + dashBoardDao.getExtOverDueCount(vo);
		}
		JSONObject result = new JSONObject();
		result.put("Add", addNum);
		result.put("Close", closeNum);
		result.put("OverDue", overdueNum);
		result.put("Processing", processingNum);
		return result;
	}

	@Override
	public JSONObject get72HourCloseRate(QueryDashboardBean vo) throws Exception {
		JSONObject addCountObject = getAddCountByTime(vo);
		JSONObject closedNum = get72HourClosedNum(vo);
		JSONObject rateObject = getRateObject(addCountObject, closedNum);
		return rateObject;
	}

	@Override
	public JSONObject getCloseRate(QueryDashboardBean vo) throws Exception {
		JSONObject addCountObject = getAddCountByTime(vo);
		JSONObject closedNum = getClosedNum(vo);
		JSONObject rateObject = getRateObject(addCountObject, closedNum);
		return rateObject;
	}

	@Override
	public JSONObject getTrentChart(QueryDashboardBean vo) throws Exception {
		//按月分组 获取对应的新增单据
		JSONObject addCountObject = getAddCountByTime(vo);
		JSONObject closedNum72 = get72HourClosedNum(vo);
		JSONObject closedNum = getClosedNum(vo);
		JSONObject onTimeNum = get24HourDisposalNum(vo);
		JSONObject close72HourObject = getRateObject(addCountObject, closedNum72);
		JSONObject closeObject = getRateObject(addCountObject, closedNum);
		JSONObject onTimeObject = getRateObject(addCountObject, onTimeNum);
		JSONObject result = new JSONObject(3);
		result.put("72HourClose", close72HourObject);
		result.put("Close", closeObject);
		result.put("onTimeDisposal", onTimeObject);
		return result;
	}

	public JSONObject get24HourDisposalNum(QueryDashboardBean vo) throws Exception {
		List<JSONObject> ecnCount = null;
		List<JSONObject> extCount = null;
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				ecnCount = dashBoardDao.getEcn24HourDisposalNum(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				extCount = dashBoardDao.getExt24HourDisposalNum(vo);
			}
		} else {
			ecnCount = dashBoardDao.getEcn24HourDisposalNum(vo);
			extCount = dashBoardDao.getExt24HourDisposalNum(vo);
		}
		return addJSONObjectNum(vo, ecnCount, extCount);
	}

	/**
	 * 获取按月分类的关闭数量
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private JSONObject getClosedNum(QueryDashboardBean vo) throws Exception {
		List<JSONObject> ecnCount = null;
		List<JSONObject> extCount = null;
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				ecnCount = dashBoardDao.getEcnCloseNum(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				extCount = dashBoardDao.getExtCloseNum(vo);
			}
		} else {
			ecnCount = dashBoardDao.getEcnCloseNum(vo);
			extCount = dashBoardDao.getExtCloseNum(vo);
		}
		return addJSONObjectNum(vo, ecnCount, extCount);
	}

	/**
	 * 获取按月分类的72小时关闭数量
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private JSONObject get72HourClosedNum(QueryDashboardBean vo) throws Exception {
		List<JSONObject> ecnCount = null;
		List<JSONObject> extCount = null;
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				ecnCount = dashBoardDao.getEcn72HourCloseNum(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				extCount = dashBoardDao.getExt72HourCloseNum(vo);
			}
		} else {
			ecnCount = dashBoardDao.getEcn72HourCloseNum(vo);
			extCount = dashBoardDao.getExt72HourCloseNum(vo);
		}
		return addJSONObjectNum(vo, ecnCount, extCount);
	}

	/**
	 * 获取按月分类的新增数量
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private JSONObject getAddCountByTime(QueryDashboardBean vo) throws Exception {
		List<JSONObject> ecnAddCount = null;
		List<JSONObject> extAddCount = null;
		if (vo.getItemType() != null && vo.getItemType().size() == 1) {
			String itemType = vo.getItemType().get(0);
			if (ECN.equals(itemType)) {
				//分组查询时，数据不存在的组，数据不能查询出来，要手动补齐
				ecnAddCount = dashBoardDao.getEcnAddCountGroupByTime(vo);
			} else if (EXTERNAL_DOCUMENT.equals(itemType)) {
				extAddCount = dashBoardDao.getExtAddCountGroupByTime(vo);
			}
		} else {
			ecnAddCount = dashBoardDao.getEcnAddCountGroupByTime(vo);
			extAddCount = dashBoardDao.getExtAddCountGroupByTime(vo);
		}
		return addJSONObjectNum(vo, ecnAddCount, extAddCount);
	}

	/**
	 * 相加 月份内的JSONObject
	 * 
	 * @param vo
	 * @return
	 */
	private JSONObject addJSONObjectNum(QueryDashboardBean vo, List<JSONObject> ecnList, List<JSONObject> extListt)
			throws Exception {
		// 如果 开始时间和 结束时间没有 指定时间 默认结束时间 填充此时， 开始时间填充 半年前时间
		if (ecnList == null) {
			ecnList = new ArrayList<>(0);
		}
		if (extListt == null) {
			extListt = new ArrayList<>(0);
		}
		String startTime = vo.getStartTime();
		String endTime = vo.getEndTime();
		if (vo.getEndTime() == null) {
			LocalDate now = LocalDate.now();
			//加一天是为了包含今天所有的
			now = now.plusDays(1);
			endTime = formatter.format(now);
		}
		if (vo.getStartTime() == null) {
			LocalDate now = LocalDate.now();
			LocalDate localDate = now.plusMonths(-6);
			startTime = formatter.format(localDate);
		}
		//不直接通过key获取月份集合，是因为时间可能为空
		List<String> betweenDate = DateUtil.getMonthBetweenDate(startTime, endTime);
		JSONObject result = new JSONObject(betweenDate.size(),true);
		for (String date : betweenDate) {
			Integer ecnCount = null;
			Integer extCount = null;
			//在java中经常使用for循环去判断是否存在，应为数据库取出的数据是集合形式
			List<JSONObject> ecn = ecnList.stream().filter(d -> date.equals(d.getString("date")))
					.collect(Collectors.toList());
			List<JSONObject> ext = extListt.stream().filter(d -> date.equals(d.getString("date")))
					.collect(Collectors.toList());
			ecnCount = (CollectionUtils.isNotEmpty(ecn)) ? ecn.get(0).getInteger("num") : null;
			extCount = (CollectionUtils.isNotEmpty(ext)) ? ext.get(0).getInteger("num") : null;
			if (ecnCount != null && extCount != null) {
				result.put(date, ecnCount + extCount);
			} else if (ecnCount != null && extCount == null) {
				result.put(date, ecnCount);
			} else if (ecnCount == null && extCount != null) {
				result.put(date, extCount);
			} else {
				result.put(date, null);
			}
		}
		return result;
	}

	/**
	 * 根据 分子 分母 生成 比率方法
	 * 
	 * @param addCountObject
	 * @param numObject
	 * @return
	 * @throws Exception
	 */
	private JSONObject getRateObject(JSONObject addCountObject, JSONObject numObject) throws Exception {
		//获得月份列表
		Set<String> date = addCountObject.keySet();
		// 排序 升序
		List<String> collect = date.stream().sorted(Comparator.comparing(String::trim)).collect(Collectors.toList());
		JSONObject result = new JSONObject(addCountObject.size(), true);
		for (String str : collect) {
			String addNum = addCountObject.getString(str);
			String num = numObject.getString(str);
			String rate = FormatUtil.divisionFormat(num, addNum);
			rate = (rate == null && "-".equals(rate)) ? "-" : rate + "%";
			JSONObject temp = new JSONObject(3);
			temp.put("total", addNum);
			temp.put("num", num);
			temp.put("rate", rate);
			result.put(str, temp);
		}
		return result;
	}
}
