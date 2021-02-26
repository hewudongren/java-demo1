package cn.jwis.qualityworkflow.modules.qims.service.imp;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.WorkflowVariableReflectMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditAbnormalMapper;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackSearch;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsBlackInfoMapper;
import cn.jwis.qualityworkflow.modules.qims.service.QimsBlackService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.common.Constants.QIMSBLACKTEMPLATEKEY;
import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.BeanUtil.transformationJson;
import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;
import static cn.jwis.qualityworkflow.util.ExcelUtil.createBlackFile;
import static cn.jwis.qualityworkflow.util.ExcelUtil.setResponseHeader;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackDB;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackExcelName;
import static cn.jwis.qualityworkflow.util.Title.QimsBlackExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/19 17:49
 */
@Service
public class QimsBlackServiceImpl extends BaseClass implements QimsBlackService {

	@Autowired
	QimsBlackInfoMapper qimsBlackInfoMapper;

	@Autowired
	WorkflowServer workflowServer;


	@Value("${qms.app.key}")
	private String tenantId;

	@Autowired
	private IDGeneratorRunner idGeneratorRunner;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	WorkflowVariableReflectMapper variableReflectMapper;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;
	@Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;

    @Autowired
	WorkFlowUtil workFlowUtil;

    @Autowired
	BlackDashBoardMapper blackDashBoardMapper;

    @Autowired
	CommonMapper commonMapper;

	@Autowired
	EsdAuditAbnormalMapper esdAuditAbnormalMapper;

	@Autowired
	UserServer userServer;

	@Value("${black.file.oid}")
	private String fileOid;




	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(JSONObject bean) {
		//获取节假日，补班日期集合
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		String taskId = bean.getString("task_id");
		QimsBlackInfo qimsBlackInfo = qimsBlackInfoMapper.getQimsBlackInfo(taskId);
		//获取创建人，主题，项目编号
		String creator = qimsBlackInfo.getCreator();
		String theme = qimsBlackInfo.getReportSubject();
		String itemNumber = qimsBlackInfo.getQuestionNumber();
		String state = bean.getString("state");
		JSONObject variables = bean.getJSONObject("variables");
		variables.put("handler",getCurrentUserName());
		variables.put("handlerDate",getCurrentDate());
		bean.put("variables",variables);
		List<String> list = new LinkedList<>();
		if ("初步原因分析".equals(state)){
			list.add("cause_analysis_person");
			list.add("cause_analysis_time");
			addFiled(bean,"create_date","cause_analysis_time","preliminary_cause_analysis_lt",list,holiday,workOvertimeList);
		}else  if ("根本原因分析".equals(state)){
			list.add("root_cause_analyst");
			list.add("root_cause_time");
			addFiled(bean,"cause_analysis_time","root_cause_time","root_cause_analysis_lt",list,holiday,workOvertimeList);
		}else if ("根本原因审核".equals(state)){
			list.add("root_cause_auditor");
			list.add("root_cause_analysis_time");
			addFiled(bean,"root_cause_time","root_cause_analysis_time","root_cause_audit_lt",list,holiday,workOvertimeList);
			addFiled(bean,"create_date","root_cause_analysis_time","current_lt",null,holiday,workOvertimeList);
		}else  if ("效果验证".equals(state)){
			list.add("effect_verifier");
			list.add("effect_verification_time");
			addFiled(bean,null,null,null,list,holiday,workOvertimeList);
		}else if ("效果审核".equals(state)){
			list.add("effect_reviewer");
			list.add("effect_review_time");
			addFiled(bean,null,null,null,list,holiday,workOvertimeList);
		}else {
			list.add("case_closing_approver");
			list.add("case_closing_date");
			addFiled(bean,null,null,null,list,holiday,workOvertimeList);
		}
		//不同的节点做不同的处理
		workFlowUtil.confirm(bean,QIMSBLACKTEMPLATEKEY,"qims_black_info",creator,theme,itemNumber);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveQimsBlackInfo(QimsBlackInfo bean) {
		String id = bean.getId();
		long count = 0;
		if (isNotNull(id)){
			count = qimsBlackInfoMapper.getCountById(id);
		}
		boolean flag = false;
		if (count == 0){
			//自动生成ID
			bean.setId(String.valueOf(idGeneratorRunner.nextId()));
			flag = true;
		}
		String line = bean.getLine();
		String model = bean.getModel();
		String anomalyReporter = bean.getAnomalyReporter();
		String failurePhenomenon = bean.getFailurePhenomenon();
		//自动生成创建人
		bean.setCreator(anomalyReporter);
		if (flag){
			//新增时项目编号变化
			String itemNumber  = qimsBlackInfoMapper.getItemNumber();
			bean.setItemNumber(itemNumber);
			String questionNumber = String.join("-","BLACK",line,itemNumber);
			bean.setQuestionNumber(questionNumber);
		}else {
			//驳回时项目编号不发生变化
			String itemNumber = bean.getItemNumber();
			String questionNumber = String.join("-","BLACK",line,itemNumber);
			bean.setQuestionNumber(questionNumber);
		}
		//自动生成报告主题
		String reportSubject = String.join("-",line,model,failurePhenomenon);
		bean.setReportSubject(reportSubject);
		//根据机型生成产品类型，制造类型，项目
		getModelCategory(model,bean);
		//根据生成时间生成业务日期
		String date = JSONObjectUtil.getDate(new Date());
		bean.setBusinessDatetime(date);
		//当前处理人，处理部门
		bean.setAssigneer(anomalyReporter);
		bean.setAssigneerDepartment(workFlowUtil.getDepartment(anomalyReporter,"QIMS"));
		saveQimsBlack(bean,id,flag);
	}

	@Override
	public List<QimsBlackInfo> getQimsBlackInfoList(QimsBlackSearch qimsBlackSearch) {
		List<QimsBlackInfo> result = new ArrayList<>();
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		//是管理员就为null 查所有
		qimsBlackSearch.setAssignee(userItemInfos);
		try {
			//分页页码处理
			pageChange(qimsBlackSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = qimsBlackInfoMapper.getQimsBlackInfoList(qimsBlackSearch);
		result = transformation(result);
		return result;
	}

	@Override
	public Long getQimsBlackInfoListCount(QimsBlackSearch qimsBlackSearch) {
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		qimsBlackSearch.setAssignee(userItemInfos);
		Long count = qimsBlackInfoMapper.getQimsBlackInfoListCount(qimsBlackSearch);
		return count;
	}

	@Override
	public void exportQimsBlackInfoList(HttpServletResponse response, HttpServletRequest request, QimsBlackSearch qimsBlackSearch) {
		String language = request.getHeader("Language");
		qimsBlackSearch.setPage(null);
		qimsBlackSearch.setSize(null);
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		qimsBlackSearch.setAssignee(userItemInfos);
		List<QimsBlackInfo> qimsBlackInfos = qimsBlackInfoMapper.getQimsBlackInfoList(qimsBlackSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (QimsBlackInfo qimsBlackInfo:qimsBlackInfos) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsBlackInfo);
			jsonObjects.add(object);
		}
		List<JSONObject> parameterList = commonMapper.getTransferParameter("qims_black_info");
		transformationJson(jsonObjects,parameterList);
		ExcelUtil.setResponseHeader(response,QimsBlackExcelName);
		String[] title = QimsBlackExcel;
		String[] strings = QimsBlackDB;
		if ("en-US".equals(language)){
			title = QimsBlackExcelUS;
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
	public Map<String, Object> getQimsBlackInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		QimsBlackInfo qimsBlackInfo = qimsBlackInfoMapper.getQimsBlackInfo(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",qimsBlackInfo);
		//通过taskId判断任务处于哪个节点
		TaskRecord taskRecord = taskRecordDao.getByTaskId(bean.getTaskId());
		//判断当前人是否能够处理
		String assigneer = qimsBlackInfo.getAssigneer();
		String status = qimsBlackInfo.getStatus();
		String taskState = taskRecord.getTaskState();
		boolean enableHandleCurrentNode = false;
       if (status.equals(taskRecord.getTaskName()) && assigneer.equals(getCurrentUserName()) && isNull(taskState)){
		   enableHandleCurrentNode = true;
	   }
		detailedInfoMap.put("enableHandleCurrentNode",enableHandleCurrentNode);
		return detailedInfoMap;
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		List<String> list = qimsBlackInfoMapper.getDropdownValue(parameter);
		return list;
	}

	@Override
	public List<String> getSubsection() {
		return qimsBlackInfoMapper.getSubsection();
	}

	@Override
	public List<String> getValueBySubsection(JSONObject bean) {
		List<String> result = new ArrayList<>();
		//获取分段值以及对应查询的字段
		String subsection = bean.getString("subsection");
		String parameter = bean.getString("parameter");
		if ("site".equals(parameter)){
			result = qimsBlackInfoMapper.getSiteBySubsection(subsection);
		}else if ("line".equals(parameter)){
			result = qimsBlackInfoMapper.getLineBySubsection(subsection);
		}else {
			result = qimsBlackInfoMapper.getPhenomenonBySubsection(subsection);
		}
		return result;
	}

	@Override
	public List<String> getModel() {
		return qimsBlackInfoMapper.getModel();
	}

	@Override
	public List<String> getDepartment() {
		return qimsBlackInfoMapper.getDepartment();
	}

	@Override
	public List<String> getNameByDepartment(String parameter) {
		return qimsBlackInfoMapper.getNameByDepartment(parameter);
	}

	@Override
	public Set<String> getUserName() {
		List<Map<String, Object>> userNameList = workflowServer.getUserName();
		Set<String> result = new HashSet<>();
		for (Map<String,Object> map : userNameList) {
			Object account = map.get("account");
			if (isNotNull(account)){
				result.add(account.toString());
			}
		}
		return result;
	}

	@Override
	public JSONObject getSuperior() {
		JSONObject result = new JSONObject();
		String superior = esdAuditAbnormalMapper.getSuperior(getCurrentUserName());
		String department = commonMapper.getDepartment(superior);
		if (isNotNull(department)){
			result.put("department",department);
		}
		if (isNotNull(superior)){
			result.put("superior",superior);
		}
		return result;
	}

	@Override
	public List<String> getLineValue(JSONObject jsonObject) {
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph"));
		return qimsBlackInfoMapper.getLineValue(list);
	}

	@Override
	public List<String> getSiteValue(JSONObject jsonObject) {
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("paragraph"));
		List<String> lineList = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("line"));
		return qimsBlackInfoMapper.getSiteValue(list,lineList);
	}

	@Override
	public void exportBlack8dInfo(HttpServletResponse response, String id) throws Exception {
		String fileName = "BlackProblem8D.xlsx";
		//设置响应头
		setResponseHeader(response, fileName);
		QimsBlackInfo qimsBlackInfoById = qimsBlackInfoMapper.getQimsBlackInfoById(id);
		createBlackFile(fileName,qimsBlackInfoById,response,fileOid);
	}

	/**
     * @Author yuyangyang
     * @Description 拟制单据节点的数据保存
     * @Date  2020/6/30  15:28
     * @Param
     * @return
     */
	@Transactional
	public void saveQimsBlack(QimsBlackInfo bean,String id,boolean flag) {
		//获取主题和项目编号
		String theme = null;
		String itemNumber = null;
		theme = bean.getReportSubject();
		itemNumber = bean.getQuestionNumber();
		//判断是新起一个流程还是提交拟制单据节点数据
		if (flag) {
			qimsBlackInfoMapper.insert(bean);
			if (isNotNull(id)) {
				historyProcessRecordMapper.deleteRecord(QIMSBLACKTEMPLATEKEY, getCurrentUserName(), "问题描述");
			}
			String name = bean.getAnomalyReporter();
			String abnormalResponsiblePerson = bean.getAbnormalResponsiblePerson();
			String workflowId = bean.getId();
			// 1.启动QIMS黑色问题流程实例，（流程实例key,流程变量）
			String processInstanceId = workFlowUtil.startProcessInstance("creator", name, tenantId, QIMSBLACKTEMPLATEKEY);
			// 获取最新的Task,
			String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
			JSONObject jsonObject = new JSONObject();
			//获取新建审核单传入的变量
			jsonObject = JSONObjectUtil.toJSONObject(bean);
			//业务处理人（当前登录用户），异常响应时间添加
			jsonObject.put("handler", getCurrentUserName());
			jsonObject.put("handlerDate", getCurrentDate());
			//2.保存历史记录数据
			historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject, HISOTORY_PROCESS_COMMIT, QIMSBLACKTEMPLATEKEY, bean.getId(), "问题描述"));
			//完成任务 流程变量
			workFlowUtil.finishTask(processInstanceId, taskId, "abnormal_responsible_person", abnormalResponsiblePerson);
			// 任务记录表，保存申请Task记录
			String qimsId = bean.getId();
			workFlowUtil.saveEcnApplyTaskRecord(qimsId, processInstanceId, taskId, name, "问题描述", "Close", QIMSBLACKTEMPLATEKEY, theme, itemNumber);
			// 当前流程的所有任务 创建下一个任务
			String status = workFlowUtil.nextNode(processInstanceId, qimsId, QIMSBLACKTEMPLATEKEY, workflowId, name, theme, itemNumber);
			//需要修改
			bean.setStatus(status);
			//修改当前处理人,当前处理部门
			bean.setAssigneer(abnormalResponsiblePerson);
			bean.setAssigneerDepartment(workFlowUtil.getDepartment(abnormalResponsiblePerson,"QIMS"));
			qimsBlackInfoMapper.updateByPrimaryKey(bean);
		}else {
            //通过workflowId得到最新的taskId和processId
			JSONObject jsonObject = workFlowUtil.getConfirmJson(id, bean, "问题描述");
			confirm(jsonObject);
		}
	}





	/**
	 * @Author yuyangyang
	 * @Description 流程提交过程中产生的LT
	 * @Date  2020/5/26  14:38
	 * @Param
	 * @return
	 */
	private void addFiled(JSONObject bean,String startDateKey,String endDateKey,String key,List<String> list,List<String> holiday,List<String> workOvertimeList){
		JSONObject variables = bean.getJSONObject("variables");
		if (isNotNull(list)){
			variables.put(list.get(0),getCurrentUserName());
			variables.put(list.get(1),getCurrentDate());
		}
		if (isNotNull(key)){
			Date startDate = variables.getDate(startDateKey);
			Date endDate = variables.getDate(endDateKey);
			String tl = DateUtil.getTl(startDate, endDate,holiday,workOvertimeList);
			variables.put(key,tl);
			if ("current_lt".equals(key)){
				String temp  = "及时";
				String black = blackDashBoardMapper.getLtStandard("黑色问题");
				if (Double.valueOf(tl) > Double.valueOf(black)){
					temp = "不及时";
				}
				variables.put("conclusion_of_case",temp);
			}
		}
		bean.put("variables",variables);
	}
	/**
	 * @Author yuyangyang
	 * @Description 根据机型获取产品类型，制造类型，项目
	 * @Date  2020/6/3  15:55
	 * @Param
	 * @return
	 */
	private void  getModelCategory(String model,QimsBlackInfo bean){
		JSONObject modelMap = qimsBlackInfoMapper.findModel(model);
		if (isNotNull(modelMap)){
			bean.setModelCategory(modelMap.getString("model_category"));
			bean.setManufactureType(modelMap.getString("manufacture_type"));
			bean.setItem(modelMap.getString("model_odm"));
			bean.setBusinessModel(modelMap.getString("business_model"));
		}
	}


    /**
     * @Author yuyangyang
     * @Description 页面展示数据库值与实际代表值转换
     * @Date  2020/6/29  14:26
     * @Param
     * @return
     */
	private List<QimsBlackInfo> transformation(List<QimsBlackInfo> list){
		for (QimsBlackInfo qims:list) {
			String isStop = qims.getIsStop();
			if ("Y".equals(isStop)){
				qims.setIsStop("是");
			}else if ("N".equals(isStop)){
				qims.setIsStop("否");
			}
		}
		return list;
	}

}