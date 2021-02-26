package cn.jwis.qualityworkflow.modules.esd.service;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialSearch;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/8 16:31
 */
@Service
public interface EsdSpecialInfoService {
    /**
     * @Author yuyangyang
     * @Description 创建ESD来料检验单
     * @Date  2020/9/7  13:56
     * @Param
     * @return
     */
	void  saveEsdSpecialInfo(EsdSpecialInfo esdSpecialInfo);

	/**
	 * @Author yuyangyang
	 * @Description ESD特采流程处理，审批
	 * @Date  2020/9/7  13:56
	 * @Param
	 * @return
	 */
	void  confirm(JSONObject bean);

	/**
	 * @Author yuyangyang
	 * @Description ESD来料单检验管理下拉值获取
	 * @Date  2020/9/7  13:56
	 * @Param
	 * @return
	 */
	List<String> getDropdownValue(String parameter);

	/**
	 * @Author yuyangyang
	 * @Description 查询ESD来料检验列表
	 * @Date  2020/9/7  13:57
	 * @Param
	 * @return
	 */
	List<EsdSpecialInfo> getEsdSpecialList(EsdSpecialSearch esdSpecialSearch);

    /**
     * @Author yuyangyang
     * @Description 查询ESD来料检验列表总数
     * @Date  2020/9/7  13:57
     * @Param
     * @return
     */
	Long getEsdSpecialListCount(EsdSpecialSearch esdSpecialSearch);

	/**
	 * @Author yuyangyang
	 * @Description 查询ESD来料检验列表表头
	 * @Date  2020/9/7  13:57
	 * @Param
	 * @return
	 */
	Map<String, String> getTitle(HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description 导出ESD来料检验列表
	 * @Date  2020/9/7  13:57
	 * @Param
	 * @return
	 */
	void exportEsdSpecialList(EsdSpecialSearch esdSpecialSearch, HttpServletRequest request, HttpServletResponse response);

	/**
	 * @Author yuyangyang
	 * @Description ESD来料检验详情接口
	 * @Date  2020/9/7  13:57
	 * @Param
	 * @return
	 */
	Map<String, Object> getEsdSpecialDetails(QueryDetailedInfoVo bean) throws Exception;
}