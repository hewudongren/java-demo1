package cn.jwis.qualityworkflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.TaskRecordExample;

@Mapper
@Component
public interface TaskRecordMapper {
    int countByExample(TaskRecordExample example);

    int deleteByExample(TaskRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    List<TaskRecord> selectByExample(TaskRecordExample example);

    TaskRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TaskRecord record, @Param("example") TaskRecordExample example);

    int updateByExample(@Param("record") TaskRecord record, @Param("example") TaskRecordExample example);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);


    /**
     * 获取TaskID 个数
     * @param taskId
     * @return
     */
    @Select("SELECT count(*) from task_record where task_id = #{taskId}")
    int getCountByTaskId(@Param("taskId") String taskId);

    /**
     * @description: 根据 id列表 获取超期的 流程任务
     * 超期的时间为 超过 节点创建时间24H
     * 会签节点超期计算次数 只为 1
     * @author: xujinbiao
     * @date: 2020/5/6 15:04
     * @param idList:
     * @return: long
     **/
    long getOverdueCount(@Param("idList") List<String> idList,@Param("delayHour") int delayHour);

    /**
     * @Description:更新对应流程TaskId节点信息为Close
     * @author longjun
     * @date 2018年8月21日
     * @param workflowBusinessId
     */
    @Update({ "<script>",
            "update task_record e set e.task_state='Close' where e.workflow_business_id = #{workflowBusinessId} and task_id = #{taskId} ",
            "</script>" })
    void updateTaskRecord(@Param("workflowBusinessId") String workflowBusinessId, @Param("taskId") String taskId);

    
    @Update({ "<script>",
        "update task_record e set e.task_state=null where e.workflow_business_id = #{workflowBusinessId} and task_id = #{taskId} ",
        "</script>" })
     void updateTaskRecordState(@Param("workflowBusinessId") String workflowBusinessId, @Param("taskId") String taskId);

	@Update({ "<script>",
			"update task_record e set e.state= 0 where e.workflow_business_id = #{workflowBusinessId} ",
			"</script>" })
    void updateTaskState(@Param("workflowBusinessId") String workflowBusinessId);
    
    /**
     * @Description:更新对应工作流实例的所有Task状态为Close
     * @author longjun
     * @date 2018年8月21日
     * @param workflowBusinessId
     */
    @Update({ "<script>",
            "update task_record e set e.task_state='Close' where e.workflow_business_id = #{workflowBusinessId} ",
            "</script>" })
    void closeTaskByBusinessId(@Param("workflowBusinessId") String workflowBusinessId);

    /**
     * @description: 获取当前流程所处节点
     * @author: xujinbiao
     * @date: 2020/5/11 14:09
     * @param workflowBusinessId:
     * @return: java.lang.String
     **/
    @Select("SELECT DISTINCT task_name from task_record WHERE  workflow_business_id = " +
            "#{workflowBusinessId} and (task_state='OnGoing' or task_state is null)")
    String getProcessCurrentState(@Param("workflowBusinessId") String workflowBusinessId);


    /**
     * @description: //通过节点taskid 获取节点状态
     * @author: xujinbiao
     * @date: 2020/5/26 11:06
     * @param taskId:
     * @return: java.lang.String
     **/
    @Select("SELECT * from task_record WHERE task_id = #{taskId}")
    TaskRecord getByTaskId(@Param("taskId") String taskId);


    @Select("SELECT * from task_record WHERE workflow_business_id = #{workflowId} and task_state is null")
    TaskRecord getByTaskWorkFlowId(@Param("workflowId") String workflowId);

	/**
	 * 根据workflow_business_id 获取 对应流程 最新的 taskRecord
	 * @param workflowId
	 * @return
	 */
	@Select("SELECT * from task_record WHERE workflow_business_id = #{workflowId} " +
			"and (task_state='OnGoing' or task_state is null) order by create_date desc limit 1")
	TaskRecord getByWorkFlowId(@Param("workflowId") String workflowId);



    @Select("Select * from task_record WHERE workflow_business_id = #{businessId} and (task_state='OnGoing' or task_state is null)")
    List<TaskRecord> getUncloseTaskByBusinessId(@Param("businessId") String businessId) throws Exception;


	@Select("Select * from task_record WHERE template_key = #{templateKey} and (task_state='OnGoing' or task_state is null)")
	List<TaskRecord> getUncloseTaskByTemplateKey(@Param("templateKey") String templateKey);

    List<JSONObject> getProcessAgent(@Param("templateKey") String templateKey, @Param("startTime") String startTime,
                                             @Param("startTime1") String startTime1, @Param("endTime") String endTime,
                                             @Param("endTime1") String endTime1, @Param("assignee") String assignee, @Param("flag") String flag,
                                             @Param("page") Integer page, @Param("size") Integer size);

    Long getProcessAgentCount(@Param("templateKey") String templateKey,@Param("startTime") String startTime,
                              @Param("startTime1") String startTime1,@Param("endTime") String endTime,
                              @Param("endTime1") String endTime1,@Param("assignee") String assignee,@Param("flag") String flag);
    @Delete({ "<script>",
        "delete from task_record where workflow_business_id = #{workflowBusinessId} and task_id = #{taskId} ",
        "</script>" })
	void deleteTaskRecord(@Param("workflowBusinessId") String workflowBusinessId, @Param("taskId") String taskId);

    @Select("SELECT * from task_record WHERE id = #{id}")
    TaskRecord getById(@Param("id") String id);
    
	@Select("SELECT *, HOUR(timediff(now() , create_date)) overTimeNum FROM task_record WHERE template_key = #{templateKey} AND task_state IS NULL AND task_name = #{taskName} AND HOUR(timediff(now() , create_date)) >= #{overTime}")
	List<TaskRecord> getOverTimeTaskRecord(@Param("templateKey") String  templateKey,@Param("taskName") String  taskName,@Param("overTime") int  overTime);

	@Select("SELECT *, HOUR(timediff(now() , create_date)) overTimeNum FROM task_record WHERE template_key = #{templateKey} AND task_state IS NULL AND HOUR(timediff(now() , create_date)) >= #{overTime}")
	List<TaskRecord> getOverTimeTaskRecord2(@Param("templateKey") String  templateKey,@Param("overTime") int  overTime);
    
    
}