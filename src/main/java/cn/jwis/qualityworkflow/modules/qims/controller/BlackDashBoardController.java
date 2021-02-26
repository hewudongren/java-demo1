package cn.jwis.qualityworkflow.modules.qims.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.qims.bean.BlackDashSearch;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetSearch;
import cn.jwis.qualityworkflow.modules.qims.service.BlackDashBoardService;
import cn.jwis.qualityworkflow.modules.qims.util.QimsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @Description 黑色问题DashBoard接口
 * @Author yuyangyang
 * @Date 2020/5/29 11:40
 */
@RestController
@RequestMapping(value = "/blackDash")
@Api(description = "黑色问题DashBoard")
public class BlackDashBoardController extends BaseClass {
	@Autowired
	BlackDashBoardService blackDashBoardService;


	@RequestMapping(value = "/getAllAndClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板新增,已处理,关闭数量", notes = "DashBoard看板新增,已处理,关闭数量")
	public ResultBean getAllAndClose(@RequestBody JSONObject jsonObject){
       ResultBean resultBean = new ResultBean();
       resultBean.setData(blackDashBoardService.getAllAndClose(jsonObject));
       return resultBean;
	}

	@RequestMapping(value = "/getOverdue", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板超期数量", notes = "DashBoard看板超期数量")
	public ResultBean getOverdue(@RequestBody JSONObject jsonObject) throws ParseException {
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getOverdue(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getDashBoardClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板关闭和及时关闭趋势图", notes = "DashBoard看板关闭和及时关闭趋势图")
	public ResultBean getDashBoardClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getDashBoardClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getDashBoardNotClose", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板关闭与未关闭数量", notes = "DashBoard看板关闭与未关闭数量")
	public ResultBean getDashBoardNotClose(@RequestBody JSONObject jsonObject){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getDashBoardNotClose(jsonObject));
		return resultBean;
	}

	@RequestMapping(value = "/getAllData", method = RequestMethod.POST)
	@ApiOperation(value = "DashBoard看板累计关闭数，关闭率接口", notes = "DashBoard看板累计关闭数，关闭率接口")
	public ResultBean getAllData(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getAllData());
		return resultBean;
	}

	@RequestMapping(value = "/getUnseasonalBola", method = RequestMethod.GET)
	@ApiOperation(value = "累计未关闭记录(不及时处理柏拉图)", notes = "累计未关闭记录(不及时处理柏拉图)")
	public ResultBean getUnseasonalBola(@RequestParam("type") String type){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getUnseasonalBola(type));
		return resultBean;
	}

	@RequestMapping(value = "/exportUnseasonalBola", method = RequestMethod.GET)
	@ApiOperation(value = "导出累计未关闭记录(不及时处理柏拉图)", notes = "导出累计未关闭记录(不及时处理柏拉图)")
	public void exportUnseasonalBola(HttpServletRequest request,HttpServletResponse response,@RequestParam("type") String type){
		blackDashBoardService.exportUnseasonalBola(type,response,request);
	}


	@RequestMapping(value = "/getUnseasonalList", method = RequestMethod.POST)
	@ApiOperation(value = "累计未关闭记录(超期未回复列表)", notes = "累计未关闭记录(超期未回复列表)")
	public ResultBean getUnseasonalList(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		int page = jsonObject.getInteger("page");
		int size = jsonObject.getInteger("size");
		List<QimsBlackInfo> unseasonalList = blackDashBoardService.getUnseasonalList(page, size);
		Map<String,String> title = blackDashBoardService.getTitle(request);
		if (isNotNull(unseasonalList)){
			return ResultBean.pagination(blackDashBoardService.getUnseasonalCount(), page, size, unseasonalList,title);
		}else {
			return ResultBean.pagination(0L, page, size, unseasonalList,title);
		}
	}

	@RequestMapping(value = "exportUnseasonalList",method = RequestMethod.POST)
	@ApiOperation(value = "导出超期未回复列表",notes = "导出超期未回复列表")
	public void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request){
		blackDashBoardService.exportUnseasonalList(response,request);
	}

	@RequestMapping(value = "/getDetails", method = RequestMethod.POST)
	@ApiOperation(value = "详细统计查询接口", notes = "详细统计查询接口")
	public ResultBean getBlackDetails(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		List<JSONObject> blackDetails = blackDashBoardService.getBlackDetails(jsonObject);
		List<JSONObject> result = new ArrayList<>();
		int temp = blackDetails.size();
		if (isNotNull(blackDetails)){
			result = QimsUtil.getData(blackDetails,page,size,temp);
			Map<String,String> title = blackDashBoardService.getDetailsTitle(request);
			return ResultBean.pagination(Long.valueOf(temp), page, size, result,title);
		}else {
			return new ResultBean();
		}
	}

	@RequestMapping(value = "/exportDetails", method = RequestMethod.POST)
	@ApiOperation(value = "详细统计导出接口", notes = "详细统计导出接口")
	public void exportBlackDetails(HttpServletRequest request,HttpServletResponse response, @RequestBody JSONObject jsonObject){
		blackDashBoardService.exportBlackDetails(request,response,jsonObject);
	}

	@RequestMapping(value = "/getBatchProblem", method = RequestMethod.POST)
	@ApiOperation(value = "批次问题发生率接口", notes = "批次问题发生率接口")
	public ResultBean getBatchProblem(@RequestBody BlackDashSearch dashSearch){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getBatchProblem(dashSearch));
		return resultBean;
	}

	@RequestMapping(value = "/exportBatchProblem", method = RequestMethod.POST)
	@ApiOperation(value = "导出批次问题详情接口", notes = "导出批次问题详情接口")
	public void exportBatchProblem(HttpServletResponse response,HttpServletRequest request,@RequestBody BlackDashSearch dashSearch){
		blackDashBoardService.exportBatchProblem(response,request,dashSearch);
	}

	@RequestMapping(value = "/exportBatchProblemTotal", method = RequestMethod.POST)
	@ApiOperation(value = "导出批次问题汇总接口", notes = "导出批次问题汇总接口")
	public void exportBatchProblemTotal(HttpServletResponse response,HttpServletRequest request,@RequestBody BlackDashSearch dashSearch){
		blackDashBoardService.exportBatchProblemTotal(response,request,dashSearch);
	}

	//节假日维护接口
	@RequestMapping(value = "/saveHoliday", method = RequestMethod.POST)
	@ApiOperation(value = "保存节假日，补班信息", notes = "保存节假日，补班信息")
	public ResultBean saveHoliday(@RequestBody JSONObject jsonObject){
		blackDashBoardService.saveHoliday(jsonObject);
		return ResultBean.success();
	}

	@RequestMapping(value = "/updateHoliday", method = RequestMethod.POST)
	@ApiOperation(value = "修改节假日，补班信息", notes = "修改节假日，补班信息")
	public ResultBean updateHoliday(@RequestBody JSONObject jsonObject){
		blackDashBoardService.updateHoliday(jsonObject);
		return ResultBean.success();
	}

	@RequestMapping(value = "/deleteHoliday", method = RequestMethod.GET)
	@ApiOperation(value = "删除节假日，补班信息", notes = "删除节假日，补班信息")
	public ResultBean deleteHoliday(@RequestParam("id") String id){
		blackDashBoardService.deleteHoliday(id);
		return ResultBean.success();
	}


	@RequestMapping(value = "/getHoliday", method = RequestMethod.POST)
	@ApiOperation(value = "获取节假日，补班信息", notes = "获取节假日，补班信息")
	public ResultBean getHoliday(HttpServletRequest request,@RequestBody JSONObject jsonObject){
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		List<JSONObject> result = blackDashBoardService.getHoliday(page,size);
		Map<String,String> title = blackDashBoardService.getHolidayTitle(request);
		if (isNotNull(result)){

			return ResultBean.pagination(blackDashBoardService.getHolidayCount(), page, size, result,title);
		}else {
			return ResultBean.pagination(0L, page, size, result,title);
		}
	}
	//目标值维护接口
	/**
	 * @Author yuyangyang
	 * @Description 查询目标值接口
	 * @Date  2020/7/20  10:33
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/getTarget", method = RequestMethod.POST)
	@ApiOperation(value = "获取目标值信息", notes = "获取目标值信息")
	public ResultBean getTarget(HttpServletRequest request,@RequestBody QimsTargetSearch qimsTargetSearch) throws Exception {
		Integer page = qimsTargetSearch.getPage();
		Integer size = qimsTargetSearch.getSize();
		List<QimsTargetInfo> result = blackDashBoardService.getTarget(qimsTargetSearch);
		Map<String,String> title = blackDashBoardService.getTargetTitle(request);
		if (isNotNull(result)){
			return ResultBean.pagination(blackDashBoardService.getTargetCount(qimsTargetSearch), page, size, result,title);
		}else {
			return ResultBean.pagination(0L, page, size, result,title);
		}
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 导入目标值接口
	 * @Date  2020/7/20  10:34
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/importTarget", method = RequestMethod.POST)
	@ApiOperation(value = "导入目标值信息", notes = "导入目标值信息")
	public ResultBean importTarget(MultipartFile file,HttpServletRequest request) {
		ResultBean resultBean = new ResultBean();
		Boolean flag = blackDashBoardService.importTarget(file,request);
		if (flag) {
			resultBean.setMessage("导入成功");
		} else {
			resultBean.setCode(-1);
			resultBean.setMessage("导入失败");
		}
		return resultBean;
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 导出目标值模板接口
	 * @Date  2020/7/20  10:35
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/exportTarget", method = RequestMethod.POST)
	@ApiOperation(value = "导出目标值模板接口", notes = "导出目标值模板接口")
	public void exportTarget(HttpServletResponse response,HttpServletRequest request){
		blackDashBoardService.exportTarget(response,request);
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 新增目标值接口
	 * @Date  2020/7/20  10:36
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/saveTarget", method = RequestMethod.POST)
	@ApiOperation(value = "保存目标值信息", notes = "保存目标值信息")
	public ResultBean saveTarget(@RequestBody QimsTargetInfo qimsTargetInfo){
		blackDashBoardService.saveTarget(qimsTargetInfo);
		return ResultBean.success();
	}
	/**
	 * @Author yuyangyang
	 * @Description 修改目标值接口
	 * @Date  2020/7/20  10:36
	 * @Param
	 * @return
	 */

	@RequestMapping(value = "/updateTarget", method = RequestMethod.POST)
	@ApiOperation(value = "修改目标值信息", notes = "修改目标值信息")
	public ResultBean updateTarget(@RequestBody QimsTargetInfo qimsTargetInfo){
		blackDashBoardService.updateTarget(qimsTargetInfo);
		return ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description 删除目标值接口
	 * @Date  2020/7/20  10:37
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/deleteTarget", method = RequestMethod.GET)
	@ApiOperation(value = "删除目标值信息", notes = "删除目标值信息")
	public ResultBean deleteTarget(@RequestParam("id") String id){
		blackDashBoardService.deleteTarget(id);
		return ResultBean.success();
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 详细统计问题部门下拉值获取
	 * @Date  2020/7/21  15:35
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/getDepartmentValue", method = RequestMethod.GET)
	@ApiOperation(value = "获取部门的下拉值", notes = "获取部门的下拉值")
	public ResultBean getDepartmentValue(@RequestParam("type") String type){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getDepartmentValue(type));
		return resultBean;
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取详细问题的标准
	 * @Date  2020/7/21  19:14
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getStandardValue", method = RequestMethod.GET)
	@ApiOperation(value = "获取详细问题的标准", notes = "获取详细问题的标准")
	public ResultBean getStandardValue(@RequestParam("type") String type){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getStandardValue(type));
		return resultBean;
	}
    /**
     * @Author yuyangyang
     * @Description 修改详细问题的标准信息
     * @Date  2020/7/21  19:23
     * @Param
     * @return
     */
	@RequestMapping(value = "/updateStandardValue", method = RequestMethod.POST)
	@ApiOperation(value = "修改详细问题的标准", notes = "修改详细问题的标准")
	public ResultBean updateStandardValue(@RequestBody List<JSONObject> list){
		blackDashBoardService.updateStandardValue(list);
		return ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description  查询LT的标准信息
	 * @Date  2020/8/3  17:32
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getLtList", method = RequestMethod.POST)
	@ApiOperation(value = "查询LT的标准信息", notes = "查询LT的标准信息")
	public ResultBean getLtList(){
		ResultBean resultBean = new ResultBean();
		resultBean.setData(blackDashBoardService.getLtList());
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 修改LT的标准信息
	 * @Date  2020/8/3  17:33
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/updateLtList", method = RequestMethod.POST)
	@ApiOperation(value = "修改LT的标准信息", notes = "修改LT的标准信息")
	public ResultBean updateLtList(@RequestBody JSONObject jsonObject){
		blackDashBoardService.updateLtList(jsonObject);
		return ResultBean.success();
	}
}
