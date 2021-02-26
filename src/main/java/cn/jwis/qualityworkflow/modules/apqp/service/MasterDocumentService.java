package cn.jwis.qualityworkflow.modules.apqp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.modules.apqp.bean.CreateMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryMasterListVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-25 17:46
 * @since 0.1.0
 **/
public interface MasterDocumentService {


	/**
	 * @description: 获取主单据列表信息
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:35
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	PageInfo<JSONObject> getInfoList(QueryMasterListVo bean) throws Exception;

	/**
	 * @description: 新增主单据信息
	 * @date: 2020/5/26 15:11
	 * @param bean:
	 * @return: void
	 **/
	void insert(@RequestBody CreateMasterDocument bean) throws Exception;

	/**
	 * 获取主单据详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	JSONObject getMasterDocument(String id) throws Exception;

	/**
	 * @description: 根据参数获取下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 10:59
	 * @param parameter:
	 * @return: java.util.List<java.lang.String>
	 **/
	List<Object> getPullDownValue(String parameter) throws Exception;

	/**
	 * @description: 导出APQP主单据
	 * @author: xujinbiao
	 * @date: 2020/5/29 14:57
	 * @param response:
	 * @param request:
	 * @param vo:
	 * @return: void
	 **/
	void exportInfo(HttpServletResponse response, HttpServletRequest request,  QueryMasterListVo vo) throws Exception;
}
