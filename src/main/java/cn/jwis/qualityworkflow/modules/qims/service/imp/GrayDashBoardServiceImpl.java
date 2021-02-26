package cn.jwis.qualityworkflow.modules.qims.service.imp;

import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.accumulation;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getBolaData;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getChartData;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getRate;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getTargetDate;
import static cn.jwis.qualityworkflow.util.DateUtil.isOverdue;
import static cn.jwis.qualityworkflow.util.FormatUtil.dateFormat;
import static cn.jwis.qualityworkflow.util.Title.GrayDB;
import static cn.jwis.qualityworkflow.util.Title.GrayDetailsDB;
import static cn.jwis.qualityworkflow.util.Title.GrayDetailsExcel;
import static cn.jwis.qualityworkflow.util.Title.GrayDetailsExcelName;
import static cn.jwis.qualityworkflow.util.Title.GrayDetailsExcelUS;
import static cn.jwis.qualityworkflow.util.Title.GrayExcel;
import static cn.jwis.qualityworkflow.util.Title.GrayExcelName;
import static cn.jwis.qualityworkflow.util.Title.GrayExcelUS;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaDb;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaDepartmentExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaDepartmentExcelUs;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaPersonExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsBolaPersonExcelUs;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.GrayDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.service.GrayDashBoardService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:14
 */
@Service
public class GrayDashBoardServiceImpl extends BaseClass implements GrayDashBoardService {
	@Autowired
	GrayDashBoardMapper grayDashBoardMapper;

	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;


	@Override
	public JSONObject getAllAndClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		List<JSONObject> list1 = grayDashBoardMapper.getAllAndClose(list,startTime,endTime);
		Integer all = 0;
		for (JSONObject json:list1) {
			int total = json.getIntValue("total");
			all+=total;
			result.put(json.getString("type"),total);
		}
		result.put("All",all);
		return result;
	}

	@Override
	public JSONObject getOverdue(JSONObject jsonObject) throws ParseException {
		JSONObject result = new JSONObject();
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		List<String> list1 = grayDashBoardMapper.getOverdue(list,startTime,endTime);
		//获取超期标准
		Integer temp = 24;
		//判断日期是否超期
		int i =0;
		for (String date:list1) {
			if (isOverdue(date,temp)){
				i++;
			}
		}
		result.put("overdue",i);
		return result;
	}

	@Override
	public JSONObject getDashBoardClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		int cycle = jsonObject.getIntValue("cycle");
		String temp = dateFormat(cycle,"business_datetime");
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		//查询分母
		List<JSONObject> dashBoardCloseSum = grayDashBoardMapper.getDashBoardCloseSum(list,startTime, endTime, temp);
		//查询分子
		List<JSONObject> dashBoardCloseMolecule = grayDashBoardMapper.getDashBoardCloseMolecule(list,startTime, endTime, temp);
		//分母格式封装
		JSONObject chartData = getChartData(dashBoardCloseSum,dashBoardCloseMolecule,betweenRuleDateDay);
		//目标值数据添加
		if (cycle<5){
			cycle = 5;
		}
		String target = blackDashBoardMapper.getTarget("灰色问题",cycle,"closing_rate");
		String target2 = blackDashBoardMapper.getTarget("灰色问题",cycle,"timely_closing_rate");
		chartData = getTargetDate(chartData,betweenRuleDateDay,target,target2);
		result.put("abscissa",betweenRuleDateDay);
		result.put("chartData",chartData);
		return result;
	}

	@Override
	public JSONObject getDashBoardNotClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		int cycle = jsonObject.getIntValue("cycle");
		String temp = dateFormat(cycle,"business_datetime");
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph_parting"));
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		List<JSONObject> dashBoardNotClose = grayDashBoardMapper.getDashBoardNotClose(list,startTime, endTime, temp);
		JSONObject chartData = getChartData(dashBoardNotClose,betweenRuleDateDay);
		result.put("abscissa",betweenRuleDateDay);
		result.put("chartData",chartData);
		return result;
	}

	@Override
	public JSONObject getAllData() {
		JSONObject result = grayDashBoardMapper.getAllData();
		Integer sum = result.getInteger("sum");
		Integer pass = result.getInteger("pass");
		Float rate = getRate(pass, sum);
		result.put("rate",rate);
		return result;
	}

	@Override
	public JSONObject getUnseasonalBola(String type) {
		JSONObject result =new JSONObject();
		List<JSONObject> unseasonalBola = grayDashBoardMapper.getUnseasonalBola(type);
		getBolaData(result,unseasonalBola);
		return result;
	}

	@Override
	public List<JSONObject> getUnseasonalList(Integer page, Integer size) {
		if (isNotNull(page) && isNotNull(size)){
			page = (page-1)*size;
		}
		List<JSONObject> list = grayDashBoardMapper.getUnseasonalList(page,size);
		return list;
	}

	@Override
	public Long getUnseasonalCount() {
		return grayDashBoardMapper.getUnseasonalCount();
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		Map<String,String> title = Title.getTitle(request,GrayDB,GrayExcel,GrayExcelUS);
		return title;
	}

	@Override
	public void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request) {
		String language = request.getHeader("Language");
		List<JSONObject> qimsCqaInfos = grayDashBoardMapper.getUnseasonalList(null,null);
		ExcelUtil.setResponseHeader(response,GrayExcelName);
		String[] title = GrayExcel;
		String[] strings = GrayDB;
		if ("en-US".equals(language)){
			title = GrayExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(qimsCqaInfos,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, String> getDetailsTitle(HttpServletRequest request) {
		Map<String,String> title = Title.getTitle(request,GrayDetailsDB,GrayDetailsExcel,GrayDetailsExcelUS);
		return title;
	}


	@Override
	public List<JSONObject> getDetails(JSONObject jsonObject) {
		List<JSONObject> result = new ArrayList<>();
		String department = jsonObject.getString("department");
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		//回复及时标准
		Float standardOfRp = jsonObject.getFloat("standardOfRp");
		List<JSONObject> blackDetails = grayDashBoardMapper.getBlackDetails(department, startTime, endTime);
		//数据封装
		result = encapsulationData(blackDetails,standardOfRp,department);
		return result;
	}

	@Override
	public void exportDetails(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) {
		List<JSONObject> list = getDetails(jsonObject);
		String language = request.getHeader("Language");
		ExcelUtil.setResponseHeader(response,GrayDetailsExcelName);
		String[] title = GrayDetailsExcel;
		String[] strings = GrayDetailsDB;
		if ("en-US".equals(language)){
			title = GrayDetailsExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(list,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportUnseasonalBola(String type, HttpServletResponse response, HttpServletRequest request) {
		String language = request.getHeader("Language");
		List<JSONObject> unseasonalBola = grayDashBoardMapper.getUnseasonalBola(type);
		ExcelUtil.setResponseHeader(response,"Gray.xlsx");
		String[] title = new String[2];
		if ("assigneer_department".equals(type)){
			title = QimsBolaDepartmentExcel;
			if ("en-US".equals(language)){
				title = QimsBolaDepartmentExcelUs;
			}
		}
		if ("assigneer".equals(type)){
			title = QimsBolaPersonExcel;
			if ("en-US".equals(language)){
				title = QimsBolaPersonExcelUs;
			}
		}
		String[] strings = QimsBolaDb;
		Workbook workbook = ExcelUtil.exporSimple(unseasonalBola,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * @Author yuyangyang
     * @Description 详情接口封装
     * @Date  2020/5/30  17:39
     * @Param
     * @return
     */
	private List<JSONObject> encapsulationData(List<JSONObject> list,Float standardOfRp,String department){
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		List<JSONObject> result = new ArrayList<>();
		JSONObject total = new JSONObject();
        Map<String,JSONObject> map = new HashMap<>();
		Set<String> keySet = map.keySet();
		Integer totalOfRp = 0;
		Integer passOfRp = 0;
		for (JSONObject jsonObject:list){
			String assignee = jsonObject.getString("assigneer");
			Date createDate = jsonObject.getDate("startTime");
			Date updateDate = jsonObject.getDate("endTime");
			JSONObject object = new JSONObject();
			Integer rp = 0;
			Integer prp = 0;
			if (keySet.contains(assignee)){
				object = map.get(assignee);
				rp = object.getInteger("totalOfRp");
				prp = object.getInteger("passOfRp");
			}
			JSONObject accumulation = new JSONObject();
			accumulation = accumulation(totalOfRp, rp, createDate, updateDate, standardOfRp, passOfRp, prp,holiday,workOvertimeList);
			Integer totalOfSum = accumulation.getInteger("totalOfSum");
			Integer totalOfPass = accumulation.getInteger("totalOfPass");
			Integer sum = accumulation.getInteger("sum");
			Integer pass = accumulation.getInteger("pass");
	        totalOfRp = totalOfSum; passOfRp=totalOfPass; rp=sum; prp=pass;
			addFiled(object,assignee+"-Id",assignee,rp,prp);
			addFiled(total,"总计",department,totalOfRp,passOfRp);
			map.put(assignee,object);
		}
		result.add(total);
		result.addAll(map.values());
		return  result;
	}
    /**
     * @Author yuyangyang
     * @Description 详细设计JSON拼装
     * @Date  2020/6/1  10:53
     * @Param
     * @return
     */
	private void addFiled(JSONObject jsonObject, String itCode, String name,Integer totalOfRp,Integer passOfRp){
		jsonObject.put("itCode",itCode);
		jsonObject.put("name",name);
		jsonObject.put("totalOfRp",totalOfRp);
		jsonObject.put("passOfRp",passOfRp);
		Float rateOfRp = getRate(passOfRp,totalOfRp);
		jsonObject.put("rateOfRp",rateOfRp);
	}
}