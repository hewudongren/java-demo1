package cn.jwis.qualityworkflow.modules.qims.service.imp;

import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.accumulation;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getBolaData;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getChartData;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getRate;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getTargetDate;
import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.BeanUtil.transformationJson2;
import static cn.jwis.qualityworkflow.util.DateUtil.isOverdue;
import static cn.jwis.qualityworkflow.util.FormatUtil.dateFormat;
import static cn.jwis.qualityworkflow.util.Title.BlackBatchDB;
import static cn.jwis.qualityworkflow.util.Title.BlackBatchExcel;
import static cn.jwis.qualityworkflow.util.Title.BlackBatchExcelName;
import static cn.jwis.qualityworkflow.util.Title.BlackBatchExcelUS;
import static cn.jwis.qualityworkflow.util.Title.BlackDB;
import static cn.jwis.qualityworkflow.util.Title.BlackDetailsDB;
import static cn.jwis.qualityworkflow.util.Title.BlackDetailsExcel;
import static cn.jwis.qualityworkflow.util.Title.BlackDetailsExcelName;
import static cn.jwis.qualityworkflow.util.Title.BlackDetailsExcelUS;
import static cn.jwis.qualityworkflow.util.Title.BlackExcel;
import static cn.jwis.qualityworkflow.util.Title.BlackExcelName;
import static cn.jwis.qualityworkflow.util.Title.BlackExcelUS;
import static cn.jwis.qualityworkflow.util.Title.HolidayDB;
import static cn.jwis.qualityworkflow.util.Title.HolidayExcel;
import static cn.jwis.qualityworkflow.util.Title.HolidayExcelUS;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackDB;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackExcelName;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackExcelUS;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaDb;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaDepartmentExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaDepartmentExcelUs;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaPersonExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaPersonExcelUs;
import static cn.jwis.qualityworkflow.util.Title.QimsTargetDb;
import static cn.jwis.qualityworkflow.util.Title.QimsTargetDb2;
import static cn.jwis.qualityworkflow.util.Title.QimsTargetExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsTargetExcel2;
import static cn.jwis.qualityworkflow.util.Title.QimsTargetExcelUs;
import static cn.jwis.qualityworkflow.util.Title.QimsTargetExcelUs2;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.qims.bean.BlackDashSearch;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetSearch;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsTargetInfoMapper;
import cn.jwis.qualityworkflow.modules.qims.service.BlackDashBoardService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.FormatUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:14
 */
@Service
public class BlackDashBoardServiceImpl extends BaseClass implements BlackDashBoardService {
	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	QimsTargetInfoMapper qimsTargetInfoMapper;

	@Autowired
	CommonMapper commonMapper;

	@Override
	public JSONObject getAllAndClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		List<JSONObject> list1 = blackDashBoardMapper.getAllAndClose(list, startTime, endTime);
		Integer all = 0;
		for (JSONObject json : list1) {
			int total = json.getIntValue("total");
			all += total;
			result.put(json.getString("type"), total);
		}
		result.put("All", all);
		return result;
	}

	@Override
	public JSONObject getOverdue(JSONObject jsonObject) throws ParseException {
		JSONObject result = new JSONObject();
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		List<String> list1 = blackDashBoardMapper.getOverdue(list, startTime, endTime);
		// 获取超期标准
		Integer temp = 24;
		// 判断日期是否超期
		int i = 0;
		for (String date : list1) {
			if (isOverdue(date, temp)) {
				i++;
			}
		}
		result.put("overdue", i);
		return result;
	}

	@Override
	public JSONObject getDashBoardClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		int cycle = jsonObject.getIntValue("cycle");
		String temp = dateFormat(cycle, "business_datetime");
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		// 查询分母
		List<JSONObject> dashBoardCloseSum = blackDashBoardMapper.getDashBoardCloseSum(list, startTime, endTime, temp);
		// 查询分子
		List<JSONObject> dashBoardCloseMolecule = blackDashBoardMapper.getDashBoardCloseMolecule(list, startTime,
				endTime, temp);
		// 分母格式封装
		JSONObject chartData = getChartData(dashBoardCloseSum, dashBoardCloseMolecule, betweenRuleDateDay);
		// 目标值数据添加
		if (cycle < 5) {
			cycle = 5;
		}
		String target = blackDashBoardMapper.getTarget("黑色问题", cycle, "closing_rate");
		String target2 = blackDashBoardMapper.getTarget("黑色问题", cycle, "timely_closing_rate");
		chartData = getTargetDate(chartData, betweenRuleDateDay, target, target2);
		result.put("abscissa", betweenRuleDateDay);
		result.put("chartData", chartData);
		return result;
	}

	@Override
	public JSONObject getDashBoardNotClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		int cycle = jsonObject.getIntValue("cycle");
		String temp = dateFormat(cycle, "business_datetime");
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		List<JSONObject> dashBoardNotClose = blackDashBoardMapper.getDashBoardNotClose(list, startTime, endTime, temp);
		JSONObject chartData = getChartData(dashBoardNotClose, betweenRuleDateDay);
		result.put("abscissa", betweenRuleDateDay);
		result.put("chartData", chartData);
		return result;
	}

	@Override
	public JSONObject getAllData() {
		JSONObject result = blackDashBoardMapper.getAllData();
		Integer sum = result.getInteger("sum");
		Integer pass = result.getInteger("pass");
		Float rate = getRate(pass, sum);
		result.put("rate", rate);
		return result;
	}

	@Override
	public JSONObject getUnseasonalBola(String type) {
		JSONObject result = new JSONObject();
		List<JSONObject> unseasonalBola = blackDashBoardMapper.getUnseasonalBola(type);
		getBolaData(result, unseasonalBola);
		return result;
	}

	@Override
	public List<QimsBlackInfo> getUnseasonalList(Integer page, Integer size) {
		if (isNotNull(page) && isNotNull(size)) {
			page = (page - 1) * size;
		}
		List<QimsBlackInfo> list = blackDashBoardMapper.getUnseasonalList(page, size);
		return list;
	}

	@Override
	public Long getUnseasonalCount() {
		return blackDashBoardMapper.getUnseasonalCount();
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		Map<String, String> title = Title.getTitle(request, BlackDB, BlackExcel, BlackExcelUS);
		return title;
	}

	@Override
	public Map<String, String> getDetailsTitle(HttpServletRequest request) {
		Map<String, String> title = Title.getTitle(request, BlackDetailsDB, BlackDetailsExcel, BlackDetailsExcelUS);
		return title;
	}

	@Override
	public void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request) {
		String language = request.getHeader("Language");
		List<QimsBlackInfo> qimsBlackInfos = blackDashBoardMapper.getUnseasonalList(null, null);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (QimsBlackInfo qimsBlackInfo : qimsBlackInfos) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsBlackInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, BlackExcelName);
		String[] title = BlackExcel;
		String[] strings = BlackDB;
		if ("en-US".equals(language)) {
			title = BlackExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<JSONObject> getBlackDetails(JSONObject jsonObject) {
		List<JSONObject> result = new ArrayList<>();
		String department = jsonObject.getString("department");
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		// 初步原因分析标准
		Float standardOfCh = jsonObject.getFloat("standardOfCh");
		// 根本原因分析标准
		Float standardOfCa = jsonObject.getFloat("standardOfCa");
		// 根本原因审核标准
		Float standardOfCau = jsonObject.getFloat("standardOfCau");
		List<JSONObject> blackDetails = blackDashBoardMapper.getBlackDetails(department, startTime, endTime);
		// 数据封装
		result = encapsulationData(blackDetails, standardOfCh, standardOfCa, standardOfCau, department);
		return result;
	}

	@Override
	public void exportBlackDetails(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) {
		List<JSONObject> list = getBlackDetails(jsonObject);
		String language = request.getHeader("Language");
		ExcelUtil.setResponseHeader(response, BlackDetailsExcelName);
		String[] title = BlackDetailsExcel;
		String[] strings = BlackDetailsDB;
		if ("en-US".equals(language)) {
			title = BlackDetailsExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(list, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getBatchProblem(BlackDashSearch dashSearch) {
		JSONObject result = new JSONObject();
		String startTime = dashSearch.getStartTime();
		String endTime = dashSearch.getEndTime();
		int cycle = dashSearch.getCycle();
		String temp = dateFormat(cycle, "business_datetime");
		dashSearch.setTemp(temp);
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		List<JSONObject> batchProblemList = blackDashBoardMapper.getBatchProblem(dashSearch);
		JSONObject chartData = getChartData2(batchProblemList, betweenRuleDateDay);
		result.put("abscissa", betweenRuleDateDay);
		result.put("chartData", chartData);
		return result;
	}

	@Override
	public void exportBatchProblem(HttpServletResponse response, HttpServletRequest request,
			BlackDashSearch dashSearch) {
		String language = request.getHeader("Language");
		List<QimsBlackInfo> qimsBlackInfos = blackDashBoardMapper.exportBatchProblem(dashSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (QimsBlackInfo qimsBlackInfo : qimsBlackInfos) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsBlackInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, QimsBlackExcelName);
		String[] title = QimsBlackExcel;
		String[] strings = QimsBlackDB;
		if ("en-US".equals(language)) {
			title = QimsBlackExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportBatchProblemTotal(HttpServletResponse response, HttpServletRequest request,
			BlackDashSearch dashSearch) {
		String language = request.getHeader("Language");
		int cycle = dashSearch.getCycle();
		String temp = dateFormat(cycle, "business_datetime");
		dashSearch.setTemp(temp);
		List<JSONObject> batchProblemList = blackDashBoardMapper.getBatchProblem(dashSearch);
		String modelCategory = FormatUtil.listToString(dashSearch.getModelCategorys());
		String manufactureType = FormatUtil.listToString(dashSearch.getManufactureTypes());
		String item = FormatUtil.listToString(dashSearch.getItems());
		String model = FormatUtil.listToString(dashSearch.getModels());
		String defectType = FormatUtil.listToString(dashSearch.getDefectTypes());
		String cycleValue = getCycle(dashSearch.getCycle());
		for (JSONObject json : batchProblemList) {
			json.put("modelCategory", modelCategory);
			json.put("manufactureType", manufactureType);
			json.put("item", item);
			json.put("model", model);
			json.put("defectType", defectType);
			json.put("cycleValue", cycleValue);
		}
		ExcelUtil.setResponseHeader(response, BlackBatchExcelName);
		String[] title = BlackBatchExcel;
		String[] strings = BlackBatchDB;
		if ("en-US".equals(language)) {
			title = BlackBatchExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(batchProblemList, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 保存节假日信息
	 * @Date 2020/6/23 11:10
	 * @Param
	 * @return
	 */
	@Override
	public void saveHoliday(JSONObject jsonObject) {
		String date = jsonObject.getString("date");
		String type = jsonObject.getString("type");
		String remarks = jsonObject.getString("remarks");
		String id = String.valueOf(idGeneratorRunner.nextId());
		String creator = getCurrentUserName();
		JSONObject holiday = blackDashBoardMapper.getHolidayByDate(date);
		if (isNotNull(holiday)) {
			throw new BaseException("日期重复,请勿重新创建");
		} else {
			blackDashBoardMapper.saveHoliday(id, date, type, creator, remarks);
		}
	}

	@Override
	public void updateHoliday(JSONObject jsonObject) {
		String date = jsonObject.getString("date");
		String type = jsonObject.getString("type");
		String remarks = jsonObject.getString("remarks");
		String id = jsonObject.getString("id");
		JSONObject holiday = blackDashBoardMapper.getHolidayByDate(date);
		if (isNotNull(holiday)) {
			String id1 = holiday.getString("id");
			if (id.equals(id1)) {
				blackDashBoardMapper.updateHoliday(id, date, type, remarks);
			} else {
				throw new BaseException("日期重复,请更改日期");
			}
		} else {
			blackDashBoardMapper.updateHoliday(id, date, type, remarks);
		}
	}

	@Override
	public void deleteHoliday(String id) {
		blackDashBoardMapper.deleteHoliday(id);
	}

	@Override
	public List<JSONObject> getHoliday(Integer page, Integer size) {
		if (isNotNull(page) && isNotNull(size)) {
			page = (page - 1) * size;
		}
		return blackDashBoardMapper.getHolidayList(page, size);
	}

	@Override
	public Map<String, String> getHolidayTitle(HttpServletRequest request) {
		Map<String, String> title = Title.getTitle(request, HolidayDB, HolidayExcel, HolidayExcelUS);
		return title;
	}

	@Override
	public Long getHolidayCount() {
		return blackDashBoardMapper.getHolidayCount();
	}

	@Override
	public void saveTarget(QimsTargetInfo qimsTargetInfo) {
		qimsTargetInfo.setId(String.valueOf(idGeneratorRunner.nextId()));
		qimsTargetInfo.setCreator(getCurrentUserName());
		qimsTargetInfoMapper.insert(qimsTargetInfo);
	}

	@Override
	public void updateTarget(QimsTargetInfo qimsTargetInfo) {
		qimsTargetInfoMapper.updateByPrimaryKey(qimsTargetInfo);
	}

	@Override
	public List<QimsTargetInfo> getTarget(QimsTargetSearch qimsTargetSearch) throws Exception {
		pageChange(qimsTargetSearch);
		String endTime = qimsTargetSearch.getEndTime();
		endTime = DateUtil.plusOneDay(endTime);
		qimsTargetSearch.setEndTime(endTime);
		return qimsTargetInfoMapper.getTarget(qimsTargetSearch);
	}

	@Override
	public Long getTargetCount(QimsTargetSearch qimsTargetSearch) {
		String endTime = qimsTargetSearch.getEndTime();
		endTime = DateUtil.plusOneDay(endTime);
		qimsTargetSearch.setEndTime(endTime);
		return qimsTargetInfoMapper.getTargetCount(qimsTargetSearch);
	}

	@Override
	public Map<String, String> getTargetTitle(HttpServletRequest request) {
		return Title.getTitle(request, QimsTargetDb, QimsTargetExcel, QimsTargetExcelUs);
	}

	@Override
	public void exportTarget(HttpServletResponse response, HttpServletRequest request) {
		String language = request.getHeader("Language");
		ExcelUtil.setResponseHeader(response, "TargetInfo.xlsx");
		String[] title = QimsTargetExcel2;
		String[] strings = QimsTargetDb2;
		if ("en-US".equals(language)) {
			title = QimsTargetExcelUs2;
		}
		Workbook workbook = ExcelUtil.exporSimple(null, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTarget(String id) {
		qimsTargetInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public Boolean importTarget(MultipartFile file, HttpServletRequest request) {
		Boolean flag = false;
		File file1 = null;
		String language = request.getHeader("Language");
		try {
			file1 = FileUtil.multipartFileToFile(file);
			List<String> title = new ArrayList<>();
			String[] strings = QimsTargetExcel2;
			if ("en-US".equals(language)) {
				strings = QimsTargetExcelUs2;
			}
			title = Arrays.asList(QimsTargetDb2);
			List<JSONObject> list = ExcelUtil.readExcel(file1, title, strings);
			if (isNull(list)) {
				throw new BaseException("请勿导入空表");
			}
			List<JSONObject> parameterList = commonMapper.getTransferParameter("qims_target_info");
			transformationJson2(list, parameterList);
			for (JSONObject temp : list) {
				temp.put("id", String.valueOf(idGeneratorRunner.nextId()));
				temp.put("creator", getCurrentUserName());
				List<JSONObject> list1 = JSONObjectUtil.separate(temp);
				commonMapper.saveDate(list1, "qims_target_info");
				flag = true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		} finally {
			FileUtil.deleteDir(file1);
		}
		return flag;
	}

	@Override
	public void exportUnseasonalBola(String type, HttpServletResponse response, HttpServletRequest request) {
		String language = request.getHeader("Language");
		List<JSONObject> unseasonalBola = blackDashBoardMapper.getUnseasonalBola(type);
		ExcelUtil.setResponseHeader(response, "Black.xlsx");
		String[] title = new String[2];
		if ("assigneer_department".equals(type)) {
			title = QimsBolaDepartmentExcel;
			if ("en-US".equals(language)) {
				title = QimsBolaDepartmentExcelUs;
			}
		}
		if ("assigneer".equals(type)) {
			title = QimsBolaPersonExcel;
			if ("en-US".equals(language)) {
				title = QimsBolaPersonExcelUs;
			}
		}
		String[] strings = QimsBolaDb;
		Workbook workbook = ExcelUtil.exporSimple(unseasonalBola, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getDepartmentValue(String type) {
		List<String> result = new ArrayList<>();
		if ("黑色问题".equals(type)) {
			result = blackDashBoardMapper.getDepartmentValue("QIMSBLACK");
		} else if ("CQA问题".equals(type)) {
			result = blackDashBoardMapper.getDepartmentValue("QIMSCQA");
		} else {
			result = blackDashBoardMapper.getGrayDepartment();
		}
		return result;
	}

	@Override
	public JSONObject getStandardValue(String type) {
		JSONObject result = new JSONObject();
		List<JSONObject> standardValue = blackDashBoardMapper.getStandardValue(type);
		for (JSONObject temp : standardValue) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", temp.getString("id"));
			jsonObject.put("value", temp.getFloat("standard_value"));
			result.put(temp.getString("standard_name"), jsonObject);
		}
		return result;
	}

	@Override
	public void updateStandardValue(List<JSONObject> list) {
		for (JSONObject temp : list) {
			String id = temp.getString("id");
			Float value = temp.getFloat("value");
			blackDashBoardMapper.updateStandardValue(id, value, getCurrentUserName());

		}
	}

	@Override
	public List<JSONObject> getLtList() {
		return blackDashBoardMapper.getLtList();
	}

	@Override
	public void updateLtList(JSONObject jsonObject) {
		String standard = jsonObject.getString("standard");
		String id = jsonObject.getString("id");
		String updator = getCurrentUserName();
		blackDashBoardMapper.updateLtList(standard, id, updator);
	}

	/**
	 * @Author yuyangyang
	 * @Description //TODO
	 * @Date 2020/6/1 17:08
	 * @Param
	 * @return
	 */
	private JSONObject getChartData2(List<JSONObject> batchProblemList, Set<String> betweenRuleDateDay) {
		JSONObject result = new JSONObject();
		Set<String> processSet = new LinkedHashSet<>();
		for (JSONObject jsonObject : batchProblemList) {
			processSet.add(jsonObject.getString("process"));
		}
		for (String process : processSet) {
			List<JSONObject> list = new ArrayList<>();
			for (String date : betweenRuleDateDay) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("total", "");
				jsonObject.put("pass", "");
				jsonObject.put("rate", "");
				jsonObject.put("abscissa", date);
				for (JSONObject temp : batchProblemList) {
					if (date.equals(temp.getString("abscissa")) && process.equals(temp.getString("process"))) {
						jsonObject.put("total", temp.getString("sum"));
						jsonObject.put("pass", temp.getString("molecule"));
						jsonObject.put("rate", temp.getString("rate"));
					}
				}
				list.add(jsonObject);
			}
			result.put(process, list);
		}
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 详情接口封装
	 * @Date 2020/5/30 17:39
	 * @Param
	 * @return
	 */
	private List<JSONObject> encapsulationData(List<JSONObject> list, Float standardOfCh, Float standardOfCa,
			Float standardOfCau, String department) {
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		List<JSONObject> result = new ArrayList<>();
		JSONObject total = new JSONObject();
		Map<String, JSONObject> map = new HashMap<>();
		Set<String> keySet = map.keySet();
		Integer totalOfCh = 0;
		Integer passOfCh = 0;
		Integer totalOfCa = 0;
		Integer passOfCa = 0;
		Integer totalOfCau = 0;
		Integer passOfCau = 0;
		for (JSONObject jsonObject : list) {
			String assignee = jsonObject.getString("assignee");
			String taskName = jsonObject.getString("task_name");
			String taskState = jsonObject.getString("task_state");
			Date createDate = jsonObject.getDate("create_date");
			Date updateDate = jsonObject.getDate("update_date");
			JSONObject object = new JSONObject();
			Integer ch = 0;
			Integer pch = 0;
			Integer ca = 0;
			Integer pca = 0;
			Integer cau = 0;
			Integer pcau = 0;
			if (keySet.contains(assignee)) {
				object = map.get(assignee);
				ch = object.getInteger("totalOfCh");
				pch = object.getInteger("passOfCh");
				ca = object.getInteger("totalOfCa");
				pca = object.getInteger("passOfCa");
				cau = object.getInteger("totalOfCau");
				pcau = object.getInteger("passOfCau");
			}
			if (isNull(taskState)) {
				updateDate = new Date();
			}
			JSONObject accumulation = new JSONObject();
			if ("初步原因分析".equals(taskName)) {
				accumulation = accumulation(totalOfCh, ch, createDate, updateDate, standardOfCh, passOfCh, pch, holiday,
						workOvertimeList);
			} else if ("根本原因分析".equals(taskName)) {
				accumulation = accumulation(totalOfCa, ca, createDate, updateDate, standardOfCa, passOfCa, pca, holiday,
						workOvertimeList);
			} else {
				accumulation = accumulation(totalOfCau, cau, createDate, updateDate, standardOfCau, passOfCau, pcau,
						holiday, workOvertimeList);
			}
			Integer totalOfSum = accumulation.getInteger("totalOfSum");
			Integer totalOfPass = accumulation.getInteger("totalOfPass");
			Integer sum = accumulation.getInteger("sum");
			Integer pass = accumulation.getInteger("pass");
			if ("初步原因分析".equals(taskName)) {
				totalOfCh = totalOfSum;
				passOfCh = totalOfPass;
				ch = sum;
				pch = pass;
			} else if ("根本原因分析".equals(taskName)) {
				totalOfCa = totalOfSum;
				passOfCa = totalOfPass;
				ca = sum;
				pca = pass;
			} else {
				totalOfCau = totalOfSum;
				passOfCau = totalOfPass;
				cau = sum;
				pcau = pass;
			}
			addFiled(object, assignee + "-Id", assignee, ch, pch, ca, pca, cau, pcau);
			addFiled(total, "总计", department, totalOfCh, passOfCh, totalOfCa, passOfCa, totalOfCau, passOfCau);
			map.put(assignee, object);
		}
		result.add(total);
		result.addAll(map.values());
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 详细设计JSON拼装
	 * @Date 2020/6/1 10:53
	 * @Param
	 * @return
	 */
	private void addFiled(JSONObject jsonObject, String itCode, String name, Integer totalOfCh, Integer passOfCh,
			Integer totalOfCa, Integer passOfCa, Integer totalOfCau, Integer passOfCau) {
		jsonObject.put("itCode", itCode);
		jsonObject.put("name", name);
		jsonObject.put("totalOfCh", totalOfCh);
		jsonObject.put("passOfCh", passOfCh);
		Float rateOfCh = getRate(passOfCh, totalOfCh);
		jsonObject.put("rateOfCh", rateOfCh);
		jsonObject.put("totalOfCa", totalOfCa);
		jsonObject.put("passOfCa", passOfCa);
		Float rateOfCa = getRate(passOfCa, totalOfCa);
		jsonObject.put("rateOfCa", rateOfCa);
		jsonObject.put("totalOfCau", totalOfCau);
		jsonObject.put("passOfCau", passOfCau);
		Float rateOfCau = getRate(passOfCau, totalOfCau);
		jsonObject.put("rateOfCau", rateOfCau);
	}

	private String getCycle(int cycle) {
		String temp = null;
		switch (cycle) {
		case 3:
			temp = "日";
			break;
		case 4:
			temp = "周";
			break;
		case 5:
			temp = "月";
			break;
		case 6:
			temp = "季度";
			break;
		case 7:
			temp = "半年";
			break;
		case 8:
			temp = "年";
			break;
		default:
			temp = null;
			break;
		}
		return temp;
	}
}