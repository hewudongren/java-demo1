package cn.jwis.qualityworkflow.modules.qims.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.qims.bean.BlackDashSearch;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetSearch;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:14
 */
@Service
public interface BlackDashBoardService {

	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板新增,已处理,关闭数量(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	JSONObject getAllAndClose(JSONObject jsonObject);

	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板超期数量(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	JSONObject getOverdue(JSONObject jsonObject) throws ParseException;

	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板关闭和及时关闭趋势图(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	JSONObject getDashBoardClose(JSONObject jsonObject);

	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板关闭与未关闭数量(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	JSONObject getDashBoardNotClose(JSONObject jsonObject);

	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板累计关闭数，关闭率接口(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	JSONObject getAllData();

	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录(不及时处理柏拉图)(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	JSONObject getUnseasonalBola(String type);

	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录(超期未回复列表)(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	List<QimsBlackInfo> getUnseasonalList(Integer page,Integer size);

	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录总数(超期未回复列表)(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	Long getUnseasonalCount();

	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录表头(超期未回复列表)(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle(HttpServletRequest request);

    /**
     * @Author yuyangyang
     * @Description 详细统计表头查询接口(黑色问题)
     * @Date  2020/9/7  14:16
     * @Param
     * @return
     */
	Map<String,String> getDetailsTitle(HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description 导出超期未回复列表(黑色问题)
	 * @Date  2020/9/7  14:16
	 * @Param
	 * @return
	 */
	void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description 详细统计查询接口(黑色问题)
	 * @Date  2020/9/7  14:17
	 * @Param
	 * @return
	 */
	 List<JSONObject> getBlackDetails(JSONObject jsonObject);

	 /**
	  * @Author yuyangyang
	  * @Description 详细统计导出接口(黑色问题)
	  * @Date  2020/9/7  14:17
	  * @Param
	  * @return
	  */
	 void exportBlackDetails(HttpServletRequest request,HttpServletResponse response,JSONObject jsonObject);

	 /**
	  * @Author yuyangyang
	  * @Description 批次问题发生率接口(黑色问题)
	  * @Date  2020/9/7  14:17
	  * @Param
	  * @return
	  */
	 JSONObject getBatchProblem(BlackDashSearch dashSearch);

	 /**
	  * @Author yuyangyang
	  * @Description 导出批次问题详情接口(黑色问题)
	  * @Date  2020/9/7  14:17
	  * @Param
	  * @return
	  */
	 void exportBatchProblem(HttpServletResponse response,HttpServletRequest request,BlackDashSearch dashSearch);

	 /**
	  * @Author yuyangyang
	  * @Description 导出批次问题汇总接口(黑色问题)
	  * @Date  2020/9/7  14:17
	  * @Param
	  * @return
	  */
	 void exportBatchProblemTotal(HttpServletResponse response,HttpServletRequest request,BlackDashSearch dashSearch);

	 /**
	  * @Author yuyangyang
	  * @Description 保存节假日，补班信息
	  * @Date  2020/9/7  14:17
	  * @Param
	  * @return
	  */
	 void saveHoliday(JSONObject jsonObject);

	 /**
	  * @Author yuyangyang
	  * @Description 修改节假日，补班信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 void updateHoliday(JSONObject jsonObject);

     /**
      * @Author yuyangyang
      * @Description 删除节假日，补班信息
      * @Date  2020/9/7  14:18
      * @Param
      * @return
      */
	 void deleteHoliday(String id);

     /**
      * @Author yuyangyang
      * @Description 获取节假日，补班信息
      * @Date  2020/9/7  14:18
      * @Param
      * @return
      */
	 List<JSONObject> getHoliday(Integer page,Integer size);

	 /**
	  * @Author yuyangyang
	  * @Description 获取节假日，补班信息的表头信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 Map<String,String> getHolidayTitle(HttpServletRequest request);

	 /**
	  * @Author yuyangyang
	  * @Description 获取节假日，补班信息总数
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 Long getHolidayCount();

	 /**
	  * @Author yuyangyang
	  * @Description 保存目标值信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 void saveTarget(QimsTargetInfo qimsTargetInfo);

	 /**
	  * @Author yuyangyang
	  * @Description 修改目标值信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 void updateTarget(QimsTargetInfo qimsTargetInfo);

	 /**
	  * @Author yuyangyang
	  * @Description 获取目标值信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 List<QimsTargetInfo> getTarget(QimsTargetSearch qimsTargetSearch) throws Exception;

	 /**
	  * @Author yuyangyang
	  * @Description 获取目标值信息总数
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 Long getTargetCount(QimsTargetSearch qimsTargetSearch);

	 /**
	  * @Author yuyangyang
	  * @Description 获取目标值信息表头
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 Map<String,String> getTargetTitle(HttpServletRequest request);

	 /**
	  * @Author yuyangyang
	  * @Description 导出目标值模板接口
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 void  exportTarget(HttpServletResponse response,HttpServletRequest request);

	 /**
	  * @Author yuyangyang
	  * @Description 删除目标值信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 void deleteTarget(String id);

	 /**
	  * @Author yuyangyang
	  * @Description 导入目标值信息
	  * @Date  2020/9/7  14:18
	  * @Param
	  * @return
	  */
	 Boolean importTarget(MultipartFile file,HttpServletRequest request);

	 /**
	  * @Author yuyangyang
	  * @Description 导出累计未关闭记录(不及时处理柏拉图)(黑色问题)
	  * @Date  2020/9/7  14:19
	  * @Param
	  * @return
	  */
	 void  exportUnseasonalBola(String type,HttpServletResponse response,HttpServletRequest request);

	 /**
	  * @Author yuyangyang
	  * @Description 获取部门的下拉值
	  * @Date  2020/9/7  14:19
	  * @Param
	  * @return
	  */
	 List<String> getDepartmentValue(String type);

	 /**
	  * @Author yuyangyang
	  * @Description 获取详细问题的标准(黑色问题)
	  * @Date  2020/9/7  14:19
	  * @Param
	  * @return
	  */
	 JSONObject getStandardValue(String type);

	 /**
	  * @Author yuyangyang
	  * @Description 修改详细问题的标准(黑色问题)
	  * @Date  2020/9/7  14:19
	  * @Param
	  * @return
	  */
	 void  updateStandardValue(List<JSONObject> list);

	 /**
	  * @Author yuyangyang
	  * @Description 查询LT的标准信息
	  * @Date  2020/9/7  14:19
	  * @Param
	  * @return
	  */
	 List<JSONObject> getLtList();

	 /**
	  * @Author yuyangyang
	  * @Description 修改LT的标准信息
	  * @Date  2020/9/7  14:19
	  * @Param
	  * @return
	  */
	 void updateLtList(JSONObject jsonObject);
}