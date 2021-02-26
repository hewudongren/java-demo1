package cn.jwis.qualityworkflow.modules.qims.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.qims.service.GrayDashBoardService;
import cn.jwis.qualityworkflow.modules.qims.util.QimsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @Description 灰色问题DashBoard接口
 * @Author yuyangyang
 * @Date 2020/5/29 11:40
 */
@RestController
@RequestMapping(value = "/grayDash")
@Api(description = "灰色问题DashBoard")
public class GrayDashBoardController extends BaseClass {
	@Autowired
	GrayDashBoardService grayDashBoardService;


	@RequestMapping(value = "/getAllAndClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板新增,已处理,关闭数量", notes = "DashBoard看板新增,已处理,关闭数量")
	public ResultBean getAllAndClose(@RequestBody JSONObject jsonObject){
       ResultBean resultBean = new ResultBean();
       resultBean.setData(grayDashBoardService.getAllAndClose(jsonObject));
       return resultBean;
	}

	@RequestMapping(value = "/getOverdue", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板超期数量", notes = "DashBoard看板超期数量")
	public ResultBean getOverdue(@RequestBody JSONObject jsonObject) throws ParseException {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayDashBoardService.getOverdue(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getDashBoardClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板关闭和及时关闭趋势图", notes = "DashBoard看板关闭和及时关闭趋势图")
	public ResultBean getDashBoardClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayDashBoardService.getDashBoardClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getDashBoardNotClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板关闭与未关闭数量", notes = "DashBoard看板关闭与未关闭数量")
	public ResultBean getDashBoardNotClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayDashBoardService.getDashBoardNotClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getAllData", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板累计关闭数，关闭率接口", notes = "DashBoard看板累计关闭数，关闭率接口")
	public ResultBean getAllData(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayDashBoardService.getAllData());
		return resultBean;
	}

	@RequestMapping(value = "/getUnseasonalBola", method = RequestMethod.GET)
	@ApiOperation(value = "累计未关闭记录(不及时处理柏拉图)", notes = "累计未关闭记录(不及时处理柏拉图)")
	public ResultBean getUnseasonalBola(@RequestParam("type") String type){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(grayDashBoardService.getUnseasonalBola(type));
		return resultBean;
	}

	@RequestMapping(value = "/exportUnseasonalBola", method = RequestMethod.GET)
	@ApiOperation(value = "导出累计未关闭记录(不及时处理柏拉图)", notes = "导出累计未关闭记录(不及时处理柏拉图)")
	public void exportUnseasonalBola(HttpServletRequest request,HttpServletResponse response,@RequestParam("type") String type){
		grayDashBoardService.exportUnseasonalBola(type,response,request);
	}

	@RequestMapping(value = "/getUnseasonalList", method = RequestMethod.POST)
	@ApiOperation(value = "累计未关闭记录(超期未回复列表)", notes = "累计未关闭记录(超期未回复列表)")
	public ResultBean getUnseasonalList(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		int page = jsonObject.getInteger("page");
		int size = jsonObject.getInteger("size");
		List<JSONObject> unseasonalList = grayDashBoardService.getUnseasonalList(page, size);
		Map<String,String> title = grayDashBoardService.getTitle(request);
		if (isNotNull(unseasonalList)){
			return ResultBean.pagination(grayDashBoardService.getUnseasonalCount(), page, size, unseasonalList,title);
		}else {
			return ResultBean.pagination(0L, page, size, unseasonalList,title);
		}
	}

	@RequestMapping(value = "exportUnseasonalList",method = RequestMethod.POST)
	@ApiOperation(value = "导出超期未回复列表",notes = "导出超期未回复列表")
	public void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request){
		grayDashBoardService.exportUnseasonalList(response,request);
	}

	@RequestMapping(value = "/getDetails", method = RequestMethod.POST)
	@ApiOperation(value = "导出详细统计查询接口", notes = "详细统计查询接口")
	public ResultBean getDetails(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		List<JSONObject> blackDetails = grayDashBoardService.getDetails(jsonObject);
		List<JSONObject> result = new ArrayList<>();
		int temp = blackDetails.size();
		if (isNotNull(blackDetails)){
			result = QimsUtil.getData(blackDetails,page,size,temp);
			Map<String,String> title = grayDashBoardService.getDetailsTitle(request);
			return ResultBean.pagination(Long.valueOf(temp), page, size, result,title);
		}else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "/exportDetails", method = RequestMethod.POST)
	@ApiOperation(value = "详细统计导出接口", notes = "详细统计导出接口")
	public void exportDetails(HttpServletRequest request,HttpServletResponse response, @RequestBody JSONObject jsonObject){
		grayDashBoardService.exportDetails(request,response,jsonObject);
	}
}
