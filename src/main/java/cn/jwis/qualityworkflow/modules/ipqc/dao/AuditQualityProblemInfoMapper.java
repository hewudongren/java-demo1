package cn.jwis.qualityworkflow.modules.ipqc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditQualityProblemInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryAuditQualityProblem;


@Mapper
@Component
public interface AuditQualityProblemInfoMapper {


    List<AuditQualityProblemInfo> getAuditQualityProblemInfo(QueryAuditQualityProblem bean);

    Long getAuditQualityProblemCount(QueryAuditQualityProblem bean);

    void insertAuditQualityProblemInfo(AuditQualityProblemInfo bean);

    void updateAuditQualityProblemInfo(AuditQualityProblemInfo bean);

    @Select("select audit_type from audit_quality_problem_info where id=#{workflowBusinessId}")
    String getAuditType(String workflowBusinessId);

    @Select("select creator from audit_quality_problem_info where id=#{id}")
    String getCreator(@Param("id") String id);

    List<AuditQualityProblemInfo> exportAuditQualityProblem(QueryAuditQualityProblem bean);

    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM  audit_quality_problem_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    AuditQualityProblemInfo getInfoByTaskId(String taskId);

    @Delete("delete from history_process_record where type='SAVE'  and workflow_type ='IPQCAuditProcess' and creator=#{creator} and workflow_businessId is null ")
    void deleteAuditQualityProblemInfo(@Param("creator") String creator);

    @Select("select * from audit_quality_problem_info where audit_type='IPQC稽核' and problem_attribute='红黄线' and problem_type='ESD防护' and (fault_type='工作台ESD接地不良' or fault_type='设备接地不良' or fault_type='桥架ESD接地不良') and create_date>=#{startTime} and create_date<=#{endTime}")
    List<AuditQualityProblemInfo> getIPQCAuditCount(@Param("startTime") String startTime, @Param("endTime")String endTime);

    @Select("select * from audit_quality_problem_info where audit_type='ODM稽核' and (fault_type='工作台ESD接地不良' or fault_type='设备接地不良' or fault_type='桥架ESD接地不良') and create_date>=#{startTime} and create_date<=#{endTime}")
    List<AuditQualityProblemInfo> getODMAuditCount(@Param("startTime")String startTime, @Param("endTime")String endTime);
}