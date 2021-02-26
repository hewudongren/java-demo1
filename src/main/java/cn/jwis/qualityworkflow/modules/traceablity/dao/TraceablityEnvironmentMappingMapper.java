package cn.jwis.qualityworkflow.modules.traceablity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityEnvironmentVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentConfiguration;

@Component
@Mapper
public interface TraceablityEnvironmentMappingMapper {

    @Select("select position from traceablity_environment_mapping where line=#{line}  group by position")
    List<String> getPosition(QueryTraceabilityEnvironmentVo bean);

    @Select({"<script>" +
            "select t.* from traceability_environment_configuration t where 1=1" +
            "<if test = 'positionList != null and positionList.size > 0'>" +
            "and t.probe_number in" +
            "<foreach collection='positionList' index='index' item ='item' separator=',' open='('  close=')'>#{item} </foreach> </if>" +
            "<if test = 'parameterList != null and parameterList.size > 0'>" +
            "and t.parameter in" +
            "<foreach collection='parameterList' index='index' item ='item' separator=',' open='('  close=')'>#{item} </foreach> </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" +
            " and t.create_date &gt;= #{startTime} </if>" +
            "<if test = 'endTime != null and endTime != \"\"'>" +
            " and t.create_date &lt;= #{endTime} </if>" +
            "order by create_date desc limit #{pageNum},#{pageSize}"+
            "</script>"})
    List<TraceabilityEnvironmentConfiguration> getEnvConfiguration(QueryTraceabilityEnvironmentVo bean);
    @Select({"<script>" +
            "select count(1) from traceability_environment_configuration t where 1=1" +
            "<if test = 'positionList != null and positionList.size > 0'>" +
            "and t.probe_number in" +
            "<foreach collection='positionList' index='index' item ='item' separator='or' open='('  close=')'>#{item} </foreach> </if>" +
            "<if test = 'parameterList != null and parameterList.size > 0'>" +
            "and t.parameter in" +
            "<foreach collection='parameterList' index='index' item ='item' separator=',' open='('  close=')'>#{item} </foreach> </if>" +
            "<if test = 'startTime != null and startTime != \"\"'>" +
            " and t.create_date &gt;= #{startTime} </if>" +
            "<if test = 'endTime != null and endTime != \"\"'>" +
            " and t.create_date &lt;= #{endTime} </if>" +
            "</script>"})
    Long getEnvConfigurationCount(QueryTraceabilityEnvironmentVo bean);

    @Insert("insert into traceability_environment_configuration(id,line,probe_number,specification_floor_value,specification_up_value,specification_centre_value,unit,creator,create_date,remark,parameter)"+
              "  values(#{id},#{line},#{probeNumber},#{specificationFloorValue},#{specificationUpValue},#{specificationCentreValue},#{unit},#{creator},#{createDate},#{remark},#{parameter})")
    void insertEnvConfiguration(TraceabilityEnvironmentConfiguration bean);

    @Update("update traceability_environment_configuration set line=#{line},probe_number=#{probeNumber},specification_floor_value=#{specificationFloorValue},specification_up_value=#{specificationUpValue},specification_centre_value=#{specificationCentreValue},unit=#{unit},remark=#{remark},parameter=#{parameter} where id=#{id}")
    void updateEnvConfiguration(TraceabilityEnvironmentConfiguration bean);

    @Select("select * from traceability_environment_configuration where id=#{id}")
    JSONObject getEnvConfigurationById(JSONObject jsonObject);

    @Delete("delete from traceability_environment_configuration where id=#{id}")
    void deleteEnvConfiguration(JSONObject jsonObject);

    @Select("select * from traceability_environment_configuration group by probe_number,parameter")
    List<TraceabilityEnvironmentConfiguration> getEnvConfigurationPullDownInfo();

    @Select("select * from traceability_environment_configuration where line=#{line} and probe_number=#{probeNumber} and parameter=#{parameter} ")
    TraceabilityEnvironmentConfiguration getConfiguration(TraceabilityEnvironmentConfiguration bean);

    @Select("select DISTINCT parameter from traceability_environment_configuration where line=#{line} and probe_number=#{probeNumber}")
	List<String> getConfigurationParameter(@Param("line") String line, @Param("probeNumber")  String probeNumber);
}