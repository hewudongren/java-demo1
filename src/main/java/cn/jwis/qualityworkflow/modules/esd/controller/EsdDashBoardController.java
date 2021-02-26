package cn.jwis.qualityworkflow.modules.esd.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdTargetInfo;
import cn.jwis.qualityworkflow.modules.esd.service.EsdDashBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/28 19:11
 */
@RestController
@RequestMapping(value = "/esdDashBoard")
@Api(description = "ESD看板接口")
public class EsdDashBoardController {

	@Autowired
	EsdDashBoardService esdDashBoardService;

	@RequestMapping(value = "getEsdData",method = RequestMethod.GET)
	@ApiOperation(value = "远程接口调用将数据保存到中间库",notes = "远程接口调用将数据保存到中间库")
	public ResultBean getEsdData(@RequestParam("startTime")String startTime,@RequestParam("endTime") String endTime) throws IOException, ParseException {
			esdDashBoardService.getEsdData(startTime,endTime);
			return ResultBean.success();
	}
	//新建目标值信息
	@RequestMapping(value = "saveTargetInfo",method = RequestMethod.POST)
	@ApiOperation(value = "新建目标值信息",notes = "新建目标值信息")
	public ResultBean saveTargetInfo(@RequestBody  EsdTargetInfo esdTargetInfo){
		esdDashBoardService.saveTargetInfo(esdTargetInfo);
		return  ResultBean.success();
	}
	//修改目标值信息
	@RequestMapping(value = "updateTargetInfo",method = RequestMethod.POST)
	@ApiOperation(value = "修改目标值信息",notes = "修改目标值信息")
	public ResultBean updateTargetInfo(@RequestBody  EsdTargetInfo esdTargetInfo){
		esdDashBoardService.updateTargetInfo(esdTargetInfo);
		return  ResultBean.success();
	}
	//删除目标值信息
	@RequestMapping(value = "deleteTargetInfo",method = RequestMethod.GET)
	@ApiOperation(value = "删除目标值信息",notes = "删除目标值信息")
	public ResultBean deleteTargetInfo(@RequestParam("id")String id){
		esdDashBoardService.deleteTargetInfo(id);
		return  ResultBean.success();
	}
	//查询目标值信息
	@RequestMapping(value = "getTargetInfo",method = RequestMethod.POST)
	@ApiOperation(value = "查询目标值信息",notes = "查询目标值信息")
	public ResultBean getTargetInfo(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		List<EsdTargetInfo> list = esdDashBoardService.getTargetInfo(page,size,startTime,endTime);
		Map<String,String> title = esdDashBoardService.getTitle(request);
		if (CollectionUtils.isNotEmpty(list)){
			return ResultBean.pagination(esdDashBoardService.getTargetInfoCount(startTime,endTime), page, size, list,title);
		}else {
			return ResultBean.pagination(0L,page,size,list,title);
		}
	}
	//导出目标值模板信息
	@RequestMapping(value = "/exportTargetInfoTemplate", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "导出目标值信息模板", notes = "导出目标值信息模板")
	public void exportEsdInStanInfoTemplate(HttpServletResponse response) {
		esdDashBoardService.exportEsdInStanInfoTemplate(response);
	}
	//导入目标值信息
	@RequestMapping(value = "/importTargetInfo", method = RequestMethod.POST)
	@ApiOperation(value = "导入目标值信息", notes = "导入目标值信息")
	public ResultBean importTargetInfo(MultipartFile file) {
		ResultBean resultBean = new ResultBean();
		Boolean flag = esdDashBoardService.importTargetInfo(file);
		if (flag) {
			resultBean.setMessage("导入成功");
		} else {
			resultBean.setCode(-1);
			resultBean.setMessage("导入失败");
		}
		return resultBean;
	}
	//查询ESD的DashBoard的图表信息
	@RequestMapping(value = "getEsdDashBoardInfo",method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD的DashBoard的图表信息",notes = "查询ESD的DashBoard的图表信息")
	public ResultBean getEsdDashBoardInfo(@RequestBody JSONObject jsonObject) throws ParseException {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdDashBoardService.getEsdDashBoardInfo(jsonObject));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description  查询LT的标准信息
	 * @Date  2020/8/3  17:32
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getEmailList", method = RequestMethod.GET)
	@ApiOperation(value = "查询邮件信息", notes = "查询邮件信息")
	public ResultBean getEmailList(@RequestParam("category")String category){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdDashBoardService.getEmailList(category));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 修改LT的标准信息
	 * @Date  2020/8/3  17:33
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/updateEmailInfo", method = RequestMethod.POST)
	@ApiOperation(value = "修改邮件信息", notes = "修改邮件信息")
	public ResultBean updateEmailInfo(@RequestBody JSONObject jsonObject){
		esdDashBoardService.updateEmailInfo(jsonObject);
		return ResultBean.success();
	}
}