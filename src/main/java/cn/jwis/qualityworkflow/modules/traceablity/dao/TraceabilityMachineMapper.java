package cn.jwis.qualityworkflow.modules.traceablity.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMachineVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMachine;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMachineData;


@Mapper
@Component
public interface TraceabilityMachineMapper {

    List<TraceabilityMachine> getTraceabilityMachine(QueryTraceabilityMachineVo bean);

    Long getTraceabilityMachineCount(QueryTraceabilityMachineVo bean);

    @Select ("select  table_name from traceability_machine_data group by table_name")
    List<String> getTraceabilityMachineList();

    @Select("select filed_name from traceability_machine_data where table_name=#{tableName}")
    List<String> getTraceabilityMachineFiledName(@Param("tableName") String tableName);

    @Insert ({"<script>" +
            "INSERT INTO ${tableName}" ,
            "<foreach collection='list' index='index' item='item' separator='' open='' close='' >",
            "<if test = 'index == 0'>",
            "<foreach collection='item' index='key' item='value' separator=',' open='(' close=')' >",
            "${key} </foreach>VALUES</if> ",
            "</foreach> "+
            "<foreach collection='list' index='index' item='item' separator=',' open='' close='' >",
            " <foreach collection='item' index='key' item='value' separator=',' open='(' close=')' >",
            "#{value}</foreach>",
            "</foreach> </script>"
    })
    void insetTraceabilityMachine(@Param("tableName")String tableName,@Param("list")List list);

    @Select({"<script>" +
            "select max(created_time) from t_plasma" +
            "</script>"})
    Date getMaxCreatedTime();

    @Select({"<script>" +
            "select max(createtime) from dust_free_shed" +
            "</script>"})
    Date getMaxCreateTime();
    @Select({"<script>" +
            "select max(measure_time) from glue_dispenser_weight" +
            "</script>"})
    Date getMaxMeasureTime();
    @Select({"<script>" +
            "select max(t_creation_time) from t_pressure_visualization" +
            "</script>"})
    Date getMaxCreationTime();

    void insertTraceabilityMachine(TraceabilityMachine bean);

    @Select("select * from traceability_machine where id=#{id}")
    JSONObject getTraceabilityMachineById(JSONObject jsonObject);

    @Update("update traceability_machine t set t.device_no=#{deviceNo},area=#{area},t.line=#{line},t.device_type=#{deviceType},t.unit=#{unit},t.specification_floor_value=#{specificationFloorValue},t.specification_up_value=#{specificationUpValue},t.specification_centre_value=#{specificationCentreValue},parameter=#{parameter} where id=#{id}")
    void updateTraceabilityMachine(TraceabilityMachine bean);

    @Delete("delete from traceability_machine where id=#{id}")
    void deleteTraceabilityMachine(JSONObject jsonObject);

    @Select("select * from traceability_machine")
    List<TraceabilityMachine> getMachinePullDownInfo();

    @Select("select t.device from traceability_machine t where t.process=#{process} and t.line=#{line} group by t.device")
    List<String> getDeviceName(JSONObject jsonObject);

    @Select("select t.parameter from traceability_machine t where t.device=#{device} group by t.parameter")
    List<String> getParameter(JSONObject jsonObject);


    @Select("select ${dateTime} as time,${filedName} as value from ${tableName} where ${filedName} is not null and  ${deviceNoName}=#{deviceNo} and ${lineName}=#{line} and ${dateTime} >= #{startTime} and ${dateTime} <= #{endTime} order by ${dateTime}")
    List<Map<String,Object>> getPlasmaTemperatureData(QueryTraceabilityMachineVo bean);


    @Select("select * from traceability_machine where line=#{line} and device_no=#{deviceNo} and line=#{line}  and device_type=#{deviceType} and parameter=#{parameter}")
    TraceabilityMachine getConfiguration(QueryTraceabilityMachineVo bean);

    @Select("select * from traceability_machine where line=#{line} and device_no=#{deviceNo} and device_type=#{deviceType} and parameter=#{parameter}")
    TraceabilityMachine getMachineConfiguration();

    @Select("select machine_name,parameter from traceability_machine_data group by machine_name,parameter")
    List<TraceabilityMachineData> getMachineDeviceType();

    @Select("select parameter from traceability_machine  where device_type=#{deviceType} and line=#{line} and device_no=#{deviceNo} group by parameter")
    List<String> getParameterByDeviceType(QueryTraceabilityMachineVo bean);

    @Select("select  filed_name as filedName,c.dateTime,a.deviceNoname,b.lineName from(select filed_name as deviceNoname from traceability_machine_data where machine_name=#{deviceType} and parameter='设备编号' group by filed_name)a,(select filed_name as lineName from traceability_machine_data where machine_name=#{deviceType} and parameter='线体' group by filed_name)b,(select filed_name as dateTime from traceability_machine_data where machine_name=#{deviceType} and parameter='时间' group by filed_name)c,traceability_machine_data where machine_name=#{deviceType} and parameter=#{parameter} group by filed_name")
    Map<String,String> getFiledName(@Param("parameter")String parameter,@Param("deviceType") String deviceType);

    @Select("select table_name from traceability_machine_data where machine_name=#{deviceType} group by table_name")
    String getDeviceTableName(@Param("deviceType") String deviceType);

    @Select("select table_name,filed_name,machine_name from traceability_machine_data where parameter='设备编号' group by table_name,filed_name ")
    List<Map<String,String>> getTableNameList();

    @Select("select * from ${tableName} where ${filedName}=#{deviceNo}")
    List<Map<String, Object>> getDataByDeviceNo(@Param("deviceNo") String deviceNo,@Param("tableName") String tableName,@Param("filedName") String filedName );

    @Select("select filed_name from traceability_machine_data where machine_name=#{type} and parameter='线体'")
    String getLineFiledName(@Param("type") String type);

    @Select("select ${lineFiledName} from ${tableName} where ${lineFiledName} is not null group by ${lineFiledName}")
    List<String> getLineByDeviceType(@Param("tableName")String tableName, @Param("lineFiledName")String lineFiledName);

    @Select("select filed_name from traceability_machine_data where machine_name=#{type} and parameter='设备编号'")
    String getDeviceNoByType(@Param("type")String type);

    @Select("select ${deviceNoFiledName} from ${tableName} where ${lineFiledName}=#{line} group by ${deviceNoFiledName}")
    List<String> getDeviceNoByDeviceType(@Param("tableName")String tableName,@Param("deviceNoFiledName") String deviceNoFiledName,@Param("line")String line,@Param("lineFiledName") String lineFiledName);
}