package cn.jwis.qualityworkflow.modules.apqp.controller;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenance;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelCascadeVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelListVo;
import cn.jwis.qualityworkflow.modules.apqp.service.ModelMaintenanceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-22 17:33
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/modelMaintenance")
@Api(description = "APQP机型维护Controller")
public class ModelMaintenanceController {

	@Autowired
	ModelMaintenanceService service;

	/**
	 * @description: 获取机型维护列表信息
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:35
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取机型维护列表信息", notes = "获取机型维护列表信息")
	public ResultBean getInfoList(@RequestBody QueryModelListVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<ApqpModelMaintenance> page = service.getInfoList(bean);
		resultBean.setData(page);
		return resultBean;
	}

	/**
	 * @description: 新增 机型维护
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:37
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "新增 机型维护", notes = "新增 机型维护")
	public ResultBean insert(@RequestBody ApqpModelMaintenance bean) throws Exception {
		service.insert(bean);
		return ResultBean.success();
	}

	/**
	 * @description: 更新 机型维护
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:37
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "更新 机型维护", notes = "更新 机型维护")
	public ResultBean update(@RequestBody ApqpModelMaintenance bean) throws Exception {
		service.update(bean);
		return ResultBean.success();
	}

	/**
	 * @description: 删除机型维护信息
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:37
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "删除机型维护信息", notes = "删除机型维护信息")
	public ResultBean update(@RequestParam(value = "id" ) String id) throws Exception {
		service.delete(id);
		return ResultBean.success();
	}

	/**
	 * @description: 获取下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 10:58
	 * @param parameter:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@GetMapping(value = "getPullDownValue")
	@ApiOperation(value = "获取下拉值", notes = "获取下拉值")
	public ResultBean getPullDownValue(@RequestParam(value = "parameter" ) String parameter) throws Exception {
		List<Object> pullDownValue = service.getPullDownValue(parameter);
		return ResultBean.success(pullDownValue);
	}

	/**
	 * @description: 下载机型维护模板
	 * @author: xujinbiao
	 * @date: 2020/5/19 14:07
	 * @param response:
	 * @param request:
	 * @return: void
	 **/
	@GetMapping(value = "exportTemplate")
	@ApiOperation(value = "下载机型维护模板", notes = "下载机型维护模板")
	public void exportTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		service.exportTemplate(response, request);
	}
	/**
	 * @description: 导入机型维护信息，并校验机型唯一性
	 * @author: xujinbiao
	 * @date: 2020/5/25 11:29
	 * @param file:
	 * @param request:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "导入机型维护信息，并校验机型唯一性", notes = "导入机型维护信息，并校验机型唯一性")
	public ResultBean importWithCheck(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
		service.importWithCheck(file, request);
		return ResultBean.success();
	}

	/**
	 * @description: //TODO
	 * @author: xujinbiao
	 * @date: 2020/5/25 15:27
	 * @param model:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@GetMapping(value = "/getPreviousProduct")
	@ApiOperation(response = ResultBean.class, value = "获取上一代机型", notes = "获取上一代机型")
	public ResultBean getPreviousProduct(@RequestParam(value = "model") String model) throws Exception {
		ResultBean resultBean = new ResultBean();
		String data = service.getPreviousProduct(model);
		resultBean.setData(data);
		return resultBean;
	}

	/**
	 * @description: 级联查询下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/5/25 15:27
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getCascadeValue")
	@ApiOperation(response = ResultBean.class, value = "级联查询下拉值接口", notes = "获取上一代机型")
	public ResultBean getCascadeValue(@RequestBody @Valid QueryModelCascadeVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		List<String> data = service.getCascadeValue(bean);
		resultBean.setData(data);
		return resultBean;
	}
}
