package cn.jwis.qualityworkflow.modules.qims.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackSearch;

@Mapper
public interface QimsBlackInfoMapper {


    int insert(QimsBlackInfo record);

    int updateByPrimaryKey(QimsBlackInfo record);

    List<QimsBlackInfo> getQimsBlackInfoList(QimsBlackSearch qimsBlackSearch);

    Long getQimsBlackInfoListCount(QimsBlackSearch qimsBlackSearch);
    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM qims_black_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    QimsBlackInfo getQimsBlackInfo (@Param("taskId") String taskId);

    @Select("SELECT qims_number_generator()")
    String getItemNumber();
    @Select({ "<script>",
            "SELECT distinct ${parameter} from qims_black_info where ${parameter} is not null",
            "</script>" })
    List<String> getDropdownValue(@Param("parameter") String parameter);

    @Select("SELECT DISTINCT model_category,manufacture_type,model_odm,business_model FROM t_model WHERE model =#{model}")
    JSONObject findModel(@Param("model") String model);

    @Select("select DISTINCT subsection from sub_to_site")
    List<String> getSubsection();

    @Select("select DISTINCT site from sub_to_site where subsection = #{subsection}")
    List<String> getSiteBySubsection(@Param("subsection") String subsection);

    @Select("select DISTINCT line from sub_to_line where subsection = #{subsection}")
    List<String> getLineBySubsection(@Param("subsection") String subsection);

    @Select("select DISTINCT phenomenon from sub_to_phe where subsection = #{subsection}")
    List<String> getPhenomenonBySubsection(@Param("subsection") String subsection);

    @Select("select DISTINCT model from t_model")
    List<String> getModel();

    @Select("select DISTINCT department from department_mapping where  department is not null")
    List<String> getDepartment();

    @Select("select DISTINCT user_account from department_mapping where department =#{parameter} and user_account is not null")
    List<String> getNameByDepartment(@Param("parameter") String parameter);

    @Select("SELECT count(1) FROM qims_black_info WHERE id =#{id}")
    long getCountById(@Param("id") String id);

    List<String> getLineValue(@Param("list") List<String> list);

    List<String> getSiteValue(@Param("list") List<String> list,@Param("lineList") List<String> lineList);

    @Select("SELECT * FROM qims_black_info WHERE id =#{id}")
    QimsBlackInfo getQimsBlackInfoById(String id);
}