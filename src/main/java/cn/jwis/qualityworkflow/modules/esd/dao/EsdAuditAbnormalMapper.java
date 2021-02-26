package cn.jwis.qualityworkflow.modules.esd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormal;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormalSearch;

@Mapper
public interface EsdAuditAbnormalMapper {


    int insert(EsdAuditAbnormal record);

    int updateByPrimaryKey(EsdAuditAbnormal record);

    @Select("select count(1) from esd_audit_abnormal where id = #{id}")
    Long getCountById(@Param("id") String id);

    @Select("select esd_audit_generator()")
    String getItemNumber();


    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM esd_audit_abnormal q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    EsdAuditAbnormal getQimsBlackInfo (@Param("taskId") String taskId);

    /**
     * @Author yuyangyang
     * @Description 获取上级用户信息
     * @Date  2020/7/3  11:32
     * @Param
     * @return
     */


    @Select("select superior from department_mapping where user_account = #{name}")
    String getSuperior(@Param("name") String name);


    @Select({ "<script>",
            "SELECT distinct ${parameter} from esd_audit_abnormal where ${parameter} is not null",
            "</script>" })
    List<String> getDropdownValue(@Param("parameter") String parameter);

    List<EsdAuditAbnormal>  getEsdAuditAbnormalInfoList(EsdAuditAbnormalSearch esdAuditAbnormalSearch);

    Long getEsdAuditAbnormalInfoCount(EsdAuditAbnormalSearch esdAuditAbnormalSearch);

    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM esd_audit_abnormal q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    EsdAuditAbnormal getEsdAuditInfo (@Param("taskId") String taskId);

}