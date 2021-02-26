package cn.jwis.qualityworkflow.modules.pcn.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PCNDashBoardDao {
    @Select ({"<script>select item,count(1) as AddCount,count(case when status!='close' or status is null then 1  end)as  ProcessingCount,count(case when status='close' then 1  end)as  ClosedCount,count(case when update_date &gt;=DATE_ADD(create_date,INTERVAL 1 DAY) then 1  end)as  OverdueCount FROM `pcn_info`  where  1=1 " ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            "</script>"})
    Map<String, Object> getPCNDashBoard(@Param ("startTime") String startTime, @Param ("endTime") String endTime);

    @Select ({"<script>select item,count(1) as AddCount,count(case when status!='close' or status is null then 1  end)as  ProcessingCount,count(case when status='close' then 1  end)as  ClosedCount,count(case when update_date &gt;=DATE_ADD(create_date,INTERVAL 1 DAY)  then 1  end)as  OverdueCount FROM `pcn_info`  where item = #{s} " ,
            "<if test = 'startTime != null and startTime != \"\"'>" ,
            " and  create_date &gt;= #{startTime} </if>" ,
            "<if test = 'endTime != null and endTime != \"\"'>" ,
            "and create_date   &lt;= #{endTime} </if>" ,
            "</script>"})
    Map<String, Object> getPCNDashBoardCount(@Param ("startTime")String startTime,@Param ("endTime") String endTime,@Param ("s")String s);


    @Select ("select count(1) from pcn_info")
    Object getCount();
}
