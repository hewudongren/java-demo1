package cn.jwis.qualityworkflow.modules.traceablity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMethodVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMethod;

@Mapper
@Component
public interface TraceabilityMethodMapper {

    List<TraceabilityMethod> getTraceabilityMethodList(QueryTraceabilityMethodVo bean);

    Long getTraceabilityMethodCount(QueryTraceabilityMethodVo bean);

    List<TraceabilityMethod> exportTraceabilityMethod(QueryTraceabilityMethodVo bean);

    @Select ("select file_owner from traceability_method group by file_owner")
    List<TraceabilityMethod> getTraManPullDownInfo();
}