package cn.jwis.qualityworkflow.modules.pcn.service.imp;


import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNInfoBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNListBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNTaskRecordBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.QueryPCNInfo;
import cn.jwis.qualityworkflow.modules.pcn.dao.PCNDao;
import cn.jwis.qualityworkflow.modules.pcn.service.PCNService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNConstants.Constants.PCNCLOSETASKSTATE;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNConstants.Constants.PCNHISOTORYPROCESSCOMMIT;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNConstants.Constants.PCNTEMPLATEKEY;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNTitle.PcnInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNTitle.PcnInfoExcel;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNTitle.PcnInfoTable;
import static cn.jwis.qualityworkflow.util.BeanUtil.generateHistoryProcessRecord;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;


@Service
public class PCNServiceImpl implements PCNService {

    @Autowired
    PCNDao pcnDao;

    @Autowired
    WorkflowServer workflowServer;

    @Value ("${qms.app.key}")
    private String tenantId;

    @Autowired
    IDGeneratorRunner idGeneratorRunner;

    @Autowired
    HistoryProcessRecordService historyProcessRecordService;

    @Autowired
    HistoryProcessRecordImpl historyProcessRecord;

    @Autowired
    PCNService pcnService;

    @Autowired
    HistoryProcessRecordImpl historyProcessRecordImpl;

    @Autowired
    WorkFlowUtil workFlowUtil;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServer userServer;

    private static SimpleDateFormat timeDate= new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     *
     * @Description: 查询PCN列表,用户的待办,处理
     * @author: jiazheng
     * @date: 2020/5/18
     * @param: bean
     * @return:
     * @throws: Exception
     */
    @Override
    public List<PCNListBean> getPCNList(QueryPCNInfo bean){
        String userItemInfos = userServer.getUserItemInfos("PCN-业务管理员");
        bean.setAssignee(userItemInfos);
        bean.setStartTime(bean.getStartTime()+" 00:00:00");
        bean.setEndTime(bean.getEndTime()+" 23:59:59");
        if (bean.getPageNum() != null && bean.getPageSize() != null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
        List<PCNListBean> resultList = pcnDao.getAllPcnList(bean);
        return resultList;
    }

    /**
     *
     * @Description: 统计条数
     * @author: jiazheng
     * @date: 2020/5/18
     * @param: bean
     * @return:
     * @throws: Exception
     */
    @Override
    public Long getPCNListCount(QueryPCNInfo bean) {
        String userItemInfos = userServer.getUserItemInfos("PCN-业务管理员");
        bean.setAssignee(userItemInfos);
        bean.setPageSize(bean.getPageNum());
        Long count=  pcnDao.getPCNListCount(bean);
        return  count;
    }

    @Override
    public Map<String, Object> getPCNDetailednessInfo(QueryDetailedInfoVo bean) throws Exception{
        Map<String, Object> detailedInfoMap = getDetailedInfoMap(bean);
        PCNInfoBean pcnInfoBean = pcnDao.getPCNDetailednessInfo(bean.getTaskId());
        detailedInfoMap.put("detailedInfo",pcnInfoBean);
        return detailedInfoMap;
    }

    @Override
    public void exportPCNData(HttpServletResponse response, HttpServletRequest request, QueryPCNInfo bean) {
        String userItemInfos = userServer.getUserItemInfos("PCN-业务管理员");
        bean.setAssignee(userItemInfos);
        bean.setStartTime(bean.getStartTime()+" 00:00:00");
        bean.setEndTime(bean.getEndTime()+" 23:59:59");
        List<PCNListBean> list = pcnDao.getExportPCNist(bean);
        // 将List<PCNListBean>  转换为   List<JSONObject>
        List<JSONObject> jsonObjects = new ArrayList<>(list.size());
        list.stream().forEach(d -> {
            JSONObject object = JSONObjectUtil.toJSONObject(d);
            String string="";
            String str="";  switch (object.get("changeTrigger").toString()){
                case "1" :
                    string+="工艺改善";
                    break;
                case "2" :
                    string+="设备改善";
                    break;
                case "3" :
                    string+="工装改善";
                    break;
                case "4" :
                    string+="成本改善";
                    break;
                case "5" :
                    string+="效率提升";
                    break;
                case "6" :
                    string+="操作手法改善";
                    break;
                case "7" :
                    string+="流程改善";
                    break;
                case "8" :
                    string+=object.get("others");
                    break;
                    default:
                        string = null;
                        break;
            }
            switch (object.get("status").toString()){
                case "CreatePCN" :
                    str+="拟制PCN单";
                    break;
                case "DepartmentReview" :
                    str+="部门审核";
                    break;
                case "QEReview" :
                    str+="QE审核";
                    break;
                case "ODMReview" :
                    str+="ODM审核";
                    break;
                case "EffectVerification" :
                    str+="效果验证";
                    break;
                case "EffectReview" :
                    str+="效果评审";
                    break;
                case "ChangeRelease" :
                    str+="变更发布申请";
                    break;
                case "ChangeApproval" :
                    str+="变更审批";
                    break;
                case "ODMClosingApproval" :
                    str+="ODM审批";
                    break;
                case "Close" :
                    str+="结案";
                    break;
                default:
                    string = null;
                    break;
            }
            object.put("changeTrigger",string);
            object.put("status",str);
            jsonObjects.add(object);
        });
        String[] title = PcnInfoExcel;
        String language = request.getHeader("Language");
        if (("en-US").equals(language)) {
            title = PcnInfoDbCamel;
        }
        Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, PcnInfoDbCamel);
        ExcelUtil.outputWorkbook(response, workbook, PcnInfoTable);
    }

    @Override
    public List<String> getChangeNameList() {
        String userItemInfos = userServer.getUserItemInfos("PCN-业务管理员");
        return  pcnDao.getChangeNameList(userItemInfos);
    }

    @Override
    public JSONObject getSuperiorByUser() {
        String string=getCurrentUserName();
        String superior=userMapper.getSuperior(string,"COMMON");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("superior",superior);
        return jsonObject;
    }


    /**
     *
     * @Description: 新增PCN单据
     * @author: jiazheng
     * @date: 2020/5/18
     * @param: bean
     * @return:
     * @throws: Exception
     */
    @Override
    public void saveFCNInfo(PCNInfoBean pcnInfoBean) throws Exception {
        pcnInfoBean.setId(String.valueOf(idGeneratorRunner.nextId()));
        pcnInfoBean.setCreateDate(new Date());
        pcnInfoBean.setItemNumber(pcnDao.getItemNumber());
        pcnInfoBean.setTargetContent(JSONArray.toJSONString(pcnInfoBean.getContent()));
        String currentUserName = getCurrentUserName();
        pcnInfoBean.setCreator(currentUserName);
        pcnInfoBean.setDate(timeDate.format(new Date()));
        List<String> modelList = pcnInfoBean.getModelList();
        List<String> appendicesList = pcnInfoBean.getAppendicesList();
        String string= StringUtils.join(modelList.toArray(), ",");
        pcnInfoBean.setModel(string);
        String str=StringUtils.join(appendicesList.toArray(), ",");
        pcnInfoBean.setAppendices(str);
        pcnDao.saveFCNInfo(pcnInfoBean);
        JSONObject jsonObject = JSONObjectUtil.toJSONObject(pcnInfoBean);
        pcnInfoBean.setState("CreatePCN");
        historyProcessRecordService.save(generateHistoryProcessRecord(jsonObject,PCNHISOTORYPROCESSCOMMIT,PCNTEMPLATEKEY,pcnInfoBean.getId(),pcnInfoBean.getState()));
        pcnDao.deleteCreatePCNRecord(pcnInfoBean.getState());
        // 1.启动PCN流程实例，
        String processInstanceId = startProcessInstance();
        String department_auditor=pcnInfoBean.getDepartmentAuditor();
        String itemNumber=pcnInfoBean.getItemNumber();
        String theme=pcnInfoBean.getChangeName();
        // 获取最新的Task,
        String taskId = workflowServer.getNewestProcessTask(tenantId, processInstanceId);
        finishTask(processInstanceId, taskId,department_auditor);
        // 任务记录表，保存申请Task记录
        String PcnId = pcnInfoBean.getId();
        savePcnApplyTaskRecord(PcnId, processInstanceId, taskId,itemNumber,theme);
        // 当前流程的所有任务
        List<Map<String, Object>> historyTaskList = workflowServer.getProcessHistoryTasks(processInstanceId);
        String processState = PCNCLOSETASKSTATE;
        String assignee = null;
        PCNTaskRecordBean pcnTaskRecordBean = null;
        // for 循环，如果某个任务的deleteReason字段为null,那它就是当前最新任务，取出任务名称，这就是当前流程的状态。
        for (Map<String, Object> historyTask : historyTaskList) {
            Object deleteReasonObj = historyTask.get("deleteReason");
            if (null == deleteReasonObj) {
                Object nameObj = historyTask.get("name");
                String taskName = String.valueOf(nameObj);
                Object idObj = historyTask.get("id");
                if (null == idObj) {
                    continue;
                }
                String id = String.valueOf(idObj);
                Object assigneeObj = historyTask.get("assignee");
                if (null == assigneeObj) {
                    continue;
                }
                assignee = String.valueOf(assigneeObj);
                processState = taskName;
                pcnTaskRecordBean = new PCNTaskRecordBean();
                pcnTaskRecordBean.setPcnId(PcnId);
                //pcnTaskRecordBean.setState(processState);
                pcnTaskRecordBean.setAssignee(assignee);
                pcnTaskRecordBean.setProcessInstanceId(processInstanceId);
                pcnTaskRecordBean.setTaskId(id);
                pcnTaskRecordBean.setTaskName(processState);
                pcnTaskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
                pcnTaskRecordBean.setCreateDate(new Date());
                pcnTaskRecordBean.setCreator(pcnInfoBean.getCreator());
                pcnTaskRecordBean.setItemNumber(pcnInfoBean.getItemNumber());
                pcnTaskRecordBean.setTheme(theme);
                pcnTaskRecordBean.setTemplateKey("PCNProcess");
                // 保存任务记录 流程Id assignee pcnId taskId 状态 taskName
                pcnDao.savePcnTaskRecord(pcnTaskRecordBean);
                PCNTaskRecordBean finalPcnTaskRecordBean = pcnTaskRecordBean;
                ThreadUtil.getThreadPool().execute(()->{
                    workFlowUtil.sendPcnEmailTohandler(pcnInfoBean.getItemNumber(), finalPcnTaskRecordBean,currentUserName);
                });
            }
        }
        pcnInfoBean.setState(processState);
        pcnInfoBean.setAssignee(assignee);
        pcnInfoBean.setStatus(processState);
        pcnDao.updatePcnInfo(pcnInfoBean);
    }

    private String startProcessInstance() {
        JSONArray variables = new JSONArray();
        JSONObject variableJson = new JSONObject();
        String currentUserName = getCurrentUserName();
        variableJson.put("name", "creator");
        variableJson.put("value", currentUserName);
        variables.add(variableJson);
        return workflowServer.startProcessInstance(tenantId, "PCNProcess", variables);
    }

    private String getCurrentUserName() {
        UserInfo currentUser = SessionHelper.getCurrentUser();
        String currentUserName = currentUser.getAccount();
        return currentUserName;
    }
    private void finishTask(String processInstanceId, String taskId,String department_auditor) {
        JSONObject variables = new JSONObject();
        variables.put("party",department_auditor);
        workflowServer.finishTask(processInstanceId, taskId, variables, null);
    }

    private void savePcnApplyTaskRecord(String PcnId, String processInstanceId, String taskId,String itemNumber,String theme) throws Exception{
        PCNTaskRecordBean pcnTaskRecordBean = new PCNTaskRecordBean();
        pcnTaskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
        pcnTaskRecordBean.setPcnId(PcnId);
        String currentUserName = getCurrentUserName();
        pcnTaskRecordBean.setAssignee(currentUserName);
        pcnTaskRecordBean.setProcessInstanceId(processInstanceId);
        pcnTaskRecordBean.setTaskId(taskId);
        pcnTaskRecordBean.setCreateDate(new Date());
        pcnTaskRecordBean.setTaskName("CreatePCN");
        pcnTaskRecordBean.setTaskState(PCNCLOSETASKSTATE);
        pcnTaskRecordBean.setItemNumber(itemNumber);
        pcnTaskRecordBean.setTheme(theme);
        pcnTaskRecordBean.setTemplateKey("PCNProcess");
        pcnTaskRecordBean.setCreator(currentUserName);
        pcnDao.savePcnTaskRecord(pcnTaskRecordBean);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirm(PCNInfoBean pcnInfoBean) throws Exception{
        String id = pcnInfoBean.getId();
        // 通过id获取创建人和项目编号
        Map<String, String> hashMap = pcnDao.getCreatorItemNumberById(id);
        String creator=hashMap.get("creator");
        String itemNumber = hashMap.get("item_number");
        String changeName = hashMap.get("change_name");
        // 获取前台传来的TaskId
        String taskId = pcnInfoBean.getTaskId();
        String processInstanceId = pcnInfoBean.getProcessInstanceId();
        JSONObject variables = new JSONObject();
        // 给下一节点处理人赋值，
        String state = pcnInfoBean.getState();
        List<String> strings = pcnDao.workflowVariableReflectList(state);
        Map<String, Object> localVariables = pcnInfoBean.getVariables();
        for (String string : strings) {
            if (localVariables.get(string) != null) {
                variables.put(string, localVariables.get(string));
            }
        }
        pcnInfoBean.setAssignee(getCurrentUserName());
        pcnInfoBean.setApproveDate(new Date());
        if ("DepartmentReview".equals(state)){
            String qeAudit=pcnDao.getQeAudit(pcnInfoBean.getId());
            variables.put("qeAudit",qeAudit);
        }else if("QEReview".equals(state) || "EffectReview".equals(state)){
            String item=pcnDao.getItem(pcnInfoBean.getId());
            if ("Inhouse".equals(item)){
                variables.put("item",item);
            }else{
                variables.put("item","ODM");
            }

        }
        //取出会签节点数量、会签已完成节点数量
        if ("EffectReview".equals(state) || "ChangeApproval".equals(state)){
            boolean isClose=false;
            int nrOfInstances = 0;
            int nrOfCompletedInstances = 0;
            List<Map<String, Object>> processVariables = workflowServer.getProcessVariables(processInstanceId);
            for (Map<String, Object> map : processVariables) {
                Object object2 = map.get("name");
                if (object2 == null) {
                    continue;
                }
                String name = String.valueOf(object2);
                if ("nrOfInstances".equals(name)) {
                    Object object3 = map.get("value");
                    nrOfInstances = Integer.parseInt(String.valueOf(object3));
                } else if ("nrOfCompletedInstances".equals(name)) {
                    Object object4 = map.get("value");
                    nrOfCompletedInstances = Integer.parseInt(String.valueOf(object4));
                }

            }
            //如果会签节点数量-会签已完成节点数量==1,说明是会签的最后的一个节点
            if("EffectReview".equals(state)){
                //一个评审不通过所有的都为close
                if((Integer)localVariables.get("verifyStatus")>0) {
                    List<String> list = pcnDao.getTaskState(processInstanceId, pcnInfoBean.getId());
                    for (int i = 0; i < list.size(); i++) {
                        // 调用接口，完成任务
                        workflowServer.finishTask(processInstanceId, list.get(i), variables, localVariables);
                        isClose = true;
                    }
                }
                //最后一个人审批完成,进入下一个节点
                if(nrOfInstances - nrOfCompletedInstances == 1 && "EffectReview".equals(state)){
                    isClose = true;
                    // 调用接口，完成任务
                    workflowServer.finishTask(processInstanceId, taskId, variables, localVariables);
                }

            }else{
                if((Integer)localVariables.get("changeStatus")>0){
                    List<String> list = pcnDao.getTaskState(processInstanceId, pcnInfoBean.getId());
                    for (int j = 0; j < list.size(); j++) {
                        // 调用接口，完成任务
                        workflowServer.finishTask(processInstanceId, list.get(j), variables, localVariables);
                        isClose = true;
                    }
                }
                if( nrOfInstances - nrOfCompletedInstances == 1 && "ChangeApproval".equals(state)){
                    isClose = true;
                    // 调用接口，完成任务
                    workflowServer.finishTask(processInstanceId, taskId, variables, localVariables);
                }
            }
            if (isClose) {
                updateProcessInfo(pcnInfoBean,itemNumber,creator,changeName);

            }else{
                // 调用接口，完成任务
                workflowServer.finishTask(processInstanceId, taskId, variables, localVariables);PCNTaskRecordBean taskRecord = new PCNTaskRecordBean();
                taskRecord.setProcessInstanceId(processInstanceId);
                taskRecord.setTaskId(taskId);
                //修改updateECNTaskRecord方法, 传processInstanceId、taskId.
                pcnDao.updatePcnTaskRecords(taskRecord);
                JSONObject jsonObject = JSONObjectUtil.toJSONObject(pcnInfoBean);
                CreateHistoryProcessVo historyProcessVo = generateHistoryProcessRecord(jsonObject,
                        PCNHISOTORYPROCESSCOMMIT, PCNTEMPLATEKEY, pcnInfoBean.getId(), pcnInfoBean.getState());
                historyProcessRecordService.appendHistoryProcess(historyProcessVo, variables);
                historyProcessRecordService.save(historyProcessVo);
            }
        }else{
            // 调用接口，完成任务
            workflowServer.finishTask(processInstanceId, taskId, variables, localVariables);
            updateProcessInfo(pcnInfoBean,itemNumber,getCurrentUserName(),changeName);
        }

    }
    @Transactional(rollbackFor = Exception.class)
    public void updateProcessInfo(PCNInfoBean pcnInfoBean,String itemNumber,String creator,String changeName) throws Exception{
        JSONObject jsonObject = JSONObjectUtil.toJSONObject(pcnInfoBean);
        //保存提交记录
        CreateHistoryProcessVo historyProcessVo = generateHistoryProcessRecord(jsonObject,
                PCNHISOTORYPROCESSCOMMIT, PCNTEMPLATEKEY, pcnInfoBean.getId(), pcnInfoBean.getState());
        JSONObject variables = jsonObject.getJSONObject("variables");
        historyProcessRecordService.appendHistoryProcess(historyProcessVo, variables);
        historyProcessRecordService.save(historyProcessVo);
        //删除提交的保存记录
        pcnDao.deleteFlowRecord(pcnInfoBean.getId(),pcnInfoBean.getState());
        String processInstanceId = pcnInfoBean.getProcessInstanceId();
        // 更新上个任务状态为关闭
        pcnDao.updatePcnTaskRecord(processInstanceId);
        // 当前流程的所有任务
        List<Map<String, Object>> historyTaskList = workflowServer.getProcessHistoryTasks(processInstanceId);
        String processState = PCNCLOSETASKSTATE;
        String assignee = null;
        PCNTaskRecordBean taskRecordBean = null;
        // for 循环，如果某个任务的deleteReason字段为null,那它就是当前最新任务，取出任务名称，这就是流程的状态。
        for (Map<String, Object> historyTask : historyTaskList) {
            Object deleteReasonObj = historyTask.get("deleteReason");
            if (null == deleteReasonObj) {
                Object nameObj = historyTask.get("name");
                String taskName = String.valueOf(nameObj);
                Object idObj = historyTask.get("id");
                String id = String.valueOf(idObj);
                Object assigneeObj = historyTask.get("assignee");
                assignee = String.valueOf(assigneeObj);
                processState = taskName;
                taskRecordBean = new PCNTaskRecordBean();
                taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
                taskRecordBean.setPcnId(pcnInfoBean.getId());
                if ("DepartmentReview".equals(processState)){
                    JSONObject object= pcnInfoBean.getVariables();
                    taskRecordBean.setAssignee(object.getString("departmentAuditor"));
                }else{
                    taskRecordBean.setAssignee(assignee);
                }

                taskRecordBean.setProcessInstanceId(processInstanceId);
                taskRecordBean.setTaskId(id);
                taskRecordBean.setTaskName(processState);
                taskRecordBean.setCreator(getCurrentUserName());
                taskRecordBean.setItemNumber(itemNumber);
                taskRecordBean.setTemplateKey("PCNProcess");
                taskRecordBean.setTheme(changeName);
                taskRecordBean.setCreateDate(new Date());
                // 保存任务记录 流程Id assignee ecnId taskId 状态 taskName
                pcnDao.savePcnTaskRecord(taskRecordBean);
                PCNTaskRecordBean finalTaskRecordBean = taskRecordBean;
                ThreadUtil.getThreadPool().execute(()->{
                    workFlowUtil.sendPcnEmailTohandler(itemNumber, finalTaskRecordBean,creator);
                });
            }
        }
        PCNInfoBean infoBean=setExportPrice(pcnInfoBean);
        if ("DepartmentReview".equals(processState)){
            JSONObject jsb = pcnInfoBean.getVariables();
            List<String> ModelList = (List<String>) jsb.get("modelList");
            List<String> appendicesList = (List<String>) jsb.get("appendicesList");
            String string="";
            String str="";
            if(ModelList!=null && ModelList.size()>0){
               string   =StringUtils.join(ModelList.toArray(), ",");
            }
            if(appendicesList!=null && appendicesList.size()>0){
               str=StringUtils.join(appendicesList.toArray(), ",");
            }
            jsb.put("model",string);
            jsb.put("state",processState);
            jsb.put("appendices",str);
            jsb.put("targetContent",JSONArray.toJSONString(jsb.get("content")));
            jsb.put("id",pcnInfoBean.getId());
            jsb.put("assignee",assignee);
            jsb.put("status",processState);
            pcnDao.updateCreatePCN(jsb);
        }else{
            infoBean.setState(processState);
            infoBean.setId(pcnInfoBean.getId());
            infoBean.setAssignee(assignee);
            infoBean.setStatus(processState);
            //计算LeadTime时间
            if ("Close".equals(processState)){
                Date date=pcnDao.getCreateDate(pcnInfoBean.getId());
                infoBean.setLeadTime(countLeadTime(date));
            }
            pcnDao.updatePcnInfo(infoBean);
        }


    }

    private String countLeadTime(Date date) throws ParseException {
        long from2 = date.getTime();
        long to2 = System.currentTimeMillis();
        Double hours = (double)(to2 - from2) / (1000 * 60 * 60);
        String str=String.format("%.2f", hours)+"h";
        return str;
    }



    private String  transitionResult(String string){
        String str="";
        if ("0".equals(string)){
            str+="通过";
        }else{
            str+="不通过";
        }
        return  str;
    }

    private PCNInfoBean setExportPrice(PCNInfoBean pcnInfoBean){
        PCNInfoBean infoBean = new PCNInfoBean();
        if (("DepartmentReview").equals(pcnInfoBean.getState())) {
            infoBean.setDepartmentAuditResults(transitionResult(pcnInfoBean.getVariables().getString("departmentAuditResults")));
            infoBean.setDepartmentAuditOpinion(pcnInfoBean.getVariables().getString("departmentAuditOpinion"));
            infoBean.setDepartmentAuditTime(timeDateFormat.format(pcnInfoBean.getApproveDate()));
        }else if("QEReview".equals(pcnInfoBean.getState())){
            infoBean.setQeAuditResults(transitionResult(pcnInfoBean.getVariables().getString("qeAuditResults")));
            infoBean.setQeAuditOpinion(pcnInfoBean.getVariables().getString("qeAuditOpinion"));
            infoBean.setQeAuditTime(timeDateFormat.format(pcnInfoBean.getApproveDate()));
        }else if("ODMReview".equals(pcnInfoBean.getState())){
            infoBean.setOdmAuditResults(transitionResult(pcnInfoBean.getVariables().getString("odmAuditResults")));
            infoBean.setOdmAuditOpinion(pcnInfoBean.getVariables().getString("odmAuditOpinion"));
            infoBean.setOdmAuditor(getCurrentUserName());
            infoBean.setOdmAuditTime(timeDateFormat.format(pcnInfoBean.getApproveDate()));
        }else if("EffectVerification".equals(pcnInfoBean.getState())){
            infoBean.setImportMode(pcnInfoBean.getVariables().getString("importMode"));
            infoBean.setSwitchDate(pcnInfoBean.getVariables().getString("switchDate"));
        }else if ("ODMClosingApproval".equals(pcnInfoBean.getState())){
            infoBean.setApproveResult(transitionResult(pcnInfoBean.getVariables().getString("noPass")));
            infoBean.setApproveTime(timeDateFormat.format(pcnInfoBean.getApproveDate()));
        }else if("ChangeApproval".equals(pcnInfoBean.getState())){
            infoBean.setApproveResult(transitionResult(pcnInfoBean.getVariables().getString("changeStatus")));
            infoBean.setApproveTime(timeDateFormat.format(pcnInfoBean.getApproveDate()));
        }
        return infoBean;
    }

    private boolean getEnableHandleCurrentNode(String taskId) throws Exception {
        // 获取当前流程未关闭的 节点 处理人列表
        TaskRecord taskRecord = pcnDao.getByTaskId(taskId);
        String taskState = taskRecord.getTaskState();
        if (CLOSE_TASK_STATE.equals(taskState)) {
            return false;
        }
        String assignee = taskRecord.getAssignee();
        // 判定能否包含当前处理人
        String currentUserName = getCurrentUserName();
        if (assignee != null && currentUserName.equals(assignee)) {
            return true;
        }
        return false;
    }
    public Map<String, Object> getDetailedInfoMap(QueryDetailedInfoVo bean) throws Exception {
        Map<String, Object> detailedInfoMap = historyProcessRecordImpl.getDetailMap(bean);
        // 判定当前处理人能否处理当前记录
        boolean enableHandleCurrentNode =getEnableHandleCurrentNode(bean.getTaskId());
        detailedInfoMap.put("enableHandleCurrentNode", enableHandleCurrentNode);
        return detailedInfoMap;
    }

}
