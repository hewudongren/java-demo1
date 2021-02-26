package cn.jwis.qualityworkflow.modules.esd.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.service.EsdAdministrationService;
import io.swagger.annotations.ApiOperation;

/**
 * @Description ESD检验单管理
 * @Author yuyangyang
 * @Date 2020/5/15 9:45
 */
@RestController
@RequestMapping(value = "/esdAdministration")
public class EsdAdministrationController {

	@Autowired
	EsdAdministrationService esdAdministrationService;

	/**
	 * @Author yuyangyang
	 * @Description ESD检验单管理样品名称下拉值接口
	 * @Date  2020/5/15  13:53
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "ESD检验单管理样品名称下拉值接口",notes = "ESD检验单管理样品名称下拉值接口")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAdministrationService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "findEsdAdministration",method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD检验单信息",notes = "查询ESD检验单信息")
	public ResultBean findEsdAdministration(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean = esdAdministrationService.findEsdAdministration(jsonObject);
		return  resultBean;
	}

	@RequestMapping(value = "/exportEsdAdministration", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出ESD检验单信息", notes = "导出ESD检验单信息")
	public void exportEsdAdministration(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
		esdAdministrationService.exportEsdAdministration(jsonObject,response);
	}

	@RequestMapping(value = "findTableNameBySampleName",method = RequestMethod.GET)
	@ApiOperation(value = "通过选择的样品名获取表头的信息(带出范围信息)",notes = "通过选择的样品名获取表头的信息(带出范围信息)")
	public ResultBean findTableNameBySampleName(@RequestParam("sampleName") String sampleName){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAdministrationService.findTableNameBySampleName(sampleName));
		return  resultBean;
	}

	@RequestMapping(value = "saveEsdAdministrationInfo",method = RequestMethod.POST)
	@ApiOperation(value = "保存ESD检验单信息",notes = "保存ESD检验单信息")
	public ResultBean saveEsdAdministrationInfo(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		esdAdministrationService.saveEsdAdministrationInfo(jsonObject);
		return  resultBean;
	}

	@RequestMapping(value = "isAdd",method = RequestMethod.GET)
	@ApiOperation(value = "提示是否继续添加",notes = "提示是否继续添加")
	public ResultBean isAdd(@RequestParam("sampleName")String sampleName ,@RequestParam("detectionMonth") String detectionDate ){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAdministrationService.isAdd(sampleName,detectionDate));
		return  resultBean;
	}

	@RequestMapping(value = "getEsdInfoById",method = RequestMethod.GET)
	@ApiOperation(value = "通过ID进行追加页面",notes = "通过ID进入追加页面")
	public ResultBean getEsdInfoById(@RequestParam("id") String id){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAdministrationService.getEsdInfoById(id));
		return resultBean;
	}

	@RequestMapping(value = "getEsdDetailInfoByEsdId",method = RequestMethod.POST)
	@ApiOperation(value = "通过ID进入详情页面",notes = "通过ID进入详情页面")
	public ResultBean getEsdDetailInfoByEsdId(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean = esdAdministrationService.getEsdDetailInfoByEsdId(jsonObject);
		return resultBean;
	}

	@RequestMapping(value = "esdRecheck",method = RequestMethod.POST)
	@ApiOperation(value = "esd复检",notes = "esd复检")
	public ResultBean esdRecheck(@RequestBody JSONObject jsonObject){
		esdAdministrationService.esdRecheck(jsonObject);
		return ResultBean.success();
	}
	@RequestMapping(value = "getEsdDetailInfoById",method = RequestMethod.GET)
	@ApiOperation(value = "通过ID进入ESD复检页面",notes = "通过ID进入ESD复检页面")
	public ResultBean getEsdDetailInfoById(@RequestParam("id") String id){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAdministrationService.getEsdDetailInfoById(id));
		return resultBean;
	}

	@RequestMapping(value = "feMaintain",method = RequestMethod.POST)
	@ApiOperation(value = "FE维修接口",notes = "FE维修接口")
	public ResultBean feMaintain(@RequestBody JSONObject jsonObject){
		esdAdministrationService.feMaintain(jsonObject);
		return ResultBean.success();
	}
}