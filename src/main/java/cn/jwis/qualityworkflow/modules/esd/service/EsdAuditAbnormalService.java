package cn.jwis.qualityworkflow.modules.esd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormal;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditAbnormalSearch;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/2 19:54
 */
@Service
public interface EsdAuditAbnormalService {
	/**
	 * @Author yuyangyang
	 * @Description ESD稽核异常流程提交接口
	 * @Date  2020/7/2  19:59
	 * @Param
	 * @return
	 */
	void confirm(JSONObject bean);

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核异常流程新建接口
	 * @Date  2020/7/2  19:59
	 * @Param
	 * @return
	 */
	void  saveEsdAuditAbnormalInfo(EsdAuditAbnormal bean);

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核下拉值
	 * @Date  2020/7/3  16:31
	 * @Param
	 * @return
	 */
	List<String> getDropdownValue(String parameter);

	/**
	 * @Author yuyangyang
	 * @Description 查询ESD稽核异常列表
	 * @Date  2020/7/3  17:39
	 * @Param
	 * @return
	 */
	List<EsdAuditAbnormal>  getEsdAuditAbnormalInfoList(EsdAuditAbnormalSearch esdAuditAbnormalSearch);

	/**
	 * @Author yuyangyang
	 * @Description 查询ESD稽核异常列表总数
	 * @Date  2020/7/3  17:39
	 * @Param
	 * @return
	 */
	Long getEsdAuditAbnormalInfoCount(EsdAuditAbnormalSearch esdAuditAbnormalSearch);

	/**
	 * @Author yuyangyang
	 * @Description 导出ESD稽核异常列表
	 * @Date  2020/7/3  17:39
	 * @Param
	 * @return
	 */
	void  exportEsdAuditAbnormalInfoList(HttpServletResponse response, HttpServletRequest request,EsdAuditAbnormalSearch esdAuditAbnormalSearch);

	/**
	 * @Author yuyangyang
	 * @Description 查询ESD稽核异常列表表头
	 * @Date  2020/7/3  17:39
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle(HttpServletRequest request);

    /**
     * @Author yuyangyang
     * @Description 获取ESD稽核详情信息
     * @Date  2020/7/3  18:06
     * @Param
     * @return
     */
	Map<String, Object> getEsdAuditInfo(QueryDetailedInfoVo bean) throws Exception;
}