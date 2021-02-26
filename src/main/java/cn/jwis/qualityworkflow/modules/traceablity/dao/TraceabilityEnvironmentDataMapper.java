package cn.jwis.qualityworkflow.modules.traceablity.dao;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityEnvironmentVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentConfiguration;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentData;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface TraceabilityEnvironmentDataMapper {

    @Insert("insert into traceability_environment_data(id,position,temperature,humidity,create_date) values(#{id},#{position},#{temperature},#{humidity},#{createDate})")
    void insertTraceabilityEnvironment(TraceabilityEnvironmentData bean);

    List<TraceabilityEnvironmentData> exportTraceabilityEnvironmentData(QueryTraceabilityEnvironmentVo bean);

    @Select("select line from traceability_environment_configuration")
    List<TraceabilityEnvironmentConfiguration> getTraEnvironmentPullDownInfo();

    @Select("select probe_number from traceability_environment_configuration where line=#{line} group by probe_number")
    List<String> getProbeNumber(JSONObject jsonObject);


    @Select("select create_date as time ,temperature as value from traceability_environment_data where position=#{probeNumber} and create_date>=#{startTime} and create_date<=#{endTime}")
    List<Map<String,Object>> getTemperatureData(QueryTraceabilityEnvironmentVo bean);

    @Select("select create_date as time ,humidity as value  from traceability_environment_data where position=#{probeNumber} and create_date>=#{startTime} and create_date<=#{endTime}")
    List<Map<String,Object>> getHumidityData(QueryTraceabilityEnvironmentVo bean);
    
    @Select("select create_date as time ,${parameter} as value  from traceability_environment_data where position=#{probeNumber} and create_date>=#{startTime} and create_date<=#{endTime}")
    List<Map<String,Object>> getParamData(QueryTraceabilityEnvironmentVo bean);

    @Select("select * from traceability_environment_configuration where line=#{line} and probe_number=#{probeNumber} and parameter=#{parameter}")
    TraceabilityEnvironmentConfiguration getEnvironmentConfiguration(QueryTraceabilityEnvironmentVo bean);
}