/**
 *
 */
package cn.jwis.qualityworkflow.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.interceptor.SessionHelper;

/**
 * @ClassName: WorkflowServer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2018年11月9日
 */

@Component
public class DFSServer {
	@Autowired
    RestTemplate restTemplate;

	@Value ("${platform.gateway.address}")
	private String gatewayAddress;
	
	public JSONObject uploadFile2DFS(File file) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("accesstoken", SessionHelper.getAccessToken());
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		form.add("file", fileSystemResource);
		// 用HttpEntity封装整个请求报文
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
		ResponseEntity<JSONObject> postForEntity = restTemplate.exchange(gatewayAddress + "/dfs-service/file/uploadFile", HttpMethod.POST,
				entity, JSONObject.class);
		JSONObject body = postForEntity.getBody();
		JSONObject resultMap =  body.getJSONObject("result");
		return resultMap;
	}
}
