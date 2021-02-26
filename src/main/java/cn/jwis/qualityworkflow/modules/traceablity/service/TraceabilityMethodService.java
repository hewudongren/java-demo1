package cn.jwis.qualityworkflow.modules.traceablity.service;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMethodVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMethod;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TraceabilityMethodService {
    List<TraceabilityMethod> getTraceabilityMethodList(QueryTraceabilityMethodVo bean);

    Long getTraceabilityMethodCount(QueryTraceabilityMethodVo bean);

    void exportTraceabilityMethod(HttpServletResponse response, HttpServletRequest request, QueryTraceabilityMethodVo bean);

    JSONObject getTraMethodPullDownInfo();
}
