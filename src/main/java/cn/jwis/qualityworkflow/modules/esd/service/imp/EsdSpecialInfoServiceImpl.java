package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.ESDSPECIALTEMPLATEKEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.util.BeanUtil.addDay;
import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialDB;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialExcelName;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialExcelUS;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialInfoDB;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialInfoExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdSpecialInfoExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialSearch;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdSpecialInfoMapper;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdSpecialManageInfoMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdSpecialInfoService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/8 16:32
 */
@Service
public class EsdSpecialInfoServiceImpl extends BaseClass implements EsdSpecialInfoService {

	@Autowired
	EsdSpecialInfoMapper esdSpecialInfoMapper;

	@Autowired
	private IDGeneratorRunner idGeneratorRunner;

	@Autowired
	WorkflowServer workflowServer;

	@Value("${qms.app.key}")
	private String tenantId;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	WorkFlowUtil workFlowUtil;

	@Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;

	@Autowired
	EsdSpecialManageInfoMapper esdSpecialManageInfoMapper;
	@Autowired
	UserServer userServer;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveEsdSpecialInfo(EsdSpecialInfo bean) {
		String id = bean.getId();
		long count = 0;
		if (isNotNull(id)){
			count = esdSpecialInfoMapper.getCountById(id);
		}
		boolean flag = false;
		if (count == 0){
			//自动生成ID
			bean.setId(String.valueOf(idGeneratorRunner.nextId()));
			flag = true;
			bean.setSpecialMiningNo(esdSpecialInfoMapper.getSpecialMiningNo());
		}
		String currentUserName = getCurrentUserName();
		bean.setSpecialApplicant(currentUserName);
		bean.setAssigneer(currentUserName);
		bean.setAssigneerDepartment(workFlowUtil.getDepartment(currentUserName,"COMMON"));
		saveEcnRecord(bean,id,flag);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(JSONObject bean) {
		String taskId = bean.getString("task_id");
		EsdSpecialInfo esdSpecialDetails = esdSpecialInfoMapper.getEsdSpecialDetails(taskId);
		String creator = esdSpecialDetails.getSpecialApplicant();
		String theme = null;
		String itemNumber = esdSpecialDetails.getSpecialMiningNo();
		JSONObject variables = bean.getJSONObject("variables");
		variables.put("handler",getCurrentUserName());
		variables.put("handlerDate",getCurrentDate());
		bean.put("variables",variables);
		workFlowUtil.confirm(bean,ESDSPECIALTEMPLATEKEY,"esd_special_info",creator,theme,itemNumber);
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		List<String> list = esdSpecialInfoMapper.getDropdownValue(parameter);
		return list;
	}

	@Override
	public List<EsdSpecialInfo> getEsdSpecialList(EsdSpecialSearch esdSpecialSearch) {
		List<EsdSpecialInfo> result = new ArrayList<>();
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdSpecialSearch.setAssignee(userItemInfos);
		//分页页码处理
		try {
			pageChange(esdSpecialSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = esdSpecialInfoMapper.getEsdSpecialList(esdSpecialSearch);
		return result;
	}

	@Override
	public Long getEsdSpecialListCount(EsdSpecialSearch esdSpecialSearch) {
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdSpecialSearch.setAssignee(userItemInfos);
		Long count = esdSpecialInfoMapper.getEsdSpecialListCount(esdSpecialSearch);
		return count;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		Map<String,String> title = Title.getTitle(request,EsdSpecialInfoDB,EsdSpecialInfoExcel,EsdSpecialInfoExcelUS);
		return title;
	}

	@Override
	public void exportEsdSpecialList(EsdSpecialSearch esdSpecialSearch, HttpServletRequest request, HttpServletResponse response) {
		String language = request.getHeader("Language");
		esdSpecialSearch.setPage(null);
		esdSpecialSearch.setSize(null);
		try {
			addDay(esdSpecialSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdSpecialSearch.setAssignee(userItemInfos);
		String[] dbList = EsdSpecialDB;
		String[] excelList = EsdSpecialExcel;
		if ("en-US".equals(language)){
			excelList = EsdSpecialExcelUS;
		}
		List<EsdSpecialInfo> list = esdSpecialInfoMapper.getEsdSpecialList(esdSpecialSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdSpecialInfo esdSpecialInfo:list) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdSpecialInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,EsdSpecialExcelName);
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,excelList,dbList);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getEsdSpecialDetails(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		EsdSpecialInfo esdSpecialInfo = esdSpecialInfoMapper.getEsdSpecialDetails(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",esdSpecialInfo);
		//通过taskId判断任务处于哪个节点
		TaskRecord taskRecord = taskRecordDao.getByTaskId(bean.getTaskId());
		//判断当前人是否能够处理
		String assigneer = esdSpecialInfo.getAssignee();
		String status = esdSpecialInfo.getStatus();
		String taskState = taskRecord.getTaskState();
		boolean enableHandleCurrentNode = false;
		if (status.equals(taskRecord.getTaskName()) && assigneer.equals(getCurrentUserName()) && isNull(taskState)){
			enableHandleCurrentNode = true;
		}
		detailedInfoMap.put("enableHandleCurrentNode",enableHandleCurrentNode);
		return detailedInfoMap;
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveEcnRecord(EsdSpecialInfo bean, String id,boolean flag) {
		//获取主题和项目编号
		String theme = null;
		String itemNumber = null;
		itemNumber = bean.getSpecialMiningNo();
		if (flag) {
			//需要修改
			esdSpecialInfoMapper.insert(bean);
			if (isNotNull(id)) {
				historyProcessRecordMapper.deleteByPrimaryKey(id);
			}
			//获取需求提出人
			String name = bean.getDemandProposer();
			//获取ESD处理人
			String esdHandler = bean.getEsdHandler();
			//获取特采验证人
			String specialProcurementVerifier = bean.getSpecialProcurementVerifier();
			//获取部门审核人
			String departmentAuditor = bean.getDepartmentAuditor();
			String workflowId = bean.getId();
			// 1.启动异常品流程实例，
			String processInstanceId = workFlowUtil.startProcessInstance("creator", name, tenantId, ESDSPECIALTEMPLATEKEY);
			// 获取最新的Task,
			String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
			JSONObject jsonObject = new JSONObject();
			//获取新建审核单传入的变量
			jsonObject = JSONObjectUtil.toJSONObject(bean);
			jsonObject.put("handler", getCurrentUserName());
			jsonObject.put("handlerDate", getCurrentDate());
			historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject, HISOTORY_PROCESS_COMMIT, ESDSPECIALTEMPLATEKEY, bean.getId(), "新建特采单"));
			JSONObject variables = new JSONObject();
			variables.put("esd_handler", esdHandler);
			variables.put("department_auditor", departmentAuditor);
			variables.put("special_procurement_verifier",specialProcurementVerifier);
			workflowServer.finishTask(processInstanceId, taskId, variables, null);
			// 任务记录表，保存申请Task记录
			String esdId = bean.getId();
			workFlowUtil.saveEcnApplyTaskRecord(esdId, processInstanceId, taskId, getCurrentUserName(), "新建特采单", "Close", ESDSPECIALTEMPLATEKEY, theme, itemNumber);
			// 当前流程的所有任务
			String status = workFlowUtil.nextNode(processInstanceId, esdId, ESDSPECIALTEMPLATEKEY, workflowId, getCurrentUserName(), theme, itemNumber);
			//需要修改
			bean.setStatus(status);
			esdSpecialInfoMapper.updateByPrimaryKey(bean);
			esdSpecialManageInfoMapper.updateByPrimaryId(id);
		}else {
			//通过workflowId得到最新的taskId和processId
			JSONObject jsonObject = workFlowUtil.getConfirmJson(id,bean,"新建特采单");
			confirm(jsonObject);
		}
	}
}