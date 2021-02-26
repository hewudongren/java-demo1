package cn.jwis.qualityworkflow.modules.esd.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdTargetInfo;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/28 19:13
 */
@Service
public interface EsdDashBoardService {
	/**
	 * @Author yuyangyang
	 * @Description ESD定时任务
	 * @Date  2020/7/28  19:38
	 * @Param
	 * @return
	 */
	 void  getEsdData(String startTime,String endTime) throws IOException;

	 /**
	  * @Author yuyangyang
	  * @Description 新建目标值信息
	  * @Date  2020/9/7  13:53
	  * @Param
	  * @return
	  */
	 void  saveTargetInfo(EsdTargetInfo esdTargetInfo);

	 /**
	  * @Author yuyangyang
	  * @Description 修改目标值信息
	  * @Date  2020/9/7  13:53
	  * @Param
	  * @return
	  */
	 void  updateTargetInfo(EsdTargetInfo esdTargetInfo);

	 /**
	  * @Author yuyangyang
	  * @Description 删除目标值信息
	  * @Date  2020/9/7  13:54
	  * @Param
	  * @return
	  */
	 void  deleteTargetInfo(String id);
	 /**
	  * @Author yuyangyang
	  * @Description 查询目标值信息
	  * @Date  2020/9/7  13:54
	  * @Param
	  * @return
	  */
	  List<EsdTargetInfo> getTargetInfo(Integer page,Integer size,String startTime,String endTime);

      /**
       * @Author yuyangyang
       * @Description 查询目标值信息总数
       * @Date  2020/9/7  13:54
       * @Param
       * @return
       */
	  Long getTargetInfoCount(String startTime,String endTime);

	  /**
	   * @Author yuyangyang
	   * @Description 查询目标值信息表头
	   * @Date  2020/9/7  13:55
	   * @Param
	   * @return
	   */
	  Map<String,String> getTitle(HttpServletRequest request);

      /**
       * @Author yuyangyang
       * @Description 导出目标值信息模板
       * @Date  2020/9/7  13:55
       * @Param
       * @return
       */
	  void exportEsdInStanInfoTemplate(HttpServletResponse response);

	  /**
	   * @Author yuyangyang
	   * @Description 导入目标值信息
	   * @Date  2020/9/7  13:55
	   * @Param
	   * @return
	   */
	  Boolean importTargetInfo(MultipartFile file);

	  /**
	   * @Author yuyangyang
	   * @Description 查询ESD的DashBoard的图表信息
	   * @Date  2020/9/7  13:55
	   * @Param
	   * @return
	   */
	  JSONObject getEsdDashBoardInfo(JSONObject jsonObject) throws ParseException;

	  /**
	   * @Author yuyangyang
	   * @Description 查询邮件信息
	   * @Date  2020/9/7  13:55
	   * @Param
	   * @return
	   */
	   List<JSONObject> getEmailList(String category);

	   /**
	    * @Author yuyangyang
	    * @Description 修改邮件信息
	    * @Date  2020/9/7  13:56
	    * @Param
	    * @return
	    */
	   void updateEmailInfo(JSONObject jsonObject);
}