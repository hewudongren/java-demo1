package cn.jwis.qualityworkflow.service.imp;

import cn.jwis.qualityworkflow.service.WebService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/18 14:06
 */
@Service
public class WebServiceImpl implements WebService {

	@Value("${fe.line.url}")
	private String lineWsdlUrl;

	@Value("${fe.equipment.url}")
	private String equipmentWsdlUrl;

	public static final Logger logger = LoggerFactory.getLogger(WebServiceImpl.class);

	@Override
	public List<String> getLine() throws IOException {
		JSONObject jsonObject = webServiceLine(lineWsdlUrl);
		JSONArray mainlist = jsonObject.getJSONArray("Mainlist");
	    List<String> result = new ArrayList<>();
		for (int i = 0; i <mainlist.size() ; i++) {
			result.add(mainlist.getString(i));
		}
		return result;
	}

	@Override
	public JSONObject getEquipment() throws IOException {
		JSONObject result = new JSONObject();
		JSONObject jsonObject = webServiceLine(equipmentWsdlUrl);
		JSONArray mainlist = jsonObject.getJSONArray("Mainlist");
		Set<String> equipmentNumberList = new HashSet<>();
		Set<String> equipmentTypeList = new HashSet<>();
		Set<String> equipmentNameList = new HashSet<>();
		for (int i = 0; i <mainlist.size() ; i++) {
			JSONObject jsonObject1 = mainlist.getJSONObject(i);
			String tEquipmentNumber = jsonObject1.getString("t_Equipment_number");
			String tEquipmentType = jsonObject1.getString("t_Equipment_type");
			String tEquipmentName = jsonObject1.getString("t_Equipment_name");
			if (StringUtils.isNotEmpty(tEquipmentType)){
				equipmentTypeList.add(tEquipmentType);
			}
			if (StringUtils.isNotEmpty(tEquipmentNumber)){
				equipmentNumberList.add(tEquipmentNumber);
			}
			if (StringUtils.isNotEmpty(tEquipmentName)){
				equipmentNameList.add(tEquipmentName);
			}
		}
		result.put("equipmentNumber",equipmentNumberList);
		result.put("equipmentType",equipmentTypeList);
		result.put("equipmentName",equipmentNameList);
		return result;
	}

	@Override
	public JSONObject webServiceEsd(String wsdUrl,String startTime,String endTime) throws IOException {
		JSONObject temp = new JSONObject();
		logger.info("------------开始连接------------");
		URL url = new URL(wsdUrl);
		// 第二步：打开一个通向服务地址的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		logger.info("------------连接打开------------");
		// 第三步：设置参数
		// 3.1发送方式设置：POST必须大写
		connection.setRequestMethod("POST");
		// 3.2设置数据格式：content-type
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		connection.setRequestProperty("Content-Length", "668");
		connection.setRequestProperty("SOAPAction", "http://tempuri.org/GetEsdList");
		connection.setRequestProperty("Host", "10.124.149.29");
		// 3.3设置输入输出，因为默认新创建的connection没有读写权限，
		connection.setDoInput(true);
		connection.setDoOutput(true);
		//传参方式XML的拼接
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
				"  <soap:Body>\n" + "    <GetEsdList xmlns=\"http://tempuri.org/\">\n" +
				"      <StartTime>"+startTime+"</StartTime>\n" +
				"      <EndTime>"+endTime+"</EndTime>\n" +
				"    </GetEsdList>\n" + "  </soap:Body>\n" + "</soap:Envelope>";
		logger.info("logger------------传递参数------------" + xml);
		OutputStream os = connection.getOutputStream();
		os.write(xml.getBytes());
		// 第五步：接收服务端响应，打印
		int responseCode = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		logger.info("------------- responseCode:" + responseCode + "responseMessage:" + responseMessage);
		if (200 == responseCode) {// 表示服务端响应成功
			BufferedReader bufr = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 设置文件输入流的编码，和url的编码一致
			String lines = "";
			String result = "";
			while ((lines = bufr.readLine()) != null) {
				result += lines;
			}
			logger.info("----------response xml--------" + result);
			logger.info("--------------服务调用成功------------");
			temp = JSONObject.parseObject(result);
		}
		return temp;
	}


	/**
     * @Author yuyangyang
     * @Description 远程接口获取线体的下拉值信息
     * @Date  2020/7/18  14:17
     * @Param
     * @return
     */
	private JSONObject webServiceLine(String wsdUrl) throws IOException {
		JSONObject temp = new JSONObject();
		logger.info("------------开始连接------------");
		URL url = new URL(wsdUrl);
		// 第二步：打开一个通向服务地址的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		logger.info("------------连接打开------------");
		// 第三步：设置参数
		// 3.1发送方式设置：POST必须大写
		connection.setRequestMethod("GET");
		// 3.2设置数据格式：content-type
		connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
		// 3.3设置输入输出，因为默认新创建的connection没有读写权限，
		connection.setDoInput(true);
		connection.setDoOutput(true);
		// 第五步：接收服务端响应，打印
		int responseCode = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		logger.info("------------- responseCode:" + responseCode + "responseMessage:" + responseMessage);
		if (200 == responseCode) {// 表示服务端响应成功
			BufferedReader bufr = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 设置文件输入流的编码，和url的编码一致
			String lines = "";
			String result = "";
			while ((lines = bufr.readLine()) != null) {
				result += lines;
			}
			logger.info("----------response xml--------" + result);
			logger.info("--------------服务调用成功------------");
			temp = JSONObject.parseObject(result);
		}
		return temp;
	}



}