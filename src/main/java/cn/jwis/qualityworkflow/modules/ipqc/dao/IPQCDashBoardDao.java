package cn.jwis.qualityworkflow.modules.ipqc.dao;


import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryDashboardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface IPQCDashBoardDao {

    @Select ({"<script>select count(*) as AddCount,count(case when status!='结案' or status is null then 1  end)as  ProcessingCount,count(case when status='结案' then 1  end)as  ClosedCount,count(case when update_date &gt;=DATE_ADD(create_date,INTERVAL 1 DAY) then 1  end)as  OverdueCount FROM `audit_quality_problem_info`  where  1=1 " ,
             "<if test = 'list != null and list.size > 0'>" +
             "and audit_type in " +
             "<foreach collection='list' index='index' item ='item' separator=',' open='('  close=')'> #{item} </foreach> </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            "</script>"})
    Map<String, Object> getIPQCDashBoard(@Param ("startTime") String startTime, @Param ("endTime") String endTime,@Param("list") List<String> list);


    @Select ({"<script>select audit_type,count(1) as AddCount,count(case when status!='结案' then 1  end)as  ProcessingCount,count(case when status='结案' then 1  end)as  ClosedCount,count(case when  update_date &gt;=DATE_ADD(create_date,INTERVAL 1 DAY)  then 1  end)as  OverdueCount FROM `audit_quality_problem_info` where  1=1 " ,
            "<if test = 'auditType != null and auditType != \"\"'>" ,
            " and  audit_type = #{auditType} </if>" ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            "</script>"})
    Map<String, Object> getIPQCDashBoardCount(@Param ("startTime")String startTime,@Param ("endTime") String endTime, @Param ("auditType")String auditType);

    @Select ({"<script>select count(1) as AddCount,count(case when status!='结案' then 1  end)as  ProcessingCount,count(case when status='结案' then 1  end)as  ClosedCount,count(case when update_date &gt;=DATE_ADD(create_date,INTERVAL 1 DAY)  then 1  end)as  OverdueCount FROM `esd_audit_abnormal`  where  1=1 " ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            "</script>"})
    Map<String, Object> getESDDashBoardCount(@Param ("startTime")String startTime, @Param ("endTime")String endTime);

    @Select({"<script>select ${groupBy} AS time," +
            " (ROUND((select count(1) from patrol_problem_record WHERE status = 'close' AND ${groupBy}= time)/(select count(1) from patrol_problem_record where ${groupBy}= time)*100,2)) as timelyClose,"+
            " (ROUND((select count(1) from patrol_problem_record WHERE (status ='closed' or status='close') AND ${groupBy}= time)/(select count(1) from patrol_problem_record where ${groupBy}= time)*100,2)) as ultimatelyClose"+
            "  FROM patrol_problem_record  where 1=1" +
            "<if test = 'process != null and process != \"\"'>" +
            " and  works_section = #{process} </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" +
            " and  create_date &gt;= #{startTime} </if>" +
            "<if test = 'endTime != null and endTime != \"\"'>"+
            "and create_date   &lt;= #{endTime} </if>" +
            " GROUP BY time ORDER BY time "+
            "</script>"})
    List<Map<String, Object>>  getAuditIssueDashBoard(QueryDashboardVo bean);

            @Select ({"<script>select owner,count(1)as  AuditIssueList FROM `patrol_problem_record` where 1=1" ,
            "<if test = 'process != null and process != \"\"'>" ,
            " and  works_section = #{process} </if>" ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
             " group by owner" ,
            "</script>"})
            List<Map<String, Object>> getAuditOwner(QueryDashboardVo bean);

    @Select ({"<script>select squad_leader,count(1)as  SquadLeaderList FROM `patrol_problem_record` where 1=1" ,
            "<if test = 'process != null and process != \"\"'>" ,
            " and  works_section = #{process} </if>" ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            " group by squad_leader" ,
            "</script>"})
    List<Map<String, Object>> getAuditSquadLeader(QueryDashboardVo bean);

    @Select ({"<script>SELECT a.fault_type,ROUND(a.sum1/a.sum2,4)  as sz FROM " +
            "(select fault_type,count(1) as sum1,(select count(fault_type) as sum2 from patrol_problem_record where fault_type is not null) AS sum2 from patrol_problem_record p where 1=1" ,
            "<if test = 'process != null and process != \"\"'>" ,
            " and  p.works_section = #{process} </if>" ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  p.create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
             "and p.create_date &lt;= #{endTime} </if>" ,
            " group by fault_type ) a " ,
            "</script>"})
    List<Map<String, Object>> getAuditFaultType(QueryDashboardVo bean);

    @Select ({"<script>select count(1) as AddCount,count(case when status!='结案' or status is null then 1  end)as  ProcessingCount,count(case when status='结案' then 1  end)as  ClosedCount,count(case when update_date &gt;=DATE_ADD(create_date,INTERVAL 1 DAY) then 1  end)as  OverdueCount FROM `esd_audit_abnormal`  where  1=1 " ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            "</script>"})
    Map<String, Object> getESDDashBoard(@Param("startTime") String startTime, @Param("endTime")String endTime);
}
