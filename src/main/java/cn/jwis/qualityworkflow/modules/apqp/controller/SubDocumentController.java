package cn.jwis.qualityworkflow.modules.apqp.controller;

import static cn.jwis.qualityworkflow.common.Constants.APQP_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

import cn.jwis.qualityworkflow.annotation.CheckTaskState;
import cn.jwis.qualityworkflow.annotation.CheckUserPermission;
import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.CretaeHistoryProcessBean;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.enums.APQPType;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDocumentInfo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QuerySubDocumentListVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.UpdateDocumentInfoVo;
import cn.jwis.qualityworkflow.modules.apqp.service.SubDocumentService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-27 15:51
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/subDocument")
@Api(description = "APQP子单据管理Controller")
public class SubDocumentController {


	@Autowired
	SubDocumentService service;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

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
		service.confirm(bean);
		return resultBean;
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
	 * @description: 获取子单据列表信息
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:35
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getInfoList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取子单据列表信息", notes = "获取子单据列表信息")
	public ResultBean getInfoList(@RequestBody QuerySubDocumentListVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<JSONObject> page = service.getInfoList(bean);
		resultBean.setData(page);
		return resultBean;
	}

	/**
	 *@description 导出记录
	 *@author 许锦标
	 *@date 2020/4/28 15:25
	 *@email jinbiao.xu@jwis.cn
	 */
	@PostMapping(value = "exportInfo")
	@ApiOperation(value = "根据查询条件导出APQP子单据", notes = "根据查询条件导出APQP子单据")
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QuerySubDocumentListVo vo) throws Exception {
		service.exportInfo(response, request, vo);
	}

	/**
	 *
	 * @Description: 获取APQP子单据流程详细详情信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDetailednessInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取流程详细详情信息(包括高风险项信息)", notes = "获取流程详细详情信息(包括高风险项信息)")
	public ResultBean getDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = service.getDetailednessInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "/confirmDocument", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "更新文件类型的上传信息", notes = "更新文件类型的上传信息")
	public ResultBean confirmDocument(@RequestBody List<UpdateDocumentInfoVo> list) throws Exception {
		service.confirmDocument(list);
		return ResultBean.success();
	}

	@RequestMapping(value = "/getDocumentByIdAndType", method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "获取子单据不同阶段的文档", notes = "获取子单据不同阶段的文档")
	public ResultBean getDocumentByIdAndType(@RequestParam( value = "id") String id, @RequestParam("type") APQPType type) throws SQLException {
		ResultBean resultBean = new ResultBean();
		List<ApqpDocumentInfo> list = service.getDocumentByIdAndType(id, type.toString());
		resultBean.setData(list);
		return resultBean;
	}

	/**
	 * @description: APQP主单据保存接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
	@ApiOperation(value = "APQP子单据保存接口 ", notes = "APQP子单据保存接口")
	public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
		CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
		String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "Apply";
		vo.setTemplateKey(APQP_TEMPLATE_KEY);
		vo.setType(HISOTORY_PROCESS_SAVE);
		vo.setWorkflowNode(node);
		vo.setContent(bean.getContent());
		vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
		historyProcessRecordService.save(vo);
		return ResultBean.success();
	}
}
