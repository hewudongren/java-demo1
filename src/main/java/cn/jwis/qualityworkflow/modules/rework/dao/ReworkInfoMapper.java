package cn.jwis.qualityworkflow.modules.rework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import cn.jwis.qualityworkflow.modules.rework.bean.QueryPieChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkApplyVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryTrentChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfoExample;

@Mapper
@Component
public interface ReworkInfoMapper {
    int countByExample(ReworkInfoExample example);

    int deleteByExample(ReworkInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReworkInfo record);

    int insertSelective(ReworkInfo record);

    List<ReworkInfo> selectByExample(ReworkInfoExample example);

    ReworkInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReworkInfo record, @Param("example") ReworkInfoExample example);

    int updateByExample(@Param("record") ReworkInfo record, @Param("example") ReworkInfoExample example);

    int updateByPrimaryKeySelective(ReworkInfo record);

    int updateByPrimaryKey(ReworkInfo record);

    /**
     * @description:
     * @author: xujinbiao
     * @date: 2020/4/30 11:01
     * @param parameter:
     * @return: java.util.List<java.lang.String>
     **/
    @Select({ "<script>",
            "SELECT distinct ${parameter} from rework_info where ${parameter} is not null",
            "</script>" })
    List<Object> getPullDownValue(@Param("parameter") String parameter);

    /**
     *
     * @Description: 通过流程TaskId获取信息
     * @author longjun
     * @date 2019年4月19日
     * @param taskId
     * @return
     */
    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM  rework_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
            "</script>" })
    ReworkInfo getInfoByTaskId(String taskId);

    /**
     * @description: 获取返工申请单流程信息
     * @author: xujinbiao
     * @date: 2020/5/15 17:59
     * @param bean:
     * @return: java.util.List<cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo>
     **/
    Page<ReworkInfo> getApplyInfo(QueryReworkApplyVo bean);

    /**
     * @description: 获取项目编号
     * @author: xujinbiao
     * @date: 2020/5/18 10:32
     * @return: java.lang.String
     **/
    @Select("SELECT rework_number_generator()")
    String getReworkNumber();

    /**
     * 批量插入ReworkInfo
     * @param list
     * @return
     */
    @Insert({"<script>", "INSERT INTO rework_info(id, data_source, creator, problem_time, crafts_section, model, " +
            "rework_quantity , problem_type, root_cause_responsibility) VALUES" ,
            "<foreach collection='list' index='index' item='item' separator='' open='' close='' >",
                "<foreach collection='item' index='' item='item' separator=',' open='(' close=')' >",
                    "#{item} " ,
                " </foreach>",
                "<if test = 'index &lt; list.size - 1 '>",
                     ",",
                "</if>",
                "<if test = 'index == list.size - 1 '>",
                    ";",
                "</if>",
            "</foreach>",
            "</script>"})
    Long batchInsert(@Param("list") List<List<String>> list);

    /**
     * @description: 获取返工数量图
     * @author: xujinbiao
     * @date: 2020/5/19 18:13
     * @param vo:
     * @return: java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    List<Map<String, Object>> getReworkQuantityChart(QueryTrentChartVo vo);

    /**
     * @description: 获取返工数量的饼图
     * @author: xujinbiao
     * @date: 2020/5/20 10:20
     * @param vo:
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getPieChart(QueryPieChartVo vo);

    @Select("select * from t_maintenance_detail where schedule_code like CONCAT( #{reworkMO}, '%')")
    List<JSONObject> exportMaintenanceDetail(@Param("reworkMO") String reworkMO);

    /**
     * 根据 ids 获取 List<Ecn>
     * @param ids
     * @return
     */
    List<ReworkInfo> selectByPrimaryKeys(@Param("ids") List<String> ids);
}