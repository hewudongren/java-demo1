package cn.jwis.qualityworkflow.modules.qims.controller;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemSearch;
import cn.jwis.qualityworkflow.modules.qims.service.GrayProblemService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/25 17:15
 */
@RestController
@RequestMapping(value = "/grayProblem")
@Api(description = "QIMS灰色问题接口")
public class GrayProblemController {

	@Autowired
	GrayProblemService grayProblemService;

	@RequestMapping(value = "/saveGrayProblem", method = RequestMethod.POST)
	@ApiOperation(value = "创建灰色问题", notes = "创建灰色问题")
	public ResultBean saveGrayProblem(@RequestBody GrayProblemInfo bean)  {
		ResultBean resultBean = new ResultBean();
		grayProblemService.saveGrayProblem(bean);
		return resultBean;
	}

	@RequestMapping(value = "/confirmGrayProblem", method = RequestMethod.POST)
	@ApiOperation(value = "提交灰色问题", notes = "提交灰色问题")
	public ResultBean confirmGrayProblem(@RequestBody GrayProblemInfo bean)  {
		ResultBean resultBean = new ResultBean();
		grayProblemService.confirmGrayProblem(bean);
		return resultBean;
	}

	@RequestMapping(value = "/temporaryGrayProblem", method = RequestMethod.POST)
	@ApiOperation(value = "暂存灰色问题", notes = "暂存灰色问题")
	public ResultBean temporaryGrayProblem(@RequestBody JSONObject bean)  {
		ResultBean resultBean = new ResultBean();
		grayProblemService.temporaryGrayProblem(bean,"SAVE");
		return resultBean;
	}

	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "QIMS下拉值获取",notes = "QIMS下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayProblemService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "getGrayProblemList",method = RequestMethod.POST)
	@ApiOperation(value = "获取灰色问题列表",notes = "获取灰色问题列表")
	public ResultBean getGrayProblemList(HttpServletRequest request, @RequestBody GrayProblemSearch grayProblemSearch){
		ResultBean resultBean = grayProblemService.getGrayProblemList(grayProblemSearch,request);
		return resultBean;
	}

	@RequestMapping(value = "exportGrayProblemList",method = RequestMethod.POST)
	@ApiOperation(value = "导出灰色问题列表",notes = "导出灰色问题列表")
	public ResultBean exportGrayProblemList(HttpServletRequest request, HttpServletResponse response, @RequestBody GrayProblemSearch grayProblemSearch){
	    grayProblemService.exportGrayProblemList(grayProblemSearch,request,response);
		return ResultBean.success();
	}

	@RequestMapping(value = "getGrayProblemDetails",method = RequestMethod.GET)
	@ApiOperation(value = "灰色问题详情信息",notes = "灰色问题详情信息")
	public ResultBean getGrayProblemDetails(@RequestParam("id") String id){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayProblemService.getGrayProblemDetails(id));
		return resultBean;
	}
}