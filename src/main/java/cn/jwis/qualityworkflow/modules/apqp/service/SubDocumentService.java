package cn.jwis.qualityworkflow.modules.apqp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDocumentInfo;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.QuerySubDocumentListVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.UpdateDocumentInfoVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 子单据接口
 * @create 2020-05-26 17:04
 * @since 0.1.0
 **/
public interface SubDocumentService {
	/**
	 * @description: 批量创建子单据流程
	 * @author: xujinbiao
	 * @date: 2020/5/26 17:06
	 * @param vo:
	 * @return: void
	 **/
	void batchInsert(CreateMasterDocument vo, String apqpNumber, String masterId) throws Exception;

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
	 * @description: 根据参数获取下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 10:59
	 * @param parameter:
	 * @return: java.util.List<java.lang.String>
	 **/
	List<Object> getPullDownValue(String parameter) throws Exception;

	/**
	 * @description: 获取子单据列表信息
	 * @author: xujinbiao
	 * @date: 2020/5/29 15:46
	 * @param vo:
	 * @return: com.github.pagehelper.PageInfo<cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument>
	 **/
	PageInfo<JSONObject> getInfoList(QuerySubDocumentListVo vo) throws Exception;

	/**
	 * @description: 导出APQP子单据
	 * @author: xujinbiao
	 * @date: 2020/5/29 14:57
	 * @param response:
	 * @param request:
	 * @param vo:
	 * @return: void
	 **/
	void exportInfo(HttpServletResponse response, HttpServletRequest request, QuerySubDocumentListVo vo) throws Exception;


	/**
	 *
	 * @Description: 获取APQP子单据流程详细详情信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception;

	/**
	 * @description: 更新上传附件的信息
	 * @author: xujinbiao
	 * @date: 2020/6/4 11:32
	 * @param bean:
	 * @return: void
	 **/
	void confirmDocument(List<UpdateDocumentInfoVo> list);

	List<ApqpDocumentInfo> getDocumentByIdAndType(String id, String type) throws SQLException;
}
