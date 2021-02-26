package cn.jwis.qualityworkflow.modules.ecn.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryExternalDocumentVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 外来文件接口定义
 * @create 2020-05-07 09:28
 * @since 0.1.0
 **/
public interface ExternalDocumentService {
	/**
	 *
	 * @Description: 获取外来文件列表信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	PageInfo<JSONObject> getList(QueryExternalDocumentVo bean) throws Exception;

	/**
	 * @description: 导出外来文件记录
	 * @author: xujinbiao
	 * @date: 2020/4/28 16:27
	 * @param response:
	 * @param request:
	 * @param vo:
	 * @return: void
	 **/
	void exportInfo(HttpServletResponse response, HttpServletRequest request, QueryExternalDocumentVo vo) throws Exception;

	/**
	 * @description: 根据参数获取下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 10:59
	 * @param parameter:
	 * @return: java.util.List<java.lang.String>
	 **/
	List<Object> getPullDownValue(String parameter) throws Exception;


	/**
	 *
	 * @Description: 流程处理，审批
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	void confirm(ConfirmVo bean) throws Exception;

	/**
	 *
	 * @Description: 获取ECN流程详细详情信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	Map<String, Object> getDetailednessInfo(QueryDetailedInfoVo bean) throws Exception;
}
