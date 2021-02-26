package cn.jwis.qualityworkflow.modules.traceablity.dao;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityManVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TraceabilityManMapper {
    List<TraceabilityMan> getTraceabilityManList(QueryTraceabilityManVo bean);

    Long getTraceabilityManListCount(QueryTraceabilityManVo bean);

    List<TraceabilityMan> exportTraceabilityMan(QueryTraceabilityManVo bean);

    @Select ("select department_name,process,line,job_name,certification_result from traceability_man group by department_name,process,line,job_name,certification_result")
    List<TraceabilityMan> getTraManPullDownInfo();
}