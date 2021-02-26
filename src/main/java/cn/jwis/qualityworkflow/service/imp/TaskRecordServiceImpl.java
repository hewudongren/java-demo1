package cn.jwis.qualityworkflow.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.enums.TaskState.CLOSE;
import static cn.jwis.qualityworkflow.enums.TaskState.ONGOING;
import static cn.jwis.qualityworkflow.util.Title.processAgentDB;
import static cn.jwis.qualityworkflow.util.Title.processAgentExcel;
import static cn.jwis.qualityworkflow.util.Title.processAgentExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.bean.TaskRecordExample;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.service.TaskRecordService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-07 16:31
 * @since 0.1.0
 **/
@Service
public class TaskRecordServiceImpl implements TaskRecordService {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String ONGOING_TASK_STATE = "OnGoing";

	@Autowired
	WorkflowServer workflowServer;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Override
	public JSONObject getProgress(String id) throws Exception {
		// 查询 符合id的记录
		TaskRecordExample example = new TaskRecordExample();
		TaskRecordExample.Criteria criteria = example.createCriteria();
		criteria.andWorkflowBusinessIdEqualTo(id);
		List<TaskRecord> list = taskRecordDao.selectByExample(example);
		// 修剪过后的taskName
		List<String> pruneTaskName = pruneTaskRecord(list, id);
		// 只保留最新的历史节点
		List<TaskRecord> newlyRecord = getNewlyRecord(list);
		// 对记录进行分类
		Map<String, List<TaskRecord>> collect = newlyRecord.stream()
				.collect(Collectors.groupingBy(TaskRecord::getTaskName));
		// 遍历分类后的记录
		JSONObject result = new JSONObject();
		// 如果list 只有一个 ，代表不是会签节点
		for (Map.Entry<String, List<TaskRecord>> entry : collect.entrySet()) {
			// 如果修剪List 中不包含就剔除
			if (!pruneTaskName.contains(entry.getKey())) {
				continue;
			}
			if (entry.getValue().size() == 1) {
				TaskRecord task = entry.getValue().get(0);
				JSONObject node = new JSONObject();
				boolean flag = "Close".equals(task.getTaskState());
				node.put("task_state", (flag) ? CLOSE : ONGOING);
				node.put("update_time", dateFormat.format(task.getUpdateDate()));
				node.put("assignee", task.getAssignee());
				String state = task.getState();
				if (!"0".equals(state)){
					result.put(entry.getKey(), node);
				}
			}
			// 会签节点 取最新的 提交人
			else if (entry.getValue().size() > 1) {
				List<TaskRecord> taskList = entry.getValue();
				// 如果有一个节点处于未Close状态就是 进行中状态
				boolean flag = taskList.stream().anyMatch(d -> !"Close".equals(d.getTaskState()));
				JSONObject node = new JSONObject();
				node.put("task_state", (flag) ? ONGOING : CLOSE);
				// 获取最近更新的会签节点 的负责人写入
				List<TaskRecord> sortedList = taskList.stream()
						.sorted(Comparator.comparing(TaskRecord::getUpdateDate).reversed())
						.collect(Collectors.toList());
				TaskRecord taskRecord = sortedList.get(0);
				// 会签展示最后一个会签的时间，及所有会签人信息，用“，”隔开
				String assignee = entry.getValue().stream().map(d -> d.getAssignee()).collect(Collectors.joining(","));
				node.put("update_time", dateFormat.format(taskRecord.getUpdateDate()));
				node.put("assignee", assignee);
				result.put(entry.getKey(), node);
			}
		}
		return result;
	}

	/**
	 * @description: //TODO
	 * @author: xujinbiao
	 * @date: 2020/5/9 18:28
	 * @param processInstanceId:
	 * @param businessId:
	 * @param templateKey:
	 * @return: java.util.Map<java.lang.String,java.lang.String>
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Map<String, Object> generateTaskRecord(String processInstanceId, String businessId, String templateKey,
			Map<String, Object> taskCommonInfo) throws Exception {
		List<Map<String, Object>> historyTaskList = workflowServer.getProcessHistoryTasks(processInstanceId);
		// 将下一个节点的名 和 处理人 返回
		String processState = null;
		String assignee = null;
		TaskRecord taskRecordBean = null;
		int historyTaskListCompleteNum = 0;
		// 追加项目编号 和 创建人
		String itemNumber = null;
		String creator = null;
		String theme = null;
		// for 循环，如果某个任务的deleteReason字段为null,那它就是当前最新任务，取出任务名称，这就是流程的状态。
		//根据
		for (Map<String, Object> historyTask : historyTaskList) {
			Object deleteReasonObj = historyTask.get("deleteReason");
			if (null == deleteReasonObj) {
				Object idObj = historyTask.get("id");
				String id = String.valueOf(idObj);
				// 验证taskId 是否存在 ，如果存在 则不创建 taskRecordBean
				// 存在代表当前节点是会签节点
				int count = taskRecordDao.getCountByTaskId(id);
				if (count > 0) {
					continue;
				}
				Object nameObj = historyTask.get("name");
				String taskName = String.valueOf(nameObj);
				//如果是会签节点有多个代理人，这里只设置了一个
				Object assigneeObj = historyTask.get("assignee");
				assignee = String.valueOf(assigneeObj);
				processState = taskName;
				taskRecordBean = new TaskRecord();
				taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
				taskRecordBean.setWorkflowBusinessId(businessId);
				taskRecordBean.setTaskId(id);
				taskRecordBean.setTaskName(processState);
				taskRecordBean.setTemplateKey(templateKey);
				taskRecordBean.setAssignee(assignee);
				taskRecordBean.setProcessInstanceId(processInstanceId);
				taskRecordBean.setTaskState(ONGOING_TASK_STATE);
				if (itemNumber == null || creator == null || theme == null) {
					itemNumber = (String) taskCommonInfo.get("itemNumber");
					creator = (String) taskCommonInfo.get("creator");
					theme = (String) taskCommonInfo.get("theme");
				}
				taskRecordBean.setItemNumber(itemNumber);
				taskRecordBean.setCreator(creator);
				taskRecordBean.setTheme(theme);
				// 保存任务记录 流程Id assignee ecnId taskId 状态 taskName
				taskRecordDao.insertSelective(taskRecordBean);
			} else {
				historyTaskListCompleteNum++;
			}
		}
		boolean isProcessInstanceComplete = (historyTaskListCompleteNum == historyTaskList.size()) ? true : false;
		Map<String, Object> resultMap = new ConcurrentHashMap<>(3);
		if (processState != null && assignee != null) {
			resultMap.put("processState", processState);
			resultMap.put("assignee", assignee);
		}
		resultMap.put("isProcessInstanceComplete", isProcessInstanceComplete);
		return resultMap;
	}

	/**
	 * @description: 新建一个流程起始节点
	 * @author: xujinbiao
	 * @date: 2020/5/11 14:31
	 * @param workflowBusinessId: 业务Id
	 * @param processInstanceId:  实例id
	 * @param taskId:             任务节点id
	 * @param templateKey:        模板Key
	 * @return: void
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveApplyTaskRecord(String workflowBusinessId, String processInstanceId, String taskId,
			String templateKey, Map<String, Object> taskCommonInfo) throws Exception {
		TaskRecord taskRecordBean = new TaskRecord();
		taskRecordBean.setId(String.valueOf(idGeneratorRunner.nextId()));
		taskRecordBean.setWorkflowBusinessId(workflowBusinessId);
		String currentUserName = getCurrentUserName();
		taskRecordBean.setAssignee(currentUserName);
		taskRecordBean.setProcessInstanceId(processInstanceId);
		taskRecordBean.setTaskId(taskId);
		taskRecordBean.setTaskName("Apply");
		taskRecordBean.setTaskState(CLOSE_TASK_STATE);
		taskRecordBean.setTemplateKey(templateKey);
		// 追加项目编号 和 创建人
		String itemNumber = (String)taskCommonInfo.get("itemNumber");
		String creator = (String)taskCommonInfo.get("creator");
		String theme = (String)taskCommonInfo.get("theme");
		taskRecordBean.setTheme(theme);
		taskRecordBean.setCreator(creator);
		taskRecordBean.setItemNumber(itemNumber);
		taskRecordDao.insertSelective(taskRecordBean);
	}

	/**
	 * @description: 查询当前节点是否处于会签节点
	 * @author: xujinbiao
	 * @date: 2020/5/11 17:34
	 * @param workflowBusinessId:
	 * @return: boolean
	 **/
	@Override
	public boolean getProcessCurrentStateIsParallel(String workflowBusinessId) throws Exception {
		// 查询记录 TaskRecord 按照创建时间降序排序
		boolean isParallel = false;
		TaskRecordExample taskRecordExample = new TaskRecordExample();
		TaskRecordExample.Criteria criteria = taskRecordExample.createCriteria();
		criteria.andWorkflowBusinessIdEqualTo(workflowBusinessId);
		taskRecordExample.setOrderByClause("`create_date` desc");
		List<TaskRecord> taskRecordList = taskRecordDao.selectByExample(taskRecordExample);
		// 比较第一个 和第二个 TaskName 是否相同， 相同则当前处于会签节点， 不相同 则不是
		if (taskRecordList.size() >= 2) {
			String firstTaskName = taskRecordList.get(0).getTaskName();
			String secondTaskName = taskRecordList.get(1).getTaskName();
			if (firstTaskName.equals(secondTaskName)) {
				isParallel = true;
			}
		}
		return isParallel;
	}

	// 判定当前用户 是否可以处理当前的节点
	@Override
	public boolean getEnableHandleCurrentNode(String taskId) throws Exception {
		// 获取当前流程未关闭的 节点 处理人列表
		TaskRecord taskRecord = taskRecordDao.getByTaskId(taskId);
		String taskState = taskRecord.getTaskState();
		//如果当前任务的状态是已完成，那么不管是不是处理人都不能处理当前任务了
		if (CLOSE_TASK_STATE.equals(taskState)) {
			return false;
		}
		String assignee = taskRecord.getAssignee();
		// 判定能否包含当前处理人
		String currentUserName = getCurrentUserName();
		if (assignee != null && currentUserName.equals(assignee)) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request, processAgentDB, processAgentExcel, processAgentExcelUS);
	}

	@Override
	public List<JSONObject> getProcessAgent(JSONObject jsonObject) {
		// 流程类型字段
		String templateKey = jsonObject.getString("templateKey");
		// 开始时间区间
		String startTime = jsonObject.getString("startTime");
		String startTime1 = jsonObject.getString("startTime1");
		startTime1 = DateUtil.plusOneDay(startTime1);
		// 结束时间区间
		String endTime = jsonObject.getString("endTime");
		String endTime2 = jsonObject.getString("endTime1");
		endTime2 = DateUtil.plusOneDay(endTime2);
		// 代办，已处理，全部标识
		String flag = jsonObject.getString("flag");
		// 分页处理
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		if (page != 0 && page != null && size != 0 && size != null) {
			page = (page - 1) * size;
		}
		// 获取当前查询的用户
		String assignee = getCurrentUserName();
		List<JSONObject> result = taskRecordDao.getProcessAgent(templateKey, startTime, startTime1, endTime, endTime2,
				assignee, flag, page, size);
		return result;
	}

	@Override
	public Long getProcessAgentcount(JSONObject jsonObject) {
		// 流程类型字段
		String templateKey = jsonObject.getString("templateKey");
		// 开始时间区间
		String startTime = jsonObject.getString("startTime");
		String startTime1 = jsonObject.getString("startTime1");
		startTime1 = DateUtil.plusOneDay(startTime1);
		// 结束时间区间
		String endTime = jsonObject.getString("endTime");
		String endTime2 = jsonObject.getString("endTime1");
		endTime2 = DateUtil.plusOneDay(endTime2);
		// 代办，已处理，全部标识
		String flag = jsonObject.getString("flag");
		// 获取当前查询的用户
		String assignee = getCurrentUserName();
		Long count = taskRecordDao.getProcessAgentCount(templateKey, startTime, startTime1, endTime, endTime2, assignee,
				flag);
		return count;
	}

	/**
	 * @description: 获取 最新的 记录列表
	 * @author: xujinbiao
	 * @date: 2020/5/11 10:28
	 * @param records:
	 * @return: java.util.List<cn.jwis.qualityworkflow.bean.TaskRecord>
	 **/
	private List<TaskRecord> getNewlyRecord(List<TaskRecord> records) {
		if (CollectionUtils.isEmpty(records)) {
			return records;
		}
		// 先获取 所有需要 添加的节点
		List<TaskRecord> needToAddRecord = new ArrayList<>();
		List<String> nodeNameList = records.stream().map(r -> r.getTaskName()).distinct().collect(Collectors.toList());
		for (String node : nodeNameList) {
			List<TaskRecord> sotedList = records.stream().sorted(Comparator.comparing(TaskRecord::getTaskId).reversed())
					.collect(Collectors.toList());
			Iterator<TaskRecord> iterator = sotedList.iterator();
			// 如果找到了符合条件的节点并且 它的下一个节点 不符合 名字相等的条件 就跳出
			boolean flag = false;
			while (iterator.hasNext()) {
				TaskRecord next = iterator.next();
				// 寻找符合条件的节点 然后 看看 他的下一个节点 是否也符合条件
				if (node.equals(next.getTaskName())) {
					needToAddRecord.add(next);
					// 添加过后就删除 提高性能
					flag = true;
					iterator.remove();
				} else if (flag) {
					break;
				}
			}
		}
		return needToAddRecord;
	}

	/**
	 * @description: 修剪
	 * @author: xujinbiao
	 * @date: 2020/6/4 16:25
	 * @param list:
	 * @return: java.util.List<java.lang.String>
	 **/
	private List<String> pruneTaskRecord(List<TaskRecord> list, String id) {
		// 按时间升序排序的列表
		List<TaskRecord> sortedList = list.stream().sorted(Comparator.comparing(TaskRecord::getTaskId))
				.collect(Collectors.toList());
		List<String> taskNameList = sortedList.stream().map(d -> d.getTaskName()).distinct()
				.collect(Collectors.toList());
		String processCurrentState = taskRecordDao.getProcessCurrentState(id);
		int index = taskNameList.indexOf(processCurrentState);
		// 保留当前节点和之前的节点
		if (index != -1) {
			index = index + 1;
			int len = taskNameList.size();
			for (int i = index; i < len; i++) {
				taskNameList.remove(index);
			}
		}
		return taskNameList;
	}

	@Override
	public List<TaskRecord> getOverTimeTaskRecord(String templateKey, String taskName, int overTime) {
		return taskRecordDao.getOverTimeTaskRecord(templateKey, taskName, overTime);
	}
}
