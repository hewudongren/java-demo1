package cn.jwis.qualityworkflow.modules.apqp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreatePfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateQcChartRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.service.RiskItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-29 17:43
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/riskItem")
@Api(description = "APQP高风险项Controller")
public class RiskItemController {


	@Autowired
	RiskItemService service;
	/**
	 * @description: 新增Dvt1高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/29 17:55
	 * @param vo:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/insertDvt1RiskList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "新增Dvt1高风险项", notes = "新增Dvt1高风险项")
	public ResultBean insertDvt1RiskList(@RequestBody CreateDvt1RiskItem vo) throws Exception {
		service.insertDvt1RiskList(vo);
		return ResultBean.success();
	}


	/**
	 * @description: 新增PFMEA高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/29 17:55
	 * @param vo:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/insertPfmeaRiskList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "新增PFMEA高风险项", notes = "新增PFMEA高风险项")
	public ResultBean insertPfmeaRiskList(@RequestBody CreatePfmeaRiskItem vo) throws Exception {
		service.insertPfmeaRiskList(vo);
		return ResultBean.success();
	}

	/**
	 * @description: 新增QC_Chart高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/29 17:55
	 * @param vo:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/insertQcChartRiskList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "新增QC_Chart高风险项", notes = "新增QC_Chart高风险项")
	public ResultBean insertQcChartRiskList(@RequestBody CreateQcChartRiskItem vo) throws Exception {
		service.insertQcChartRiskList(vo);
		return ResultBean.success();
	}


}
