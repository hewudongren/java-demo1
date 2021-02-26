package cn.jwis.qualityworkflow.modules.esd.controller;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialSearch;
import cn.jwis.qualityworkflow.modules.esd.service.EsdSpecialInfoService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
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

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/8 16:33
 */
@RestController
@RequestMapping(value = "/esdSpecial")
@Api(description = "ESD来料检验接口")
public class EsdSpecialInfoController {

	@Autowired
	EsdSpecialInfoService esdSpecialInfoService;

	@RequestMapping(value = "/saveEsdSpecialInfo", method = RequestMethod.POST)
	@ApiOperation(value = "创建ESD来料检验单", notes = "创建ESD来料检验单")
	public ResultBean saveEsdSpecialInfo(@RequestBody EsdSpecialInfo bean)  {
		ResultBean resultBean = new ResultBean();
		esdSpecialInfoService.saveEsdSpecialInfo(bean);
		return resultBean;
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ApiOperation(value = "流程处理，审批", notes = "流程处理，审批")
	public ResultBean confirm(@RequestBody JSONObject bean)  {
		ResultBean resultBean = new ResultBean();
		esdSpecialInfoService.confirm(bean);
		return resultBean;
	}
    /**
     * @Author yuyangyang
     * @Description 来料单检验管理下拉值接口
     * @Date  2020/6/9  14:46
     * @Param
     * @return
     */
	@RequestMapping(value = "getDropdownValue",method = RequestMethod.GET)
	@ApiOperation(value = "ESD来料单检验管理下拉值获取",notes = "ESD来料单检验管理下拉值获取")
	public ResultBean getDropdownValue(@RequestParam("parameter")String parameter){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialInfoService.getDropdownValue(parameter));
		return resultBean;
	}

    /**
     * @Author yuyangyang
     * @Description 来料检验列表查询
     * @Date  2020/6/9  19:33
     * @Param
     * @return
     */
	@RequestMapping(value = "getEsdSpecialList",method = RequestMethod.POST)
	@ApiOperation(value = "查询ESD来料检验列表",notes = "查询ESD来料检验列表")
	public ResultBean getEsdSpecialList(HttpServletRequest request, @RequestBody EsdSpecialSearch esdSpecialSearch){
		int page = esdSpecialSearch.getPage();
		int size = esdSpecialSearch.getSize();
		List<EsdSpecialInfo> list = esdSpecialInfoService.getEsdSpecialList(esdSpecialSearch);
		Map<String, String> title = esdSpecialInfoService.getTitle(request);
		if (CollectionUtils.isNotEmpty(list)){
			return ResultBean.pagination(esdSpecialInfoService.getEsdSpecialListCount(esdSpecialSearch), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, null,title);
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 导出ESD来料检验列表
	 * @Date  2020/6/9  19:34
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "exportEsdSpecialList",method = RequestMethod.POST)
	@ApiOperation(value = "导出ESD来料检验列表",notes = "导出ESD来料检验列表")
	public ResultBean exportEsdSpecialList(HttpServletRequest request, HttpServletResponse response, @RequestBody EsdSpecialSearch esdSpecialSearch){
		esdSpecialInfoService.exportEsdSpecialList(esdSpecialSearch,request,response);
		return ResultBean.success();
	}

    /**
     * @Author yuyangyang
     * @Description ESD来料检验详情接口
     * @Date  2020/6/9  20:04
     * @Param
     * @return
     */
	@RequestMapping(value = "getEsdSpecialDetails",method = RequestMethod.POST)
	@ApiOperation(value = "ESD来料检验详情接口",notes = "ESD来料检验详情接口")
	public ResultBean getEsdSpecialDetails(@RequestBody  QueryDetailedInfoVo bean) throws Exception {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(esdSpecialInfoService.getEsdSpecialDetails(bean));
		return resultBean;
	}
}