package cn.jwis.qualityworkflow.modules.qims.service.imp;

import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.accumulation;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getBolaData;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getChartData;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getRate;
import static cn.jwis.qualityworkflow.modules.qims.util.QimsUtil.getTargetDate;
import static cn.jwis.qualityworkflow.util.DateUtil.isOverdue;
import static cn.jwis.qualityworkflow.util.FormatUtil.dateFormat;
import static cn.jwis.qualityworkflow.util.Title.CQADB;
import static cn.jwis.qualityworkflow.util.Title.CQADetailsDB;
import static cn.jwis.qualityworkflow.util.Title.CQADetailsExcel;
import static cn.jwis.qualityworkflow.util.Title.CQADetailsExcelName;
import static cn.jwis.qualityworkflow.util.Title.CQADetailsExcelUS;
import static cn.jwis.qualityworkflow.util.Title.CQAExcel;
import static cn.jwis.qualityworkflow.util.Title.CQAExcelName;
import static cn.jwis.qualityworkflow.util.Title.CQAExcelUS;
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
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.CqaDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.service.CqaDashBoardService;
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
public class CqaDashBoardServiceImpl extends BaseClass implements CqaDashBoardService {
	@Autowired
	CqaDashBoardMapper cqaDashBoardMapper;

	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;


	@Override
	public JSONObject getAllAndClose(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		List<JSONObject> list1 = cqaDashBoardMapper.getAllAndClose(startTime,endTime);
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
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		List<String> list1 = cqaDashBoardMapper.getOverdue(startTime,endTime);
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
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		int cycle = jsonObject.getIntValue("cycle");
		String temp = dateFormat(cycle,"business_datetime");
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		//查询分母
		List<JSONObject> dashBoardCloseSum = cqaDashBoardMapper.getDashBoardCloseSum(startTime, endTime, temp);
		//查询分子
		List<JSONObject> dashBoardCloseMolecule = cqaDashBoardMapper.getDashBoardCloseMolecule(startTime, endTime, temp);
		//分母格式封装
		JSONObject chartData = getChartData(dashBoardCloseSum,dashBoardCloseMolecule,betweenRuleDateDay);
		//目标值数据添加
		if (cycle<5){
			cycle = 5;
		}
		String target = blackDashBoardMapper.getTarget("CQA问题",cycle,"closing_rate");
		String target2 = blackDashBoardMapper.getTarget("CQA问题",cycle,"timely_closing_rate");
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
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay(cycle, startTime, endTime);
		List<JSONObject> dashBoardNotClose = cqaDashBoardMapper.getDashBoardNotClose(startTime, endTime, temp);
		JSONObject chartData = getChartData(dashBoardNotClose,betweenRuleDateDay);
		result.put("abscissa",betweenRuleDateDay);
		result.put("chartData",chartData);
		return result;
	}

	@Override
	public JSONObject getAllData() {
		JSONObject result = cqaDashBoardMapper.getAllData();
		Integer sum = result.getInteger("sum");
		Integer pass = result.getInteger("pass");
		Float rate = getRate(pass, sum);
		result.put("rate",rate);
		return result;
	}

	@Override
	public JSONObject getUnseasonalBola(String type) {
		JSONObject result =new JSONObject();
		List<JSONObject> unseasonalBola = cqaDashBoardMapper.getUnseasonalBola(type);
		getBolaData(result,unseasonalBola);
		return result;
	}

	@Override
	public List<QimsCqaInfo> getUnseasonalList(Integer page,Integer size) {
		if (isNotNull(page) && isNotNull(size)){
			page = (page-1)*size;
		}
		List<QimsCqaInfo> list = cqaDashBoardMapper.getUnseasonalList(page,size);
		return list;
	}

	@Override
	public Long getUnseasonalCount() {
		return cqaDashBoardMapper.getUnseasonalCount();
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		Map<String,String> title = Title.getTitle(request,CQADB,CQAExcel,CQAExcelUS);
		return title;
	}

	@Override
	public Map<String, String> getDetailsTitle(HttpServletRequest request) {
		Map<String,String> title = Title.getTitle(request,CQADetailsDB,CQADetailsExcel,CQADetailsExcelUS);
		return title;
	}

	@Override
	public void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request) {
		String language = request.getHeader("Language");
		List<QimsCqaInfo> qimsCqaInfos = cqaDashBoardMapper.getUnseasonalList(null,null);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (QimsCqaInfo qimsCqaInfo:qimsCqaInfos) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsCqaInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,CQAExcelName);
		String[] title = CQAExcel;
		String[] strings = CQADB;
		if ("en-US".equals(language)){
			title = CQAExcelUS;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<JSONObject> getDetails(JSONObject jsonObject) {
		List<JSONObject> result = new ArrayList<>();
		String department = jsonObject.getString("department");
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		//风险评估标准
		Float standardOfRa = jsonObject.getFloat("standardOfRa");
		//初步原因分析标准
		Float standardOfCh = jsonObject.getFloat("standardOfCh");
		//根本原因分析标准
		Float standardOfCa = jsonObject.getFloat("standardOfCa");
		//根本原因审核标准
		Float standardOfCau = jsonObject.getFloat("standardOfCau");
		List<JSONObject> blackDetails = cqaDashBoardMapper.getBlackDetails(department, startTime, endTime);
		//数据封装
		result = encapsulationData(blackDetails,standardOfRa,standardOfCh,standardOfCa,standardOfCau,department);
		return result;
	}

	@Override
	public void exportDetails(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) {
		List<JSONObject> list = getDetails(jsonObject);
		String language = request.getHeader("Language");
		ExcelUtil.setResponseHeader(response,CQADetailsExcelName);
		String[] title = CQADetailsExcel;
		String[] strings = CQADetailsDB;
		if ("en-US".equals(language)){
			title = CQADetailsExcelUS;
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
		List<JSONObject> unseasonalBola = cqaDashBoardMapper.getUnseasonalBola(type);
		ExcelUtil.setResponseHeader(response,"CQA.xlsx");
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
	private List<JSONObject> encapsulationData(List<JSONObject> list,Float standardOfRa,Float standardOfCh,Float standardOfCa,Float standardOfCau,String department){
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		List<JSONObject> result = new ArrayList<>();
		JSONObject total = new JSONObject();
        Map<String,JSONObject> map = new HashMap<>();
		Set<String> keySet = map.keySet();
		Integer totalOfRa = 0;
		Integer passOfRa = 0;
		Integer totalOfCh = 0;
		Integer passOfCh = 0;
		Integer totalOfCa = 0;
		Integer passOfCa = 0;
		Integer totalOfCau = 0;
		Integer passOfCau = 0;
		for (JSONObject jsonObject:list){
			String assignee = jsonObject.getString("assignee");
			String taskName = jsonObject.getString("task_name");
			String taskState = jsonObject.getString("task_state");
			Date createDate = jsonObject.getDate("create_date");
			Date updateDate = jsonObject.getDate("update_date");
			JSONObject object = new JSONObject();
			Integer ra = 0;
			Integer pra = 0;
			Integer ch = 0;
			Integer pch = 0;
			Integer ca = 0;
			Integer pca = 0;
			Integer cau = 0;
			Integer pcau = 0;
			if (keySet.contains(assignee)){
				object = map.get(assignee);
				ra = object.getInteger("totalOfRa");
				pra = object.getInteger("passOfRa");
				ch = object.getInteger("totalOfCh");
				pch = object.getInteger("passOfCh");
				ca = object.getInteger("totalOfCa");
				pca = object.getInteger("passOfCa");
				cau = object.getInteger("totalOfCau");
				pcau = object.getInteger("passOfCau");
			}
			if (isNull(taskState)){
				updateDate = new Date();
			}
			JSONObject accumulation = new JSONObject();
			if ("风险评估".equals(taskName)){
				accumulation = accumulation(totalOfRa, ra, createDate, updateDate, standardOfRa, passOfRa, pra,holiday,workOvertimeList);
			}else if ("初步原因分析".equals(taskName)){
				 accumulation = accumulation(totalOfCh, ch, createDate, updateDate, standardOfCh, passOfCh, pch,holiday,workOvertimeList);
			}else if ("根本原因分析".equals(taskName)){
				 accumulation = accumulation(totalOfCa, ca, createDate, updateDate, standardOfCa, passOfCa, pca,holiday,workOvertimeList);
			}else {
				 accumulation = accumulation(totalOfCau, cau, createDate, updateDate, standardOfCau, passOfCau, pcau,holiday,workOvertimeList);
			}
			Integer totalOfSum = accumulation.getInteger("totalOfSum");
			Integer totalOfPass = accumulation.getInteger("totalOfPass");
			Integer sum = accumulation.getInteger("sum");
			Integer pass = accumulation.getInteger("pass");
			if ("风险评估".equals(taskName)){
				totalOfRa = totalOfSum; passOfRa=totalOfPass; ra=sum; pra=pass;
			}else if ("初步原因分析".equals(taskName)){
				totalOfCh = totalOfSum; passOfCh=totalOfPass; ch=sum; pch=pass;
			}else if ("根本原因分析".equals(taskName)){
				totalOfCa = totalOfSum; passOfCa=totalOfPass; ca=sum; pca=pass;
			}else {
				totalOfCau = totalOfSum; passOfCau=totalOfPass; cau=sum; pcau=pass;
			}
			addFiled(object,assignee+"-Id",assignee,ra,pra,ch,pch,ca,pca,cau,pcau);
			addFiled(total,"总计",department,totalOfRa,passOfRa,totalOfCh,passOfCh,totalOfCa,passOfCa,totalOfCau,passOfCau);
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
	private void addFiled(JSONObject jsonObject, String itCode, String name,Integer totalOfRa,Integer passOfRa, Integer totalOfCh, Integer passOfCh, Integer totalOfCa,Integer passOfCa,Integer totalOfCau,Integer passOfCau){
		jsonObject.put("itCode",itCode);
		jsonObject.put("name",name);
		jsonObject.put("totalOfRa",totalOfRa);
		jsonObject.put("passOfRa",passOfRa);
		Float rateOfRa = getRate(passOfRa,totalOfRa);
		jsonObject.put("rateOfRa",rateOfRa);
		jsonObject.put("totalOfCh",totalOfCh);
		jsonObject.put("passOfCh",passOfCh);
		Float rateOfCh = getRate(passOfCh,totalOfCh);
		jsonObject.put("rateOfCh",rateOfCh);
		jsonObject.put("totalOfCa",totalOfCa);
		jsonObject.put("passOfCa",passOfCa);
		Float rateOfCa = getRate(passOfCa,totalOfCa);
		jsonObject.put("rateOfCa",rateOfCa);
		jsonObject.put("totalOfCau",totalOfCau);
		jsonObject.put("passOfCau",passOfCau);
		Float rateOfCau = getRate(passOfCau,totalOfCau);
		jsonObject.put("rateOfCau",rateOfCau);
	}
}