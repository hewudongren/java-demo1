package cn.jwis.qualityworkflow.modules.factoryaudit.controller;

import java.util.List;

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
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventSearch;
import cn.jwis.qualityworkflow.modules.factoryaudit.service.AuditEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 审核事件接口
 * 
 * @author longjun
 */
@RestController
@RequestMapping(value = "/auditevent")
@Api(description = "审核事件接口")
public class AuditEventController extends BaseClass {
	@Autowired
	AuditEventService auditEventService;

	@RequestMapping(value = "/saveAuditEvents", method = RequestMethod.POST)
	@ApiOperation(value = "保存审核事件", notes = "保存审核事件")
	public ResultBean saveAuditEvents(@RequestBody AuditEventInfo auditEventInfo) throws Exception {
		auditEventService.saveAuditEvents(auditEventInfo);
		return ResultBean.success();
	}

	@RequestMapping(value = "/getAuditEventList", method = RequestMethod.POST)
	@ApiOperation(value = "获取审核事件清单", notes = "获取审核事件清单")
	public ResultBean getAuditEventList(@RequestBody AuditEventSearch auditEventSearch) throws Exception {
		int page = auditEventSearch.getPage();
		int size = auditEventSearch.getSize();
		List<AuditEventInfo> list = auditEventService.getAuditEventList(auditEventSearch);
		if (isNotNull(list)) {
			return ResultBean.pagination(auditEventService.getAuditEventCount(auditEventSearch), page, size, list,
					null);
		} else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "/getAuditCalendarInfo", method = RequestMethod.POST)
	@ApiOperation(value = "获取审核日历信息", notes = "获取审核日历信息")
	public ResultBean getAuditCalendarInfo(@RequestBody AuditEventSearch auditEventSearch) throws Exception {
		List<AuditEventInfo> auditCalendarInfo = auditEventService.getAuditCalendarInfo(auditEventSearch);
		return ResultBean.success(auditCalendarInfo);
	}

	@RequestMapping(value = "/getYearAuditCalendarInfo", method = RequestMethod.POST)
	@ApiOperation(value = "获取年度审核日历信息", notes = "获取年度审核日历信息")
	public ResultBean getYearAuditCalendarInfo(@RequestBody AuditEventSearch auditEventSearch) throws Exception {
		List<JSONObject> resultList = auditEventService.getYearAuditCalendarInfo(auditEventSearch);
		return ResultBean.success(resultList);
	}

	@RequestMapping(value = "/getAuditEventById", method = RequestMethod.GET)
	@ApiOperation(value = "通过ID获取审核事件", notes = "通过ID获取审核事件")
	public ResultBean getAuditEventById(@RequestParam("id") String id) throws Exception {
		AuditEventInfo auditEventInfo = auditEventService.getAuditEventById(id);
		return ResultBean.success(auditEventInfo);
	}

	@RequestMapping(value = "/getAuditEventByTopics", method = RequestMethod.GET)
	@ApiOperation(value = "通过审核主题获取审核事件", notes = "通过审核主题获取审核事件")
	public ResultBean getAuditEventByTopics(@RequestParam("topics") String topics) throws Exception {
		List<AuditEventInfo> auditEventByTopics = auditEventService.getAuditEventByTopics(topics);
		return ResultBean.success(auditEventByTopics);
	}

	@RequestMapping(value = "/getAuditEventTopics", method = RequestMethod.GET)
	@ApiOperation(value = "获取审核主题", notes = "获取审核主题")
	public ResultBean getAuditEventTopics() throws Exception {
		List<String> topicsList = auditEventService.getAuditEventTopics();
		return ResultBean.success(topicsList);
	}

	@RequestMapping(value = "/exportAuditEventList", method = RequestMethod.POST)
	@ApiOperation(value = "导出审核事件清单", notes = "导出审核事件清单")
	public ResultBean exportAuditEventList(HttpServletResponse response, HttpServletRequest request,
			@RequestBody AuditEventSearch auditEventSearch) throws Exception {
		auditEventService.exportAuditEventList(response, request, auditEventSearch);
		return ResultBean.success();
	}
}