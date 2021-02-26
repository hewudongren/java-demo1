package cn.jwis.qualityworkflow.modules.esd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleSearch;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/11 14:29
 */
@Mapper
public interface EsdCycleMapper {

	 void saveEsdCycleInfo(EsdCycleInfo esdCycleInfo);

	 void updateEsdCycleInfo(EsdCycleInfo esdCycleInfo);

	@Select({ "<script>",
			"SELECT distinct ${parameter} from esd_cycle_info where ${parameter} is not null",
			"</script>" })
	 List<String> getDropdownValue(@Param("parameter") String parameter);

	 List<EsdCycleInfo> getEsdCycleList(EsdCycleSearch esdCycleSearch);

	 Long getEsdCycleListCount(EsdCycleSearch esdCycleSearch);

	 @Select({ "<script>",
			"SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM esd_cycle_info q," +
					"(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM " +
					"task_record WHERE task_id=#{taskId}) " +
					"ar where q.id=ar.workflow_business_id",
			"</script>" })
	 EsdCycleInfo getEsdCycleInfo(@Param("taskId") String taskId);

	@Select("select count(1) from esd_cycle_info where id = #{id}")
	 Long getCountById(@Param("id") String id);

	@Select("select distinct detection_month from esd_info where sample_name =#{name}")
	 List<String> getMonthByName(@Param("name") String name);

	@Select("select  sampling_qty samplingQty,detection_qty detectionQty,qualified_qty qualifiedQty,unqualified_qty unqualifiedQty,acceptance_rate acceptanceRate,final_acceptance_rate finalAcceptanceRate," +
			"nonconforming_disposal_rate nonconformingDisposalRate from esd_info where sample_name =#{sampleName} and detection_month =#{detectionMonth}")
	JSONObject getInfoByMonthAndName(@Param("sampleName") String sampleName,
									 @Param("detectionMonth") String detectionMonth);

	@Select("select esd_cycle_generator()")
	String getItemNumber();

	@Select("SELECT DISTINCT detection_month FROM esd_info WHERE sample_name =#{name}")
    List<String> getMonthsByName(@Param("name") String name);
}