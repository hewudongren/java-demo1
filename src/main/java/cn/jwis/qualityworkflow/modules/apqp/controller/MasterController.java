package cn.jwis.qualityworkflow.modules.apqp.controller;

import static cn.jwis.qualityworkflow.common.Constants.APQP_MASTER_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.CretaeHistoryProcessBean;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.bean.TimeBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.DashBoardCountBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryDashboardDetailVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryMasterListVo;
import cn.jwis.qualityworkflow.modules.apqp.service.ApqpDashBoardService;
import cn.jwis.qualityworkflow.modules.apqp.service.MasterDocumentService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-25 17:40
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/masterDocument")
@Api(description = "APQP主单据管理Controller")
public class MasterController {

	@Autowired
	MasterDocumentService service;

	@Autowired
	ApqpDashBoardService dashBoardService;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	/**
	 * @description: 获取主单据列表信息
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:35
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取主单据列表信息", notes = "获取主单据列表信息")
	public ResultBean getInfoList(@RequestBody QueryMasterListVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<JSONObject> page = service.getInfoList(bean);
		resultBean.setData(page);
		return resultBean;
	}

	/**
	 * @description: 新增主单据信息
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:35
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "新增主单据信息", notes = "新增主单据信息")
	public ResultBean insert(@RequestBody CreateMasterDocument bean) throws Exception {
		service.insert(bean);
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
	 *
	 * @Description: 获取APQP主单据流程详细详情信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMasterDocumentById", method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "获取主单据信息)", notes = "获取主单据信息")
	public ResultBean getMasterDocumentById(@RequestParam(value = "id") String id) throws Exception {
		ResultBean resultBean = new ResultBean();
		JSONObject object = service.getMasterDocument(id);
		resultBean.setData(object);
		return resultBean;
	}

	/**
	 *@description 导出记录
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "exportInfo")
	@ApiOperation(value = "根据查询条件导出APQP主单据", notes = "根据查询条件导出APQP主单据")
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryMasterListVo vo) throws Exception {
		service.exportInfo(response, request, vo);
	}

	@PostMapping(value = "getAddCount")
	@ApiOperation(response = ResultBean.class, value = "Dashboard 新增主单据", notes = "Dashboard 新增主单据")
	public ResultBean getAddCount(@RequestBody TimeBean bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		int addCount = dashBoardService.getAddCount(bean);
		resultBean.setData(addCount);
		return resultBean;
	}

	@PostMapping(value = "getProcessingCount")
	@ApiOperation(response = ResultBean.class, value = "Dashboard 处理中主单据", notes = "Dashboard 处理中主单据")
	public ResultBean getProcessingCount(@RequestBody TimeBean bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		int processingCount = dashBoardService.getProcessingCount(bean);
		resultBean.setData(processingCount);
		return resultBean;
	}

	@PostMapping(value = "getCloseCount")
	@ApiOperation(response = ResultBean.class, value = "Dashboard 已关闭主单据", notes = "Dashboard 已关闭主单据")
	public ResultBean getCloseCount(@RequestBody TimeBean bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		int closeCount = dashBoardService.getCloseCount(bean);
		resultBean.setData(closeCount);
		return resultBean;
	}

	@PostMapping(value = "getOverdueCount")
	@ApiOperation(response = ResultBean.class, value = "Dashboard 超期增主单据", notes = "Dashboard 超期增主单据")
	public ResultBean getOverdueCount(@RequestBody TimeBean bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		int overdueCount = dashBoardService.getOverdueCount(bean);
		resultBean.setData(overdueCount);
		return resultBean;
	}

	@PostMapping(value = "getDashBoardCount")
	@ApiOperation(response = ResultBean.class, value = "Dashboard 主单据数量", notes = "Dashboard 主单据数量")
	public ResultBean getDashBoardCount(@RequestBody TimeBean bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		DashBoardCountBean object = dashBoardService.getDashBoardCount(bean);
		resultBean.setData(object);
		return resultBean;
	}

	@PostMapping(value = "getSummaryGroupByItem")
	@ApiOperation(response = ResultBean.class, value = "Dashboard 获取Summary页面根据项目分类的条形图", notes = "Dashboard 获取Summary页面根据项目分类的条形图")
	public ResultBean getSummaryGroupByItem(@RequestBody TimeBean bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		JSONObject summaryGroupByItem = dashBoardService.getSummaryGroupByItem(bean);
		resultBean.setData(summaryGroupByItem);
		return resultBean;
	}

	@PostMapping(value = "getDashBoardDetail")
	@ApiOperation(response = ResultBean.class, value = "获取Dashboard Detail页面文件统计", notes = "获取Dashboard Detail页面文件统计")
	public ResultBean getDashBoardDetail(@RequestBody QueryDashboardDetailVo bean) throws Exception{
		ResultBean resultBean = new ResultBean();
		JSONObject detail = dashBoardService.getDashBoardDetail(bean);
		resultBean.setData(detail);
		return resultBean;
	}

	/**
	 * @description: 查询最新的保存记录
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.GET)
	@ApiOperation(value = "查询更新时间最新的历史记录 ", notes = "查询更新时间最新的历史记录")
	public ResultBean getLatestRecordContent() throws Exception {
		QueryHistoryProcessRecord bean = new QueryHistoryProcessRecord();
		bean.setCreator(UserUtil.getCurrentUserName());
		bean.setWorkflowType(APQP_MASTER_TEMPLATE_KEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowNode("Apply");
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContent(bean);
		return ResultBean.success(result);
	}

	/**
	 * @description: APQP主单据保存接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
	@ApiOperation(value = "APQP主单据保存接口 ", notes = "APQP主单据保存接口")
	public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
		CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
		vo.setTemplateKey(APQP_MASTER_TEMPLATE_KEY);
		vo.setType(HISOTORY_PROCESS_SAVE);
		String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "Apply";
		vo.setContent(bean.getContent());
		vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
		vo.setWorkflowNode(node);
		historyProcessRecordService.save(vo);
		return ResultBean.success();
	}
}
