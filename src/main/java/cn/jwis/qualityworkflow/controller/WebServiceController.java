package cn.jwis.qualityworkflow.controller;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.service.WebService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/18 14:06
 */
@RestController
@RequestMapping(value = "/webService")
@Api(description = "远程调用webService接口")
public class WebServiceController {
    @Autowired
	WebService webService;

	@GetMapping(value = "getLine")
	@ApiOperation(value = "远程接口获取线体的下拉值信息", notes = "远程接口获取线体的下拉值信息")
	public ResultBean getLine() throws IOException {
		List<String> line = webService.getLine();
		return ResultBean.success(line);
	}

	@GetMapping(value = "getEquipment")
	@ApiOperation(value = "远程接口获取设备的下拉值信息", notes = "远程接口获取设备的下拉值信息")
	public ResultBean getEquipment() throws IOException {
		JSONObject result = webService.getEquipment();
		return ResultBean.success(result);
	}
}