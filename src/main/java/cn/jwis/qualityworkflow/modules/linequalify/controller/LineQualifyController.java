package cn.jwis.qualityworkflow.modules.linequalify.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifyInfo;
import cn.jwis.qualityworkflow.modules.linequalify.bean.LineQualifySearch;
import cn.jwis.qualityworkflow.modules.linequalify.service.LineQualifyService;
//import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:54
 */
@RestController
@RequestMapping(value = "/lineQualify")
@Api(description = "Line Qualify线体认证")
public class LineQualifyController extends BaseClass {

	@Autowired
	LineQualifyService lineQualifyService;
	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@RequestMapping(value = "/saveLineQualifyInfo", method = RequestMethod.POST)
	@ApiOperation(value = "保存Line Qualify单据信息", notes = "保存Line Qualify单据信息")
	public ResultBean saveLineQualifyInfo(@RequestBody LineQualifyInfo bean) throws Exception {
		lineQualifyService.saveLineQualifyInfo(bean);
		return ResultBean.success();
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean) throws Exception {
		lineQualifyService.confirm(bean);
		return ResultBean.success();
	}

	@RequestMapping(value = "getDropdownValue", method = RequestMethod.GET)
	@ApiOperation(value = "下拉值获取", notes = "下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter") String parameter) {
		return ResultBean.success(lineQualifyService.getDropdownValue(parameter));
	}

	@RequestMapping(value = "getLineQualifyInfoList", method = RequestMethod.POST)
	@ApiOperation(value = "获取Line Qualify列表", notes = "获取Line Qualify列表")
	public ResultBean getLineQualifyInfoList(HttpServletRequest request,
			@RequestBody LineQualifySearch lineQualifySearch) {
		int page = lineQualifySearch.getPage();
		int size = lineQualifySearch.getSize();
		List<LineQualifyInfo> list = lineQualifyService.getLineQualifyInfoList(lineQualifySearch);
		if (isNotNull(list)) {
			return ResultBean.pagination(lineQualifyService.getLineQualifyInfoCount(lineQualifySearch), page, size,
					list, null);
		} else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "exportLineQualifyInfoList", method = RequestMethod.POST)
	@ApiOperation(value = "导出Line Qualify列表", notes = "导出Line Qualify列表")
	public void exportLineQualifyInfoList(HttpServletResponse response, HttpServletRequest request,
			@RequestBody LineQualifySearch lineQualifySearch) {
		lineQualifyService.exportLineQualifyInfoList(response, request, lineQualifySearch);
	}

	@RequestMapping(value = "/getLineQualifyDetailednessInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取Line Qualify详情信息", notes = "获取Line Qualify详情信息")
	public ResultBean getLineQualifyDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = lineQualifyService.getLineQualifyDetailednessInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "getCertificationList", method = RequestMethod.POST)
	@ApiOperation(value = "获取线体认证查询信息", notes = "获取线体认证查询信息")
	public ResultBean getCertificationList(HttpServletRequest request,
			@RequestBody LineQualifySearch lineQualifySearch) {
		int page = lineQualifySearch.getPage();
		int size = lineQualifySearch.getSize();
		List<LineQualifyInfo> list = lineQualifyService.getCertificationList(lineQualifySearch);
		if (isNotNull(list)) {
			return ResultBean.pagination(lineQualifyService.getCertificationCount(lineQualifySearch), page, size, list,
					null);
		} else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "exportCertificationList", method = RequestMethod.POST)
	@ApiOperation(value = "导出线体认证查询", notes = "导出线体认证查询")
	public void exportCertificationList(HttpServletResponse response, HttpServletRequest request,
			@RequestBody LineQualifySearch lineQualifySearch) {
		lineQualifyService.exportCertificationList(response, request, lineQualifySearch);
	}

	/**
	 * @description: 根据id获取流程进展
	 * @date: 2020/4/30 11:27
	 * @param id:
	 **/
	@GetMapping(value = "getProgress")
	@ApiOperation(value = "根据id获取LineQualify流程进展", notes = "根据id获取LineQualify流程进展")
	public ResultBean getProgress(@RequestParam(value = "id") String id) throws Exception {
		JSONObject progress = lineQualifyService.getProgress(id);
		return ResultBean.success(progress);
	}

}