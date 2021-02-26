package cn.jwis.qualityworkflow.modules.qims.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:16
 */
@Mapper
public interface CqaDashBoardMapper {

	List<JSONObject> getAllAndClose(@Param("startTime") String startTime, @Param("endTime") String endTime);

	List<String> getOverdue(@Param("startTime") String startTime, @Param("endTime") String endTime);

	List<JSONObject> getDashBoardCloseSum( @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	List<JSONObject> getDashBoardCloseMolecule(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

	List<JSONObject> getDashBoardNotClose(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("temp") String temp);

    @Select("SELECT COUNT(1) sum,sum(if(`status`='结案',1,0)) pass FROM qims_cqa_info")
	JSONObject getAllData();

    List<JSONObject> getUnseasonalBola(@Param("type") String type);

	List<QimsCqaInfo> getUnseasonalList(@Param("page") Integer page, @Param("size") Integer size);

	Long getUnseasonalCount();

	List<JSONObject> getBlackDetails(@Param("department") String department, @Param("startTime") String startTime, @Param("endTime") String endTime);


}