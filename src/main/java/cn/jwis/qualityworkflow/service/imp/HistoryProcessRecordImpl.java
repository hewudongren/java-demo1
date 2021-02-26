package cn.jwis.qualityworkflow.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecordExample;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.bean.WorkflowHandleMapping;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.dao.WorkflowHandleMappingMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.service.TaskRecordService;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.UserUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-04-30 15:49
 * @since 0.1.0
 **/
@Service
public class HistoryProcessRecordImpl implements HistoryProcessRecordService {

	@Autowired
	HistoryProcessRecordMapper historyProcessRecordDao;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Autowired
	TaskRecordService taskRecordService;

	@Resource
	WorkflowHandleMappingMapper workflowHandleDao;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int save(CreateHistoryProcessVo bean) {
		//组装实体类数据
		HistoryProcessRecord record = new HistoryProcessRecord();
		String id = String.valueOf(idGeneratorRunner.nextId());
		String currentUserName = UserUtil.getCurrentUserName();
		record.setId(id);
		record.setDepartment(bean.getDepartment());
		record.setCreator(currentUserName);
		JSONObject content = bean.getContent();
		if (content != null && content.size() >0){
			record.setContent(JSONObject.toJSONString(content));
		}
		record.setWorkflowBusinessid(bean.getWorkflowBusinessid());
		record.setType(bean.getType());
		record.setWorkflowNode(bean.getWorkflowNode());
		record.setWorkflowType(bean.getTemplateKey());
		// 如果处理意见不为空
		if (bean.getHandleOpinion() != null) {
			record.setHandleOpinion(bean.getHandleOpinion());
		}
		// 如果驳回原因不为空
		if (bean.getRejectReason() != null) {
			record.setRejectReason(bean.getRejectReason());
		}
		int count = historyProcessRecordDao.insert(record);
		return count;
	}

	/**
	 * @description:查询最新的保存记录
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:46
	 * @param bean:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	@Override
	public HistoryProcessRecord getLatestRecord(QueryHistoryProcessRecord bean) throws Exception {
		HistoryProcessRecordExample example = new HistoryProcessRecordExample();
		HistoryProcessRecordExample.Criteria criteria = example.createCriteria();
		if (bean.getType() != null) {
			criteria.andTypeEqualTo(bean.getType());
		}
		if (bean.getWorkflowType() != null) {
			criteria.andWorkflowTypeEqualTo(bean.getWorkflowType());
		}
		// id比较特殊，为null 的情况下， 表示查询 id为null 而不是全部
		if (bean.getWorkflowBusinessid() == null) {
			criteria.andWorkflowBusinessidIsNull();
		} else {
			criteria.andWorkflowBusinessidEqualTo(bean.getWorkflowBusinessid());
		}
		if (bean.getWorkflowNode() != null) {
			criteria.andWorkflowNodeEqualTo(bean.getWorkflowNode());
		}
		criteria.andCreatorEqualTo(bean.getCreator());
		List<HistoryProcessRecord> historyProcessRecords = historyProcessRecordDao.selectByExample(example);
		// 返回最近更新时间 符合条件的记录
		if (CollectionUtils.isNotEmpty(historyProcessRecords)) {
			List<HistoryProcessRecord> sortedList = historyProcessRecords.stream()
					.sorted(Comparator.comparing(HistoryProcessRecord::getUpdateDate).reversed())
					.collect(Collectors.toList());
			HistoryProcessRecord historyProcessRecord = sortedList.get(0);
			return historyProcessRecord;
		}
		return null;
	}

	@Override
	public JSONObject getLatestRecordContent(QueryHistoryProcessRecord bean) throws Exception {
		HistoryProcessRecord latestRecord = getLatestRecord(bean);
		if (latestRecord != null && latestRecord.getContent() != null) {
			return JSONObjectUtil.toJSONObject(latestRecord.getContent());
		}
		return null;
	}

	@Override
	public JSONObject getLatestRecordContentAndId(QueryHistoryProcessRecord bean) throws Exception {
		HistoryProcessRecord latestRecord = getLatestRecord(bean);
		if (latestRecord != null && latestRecord.getContent() != null) {
			JSONObject result = JSONObjectUtil.toJSONObject(latestRecord.getContent());
			result.put("id", latestRecord.getId());
			return result;
		}
		return null;
	}

	/**
	 * 从流程历史记录表获取节点信息 PCN流程和其他流程判断能否编辑页面逻辑不同,所以抽出此方法
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDetailMap(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = new ConcurrentHashMap<>();
		// 查询 各节点已提交的历史消息
		List<HistoryProcessRecord> records = historyProcessRecordDao
				.getRecordByBusinessId(bean.getWorkflowBusinessId());
		//取出各个任务最新的数据（多次提交驳回现象）
		// 为什么搞那么复杂，直接按节点分组，再取出最新的就好了呀,因为有会签呀
		List<HistoryProcessRecord> newlyRecord = getNewlyRecord(records);
		//以当前任务节点为基准，过滤驳回之后节点
		List<String> pruneList = pruneRecord(records, bean.getWorkflowBusinessId());
		for (int i = 0; i < newlyRecord.size(); i++) {
			HistoryProcessRecord task = newlyRecord.get(i);
			String taskName = task.getWorkflowNode();
			// 如果修剪的List中包含当前节点名，则加入。 不存在说明，需要 剔除
			if (pruneList.contains(taskName)) {
				if (detailedInfoMap.get(taskName) == null) {
					detailedInfoMap.put(taskName, task);
				}
				// 如果 是 会签节点(上一个节点 和 当前节点同名)
				else {
					Object object = detailedInfoMap.get(taskName);
					// 第二个会签节点插入时进行 由Object 转为List
					if (object instanceof HistoryProcessRecord) {
						List<HistoryProcessRecord> list = new ArrayList<>();
						list.add((HistoryProcessRecord) object);
						list.add(task);
						detailedInfoMap.put(taskName, list);
					} else if (object instanceof List) {
						List<HistoryProcessRecord> list = (List) object;
						list.add(task);
					}
				}
			}
		}
		// 处理保存信息，看当前节点有没有保存信息，有则处理
		dealSaveRecord(bean, detailedInfoMap);
		// 将map中的 HistoryProcessRecord 转为其中的content
		for (Map.Entry<String, Object> entry : detailedInfoMap.entrySet()) {
			Object o = entry.getValue();
			if (o instanceof HistoryProcessRecord) {
				HistoryProcessRecord record = (HistoryProcessRecord) o;
				String content = record.getContent();
				if(StringUtils.isNotEmpty(content)){
					JSONObject jsonObject = JSONObjectUtil.toJSONObject(content);
					detailedInfoMap.put(entry.getKey(), jsonObject);
				}else {
					detailedInfoMap.remove(entry.getKey());
				}
			} else if (o instanceof List) {
				List<HistoryProcessRecord> list = (List) o;
				List<JSONObject> objectList = new ArrayList<>(list.size());
				list.stream().forEach(d -> {
					JSONObject object = JSONObjectUtil.toJSONObject(d.getContent());
					objectList.add(object);
				});
				detailedInfoMap.put(entry.getKey(), objectList);
			}
		}
		return detailedInfoMap;
	}

	@Override
	public Map<String, Object> getDetailedInfoMap(QueryDetailedInfoVo bean) throws Exception {
		//获取当前任务的历史流程记录
		Map<String, Object> detailedInfoMap = getDetailMap(bean);
		// 判定当前处理人能否处理当前记录
		boolean enableHandleCurrentNode = taskRecordService.getEnableHandleCurrentNode(bean.getTaskId());
		detailedInfoMap.put("enableHandleCurrentNode", enableHandleCurrentNode);
		return detailedInfoMap;
	}

	@Override
	public void deleteApplyRecord(String templateKey) throws Exception {
		String currentUserName = UserUtil.getCurrentUserName();
		historyProcessRecordDao.deleteApplyRecord(templateKey, currentUserName);
	}

	/**
	 * 追加history_process_record
	 * 
	 * @param vo
	 * @throws Exception
	 */
	@Override
	public String appendHistoryProcess(CreateHistoryProcessVo vo, JSONObject jsonObject) throws Exception {
		String temp = null;
		String templateKey = vo.getTemplateKey();
		String workflowNode = vo.getWorkflowNode();
		String workflowBusinessid = vo.getWorkflowBusinessid();
		if (templateKey != null && workflowNode != null) {
			WorkflowHandleMapping workflowMapping = workflowHandleDao.getWorkflowMapping(templateKey, workflowNode);
			if (workflowMapping != null) {
				String rejectReason = workflowMapping.getRejectReason();
				String handleOpinion = workflowMapping.getHandleOpinion();
				String handleOpinionValue = jsonObject.getString(handleOpinion);
				String rejectReasonValue = jsonObject.getString(rejectReason);
				// 处理意见 为 n 和 ng 转为 驳回。 其他的默认为通过
				if ("N".equalsIgnoreCase(handleOpinionValue) || "NG".equalsIgnoreCase(handleOpinionValue)
						|| "1".equalsIgnoreCase(handleOpinionValue) || "驳回".equals(handleOpinionValue)
						|| "不通过".equals(handleOpinionValue)) {
					vo.setHandleOpinion("驳回");
					vo.setRejectReason(rejectReasonValue);
					temp = rejectReasonValue;
					if("ESDACCEPT".equals(templateKey)){
						historyProcessRecordService.deleteRejectBeforeRecord(templateKey, "新建特采单", workflowBusinessid);
						//将taskRecord的
						taskRecordDao.updateTaskState(workflowBusinessid);
					}
					vo.setContent(null);
				} else if ("RJ".equals(handleOpinionValue)) {
					vo.setHandleOpinion("不同意特采,退货供应商");
					vo.setRejectReason(rejectReasonValue);
				} else {
					vo.setHandleOpinion("通过");
				}
			}
		}
		return temp;
	}

	@Override
	public List<HistoryProcessRecord> getWorkflowOperationRecord(String id) throws Exception {
		List<HistoryProcessRecord> workflowOperationRecord = historyProcessRecordDao.getWorkflowOperationRecord(id);
		workflowOperationRecord.stream().forEach(d -> d.setContent(null));
		return workflowOperationRecord;
	}

	private void dealSaveRecord(QueryDetailedInfoVo bean, Map<String, Object> detailedInfoMap) throws Exception {
		String processCurrentState = taskRecordDao.getProcessCurrentState(bean.getWorkflowBusinessId());
		// 如果当前流程所处状态 不是关闭的状态(为空就是为关闭状态)
		if (processCurrentState != null) {
			// 获取当前用户最新保存的记录
			//如果保存记录和提交记录比较，如果保存在后，则回填保存数据
			HistoryProcessRecord saveRecord = getSaveContent(bean);
			// 如果保存记录不为null, 并且 记录的 节点状态 与 当前 流程所处节点相同
			if (saveRecord != null && saveRecord.getWorkflowNode() != null
					&& saveRecord.getWorkflowNode().equals(processCurrentState)) {

				//其实有保存数据一定是在提交数据之后，应为提交数据成功会删除对应的保存数据
				// 利用当前流程节点id 对应的处理人。 查询最新的流程节点处理人 提交的节点信息。
				String currentNode = saveRecord.getWorkflowNode();
				String creator = saveRecord.getCreator();
				Object o = detailedInfoMap.get(currentNode);
				// 判断当节点是否为会签节点
				boolean isParallelNode = false;
				if (o != null && o instanceof List) {
					isParallelNode = true;
				} else if (o != null && o instanceof HistoryProcessRecord) {
					HistoryProcessRecord record = (HistoryProcessRecord) o;
					String recordCreator = record.getCreator();
					// 如果现有节点（的提交人）内容 和 保存的人 不是同一个人 说明 该节点 也是一个会签节点
					//多人会签节点，如果只有一个人提交，别人有保存，
					if (!creator.equals(recordCreator)) {
						isParallelNode = true;
					}
				}
				// 如果是会签节点的保存，需要 添加到List后面
				// 会签比较特殊，有覆盖 和 追加 和 不予处理 三种模式
				// 覆盖是需要替换掉之前提交的内容，把新的保存内容展现出来。
				// 追加是当前用户，之前没有旧的提交内容，不需要替换
				// 不予处理 是 提交的内容的创建时间 after 保存的内容的创建时间
				if (isParallelNode) {
					if (o instanceof List) {  //对应的是有多人提交
						List<HistoryProcessRecord> processRecords = (List) o;
						Iterator<HistoryProcessRecord> iterator = processRecords.iterator();
						// 是否覆盖标志位
						boolean coverFlag = false;
						// 追加标志位
						boolean appendFlag = true;
						// 迭代 判断是否需要覆盖
						while (iterator.hasNext()) {
							HistoryProcessRecord next = iterator.next();
							// 如果创建者都相等 说明有节点 保存记录和 提交记录冲突，追加模式为false
							// 比较各自的创建时间。看看谁是最新的， 如果是保存比较新，则进行覆盖
							if (creator.equals(next.getCreator())) {
								// 当有同名时说明不是追加，而是覆盖了
								appendFlag = false;
								if (next.getCreateDate().before(saveRecord.getCreateDate())) {
									//把提交数据删去，即覆盖
									iterator.remove();
									coverFlag = true;
								}
								break;
							}
						}
						// 如果追加标志位 和 覆盖标志位其中一个为true
						if (coverFlag || appendFlag) {
							processRecords.add(saveRecord);
						}
					}
					//会签节点
					else {
						List<HistoryProcessRecord> list = new ArrayList<>();
						list.add((HistoryProcessRecord) o);
						list.add(saveRecord);
						detailedInfoMap.put(processCurrentState, list);
					}
				}
				// 提交过 和 保存过， 非会签节点处理  覆盖
				else if (o != null && !isParallelNode) {
					HistoryProcessRecord record = (HistoryProcessRecord) o;
					//如果保存数据在后 ，则回填保存数据
					if (record.getCreateDate().before(saveRecord.getCreateDate())) {
						detailedInfoMap.put(currentNode, saveRecord);
					}
				}
				// 如果没有说明没有提交过 直接将保存数据回填  追加
				else if (o == null) {
					detailedInfoMap.put(currentNode, saveRecord);
				}
			}
		}
	}

	/**
	 * @description: 获取当前用户最新的保存信息
	 * @author: xujinbiao
	 * @date: 2020/4/30 18:08
	 * @param bean:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	private HistoryProcessRecord getSaveContent(QueryDetailedInfoVo bean) throws Exception {
		QueryHistoryProcessRecord object = new QueryHistoryProcessRecord();
		object.setCreator(UserUtil.getCurrentUserName());
		object.setType(HISOTORY_PROCESS_SAVE);
		object.setWorkflowBusinessid(bean.getWorkflowBusinessId());
		HistoryProcessRecord latestRecord = getLatestRecord(object);
		return latestRecord;
	}

	/**
	 * @description: 获取 最新的 记录列表
	 * @author: xujinbiao
	 * @date: 2020/5/11 10:28
	 * @param records:
	 * @return: java.util.List<cn.jwis.qualityworkflow.bean.HistoryProcessRecord>
	 **/
	private List<HistoryProcessRecord> getNewlyRecord(List<HistoryProcessRecord> records) {
		if (CollectionUtils.isEmpty(records)) {
			return records;
		}
		// 先获取 所有需要 添加的节点 按创建时间降序
		List<HistoryProcessRecord> needToAddRecord = new ArrayList<>();
		List<String> nodeNameList = records.stream().map(r -> r.getWorkflowNode()).distinct()
				.collect(Collectors.toList()); //["Apply","Plan"]
		for (String node : nodeNameList) {
			//将任务列表按降序排列
			List<HistoryProcessRecord> sortedList = records.stream()
					.sorted(Comparator.comparing(HistoryProcessRecord::getCreateDate).reversed())
					.collect(Collectors.toList());
			//添加遍历器
			Iterator<HistoryProcessRecord> iterator = sortedList.iterator();
			// flag 代表的 是 已经找到了符合条件的节点
			// 如果找到了符合条件的节点并且 它的下一个节点 不符合 名字相等的条件 就跳出
			boolean flag = false;
			while (iterator.hasNext()) {
				HistoryProcessRecord next = iterator.next();
				// 寻找符合条件的节点 然后 看看 他的下一个节点 是否也符合条件（考虑会签节点的情况）
				if (node.equals(next.getWorkflowNode())) {
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
	 * @description: 获取修剪 剩下 的节点名，不在 这个List中的需要剔除
	 * @author: xujinbiao
	 * @date: 2020/6/4 16:25
	 * @param list:
	 * @return: java.util.List<java.lang.String>
	 **/
	private List<String> pruneRecord(List<HistoryProcessRecord> list, String id) {
		// 按创建时间升序排序的列表
		List<HistoryProcessRecord> sortedList = list.stream()
				.sorted(Comparator.comparing(HistoryProcessRecord::getCreateDate)).collect(Collectors.toList());
		List<String> taskNameList = sortedList.stream().map(d -> d.getWorkflowNode()).distinct()
				.collect(Collectors.toList()); //["Apply","Plan","PQE"]
		//以当前节点为基准
		String processCurrentState = taskRecordDao.getProcessCurrentState(id); //"PQE"
		int index = taskNameList.indexOf(processCurrentState); //2
		// 保留当前节点 和剩下的节点 （和之前的节点）
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
	public Map<String, List<JSONObject>> getDetailedInfoMaps(QueryDetailedInfoVo bean) {
		List<JSONObject> detailedInfoList = historyProcessRecordDao.getDetailedInfoMaps(bean);
		Map<String, List<JSONObject>> resultMap = new HashMap<String, List<JSONObject>>();
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String account = currentUser.getAccount();
		for (JSONObject jsonObject : detailedInfoList) {
			String state = jsonObject.getString("workflow_node");
			String creator = jsonObject.getString("creator");
			String type = jsonObject.getString("type");
			String string = jsonObject.getString("content");
			if (StringUtils.isBlank(string)) {
				continue;
			}
			JSONObject content = JSONObjectUtil.toJSONObject(string);
			content.put("author", creator);
			content.put("handlerDate", jsonObject.get("create_date"));
			jsonObject.put("content", content);
			if ("SAVE".equals(type) && !account.equals(creator)) {
				continue;
			}
			List<JSONObject> list = resultMap.get(state);
			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<JSONObject>();
			}
			list.add(jsonObject);
			resultMap.put(state, list);
		}
		// 获取当前用户
		// TODO Auto-generated method stub
		return resultMap;
	}

	@Override
	public void deleteRecord(String templateKey, String workflowNode, String businessId) throws Exception {
		String currentUserName = UserUtil.getCurrentUserName();
		historyProcessRecordDao.deleteRecordByBusinessId(templateKey, currentUserName, workflowNode, businessId);
	}

	@Override
	public void deleteRejectBeforeRecord(String templateKey, String workflowNode, String businessId) throws Exception {
		historyProcessRecordDao.deleteRejectBeforeRecord(templateKey, workflowNode, businessId);
	}

}
