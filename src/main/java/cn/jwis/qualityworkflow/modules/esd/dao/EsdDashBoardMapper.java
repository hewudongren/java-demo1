package cn.jwis.qualityworkflow.modules.esd.dao;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdTargetInfo;
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
 * @Date 2020/7/28 19:13
 */
@Mapper
public interface EsdDashBoardMapper {


     @Insert("insert into esd_dashboard_info(id,data_source,type,number,business_datetime) " +
			 "values (#{id},#{dataSource},#{type},#{number},#{date})")
	 void insert(@Param("id") String id ,@Param("dataSource") String dataSource,
				 @Param("type") String type,@Param("number") Integer number,@Param("date") String date);

     @Select("select count(1) from esd_dashboard_target where year=#{year} and month=#{month}")
     Long getTargetInfoByYearAndMonth(@Param("year") String year,@Param("month") String month);

	 @Select("select count(1) from esd_dashboard_target where year=#{year} and month=#{month} and id != #{id}")
	 Long getTargetInfoById(@Param("year") String year,@Param("month") String month,@Param("id")String id);

	 @Insert("insert into esd_dashboard_target(id,year,month,esd,eos,total,creator) " +
			"values (#{id},#{year},#{month},#{esd},#{eos},#{total},#{creator})")
     void  saveTargetInfo(EsdTargetInfo esdTargetInfo);

	 @Update("update esd_dashboard_target set year =#{year},month=#{month},esd=#{esd},eos=#{eos},total=#{total} where id =#{id}")
	 void  updateTargetInfo(EsdTargetInfo esdTargetInfo);

	@Delete("delete from esd_dashboard_target where id =#{id}")
	 void  deleteTargetInfo(@Param("id") String id);

	List<EsdTargetInfo> getTargetInfo(@Param("page") Integer page,@Param("size") Integer size,
									  @Param("startTime") String startTime,@Param("endTime") String endTime);

	Long getTargetInfoCount( @Param("startTime") String startTime,@Param("endTime") String endTime);

	List<JSONObject> getMoleculeInfo(@Param("molecule") String molecule,@Param("startTime") String startTime,@Param("endTime") String endTime);

	@Select("select * from esd_email_info WHERE category = #{category} order by update_date desc ")
	List<JSONObject> getEmailList(@Param("category") String category);

	@Update("update esd_email_info set main_delivery =#{mainDelivery},updator=#{updator},cc_person=#{ccPerson},is_effective =#{isEffective} where id = #{id}")
	void updateEmailInfo(@Param("updator") String updator,@Param("id") String id,@Param("mainDelivery") String mainDelivery,
						 @Param("ccPerson") String ccPerson,@Param("isEffective") String isEffective);
}