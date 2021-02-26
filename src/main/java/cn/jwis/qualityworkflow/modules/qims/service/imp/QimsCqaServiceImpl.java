package cn.jwis.qualityworkflow.modules.qims.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.common.Constants.QIMSCQATEMPLATEKEY;
import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;
import static cn.jwis.qualityworkflow.util.ExcelUtil.createCQAFile;
import static cn.jwis.qualityworkflow.util.ExcelUtil.setResponseHeader;
import static cn.jwis.qualityworkflow.util.FormatUtil.stringToList;
import static cn.jwis.qualityworkflow.util.Title.QimsCQADB;
import static cn.jwis.qualityworkflow.util.Title.QimsCQADBa;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcel;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcelName;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcelUS;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcelUSa;
import static cn.jwis.qualityworkflow.util.Title.QimsCQAExcela;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.WorkflowVariableReflectMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditAbnormalMapper;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaSearch;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import cn.jwis.qualityworkflow.modules.qims.dao.QimsCqaInfoMapper;
import cn.jwis.qualityworkflow.modules.qims.service.QimsCqaService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public class QimsCqaServiceImpl extends BaseClass implements QimsCqaService {
    @Autowired
	IDGeneratorRunner idGeneratorRunner;
    @Autowired
	WorkFlowUtil workFlowUtil;
    @Autowired
	QimsCqaInfoMapper cqaInfoMapper;
    @Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;
    @Autowired
	EsdAuditAbnormalMapper esdAuditAbnormalMapper;
    @Autowired
	CommonMapper commonMapper;
	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	WorkflowVariableReflectMapper variableReflectMapper;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;
	@Autowired
	WorkflowServer workflowServer;
	@Autowired
	UserServer userServer;
	@Value("${qms.app.key}")
	private String tenantId;

	@Value("${cqa.file.oid}")
	private String fileOid;

	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveQimsCqaInfo(QimsCqaInfo bean) {
		String id = bean.getId();
		//需要根据id判断是驳回的数据还是保存的数据
		long count = 0;
		boolean flag = false;
		String itemNumber = bean.getItemNumber();
		if (isNotNull(id)){
			count = cqaInfoMapper.getCountById(id);
		}
		if (count == 0){
			//自动生成ID
			bean.setId(String.valueOf(idGeneratorRunner.nextId()));
			flag = true;
			//新增时项目编号变化
			itemNumber  = cqaInfoMapper.getItemNumber();
			bean.setItemNumber(itemNumber);
		}
		//获取异常报告人
		String anomalyReporter = bean.getAnomalyReporter();
		//获取异常分析人的部门
		String abnormalResponsiblePerson = bean.getAbnormalResponsiblePerson();
		bean.setAbnormalDepartment(workFlowUtil.getDepartment(abnormalResponsiblePerson,"QIMS"));
		//计算缺陷率
		Integer inspectionQty = bean.getInspectionQty();
		Integer failuresNumber = bean.getFailuresNumber();
		bean.setFailureRate(getRate(failuresNumber,inspectionQty));
		bean.setItemNumber(itemNumber);
		String questionNumber = "CQA"+itemNumber;
		bean.setQuestionNumber(questionNumber);
		//问题类型
		bean.setProblemType("CQA");
		//业务日期
		String date = JSONObjectUtil.getDate(new Date());
		bean.setBusinessDatetime(date);
		//当前处理人，处理部门
		bean.setAssigneer(anomalyReporter);
		bean.setAssigneerDepartment(workFlowUtil.getDepartment(anomalyReporter,"QIMS"));
		saveQimsCqa(bean,id,flag);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(JSONObject bean) {
		//获取节假日，补班日期集合
		List<String> holiday = blackDashBoardMapper.getHoliday();
		List<String> workOvertimeList = blackDashBoardMapper.getWorkOvertimeList();
		String taskId = bean.getString("task_id");
		QimsCqaInfo qimsCqaInfo = cqaInfoMapper.getQimsCqaInfo(taskId);
		//获取创建人，主题，项目编号
		String creator = qimsCqaInfo.getAnomalyReporter();
		String theme = qimsCqaInfo.getReportSubject();
		String itemNumber = qimsCqaInfo.getQuestionNumber();
		JSONObject variables = bean.getJSONObject("variables");
		variables.put("handler",getCurrentUserName());
		variables.put("handlerDate",getCurrentDate());
		bean.put("variables",variables);
		//不同的节点做不同的处理
		String state = bean.getString("state");
		List<String> list = new LinkedList<>();
		if("风险评估".equals(state)){
            list.add("risk_assessor");
            list.add("evaluate_time");
            addFiled(bean,null,null,null,list,holiday,workOvertimeList);
		}else if ("初步原因分析".equals(state)){
			list.add("cause_analysis_person");
			list.add("cause_analysis_time");
			addFiled(bean,"reporting_time","cause_analysis_time","preliminary_cause_analysis_lt",list,holiday,workOvertimeList);
		}else  if ("根本原因分析".equals(state)){
			list.add("root_cause_analyster");
			list.add("root_cause_time");
			addFiled(bean,"cause_analysis_time","root_cause_time","root_cause_analysis_lt",list,holiday,workOvertimeList);
		}else if ("根本原因审核".equals(state)){
			list.add("root_cause_auditor");
			list.add("root_cause_analysis_time");
			addFiled(bean,"root_cause_time","root_cause_analysis_time","root_cause_audit_lt",list,holiday,workOvertimeList);
			addFiled(bean,"reporting_time","root_cause_analysis_time","current_lt",null,holiday,workOvertimeList);
		}else if ("效果验证".equals(state)){
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
		workFlowUtil.confirm(bean,QIMSCQATEMPLATEKEY,"qims_cqa_info",creator,theme,itemNumber);
		//人机料法环的数据修改
		if("初步原因分析".equals(state)){
			String resultOfHandling = variables.getString("result_of_handling");
			if ("OK".equals(resultOfHandling)) {
				ThreadUtil.getThreadPool().execute(() -> {
					updateCqaInfo(qimsCqaInfo,bean);
				});
			}
		}
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		return  cqaInfoMapper.getDropdownValue(parameter);
	}

	@Override
	public List<QimsCqaInfo> getQimsCqaInfoList(QimsCqaSearch qimsCqaSearch) {
		List<QimsCqaInfo> result = new ArrayList<>();
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		qimsCqaSearch.setAssignee(userItemInfos);
		try {
			//分页页码处理
			pageChange(qimsCqaSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = cqaInfoMapper.getQimsCqaInfoList(qimsCqaSearch);
		result = transformation(result);
		return result;
	}

	@Override
	public Long getQimsCqaInfoListCount(QimsCqaSearch qimsCqaSearch) {
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		qimsCqaSearch.setAssignee(userItemInfos);
		Long count = cqaInfoMapper.getQimsCqaInfoListCount(qimsCqaSearch);
		return count;
	}

	@Override
	public void exportQimsCqaInfoList(HttpServletResponse response, HttpServletRequest request, QimsCqaSearch qimsCqaSearch) {
		String language = request.getHeader("Language");
		qimsCqaSearch.setPage(null);
		qimsCqaSearch.setSize(null);
		String userItemInfos = userServer.getUserItemInfos("QIMS-业务管理员");
		qimsCqaSearch.setAssignee(userItemInfos);
		List<QimsCqaInfo> qimsBlackInfoList = cqaInfoMapper.getQimsCqaInfoList(qimsCqaSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (QimsCqaInfo qimsCqaInfo:qimsBlackInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(qimsCqaInfo);
			jsonObjects.add(object);
		}
		jsonObjects = transformationJson(jsonObjects);
		setResponseHeader(response,QimsCQAExcelName);
		String[] title = QimsCQAExcel;
		String[] strings = QimsCQADB;
		if ("en-US".equals(language)){
			title = QimsCQAExcelUS;
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
	public Map<String, Object> getQimsCqaInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		QimsCqaInfo qimsCqaInfo = cqaInfoMapper.getQimsCqaInfo(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",qimsCqaInfo);
		//通过taskId判断任务处于哪个节点
		TaskRecord taskRecord = taskRecordDao.getByTaskId(bean.getTaskId());
		//判断当前人是否能够处理
		String assigneer = qimsCqaInfo.getAssigneer();
		String status = qimsCqaInfo.getStatus();
		String taskState = taskRecord.getTaskState();
		boolean enableHandleCurrentNode = false;
		if (status.equals(taskRecord.getTaskName()) && assigneer.equals(getCurrentUserName()) && isNull(taskState)){
			enableHandleCurrentNode = true;
		}
		detailedInfoMap.put("enableHandleCurrentNode",enableHandleCurrentNode);
		return detailedInfoMap;
	}


	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		Map<String,String> title = Title.getTitle(request,QimsCQADBa,QimsCQAExcela,QimsCQAExcelUSa);
		return title;
	}

	@Override
	public List<String> getDropValue(String parameter) {
		List<String> result = new ArrayList<>();
		if ("man".equals(parameter)){
           result = cqaInfoMapper.getManValue();
		}else if ("method".equals(parameter)){
			result = cqaInfoMapper.getMethodValue();
		}else if("environment".equals(parameter)){
			result = cqaInfoMapper.getEnvironmentValue();
		}
		return result;
	}

	@Override
	public String getSuperior(String name) {
		return esdAuditAbnormalMapper.getSuperior(name);
	}

	@Override
	public void exportCqa8dInfo(HttpServletResponse response, String id) throws Exception {
		String fileName = "CqaProblem8D.xlsx";
		setResponseHeader(response, fileName);
		QimsCqaInfo qimsCqaInfo = cqaInfoMapper.getQimsCqaInfoById(id);
		createCQAFile(fileName,qimsCqaInfo,response,fileOid);
	}

	@Transactional
	public void saveQimsCqa(QimsCqaInfo bean, String id,boolean flag) {
		//获取主题和项目编码
		String theme = null;
		String itemNumber = null;
		itemNumber = bean.getQuestionNumber();
		theme = bean.getReportSubject();
		//需要修改(防止直接驳回到拟制单据节点)
		if(flag) {
			cqaInfoMapper.insert(bean);
			if (isNotNull(id)) {
				historyProcessRecordMapper.deleteRecord(QIMSCQATEMPLATEKEY, getCurrentUserName(), "CQA问题描述");
			}
			String name = bean.getAnomalyReporter();
			String riskAssessor = bean.getRiskAssessor();
			String abnormalResponsiblePerson = bean.getAbnormalResponsiblePerson();
			String workflowId = bean.getId();
			// 1.启动QIMS黑色问题流程实例，
			String processInstanceId = workFlowUtil.startProcessInstance("creator", name, tenantId, QIMSCQATEMPLATEKEY);
			// 获取最新的Task,
			String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
			JSONObject jsonObject = new JSONObject();
			//获取新建审核单传入的变量
			jsonObject = JSONObjectUtil.toJSONObject(bean);
			//处理人和处理时间添加
			jsonObject.put("handler", getCurrentUserName());
			jsonObject.put("handlerDate", getCurrentDate());
			historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject, HISOTORY_PROCESS_COMMIT, QIMSCQATEMPLATEKEY, bean.getId(), "CQA问题描述"));
			JSONObject variables = new JSONObject();
			variables.put("risk_assessor", riskAssessor);
			variables.put("abnormal_responsible_person", abnormalResponsiblePerson);
			workflowServer.finishTask(processInstanceId, taskId, variables, null);
			// 任务记录表，保存申请Task记录
			String qimsId = bean.getId();
			workFlowUtil.saveEcnApplyTaskRecord(qimsId, processInstanceId, taskId, name, "CQA问题描述", "Close", QIMSCQATEMPLATEKEY, theme, itemNumber);
			// 当前流程的所有任务
			String status = workFlowUtil.nextNode(processInstanceId, qimsId, QIMSCQATEMPLATEKEY, workflowId, name, theme, itemNumber);
			//需要修改
			bean.setStatus(status);
			//修改当前处理人,当前处理部门
			bean.setAssigneer(riskAssessor);
			bean.setAssigneerDepartment(workFlowUtil.getDepartment(riskAssessor,"QIMS"));
			cqaInfoMapper.updateByPrimaryKey(bean);
		}else {
			//通过workflowId得到最新的taskId和processId
			JSONObject jsonObject = workFlowUtil.getConfirmJson(id,bean,"CQA问题描述");
			confirm(jsonObject);
		}
	}
	/**
	 * @Author yuyangyang
	 * @Description 计算缺陷率
	 * @Date  2020/5/27  15:03
	 * @Param
	 * @return
	 */
	private Float getRate(Integer failuresNumber, Integer inspectionQty){
		BigDecimal a = new BigDecimal(failuresNumber);
		BigDecimal b = new BigDecimal(inspectionQty);
		Float result = (a.multiply(new BigDecimal("100")).divide(b,BigDecimal.ROUND_HALF_DOWN,2)).floatValue();
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description 计算TL的方式
	 * @Date  2020/5/28  14:30
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
		bean.put("variables",variables);
		if ("current_lt".equals(key)){
			String temp  = "及时";
			String cqa = blackDashBoardMapper.getLtStandard("CQA问题");
			if (Double.valueOf(tl) > Double.valueOf(cqa)){
				temp = "不及时";
			}
			variables.put("conclusion_of_case",temp);
		   }
		}
		bean.put("variables",variables);
	}

	/**
	 * @Author yuyangyang
	 * @Description 初步原因分析节点修改CQA基础表的信息
	 * @Date  2020/7/9  10:42
	 * @Param
	 * @return
	 */
	public void  updateCqaInfo(QimsCqaInfo qimsCqaInfo,JSONObject bean){
		//获取imel号码
		String imelTrackID = qimsCqaInfo.getImelTrackID();
		bean = bean.getJSONObject("variables");
		List<String> list = stringToList(imelTrackID);
		String man = bean.getString("people");
		String machine = bean.getString("machines");
		String material = bean.getString("materials");
		String method = bean.getString("regulations");
		String environment = bean.getString("environment");
		cqaInfoMapper.updateCqaInfo(list,man,machine,material,method,environment);
	}

	/**
	 * @Author yuyangyang
	 * @Description 根据数据库规则转换数据
	 * @Date  2020/6/28  18:02
	 * @Param
	 * @return
	 */
	private List<JSONObject> transformationJson(List<JSONObject> list){
		List<JSONObject> parameterList = commonMapper.getTransferParameter("qims_cqa_info");
		JSONObject mapping = new JSONObject();
		Set<String> set = new HashSet<>();
		for (JSONObject json : parameterList){
			String parameter = json.getString("parameter");
			String key = parameter +"-"+json.getString("db_data");
			String value = json.getString("transfer_data");
			mapping.put(key,value);
			set.add(parameter);
		}
		for (JSONObject temp : list){
			for (String key:set){
				String string = temp.getString(key);
				if (isNotNull(string)){
					String str = key+"-"+string;
					String string1 = mapping.getString(str);
					if(isNotNull(string1)){
						temp.put(key,string1);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @Author yuyangyang
	 * @Description 页面展示数据库值与实际代表值转换
	 * @Date  2020/6/29  14:26
	 * @Param
	 * @return
	 */
	private List<QimsCqaInfo> transformation(List<QimsCqaInfo> list){
		for (QimsCqaInfo qims:list) {
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