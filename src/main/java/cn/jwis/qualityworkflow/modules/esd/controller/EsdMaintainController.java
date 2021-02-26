package cn.jwis.qualityworkflow.modules.esd.controller;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSamplingLevelInfo;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdMaintainMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdMaintainService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.util.DateUtil.plusOneDay;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/9 11:32
 */
@RestController
@RequestMapping(value = "/esdMaintain")
@Api(description = "ESD维护页面接口")
public class EsdMaintainController extends BaseClass {
	@Autowired
	EsdMaintainService esdMaintainService;
	@Autowired
	EsdMaintainMapper esdMaintainMapper;

	@RequestMapping(value = "/savaEsdInStanInfo", method = RequestMethod.POST)
	@ApiOperation(value = "保存ESD检验标准维护信息", notes = "保存ESD检验标准维护信息")
	public ResultBean savaEsdInStanInfo(@RequestBody EsdInStanInfo esdInStanInfo)  {
		esdMaintainService.savaEsdInStanInfo(esdInStanInfo);
		return ResultBean.success();
	}

	@RequestMapping(value = "/updateEsdInStanInfo", method = RequestMethod.POST)
	@ApiOperation(value = "修改ESD检验标准维护信息", notes = "修改ESD检验标准维护信息")
	public ResultBean updateEsdInStanInfo(@RequestBody EsdInStanInfo esdInStanInfo)  {
		esdMaintainService.updateEsdInStanInfo(esdInStanInfo);
		return ResultBean.success();
	}

	@RequestMapping(value = "/removeEsdInStanInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除ESD检验标准维护信息", notes = "删除ESD检验标准维护信息")
	public ResultBean removeEsdInStanInfo(@RequestBody JSONObject jsonObject)  {
		esdMaintainService.removeEsdInStanInfo(jsonObject.getString("id"));
		return ResultBean.success();
	}

	@RequestMapping(value = "/findEsdInStanInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD检验标准维护信息", notes = "查询ESD检验标准维护信息")
	public ResultBean findEsdInStanInfo(HttpServletRequest request,@RequestBody EsdInStanSearch esdInStanSearch)  {
		int page = esdInStanSearch.getPage();
		int size = esdInStanSearch.getSize();
		esdInStanSearch.setEndDate(plusOneDay(esdInStanSearch.getEndDate()));
		List<EsdInStanInfo> esdInStanInfo = esdMaintainService.findEsdInStanInfo(esdInStanSearch);
		String category = esdInStanSearch.getCategory();
		Map<String,String> title = esdMaintainService.getTitle(request,category);
		if (isNotNull(esdInStanInfo)){
			return ResultBean.pagination(esdMaintainMapper.findEsdInStanInfoCount(esdInStanSearch), page, size, esdInStanInfo,title);
		}else {
			return ResultBean.pagination(0L, page, size, esdInStanInfo,title);
		}
	}

	@RequestMapping(value = "/exportEsdInStanInfoTemplate", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "导出ESD检验标准维护信息模板", notes = "导出ESD检验标准维护信息模板")
	public void exportEsdInStanInfoTemplate(HttpServletResponse response, @RequestParam("category") String category,
											@RequestParam("fileName") String fileName) {
		esdMaintainService.exportEsdInStanInfoTemplate(response,category,fileName);
	}

	@RequestMapping(value = "/exportEsdInStanInfo", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出ESD检验标准维护信息", notes = "导出ESD检验标准维护信息")
	public void exportEsdInStanInfo(@RequestBody EsdInStanSearch esdInStanSearch,HttpServletResponse response) {
		esdInStanSearch.setEndDate(plusOneDay(esdInStanSearch.getEndDate()));
		esdMaintainService.exportEsdInStanInfo(esdInStanSearch,response);
	}

	@RequestMapping(value = "/importEsdInStanInfoWithId", method = RequestMethod.POST)
	@ApiOperation(value = "导入ESD检验标准维护信息", notes = "导入ESD检验标准维护信息")
	public ResultBean importEsdInStanInfoWithId(MultipartFile file,String category,
												HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		Boolean flag = esdMaintainService.importEsdInStanInfoWithId(file,request,category);
		if (flag) {
			resultBean.setMessage("导入成功");
		} else {
			resultBean.setCode(-1);
			resultBean.setMessage("导入失败");
		}
		return resultBean;
	}

	@RequestMapping(value = "/getSampleNameList", method = RequestMethod.GET)
	@ApiOperation(value = "获取样品名的下拉值(检验标准)", notes = "获取样品名的下拉值(检验标准)")
	public ResultBean getSampleNameList(@RequestParam("category")String category) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdMaintainMapper.getSampleNameList(category));
		return resultBean;
	}

	@RequestMapping(value = "/getDetectionItemList", method = RequestMethod.GET)
	@ApiOperation(value = "获取检验项目的下拉值", notes = "获取检验项目的下拉值")
	public ResultBean getDetectionItemList(@RequestParam("category")String category) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdMaintainMapper.getDetectionItemList(category));
		return resultBean;
	}

	@RequestMapping(value = "/savaEsdSamplingLevelInfo", method = RequestMethod.POST)
	@ApiOperation(value = "保存ESD抽样水准维护信息", notes = "保存ESD抽样水准维护信息")
	public ResultBean savaEsdSamplingLevelInfo(@RequestBody EsdSamplingLevelInfo esdSamplingLevelInfo)  {
		esdMaintainService.savaEsdSamplingLevelInfo(esdSamplingLevelInfo);
		return ResultBean.success();
	}

	@RequestMapping(value = "/updateEsdSamplingLevelInfo", method = RequestMethod.POST)
	@ApiOperation(value = "修改ESD抽样水准维护信息", notes = "修改ESD抽样水准维护信息")
	public ResultBean updateEsdSamplingLevelInfo(@RequestBody EsdSamplingLevelInfo esdSamplingLevelInfo)  {
		esdMaintainService.updateEsdSamplingLevelInfo(esdSamplingLevelInfo);
		return ResultBean.success();
	}

	@RequestMapping(value = "/removeEsdSamplingLevelInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除ESD抽样水准维护信息", notes = "删除ESD抽样水准维护信息")
	public ResultBean removeEsdSamplingLevelInfo(@RequestBody JSONObject jsonObject)  {
		esdMaintainService.removeEsdSamplingLevelInfo(jsonObject.getString("id"));
		return ResultBean.success();
	}

	@RequestMapping(value = "/findEsdSamplingLevelInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD抽样水准维护信息", notes = "查询ESD抽样水准维护信息")
	public ResultBean findEsdSamplingLevelInfo(HttpServletRequest request,@RequestBody EsdInStanSearch esdInStanSearch)  {
		int page = esdInStanSearch.getPage();
		int size = esdInStanSearch.getSize();
		esdInStanSearch.setEndDate(plusOneDay(esdInStanSearch.getEndDate()));
		List<EsdSamplingLevelInfo> esdSamplingLevelInfoList = esdMaintainService.findEsdSamplingLevelInfo(esdInStanSearch);
		String category = esdInStanSearch.getCategory();
		Map<String,String> title = esdMaintainService.getTitle2(request,category);
		if (isNotNull(esdSamplingLevelInfoList)){
			return ResultBean.pagination(esdMaintainMapper.findEsdSamplingLevelInfoCount(esdInStanSearch), page, size, esdSamplingLevelInfoList,title);
		}else {
			return ResultBean.pagination(0L, page, size, esdSamplingLevelInfoList,title);
		}
	}

	@RequestMapping(value = "/exportEsdSamplingLevelInfoTemplate", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "导出ESD检验抽样水准信息模板", notes = "导出ESD抽样水准维护信息模板")
	public void exportEsdSamplingLevelInfoTemplate(HttpServletResponse response,@RequestParam("category") String category,
												   @RequestParam("fileName") String fileName) {
		esdMaintainService.exportEsdSamplingLevelInfoTemplate(response,category,fileName);
	}

	@RequestMapping(value = "/exportEsdSamplingLevelInfo", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出ESD抽样水准维护信息", notes = "导出ESD抽样水准维护信息")
	public void exportEsdSamplingLevelInfo(@RequestBody EsdInStanSearch esdInStanSearch,HttpServletResponse response) {
		esdInStanSearch.setEndDate(plusOneDay(esdInStanSearch.getEndDate()));
		esdMaintainService.exportEsdSamplingLevelInfo(esdInStanSearch,response);
	}

	@RequestMapping(value = "/importEsdSamplingLevelInfoWithId", method = RequestMethod.POST)
	@ApiOperation(value = "导入ESD抽样水准维护信息", notes = "导入ESD抽样水准维护信息")
	public ResultBean importEsdSamplingLevelInfoWithId(MultipartFile file,String category,
													   HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		Boolean flag = esdMaintainService.importEsdSamplingLevelInfoWithId(file,request,category);
		if (flag) {
			resultBean.setMessage("导入成功");
		} else {
			resultBean.setCode(-1);
			resultBean.setMessage("导入失败");
		}
		return resultBean;
	}

	@RequestMapping(value = "/getSampleNameListLevel", method = RequestMethod.GET)
	@ApiOperation(value = "获取样品名的下拉值(抽样水准)", notes = "获取样品名的下拉值(抽样水准)")
	public ResultBean getSampleNameListLevel(@RequestParam("category")String category) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdMaintainMapper.getSampleNameListLevel(category));
		return resultBean;
	}

}