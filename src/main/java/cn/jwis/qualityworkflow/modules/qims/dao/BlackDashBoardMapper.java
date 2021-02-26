package cn.jwis.qualityworkflow.modules.qims.dao;

import cn.jwis.qualityworkflow.modules.qims.bean.BlackDashSearch;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:16
 */
@Mapper
public interface BlackDashBoardMapper {

	List<JSONObject> getAllAndClose(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<String> getOverdue(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<JSONObject> getDashBoardCloseSum(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	List<JSONObject> getDashBoardCloseMolecule(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	List<JSONObject> getDashBoardNotClose(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	@Select("SELECT COUNT(1) sum,sum(if(`status`='结案',1,0)) pass FROM qims_black_info")
	JSONObject getAllData();

	List<JSONObject> getUnseasonalBola(@Param("type") String type);

	List<QimsBlackInfo> getUnseasonalList(@Param("page") Integer page, @Param("size") Integer size);

	Long getUnseasonalCount();

	List<JSONObject> getBlackDetails(@Param("department") String department, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<JSONObject> getBatchProblem(BlackDashSearch blackDashSearch);

	List<QimsBlackInfo> exportBatchProblem(BlackDashSearch blackDashSearch);

	@Select("SELECT  *  FROM holiday_maintenance where date =#{date}")
	JSONObject getHolidayByDate(@Param("date") String date);

	@Insert("insert into holiday_maintenance (id,date,type,creator,remarks) values (#{id},#{date},#{type},#{creator},#{remarks})")
	void saveHoliday(@Param("id") String id, @Param("date") String date, @Param("type") String type, @Param("creator") String creator, @Param("remarks") String remarks);

	@Update("update holiday_maintenance set date = #{date},type =#{type},remarks=#{remarks} where id = #{id}")
	void updateHoliday(@Param("id") String id, @Param("date") String date, @Param("type") String type, @Param("remarks") String remarks);

	@Delete("delete from holiday_maintenance where id =#{id}")
	void deleteHoliday(@Param("id") String id);

	@Select("select * from holiday_maintenance order by update_date desc limit #{page},#{size}")
	List<JSONObject> getHolidayList(@Param("page") Integer page, @Param("size") Integer size);

	@Select("select count(1) from holiday_maintenance ")
	Long getHolidayCount();

	@Select("SELECT DISTINCT date FROM holiday_maintenance WHERE type = '节假日'")
	List<String> getHoliday();

	@Select("SELECT DISTINCT date FROM holiday_maintenance WHERE type = '补班'")
	List<String> getWorkOvertimeList();

	@Select("select sum(standard) from qims_lt_standard_info where type = #{type}")
	String getLtStandard(@Param("type") String type);

	@Select("SELECT DISTINCT department FROM task_record WHERE template_key = #{type} and department is not null")
	List<String> getDepartmentValue(@Param("type") String type);

	@Select("SELECT DISTINCT assigneer_department FROM gray_problem_info WHERE assigneer_department is not null")
	List<String> getGrayDepartment();

	@Select("SELECT * FROM qims_standard_info WHERE type = #{type}")
	List<JSONObject> getStandardValue(@Param("type") String type);

	@Update("update qims_standard_info set updator = #{name},standard_value=#{value} where id = #{id}")
	void updateStandardValue(@Param("id") String id, @Param("value") Float value, @Param("name") String name);

	@Select("SELECT ${key} FROM qims_target_info WHERE problem_type = #{problemType} and cycle =#{cycle} order by update_date desc limit 0,1")
	String getTarget(@Param("problemType") String problemType,@Param("cycle") Integer cycle,
					 @Param("key") String key);
	@Select("select * from qims_lt_standard_info order by update_date desc")
	List<JSONObject> getLtList();

	@Update("update qims_lt_standard_info set standard =#{standard},updator=#{updator} where id = #{id}")
	void updateLtList(@Param("standard") String standard,@Param("id") String id,@Param("updator")String updator);
}