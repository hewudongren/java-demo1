package cn.jwis.qualityworkflow.modules.qims.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:16
 */
@Mapper
public interface GrayDashBoardMapper {

	List<JSONObject> getAllAndClose(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<String> getOverdue(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime);

	List<JSONObject> getDashBoardCloseSum(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	List<JSONObject> getDashBoardCloseMolecule(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	List<JSONObject> getDashBoardNotClose(@Param("paragraphPartings") List<String> list, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

    @Select("SELECT COUNT(1) sum,sum(if(record_status='结案',1,0)) pass FROM gray_problem_info")
	JSONObject getAllData();

    List<JSONObject> getUnseasonalBola(@Param("type") String type);

	List<JSONObject> getUnseasonalList(@Param("page") Integer page, @Param("size") Integer size);

	Long getUnseasonalCount();

	List<JSONObject> getBlackDetails(@Param("department") String department, @Param("startTime") String startTime, @Param("endTime") String endTime);

}