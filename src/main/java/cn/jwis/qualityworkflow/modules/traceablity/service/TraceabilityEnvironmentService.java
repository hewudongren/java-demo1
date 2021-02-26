package cn.jwis.qualityworkflow.modules.traceablity.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityEnvironmentVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentConfiguration;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentData;

public interface TraceabilityEnvironmentService {
    JSONObject getTraceabilityEnvironmentData(QueryTraceabilityEnvironmentVo bean) throws Exception;

    void insertTraceabilityEnvironment(TraceabilityEnvironmentData bean);

    void exportTraceabilityEnvironmentData(HttpServletResponse response, HttpServletRequest request, QueryTraceabilityEnvironmentVo bean);

    JSONObject getTraEnvironmentPullDownInfo();

    List<String> getProbeNumber(JSONObject jsonObject);

    List<TraceabilityEnvironmentConfiguration> getEnvConfiguration(QueryTraceabilityEnvironmentVo bean);

    Long getEnvConfigurationCount(QueryTraceabilityEnvironmentVo bean);

    int insertEnvConfiguration(TraceabilityEnvironmentConfiguration bean);

    JSONObject getEnvConfigurationById(JSONObject jsonObject);

    void deleteEnvConfiguration(JSONObject jsonObject);

    JSONObject getEnvConfigurationPullDownInfo();
}
