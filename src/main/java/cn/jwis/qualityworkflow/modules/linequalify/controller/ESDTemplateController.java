package cn.jwis.qualityworkflow.modules.linequalify.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.linequalify.bean.ESDTemplateInfo;
import cn.jwis.qualityworkflow.modules.linequalify.service.ESDTemplateService;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:54
 */
@RestController
@RequestMapping(value = "/esdTemplate")
public class ESDTemplateController extends BaseClass {

	@Autowired
	ESDTemplateService esdTemplateService;

	@RequestMapping(value = "/saveEsdTemplates", method = RequestMethod.POST)
	@ApiOperation(value = "创建ESD线体认证模版", notes = "创建ESD线体认证模版")
	public ResultBean saveEsdTemplates(@RequestBody List<ESDTemplateInfo> beans) throws Exception {
		esdTemplateService.saveEsdTemplates(beans);
		return ResultBean.success();
	}

	@RequestMapping(value = "/getEsdTemplateList", method = RequestMethod.POST)
	@ApiOperation(value = "获取ESD线体认证模版", notes = "获取ESD线体认证模版")
	public ResultBean getEsdTemplateList() throws Exception {
		List<ESDTemplateInfo> resultList = esdTemplateService.getEsdTemplateList();
		return ResultBean.success(resultList);
	}

	// 导出模板
	@RequestMapping(value = "/exportTemplate", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出ESD线体认证导入模版", notes = "导出ESD线体认证导入模版")
	public void commonExportTemplate(HttpServletResponse response, HttpServletRequest request,
			@RequestBody JSONObject jsonObject) {
		esdTemplateService.exportTemplate(jsonObject, response, request);
	}

	@RequestMapping(value = "/exportEsdTemplateList", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出ESD线体认证模版清单", notes = "导出ESD线体认证模版清单")
	public void exportEsdTemplateList(HttpServletResponse response, HttpServletRequest request,
			@RequestBody JSONObject jsonObject) {
		esdTemplateService.exportEsdTemplateList(jsonObject, response, request);
	}

}