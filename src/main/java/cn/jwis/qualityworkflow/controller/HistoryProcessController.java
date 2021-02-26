package cn.jwis.qualityworkflow.controller;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 获取历史进展控制器
 * @create 2020-04-30 15:46
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/historyProcess")
@Api(description = "历史记录接口")
public class HistoryProcessController {

	@Autowired
	HistoryProcessRecordService service;

	/**
	 * @description: 保存历史进展
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:04
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ApiOperation(value = "保存历史进展 ", notes = "保存历史进展")
	public ResultBean saveEcnInfo(@RequestBody CreateHistoryProcessVo bean) throws Exception {
		int matchRows = service.save(bean);
		if (matchRows == 0) {
			return ResultBean.fail();
		} else {
			return ResultBean.success();
		}
	}

	/**
	 * @description: 查询最新的保存记录
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@ResponseBody
	@RequestMapping(value = "/getLatestRecord", method = RequestMethod.POST)
	@ApiOperation(value = "查询更新时间最新的历史记录 ", notes = "查询更新时间最新的历史记录")
	public ResultBean getLatestRecord(@RequestBody QueryHistoryProcessRecord bean) throws Exception {
		HistoryProcessRecord latestRecord = service.getLatestRecord(bean);
		return ResultBean.success(latestRecord);
	}

	/**
	 * @description: 查询最新的保存记录
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.POST)
	@ApiOperation(value = "查询更新时间最新的历史记录 ", notes = "查询更新时间最新的历史记录")
	public ResultBean getLatestRecordContent(@RequestBody QueryHistoryProcessRecord bean) throws Exception {
		JSONObject result = service.getLatestRecordContent(bean);
		return ResultBean.success(result);
	}

	/**
	 * @description: 查询流程操作记录
	 * @author: xujinbiao
	 * @date: 2020/4/30 16:25
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	@RequestMapping(value = "/getWorkflowOperationRecord", method = RequestMethod.GET)
	@ApiOperation(value = "根据业务id查询流程操作记录 ", notes = "查询流程操作记录")
	public ResultBean getWorkflowOperationRecord(@RequestParam(value = "id") String id) throws Exception {
		List<HistoryProcessRecord> result = service.getWorkflowOperationRecord(id);
		return ResultBean.success(result);
	}
}
