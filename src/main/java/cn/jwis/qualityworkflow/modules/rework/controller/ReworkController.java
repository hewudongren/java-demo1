package cn.jwis.qualityworkflow.modules.rework.controller;

import cn.jwis.qualityworkflow.annotation.CheckTaskState;
import cn.jwis.qualityworkflow.annotation.CheckUserPermission;
import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.CretaeHistoryProcessBean;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryPieChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkApplyVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkInfoVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryTrentChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkMoStatus;
import cn.jwis.qualityworkflow.modules.rework.service.ReworkService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import com.alibaba.fastjson.JSONObject;
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
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;
import static cn.jwis.qualityworkflow.common.Constants.REWORK_TEMPLATE_KEY;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 返工流程接口
 * @create 2020-05-18 11:10
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/rework")
@Api(description = "返工流程接口")
public class
ReworkController {
	@Autowired
	ReworkService reworkService;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	/**
	 * @description: 获取返工申请列表信息
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:14
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getApplyList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取返工申请列表信息", notes = "获取返工申请列表信息")
	public ResultBean getApplyList(@RequestBody QueryReworkApplyVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<JSONObject> page = reworkService.getApplyInfo(bean);
		resultBean.setData(page);
		return resultBean;
	}

	/**
	 * @description: 获取返工记录详情信息
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:15
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getDetailednessInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取返工记录详情信息", notes = "获取返工记录详情信息")
	public ResultBean getDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = reworkService.getDetailednessInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	/**
	 * @description: 流程提交接口
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:16
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@CheckUserPermission
	@CheckTaskState
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程提交接口", notes = "流程提交接口")
	public ResultBean confirm(@RequestBody ConfirmVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		reworkService.confirm(bean);
		return resultBean;
	}

	/**
	 *@description 导出记录
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "exportApplyInfo")
	@ApiOperation(value = "根据查询条件导出返工申请单据", notes = "根据查询条件导出返工申请单据")
	public void exportApplyInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryReworkApplyVo vo) throws Exception {
		reworkService.exportApplyInfo(response, request, vo);
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
		List<Object> pullDownValue = reworkService.getPullDownValue(parameter);
		return ResultBean.success(pullDownValue);
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
		bean.setWorkflowNode("Apply");
		bean.setWorkflowType(REWORK_TEMPLATE_KEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		JSONObject result = historyProcessRecordService.getLatestRecordContent(bean);
		return ResultBean.success(result);
	}

	/**
	 * @description: 下载返工单信息模板
	 * @author: xujinbiao
	 * @date: 2020/5/19 14:07
	 * @param response:
	 * @param request:
	 * @return: void
	 **/
	@GetMapping(value = "exportReworkTemplate")
	@ApiOperation(value = "下载返工单信息模板", notes = "下载返工单信息模板")
	public void exportReworkTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		reworkService.exportReworkTemplate(response, request);
	}

	/**
	 * @description: 获取返工信息列表
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:14
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getReworkList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取返工信息列表", notes = "获取返工信息列表")
	public ResultBean getReworkList(@RequestBody QueryReworkInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<ReworkInfo> page = reworkService.getReworkList(bean);
		resultBean.setData(page);
		return resultBean;
	}

	/**
	 * @description: 导出返工信息列表
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:14
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/exportReworkList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "导出返工信息列表", notes = "导出返工信息列表")
	public void exportReworkList(HttpServletResponse response, HttpServletRequest request,
									   @RequestBody QueryReworkInfoVo bean) throws Exception {
		reworkService.exportReworkList(response, request, bean);
	}

	/**
	 * 导入 返工信息
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importReworkInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "导入返工信息", notes = "导入 返工信息")
	public ResultBean importReworkInfo(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request) throws Exception {
		ResultBean resultBean = new ResultBean();
		reworkService.importReworkInfo(file, request);
		return resultBean;
	}

	/**
	 *@description
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "getReworkQuantityChart")
	@ApiOperation(value = "获取返工数量趋势图", notes = "获取返工数量趋势图")
	public ResultBean getReworkQuantityChart(@RequestBody QueryTrentChartVo vo) throws Exception {
		ResultBean resultBean = new ResultBean();
		JSONObject reworkQuantityChart = reworkService.getReworkQuantityChart(vo);
		resultBean.setData(reworkQuantityChart);
		return resultBean;
	}

	/**
	 *@description 获取问题类型饼图
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "getProblemTypeChart")
	@ApiOperation(value = "获取问题类型饼图", notes = "获取问题类型饼图")
	public ResultBean getProblemTypeChart(@RequestBody QueryPieChartVo vo) throws Exception {
		ResultBean resultBean = new ResultBean();
		vo.setGroupBy("problem_type");
		JSONObject problemTypeChart = reworkService.getPieChart(vo);
		resultBean.setData(problemTypeChart);
		return resultBean;
	}

	/**
	 *@description 获取根本原因责任方饼图
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "getRootCauseChart")
	@ApiOperation(value = "获取根本原因责任方饼图", notes = "获取根本原因责任方饼图")
	public ResultBean getRootCauseChart(@RequestBody QueryPieChartVo vo) throws Exception {
		ResultBean resultBean = new ResultBean();
		vo.setGroupBy("root_cause_responsibility");
		JSONObject rootCauseChart = reworkService.getPieChart(vo);
		resultBean.setData(rootCauseChart);
		return resultBean;
	}

	/**
	 * @description: 根据返工MO(排程号) 导出 维修明细表数据(t_maintenance_detail )数据
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:14
	 * @param reworkMO: 返工MO(排程号)
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/exportMaintenanceDetail", method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "导出返工信息列表", notes = "导出返工信息列表")
	public void exportMaintenanceDetail(HttpServletResponse response, HttpServletRequest request,
									   @RequestParam(value = "返工MO") String reworkMo) throws Exception {
		reworkService.exportMaintenanceDetail(response, request, reworkMo);
	}

	/**
	 * @description: Rework保存接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
	@ApiOperation(value = "Rework保存接口 ", notes = "Rework保存接口")
	public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
		CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
		vo.setTemplateKey(REWORK_TEMPLATE_KEY);
		vo.setType(HISOTORY_PROCESS_SAVE);
		String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "Apply";
		vo.setContent(bean.getContent());
		vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
		vo.setWorkflowNode(node);
		historyProcessRecordService.save(vo);
		return ResultBean.success();
	}

	@RequestMapping(value = "/getMoStatusList", method = RequestMethod.GET)
	@ApiOperation(value = "根据reworkId获取对应的Mo状态", notes = "根据reworkId获取对应的Mo状态")
	public ResultBean getMoStatusList(@RequestParam(value = "id") String id) throws Exception {
		ResultBean resultBean = new ResultBean();
		List<ReworkMoStatus> reworkMoStatusList = reworkService.getReworkMoStatusList(id);
		resultBean.setData(reworkMoStatusList);
		return resultBean;
	}


	@RequestMapping(value = "/thawReworkMo", method = RequestMethod.GET)
	@ApiOperation(value = "根据id解冻对应的Mo", notes = "根据id解冻对应的Mo")
	public ResultBean saveRecordContent(@RequestParam(value = "id") String id) throws Exception {
		reworkService.thawReworkMo(id);
		return ResultBean.success();
	}
}
