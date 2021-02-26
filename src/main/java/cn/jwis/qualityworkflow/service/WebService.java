package cn.jwis.qualityworkflow.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/18 14:06
 */
@Service
public interface WebService {

	List<String> getLine() throws IOException;

	JSONObject getEquipment() throws IOException;

	JSONObject webServiceEsd(String wsdUrl,String startTime,String endTime) throws IOException;

}