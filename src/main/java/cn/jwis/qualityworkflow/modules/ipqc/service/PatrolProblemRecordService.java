package cn.jwis.qualityworkflow.modules.ipqc.service;

import cn.jwis.qualityworkflow.modules.ipqc.bean.PatrolProblemRecord;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryPatrolProblemRecord;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PatrolProblemRecordService {
    List<PatrolProblemRecord> getPatrolProblemRecordList(QueryPatrolProblemRecord bean);

    Long getPatrolProblemRecordCount(QueryPatrolProblemRecord bean);

    JSONObject insertPatrolProblemRecord(PatrolProblemRecord bean);

    void exportPatrolProblemRecord(HttpServletResponse response, HttpServletRequest request, QueryPatrolProblemRecord bean);

    JSONObject getPatrolProblemRecordPullDown();

    JSONObject getProblemDescription(JSONObject jsonObject);
}
