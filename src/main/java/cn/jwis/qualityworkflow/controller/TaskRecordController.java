package cn.jwis.qualityworkflow.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.service.TaskRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-07 16:56
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/taskRecord")
@Api(description = "流程记录接口")
public class TaskRecordController extends BaseClass {
	@Autowired
	TaskRecordService service;

	/**
	 * @description: 根据id获取Ecn流程进展
	 * @author: xujinbiao
	 * @date: 2020/4/30 11:27
	 * @param id:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@GetMapping(value = "getProgress")
	@ApiOperation(value = "根据id获取Ecn流程进展", notes = "根据id获取Ecn流程进展")
	public ResultBean getProgress(@RequestParam(value = "id") String id) throws Exception {
		JSONObject progress = service.getProgress(id);
		return ResultBean.success(progress);

	}

	/**
	 * 流程代办页面接口查询
	 */
	@RequestMapping(value = "getProcessAgent",method = RequestMethod.POST)
	@ApiOperation(value = "获取流程代办信息",notes = "获取流程代办信息")
	public ResultBean getProcessAgent(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		int page = jsonObject.getInteger("page");
		int size = jsonObject.getInteger("size");
		List<JSONObject> list = service.getProcessAgent(jsonObject);
		Map<String,String> title = service.getTitle(request);
		if (isNotNull(list)){
			return ResultBean.pagination(service.getProcessAgentcount(jsonObject), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, list,title);
		}
	}
}
