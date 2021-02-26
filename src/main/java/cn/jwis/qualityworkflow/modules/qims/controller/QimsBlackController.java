package cn.jwis.qualityworkflow.modules.qims.controller;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;
import static cn.jwis.qualityworkflow.common.Constants.QIMSBLACKTEMPLATEKEY;

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
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackSearch;
import cn.jwis.qualityworkflow.modules.qims.service.QimsBlackService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description QIMS黑色问题控制层
 * @Author yuyangyang
 * @Date 2020/5/19 17:50
 */
@RestController
@RequestMapping(value = "/qimsBlack")
@Api(description = "QIMS黑色问题接口")
public class QimsBlackController extends BaseClass {

	@Autowired
	QimsBlackService qimsBlackService;
	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean)  {
		ResultBean resultBean = new ResultBean();
		qimsBlackService.confirm(bean);
		return resultBean;
	}

	@RequestMapping(value = "/saveQimsBlackInfo", method = RequestMethod.POST)
	@ApiOperation(value = "创建QIMS黑色问题单据", notes = "创建QIMS黑色问题单据")
	public ResultBean saveQimsBlackInfo(@RequestBody QimsBlackInfo bean)  {
		ResultBean resultBean = new ResultBean();
		qimsBlackService.saveQimsBlackInfo(bean);
		return resultBean;
	}

	@RequestMapping(value = "getQimsBlackInfoList",method = RequestMethod.POST)
	@ApiOperation(value = "查询QIMS黑色问题列表",notes = "查询QIMS黑色问题列表")
	public ResultBean getQimsBlackInfoList(@RequestBody QimsBlackSearch qimsBlackSearch){
		int page = qimsBlackSearch.getPage();
		int size = qimsBlackSearch.getSize();
		List<QimsBlackInfo> list = qimsBlackService.getQimsBlackInfoList(qimsBlackSearch);
		if (isNotNull(list)){
			return ResultBean.pagination(qimsBlackService.getQimsBlackInfoListCount(qimsBlackSearch), page, size, list);
		}else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "exportQimsBlackInfoList",method = RequestMethod.POST)
	@ApiOperation(value = "导出QIMS黑色问题列表",notes = "导出QIMS黑色问题列表")
	public void exportQimsBlackInfoList(HttpServletResponse response, HttpServletRequest request, @RequestBody QimsBlackSearch qimsBlackSearch){
		qimsBlackService.exportQimsBlackInfoList(response,request,qimsBlackSearch);
	}

	@RequestMapping(value = "/getQimsBlackInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取QIMS黑色问题检验详情信息", notes = "获取QIMS黑色问题检验详情信息")
	public ResultBean getQimsBlackInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = qimsBlackService.getQimsBlackInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.GET)
	@ApiOperation(value = "查询是否新建时是否有历史记录 ", notes = "查询是否新建时是否有历史记录")
	public ResultBean getLatestRecordContent() throws Exception {
		QueryHistoryProcessRecord bean = new QueryHistoryProcessRecord();
		bean.setCreator(UserUtil.getCurrentUserName());
		bean.setWorkflowType(QIMSBLACKTEMPLATEKEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowNode("问题描述");
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContentAndId(bean);
		return ResultBean.success(result);
	}

	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "QIMS黑色问题下拉值获取",notes = "QIMS黑色问题下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "getSubsection",method = RequestMethod.GET)
	@ApiOperation(value = "QIMS黑色问题新增段别下拉值",notes = "QIMS黑色问题新增段别下拉值")
	public ResultBean getSubsection(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getSubsection());
		return resultBean;
	}

	@RequestMapping(value = "getModel",method = RequestMethod.GET)
	@ApiOperation(value = "QIMS黑色问题新增机型下拉值",notes = "QIMS黑色问题新增机型下拉值")
	public ResultBean getModel(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getModel());
		return resultBean;
	}

	@RequestMapping(value = "getDepartment",method = RequestMethod.GET)
	@ApiOperation(value = "QIMS黑色问题新增部门下拉值",notes = "QIMS黑色问题新增部门下拉值")
	public ResultBean getDepartment(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getDepartment());
		return resultBean;
	}

	@RequestMapping(value = "getNameByDepartment",method = RequestMethod.GET)
	@ApiOperation(value = "QIMS黑色问题新增部门用户下拉值",notes = "QIMS黑色问题新增部门用户下拉值")
	public ResultBean getNameByDepartment(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getNameByDepartment(parameter));
		return resultBean;
	}

	@RequestMapping(value = "getValueBySubsection",method = RequestMethod.POST)
	@ApiOperation(value = "QIMS黑色问题新增通过段别带出下拉值",notes = "QIMS黑色问题新增通过段别带出下拉值")
	public ResultBean getValueBySubsection(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getValueBySubsection(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "getUserName",method = RequestMethod.GET)
	@ApiOperation(value = "获取系统所有用户的名字",notes = "获取系统所有用户的名字")
	public ResultBean getUserName(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getUserName());
		return resultBean;
	}

	@RequestMapping(value = "getSuperior",method = RequestMethod.GET)
	@ApiOperation(value = "获取上级用户信息",notes = "获取上级用户信息")
	public ResultBean getSuperior(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getSuperior());
		return resultBean;
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 段别带出线体下拉值
	 * @Date  2020/7/22  11:38
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "getLineValue",method = RequestMethod.POST)
	@ApiOperation(value = "段别带出线体下拉值",notes = "段别带出线体下拉值")
	public ResultBean getLineValue(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getLineValue(jsonObject));
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 段别和线体带出异常站点
	 * @Date  2020/7/22  11:39
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "getSiteValue",method = RequestMethod.POST)
	@ApiOperation(value = "段别和线体带出异常站点",notes = "段别和线体带出异常站点")
	public ResultBean getSiteValue(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsBlackService.getSiteValue(jsonObject));
		return resultBean;
	}
	/**
	 * @Author yuyangyang
	 * @Description 导出黑色问题8D报告
	 * @Date  2020/8/7  15:22
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "exportBlack8dInfo",method = RequestMethod.POST)
	@ApiOperation(value = "导出黑色问题8D报告",notes = "导出黑色问题8D报告")
	public void exportBlack8dInfo(HttpServletResponse response,@RequestBody JSONObject jsonObject) throws Exception {
		String id = jsonObject.getString("id");
		qimsBlackService.exportBlack8dInfo(response,id);
	}
}