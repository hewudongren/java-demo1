package cn.jwis.qualityworkflow.modules.ecn.controller;

import cn.jwis.qualityworkflow.annotation.CheckTaskState;
import cn.jwis.qualityworkflow.annotation.CheckUserPermission;
import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.CretaeHistoryProcessBean;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryDashboardBean;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryEcnInfoVo;
import cn.jwis.qualityworkflow.modules.ecn.service.EcnDashBoardService;
import cn.jwis.qualityworkflow.modules.ecn.service.ECNService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.common.Constants.ECN_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

/**
 * 
 * @ClassName: EcnController
 * @Description: ECN品管理Controller
 * @author longjun
 * @date 2018年8月31日
 */
@RestController
@RequestMapping(value = "/ecn")
@Api(description = "ECN流程接口")
public class ECNController {

	@Autowired
	ECNService ecnService;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;


	@Autowired
	EcnDashBoardService ecnDashBoardService;




	/**
	 * 列表查询条件--------------------------------------------------------------
	 */

	/**
	 * 流程保存、列表查询、流程详情、流程确认--------------------------------------------------------------
	 */



	/**
	    *@description 保存ECN流程
	    *@author 许锦标
	    *@date 2020/4/26 16:07
	    *@email jinbiao.xu@jwis.cn
	 */
//	@RequestMapping(value = "/saveEcnInfo", method = RequestMethod.POST)
//	@ApiOperation(value = "保存ECN流程 ", notes = "保存ECN流程 (只是产生记录，停留在拟制单据阶段(Apply))")
//	public ResultBean saveEcnInfo(@RequestBody EcnInfo bean) throws Exception {
//		int matcheRows = ecnService.saveEcnInfo(bean);
//		if (matcheRows == 0) {
//			return ResultBean.fail();
//		} else {
//			return ResultBean.success();
//		}
//	}

	/**
	 * 
	 * @Description: 获取ECN品流程列表信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEcnInfoList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取ECN品流程列表信息", notes = "获取ECN品流程列表信息")
	public ResultBean getEcnInfoList(@RequestBody QueryEcnInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<JSONObject> page = ecnService.getEcnInfoList(bean);
		resultBean.setData(page);
		return resultBean;
	}

	/**
	 * 
	 * @Description: 获取ECN品流程详细详情信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEcnDetailednessInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取ECN品流程详细详情信息", notes = "获取ECN品流程详细详情信息")
	public ResultBean getEcnDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = ecnService.getEcnDetailedInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	/**
	 * 
	 * @Description: 流程处理，审批
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@CheckUserPermission
	@CheckTaskState
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody ConfirmVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		ecnService.confirm(bean);
		return resultBean;
	}

	/**
	    *@description 导出记录
	    *@author 许锦标
	    *@date 2020/4/28 15:25
	    *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "exportEcnInfo")
	@ApiOperation(value = "根据查询条件导出Ecn单据", notes = "根据查询条件导出Ecn单据")
	public void exportEcnInfo(HttpServletResponse response, HttpServletRequest request,@RequestBody QueryEcnInfoVo vo) throws Exception {
		ecnService.exportEcnInfo(response, request, vo);
	}

	/**
	 * @description: 获取下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 10:58
	 * @param parameter:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@GetMapping(value = "getPullDownValue")
	@ApiOperation(value = "获取Ecn下拉值", notes = "根据查询条件导出Ecn单据")
	public ResultBean getPullDownValue(@RequestParam(value = "parameter" ) String parameter) throws Exception {
		List<Object> pullDownValue = ecnService.getPullDownValue(parameter);
		return ResultBean.success(pullDownValue);
	}



	/**
	 * @description: 获取DashBoard新增数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:02
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getAddCount")
	@ApiOperation(value = "获取DashBoard新增数量", notes = "获取DashBoard新增数量")
	public ResultBean getAddCount(@RequestBody QueryDashboardBean bean) throws Exception {
		long addCount = ecnDashBoardService.getAddCount(bean);
		return ResultBean.success(addCount);

	}

	/**
	 * @description: 获取DashBoard处理中的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:02
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getProcessingCount")
	@ApiOperation(value = "获取DashBoard处理中的数量", notes = "获取DashBoard处理中的数量")
	public ResultBean getProcessingCount(@RequestBody QueryDashboardBean bean) throws Exception {
		long processingCount = ecnDashBoardService.getProcessingCount(bean);
		return ResultBean.success(processingCount);
	}

	/**
	 * @description: 获取DashBoard已关闭的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:02
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getClosedCount")
	@ApiOperation(value = "获取DashBoard已关闭的数量", notes = "获取DashBoard已关闭的数量")
	public ResultBean getClosedCount(@RequestBody QueryDashboardBean bean) throws Exception {
		long closedCount = ecnDashBoardService.getClosedCount(bean);
		return ResultBean.success(closedCount);
	}


	/**
	 * @description: 获取DashBoard 超期 新增 已关闭 处理中数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:02
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getDashBoardCount")
	@ApiOperation(value = "获取DashBoard 超期 新增 已关闭 处理中数量", notes = "获取DashBoard 超期 新增 已关闭 处理中数量")
	public ResultBean getDashBoardCount(@RequestBody QueryDashboardBean bean) throws Exception {
		JSONObject dashBoardCount = ecnDashBoardService.getDashBoardCount(bean);
		return ResultBean.success(dashBoardCount);
	}

	/**
	 * @description: 获取DashBoard 超期 新增 已关闭 处理中数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:02
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getTrentChart")
	@ApiOperation(value = "获取DashBoard 趋势图", notes = "获取DashBoard 趋势图")
	public ResultBean getTrentChart(@RequestBody QueryDashboardBean bean) throws Exception {
		JSONObject dashBoardCount = ecnDashBoardService.getTrentChart(bean);
		return ResultBean.success(dashBoardCount);
	}

	/**
	 * @description: 获取DashBoard过期的数量
	 * @author: xujinbiao
	 * @date: 2020/5/6 11:02
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@PostMapping(value = "/getOverdueCount")
	@ApiOperation(value = "获取DashBoard过期的数量", notes = "获取DashBoard过期的数量  " +
			"\n1.超期的时间为 超过 节点创建时间24H  \n 2.会签节点超期计算次数 只为 1 不分什么会签，是以业务流程为维度")
	public ResultBean getOverdueCount(@RequestBody QueryDashboardBean bean) throws Exception {
		long overdueCount = ecnDashBoardService.getOverdueCount(bean);
		return ResultBean.success(overdueCount);
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
		bean.setWorkflowType(ECN_TEMPLATE_KEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowNode("Apply");
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContent(bean);
		return ResultBean.success(result);
	}

	/**
	 * @description: Ecn保存接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
	@ApiOperation(value = "Ecn保存接口 ", notes = "Ecn保存接口")
	public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
		CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
		vo.setTemplateKey(ECN_TEMPLATE_KEY);
		vo.setType(HISOTORY_PROCESS_SAVE);
		String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "Apply";
		vo.setContent(bean.getContent());
		vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
		vo.setWorkflowNode(node);
		historyProcessRecordService.save(vo);
		return ResultBean.success();
	}
//	/**
//	 *
//	 * @Description: 得到ECN新增个数
//	 * @author longjun
//	 * @date 2018年8月31日
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/getTodayAddEcnCount", method = RequestMethod.GET)
//	@ApiOperation(response = ResultBean.class, value = "得到今日ECN新增个数", notes = "得到今日ECN新增个数")
//	public ResultBean getTodayAddEcnCount() throws Exception {
//		ResultBean resultBean = new ResultBean();
//		Map<String, Object> productNameList = ecnService.getTodayAddEcnCount();
//		resultBean.setData(productNameList);
//		return resultBean;
//	}
//
//	/**
//	 *
//	 * @Description: 得到今日ECN关闭个数
//	 * @author longjun
//	 * @date 2018年8月31日
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/getTodayCloseEcnCount", method = RequestMethod.GET)
//	@ApiOperation(response = ResultBean.class, value = "得到今日ECN关闭个数", notes = "得到今日ECN关闭个数")
//	public ResultBean getTodayCloseEcnCount() throws Exception {
//		ResultBean resultBean = new ResultBean();
//		Map<String, Object> closeCountMap = ecnService.getTodayCloseEcnCount();
//		resultBean.setData(closeCountMap);
//		return resultBean;
//	}
//
//
//
//	/**
//	 *
//	 * @Description: 查询ECN流程趋势
//	 * @author longjun
//	 * @date 2020年4月21日
//	 * @param bean
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/queryEcnTendencyChartDatas", method = RequestMethod.POST)
//	@ApiOperation(response = ResultBean.class, value = "查询ECN品流程趋势", notes = "查询ECN流程趋势")
//	public ResultBean queryEcnTendencyChartDatas(@RequestBody ECNInfoBean bean) throws Exception {
//		ResultBean resultBean = new ResultBean();
//		Map<String, Object> tendencyChartDataMap = ecnService.queryEcnTendencyChartDatas(bean);
//		resultBean.setData(tendencyChartDataMap);
//		return resultBean;
//	}


}
