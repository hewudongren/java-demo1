package cn.jwis.qualityworkflow.modules.factoryaudit.controller;

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
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionSearch;
import cn.jwis.qualityworkflow.modules.factoryaudit.service.AuditQuestionService;
//import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author longjun
 *
 */
@RestController
@RequestMapping(value = "/auditquestion")
@Api(description = "审核问题接口")
public class AuditQuestionController extends BaseClass {

	@Autowired
	AuditQuestionService auditQuestionService;
	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@RequestMapping(value = "/saveAuditQuestionInfo", method = RequestMethod.POST)
	@ApiOperation(value = "保存审核问题单据", notes = "保存审核问题单据")
	public ResultBean saveAuditQuestionInfo(@RequestBody AuditQuestionInfo bean) throws Exception {
		auditQuestionService.saveAuditQuestionInfo(bean);
		return ResultBean.success();
	}

	@RequestMapping(value = "/saveAuditQuestions", method = RequestMethod.POST)
	@ApiOperation(value = "批量保存审核问题单据", notes = "批量保存审核问题单据")
	public ResultBean saveAuditQuestions(@RequestBody List<AuditQuestionInfo> beans) throws Exception {
		for (AuditQuestionInfo auditQuestionInfo : beans) {
			auditQuestionService.saveAuditQuestionInfo(auditQuestionInfo);
		}
		return ResultBean.success();
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean) throws Exception {
		auditQuestionService.confirm(bean);
		return ResultBean.success();
	}

	@RequestMapping(value = "getDropdownValue", method = RequestMethod.GET)
	@ApiOperation(value = "下拉值获取", notes = "下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter") String parameter) {
		return ResultBean.success(auditQuestionService.getDropdownValue(parameter));
	}

	@RequestMapping(value = "getAuditQuestionInfoList", method = RequestMethod.POST)
	@ApiOperation(value = "获取质量问题清单", notes = "获取质量问题清单")
	public ResultBean getAuditQuestionInfoList(@RequestBody AuditQuestionSearch auditQuestionSearch) {
		int page = auditQuestionSearch.getPage();
		int size = auditQuestionSearch.getSize();
		List<AuditQuestionInfo> list = auditQuestionService.getAuditQuestionInfoList(auditQuestionSearch);
		if (isNotNull(list)) {
			return ResultBean.pagination(auditQuestionService.getAuditQuestionInfoCount(auditQuestionSearch), page,
					size, list, null);
		} else {
			return new ResultBean();
		}
	}

	// 导出模板
	@RequestMapping(value = "/exportTemplate", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出审核问题导入模版", notes = "导出审核问题导入模版")
	public void commonExportTemplate(HttpServletResponse response, HttpServletRequest request,
			@RequestBody JSONObject jsonObject) {
		auditQuestionService.exportTemplate(jsonObject, response, request);
	}

	@RequestMapping(value = "/exportAuditQuestionList", method = RequestMethod.POST)
	@ApiOperation(value = "导出审核问题清单", notes = "导出审核问题清单")
	public ResultBean exportAuditQuestionList(HttpServletResponse response, HttpServletRequest request,
			@RequestBody AuditQuestionSearch auditQuestionSearch) throws Exception {
		auditQuestionService.exportAuditQuestionList(response, request, auditQuestionSearch);
		return ResultBean.success();
	}

	@RequestMapping(value = "/getAuditQuestionDetailednessInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取审核问题详情信息", notes = "获取审核问题详情信息")
	public ResultBean getAuditQuestionDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = auditQuestionService.getAuditQuestionDetailednessInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "/getAuditQuestionTrends", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取及时关闭率看板趋势图", notes = "获取及时关闭率看板趋势图")
	public ResultBean getAuditQuestionTrends(@RequestBody AuditQuestionSearch auditQuestionSearch) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = auditQuestionService.getAuditQuestionTrends(auditQuestionSearch);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "/getKeywordsPlato", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取关键字柏拉图", notes = "获取关键字柏拉图")
	public ResultBean getKeywordsPlato(@RequestBody AuditQuestionSearch auditQuestionSearch) throws Exception {
		ResultBean resultBean = new ResultBean();
		JSONObject resultMap = auditQuestionService.getKeywordsPlato(auditQuestionSearch);
		resultBean.setData(resultMap);
		return resultBean;
	}
}