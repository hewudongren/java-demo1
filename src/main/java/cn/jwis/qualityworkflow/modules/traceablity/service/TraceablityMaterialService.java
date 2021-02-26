package cn.jwis.qualityworkflow.modules.traceablity.service;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceablityMaterialVo;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TraceablityMaterialService {
    JSONObject getTraceabilityMaterial(QueryTraceablityMaterialVo bean) throws  Exception;


    void exportTraceabilityMaterial(HttpServletResponse response, HttpServletRequest request, QueryTraceablityMaterialVo bean)throws  Exception;

    JSONObject getMaterialPullDownInfo(QueryTraceablityMaterialVo bean) throws  Exception;
}
