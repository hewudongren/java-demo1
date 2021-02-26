package cn.jwis.qualityworkflow.modules.pcn.dao;

import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNInfoBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNListBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNTaskRecordBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.QueryPCNInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface PCNDao {

    @Select ({"<script>select p.*,ar.processInstanceId,MAX(ar.taskId) as taskId from pcn_info p," +
            " (SELECT pcnId,processInstanceId,taskId,state FROM pcn_task_record where 1=1 " +
            "<if test = 'assignee != null and assignee != \"\"'>" +
            " and assignee = #{assignee} </if>" +
            ") ar "+
            " where p.id=ar.pcnId"+
            "<if test = 'changeNameList != null and changeNameList.size > 0'>" +
            "and" +
            "<foreach collection='changeNameList' index='index' item ='item' separator='or' open='('  close=')'>p.change_name like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'itemList != null and itemList.size > 0'>" +
            "and " +
            "<foreach collection='itemList' index='index' item ='item' separator='or' open='('  close=')'> p.item like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'modelList != null and modelList.size > 0'>" +
            "and" +
            "<foreach collection='modelList' index='index' item ='item' separator='or' open='('  close=')'> p.model like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'changeTypeList != null and changeTypeList.size > 0'>" +
            "and " +
            "<foreach collection='changeTypeList' index='index' item ='item' separator='or' open='('  close=')'> p.change_type like CONCAT('%','${item}','%')  </foreach> </if>" +
            "<if test = 'changeTriggerList != null and changeTriggerList.size > 0'>" +
            "and " +
            "<foreach collection='changeTriggerList' index='index' item ='item' separator='or' open='('  close=')'> p.change_trigger like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" +
            " and p.create_date &gt;= #{startTime} </if>" +
            "<if test = 'endTime != null and endTime != \"\"'>" +
            " and p.create_date &lt;= #{endTime} </if>" +
            "  group by pcnId,processInstanceId order by create_date desc limit #{pageNum},#{pageSize}" +
            "</script>"})
    List<PCNListBean> getAllPcnList(QueryPCNInfo bean);


    @Select ({"<script>select count(1) from pcn_info p," +
            " (SELECT pcnId,processInstanceId,taskId,state FROM pcn_task_record where 1=1 " +
            "<if test = 'assignee != null and assignee != \"\"'>" +
            " and assignee = #{assignee} </if>" +
            "group by pcnId,processInstanceId) ar "+
            " where p.id=ar.pcnId"+
            "<if test = 'changeNameList != null and changeNameList.size > 0'>" +
            "and" +
            "<foreach collection='changeNameList' index='index' item ='item' separator='or' open='('  close=')'>p.change_name like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'itemList != null and itemList.size > 0'>" +
            "and " +
            "<foreach collection='itemList' index='index' item ='item' separator='or' open='('  close=')'> p.item like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'modelList != null and modelList.size > 0'>" +
            "and" +
            "<foreach collection='modelList' index='index' item ='item' separator='or' open='('  close=')'> p.model like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'changeTypeList != null and changeTypeList.size > 0'>" +
            "and " +
            "<foreach collection='changeTypeList' index='index' item ='item' separator='or' open='('  close=')'> p.change_type like CONCAT('%','${item}','%')  </foreach> </if>" +
            "<if test = 'changeTriggerList != null and changeTriggerList.size > 0'>" +
            "and " +
            "<foreach collection='changeTriggerList' index='index' item ='item' separator='or' open='('  close=')'> p.change_trigger like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" +
            " and p.create_date &gt;= #{startTime} </if>" +
            "<if test = 'endTime != null and endTime != \"\"'>" +
            " and p.create_date &lt;= #{endTime} </if>" +
            "</script>"})
    Long getPCNListCount(QueryPCNInfo bean);


    @Insert ("INSERT INTO pcn_info(id,item_number, item_type, change_name, item, model, change_type,presentation_department,works_section,applicant,date,change_trigger,what,`where`,why,who,`when`,how,appendices,test_plan,target_content,delivery_list,department_auditor,qe_auditor,create_date,creator,others) "
            + " VALUES(#{id},#{itemNumber},#{itemType},#{changeName},#{item},#{model},#{changeType},#{presentationDepartment},#{worksSection},#{applicant},#{date},#{changeTrigger},#{what},#{where},#{why},#{who},#{when},#{how},#{appendices},#{testPlan},#{targetContent},#{deliveryList},#{departmentAuditor},#{qeAuditor},#{createDate},#{creator},#{others})")
    void saveFCNInfo(PCNInfoBean pcnInfoBean);

    @Insert ("INSERT INTO pcn_task_record(id, processInstanceId, assignee,pcnId, taskState,taskName,taskId,state,createDate,template_key,department,item_number,theme,creator) "
            + " VALUES(#{id}, #{processInstanceId}, #{assignee}, #{pcnId}, #{taskState},#{taskName},#{taskId},#{state},#{createDate},#{templateKey},#{department},#{itemNumber},#{theme},#{creator})")
    void savePcnTaskRecord(PCNTaskRecordBean pcnTaskRecordBean);


    @Update ({ "<script>",
            "update pcn_info p set p.state=#{state},p.assignee=#{assignee},p.status=#{status}"+
                    " <if test = 'leadTime != null  and leadTime != \"\"'>" +
                    " ,p.leadTime = #{leadTime} </if>" +
                    " <if test = 'departmentAuditor != null  and departmentAuditor != \"\"'>" +
                    " ,p.department_auditor = #{departmentAuditor} </if>" +
                    " <if test = 'qeAuditor != null  and qeAuditor != \"\"'>" +
                    " ,p.qe_auditor = #{qeAuditor} </if>" +
                    " <if test = 'departmentAuditResults != null  and departmentAuditResults != \"\"'>" +
                    " ,p.department_audit_results = #{departmentAuditResults} </if>" +
                    " <if test = 'departmentAuditOpinion != null  and departmentAuditOpinion != \"\"'>" +
                    " ,p.department_audit_opinion = #{departmentAuditOpinion} </if>" +
                    " <if test = 'departmentAuditTime != null  and departmentAuditTime != \"\"'>" +
                    " ,p.department_audit_time = #{departmentAuditTime} </if>" +
                    " <if test = 'qeAuditResults != null  and qeAuditResults != \"\"'>" +
                    " ,p.qe_audit_results = #{qeAuditResults} </if>" +
                    " <if test = 'qeAuditOpinion != null  and qeAuditOpinion != \"\"'>" +
                    " ,p.qe_audit_opinion = #{qeAuditOpinion} </if>" +
                    " <if test = 'qeAuditTime != null  and qeAuditTime != \"\"'>" +
                    " ,p.qe_audit_time = #{qeAuditTime} </if>" +
                    " <if test = 'odmAuditResults != null  and odmAuditResults != \"\"'>" +
                    " ,p.odm_audit_results = #{odmAuditResults} </if>" +
                    " <if test = 'odmAuditOpinion != null  and odmAuditOpinion != \"\"'>" +
                    " ,p.odm_audit_opinion = #{odmAuditOpinion} </if>" +
                    " <if test = 'odmAuditor != null  and odmAuditor != \"\"'>" +
                    " ,p.odm_auditor = #{odmAuditor} </if>" +
                    " <if test = 'odmAuditTime != null  and odmAuditTime != \"\"'>" +
                    " ,p.odm_audit_time = #{odmAuditTime} </if>" +
                    " <if test = 'importMode != null  and importMode != \"\"'>" +
                    " ,p.import_mode = #{importMode} </if>" +
                    " <if test = 'switchDate != null  and switchDate != \"\"'>" +
                    " ,p.switch_date = #{switchDate} </if>" +
                    " <if test = 'approveResult != null  and approveResult != \"\"'>" +
                    " ,p.approve_result = #{approveResult} </if>" +
                    " <if test = 'approveTime != null  and approveTime != \"\"'>" +
                    " ,p.approve_time = #{approveTime} </if>" +
                    " where p.id = #{id}" +
                    "</script>"})
    void updatePcnInfo(PCNInfoBean pcnInfoBean);

    /**
     * @Description:更新PCN流程Task节点信息
     * @author jiazheng
     * @date 2018年8月21日
     * @param processInstanceId
     */
    @Update({ "<script>",
            "update pcn_task_record p set p.taskState='Close' where p.processInstanceId = #{processInstanceId} and p.taskState is null ",
            "</script>" })
    void updatePcnTaskRecord(String processInstanceId);


@Select ({"<script>",
        "SELECT qe_auditor FROM pcn_info where id = #{id}"+
        "</script>"
})
 String getQeAudit(@Param ("id") String id);

 @Select ("select `variable` from workflow_variable_reflect where node = #{state}")
 List<String> workflowVariableReflectList(String state);

 @Select ("select `item` from pcn_info where id = #{id}")
 String getItem(@Param ("id") String id);

    @Select ({ "<script>",
            "select ar.assignee,p.*,ar.processInstanceId,ar.taskId,ar.taskState from pcn_info p," +
            " (SELECT pcnId,processInstanceId,taskId,taskState,assignee FROM pcn_task_record where taskId=#{taskId})ar "+
             " where p.id=ar.pcnId" ,
            "</script>" })
    PCNInfoBean getPCNDetailednessInfo(@Param ("taskId") String taskId);



    @Select ("select taskId from pcn_task_record where pcnId = #{id} and processInstanceId=#{processInstanceId} and taskState is null")
    List<String> getTaskState(@Param ("processInstanceId")String processInstanceId, @Param ("id")String id);

    @Update ({ "<script>",
            "update pcn_task_record p set p.taskState='Close' where p.processInstanceId = #{processInstanceId} and taskId=#{taskId} and p.taskState is null ",
            "</script>" })
    void updatePcnTaskRecords(PCNTaskRecordBean taskRecord);


    @Select ("select taskId from pcn_task_record where taskId=#{id} ")
    String getTaskId(@Param ("id")String id);


    @Select ("select p.create_date from pcn_info p where p.id=#{id} ")
    Date getCreateDate(@Param ("id")String id);


    @Select ({ "<script>"+
            "select p.change_name from pcn_info p," +
            " (SELECT pcnId,processInstanceId FROM pcn_task_record where 1=1" +
            "<if test = 'assignee != null and assignee != \"\"'>" +
            " and assignee = #{assignee} </if>" +
            " group by pcnId,processInstanceId) ar "+
            " where p.id=ar.pcnId group by  p.change_name"+
            "</script>" })
    List<String> getChangeNameList(@Param ("assignee")String assignee);

    @Select("SELECT pcn_number_generator()")
    String getItemNumber();


    @Delete("DELETE from history_process_record where workflow_businessId = 'create' and workflow_type = 'PCNProcess'" +
            "and type = 'SAVE' and workflow_node =#{State}")
    void deleteCreatePCNRecord(@Param ("State")String state);

    
    @Delete("DELETE from history_process_record where workflow_businessId = #{id} and workflow_type = 'PCNProcess'" +
            "and type = 'SAVE' and workflow_node =#{State}")
    void deleteFlowRecord(@Param ("id")String id,@Param ("State")String state);


    @Select ({"<script>select p.*,ar.processInstanceId,MAX(ar.taskId) as taskId from pcn_info p," +
            " (SELECT pcnId,processInstanceId,taskId,state FROM pcn_task_record where 1=1 " +
            "<if test = 'assignee != null and assignee != \"\"'>" +
            " and assignee = #{assignee} </if>" +
            ") ar "+
            " where p.id=ar.pcnId"+
            "<if test = 'changeNameList != null and changeNameList.size > 0'>" +
            "and p.change_name in " +
            "<foreach collection='changeNameList' index='index' item ='item' separator=',' open='('  close=')'> #{item} </foreach> </if>" +
            "<if test = 'itemList != null and itemList.size > 0'>" +
            "and p.item in " +
            "<foreach collection='itemList' index='index' item ='item' separator=',' open='('  close=')'> #{item} </foreach> </if>" +
            "<if test = 'modelList != null and modelList.size > 0'>" +
            "and" +
            "<foreach collection='modelList' index='index' item ='item' separator='or' open='('  close=')'> p.model like CONCAT('%','${item}','%') </foreach> </if>" +
            "<if test = 'changeTypeList != null and changeTypeList.size > 0'>" +
            "and p.change_type in " +
            "<foreach collection='changeTypeList' index='index' item ='item' separator=',' open='('  close=')'> #{item} </foreach> </if>" +
            "<if test = 'changeTriggerList != null and changeTriggerList.size > 0'>" +
            "and p.change_trigger in " +
            "<foreach collection='changeTriggerList' index='index' item ='item' separator=',' open='('  close=')'> #{item} </foreach> </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" +
            " and p.create_date &gt;= #{startTime} </if>" +
            "<if test = 'endTime != null and endTime != \"\"'>" +
            " and p.create_date &lt;= #{endTime} </if>" +
            "  group by pcnId,processInstanceId order by create_date desc" +
            "</script>"})
    List<PCNListBean> getExportPCNist(QueryPCNInfo bean);
    @Update ({ "<script>",
            "update pcn_info p set p.state=#{state},p.assignee=#{assignee},p.status=#{status}"+
                    " <if test = 'item != null  and item != \"\"'>" +
                    " ,p.item = #{item} </if>" +
                    " <if test = 'itemType != null  and itemType != \"\"'>" +
                    " ,p.item_type = #{itemType} </if>" +
                    " <if test = 'changeName != null  and changeName != \"\"'>" +
                    " ,p.change_name = #{changeName} </if>" +
                    " <if test = 'model != null  and model != \"\"'>" +
                    " ,p.model = #{model} </if>" +
                    " <if test = 'changeType != null  and changeType != \"\"'>" +
                    " ,p.change_type = #{changeType} </if>" +
                    " <if test = 'presentationDepartment != null  and presentationDepartment != \"\"'>" +
                    " ,p.presentation_department = #{presentationDepartment} </if>" +
                    " <if test = 'worksSection != null  and worksSection != \"\"'>" +
                    " ,p.works_section = #{worksSection} </if>" +
                    " <if test = 'date != null  and date != \"\"'>" +
                    " ,p.date = #{date} </if>" +
                    " <if test = 'changeTrigger != null  and changeTrigger != \"\"'>" +
                    " ,p.change_trigger = #{changeTrigger} </if>" +
                    " <if test = 'what != null  and what != \"\"'>" +
                    " ,p.what = #{what} </if>" +
                    " <if test = 'where != null  and where != \"\"'>" +
                    " ,p.where = #{where} </if>" +
                    " <if test = 'why != null  and why != \"\"'>" +
                    " ,p.why = #{why} </if>" +
                    " <if test = 'who != null  and who != \"\"'>" +
                    " ,p.who = #{who} </if>" +
                    " <if test = 'when != null  and when != \"\"'>" +
                    " ,p.when = #{when} </if>" +
                    " <if test = 'how != null  and how != \"\"'>" +
                    " ,p.how = #{how} </if>" +
                    " <if test = 'state != null  and state != \"\"'>" +
                    " ,p.state = #{state} </if>" +
                    " <if test = 'appendices != null  and appendices != \"\"'>" +
                    " ,p.appendices = #{appendices} </if>" +
                    " <if test = 'testPlan != null  and testPlan != \"\"'>" +
                    " ,p.test_plan = #{testPlan} </if>" +
                    " <if test = 'targetContent != null  and targetContent != \"\"'>" +
                    " ,p.target_content = #{targetContent} </if>" +
                    " <if test = 'deliveryList != null  and deliveryList != \"\"'>" +
                    " ,p.delivery_list = #{deliveryList} </if>" +
                    " <if test = 'others != null  and others != \"\"'>" +
                    " ,p.others = #{others} </if>" +
                    " <if test = 'departmentAuditor != null  and departmentAuditor != \"\"'>" +
                    " ,p.department_auditor = #{departmentAuditor} </if>" +
                    " <if test = 'qeAuditor != null  and qeAuditor != \"\"'>" +
                    " ,p.qe_auditor = #{qeAuditor} </if>" +
                    " where p.id = #{id}" +
                    "</script>"})
    void updateCreatePCN(JSONObject jsb);


    @Select("SELECT * from pcn_task_record WHERE taskId = #{taskId}")
    TaskRecord getByTaskId(@Param("taskId")String taskId);


    @Select("select creator,item_number,change_name  from pcn_info where id=#{id}")
    Map<String, String> getCreatorItemNumberById(String id);
}
