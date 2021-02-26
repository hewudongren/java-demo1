package cn.jwis.qualityworkflow.modules.qims.service.imp;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemSearch;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.GrayProblemInfoMapper;
import cn.jwis.qualityworkflow.modules.qims.service.GrayProblemService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FormatUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;
import static cn.jwis.qualityworkflow.util.Title.QimsGrayDB;
import static cn.jwis.qualityworkflow.util.Title.QimsGrayExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsGrayExcelName;
import static cn.jwis.qualityworkflow.util.Title.QimsGrayExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/25 17:14
 */
@Service
public class GrayProblemServiceImpl extends BaseClass implements GrayProblemService {

	@Autowired
	GrayProblemInfoMapper grayProblemInfoMapper;
	@Autowired
	IDGeneratorRunner idGeneratorRunner;
	@Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;
	@Autowired
	WorkFlowUtil workFlowUtil;
	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;
	@Autowired
	UserServer userServer;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGrayProblem(GrayProblemInfo grayProblemInfo) {
		//问题类型添加
		grayProblemInfo.setProblemType("GRAY");
		//id获取
		grayProblemInfo.setId(String.valueOf(idGeneratorRunner.nextId()));
		//获取节假日，补班日期
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		//响应LT获取
		String tl = DateUtil.getTl(grayProblemInfo.getOccurrenceTime(), grayProblemInfo.getResponseTime(),holiday,workOvertimeList);
		grayProblemInfo.setResponseLt(tl);
		//问题LT获取
		String tl1 = DateUtil.getTl(grayProblemInfo.getResponseTime(), grayProblemInfo.getProcessingTime(),holiday,workOvertimeList);
		grayProblemInfo.setProblemSolvingLt(tl1);
		//LT获取
		String tl2 = DateUtil.getTl(grayProblemInfo.getOccurrenceTime(), grayProblemInfo.getProcessingTime(),holiday,workOvertimeList);
		grayProblemInfo.setLt(tl2);
		//Recordstatus
		grayProblemInfo.setRecordStatus("拟制完成");
		//工厂、部门、Owner所属组
//		grayProblemInfo.setFactory("");
//		grayProblemInfo.setDepartment("");
//		grayProblemInfo.setOwnerGroup("");
		//获取是否及时(标准在数据库中获取)
		String isTime = "及时";
		String gray = blackDashBoardMapper.getLtStandard("灰色问题");
		if (Double.valueOf(tl2) > Double.valueOf(gray)){
           isTime = "不及时";
		}
		//业务日期
		String date = JSONObjectUtil.getDate(new Date());
		grayProblemInfo.setBusinessDatetime(date);
		//当前处理人，处理部门
		String questionTaker = grayProblemInfo.getQuestionTaker();
		grayProblemInfo.setAssigneer(questionTaker);
		grayProblemInfo.setAssigneerDepartment(workFlowUtil.getDepartment(questionTaker,"QIMS"));
		grayProblemInfo.setIsTimely(isTime);
		grayProblemInfoMapper.insert(grayProblemInfo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirmGrayProblem(GrayProblemInfo grayProblemInfo) {
		String qaPerson = grayProblemInfo.getQaPerson();
		grayProblemInfo.setQaTime(new Date());
		grayProblemInfo.setAssigneer(qaPerson);
		grayProblemInfo.setAssigneerDepartment(workFlowUtil.getDepartment(qaPerson,"QIMS"));
		grayProblemInfoMapper.updateByPrimaryKey(grayProblemInfo);
		JSONObject jsonObject = JSONObjectUtil.toJSONObject(grayProblemInfo);
		jsonObject.put("handler",getCurrentUserName());
		jsonObject.put("handlerDate",getCurrentDate());
		temporaryGrayProblem(jsonObject,"COMMIT");
	}

	@Override
	public void temporaryGrayProblem(JSONObject grayProblemInfo,String type) {
		HistoryProcessRecord historyProcessRecord = new HistoryProcessRecord();
		historyProcessRecord.setId(grayProblemInfo.getString("id"));
		historyProcessRecord.setCreator(grayProblemInfo.getString("qaPerson"));
		historyProcessRecord.setType(type);
		historyProcessRecord.setWorkflowType("Gray");
		grayProblemInfo.remove("id");
		historyProcessRecord.setContent(JSONObject.toJSONString(grayProblemInfo));
		historyProcessRecordMapper.replaceInsert(historyProcessRecord);
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		List<String> result = grayProblemInfoMapper.getDropdownValue(parameter);
		return  result;
	}

	@Override
	public ResultBean getGrayProblemList(GrayProblemSearch grayProblemSearch, HttpServletRequest request) {
		String language = request.getHeader("Language");
		int page = grayProblemSearch.getPage();
		int size = grayProblemSearch.getSize();
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		grayProblemSearch.setName(userItemInfos);
		pageChange(grayProblemSearch);
		String[] dbList = QimsGrayDB;
		String[] excelList = QimsGrayExcel;
		if ("en-US".equals(language)){
			excelList = QimsGrayExcelUS;
		}
		Map<String, String> title = FormatUtil.getTitle(dbList,excelList);
		List<GrayProblemInfo> list = grayProblemInfoMapper.getGrayProblemList(grayProblemSearch);
		if (isNotNull(list)){
		 return ResultBean.pagination(grayProblemInfoMapper.getGrayProblemCount(grayProblemSearch), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, list,title);
		}
	}

	@Override
	public void exportGrayProblemList(GrayProblemSearch grayProblemSearch, HttpServletRequest request, HttpServletResponse response) {
		String language = request.getHeader("Language");
		grayProblemSearch.setPage(null);
		grayProblemSearch.setSize(null);
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		grayProblemSearch.setName(userItemInfos);
		String[] dbList = QimsGrayDB;
		String[] excelList = QimsGrayExcel;
		if ("en-US".equals(language)){
			excelList = QimsGrayExcelUS;
		}
		List<GrayProblemInfo> list = grayProblemInfoMapper.getGrayProblemList(grayProblemSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (GrayProblemInfo grayProblemInfo:list) {
			JSONObject object = JSONObjectUtil.toJSONObject(grayProblemInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,QimsGrayExcelName);
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,excelList,dbList);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getGrayProblemDetails(String id) {
		JSONObject result = new JSONObject();
		GrayProblemInfo grayProblemInfo = grayProblemInfoMapper.getGrayProblemById(id);
		HistoryProcessRecord historyProcessRecord = historyProcessRecordMapper.selectByPrimaryKey(id);
		JSONObject jsonObject = new JSONObject();
		if (isNotNull(historyProcessRecord)){
			jsonObject = JSONObject.parseObject(historyProcessRecord.getContent());
		}
		String recordStatus = grayProblemInfo.getRecordStatus();
		String responder = grayProblemInfo.getResponder();
		String currentUserName = getCurrentUserName();
		Boolean flag = false;
		if (!"结案".equals(recordStatus) && responder.equals(currentUserName)){
			flag = true;
		}
		result.put("enableHandleCurrentNode",flag);
		result.put("提交",jsonObject);
		result.put("detailedInfo",grayProblemInfo);
		return result;
	}

	/**
	 * page和size的转换
	 */
	private void pageChange(GrayProblemSearch grayProblemSearch){
		Integer page = grayProblemSearch.getPage();
		Integer size = grayProblemSearch.getSize();
		if (isNotNull(page) && isNotNull(size)){
			page = (page-1)*size;
			grayProblemSearch.setPage(page);
		}
	}
}