package cn.jwis.qualityworkflow.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-04-30 15:49
 * @since 0.1.0
 **/
public interface HistoryProcessRecordService {
	/**
	 * @description: 保存历史进展数据
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:00
	 * @param bean:
	 * @return: int
	 **/
	 int save(CreateHistoryProcessVo bean) throws Exception;

	/**
	 * @description: 查询最新的保存记录
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:28
	 * @param bean:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	HistoryProcessRecord getLatestRecord(QueryHistoryProcessRecord bean) throws Exception;

	/**
	 * @description: 查询最新的保存记录的内容
	 * @author: xujinbiao
	 * @date: 2020/5/6 9:44
	 * @param bean:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	JSONObject getLatestRecordContent(QueryHistoryProcessRecord bean) throws Exception;

    /**
     * @Author yuyangyang
     * @Description 查询最新的保存记录的内容和ID
     * @Date  2020/5/25  14:40
     * @Param
     * @return
     */
	JSONObject getLatestRecordContentAndId(QueryHistoryProcessRecord bean) throws Exception;

	/**
	 * @description: 获取用户 记录详情
	 * @author: xujinbiao
	 * @date: 2020/5/9 14:49
	 * @param bean:
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 **/
	Map<String, Object> getDetailedInfoMap(QueryDetailedInfoVo bean) throws Exception;


	/**
	 * 清除对应用户拟制单据阶段保存的信息
	 * @param templateKey
	 * @throws Exception
	 */
	void deleteApplyRecord(String templateKey) throws Exception;
	
	void deleteRecord(String templateKey,String workflowNode, String lineQualifyId) throws Exception;

	Map<String, List<JSONObject>> getDetailedInfoMaps(QueryDetailedInfoVo bean);

	/**
	 * 将workflow_handleing_mapping 配置表中的对应的节点
	 * @param vo
	 * @throws Exception
	 */
	String appendHistoryProcess(CreateHistoryProcessVo vo, JSONObject jsonObject) throws Exception;


	/**
	 * 获取流程操作记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<HistoryProcessRecord> getWorkflowOperationRecord(String id) throws Exception;

	void deleteRejectBeforeRecord(String templateKey, String workflowNode, String businessId) throws Exception;
}
