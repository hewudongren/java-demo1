package cn.jwis.qualityworkflow.modules.esd.dao;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @Description ESD检验单管理
 * @Author yuyangyang
 * @Date 2020/5/15 9:49
 */
@Mapper
public interface EsdAdministrationMapper {

	int  insertEsdInfo (EsdInfo esdInfo);

	int updateEsdInfoByPrimaryKey(EsdInfo esdInfo);

	@Select({ "<script>",
			"SELECT distinct ${parameter} from esd_info where ${parameter} is not null",
			"</script>" })
	 List<String> getDropdownValue(@Param("parameter") String parameter);

	 List<EsdInfo> findEsdAdministration(@Param("list") List<String> list, @Param("dateList") Set<String> dateList,
										 @Param("page") Integer page, @Param("size")Integer size);

	 Long findEsdAdministrationCount(@Param("list") List<String> list, @Param("dateList") Set<String> dateList);

	@Select({ "<script>",
			"SELECT CONCAT(detection_item,'(',unit,')') title,lower_limit min,upper_limit max FROM esd_inspection_standard_info WHERE category ='cycle' and sample_name =#{sampleName}",
			"</script>" })
	 List<JSONObject> findTableNameBySampleName(@Param("sampleName") String sampleName);

	@Select({ "<script>",
			"SELECT detection_item FROM esd_inspection_standard_info WHERE category ='cycle' and sample_name =#{sampleName}",
			"</script>" })
	List<String> findTableNameBySampleName2(@Param("sampleName") String sampleName);

	@Select({ "<script>",
			"SELECT CONCAT(detection_item,'(',unit,')') title FROM esd_inspection_standard_info WHERE category ='cycle' and sample_name =#{sampleName}",
			"</script>" })
	List<String> findNameBySampleName(@Param("sampleName") String sampleName);
	@Select({"<script>",
			"SELECT sampling_qty FROM esd_sampling_level_info WHERE category ='cycle' and  sample_name = #{sampleName} order by update_date desc limit 0,1",
			"</script>" })
	Integer getSamplingQty(@Param("sampleName")String sampleName);

	@Select({"<script>",
			"SELECT count(1) FROM esd_detail_info WHERE sample_name=#{sampleName} and detection_month =#{detectionMonth}",
			"</script>" })
	Long getCountBySampleAndMonth(@Param("sampleName")String sampleName,@Param("detectionMonth")String detectionMonth);

	@Select({"<script>",
			"SELECT * FROM esd_info WHERE sample_name=#{sampleName} and detection_month =#{detectionMonth}",
			"</script>" })
    EsdInfo getEsdBySampleAndMonth(@Param("sampleName")String sampleName,@Param("detectionMonth")String detectionMonth);

	@Select({"<script>",
			"SELECT * FROM esd_info WHERE id=#{id}",
			"</script>" })
	EsdInfo getEsdInfoById(String id);

	List<JSONObject> getEsdDetailInfoByEsdId(@Param("id") String id,@Param("isPass") List<String> isPass,@Param("startTime") String startTime,@Param("endTime") String endTime,
									@Param("page") Integer page,@Param("size") Integer size);

	Long getEsdDetailCountByEsdId(@Param("id") String id,@Param("isPass")  List<String> isPass,@Param("startTime") String startTime,@Param("endTime") String endTime);

	@Select({"<script>",
			"SELECT detection_qty FROM esd_info WHERE id=#{id}",
			"</script>" })
	Integer getDetectionQtyById(@Param("id") String id);

	@Select({"<script>",
			"SELECT count(1) FROM esd_detail_info WHERE esd_id =#{id} and  final_result ='OK'",
			"</script>" })
	Integer getQualifiedQty(@Param("id") String id);


	void  updatePassByPrimaryKey(@Param("id") String id,@Param("qualifiedQty") Integer qualifiedQty,@Param("passRate") Float passRate,
								 @Param("unQualifiedQty")Integer unQualifiedQty,@Param("rate") Float rate);

	@Select({"<script>",
			"SELECT * FROM esd_detail_info WHERE id =#{id}",
			"</script>" })
	JSONObject getEsdDetailInfoById(@Param("id") String id);

	@Select({"<script>",
			"SELECT count(1) FROM esd_detail_info WHERE sample_name=#{sampleName} and detection_month =#{detectionMonth} and is_qualified ='NG'",
			"</script>" })
	Long getNgCount(@Param("sampleName")String sampleName,@Param("detectionMonth")String detectionMonth);

	@Select({"<script>",
			"SELECT count(1) FROM esd_detail_info WHERE sample_name=#{sampleName} and detection_month =#{detectionMonth} and is_qualified ='NG' and final_result is not null",
			"</script>" })
	Long getNgDealCount(@Param("sampleName")String sampleName,@Param("detectionMonth")String detectionMonth);

	@Select({"<script>",
			"SELECT count(1) FROM esd_detail_info WHERE esd_id =#{id} and  is_qualified ='OK'",
			"</script>" })
	Integer getQualifiedQty2(@Param("id") String id);

}