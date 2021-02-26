package cn.jwis.qualityworkflow.modules.ecn.controller;

import cn.jwis.qualityworkflow.annotation.CheckTaskState;
import cn.jwis.qualityworkflow.annotation.CheckUserPermission;
import cn.jwis.qualityworkflow.bean.*;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryExternalDocumentVo;
import cn.jwis.qualityworkflow.modules.ecn.service.ExternalDocumentService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.common.Constants.EXTERNAL_DOCUMNENT_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-07 16:14
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/externalDocument")
@Api(description = "外来文件接口")
public class ExternalDocumentController {

	@Autowired
	ExternalDocumentService documentService;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;
	/**
	 *
	 * @Description:
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取外来文件列表信息", notes = "获取外来文件列表信息")
	public ResultBean getList(@RequestBody QueryExternalDocumentVo bean ,HttpServletRequest request) throws Exception {
		ResultBean resultBean = new ResultBean();
		String origin = request.getHeader("Origin");
		PageInfo<JSONObject> page = documentService.getList(bean);
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
	@RequestMapping(value = "/getDetailednessInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取外来文件详细详情信息", notes = "获取外来文件详细详情信息")
	public ResultBean getDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = documentService.getDetailednessInfo(bean);
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
	@ApiOperation(value = "外来文件流程处理，审批", notes = "外来文件流程处理，审批")
	public ResultBean confirm(@RequestBody ConfirmVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		documentService.confirm(bean);
		return resultBean;
	}

	/**
	 *@description 导出记录
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "/exportInfo")
	@ApiOperation(value = "根据查询条件导出外来文件单据", notes = "根据查询条件导出外来文件单据")
	public void exportEcnInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryExternalDocumentVo vo) throws Exception {
		documentService.exportInfo(response, request, vo);
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
		List<Object> pullDownValue = documentService.getPullDownValue(parameter);
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
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowType(EXTERNAL_DOCUMNENT_TEMPLATE_KEY);
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContent(bean);
		return ResultBean.success(result);
	}

	/**
	 * @description: 外来文件保存接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
	@ApiOperation(value = "外来文件保存接口 ", notes = "外来文件保存接口")
	public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
		CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
		vo.setTemplateKey(EXTERNAL_DOCUMNENT_TEMPLATE_KEY);
		vo.setType(HISOTORY_PROCESS_SAVE);
		String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "Apply";
		vo.setContent(bean.getContent());
		vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
		vo.setWorkflowNode(node);
		historyProcessRecordService.save(vo);
		return ResultBean.success();
	}
}
