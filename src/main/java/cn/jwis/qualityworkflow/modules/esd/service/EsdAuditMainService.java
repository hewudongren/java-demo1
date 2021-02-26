package cn.jwis.qualityworkflow.modules.esd.service;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdAudiMainSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListSecondary;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSave;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSecondary;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/1 16:32
 */
@Service
public interface EsdAuditMainService {
    /**
     * @Author yuyangyang
     * @Description ESD稽核模板管理查询下拉值获取
     * @Date  2020/7/1  16:59
     * @Param
     * @return
     */
	List<String> getDropdownValue(String parameter);

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核模板管理查询接口
	 * @Date  2020/7/1  16:59
	 * @Param
	 * @return
	 */
	List<EsdAuditMain> getEsdAudiMainList(EsdAudiMainSearch esdAudiMainSearch) throws Exception;

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核模板管理查询页面展示的表头
	 * @Date  2020/7/1  17:00
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle(HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核模板下载接口
	 * @Date  2020/7/1  17:46
	 * @Param
	 * @return
	 */
	void exportEsdAdministration(HttpServletResponse response);

	/**
	 * @Author yuyangyang
	 * @Description 保存稽核模板的数据
	 * @Date  2020/7/1  18:25
	 * @Param
	 * @return
	 */
	void  saveEsdAudit(EsdAuditSave esdAuditSave);
	/**
	 * @Author yuyangyang
	 * @Description 获取稽核模板的详情数据
	 * @Date  2020/7/1  19:42
	 * @Param
	 * @return
	 */
	EsdAuditSave getEsdAuditDetails(String id);

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单管理查询页面下拉值接口
	 * @Date  2020/7/2  11:31
	 * @Param
	 * @return
	 */
	List<String> getDownValueList(String parameter);

   /**
    * @Author yuyangyang
    * @Description ESD稽核单据管理查询接口
    * @Date  2020/7/2  14:27
    * @Param
    * @return
    */
	List<EsdAuditListMain> getEsdAuditList(EsdAuditSearch esdAuditSearch) throws Exception;

	/**
	 * @Author yuyangyang
	 * @Description 获取稽核单管理的表头
	 * @Date  2020/7/2  14:27
	 * @Param
	 * @return
	 */
	Map<String,String> getAuditTitle(HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description 导出稽核单管理信息
	 * @Date  2020/7/2  15:20
	 * @Param
	 * @return
	 */
	void  exportEsdAuditList(HttpServletResponse response,HttpServletRequest request,EsdAuditSearch esdAuditSearch);
	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核页面文件名称的下拉值
	 * @Date  2020/7/2  15:47
	 * @Param
	 * @return
	 */
	List<String> getDocumentNameList(String parameter);
	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核页面文件名称带出文件编号
	 * @Date  2020/7/2  15:49
	 * @Param
	 * @return
	 */
	List<String> getDocumentNumberListByName(String documentName);

	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核页面通过文件编号带出详情信息
	 * @Date  2020/7/2  16:02
	 * @Param
	 * @return
	 */
	List<EsdAuditSecondary> getDetailsByNumber(String documentNumber);

	/**
	 * @Author yuyangyang
	 * @Description 新增ESD稽核管理单
	 * @Date  2020/7/2  17:19
	 * @Param
	 * @return
	 */
	void  saveEsdAuditList(JSONObject jsonObject);
	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单详情接口
	 * @Date  2020/7/2  18:02
	 * @Param
	 * @return
	 */
	JSONObject getEsdAuditListDetails(String id);

	/**
	 * @Author yuyangyang
	 * @Description ESD稽核单修改接口
	 * @Date  2020/7/2  18:16
	 * @Param
	 * @return
	 */
	void  updateEsdAuditListSecondary(EsdAuditListSecondary esdAuditListSecondary);

	/**
	 * @Author yuyangyang
	 * @Description 删除ESD稽核单模板
	 * @Date  2020/7/14  15:31
	 * @Param
	 * @return
	 */
	void removeEsdAudit(String id);

	/**
	 * @Author yuyangyang
	 * @Description 删除ESD稽核单信息
	 * @Date  2020/7/14  15:40
	 * @Param
	 * @return
	 */
	void  removeEsdAuditList(String id);

	/**
	 * @Author yuyangyang
	 * @Description 获取通过ID获取副表的详细数据
	 * @Date  2020/9/7  13:53
	 * @Param
	 * @return
	 */
	JSONObject getEsdAuditListSecondary(String id);
}