package cn.jwis.qualityworkflow.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/11 17:30
 */
@Service
public interface UserService {

	List<JSONObject> getUserList(String name,Integer page,Integer size,String type);

	Long getUserListCount(String name,String type);

	Map<String,String> getTitle(HttpServletRequest request);

	void  saveUserInfo(JSONObject jsonObject);

	void updateUserInfo(JSONObject jsonObject);

	void deleteUserInfo(String id);

	void exportUserInfoTemplate(HttpServletResponse response);

	Boolean importUserInfo(MultipartFile file,String type);

	void  exportUserInfo(JSONObject jsonObject,HttpServletResponse response);

	Set<String> getUserInfoByRole (List<String> list);

	Set<String> getUserListByDepartment(String department,String type);

	String getDepartment(String name,String type);

	JSONObject getSuperior(String name,String type);

	List<String> getDepartmentList(String type);

	String getSuperiorName(String name,String type);
}