/**
 *
 */
package cn.jwis.qualityworkflow.util;

import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkflowServer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2018年11月9日
 */

@Component
public class WorkflowServer {
	@Autowired
    RestTemplate restTemplate;

	@Value ("${platform.gateway.address}")
	private String gatewayAddress;

	public static final Logger logger = LoggerFactory.getLogger(WorkflowServer.class);

	public String startProcessInstance(String tenantId, String processDefinitionKey, JSONArray variables) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject paramMap = new JSONObject();
		paramMap.put("tenantId", tenantId);
		paramMap.put("processDefinitionKey", processDefinitionKey);
		paramMap.put("variables", variables);
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(paramMap, headers);
		ResponseEntity<JSONObject> postForEntity = restTemplate.exchange(
				gatewayAddress + "/workflow/workflow/runtime/startProcessInstance", HttpMethod.POST, entity,
				JSONObject.class);
		String processInstanceId = getProcessInstanceId(postForEntity);
		return processInstanceId;
	}

	private String getProcessInstanceId(ResponseEntity<JSONObject> postForEntity) {
		String processInstanceId = null;
		// 获取流程id
		if (null == postForEntity) {
			return processInstanceId;
		}
		JSONObject body = postForEntity.getBody();
		Object processInstanceIdObj = body.get("id");
		processInstanceId = String.valueOf(processInstanceIdObj);
		return processInstanceId;
	}

	@SuppressWarnings("unchecked")
	public String getNewestProcessTask(String tenantId, String processInstanceId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		JSONObject paramMap = new JSONObject();
		paramMap.put("tenantId", tenantId);
		paramMap.put("processInstanceId", processInstanceId);
		ResponseEntity<JSONObject> postForEntity = restTemplate.exchange(
				gatewayAddress
						+ "/workflow/workflow/task/tasks?processInstanceId={processInstanceId}&tenantId={tenantId}",
				HttpMethod.GET, entity, JSONObject.class, paramMap);
		JSONObject responseInfo = postForEntity.getBody();
		List<Map<String, Object>> data = (List<Map<String, Object>>) responseInfo.get("data");
		Map<String, Object> taskInfoMap = data.get(0);
		Object taskIdObj = taskInfoMap.get("id");
		String taskId = String.valueOf(taskIdObj);
		return taskId;
	}

	/**
	 *
	 * @Description: 得到流程任务变量信息
	 * @author longjun
	 * @date 2018年11月9日
	 * @param taskId
	 * @return
	 */
	public Map<String, Object> getTaskVariables(String taskId) {
		Map<String, Object> taskVariableMap = new HashMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		JSONObject paramMap1 = new JSONObject();
		paramMap1.put("taskId", taskId);
		ResponseEntity<JSONObject> postForEntity1 = restTemplate.exchange(
				gatewayAddress + "/workflow/workflow/task/formData/{taskId}", HttpMethod.GET, entity, JSONObject.class,
				paramMap1);
		JSONObject body = postForEntity1.getBody();
		Object variablesObj = body.get("variables");
		Object nameObj = body.get("name");
		String name = String.valueOf(nameObj);
		taskVariableMap.put(name, variablesObj);
		return taskVariableMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserInfo(String assignee) {
		JSONObject paramMap = new JSONObject();
		paramMap.put("assignee", assignee);
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<JSONObject> postForEntity = restTemplate.exchange(
				gatewayAddress + "/account/common/getUserByAccount?account={assignee}", HttpMethod.GET, entity,
				JSONObject.class, paramMap);
		JSONObject responseInfo = postForEntity.getBody();
		Map<String, Object> userInfoMap = (Map<String, Object>) responseInfo.get("result");
		return userInfoMap;
	}

	public void finishTask(String processInstanceId, String taskId, JSONObject variables,
			Map<String, Object> localVariables) {
		JSONObject paramMap = new JSONObject();
		paramMap.put("processInstanceId", processInstanceId);
		paramMap.put("taskId", taskId);
		paramMap.put("variables", variables);
		paramMap.put("localVariables", localVariables);
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(paramMap, headers);
		restTemplate.exchange(gatewayAddress + "/workflow/workflow/task/finishTask", HttpMethod.POST, entity, Map.class);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProcessHistoryTasks(String processInstanceId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		JSONObject paramMap = new JSONObject();
		paramMap.put("processInstanceId", processInstanceId);
		ResponseEntity<JSONObject> postForEntity = restTemplate.exchange(gatewayAddress
				+ "/workflow/workflow/history/historic-task-instances?processInstanceId={processInstanceId}&size=100",
				HttpMethod.GET, entity, JSONObject.class, paramMap);
		JSONObject body = postForEntity.getBody();
		List<Map<String, Object>> historyTaskList = (List<Map<String, Object>>) body.get("data");
		return historyTaskList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProcessVariables(String processInstanceId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		JSONObject paramMap1 = new JSONObject();
		paramMap1.put("processInstanceId", processInstanceId);
		ResponseEntity<JSONObject> postForEntity1 = restTemplate.exchange(
				gatewayAddress + "/workflow/workflow/history/historic-process-instances/{processInstanceId}",
				HttpMethod.GET, entity, JSONObject.class, paramMap1);
		JSONObject body = postForEntity1.getBody();
		Object object = body.get("variables");
		List<Map<String, Object>> list = (List<Map<String, Object>>) object;
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserName() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<JSONObject> postForEntity1 = restTemplate.exchange(
				gatewayAddress + "/platform/sys/user/read?skip=0&take=100000&name=",
				HttpMethod.GET, entity, JSONObject.class);
		JSONObject body = postForEntity1.getBody();
		JSONObject jsonObject = body.getJSONObject("result");
		Object object = jsonObject.get("rows");
		List<Map<String, Object>> list = (List<Map<String, Object>>) object;
		return list;
	}
}
