package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.ESDCYCLETEMPLATEKEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;
import static cn.jwis.qualityworkflow.util.Title.EsdCycleInfExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdCycleInfExcel2;
import static cn.jwis.qualityworkflow.util.Title.EsdCycleInfExcelUS2;
import static cn.jwis.qualityworkflow.util.Title.EsdCycleInfoDB;
import static cn.jwis.qualityworkflow.util.Title.EsdCycleInfoDB2;
import static cn.jwis.qualityworkflow.util.Title.EsdCycleInfoExcelName;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleSearch;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditAbnormalMapper;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdCycleMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdCycleService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.BeanUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.UserUtil;
import cn.jwis.qualityworkflow.util.WorkFlowUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/11 14:32
 */
@Service
public class EsdCycleServiceImpl extends BaseClass implements EsdCycleService {
	@Autowired
	EsdCycleMapper esdCycleMapper;

	@Autowired
	EsdAuditAbnormalMapper esdAuditAbnormalMapper;

	@Autowired
	WorkflowServer workflowServer;

	@Value("${qms.app.key}")
	private String tenantId;
	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	private IDGeneratorRunner idGeneratorRunner;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	WorkFlowUtil workFlowUtil;
	@Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;
	@Autowired
	UserServer userServer;
	@Autowired
	UserMapper userMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(JSONObject bean) {
		String taskId = bean.getString("task_id");
		EsdCycleInfo esdCycleInfo = esdCycleMapper.getEsdCycleInfo(taskId);
		String state = bean.getString("state");
		String creator = esdCycleInfo.getCreator();
		String theme = null;
		String itemNumber = esdCycleInfo.getItemNumber();
		JSONObject variables = bean.getJSONObject("variables");
		variables.put("handler",getCurrentUserName());
		variables.put("handlerDate",getCurrentDate());
		bean.put("variables",variables);
		List<String> keyList = new LinkedList<>();
		List<Object> valueList = new LinkedList<>();
		if ("原因分析".equals(state)){
			//自动指定节点处理人
			keyList.add("reason_analysiser");keyList.add("cause_analysis_date");
	        valueList.add(userMapper.getSuperior(getCurrentUserName(),"COMMON"));
	        valueList.add(getCurrentDate());
			addFiled(bean,keyList,valueList);
		}else  if ("原因分析审核".equals(state)){
			addFiled(bean,keyList,valueList);
		}else if ("改善措施".equals(state)){
			keyList.add("improvement_measures_date");keyList.add("handlerDepartment");
			valueList.add(getCurrentDate());valueList.add(workFlowUtil.getDepartment(getCurrentUserName(),"COMMON"));
			addFiled(bean,keyList,valueList);
		}else  if ("措施审核".equals(state)){
			addFiled(bean,keyList,valueList);
		}else if ("效果验证".equals(state)){
			keyList.add("verifiedor");keyList.add("verified_date");
			valueList.add(getCurrentUserName());valueList.add(getCurrentDate());
			addFiled(bean,keyList,valueList);
		}else {
			keyList.add("case_closing_date");
			valueList.add(getCurrentDate());
			addFiled(bean,keyList,valueList);
		}
		workFlowUtil.confirm(bean,ESDCYCLETEMPLATEKEY,"esd_cycle_info",creator,theme,itemNumber);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveEsdCycleInfo(EsdCycleInfo bean) {
		String id = bean.getId();
		long count = 0;
		if (isNotNull(id)){
			//驳回情形
			count = esdCycleMapper.getCountById(id);
		}
		boolean flag = false;
		if (count == 0){
			//自动生成ID
			bean.setId(String.valueOf(idGeneratorRunner.nextId()));
			flag = true;
		}
		bean.setCreator(UserUtil.getCurrentUserName());
		saveEcnRecord(bean,id,flag);
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		List<String> list = esdCycleMapper.getDropdownValue(parameter);
		return list;
	}

	@Override
	public List<EsdCycleInfo> getEsdCycleList(EsdCycleSearch esdCycleSearch) {
		List<EsdCycleInfo> result = new ArrayList<>();
		//分页页码处理
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdCycleSearch.setAssignee(userItemInfos);
		pageChange(esdCycleSearch);
		result = esdCycleMapper.getEsdCycleList(esdCycleSearch);
		return result;
	}

	@Override
	public Long getEsdCycleListCount(EsdCycleSearch esdCycleSearch) {
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdCycleSearch.setAssignee(userItemInfos);
		Long count = esdCycleMapper.getEsdCycleListCount(esdCycleSearch);
		return count;
	}

	@Override
	public void exportEsdCycleList(HttpServletResponse response, EsdCycleSearch esdCycleSearch) {
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdCycleSearch.setAssignee(userItemInfos);
		esdCycleSearch.setPage(null);
		esdCycleSearch.setSize(null);
		//将结束日期+1天,并添加用户
		List<EsdCycleInfo> esdCycleList = esdCycleMapper.getEsdCycleList(esdCycleSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdCycleInfo esdCycleInfo:esdCycleList) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdCycleInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,EsdCycleInfoExcelName);
		String[] title = EsdCycleInfExcel;
		String[] strings = EsdCycleInfoDB;
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getEsdCycleInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		EsdCycleInfo esdCycleInfo = esdCycleMapper.getEsdCycleInfo(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",esdCycleInfo);
		//通过taskId判断任务处于哪个节点
		TaskRecord taskRecord = taskRecordDao.getByTaskId(bean.getTaskId());
		//判断当前人是否能够处理
		String assigneer = esdCycleInfo.getAssignee();
		String status = esdCycleInfo.getStatus();
		String taskState = taskRecord.getTaskState();
		boolean enableHandleCurrentNode = false;
		if (status.equals(taskRecord.getTaskName()) && assigneer.equals(getCurrentUserName()) && isNull(taskState)){
			enableHandleCurrentNode = true;
		}
		detailedInfoMap.put("enableHandleCurrentNode",enableHandleCurrentNode);
		return detailedInfoMap;
	}

	@Override
	public List<String> getMonthByName(String name) {
		return esdCycleMapper.getMonthByName(name);
	}

	@Override
	public JSONObject getInfoByMonthAndName(String sampleName, String detectionMonth) {
		return esdCycleMapper.getInfoByMonthAndName(sampleName,detectionMonth);
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request,EsdCycleInfoDB2,EsdCycleInfExcel2,EsdCycleInfExcelUS2);
	}

	@Override
	public List<String> getMonthsByName(String name) {
		return esdCycleMapper.getMonthsByName(name);
	}


	@Transactional
	public void saveEcnRecord(EsdCycleInfo bean,String id,boolean flag) {
		if (flag){
			//第一次起流程（还有驳回流程情况）
			//获取主题
			String theme = null;
			String itemNumber = esdCycleMapper.getItemNumber();
			//获取项目编号
			//需要修改
			//1.保存业务数据 （存入业务表）
			esdCycleMapper.saveEsdCycleInfo(bean);
			//2.如果是驳回， 删除保存的历史记录
			if (isNotNull(id)){
				historyProcessRecordMapper.deleteRecord(ESDCYCLETEMPLATEKEY, getCurrentUserName(), "新建ESD异常单");
			}
			//获取下一个任务的代理人
			String name = bean.getCausalAnalyst();
			//获取业务id
			String workflowId = bean.getId();
			// 3.启动异常品流程实例，（需要流程实例的唯一标识符，动态任务处理人变量）
			String processInstanceId = workFlowUtil.startProcessInstance("creator",getCurrentUserName(),tenantId,ESDCYCLETEMPLATEKEY);
			// 4.获取最新的Task,（根据流程实例获取当前活动的任务）
			String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
			JSONObject jsonObject = new JSONObject();
			//获取新建审核单传入的变量 将java对象转换成json对象
		    jsonObject = JSONObjectUtil.toJSONObject(bean);
		    //当前处理人，当前处理时间添加
			jsonObject.put("handler", getCurrentUserName());
			jsonObject.put("handlerDate", getCurrentDate());
			//5.保存业务和流程组成的任务数据（存入历史表）
			historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject,HISOTORY_PROCESS_COMMIT,ESDCYCLETEMPLATEKEY,bean.getId(),"新建ESD异常单"));
			//6.完成任务 （填充下一个节点的变量）
			workFlowUtil.finishTask(processInstanceId, taskId,"causal_analyst",name);
			//7. 任务记录表，保存申请Task记录 （将已经完成的任务存入任务表）
			String esdId = bean.getId();
			workFlowUtil.saveEcnApplyTaskRecord(esdId,processInstanceId, taskId,getCurrentUserName(),"新建ESD异常单","Close",ESDCYCLETEMPLATEKEY,theme,itemNumber);
			// 当前流程的所有任务
			//启动下一个任务
			String status = workFlowUtil.nextNode(processInstanceId,esdId,ESDCYCLETEMPLATEKEY,workflowId,getCurrentUserName(),theme,itemNumber);
			//需要修改 更新业务数据的状态
			bean.setStatus(status);
			esdCycleMapper.updateEsdCycleInfo(bean);
		}else {
			//通过workflowId得到最新的taskId和processId
			JSONObject jsonObject = workFlowUtil.getConfirmJson(id,bean,"新建ESD异常单");
			confirm(jsonObject);
		}

	}



	/**
	 * page和size的转换
	 */
	private void pageChange(EsdCycleSearch esdCycleSearch){
		Integer page = esdCycleSearch.getPage();
		Integer size = esdCycleSearch.getSize();
		if (isNotNull(page) && isNotNull(size)){
			page = (page-1)*size;
			esdCycleSearch.setPage(page);
		}
	}

	private void addFiled(JSONObject bean,List<String> keylist,List<Object> valueList){
		JSONObject variables = bean.getJSONObject("variables");
		if (isNotNull(keylist)){
			for (int i = 0; i <keylist.size() ; i++) {
				variables.put(keylist.get(i),valueList.get(i));
			}
		}
		bean.put("variables",variables);
	}

}