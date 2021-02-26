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
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.modules.qims.service.CqaDashBoardService;
import cn.jwis.qualityworkflow.modules.qims.util.QimsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @Description CQA问题DashBoard接口
 * @Author yuyangyang
 * @Date 2020/5/29 11:40
 */
@RestController
@RequestMapping(value = "/cqaDash")
@Api(description = "Cqa问题DashBoard")
public class CqaDashBoardController extends BaseClass {
	@Autowired
	CqaDashBoardService cqaDashBoardService;


	@RequestMapping(value = "/getAllAndClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板新增,已处理,关闭数量", notes = "DashBoard看板新增,已处理,关闭数量")
	public ResultBean getAllAndClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(cqaDashBoardService.getAllAndClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getOverdue", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板超期数量", notes = "DashBoard看板超期数量")
	public ResultBean getOverdue(@RequestBody JSONObject jsonObject) throws ParseException {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(cqaDashBoardService.getOverdue(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getDashBoardClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板关闭和及时关闭趋势图", notes = "DashBoard看板关闭和及时关闭趋势图")
	public ResultBean getDashBoardClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(cqaDashBoardService.getDashBoardClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getDashBoardNotClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板关闭与未关闭数量", notes = "DashBoard看板关闭与未关闭数量")
	public ResultBean getDashBoardNotClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(cqaDashBoardService.getDashBoardNotClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getAllData", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板累计关闭数，关闭率接口", notes = "DashBoard看板累计关闭数，关闭率接口")
	public ResultBean getAllData(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(cqaDashBoardService.getAllData());
		return resultBean;
	}

	@RequestMapping(value = "/getUnseasonalBola", method = RequestMethod.GET)
	@ApiOperation(value = "累计未关闭记录(不及时处理柏拉图)", notes = "累计未关闭记录(不及时处理柏拉图)")
	public ResultBean getUnseasonalBola(@RequestParam("type") String type){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(cqaDashBoardService.getUnseasonalBola(type));
		return resultBean;
	}

	@RequestMapping(value = "/exportUnseasonalBola", method = RequestMethod.GET)
	@ApiOperation(value = "导出累计未关闭记录(不及时处理柏拉图)", notes = "导出累计未关闭记录(不及时处理柏拉图)")
	public void exportUnseasonalBola(HttpServletRequest request,HttpServletResponse response,@RequestParam("type") String type){
		cqaDashBoardService.exportUnseasonalBola(type,response,request);
	}


	@RequestMapping(value = "/getUnseasonalList", method = RequestMethod.POST)
	@ApiOperation(value = "累计未关闭记录(超期未回复列表)", notes = "累计未关闭记录(超期未回复列表)")
	public ResultBean getUnseasonalList(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		int page = jsonObject.getInteger("page");
		int size = jsonObject.getInteger("size");
		List<QimsCqaInfo> unseasonalList = cqaDashBoardService.getUnseasonalList(page, size);
		Map<String,String> title = cqaDashBoardService.getTitle(request);
		if (isNotNull(unseasonalList)){
			return ResultBean.pagination(cqaDashBoardService.getUnseasonalCount(), page, size, unseasonalList,title);
		}else {
			return ResultBean.pagination(0L, page, size, unseasonalList,title);
		}
	}

	@RequestMapping(value = "exportUnseasonalList",method = RequestMethod.POST)
	@ApiOperation(value = "导出超期未回复列表",notes = "导出超期未回复列表")
	public void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request){
		cqaDashBoardService.exportUnseasonalList(response,request);
	}

	@RequestMapping(value = "/getDetails", method = RequestMethod.POST)
	@ApiOperation(value = "详细统计查询接口", notes = "详细统计查询接口")
	public ResultBean getBlackDetails(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		List<JSONObject> cqaDetails = cqaDashBoardService.getDetails(jsonObject);
		List<JSONObject> result = new ArrayList<>();
		int temp = cqaDetails.size();
		if (isNotNull(cqaDetails)){
			result = QimsUtil.getData(cqaDetails,page,size,temp);
			Map<String,String> title = cqaDashBoardService.getDetailsTitle(request);
			return ResultBean.pagination(Long.valueOf(temp), page, size, result,title);
		}else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "/exportDetails", method = RequestMethod.POST)
	@ApiOperation(value = "详细统计导出接口", notes = "详细统计导出接口")
	public void exportDetails(HttpServletRequest request,HttpServletResponse response, @RequestBody JSONObject jsonObject){
		cqaDashBoardService.exportDetails(request,response,jsonObject);
	}

}
