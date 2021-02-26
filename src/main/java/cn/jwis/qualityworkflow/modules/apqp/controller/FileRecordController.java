package cn.jwis.qualityworkflow.modules.apqp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryFileUpdateVo;
import cn.jwis.qualityworkflow.modules.apqp.service.FileUpdateRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-14 14:17
 * @since 0.1.0
 **/
@RestController
@RequestMapping(value = "/fileRecord")
@Api(description = "APQP文件更新记录Controller")
public class FileRecordController {
	@Autowired
	FileUpdateRecordService service;

	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "分页获取文件更新记录列表", notes = "分页获取文件更新记录列表")
	public ResultBean getInfoList(@RequestBody QueryFileUpdateVo vo) throws Exception {
		ResultBean resultBean = new ResultBean();
		PageInfo<ApqpFileUpdateRecord> page = service.getList(vo);
		resultBean.setData(page);
		return resultBean;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ApiOperation(response = ResultBean.class, value = "新增文件更新记录", notes = "新增文件更新记录")
	public ResultBean insert(@RequestBody CreateFileUpdateRecord bean) throws Exception {
		service.insert(bean);
		return ResultBean.success();
	}

	@PostMapping(value = "/exportInfo")
	@ApiOperation(value = "根据查询条件导出文件更新记录", notes = "根据查询条件导出文件更新记录")
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryFileUpdateVo vo) throws Exception {
		service.exportInfo(response, request, vo);
	}

}
