package cn.jwis.qualityworkflow.modules.homePage.service.imp;

import static cn.jwis.qualityworkflow.util.DateUtil.getBetweenRuleDateDay;
import static cn.jwis.qualityworkflow.util.DateUtil.getEndTime;
import static cn.jwis.qualityworkflow.util.DateUtil.getStartTime;
import static cn.jwis.qualityworkflow.util.Title.WorkFlowExcelUs;
import static cn.jwis.qualityworkflow.util.Title.workFlowDb;
import static cn.jwis.qualityworkflow.util.Title.workFlowExcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.modules.apqp.service.ApqpDashBoardService;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryDashboardBean;
import cn.jwis.qualityworkflow.modules.ecn.service.EcnDashBoardService;
import cn.jwis.qualityworkflow.modules.homePage.dao.HomePageMapper;
import cn.jwis.qualityworkflow.modules.homePage.service.HomePageService;
import cn.jwis.qualityworkflow.modules.ipqc.service.IPQCDashBoardService;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNDashboardVo;
import cn.jwis.qualityworkflow.modules.pcn.service.PCNDashBoardService;
import cn.jwis.qualityworkflow.modules.qims.service.BlackDashBoardService;
import cn.jwis.qualityworkflow.modules.qims.service.CqaDashBoardService;
import cn.jwis.qualityworkflow.modules.qims.service.GrayDashBoardService;
import cn.jwis.qualityworkflow.util.Title;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/6 15:52
 */
@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	HomePageMapper homePageMapper;

	@Autowired
	BlackDashBoardService blackDashBoardService;

	@Autowired
	GrayDashBoardService grayDashBoardService;

	@Autowired
	CqaDashBoardService cqaDashBoardService;

	@Autowired
	PCNDashBoardService pcnDashBoardService;

	@Autowired
	EcnDashBoardService ecnDashBoardService;

	@Autowired
	IPQCDashBoardService ipqcDashBoardService;

	@Autowired
	ApqpDashBoardService apqpDashBoardService;

	@Override
	public JSONObject getAllProcessStatus(JSONObject bean) throws Exception {
		JSONObject result = new JSONObject();
		Integer addSum = 0;
		Integer handleSum = 0;
		Integer closeSum = 0;
		Integer overdueSum = 0;
		//QIMS的流程信息接口
		JSONObject qimsInfo = getQimsInfo(bean);
		//ECN的流程信息接口
		JSONObject ecnInfo = getEcnInfo(bean);
		//PCN的流程信息接口
		JSONObject pcnInfo = getPcnInfo(bean);
		//E-AUDIT的流程信息接口
		JSONObject eauditInfo = getEauditInfo(bean);
		//APQP的流程信息接口
		JSONObject apqpInfo = getApqpInfo(bean);
		addSum = getInteger(qimsInfo,"all")+getInteger(ecnInfo,"all")+getInteger(pcnInfo,"all")+getInteger(eauditInfo,"all")+getInteger(apqpInfo,"all");
		handleSum = getInteger(qimsInfo,"processing")+getInteger(ecnInfo,"processing")+getInteger(pcnInfo,"processing")+getInteger(eauditInfo,"processing")+getInteger(apqpInfo,"processing");
		closeSum = getInteger(qimsInfo,"close")+getInteger(ecnInfo,"close")+getInteger(pcnInfo,"close")+getInteger(eauditInfo,"close")+getInteger(apqpInfo,"close");
		overdueSum = getInteger(qimsInfo,"overdue")+getInteger(ecnInfo,"overdue")+getInteger(pcnInfo,"overdue")+getInteger(eauditInfo,"overdue")+getInteger(apqpInfo,"overdue");
		result.put("all",addSum);
		result.put("processing",handleSum);
		result.put("close",closeSum);
		result.put("overdue",overdueSum);
		return result;
	}

	@Override
	public JSONObject getProcessStatusByName(String name, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		Map<String, String> title = Title.getTitle(request, workFlowDb, workFlowExcel, WorkFlowExcelUs);
		List<JSONObject> list = new ArrayList<>();
		switch (name){
			case "QIMS":
				list = getQimsProcess();
				break;
			case "ESD":
				list = getEsdProcess();
				break;
			case "ECN":
				list = getEcnProcess();
				break;
			case "PCN":
				list = getPcnProcess();
				break;
			case "Factory Audit":
				list = getFacAuProcess();
				break;
			case "E Audit":
				list = getEauProcess();
				break;
			case "Line Qualify":
				list = getLineQuaProcess();
				break;
			case "Rework":
				list = getReworkProcess();
				break;
			case "APQP":
				list = getApqpProcess();
				break;
				default:
					list = null;

		}
		result.put("title",title);
		result.put("data",list);
		return result;
	}

	@Override
	public JSONObject getKpiReport(String modelCategory) throws ParseException {
		JSONObject result = new JSONObject();
		Map<String,String> map = new HashMap<String,String>();
		//获取Title的信息
		List<JSONObject> list = new ArrayList<>();
		List<JSONObject> kpiReport = homePageMapper.getKpiReport(modelCategory);
		String date = null;
		for (JSONObject temp :kpiReport){
			date = temp.getString("date");
			String content = temp.getString("content");
			list.add(JSONObject.parseObject(content));
		}
		map = getTitleInfo(date);
		result.put("title",map);
		result.put("data",list);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description QIMS整体流程的处理信息
	 * @Date  2020/7/7  10:38
	 * @Param
	 * @return
	 */
	private JSONObject getQimsInfo(JSONObject bean) throws ParseException {
		//定义新增，处理中，关闭，超期的信息
		Integer addSum = 0;
		Integer handleSum = 0;
		Integer closeSum = 0;
		Integer overdueSum = 0;
		JSONObject result = new JSONObject();
		//黑色问题
		JSONObject allAndClose = blackDashBoardService.getAllAndClose(bean);
		JSONObject overdue = blackDashBoardService.getOverdue(bean);
		//灰色问题
		JSONObject allAndClose1 = grayDashBoardService.getAllAndClose(bean);
		JSONObject overdue1 = grayDashBoardService.getOverdue(bean);
		//CQA问题
		JSONObject allAndClose2 = cqaDashBoardService.getAllAndClose(bean);
		JSONObject overdue2 = cqaDashBoardService.getOverdue(bean);
        addSum = getInteger(allAndClose,"All")+getInteger(allAndClose1,"All")+getInteger(allAndClose2,"All");
		handleSum = getInteger(allAndClose,"Processing")+getInteger(allAndClose1,"Processing")+getInteger(allAndClose2,"Processing");
		closeSum = getInteger(allAndClose,"Close")+getInteger(allAndClose1,"Close")+getInteger(allAndClose2,"Close");
		overdueSum = getInteger(overdue,"overdue")+getInteger(overdue1,"overdue")+getInteger(overdue2,"overdue");
		result.put("all",addSum);
		result.put("processing",handleSum);
		result.put("close",closeSum);
		result.put("overdue",overdueSum);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description ECN整体流程的处理信息
	 * @Date  2020/7/7  10:40
	 * @Param
	 * @return
	 */
	private JSONObject getEcnInfo(JSONObject bean) throws Exception {
		String startTime = bean.getString("startTime");
		String endTime = bean.getString("endTime");
		QueryDashboardBean queryDashboardVo = new QueryDashboardBean();
		queryDashboardVo.setEndTime(endTime);
		queryDashboardVo.setStartTime(startTime);
		JSONObject result = new JSONObject();
		long addCount = ecnDashBoardService.getAddCount(queryDashboardVo);
		long processingCount = ecnDashBoardService.getProcessingCount(queryDashboardVo);
		long closedCount = ecnDashBoardService.getClosedCount(queryDashboardVo);
		long overdueCount = ecnDashBoardService.getOverdueCount(queryDashboardVo);
		result.put("all",addCount);
		result.put("processing",processingCount);
		result.put("close",closedCount);
		result.put("overdue",overdueCount);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description PCN整体流程的处理信息
	 * @Date  2020/7/7  10:42
	 * @Param
	 * @return
	 */
	private JSONObject getPcnInfo(JSONObject bean) throws Exception {
		String startTime = bean.getString("startTime");
		String endTime = bean.getString("endTime");
		JSONObject result = new JSONObject();
		PCNDashboardVo pcnDashboardVo  = new PCNDashboardVo();
		pcnDashboardVo.setStartTime(startTime);
		pcnDashboardVo.setEndTime(endTime);
		Map<String, Object> pcnDashBoard = pcnDashBoardService.getPCNDashBoard(pcnDashboardVo);
		result.put("all",pcnDashBoard.get("AddCount"));
		result.put("processing",pcnDashBoard.get("ProcessingCount"));
		result.put("close",pcnDashBoard.get("ClosedCount"));
		result.put("overdue",pcnDashBoard.get("OverdueCount"));
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description E-AUDIT整体流程的处理信息
	 * @Date  2020/7/7  10:42
	 * @Param
	 * @return
	 */
	private JSONObject getEauditInfo(JSONObject bean) {
		JSONObject result = new JSONObject();
		String startTime = bean.getString("startTime");
		String endTime = bean.getString("endTime");
		cn.jwis.qualityworkflow.modules.ipqc.bean.QueryDashboardVo queryDashboardVo = new cn.jwis.qualityworkflow.modules.ipqc.bean.QueryDashboardVo();
		queryDashboardVo.setEndTime(endTime);
		queryDashboardVo.setStartTime(startTime);
		Map<String, Object> ipqcDashBoard = ipqcDashBoardService.getIPQCDashBoard(queryDashboardVo);
		result.put("all",ipqcDashBoard.get("AddCount"));
		result.put("processing",ipqcDashBoard.get("ProcessingCount"));
		result.put("close",ipqcDashBoard.get("ClosedCount"));
		result.put("overdue",ipqcDashBoard.get("OverdueCount"));
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description APQP整体流程的处理信息
	 * @Date  2020/7/7  10:42
	 * @Param
	 * @return
	 */
	private JSONObject getApqpInfo(JSONObject bean) throws Exception {
		JSONObject result = new JSONObject();
		String startTime = bean.getString("startTime");
		String endTime = bean.getString("endTime");
		TimeBean timeBean  = new TimeBean();
		timeBean.setStartTime(startTime);
		timeBean.setEndTime(endTime);
		int addCount = apqpDashBoardService.getAddCount(timeBean);
		int processingCount = apqpDashBoardService.getProcessingCount(timeBean);
		int closeCount = apqpDashBoardService.getCloseCount(timeBean);
		int overdueCount = apqpDashBoardService.getOverdueCount(timeBean);
		result.put("all",addCount);
		result.put("processing",processingCount);
		result.put("close",closeCount);
		result.put("overdue",overdueCount);
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取JSONObject的int值
	 * @Date  2020/7/7  11:11
	 * @Param
	 * @return
	 */
	private Integer getInteger(JSONObject bean,String key){
		Integer result = 0;
		Integer integer = bean.getInteger(key);
		if (integer != null){
			result = integer;
		}
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取QIMS未 结案的流程信息
	 * @Date  2020/7/7  17:45
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getQimsProcess(){
		List<JSONObject> result = new ArrayList<>();
		//黑色问题
		List<JSONObject> blackInfo = homePageMapper.getBlackInfo();
		addFiled(blackInfo,"QIMS黑色问题");
		//灰色问题
		List<JSONObject> grayInfo = homePageMapper.getGrayInfo();
		addFiled(grayInfo,"QIMS灰色问题");
		//CQA问题
		List<JSONObject> cqaInfo = homePageMapper.getCqaInfo();
		addFiled(cqaInfo,"QIMSCQA问题");
		result.addAll(blackInfo);
		result.addAll(grayInfo);
		result.addAll(cqaInfo);
		return result;
	}

    /**
     * @Author yuyangyang
     * @Description 获取ESD未结案的流程信息
	 * @Date  2020/7/7  17:46
     * @Param
     * @return
     */
	private  List<JSONObject> getEsdProcess(){
		List<JSONObject> result = new ArrayList<>();
		//周期性检验
		List<JSONObject> esdCycle = homePageMapper.getEsdCycle();
		addFiled(esdCycle,"ESD周期性检验");
		//来料检验
		List<JSONObject> esdSpecial = homePageMapper.getEsdSpecial();
		addFiled(esdSpecial,"ESD来料检验");
		//稽核检验
		List<JSONObject> esdAudit = homePageMapper.getEsdAudit();
		addFiled(esdAudit,"ESD稽核检验");
		result.addAll(esdCycle);
		result.addAll(esdSpecial);
		result.addAll(esdAudit);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取ECN未结案的流程信息
	 * @Date  2020/7/7  17:47
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getEcnProcess(){
		List<JSONObject> result = new ArrayList<>();
		//ECN
		List<JSONObject> ecnInfo = homePageMapper.getEcnInfo();
		addFiled(ecnInfo,"ECN单管理");
		//外来文件
		List<JSONObject> externalDocument = homePageMapper.getExternalDocument();
		addFiled(externalDocument,"外来文件管理");
		result.addAll(ecnInfo);
		result.addAll(externalDocument);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取PCN未结案的流程信息
	 * @Date  2020/7/7  17:48
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getPcnProcess(){
		List<JSONObject> result = new ArrayList<>();
		List<JSONObject> pcnProcess = homePageMapper.getPcnProcess();
		addFiled(pcnProcess,"PCN检验单管理");
		result.addAll(pcnProcess);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取Factory Audit未结案的流程信息
	 * @Date  2020/7/7  17:48
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getFacAuProcess(){
		List<JSONObject> result = new ArrayList<>();
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取E Audit未结案的流程信息
	 * @Date  2020/7/7  17:48
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getEauProcess(){
		List<JSONObject> result = new ArrayList<>();
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取Line Qualify未结案的流程信息
	 * @Date  2020/7/7  17:51
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getLineQuaProcess(){
		List<JSONObject> result = new ArrayList<>();
		List<JSONObject> lineQuaProcess = homePageMapper.getLineQuaProcess();
		addFiled(lineQuaProcess,"Line Qualify");
		result.addAll(lineQuaProcess);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取Rework未结案的流程信息
	 * @Date  2020/7/7  17:51
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getReworkProcess(){
		List<JSONObject> result = new ArrayList<>();
		List<JSONObject> pcnProcess = homePageMapper.getReworkProcess();
		addFiled(pcnProcess,"Rework");
		result.addAll(pcnProcess);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取APQP未结案的流程信息
	 * @Date  2020/7/7  17:51
	 * @Param
	 * @return
	 */
	private  List<JSONObject> getApqpProcess(){
		List<JSONObject> result = new ArrayList<>();
		List<JSONObject> apqpProcess = homePageMapper.getApqpProcess();
		addFiled(apqpProcess,"APQP");
		result.addAll(apqpProcess);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 添加表单描述字段
	 * @Date  2020/7/7  17:53
	 * @Param
	 * @return
	 */
	private void  addFiled(List<JSONObject> list,String value){
		for (JSONObject temp:list) {
			temp.put("table_description",value);
		}
	}
    /**
     * @Author yuyangyang
     * @Description 获取Title的名字
     * @Date  2020/7/10  17:59
     * @Param
     * @return
     */
	private Map<String,String> getTitleInfo(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sdf.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		int temp = Calendar.DAY_OF_YEAR;calendar.add(temp,-1);
		date = sdf.format(calendar.getTime());
		date = date+" 00:00:00";
		String endTime = getEndTime(date);
		String startTime = getStartTime(endTime);
		Set<String> betweenRuleDateDay = getBetweenRuleDateDay(5, startTime, endTime);
		Set<String> betweenRuleDateDay1 = getBetweenRuleDateDay(4, startTime, endTime);
		Map<String,String> title = new LinkedHashMap<>();
        title.put("item","KPI Item");
        title.put("target","Target");
		for (String key:betweenRuleDateDay) {
			title.put(key,"MTD"+"("+key+")");
		}
		for (String key:betweenRuleDateDay1) {
            title.put(key,key.split("-")[1]);
		}
        return title;
	}
}