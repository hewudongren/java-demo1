package cn.jwis.qualityworkflow.util;

import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.interceptor.SessionHelper;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/12 10:55
 */
@Component
public class UserServer {

	@Autowired
	RestTemplate restTemplate;

	@Value("${platform.gateway.address}")
	private String gatewayAddress;

	/**
	 * @Author yuyangyang
	 * @Description 获取用户
	 * @Date 2020/8/11 10:40
	 * @Param
	 * @return
	 */
	public String getUserItemInfos(String temp) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		String url = gatewayAddress + "/foundation-pqm/user/searchGroupsByUser";
		ResponseEntity<Map> map = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
		List<String> object = (List<String>) map.getBody().get("result");
		if (CollectionUtils.isEmpty(object)) {
			return getCurrentUserName();
		} else {
			if (object.contains("admin") || object.contains(temp)) {
				return null;
			} else {
				return getCurrentUserName();
			}
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 通过角色名称获取oid
	 * @Date 2020/8/12 11:29
	 * @Param
	 * @return
	 */
	public String getOidByRole(String temp) {
		String oid = null;
		JSONObject paramMap = new JSONObject();
		paramMap.put("page", 1);
		paramMap.put("size", 10);
		paramMap.put("searchKey", temp);
		HttpHeaders headers = new HttpHeaders();
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.set("appName", SessionHelper.getAppName());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		String url = gatewayAddress + "/foundation-pqm/group/search?page={page}&size={size}&searchKey={searchKey}";
		ResponseEntity<Map> map = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class, paramMap);
		Map<String, Object> result = (Map<String, Object>) map.getBody().get("result");
		List<Map<String, Object>> list = (ArrayList<Map<String, Object>>) result.get("rows");
		for (Map<String, Object> map1 : list) {
			oid = map1.get("oid").toString();
		}
		return oid;
	}

	/**
	 * @Author yuyangyang
	 * @Description 通过oid获取角色下面的用户
	 * @Date 2020/8/12 11:29
	 * @Param
	 * @return
	 */
	public Set<String> getUserListByOid(String oid) {
		Set<String> set = new HashSet<>();
		HttpHeaders headers = new HttpHeaders();
		JSONObject paramMap = new JSONObject();
		paramMap.put("oid", oid);
		headers.set("accesstoken", SessionHelper.getAccessToken());
		headers.set("appName", SessionHelper.getAppName());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		String url = gatewayAddress + "/foundation-pqm/group/searchByOid?oid={oid}";
		ResponseEntity<Map> map = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class, paramMap);
		Map<String, Object> result = (Map<String, Object>) map.getBody().get("result");
		List<Map<String, Object>> userList = (ArrayList<Map<String, Object>>) result.get("userList");
		for (Map<String, Object> map1 : userList) {
			set.add(map1.get("account").toString());
		}
		return set;
	}
}