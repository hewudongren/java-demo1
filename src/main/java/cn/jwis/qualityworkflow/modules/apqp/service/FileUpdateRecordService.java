package cn.jwis.qualityworkflow.modules.apqp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateFileUpdateRecord;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryFileUpdateVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-14 10:53
 * @since 0.1.0
 **/
public interface FileUpdateRecordService {
	/**
	 * 新增接口
	 * @param vo
	 * @throws Exception
	 */
	void insert(CreateFileUpdateRecord vo) throws Exception;

	/**
	 * 分页查询接口
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	PageInfo<ApqpFileUpdateRecord> getList(QueryFileUpdateVo vo) throws Exception;

	/**
	 * 导出接口
	 * @param response
	 * @param request
	 * @param vo
	 * @throws Exception
	 */
	void exportInfo(HttpServletResponse response, HttpServletRequest request, QueryFileUpdateVo vo) throws Exception;
}
