package cn.jwis.qualityworkflow.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Set;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/11 17:31
 */
@Mapper
public interface UserMapper {

	List<JSONObject> getUserList(@Param("name") String name,@Param("page") Integer page,
								 @Param("size") Integer size,@Param("type") String type);


	Long getUserListCount(@Param("name") String name,@Param("type") String type);

	@Select("select count(1) from department_mapping where user_account = #{name} and type =#{type}")
	Long getCountByName(@Param("name") String name,@Param("type")String type);

	@Select("select count(1) from department_mapping where user_account = #{userAccount} and type =#{type} and id != #{id}")
	Long getCountByNameAndId(@Param("userAccount") String userAccount,@Param("type")String type,@Param("id")String id);

	@Delete("delete from department_mapping where id =#{id}")
	void  deleteUserInfo(@Param("id") String id);


	//通过用户账号获取上级主管账户
	@Select("select superior from department_mapping where user_account = #{name} and type = #{type} limit 0,1")
	String getSuperior(@Param("name") String name,@Param("type") String type);

	//通过用户账号获取上级经理账户
	@Select("select superior_manager from department_mapping where user_account = #{name} and type = #{type} limit 0,1")
	String getSuperiorManagerByUser(@Param("name") String name,@Param("type") String type);

	//通过部门带出普通用户的账号信息
	@Select("select DISTINCT user_account from department_mapping where department = #{department} and  type = #{type} and user_account is not null and position is null ")
	Set<String> getUserListByDepartment(@Param("department") String department,@Param("type") String type);

	//通过用户带出部门信息
	@Select("select department from department_mapping where user_account = #{name} and  type = #{type}")
	String getDepartment(@Param("name")String name,@Param("type")String type);

	//获取部门的下拉值信息
	@Select("select DISTINCT department from department_mapping where  type = #{type} and department is not null")
	List<String> getDepartmentList(@Param("type") String type);
}