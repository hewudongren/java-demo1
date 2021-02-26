package cn.jwis.qualityworkflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jwis.qualityworkflow.bean.WorkflowHandleMapping;
import cn.jwis.qualityworkflow.bean.WorkflowHandleMappingExample;

/**
 *  HistoryProcessRecord 参数 配置表
 */
@Mapper
public interface WorkflowHandleMappingMapper {
    int countByExample(WorkflowHandleMappingExample example);

    int deleteByExample(WorkflowHandleMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkflowHandleMapping record);

    int insertSelective(WorkflowHandleMapping record);

    List<WorkflowHandleMapping> selectByExample(WorkflowHandleMappingExample example);

    WorkflowHandleMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkflowHandleMapping record, @Param("example") WorkflowHandleMappingExample example);

    int updateByExample(@Param("record") WorkflowHandleMapping record, @Param("example") WorkflowHandleMappingExample example);

    int updateByPrimaryKeySelective(WorkflowHandleMapping record);

    int updateByPrimaryKey(WorkflowHandleMapping record);

    @Select("SELECT * from workflow_handle_mapping where workflow_type = #{workflowType} and workflow_node = #{workflowNode} limit 1")
    WorkflowHandleMapping getWorkflowMapping(@Param("workflowType") String workflowType, @Param("workflowNode") String workflowNode);
}