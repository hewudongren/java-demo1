package cn.jwis.qualityworkflow.modules.traceablity.service;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityManVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMan;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TraceabilityManService {
    List<TraceabilityMan> getTraceabilityManList(QueryTraceabilityManVo bean);

    Long getTraceabilityManListCount(QueryTraceabilityManVo bean);

    void exportTraceabilityMan(HttpServletResponse response, HttpServletRequest request, QueryTraceabilityManVo bean);

    JSONObject getTraManPullDownInfo();
}
