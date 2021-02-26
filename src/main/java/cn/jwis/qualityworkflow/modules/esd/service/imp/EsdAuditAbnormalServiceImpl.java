package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.ESDAUDITTEMPLATEKEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.DateUtil.getCurrentDate;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditADb;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditAExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditAExcelUs;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditBDb;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditBExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditBExcelUs;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormal;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormalSearch;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditAbnormalMapper;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditMainMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdAuditAbnormalService;
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
 * @Date 2020/7/2 19:54
 */
@Service
public class EsdAuditAbnormalServiceImpl extends BaseClass implements EsdAuditAbnormalService {

	@Autowired
	EsdAuditAbnormalMapper esdAuditAbnormalMapper;

	@Autowired
	WorkflowServer workflowServer;

	@Value("${qms.app.key}")
	private String tenantId;

	@Autowired
	private IDGeneratorRunner idGeneratorRunner;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	WorkFlowUtil workFlowUtil;
	@Autowired
	HistoryProcessRecordMapper historyProcessRecordMapper;
	@Autowired
	EsdAuditMainMapper esdAuditMainMapper;
	@Autowired
	TaskRecordMapper taskRecordDao;
	@Autowired
	UserServer userServer;
	@Autowired
	UserMapper userMapper;

	@Override
	@Transactional
	public void confirm(JSONObject bean) {
		String taskId = bean.getString("task_id");
		EsdAuditAbnormal esdAuditAbnormal = esdAuditAbnormalMapper.getQimsBlackInfo(taskId);
		//获取创建人，主题，项目编号
		String creator = esdAuditAbnormal.getCreator();
		String theme = null;
		String itemNumber = esdAuditAbnormal.getItemNumber();
		String state = bean.getString("state");
		JSONObject variables = bean.getJSONObject("variables");
		variables.put("handler",getCurrentUserName());
		variables.put("handlerDate",getCurrentDate());
		bean.put("variables",variables);
		List<String> keyList = new LinkedList<>();
		List<Object> valueList = new LinkedList<>();
		if ("原因分析".equals(state)){
			keyList.add("causal_analyst");keyList.add("causal_analyst_date");keyList.add("reason_analysiser");
			valueList.add(getCurrentUserName());valueList.add(getCurrentDate());valueList.add(userMapper.getSuperior(getCurrentUserName(),"COMMON"));
			addFiled(bean,keyList,valueList);
		}else  if ("原因分析审核".equals(state)){
			addFiled(bean,keyList,valueList);
		}else if ("改善措施".equals(state)){
			keyList.add("improvement_measures_date");keyList.add("improvement_measures_auditor");keyList.add("improvement_measures_department");
			valueList.add(getCurrentDate());valueList.add(userMapper.getSuperior(getCurrentUserName(),"COMMON"));valueList.add(workFlowUtil.getDepartment(getCurrentUserName(),"COMMON"));
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
		//不同的节点做不同的处理
		workFlowUtil.confirm(bean,ESDAUDITTEMPLATEKEY,"esd_audit_abnormal",creator,theme,itemNumber);
	}

	@Override
	@Transactional
	public void saveEsdAuditAbnormalInfo(EsdAuditAbnormal bean) {
		String id = bean.getId();
		long count = 0;
		if (isNotNull(id)){
			count = esdAuditAbnormalMapper.getCountById(id);
		}
		boolean flag = false;
		if (count == 0){
			//自动生成ID
			bean.setId(String.valueOf(idGeneratorRunner.nextId()));
			flag = true;
		}
		//检验员
		String auditer = bean.getAuditer();
		//自动生成创建人
		bean.setCreator(auditer);
		//当前处理人，处理部门
		bean.setAssigneer(auditer);
		bean.setAssigneerDepartment(workFlowUtil.getDepartment(auditer,"COMMON"));
		saveEsdAuditAbnormal(bean,id,flag);
	}

	@Override
	public List<String> getDropdownValue(String parameter) {
		return esdAuditAbnormalMapper.getDropdownValue(parameter);
	}

	@Override
	public List<EsdAuditAbnormal> getEsdAuditAbnormalInfoList(EsdAuditAbnormalSearch esdAuditAbnormalSearch) {
		List<EsdAuditAbnormal> result = new ArrayList<>();
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdAuditAbnormalSearch.setAssignee(userItemInfos);
		try {
			//分页页码处理
			pageChange(esdAuditAbnormalSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = esdAuditAbnormalMapper.getEsdAuditAbnormalInfoList(esdAuditAbnormalSearch);
		return result;
	}

	@Override
	public Long getEsdAuditAbnormalInfoCount(EsdAuditAbnormalSearch esdAuditAbnormalSearch) {
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdAuditAbnormalSearch.setAssignee(userItemInfos);
		Long count = esdAuditAbnormalMapper.getEsdAuditAbnormalInfoCount(esdAuditAbnormalSearch);
		return count;
	}

	@Override
	public void exportEsdAuditAbnormalInfoList(HttpServletResponse response, HttpServletRequest request, EsdAuditAbnormalSearch esdAuditAbnormalSearch) {
		String language = request.getHeader("Language");
		esdAuditAbnormalSearch.setPage(null);
		esdAuditAbnormalSearch.setSize(null);
		//将结束日期+1天,并添加用户
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		esdAuditAbnormalSearch.setAssignee(userItemInfos);
		List<EsdAuditAbnormal> list = esdAuditAbnormalMapper.getEsdAuditAbnormalInfoList(esdAuditAbnormalSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdAuditAbnormal bean:list) {
			JSONObject object = JSONObjectUtil.toJSONObject(bean);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,"esdAuditAbnormal.xlsx");
		String[] title = EsdAuditBExcel;
		String[] strings = EsdAuditBDb;
		if ("en-US".equals(language)){
			title = EsdAuditBExcelUs;
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
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request,EsdAuditADb,EsdAuditAExcel,EsdAuditAExcelUs);
	}

	@Override
	public Map<String, Object> getEsdAuditInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		EsdAuditAbnormal esdAuditAbnormal = esdAuditAbnormalMapper.getEsdAuditInfo(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",esdAuditAbnormal);
		//通过taskId判断任务处于哪个节点
		TaskRecord taskRecord = taskRecordDao.getByTaskId(bean.getTaskId());
		//判断当前人是否能够处理
		String assigneer = esdAuditAbnormal.getAssigneer();
		String status = esdAuditAbnormal.getStatus();
		String taskState = taskRecord.getTaskState();
		boolean enableHandleCurrentNode = false;
		if (status.equals(taskRecord.getTaskName()) && assigneer.equals(getCurrentUserName()) && isNull(taskState)){
			enableHandleCurrentNode = true;
		}
		detailedInfoMap.put("enableHandleCurrentNode",enableHandleCurrentNode);
		return detailedInfoMap;
	}


	@Transactional
	public synchronized void saveEsdAuditAbnormal(EsdAuditAbnormal bean,String id,boolean flag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (flag){
			bean.setItemNumber(esdAuditAbnormalMapper.getItemNumber());
		}
		//获取主题和项目编号
		String theme = null;
		String itemNumber = null;
		itemNumber = bean.getItemNumber();
		//判断是新起一个流程还是提交拟制单据节点数据
		if (flag) {
			//新增时项目编号变化
			esdAuditAbnormalMapper.insert(bean);
			if (isNotNull(id)) {
				historyProcessRecordMapper.deleteRecord(ESDAUDITTEMPLATEKEY, getCurrentUserName(), "新建ESD异常单");
			}
			//检验人为新建ESD异常单人（第一个节点）
			String name = bean.getAuditer();
			//责任人为第二个节点的原因分析人
			String personLiable = bean.getPersonLiable();
			String workflowId = bean.getId();
			// 1.启动QIMS黑色问题流程实例，
			String processInstanceId = workFlowUtil.startProcessInstance("creator", name, tenantId, ESDAUDITTEMPLATEKEY);
			// 获取最新的Task,
			String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
			JSONObject jsonObject = new JSONObject();
			//获取新建审核单传入的变量
			jsonObject = JSONObjectUtil.toJSONObject(bean);
			Date auditDate = jsonObject.getDate("auditDate");
			String format = sdf.format(auditDate);
            jsonObject.put("auditDate",format);
			//异常处理人，异常响应时间添加
			jsonObject.put("handler", getCurrentUserName());
			jsonObject.put("handlerDate", getCurrentDate());
			historyProcessRecord.save(BeanUtil.generateHistoryProcessRecord(jsonObject, HISOTORY_PROCESS_COMMIT, ESDAUDITTEMPLATEKEY, bean.getId(), "新建ESD异常单"));
			workFlowUtil.finishTask(processInstanceId, taskId, "causal_analyst", personLiable);
			// 任务记录表，保存申请Task记录
			String qimsId = bean.getId();
			workFlowUtil.saveEcnApplyTaskRecord(qimsId, processInstanceId, taskId, name, "新建ESD异常单", "Close", ESDAUDITTEMPLATEKEY, theme, itemNumber);
			// 当前流程的所有任务
			String status = workFlowUtil.nextNode(processInstanceId, qimsId, ESDAUDITTEMPLATEKEY, workflowId, name, theme, itemNumber);
			//需要修改
			bean.setStatus(status);
			//修改当前处理人,当前处理部门
			bean.setAssigneer(personLiable);
			bean.setAssigneerDepartment(workFlowUtil.getDepartment(personLiable,"COMMON"));
			esdAuditAbnormalMapper.updateByPrimaryKey(bean);
			esdAuditMainMapper.updateEsdAuditListSecondaryById(id);
		}else {
			//通过workflowId得到最新的taskId和processId
			JSONObject jsonObject = workFlowUtil.getConfirmJson(id,bean,"新建ESD异常单");
			confirm(jsonObject);
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核流程中业务上添加的字段
	 * @Date  2020/7/3  11:13
	 * @Param
	 * @return
	 */
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