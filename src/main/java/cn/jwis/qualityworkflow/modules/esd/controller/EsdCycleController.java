package cn.jwis.qualityworkflow.modules.esd.controller;

import static cn.jwis.qualityworkflow.common.Constants.ESDCYCLETEMPLATEKEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

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
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleSearch;
import cn.jwis.qualityworkflow.modules.esd.service.EsdCycleService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/11 14:35
 */
@RestController
@RequestMapping(value = "/esdPeriodic")
@Api(description = "ESD周期性检验接口")
public class EsdCycleController extends BaseClass {
	@Autowired
	EsdCycleService esdCycleService;
	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean)  {
		ResultBean resultBean = new ResultBean();
		esdCycleService.confirm(bean);
		return resultBean;
	}

	@RequestMapping(value = "/saveEsdInfo", method = RequestMethod.POST)
	@ApiOperation(value = "创建ESD周期性检验单", notes = "创建ESD周期性检验单")
	public ResultBean saveEsdCycleInfo(@RequestBody EsdCycleInfo bean)  {
		ResultBean resultBean = new ResultBean();
		esdCycleService.saveEsdCycleInfo(bean);
		return resultBean;
	}
    /**
     * @Author yuyangyang
     * @Description ESD周期性下拉值获取
     * @Date  2020/5/13  10:25
     * @Param
     * @return
     */
	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "ESD周期性检验下拉值获取",notes = "ESD周期性检验下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdCycleService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "getEsdCycleList",method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD周期性检验列表",notes = "查询ESD周期性检验列表")
	public ResultBean getEsdCycleList(HttpServletRequest request, @RequestBody EsdCycleSearch esdCycleSearch){
		int page = esdCycleSearch.getPage();
		int size = esdCycleSearch.getSize();
		List<EsdCycleInfo> list = esdCycleService.getEsdCycleList(esdCycleSearch);
		Map<String,String> title = esdCycleService.getTitle(request);
		if (isNotNull(list)){
			return ResultBean.pagination(esdCycleService.getEsdCycleListCount(esdCycleSearch), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, list,title);
		}
	}

	@RequestMapping(value = "exportEsdCycleList",method = RequestMethod.POST)
	@ApiOperation(value = "导出ESD周期性检验列表",notes = "导出ESD周期性检验列表")
	public void getDropdownValue(HttpServletResponse response, @RequestBody EsdCycleSearch esdCycleSearch){
		esdCycleService.exportEsdCycleList(response,esdCycleSearch);
	}

	@RequestMapping(value = "/getEsdCycleInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取ESD周期性检验详情信息", notes = "获取ESD周期性检验详情信息")
	public ResultBean getEsdCycleInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = esdCycleService.getEsdCycleInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.GET)
	@ApiOperation(value = "查询是否新建时是否有历史记录 ", notes = "查询是否新建时是否有历史记录")
	public ResultBean getLatestRecordContent() throws Exception {
		QueryHistoryProcessRecord bean = new QueryHistoryProcessRecord();
		bean.setCreator(UserUtil.getCurrentUserName());
		bean.setWorkflowType(ESDCYCLETEMPLATEKEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowNode("新建ESD异常单");
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContentAndId(bean);
		return ResultBean.success(result);
	}

	@RequestMapping(value = "/getMonthByName", method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "通过样品名带出检验月份", notes = "通过样品名带出检验月份")
	public ResultBean getMonthByName(@RequestParam("sampleName") String sampleName){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdCycleService.getMonthByName(sampleName));
		return resultBean;
	}

	@RequestMapping(value = "/getInfoByMonthAndName", method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "通过样品名和检验月份带出基本信息", notes = "通过样品名和检验月份带出基本信息")
	public ResultBean getInfoByMonthAndName(@RequestParam("sampleName") String sampleName,
											@RequestParam("detectionMonth")String detectionMonth ){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdCycleService.getInfoByMonthAndName(sampleName,detectionMonth));
		return resultBean;
	}
}