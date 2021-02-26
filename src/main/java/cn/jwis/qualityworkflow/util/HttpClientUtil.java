package cn.jwis.qualityworkflow.util;


import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author 李宗
 * @email zong.li@jwis.cn
 */
@SuppressWarnings("all")
public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	public static Map<String, String> baseHeaderMap = new HashMap<String, String>();

	static {
		baseHeaderMap.put("accept", "*/*");
		baseHeaderMap.put("content-type", "application/json");
		baseHeaderMap.put("accept-encoding", "UTF-8");
		baseHeaderMap.put("Accept-Language", "zh-CN");
		baseHeaderMap.put("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");
	}

	/**
	 * 文件下载
	 *
	 * @param HttpEntity
	 *            httpEntity
	 * @param url
	 *            url
	 * @return InputStream
	 */
	public static InputStream clientPOSTRequestGetIO(HttpEntity httpEntity, String url) {
		InputStream content = null;
		try {
			HttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost();
			URI uri = new URI(url);
			httpPost.setURI(uri);
			httpPost.setEntity(httpEntity);
			HttpResponse execute = client.execute(httpPost);
			HttpEntity entity = execute.getEntity();
			content = entity.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 公共的HttpClient get请求
	 * 
	 * @param url
	 *            url
	 * @return InputStream
	 */
	public static InputStream clientGETRequestGetIO(String url) {
		Map<String, String> map = getHeader();
		HttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet();
		for (String key : map.keySet()) {
			httpGet.addHeader(key, map.get(key));
		}
		InputStream content = null;
		try {
			URI uri = new URI(url);
			httpGet.setURI(uri);
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			content = entity.getContent();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return content;
	}

	/**
	 * 发送get请求
	 * 
	 * @param postUrl
	 * @param customHeaderMap
	 * @return
	 */
	public static String sendGetRequest(String postUrl, Map<String, String> customHeaderMap) {
		logger.info("Http Request:" + postUrl);
		String respContent = "";
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
				.setConnectionRequestTimeout(15000).build();

		HttpGet httpGet = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			Map<String, String> newHeaderMapper = new HashMap<String, String>();
			newHeaderMapper.putAll(baseHeaderMap);
			httpGet = new HttpGet(postUrl);
			if (customHeaderMap != null && customHeaderMap.size() > 0) {
				newHeaderMapper.putAll(customHeaderMap);
			}
			Set<String> keys = newHeaderMapper.keySet();
			for (String key : keys) {
				httpGet.setHeader(key, newHeaderMapper.get(key));
			}
			// forwardHeadersFromGateway(httpGet);
			httpGet.setConfig(requestConfig);
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			respContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error("send Http Request to:" + postUrl + " ,has errors:" + e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				logger.error("close Response OR httpClient to:" + postUrl + " ,has errors:" + e.getMessage());
			}
		}
		return respContent;
	}

	/**
	 * 发送post请求
	 * 
	 * @param postUrl
	 * @param bodyContent
	 * @param customHeaderMap
	 * @return
	 */
	public static String sendPostRequest(String postUrl, Map<String, String> bodyContent,
			Map<String, String> customHeaderMap) {
		String respContent = "";
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
				.setConnectionRequestTimeout(15000).build();

		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			Map<String, String> newHeaderMapper = new HashMap<String, String>();
			newHeaderMapper.putAll(baseHeaderMap);
			httpPost = new HttpPost(postUrl);
			if (customHeaderMap != null && customHeaderMap.size() > 0) {
				newHeaderMapper.putAll(customHeaderMap);
			}
			Set<String> keys = newHeaderMapper.keySet();
			for (String key : keys) {
				httpPost.setHeader(key, newHeaderMapper.get(key));
			}
			HttpEntity requestEntity = null;
			if (bodyContent != null && bodyContent.size() > 0) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String mapKey : bodyContent.keySet()) {
					params.add(new BasicNameValuePair(mapKey, bodyContent.get(mapKey)));
				}
				requestEntity = new UrlEncodedFormEntity(params, "UTF-8");
			}
			if (requestEntity != null) {
				httpPost.setEntity(requestEntity);
				httpPost.setHeader("content-type", requestEntity.getContentType().getValue());
			}
			httpPost.setConfig(requestConfig);
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			respContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error("send Http Request to:" + postUrl + " ,has errors:" + e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				logger.error("close Response OR httpClient to:" + postUrl + " ,has errors:" + e.getMessage());
			}
		}
		return respContent;
	}

	/**
	 * 发送HTTP POST请求
	 * 
	 * @param postUrl
	 * @param bodyContent
	 * @param customHeaderMap
	 * @return
	 */
	public static String sendPutRequest(String postUrl, Map<String, String> bodyContent,
			Map<String, String> customHeaderMap) {
		String respContent = "";
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
				.setConnectionRequestTimeout(15000).build();

		HttpPut httpPut = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			Map<String, String> newHeaderMapper = new HashMap<String, String>();
			newHeaderMapper.putAll(baseHeaderMap);
			httpPut = new HttpPut(postUrl);
			if (customHeaderMap != null && customHeaderMap.size() > 0) {
				newHeaderMapper.putAll(customHeaderMap);
			}
			Set<String> keys = newHeaderMapper.keySet();
			for (String key : keys) {
				httpPut.setHeader(key, newHeaderMapper.get(key));
			}
			HttpEntity requestEntity = null;
			if (bodyContent != null && bodyContent.size() > 0) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String mapKey : bodyContent.keySet()) {
					params.add(new BasicNameValuePair(mapKey, bodyContent.get(mapKey)));
				}
				requestEntity = new UrlEncodedFormEntity(params, "UTF-8");
			}
			if (requestEntity != null) {
				httpPut.setEntity(requestEntity);
				httpPut.setHeader("content-type", requestEntity.getContentType().getValue());
			}
			httpPut.setConfig(requestConfig);
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpPut);
			entity = response.getEntity();
			respContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error("send Http Request to:" + postUrl + " ,has errors:" + e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				logger.error("close Response OR httpClient to:" + postUrl + " ,has errors:" + e.getMessage());
			}
		}
		return respContent;
	}

	// 获取请求流
	public static OutputStream doGet(String url) {
		HttpClient httpclient = HttpClients.createDefault();
		OutputStream os = null;
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				os = new ByteArrayOutputStream();
				int temp = 0;
				while ((temp = instream.read()) != -1) {
					os.write(temp);
				}

				os.flush();
				os.close();
				return os;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return null;

	}

	/**
	 * 发送JSONObject数据
	 * 
	 * @param obj
	 * @param url
	 * @return get("status") get("responseContent")
	 */
	public static JSONObject postData(JSONObject obj, String url, Map<String, String> header) {
		JSONObject objc = null;
		try {
			objc = new JSONObject();
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			if (header != null && header.size() > 0) {
				for (String key : header.keySet()) {
					httpPost.addHeader(key, header.get(key));
				}
			}
			for (String key : baseHeaderMap.keySet()) {
				httpPost.addHeader(key, baseHeaderMap.get(key));
			}
			httpPost.setEntity(new StringEntity(obj.toJSONString(), "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			int status = response.getStatusLine().getStatusCode();
			objc.put("status", status);
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			objc.put("responseContent", JSONObject.parse(responseContent));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		}
		return objc;
	}

	/**
	 * 获取全局的一个Id生成
	 * 
	 * @param url
	 * @return
	 */
	public static String getGeneratorId(String url) {
		JSONObject objc = null;
		try {
			objc = new JSONObject();
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url + "/id/get");
			httpPost.addHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			objc.put("status", response.getStatusLine().getStatusCode());
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			objc.put("context", responseContent);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		}
		return objc.getString("context");
	}

	/**
	 * @param obj
	 * @param url
	 * @return get(" status ") get("responseContent")
	 */
	public static JSONObject sendHttpPost(JSONObject obj, String url) throws Exception {
		JSONObject result = new JSONObject();
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(new StringEntity(obj.toJSONString(), "UTF-8"));
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		result.put("status", response.getStatusLine().getStatusCode());
		String responseContent = EntityUtils.toString(entity, "UTF-8");
		result.put("responseContent", responseContent);
		return result;
	}




	/**
	 * 封装头文件Token的传递
	 * 
	 * @return
	 */
	public static Map<String, String> getHeader() {
		Map map = new HashMap<>();
		map.put("accesstoken", SessionHelper.getAccessToken());
		map.put("appName", SessionHelper.getAppName());
		map.put("appId", SessionHelper.getAppName());
		return map;
	}

}
