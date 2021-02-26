package cn.jwis.qualityworkflow.modules.apqp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenance;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelCascadeVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelListVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-22 17:41
 * @since 0.1.0
 **/
public interface ModelMaintenanceService {
	/**
	 * @description: 获取机型维护列表
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:43
	 * @param bean:
	 * @return: com.github.pagehelper.PageInfo<cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenance>
	 **/
	PageInfo<ApqpModelMaintenance> getInfoList(QueryModelListVo bean) throws Exception;

	/**
	 * @description:  新增机型维护
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:43
	 * @param bean:
	 * @return: void
	 **/
	void insert(ApqpModelMaintenance bean) throws Exception;

	/**
	 * @description: 更新机型维护
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:44
	 * @param bean:
	 * @return: void
	 **/
	void update(ApqpModelMaintenance bean) throws Exception;

	/**
	 * @description: 删除机型维护
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:44
	 * @param id:
	 * @return: void
	 **/
	void delete(String id) throws Exception;

	/**
	 * @description: //获取下拉值
	 * @author: xujinbiao
	 * @date: 2020/5/25 10:17
	 * @param parameter:
	 * @return: java.util.List<java.lang.Object>
	 **/
	List<Object> getPullDownValue(String parameter) throws Exception;

	/**
	 * @description: 下载机型维护模板
	 * @author: xujinbiao
	 * @date: 2020/5/19 14:07
	 * @param response:
	 * @param request:
	 * @return: void
	 **/
	void exportTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception;

	/**
	 * @description: 导入机型维护信息，并校验机型唯一性
	 * @author: xujinbiao
	 * @date: 2020/5/25 11:29
	 * @param file:
	 * @param request:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	void importWithCheck(MultipartFile file, HttpServletRequest request) throws Exception;

	/**
	 * @description: //获取上一代机型
	 * @author: xujinbiao
	 * @date: 2020/5/25 15:23
	 * @param model:
	 * @return: java.lang.String
	 **/
	String getPreviousProduct(String model) throws Exception;

	/**
	 * @description: 获取级联下拉值
	 * @author: xujinbiao
	 * @date: 2020/5/25 16:36
	 * @param bean:
	 * @return: java.util.List<java.lang.String>
	 **/
	List<String> getCascadeValue(QueryModelCascadeVo bean) throws Exception;
}
