package cn.jwis.qualityworkflow.modules.esd.controller;

import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

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

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAudiMainSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListSecondary;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSave;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSearch;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditMainMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdAuditMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/1 16:33
 */
@RestController
@RequestMapping(value = "/esdAuditMain")
@Api(description = "ESD稽核模板管理")
public class EsdAuditMainController {

	@Autowired
	EsdAuditMainService esdAuditMainService;

	@Autowired
	EsdAuditMainMapper esdAuditMainMapper;

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核模板管理查询下拉值获取
	 * @Date  2020/7/1  16:39
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "ESD稽核模板管理查询下拉值获取",notes = "ESD稽核模板管理查询下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getDropdownValue(parameter));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核模板管理查询接口
	 * @Date  2020/7/1  16:46
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getEsdAuditMainList",method = RequestMethod.POST)
	@ApiOperation(value = "ESD稽核模板管理查询接口",notes = "ESD稽核模板管理查询接口")
	public ResultBean getEsdAuditMainList(HttpServletRequest request, @RequestBody EsdAudiMainSearch esdAudiMainSearch)throws Exception{
		Integer page = esdAudiMainSearch.getPage();
		Integer size = esdAudiMainSearch.getSize();
		List<EsdAuditMain> list = esdAuditMainService.getEsdAudiMainList(esdAudiMainSearch);
		Map<String,String> title = esdAuditMainService.getTitle(request);
		if (CollectionUtils.isNotEmpty(list)){
			return ResultBean.pagination(esdAuditMainMapper.getEsdAudiMainCount(esdAudiMainSearch), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, list,title);
		}
	}
    /**
	 * @Author yuyangyang
	 * @Description ESD稽核模板下载接口
	 * @Date  2020/7/1  17:43
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/exportEsdAudit", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "ESD稽核模板下载接口", notes = "ESD稽核模板下载接口")
	public void exportEsdAdministration( HttpServletResponse response) {
		esdAuditMainService.exportEsdAdministration(response);
	}

    /**
     * @Author yuyangyang
     * @Description 保存稽核模板的数据
     * @Date  2020/7/1  18:24
     * @Param
     * @return
     */
	@RequestMapping(value = "/saveEsdAudit", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "保存稽核模板的数据", notes = "保存稽核模板的数据")
	public ResultBean saveEsdAudit(@RequestBody EsdAuditSave esdAuditSave) {
		esdAuditMainService.saveEsdAudit(esdAuditSave);
		return ResultBean.success();
	}
	/**
	 * @Author yuyangyang
	 * @Description //TODO
	 * @Date  2020/7/14  15:27
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "removeEsdAudit",method = RequestMethod.GET)
	@ApiOperation(value = "删除ESD稽核模板",notes = "删除ESD稽核模板")
	public ResultBean removeEsdAudit(@RequestParam("id") String id){
		esdAuditMainService.removeEsdAudit(id);
		return ResultBean.success();
	}
    /**
     * @Author yuyangyang
     * @Description 获取稽核模板的详情数据
     * @Date  2020/7/2  11:28
     * @Param
     * @return
     */
	@RequestMapping(value = "getEsdAuditDetails",method = RequestMethod.GET)
	@ApiModelProperty(value = "获取稽核模板的详情数据",name = "获取稽核模板的详情数据")
	public ResultBean getEsdAuditDetails(@RequestParam("id") String id) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getEsdAuditDetails(id));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单管理查询页面下拉值接口
	 * @Date  2020/7/2  11:28
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getDownValueList",method = RequestMethod.GET)
	@ApiOperation(value = "ESD稽核单管理查询页面下拉值接口",notes = "ESD稽核单管理查询页面下拉值接口")
	public ResultBean getDownValueList(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getDownValueList(parameter));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单管理查询接口
	 * @Date  2020/7/2  14:14
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getEsdAuditList",method = RequestMethod.POST)
	@ApiOperation(value = "ESD稽核单据管理查询接口",notes = "ESD稽核单据管理查询接口")
	public ResultBean getEsdAuditList(HttpServletRequest request, @RequestBody EsdAuditSearch esdAuditSearch) throws Exception {
		Integer page = esdAuditSearch.getPage();
		Integer size = esdAuditSearch.getSize();
		List<EsdAuditListMain> list = esdAuditMainService.getEsdAuditList(esdAuditSearch);
		Map<String, String> title = esdAuditMainService.getAuditTitle(request);
		if (CollectionUtils.isNotEmpty(list)) {
			return ResultBean.pagination(esdAuditMainMapper.getEsdAuditCount(esdAuditSearch), page, size, list, title);
		} else {
			return ResultBean.pagination(0L, page, size, list, title);
		}
	}
    /**
     * @Author yuyangyang
     * @Description ESD稽核单管理导出接口
     * @Date  2020/7/2  15:34
     * @Param
     * @return
     */
	@RequestMapping(value = "exportEsdAuditList",method = RequestMethod.POST)
	@ApiOperation(value = "导出ESD稽核单据管理接口",notes = "导出ESD稽核单据管理接口")
	public ResultBean exportEsdAuditList(HttpServletResponse response,HttpServletRequest request, @RequestBody EsdAuditSearch esdAuditSearch) throws Exception {
		esdAuditMainService.exportEsdAuditList(response,request,esdAuditSearch);
		return  ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核页面文件名称的下拉值
	 * @Date  2020/7/2  15:37
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getDocumentNameList",method = RequestMethod.GET)
	@ApiOperation(value = "新增ESD稽核页面文件名称的下拉值",notes = "新增ESD稽核页面文件名称的下拉值")
	public ResultBean getDocumentNameList(@RequestParam("parameter") String parameter) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getDocumentNameList(parameter));
		return  resultBean;
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核页面文件名称带出文件编号
	 * @Date  2020/7/2  15:37
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "getDocumentNumberListByName",method = RequestMethod.GET)
	@ApiOperation(value = "新增ESD稽核页面文件名称带出文件编号",notes = "新增ESD稽核页面文件名称带出文件编号")
	public ResultBean getDocumentNumberListByName(@RequestParam("documentName")String documentName) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getDocumentNumberListByName(documentName));
		return  resultBean;
	}
	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核页面通过文件编号带出详情信息
	 * @Date  2020/7/2  15:40
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getDetailsByNumber",method = RequestMethod.GET)
	@ApiOperation(value = "新增ESD稽核页面通过文件编号带出详情信息",notes = "新增ESD稽核页面通过文件编号带出详情信息")
	public ResultBean getDetailsByNumber(@RequestParam("documentNumber")String documentNumber) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getDetailsByNumber(documentNumber));
		return  resultBean;
	}
    /**
     * @Author yuyangyang
     * @Description 新增ESD稽核单据
     * @Date  2020/7/2  16:28
     * @Param
     * @return
     */
	@RequestMapping(value = "saveEsdAuditList",method = RequestMethod.POST)
	@ApiOperation(value = "新增ESD稽核单据",notes = "新增ESD稽核单据")
	public ResultBean saveEsdAuditList(@RequestBody JSONObject jsonObject) {
		esdAuditMainService.saveEsdAuditList(jsonObject);
		return  ResultBean.success();
	}
    /**
     * @Author yuyangyang
     * @Description 删除ESD稽核单的信息
     * @Date  2020/7/14  15:38
     * @Param
     * @return
     */
	@RequestMapping(value = "removeEsdAuditList",method = RequestMethod.GET)
	@ApiOperation(value = "删除ESD稽核单据",notes = "删除ESD稽核单据")
	public ResultBean removeEsdAuditList(@RequestParam("id") String id) {
		esdAuditMainService.removeEsdAuditList(id);
		return  ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单详情接口
	 * @Date  2020/7/2  18:00
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getEsdAuditListDetails",method = RequestMethod.GET)
	@ApiOperation(value = "ESD稽核单详情接口",notes = "ESD稽核单详情接口")
	public ResultBean getEsdAuditListDetails(@RequestParam("id") String id) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getEsdAuditListDetails(id));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单修改接口
	 * @Date  2020/7/2  18:12
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "updateEsdAuditListSecondary",method = RequestMethod.POST)
	@ApiOperation(value = "ESD稽核单修改接口",notes = "ESD稽核单修改接口")
	public ResultBean updateEsdAuditListSecondary(@RequestBody EsdAuditListSecondary esdAuditListSecondary) {
		esdAuditMainService.updateEsdAuditListSecondary(esdAuditListSecondary);
		return ResultBean.success();
	}
    /**
     * @Author yuyangyang
     * @Description 获取通过ID获取副表的详细数据
     * @Date  2020/7/15  14:56
     * @Param
     * @return
     */
	@RequestMapping(value = "getEsdAuditListSecondary",method = RequestMethod.GET)
	@ApiOperation(value = "获取通过ID获取副表的详细数据",notes = "获取通过ID获取副表的详细数据")
	public ResultBean getEsdAuditListSecondary(@RequestParam("id") String id) {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditMainService.getEsdAuditListSecondary(id));
		return resultBean;
	}

}