package cn.jwis.qualityworkflow.modules.esd.controller;

import static cn.jwis.qualityworkflow.common.Constants.ESDAUDITTEMPLATEKEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

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

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormal;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormalSearch;
import cn.jwis.qualityworkflow.modules.esd.service.EsdAuditAbnormalService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import io.swagger.annotations.ApiOperation;


/**
 * @Description ESD稽核异常流程接口
 * @Author yuyangyang
 * @Date 2020/7/2 19:55
 */
@RestController
@RequestMapping(value = "/esdAuditAbnormal")
public class EsdAuditAbnormalController {

	@Autowired
	EsdAuditAbnormalService esdAuditAbnormalService;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;


	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean)  {
		ResultBean resultBean = new ResultBean();
		esdAuditAbnormalService.confirm(bean);
		return resultBean;
	}

	@RequestMapping(value = "/saveEsdAuditAbnormalInfo", method = RequestMethod.POST)
	@ApiOperation(value = "创建ESD稽核异常检验单", notes = "创建ESD稽核异常检验单")
	public ResultBean saveEsdAuditAbnormalInfo(@RequestBody EsdAuditAbnormal bean)  {
		ResultBean resultBean = new ResultBean();
		esdAuditAbnormalService.saveEsdAuditAbnormalInfo(bean);
		return resultBean;
	}

	@RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.GET)
	@ApiOperation(value = "查询是否新建时是否有历史记录 ", notes = "查询是否新建时是否有历史记录")
	public ResultBean getLatestRecordContent() throws Exception {
		QueryHistoryProcessRecord bean = new QueryHistoryProcessRecord();
		bean.setCreator(UserUtil.getCurrentUserName());
		bean.setWorkflowType(ESDAUDITTEMPLATEKEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowNode("新建ESD异常单");
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContentAndId(bean);
		return ResultBean.success(result);
	}


	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "ESD稽核异常下拉值获取",notes = "ESD稽核异常下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdAuditAbnormalService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "getEsdAuditAbnormalInfoList",method = RequestMethod.POST)
	@ApiOperation(value = "查询QIMS黑色问题列表",notes = "查询QIMS黑色问题列表")
	public ResultBean getEsdAuditInfoList(HttpServletRequest request,@RequestBody EsdAuditAbnormalSearch esdAuditAbnormalSearch){
		int page = esdAuditAbnormalSearch.getPage();
		int size = esdAuditAbnormalSearch.getSize();
		List<EsdAuditAbnormal> list = esdAuditAbnormalService.getEsdAuditAbnormalInfoList(esdAuditAbnormalSearch);
		Map<String,String> title = esdAuditAbnormalService.getTitle(request);
		if (CollectionUtils.isNotEmpty(list)){
			return ResultBean.pagination(esdAuditAbnormalService.getEsdAuditAbnormalInfoCount(esdAuditAbnormalSearch), page, size, list,title);
		}else {
			return ResultBean.pagination(0L,page,size,list,title);
		}
	}

	@RequestMapping(value = "exportEsdAuditAbnormalInfoList",method = RequestMethod.POST)
	@ApiOperation(value = "导出QIMS黑色问题列表",notes = "导出QIMS黑色问题列表")
	public void exportEsdAuditAbnormalInfoList(HttpServletResponse response, HttpServletRequest request, @RequestBody EsdAuditAbnormalSearch esdAuditAbnormalSearch){
		esdAuditAbnormalService.exportEsdAuditAbnormalInfoList(response,request,esdAuditAbnormalSearch);
	}

	@RequestMapping(value = "/getEsdAuditInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取ESD稽核详情信息", notes = "获取ESD稽核详情信息")
	public ResultBean getEsdAuditInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = esdAuditAbnormalService.getEsdAuditInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}
}