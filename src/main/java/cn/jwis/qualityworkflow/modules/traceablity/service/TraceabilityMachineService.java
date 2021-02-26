package cn.jwis.qualityworkflow.modules.traceablity.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMachineVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMachine;

public interface TraceabilityMachineService {
    List<TraceabilityMachine> getTraceabilityMachine(QueryTraceabilityMachineVo bean);

    Long getTraceabilityMachineCount(QueryTraceabilityMachineVo bean);

    int insertTraceabilityMachine(TraceabilityMachine bean);

    JSONObject getTraceabilityMachineById(JSONObject jsonObject);

    JSONObject getTraceabilityMachineChart(QueryTraceabilityMachineVo bean)throws  Exception;

    void deleteTraceabilityMachine(JSONObject jsonObject);

    JSONObject getDeviceName(JSONObject jsonObject);

    JSONObject getLineByDeviceType(String type);

    JSONObject getFourMeMachineChart(QueryTraceabilityMachineVo bean)throws  Exception;

    JSONObject getMachinePullDownInfo();

    void insetTraceabilityMachine(JSONObject jsonObject)throws Exception;

    JSONObject getMachineDeviceType();

    JSONObject getDeviceNoByDeviceType(String line, String type);
}
