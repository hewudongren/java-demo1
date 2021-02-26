package cn.jwis.qualityworkflow.modules.homePage.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.homePage.service.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 首页看板接口
 * @Author yuyangyang
 * @Date 2020/7/6 15:48
 */
@RestController
@RequestMapping(value = "/homePage")
@Api(description = "QMS2.0首页接口")
public class HomePageController {

	@Autowired
	HomePageService homePageService;

	/**
	 * @Author yuyangyang
	 * @Description 首页整体进程的接口
	 * @Date  2020/7/6  16:07
	 * @Param
	 * @return
	 */
	@PostMapping(value = "/getAllProcessStatus")
	@ApiOperation(response = ResultBean.class, value = "首页获取整体流程的处理，超期信息接口", notes = "首页获取整体流程的处理，超期信息接口")
	public ResultBean getAllProcessStatus(@RequestBody JSONObject bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(homePageService.getAllProcessStatus(bean));
		return resultBean;
	}
	
	
	/**
	 * @Author yuyangyang
	 * @Description 首页获取不同流程未关闭的流程信息
	 * @Date  2020/7/6  16:08
	 * @Param
	 * @return
	 */
	@GetMapping(value = "/getProcessStatusByName")
	@ApiOperation(response = ResultBean.class, value = "首页获取不同流程未关闭的订单信息", notes = "首页获取不同流程未关闭的订单信息")
	public ResultBean getProcessStatusByName(HttpServletRequest request, @RequestParam("name") String name){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(homePageService.getProcessStatusByName(name,request));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 首页获取不同流程未关闭的流程信息
	 * @Date  2020/7/6  16:08
	 * @Param
	 * @return
	 */
	@GetMapping(value = "/getKpiReport")
	@ApiOperation(response = ResultBean.class, value = "获取R2的总体KPI报表信息", notes = "获取R2的总体KPI报表信息")
	public ResultBean getKpiReport(@RequestParam("modelCategory") String modelCategory) throws ParseException {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(homePageService.getKpiReport(modelCategory));
		return resultBean;
	}
}