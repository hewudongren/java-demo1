package cn.jwis.qualityworkflow.modules.esd.service;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSamplingLevelInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/9 11:30
 */
@Service
public interface EsdMaintainService {
	/**
	 * @Author yuyangyang
	 * @Description //保存ESD检验标准维护信息
	 * @Date  2020/5/9  11:03
	 * @Param
	 * @return
	 */
	void savaEsdInStanInfo(EsdInStanInfo esdInStanInfo);

	/**
	 * @Author yuyangyang
	 * @Description 修改ESD检验标准维护信息
	 * @Date  2020/5/9  14:03
	 * @Param
	 * @return
	 */
	void updateEsdInStanInfo(EsdInStanInfo esdInStanInfo);
	/**
	 * @Author yuyangyang
	 * @Description 删除ESD检验标准维护信息
	 * @Date  2020/5/9  14:08
	 * @Param
	 * @return
	 */
	void  removeEsdInStanInfo(String id);

	/**
	 * @Author yuyangyang
	 * @Description 查询ESD检验标注维护信息
	 * @Date  2020/5/9  14:23
	 * @Param
	 * @return
	 */
	List<EsdInStanInfo> findEsdInStanInfo(EsdInStanSearch esdInStanSearch);

    /**
    * @Author yuyangyang
    * @Description 导出ESD检验标准维护信息模板
    * @Date  2020/5/9  15:04
    * @Param
    * @return
    */
	void exportEsdInStanInfoTemplate(HttpServletResponse response,String category,String fileName);

	/**
	 * @Author yuyangyang
	 * @Description 导出ESD检验标准维护信息
	 * @Date  2020/5/9  15:20
	 * @Param
	 * @return
	 */
	void exportEsdInStanInfo(EsdInStanSearch esdInStanSearch,HttpServletResponse response);
	/**
	 * @Author yuyangyang
	 * @Description 导入ESD检验标准维护信息
	 * @Date  2020/5/9  15:46
	 * @Param
	 * @return
	 */
	Boolean importEsdInStanInfoWithId(MultipartFile file, HttpServletRequest request,String category);
    /**
     * @Author yuyangyang
     * @Description 保存ESD抽样水准维护信息
     * @Date  2020/5/9  17:05
     * @Param
     * @return
     */
	void savaEsdSamplingLevelInfo(EsdSamplingLevelInfo esdSamplingLevelInfo);
	/**
	 * @Author yuyangyang
	 * @Description 修改ESD抽样水准维护信息
	 * @Date  2020/5/9  17:05
	 * @Param
	 * @return
	 */
	void updateEsdSamplingLevelInfo(EsdSamplingLevelInfo esdSamplingLevelInfo);
	/**
	 * @Author yuyangyang
	 * @Description 删除ESD抽样水准维护信息
	 * @Date  2020/5/9  17:05
	 * @Param
	 * @return
	 */
	void  removeEsdSamplingLevelInfo(String id);
	/**
	 * @Author yuyangyang
	 * @Description 查询ESD抽样水准维护信息
	 * @Date  2020/5/9  17:05
	 * @Param
	 * @return
	 */
	List<EsdSamplingLevelInfo> findEsdSamplingLevelInfo(EsdInStanSearch esdInStanSearch);
	/**
	 * @Author yuyangyang
	 * @Description 导出ESD抽样水准维护信息模板
	 * @Date  2020/5/9  17:05
	 * @Param
	 * @return
	 */
	void exportEsdSamplingLevelInfoTemplate(HttpServletResponse response,String fileName,String category);
	/**
	 * @Author yuyangyang
	 * @Description 导出ESD抽样水准维护信息
	 * @Date  2020/5/9  17:05
	 * @Param
	 * @return
	 */
	void exportEsdSamplingLevelInfo(EsdInStanSearch esdInStanSearch,HttpServletResponse response);
	/**
	 * @Author yuyangyang
	 * @Description 导入ESD抽样水准维护信息
	 * @Date  2020/5/9  17:05
	 * @Param
	 * @return
	 */
	Boolean importEsdSamplingLevelInfoWithId(MultipartFile file, HttpServletRequest request,String category);

	/**
	 * @Author yuyangyang
	 * @Description 获取页面展示的表头信息
	 * @Date  2020/7/14  15:17
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle(HttpServletRequest request,String category);
	/**
	 * @Author yuyangyang
	 * @Description 获取页面展示的表头信息(抽样水准)
	 * @Date  2020/7/14  17:43
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle2(HttpServletRequest request,String category);
}