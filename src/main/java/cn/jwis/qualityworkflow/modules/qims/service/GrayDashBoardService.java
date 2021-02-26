package cn.jwis.qualityworkflow.modules.qims.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;


/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/29 12:14
 */
@Service
public interface GrayDashBoardService {

	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板新增,已处理,关闭数量(灰色问题)
	 * @Date  2020/9/7  14:05
	 * @Param
	 * @return
	 */
	JSONObject getAllAndClose(JSONObject jsonObject);
	
    /**
     * @Author yuyangyang
     * @Description DashBoard看板超期数量(灰色问题)
     * @Date  2020/9/7  14:05
     * @Param
     * @return
     */
	JSONObject getOverdue(JSONObject jsonObject) throws ParseException;
    
	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板关闭和及时关闭趋势图(灰色问题)
	 * @Date  2020/9/7  14:06
	 * @Param 
	 * @return 
	 */
	JSONObject getDashBoardClose(JSONObject jsonObject);
    
	/**
	 * @Author yuyangyang
	 * @Description DashBoard看板关闭与未关闭数量(灰色问题)
	 * @Date  2020/9/7  14:06
	 * @Param 
	 * @return 
	 */
	JSONObject getDashBoardNotClose(JSONObject jsonObject);
	
    /**
     * @Author yuyangyang
     * @Description DashBoard看板累计关闭数，关闭率接口(灰色问题)
     * @Date  2020/9/7  14:06
     * @Param 
     * @return 
     */
	JSONObject getAllData();
    
	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录(不及时处理柏拉图)(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	JSONObject getUnseasonalBola(String type);
	
	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录(超期未回复列表)(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	List<JSONObject> getUnseasonalList(Integer page, Integer size);
    
	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录总数(超期未回复列表)(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	Long getUnseasonalCount();
    
	/**
	 * @Author yuyangyang
	 * @Description 累计未关闭记录表头(超期未回复列表)(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	Map<String,String> getTitle(HttpServletRequest request);
    
	/**
	 * @Author yuyangyang
	 * @Description 导出超期未回复列表(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	void exportUnseasonalList(HttpServletResponse response, HttpServletRequest request);
    
	/**
	 * @Author yuyangyang
	 * @Description 详细统计表头查询接口(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	Map<String,String> getDetailsTitle(HttpServletRequest request);
	
	/**
	 * @Author yuyangyang
	 * @Description 详细统计查询接口(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	List<JSONObject> getDetails(JSONObject jsonObject);
    
	/**
	 * @Author yuyangyang
	 * @Description 详细统计导出接口(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	void exportDetails(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject);
    
	/**
	 * @Author yuyangyang
	 * @Description 导出累计未关闭记录(不及时处理柏拉图)(灰色问题)
	 * @Date  2020/9/7  14:07
	 * @Param 
	 * @return 
	 */
	void  exportUnseasonalBola(String type,HttpServletResponse response,HttpServletRequest request);
}