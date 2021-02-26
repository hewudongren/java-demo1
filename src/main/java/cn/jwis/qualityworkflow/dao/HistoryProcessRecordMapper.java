package cn.jwis.qualityworkflow.dao;

import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecordExample;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Mapper
@Component
public interface HistoryProcessRecordMapper {
	int countByExample(HistoryProcessRecordExample example);

	int deleteByExample(HistoryProcessRecordExample example);

	int deleteByPrimaryKey(String id);

	int insert(HistoryProcessRecord record);

	int replaceInsert(HistoryProcessRecord record);

	int insertSelective(HistoryProcessRecord record);

	List<HistoryProcessRecord> selectByExample(HistoryProcessRecordExample example);

	HistoryProcessRecord selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") HistoryProcessRecord record,
			@Param("example") HistoryProcessRecordExample example);

	int updateByExample(@Param("record") HistoryProcessRecord record,
			@Param("example") HistoryProcessRecordExample example);

	int updateByPrimaryKeySelective(HistoryProcessRecord record);

	int updateByPrimaryKey(HistoryProcessRecord record);

	/**
	 * @description: 根据业务Id 获取 已提交的流程 按照 创建时间降序排序
	 * @author: xujinbiao
	 * @date: 2020/5/9 16:47
	 * @param businessId:
	 * @return: java.util.List<cn.jwis.qualityworkflow.bean.HistoryProcessRecord>
	 **/
	@Select("SELECT * from history_process_record where workflow_businessId = #{businessId} " +
			"and type = 'COMMIT' order by create_date desc")
	List<HistoryProcessRecord> getRecordByBusinessId(@Param("businessId") String businessId);

	@Delete("DELETE from history_process_record where workflow_type = #{templateKey} and creator = #{creator} "
			+ "and type = 'SAVE' and workflow_node = 'Apply' and workflow_businessId is NULL ")
	void deleteApplyRecord(@Param("templateKey") String templateKey, @Param("creator") String creator);

	@Delete("DELETE from history_process_record where workflow_type = #{templateKey} and creator = #{creator} "
			+ "and type = 'SAVE' and workflow_node = #{workflowNode} and workflow_businessId is NULL ")
	void deleteRecord(@Param("templateKey") String templateKey, @Param("creator") String creator,
			@Param("workflowNode") String workflowNode);

	@Select("SELECT hp.* FROM history_process_record hp INNER JOIN" +
			"( SELECT h.workflow_type , h.workflow_node ,h.department, max(h.create_date) maxCreateDate " +
			"FROM history_process_record h WHERE h.workflow_type =#{workflowType} AND h.workflow_businessId = #{workflowBusinessId} " +
			"GROUP BY h.workflow_type , h.workflow_node,h.department,h.creator) temp " +
			"ON hp.workflow_type = temp.workflow_type AND hp.workflow_node = temp.workflow_node AND hp.create_date = temp.maxCreateDate " +
			"WHERE hp.workflow_type = #{workflowType} AND hp.workflow_businessId = #{workflowBusinessId}")
	List<JSONObject> getDetailedInfoMaps(QueryDetailedInfoVo bean);

	/**
	 * 根据workflow_businessId 获取 COMMIT 的 历史记录 按照 先 更新时间 后 id
	 *  有会签任务的更新时间一样，再按趋势递增id排序
	 * @param id
	 * @return
	 */
	@Select("SELECT * from history_process_record where workflow_businessId = #{id} and type = \"COMMIT\" order by update_date, id")
	List<HistoryProcessRecord> getWorkflowOperationRecord(@Param("id") String id);

	@Delete("DELETE from history_process_record where workflow_type = #{templateKey} and creator = #{creator} "
			+ "and type = 'SAVE' and workflow_node = #{workflowNode} and workflow_businessId=#{businessId}")
	void deleteRecordByBusinessId(@Param("templateKey") String templateKey, @Param("creator") String creator,
			@Param("workflowNode") String workflowNode, @Param("businessId") String businessId);


	/**
	 * 通过流程id和节点名 获取 对应的最新记录
	 * @param businessId
	 * @param workflowNode
	 * @return
	 */
	@Select("SELECT * from history_process_record where  workflow_businessId=#{businessId} " +
			"and workflow_node = #{workflowNode} and type ='COMMIT' order by create_date desc limit 1")
	HistoryProcessRecord getNewlyRecordByIdAndNode(@Param("businessId") String businessId,
												   @Param("workflowNode") String workflowNode);

	/**
	 * 获取依据 根据 流程id 和 不等于当前 流程节点名 最新的提交 记录
	 * 就是 上一个 节点的提交记录 相对getNewlyRecordByIdAndNode 查询会慢一点
	 * 通过流程id和节点名 获取 对应的最新记录
	 * @param businessId
	 * @param workflowNode
	 * @return
	 */
	@Select("SELECT * from history_process_record where  workflow_businessId=#{businessId} " +
			"and workflow_node <> #{workflowNode} and type ='COMMIT' order by create_date desc limit 1")
	HistoryProcessRecord getNewlyRecord(@Param("businessId") String businessId,
												   @Param("workflowNode") String workflowNode);

	
	
	@Update("update  history_process_record set content=null where workflow_type = #{templateKey} "
			+ " and workflow_node <> #{workflowNode} and workflow_businessId=#{businessId}")
	void deleteRejectBeforeRecord(@Param("templateKey") String templateKey,
			@Param("workflowNode") String workflowNode, @Param("businessId") String businessId);

}