package cn.jwis.qualityworkflow.modules.ipqc.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryDashboardVo;

public interface IPQCDashBoardService {
    Map<String, Object> getIPQCDashBoard(QueryDashboardVo bean);

    List getIPQCDashBoardCount(QueryDashboardVo bean);

    List<Map<String, Object>> getAuditIssueDashBoard(QueryDashboardVo bean)throws Exception;

    JSONObject getPatrolProblemRecordDashBoard(QueryDashboardVo bean);
}
