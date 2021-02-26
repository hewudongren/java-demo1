package cn.jwis.qualityworkflow.modules.qims.service;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackSearch;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/19 17:49
 */
@Service
public interface QimsBlackService {
     /**
      * @Author yuyangyang
      * @Description 流程提交
      * @Date  2020/5/19  18:12
      * @Param
      * @return
      */
	 void confirm(JSONObject bean);

	 /**
	  * @Author yuyangyang
	  * @Description 保存QIMS单据
	  * @Date  2020/5/19  18:12
	  * @Param
	  * @return
	  */
	 void saveQimsBlackInfo(QimsBlackInfo bean);

	 /**
	  * @Author yuyangyang
	  * @Description 查询QIMS黑色问题列表
	  * @Date  2020/5/22  20:14
	  * @Param
	  * @return
	  */
	 List<QimsBlackInfo> getQimsBlackInfoList(QimsBlackSearch qimsBlackSearch);

	 /**
	  * @Author yuyangyang
	  * @Description 查询QIMS黑色问题列表总数
	  * @Date  2020/5/22  20:14
	  * @Param
	  * @return
	  */
	 Long getQimsBlackInfoListCount(QimsBlackSearch qimsBlackSearch);
     /**
      * @Author yuyangyang
      * @Description 导出QIMS黑色问题集合
      * @Date  2020/5/22  20:45
      * @Param
      * @return
      */
	 void exportQimsBlackInfoList(HttpServletResponse response, HttpServletRequest request,QimsBlackSearch qimsBlackSearch);
	  /**
	   * @Author yuyangyang
	   * @Description 获取QIMS黑色问题详情
	   * @Date  2020/5/25  10:10
	   * @Param
	   * @return
	   */
	 Map<String, Object> getQimsBlackInfo(QueryDetailedInfoVo bean) throws Exception;
	 /**
	  * @Author yuyangyang
	  * @Description QIMS黑色问题下拉值
	  * @Date  2020/5/25  15:07
	  * @Param
	  * @return
	  */
	 List<String> getDropdownValue(String parameter);

	 /**
	  * @Author yuyangyang
	  * @Description QIMS黑色问题新增时分段下拉值
	  * @Date  2020/6/20  10:11
	  * @Param
	  * @return
	  */
	 List<String> getSubsection();

     /**
      * @Author yuyangyang
      * @Description QIMS黑色问题新增时通过分段获取下拉值
      * @Date  2020/6/20  10:17
      * @Param
      * @return
      */
	 List<String> getValueBySubsection(JSONObject bean);
     
	 /**
	  * @Author yuyangyang
	  * @Description QIMS黑色问题新增时机型下拉值
	  * @Date  2020/6/20  10:43
	  * @Param 
	  * @return 
	  */
	 List<String> getModel();
     /**
      * @Author yuyangyang
      * @Description QIMS黑色问题新增时部门下拉值
      * @Date  2020/6/20  10:57
      * @Param
      * @return
      */
	 List<String> getDepartment();

	 /**
	  * @Author yuyangyang
	  * @Description QIMS黑色问题新增是通过部门带出用户接口
	  * @Date  2020/6/20  11:09
	  * @Param
	  * @return
	  */
	 List<String> getNameByDepartment(String parameter);

	 /**
	  * @Author yuyangyang
	  * @Description 获取系统所有用户的名字
	  * @Date  2020/6/29  14:25
	  * @Param
	  * @return
	  */
	 Set<String> getUserName();

	 /**
	  * @Author yuyangyang
	  * @Description 获取用户上级信息和部门
	  * @Date  2020/7/21  15:08
	  * @Param
	  * @return
	  */
	 JSONObject getSuperior();

	 /**
	  * @Author yuyangyang
	  * @Description 段别带出线体下拉值
	  * @Date  2020/9/7  14:04
	  * @Param
	  * @return
	  */
	 List<String> getLineValue(JSONObject paragraph);

	 /**
	  * @Author yuyangyang
	  * @Description 段别和线体带出异常站点
	  * @Date  2020/9/7  14:04
	  * @Param
	  * @return
	  */
	 List<String> getSiteValue(JSONObject jsonObject);

	 /**
	  * @Author yuyangyang
	  * @Description 导出黑色问题8D报告
	  * @Date  2020/9/7  14:04
	  * @Param
	  * @return
	  */
	 void exportBlack8dInfo(HttpServletResponse response,String id) throws Exception;
}