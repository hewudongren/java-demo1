package cn.jwis.qualityworkflow.modules.qims.dao;

import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;

import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QimsCqaInfoMapper {


    int insert(QimsCqaInfo record);

    int updateByPrimaryKey(QimsCqaInfo record);

    List<QimsCqaInfo> getQimsCqaInfoList(QimsCqaSearch qimsCqaSearch);

    Long getQimsCqaInfoListCount(QimsCqaSearch qimsCqaSearch);

    @Select({ "<script>",
            "SELECT distinct ${parameter} from qims_cqa_info where ${parameter} is not null",
            "</script>" })
    List<String> getDropdownValue(@Param("parameter") String parameter);

    @Select("SELECT qims_cqa_generator()")
    String getItemNumber();

    @Select("SELECT count(1) FROM qims_cqa_info WHERE id =#{id}")
    long getCountById(@Param("id") String id);

    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM qims_cqa_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    QimsCqaInfo getQimsCqaInfo (@Param("taskId") String taskId);

    @Select("SELECT distinct employee_num FROM traceability_man ")
    List<String> getManValue();

    @Select("SELECT distinct file_name FROM traceability_method")
    List<String> getMethodValue();

    @Select("SELECT  distinct position FROM traceability_environment_data")
    List<String> getEnvironmentValue();

    void  updateCqaInfo(@Param("list") List<String> list,@Param("man") String man,
                        @Param("machine") String machine,@Param("material") String material,
                        @Param("method") String method,@Param("environment") String environment);

    @Select("SELECT * FROM qims_cqa_info WHERE id =#{id}")
    QimsCqaInfo getQimsCqaInfoById(String id);
}