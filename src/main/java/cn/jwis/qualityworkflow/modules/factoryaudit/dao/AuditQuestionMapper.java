package cn.jwis.qualityworkflow.modules.factoryaudit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionSearch;

@Mapper
public interface AuditQuestionMapper {

	@Select({ "<script>",
			"SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState,ar.taskName FROM audit_problem_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState,task_name taskName FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
			"</script>" })
	AuditQuestionInfo getAuditQuestionDetailednessInfo(@Param("taskId") String taskId);


	Long getAuditQuestionInfoCount(AuditQuestionSearch lineQualifySearch);

	void saveAuditQuestionInfo(AuditQuestionInfo bean);

	
	@Update(" update audit_problem_info q set q.status=#{status} where q.id = #{id} ")
	void updateById(AuditQuestionInfo bean);

	List<AuditQuestionInfo> getAuditQuestionInfoList(AuditQuestionSearch lineQualifySearch);


	List<JSONObject> getKeywordsPlato(AuditQuestionSearch auditQuestionSearch);


	List<Map<String, Object>> getAuditQuestionTrends(AuditQuestionSearch auditQuestionSearch);

	@Select("select * from audit_problem_info where id =#{id}")
	AuditQuestionInfo getAuditQuestionInfo(String id);

	@Select("SELECT audit_problem_number()")
	String getItemNumber();

}