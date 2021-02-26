package cn.jwis.qualityworkflow.modules.esd.controller;

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
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpeciaManageSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialManageInfo;
import cn.jwis.qualityworkflow.modules.esd.service.EsdSpecialManageInfoService;
import cn.jwis.qualityworkflow.util.UserServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/8 16:33
 */
@RestController
@RequestMapping(value = "/esdSpecialManage")
@Api(description = "ESD来料检验单管理")
public class EsdSpecialManageInfoController extends BaseClass {

	@Autowired
	EsdSpecialManageInfoService esdSpecialManageInfoService;

	@Autowired
	UserServer userServer;
    /**
     * @Author yuyangyang
     * @Description Esd来料检验单管理查询下拉值接口
     * @Date  2020/6/16  17:24
     * @Param
     * @return
     */
	@RequestMapping(value = "/getDropdownValue", method = RequestMethod.GET)
	@ApiOperation(value = "ESD来料检验单管理查询下拉值接口", notes = "ESD来料检验单管理查询下拉值接口")
	public ResultBean saveEsdSpecialInfo(@RequestParam("parameter")String parameter)  {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialManageInfoService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "/findSpecialManageInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD来料检验单信息", notes = "查询ESD来料检验单信息")
	public ResultBean confirm(HttpServletRequest request, @RequestBody EsdSpeciaManageSearch bean)  {
		int page = bean.getPage();
		int size = bean.getSize();
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		bean.setAssignee(userItemInfos);
		List<JSONObject> list = esdSpecialManageInfoService.findSpecialManageInfo(bean);
		Map<String,String> title = esdSpecialManageInfoService.getTitle(request);
		if (isNotNull(list)){
			return ResultBean.pagination(esdSpecialManageInfoService.findSpecialManageCount(bean), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, list,title);
		}
	}


	@RequestMapping(value = "/exportSpecialManageInfo", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出ESD来料检验单管理", notes = "导出ESD来料检验单管理")
	public void exportSpecialManageInfo(@RequestBody EsdSpeciaManageSearch bean, HttpServletResponse response,HttpServletRequest request) {
		esdSpecialManageInfoService.exportSpecialManageInfo(bean,response,request);
	}


	@RequestMapping(value = "/getModelList", method = RequestMethod.POST)
	@ApiOperation(value = "ESD来料检验单管理新增时机型下拉值接口", notes = "ESD来料检验单管理新增时机型下拉值接口")
	public ResultBean getModelList()  {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialManageInfoService.getModelList());
		return resultBean;
	}

	@RequestMapping(value = "/getRateAndSum", method = RequestMethod.POST)
	@ApiOperation(value = "通过样品名获取比率和抽样数量", notes = "通过样品名获取比率和抽样数量")
	public ResultBean getRateAndSum(@RequestBody  JSONObject jsonObject)  {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialManageInfoService.getRateAndSum(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "findTableNameBySampleName",method = RequestMethod.GET)
	@ApiOperation(value = "通过选择的样品名获取表头的信息(带出范围信息)",notes = "通过选择的样品名获取表头的信息(带出范围信息)")
	public ResultBean findTableNameBySampleName(@RequestParam("sampleName") String sampleName){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialManageInfoService.findTableNameBySampleName(sampleName));
		return  resultBean;
	}


	@RequestMapping(value = "saveEsdSpecialManageInfo",method = RequestMethod.POST)
	@ApiOperation(value = "保存ESD来料检验单信息",notes = "保存ESD来料检验单信息")
	public ResultBean saveEsdSpecialManageInfo(@RequestBody EsdSpecialManageInfo esdSpecialManageInfo){
		ResultBean resultBean = new ResultBean();
		esdSpecialManageInfoService.saveEsdSpecialManageInfo(esdSpecialManageInfo);
		return  resultBean;
	}

	@RequestMapping(value = "handleEsdSpecialManageInfo",method = RequestMethod.POST)
	@ApiOperation(value = "处理ESD来料检验单信息",notes = "处理ESD来料检验单信息")
	public ResultBean handleEsdSpecialManageInfo(@RequestBody EsdSpecialManageInfo esdSpecialManageInfo){
		ResultBean resultBean = new ResultBean();
		esdSpecialManageInfoService.handleEsdSpecialManageInfo(esdSpecialManageInfo);
		return  resultBean;
	}

	@RequestMapping(value = "findEsdSpecialManageDetail",method = RequestMethod.GET)
	@ApiOperation(value = "获取ESD来料检验单详情信息",notes = "获取ESD来料检验单详情信息")
	public ResultBean findEsdSpecialManageDetail(@RequestParam("id") String id){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialManageInfoService.findEsdSpecialManageDetail(id));
		return  resultBean;
	}
}