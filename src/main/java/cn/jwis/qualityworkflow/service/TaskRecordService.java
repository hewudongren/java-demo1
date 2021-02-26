package cn.jwis.qualityworkflow.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.TaskRecord;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-07 16:29
 * @since 0.1.0
 **/
public interface TaskRecordService {

	/**
	 * @description: 根据id获取流程进展(此id是 workflow_businessId)
	 * @author: xujinbiao
	 * @date: 2020/4/30 11:28
	 * @param id:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	JSONObject getProgress(String id) throws Exception;

	/**
	 * @description:  结束节点任务 ， 然后生成taskRecord
	 * @author: xujinbiao
	 * @date: 2020/5/9 18:31
	 * @param processInstanceId:
	 * @param businessId:
	 * @param templateKey:
	 * @param taskCommonInfo: task表 需要 追加的公用信息 比如 项目编号，单据创建人
	 * @return: java.util.Map<java.lang.String,java.lang.String>
	 **/
	Map<String, Object> generateTaskRecord(String processInstanceId, String businessId,
										   String templateKey, Map<String, Object> taskCommonInfo) throws Exception;

	/**
	 * @description: 新建一个流程起始节点
	 * @author: xujinbiao
	 * @date: 2020/5/11 14:31
	 * @param workflowBusinessId: 业务Id
	 * @param processInstanceId: 实例id
	 * @param taskId: 任务节点id
	 * @param templateKey: 模板Key
	 * @return: void
	 **/
	void saveApplyTaskRecord(String workflowBusinessId, String processInstanceId,
						String taskId,  String templateKey, Map<String, Object> taskCommonInfo) throws Exception;


	/**
	 * @description: 查询当前节点是否处于会签节点
	 * @author: xujinbiao
	 * @date: 2020/5/11 17:34
	 * @param workflowBusinessId:
	 * @return: boolean
	 **/
	boolean getProcessCurrentStateIsParallel(String workflowBusinessId) throws Exception;

	/**
	 * @description:  判定当前处理人能否处理当前记录
	 * @author: xujinbiao
	 * @date: 2020/5/26 11:17
	 * @param taskId:
	 * @return: boolean
	 **/
	boolean getEnableHandleCurrentNode(String taskId) throws Exception;
	/**
	 * @Author yuyangyang
	 * @Description 获取表头信息
	 * @Date  2020/6/17  19:04
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle(HttpServletRequest request);
	/**
	 * @Author yuyangyang
	 * @Description 流程待办的集合
	 * @Date  2020/6/17  19:16
	 * @Param 
	 * @return 
	 */
	List<JSONObject> getProcessAgent(JSONObject jsonObject);
	/**
	 * @Author yuyangyang
	 * @Description 流程代办的总数
	 * @Date  2020/6/17  19:16
	 * @Param 
	 * @return 
	 */
	Long getProcessAgentcount(JSONObject jsonObject);
	
	List<TaskRecord> getOverTimeTaskRecord(String templateKey, String taskName, int overTime);
}
