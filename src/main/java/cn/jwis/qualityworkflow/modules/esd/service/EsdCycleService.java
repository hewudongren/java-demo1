package cn.jwis.qualityworkflow.modules.esd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdCycleSearch;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/11 14:32
 */
@Service
public interface EsdCycleService {
	/**
	 * @Author yuyangyang
	 * @Description  流程处理，审批
	 * @Date  2020/5/11  14:40
	 * @Param
	 * @return
	 */
	void confirm(JSONObject bean);
    /**
     * @Author yuyangyang
     * @Description 创建ESD周期性检验流程单据
     * @Date  2020/5/11  15:12
     * @Param
     * @return
     */
	 void saveEsdCycleInfo(EsdCycleInfo bean);
     /**
      * @Author yuyangyang
      * @Description ESD周期性检验下拉值获取
      * @Date  2020/5/13  10:29
      * @Param
      * @return
      */
	 List<String> getDropdownValue(String parameter);
	 /**
	  * @Author yuyangyang
	  * @Description 查询ESD周期性检验列表
	  * @Date  2020/5/13  11:35
	  * @Param
	  * @return
	  */
	 List<EsdCycleInfo> getEsdCycleList(EsdCycleSearch esdCycleSearch);
	 /**
	  * @Author yuyangyang
	  * @Description 查询ESD周期性检验列表总数
	  * @Date  2020/5/13  13:48
	  * @Param
	  * @return
	  */
	 Long getEsdCycleListCount(EsdCycleSearch esdCycleSearch);
	 /**
	  * @Author yuyangyang
	  * @Description 导出ESD周期性检验列表
	  * @Date  2020/5/13  15:07
	  * @Param
	  * @return
	  */
	 void exportEsdCycleList(HttpServletResponse response,EsdCycleSearch esdCycleSearch);
     /**
      * @Author yuyangyang
      * @Description 获取ESD周期性检验详情信息
      * @Date  2020/5/13  17:12
      * @Param
      * @return
      */
	 Map<String, Object> getEsdCycleInfo(QueryDetailedInfoVo bean) throws Exception;
     /**
      * @Author yuyangyang
      * @Description 通过样品名带出检验月份
      * @Date  2020/7/15  18:49
      * @Param
      * @return
      */
	 List<String> getMonthByName(String name);
	 /**
	  * @Author yuyangyang
	  * @Description 通过样品名和检验月份带出基本信息
	  * @Date  2020/7/15  18:55
	  * @Param
	  * @return
	  */
	 JSONObject getInfoByMonthAndName(String sampleName,String detectionMonth);
	 /**
	  * @Author yuyangyang
	  * @Description 获取表头
	  * @Date  2020/7/16  17:24
	  * @Param
	  * @return
	  */
	 Map<String,String> getTitle(HttpServletRequest request);

    List<String> getMonthsByName(String name);
}