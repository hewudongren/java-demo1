package cn.jwis.qualityworkflow.modules.ipqc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditQualityProblemInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryAuditQualityProblem;

public interface AuditQualityProblemService {
    List<AuditQualityProblemInfo> getAuditQualityProblemInfo(QueryAuditQualityProblem bean);

    Long getAuditQualityProblemCount(QueryAuditQualityProblem bean);

    void insertAuditQualityProblemInfo(AuditQualityProblemInfo bean)throws Exception;

    void confirm(JSONObject jsonObject)throws Exception;

    void exportAuditQualityProblem(HttpServletResponse response, HttpServletRequest request, QueryAuditQualityProblem bean);

    @Select ({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM  audit_quality_problem_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    Map<String, Object> getDetailednessInfo(QueryDetailedInfoVo bean)throws Exception;

    List<JSONObject> importAuditQualityProblem(MultipartFile file, HttpServletRequest request) throws Exception;

    void insertAuditQualityProblemInfoList(List<AuditQualityProblemInfo> list)throws Exception;

    void downLoadAuditQualityProblem(HttpServletResponse response, HttpServletRequest request, List<AuditQualityProblemInfo> list);

    void getCountAuditEsdEos(String startTime, String endTime);

    void  qimsSendMail();
}
