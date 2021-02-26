package cn.jwis.qualityworkflow.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/11 17:12
 */
@RestController
@RequestMapping(value = "/user")
@Api(description = "用户部门")
public class UserController extends BaseClass {

	@Autowired
	UserService userService;
	/**
	 * @Author yuyangyang
	 * @Description 查询用户部门信息
	 * @Date  2020/8/11  17:20
	 * @Param
	 * @return
	 */
	@PostMapping(value = "getUserList")
	@ApiOperation(value = "获取用户部门信息", notes = "获取用户部门信息")
	public ResultBean getUserList(@RequestBody JSONObject jsonObject, HttpServletRequest request){
		String name = jsonObject.getString("name");
		Integer page = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		String type = jsonObject.getString("type");
		List<JSONObject> list = userService.getUserList(name,page,size,type);
		Map<String,String> title = userService.getTitle(request);
		if (isNotNull(list)){
			return ResultBean.pagination( userService.getUserListCount(name,type), page, size, list,title);
		}else {
			return ResultBean.pagination(0L, page, size, list,title);
		}
	}


	/**
	 * @Author yuyangyang
	 * @Description 新增用户部门信息
	 * @Date  2020/8/11  17:20
	 * @Param
	 * @return
	 */
	@PostMapping(value = "saveUserInfo")
	@ApiOperation(value = "保存用户部门信息", notes = "保存用户部门信息")
	public ResultBean saveUserInfo(@RequestBody JSONObject jsonObject){
		userService.saveUserInfo(jsonObject);
		return ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description 修改用户部门信息
	 * @Date  2020/8/11  17:23
	 * @Param
	 * @return
	 */
	@PostMapping(value = "updateUserInfo")
	@ApiOperation(value = "修改用户部门信息", notes = "修改用户部门信息")
	public ResultBean updateUserInfo(@RequestBody JSONObject jsonObject){
		userService.updateUserInfo(jsonObject);
		return ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description 删除用户部门信息
	 * @Date  2020/8/11  17:23
	 * @Param
	 * @return
	 */
	@GetMapping(value = "deleteUserInfo")
	@ApiOperation(value = "删除用户部门信息", notes = "删除用户部门信息")
	public ResultBean deleteUserInfo(@RequestParam("id") String id){
		userService.deleteUserInfo(id);
		return ResultBean.success();
	}

	/**
	 * @Author yuyangyang
	 * @Description 导出用户部门模板接口
	 * @Date  2020/8/11  17:23
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/exportUserInfoTemplate", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "导出用户部门信息模板", notes = "导出用户部门信息模板")
	public void exportUserInfoTemplate(HttpServletResponse response) {
		userService.exportUserInfoTemplate(response);
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 导出用户部门信息
	 * @Date  2020/8/11  17:24
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/exportUserInfo", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导出用户部门信息", notes = "导出用户部门信息")
	public void exportUserInfo(HttpServletResponse response,@RequestBody JSONObject jsonObject) {
		userService.exportUserInfo(jsonObject,response);
	}

	
	/**
	 * @Author yuyangyang
	 * @Description 导入用户部门信息
	 * @Date  2020/8/11  17:26
	 * @Param 
	 * @return 
	 */
	@RequestMapping(value = "/importUserInfo", method = RequestMethod.POST)
	@ApiOperation(value = "导入用户部门信息", notes = "导入用户部门信息")
	public ResultBean importUserInfo(MultipartFile file,String type) {
		ResultBean resultBean = new ResultBean();
		Boolean flag = userService.importUserInfo(file,type);
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
	 * @Description 通过角色名称获取用户账号列表
	 * @Date   11:10
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getUserInfoByRole", method = RequestMethod.POST)
	@ApiOperation(value = "通过角色名称获取用户账号列表", notes = "通过角色名称获取用户账号列表")
	public ResultBean getUserInfoByRole(@RequestBody List<String> list) {
		ResultBean resultBean = new ResultBean();
		Set<String> result = userService.getUserInfoByRole(list);
		resultBean.setData(result);
		return resultBean;
	}
	/**
	 * @Author yuyangyang
	 * @Description 通过部门带出普通用户(QIMS)
	 * @Date  2020/8/13  14:36
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getUserListByDepartment",method = RequestMethod.GET)
	@ApiOperation(value = "通过部门带出普通用户",notes = "通过部门带出普通用户")
	public ResultBean getUserListByDepartment(@RequestParam("department") String department,@RequestParam("type")String type){
		ResultBean resultBean = new ResultBean();
		Set<String> result = userService.getUserListByDepartment(department,type);
		resultBean.setData(result);
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 通过用户带出上级信息
	 * @Date  2020/8/14  11:22
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getSuperior",method = RequestMethod.GET)
	@ApiOperation(value = "通过用户带出上级用户部门信息",notes = "通过用户带出上级用户部门信息")
	public ResultBean getSuperior(@RequestParam("name") String name,@RequestParam("type")String type){
		ResultBean resultBean = new ResultBean();
		JSONObject result  = userService.getSuperior(name,type);
		resultBean.setData(result);
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 部门下拉值
	 * @Date  2020/8/17  13:48
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getDepartmentList",method = RequestMethod.GET)
	@ApiOperation(value = "获取部门的下拉值",notes = "获取部门的下拉值")
	public ResultBean getDepartmentList(@RequestParam("type")String type){
		ResultBean resultBean = new ResultBean();
		List<String> result  = userService.getDepartmentList(type);
		resultBean.setData(result);
		return resultBean;
	}

	/**
	 * @Author yuyangyang
	 * @Description 通过用户带出上级用户信息
	 * @Date  2020/8/17  14:10
	 * @Param
	 * @return
	 */
	@RequestMapping(value = "/getSuperiorName",method = RequestMethod.GET)
	@ApiOperation(value = "通过用户带出上级用户信息",notes = "通过用户带出上级用户信息")
	public ResultBean getSuperiorName(@RequestParam("name") String name,@RequestParam("type")String type){
		ResultBean resultBean = new ResultBean();
		String superiorName  = userService.getSuperiorName(name,type);
		resultBean.setData(superiorName);
		return resultBean;
	}

}