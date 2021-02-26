package cn.jwis.qualityworkflow.modules.qims.controller;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaSearch;
import cn.jwis.qualityworkflow.modules.qims.service.QimsCqaService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_SAVE;
import static cn.jwis.qualityworkflow.common.Constants.QIMSCQATEMPLATEKEY;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:54
 */
@RestController
@RequestMapping(value = "/qimsCqa")
@Api(description = "QIMSCQA问题接口")
public class QimsCqaController extends BaseClass {

	@Autowired
	QimsCqaService qimsCqaService;
	@Autowired
	HistoryProcessRecordService historyProcessRecordService;


	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean)  {
		ResultBean resultBean = new ResultBean();
		qimsCqaService.confirm(bean);
		return resultBean;
	}

	@RequestMapping(value = "/saveQimsCqaInfo", method = RequestMethod.POST)
	@ApiOperation(value = "创建QIMSCQA问题单据", notes = "创建QIMSCQA问题单据")
	public ResultBean saveQimsCqaInfo(@RequestBody QimsCqaInfo bean)  {
		ResultBean resultBean = new ResultBean();
		qimsCqaService.saveQimsCqaInfo(bean);
		return resultBean;
	}

	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "QIMSCQA问题下拉值获取",notes = "QIMSCQA问题下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(qimsCqaService.getDropdownValue(parameter));
		return resultBean;
	}

	@RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.GET)
	@ApiOperation(value = "查询是否新建时是否有历史记录 ", notes = "查询是否新建时是否有历史记录")
	public ResultBean getLatestRecordContent() throws Exception {
		QueryHistoryProcessRecord bean = new QueryHistoryProcessRecord();
		bean.setCreator(UserUtil.getCurrentUserName());
		bean.setWorkflowType(QIMSCQATEMPLATEKEY);
		bean.setType(HISOTORY_PROCESS_SAVE);
		bean.setWorkflowNode("CQA问题描述");
		bean.setWorkflowBusinessid(null);
		JSONObject result = historyProcessRecordService.getLatestRecordContentAndId(bean);
		return ResultBean.success(result);
	}

	@RequestMapping(value = "getQimsCqaInfoList",method = RequestMethod.POST)
	@ApiOperation(value = "查询QIMSCQA问题列表",notes = "查询QIMSCQA问题列表")
	public ResultBean getQimsCqaInfoList(HttpServletRequest request,@RequestBody QimsCqaSearch qimsCqaSearch){
		int page = qimsCqaSearch.getPage();
		int size = qimsCqaSearch.getSize();
		List<QimsCqaInfo> list = qimsCqaService.getQimsCqaInfoList(qimsCqaSearch);
		Map<String, String> title = qimsCqaService.getTitle(request);
		if (isNotNull(list)){
			return ResultBean.pagination(qimsCqaService.getQimsCqaInfoListCount(qimsCqaSearch), page, size, list,title);
		}else {
			return ResultBean.pagination(0L,page,size,list,title);
		}
	}

	@RequestMapping(value = "exportQimsCqaInfoList",method = RequestMethod.POST)
	@ApiOperation(value = "导出QIMSCQA问题列表",notes = "导出QIMSCQA问题列表")
	public void exportQimsCqaInfoList(HttpServletResponse response, HttpServletRequest request, @RequestBody QimsCqaSearch qimsCqaSearch){
		qimsCqaService.exportQimsCqaInfoList(response,request,qimsCqaSearch);
	}

	@RequestMapping(value = "/getQimsCqaInfo", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "获取QIMSCQA问题检验详情信息", notes = "获取QIMSCQA问题检验详情信息")
	public ResultBean getQimsCqaInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		Map<String, Object> resultMap = qimsCqaService.getQimsCqaInfo(bean);
		resultBean.setData(resultMap);
		return resultBean;
	}

	@RequestMapping(value = "getDropValue",method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "CQA流程中人，法，环的下拉值获取", notes = "CQA流程中人，法，环的下拉值获取")
	public ResultBean getDropValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		List<String> list = qimsCqaService.getDropValue(parameter);
		resultBean.setData(list);
		return resultBean;
	}
	@RequestMapping(value = "getSuperior",method = RequestMethod.GET)
	@ApiOperation(response = ResultBean.class, value = "获取上级领导", notes = "获取上级领导")
	public ResultBean getSuperior(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		String name = qimsCqaService.getSuperior(parameter);
		resultBean.setData(name);
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 导出CQA问题的8D报告
	 * @Date  2020/8/7  15:22
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "exportCqa8dInfo",method = RequestMethod.POST)
	@ApiOperation(value = "导出CQA问题8D报告",notes = "导出CQA问题8D报告")
	public void exportCqa8dInfo(HttpServletResponse response,@RequestBody JSONObject jsonObject) throws Exception {
		String id = jsonObject.getString("id");
		qimsCqaService.exportCqa8dInfo(response,id);
	}
}