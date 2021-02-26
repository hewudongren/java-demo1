package cn.jwis.qualityworkflow.modules.linequalify.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifyInfo;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifySearch;

@Mapper
public interface LineQualifyMapper {

	@Select({ "<script>",
			"SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState,ar.taskName FROM line_qualify_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState,task_name taskName FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
			"</script>" })
	LineQualifyInfo getLineQualifyDetailednessInfo(@Param("taskId") String taskId);

	List<LineQualifyInfo> getLineQualifyInfoList(LineQualifySearch lineQualifySearch);

	Long getLineQualifyInfoCount(LineQualifySearch lineQualifySearch);

	void saveLineQualifyInfo(LineQualifyInfo bean);

	@Update(" update line_qualify_info q set q.status=#{status} where q.id = #{id} ")
	void updateById(LineQualifyInfo bean);

	List<LineQualifyInfo> getCertificationList(LineQualifySearch lineQualifySearch);

	Long getCertificationCount(LineQualifySearch lineQualifySearch);

	@Select("select * from line_qualify_info where id =#{id}")
	LineQualifyInfo getlineQualifyInfo(@Param("id") String id);

	void updateLineQualifyById(LineQualifyInfo bean);

	@Select("select line_qualify_number()")
	String getItemNumber();

	@Select("SELECT task_name , GROUP_CONCAT(DISTINCT assignee) assignee , max(update_date) update_time " +
			"FROM task_record WHERE template_key = 'Line Qualify' AND workflow_business_id =#{id} GROUP BY task_name")
	List<JSONObject> getProgress(@Param("id") String id);

}