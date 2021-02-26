package cn.jwis.qualityworkflow.modules.ipqc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ipqc.bean.PatrolProblemRecord;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryPatrolProblemRecord;


@Mapper
@Component
public interface PatrolProblemRecordMapper {

    List<PatrolProblemRecord> getPatrolProblemRecordList(QueryPatrolProblemRecord bean);

    Long getPatrolProblemRecordCount(QueryPatrolProblemRecord bean);

    void insertPatrolProblemRecord(PatrolProblemRecord bean);

    List<PatrolProblemRecord> exportPatrolProblemRecord(QueryPatrolProblemRecord bean);

    @Update({"<script>update patrol_problem_record set cumulative_frequency=#{cumulativeFrequency},status=#{status},is_signature=#{isSignature},remark=#{remark},problem_picture = #{problemPicture}" +
            "<if test = 'statusUpdateDate != null and statusUpdateDate != \"\"'>" +
            " ,status_update_date = #{statusUpdateDate} </if>" +
            "where id=#{id}" +
            "</script>"})
    void updatePatrolProblemRecord(PatrolProblemRecord bean);


    @Select("select p.inspector from patrol_problem_record p where p.inspector is not null  group by p.inspector")
    List<PatrolProblemRecord> getPatrolProblemRecordPullDown();

    @Select("select * from patrol_problem_record")
    List<PatrolProblemRecord> getPatrolProblemRecordMapper();

    @Select("select *   from patrol_problem_record where frequency=#{frequency} and audit_date=#{auditDate} and audit_type=#{auditType} and model=#{model} and works_section=#{worksSection} and audit_template_detail_id=#{auditTemplateDetailId} and line=#{line} group by problem_description")
    List<PatrolProblemRecord> getProblemDescription(JSONObject jsonObject);

    @Select("select * from patrol_problem_record where frequency='晚班' and create_date>=#{startTime} and create_date<=#{endTime}")
    List<PatrolProblemRecord> getNighthtPatrolProblemRecord(@Param("startTime") Date startTime, @Param("endTime")Date endTime);

    @Select("select * from patrol_problem_record where frequency='白班' and create_date>=#{startTime} and create_date<=#{endTime}")
    List<PatrolProblemRecord> getDaytPatrolProblemRecord(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}