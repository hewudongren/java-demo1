package cn.jwis.qualityworkflow.service.imp;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.dao.UserMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.service.UserService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.UserUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.jwis.qualityworkflow.util.Title.UserDepartmentDb;
import static cn.jwis.qualityworkflow.util.Title.UserDepartmentExcel;
import static cn.jwis.qualityworkflow.util.Title.UserDepartmentExcelUs;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/11 17:30
 */
@Service
public class UserServiceImpl extends BaseClass implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	IDGeneratorRunner idGeneratorRunner;
	@Autowired
	CommonMapper commonMapper;
    @Autowired
	UserServer userServer;

	@Override
	public List<JSONObject> getUserList(String name, Integer page, Integer size,String type) {
		if (isNotNull(page) && isNotNull(size)){
			page = (page-1)*size;
		}
		if (isNotNull(name)){
			name = "%"+name+"%";
		}
		return userMapper.getUserList(name,page,size,type);
	}

	@Override
	public Long getUserListCount(String name,String type) {
		if (isNotNull(name)){
			name = "%"+name+"%";
		}
		return userMapper.getUserListCount(name,type);
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request,UserDepartmentDb,UserDepartmentExcel,UserDepartmentExcelUs);
	}

	@Override
	public void saveUserInfo(JSONObject jsonObject) {
		checkRule(jsonObject);
		String id = String.valueOf(idGeneratorRunner.nextId());
		jsonObject.put("id",id);
		jsonObject.put("creator",getCurrentUserName());
		List<JSONObject> list = JSONObjectUtil.separate(jsonObject);
		commonMapper.saveDate(list,"department_mapping");
	}

	@Override
	public void updateUserInfo(JSONObject jsonObject) {
		checkRule(jsonObject);
		jsonObject.put("lastUpdatePerson", UserUtil.getCurrentUserName());
		List<JSONObject> list = JSONObjectUtil.separate(jsonObject);
		String id = jsonObject.getString("id");
		commonMapper.update(list,"department_mapping",id);
	}

	@Override
	public void deleteUserInfo(String id) {
		userMapper.deleteUserInfo(id);
	}

	@Override
	public void exportUserInfoTemplate(HttpServletResponse response) {
		ExcelUtil.setResponseHeader(response,"UserInfoTemplate.xlsx");
		String[] Title = UserDepartmentExcel;
		Workbook workbook = ExcelUtil.exporSimple(null,Title,null);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public Boolean importUserInfo(MultipartFile file,String type) {
		Boolean flag = false;
		File file1 = null;
		try {
			file1 = FileUtil.multipartFileToFile(file);
			List<String> title = new ArrayList<>();
			String[] strings = UserDepartmentExcel;
			title = Arrays.asList(UserDepartmentDb);
			List<JSONObject> list = ExcelUtil.readExcel(file1, title,strings);
			if (isNull(list)) {
				throw new BaseException("请勿导入空表");
			}
			for (JSONObject temp : list) {
				temp.put("type",type);
				saveUserInfo(temp);
				flag = true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		} finally {
			FileUtil.deleteDir(file1);
		}
		return flag;
	}

	@Override
	public void exportUserInfo(JSONObject jsonObject, HttpServletResponse response) {
		String name = jsonObject.getString("name");
		String type = jsonObject.getString("type");
		List<JSONObject> result  = userMapper.getUserList(name,null,null,type);
		ExcelUtil.setResponseHeader(response,"UserInfo.xlsx");
		Workbook workbook = ExcelUtil.exporSimple(result,UserDepartmentExcel,UserDepartmentDb);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<String> getUserInfoByRole(List<String> list) {
		Set<String> result = new HashSet<>();
		for (String str:list) {
			String oid = userServer.getOidByRole(str);
            result.addAll(userServer.getUserListByOid(oid));
		}
		return result;
	}

	@Override
	public Set<String> getUserListByDepartment(String department, String type) {
		return userMapper.getUserListByDepartment(department,type);
	}

	@Override
	public String getDepartment(String name, String type) {
		return  userMapper.getDepartment(name,type);
	}

	@Override
	public JSONObject getSuperior(String name,String type) {
		if (isNull(name)){
			name = getCurrentUserName();
		}
		JSONObject result = new JSONObject();
		String superior = userMapper.getSuperior(name,type);
		String department = userMapper.getDepartment(superior,type);
		if (isNotNull(department)){
				result.put("department",department);
		}
		if (isNotNull(superior)){
				result.put("superior",superior);
		}
		return result;
	}

	@Override
	public List<String> getDepartmentList(String type) {
		return userMapper.getDepartmentList(type);
	}

	@Override
	public String getSuperiorName(String name, String type) {
		return userMapper.getSuperior(name,type);
	}


	/**
     * @Author yuyangyang
     * @Description //TODO 
     * @Date  2020/8/11  18:43
     * @Param 
     * @return 
     */
	private  void checkRule(JSONObject jsonObject){
		String id = jsonObject.getString("id");
		String userAccount = jsonObject.getString("user_account");
		String type = jsonObject.getString("type");
		Long count = 0L;
		if (isNull(id)){
			count = userMapper.getCountByName(userAccount,type);
			if (count >0 ){
				throw new BaseException("请勿创建重复的用户数据");
			}
		}else {
			count = userMapper.getCountByNameAndId(userAccount,type,id);
			if (count >0 ){
				throw new BaseException("请勿创建重复的类型用户数据");
			}
		}
	}
}