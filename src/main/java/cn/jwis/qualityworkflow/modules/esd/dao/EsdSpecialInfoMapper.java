package cn.jwis.qualityworkflow.modules.esd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialSearch;

@Mapper
public interface EsdSpecialInfoMapper {

    int insert(EsdSpecialInfo record);


    int updateByPrimaryKey(EsdSpecialInfo record);

    @Select("SELECT esd_number_generator()")
    String getSpecialMiningNo();


    @Select({ "<script>",
            "SELECT distinct ${parameter} from esd_special_info where ${parameter} is not null",
            "</script>" })
    List<String> getDropdownValue(@Param("parameter") String parameter);

    List<EsdSpecialInfo> getEsdSpecialList(EsdSpecialSearch esdSpecialSearch);

    Long getEsdSpecialListCount(EsdSpecialSearch esdSpecialSearch);

    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM esd_special_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    EsdSpecialInfo getEsdSpecialDetails(@Param("taskId") String taskId);

    @Select("SELECT count(1) FROM esd_special_info WHERE id =#{id}")
    long getCountById(@Param("id") String id);
}