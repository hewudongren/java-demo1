package cn.jwis.qualityworkflow.modules.qims.service;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaSearch;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public interface QimsCqaService {
 
	/**
	 * @Author yuyangyang
	 * @Description 创建QIMSCQA问题单据
	 * @Date  2020/9/7  14:01
	 * @Param 
	 * @return 
	 */
	void saveQimsCqaInfo(QimsCqaInfo qimsCqaInfo);
    
	/**
	 * @Author yuyangyang
	 * @Description QIMSCQA问题流程处理，审批
	 * @Date  2020/9/7  14:01
	 * @Param 
	 * @return 
	 */
	void confirm(JSONObject bean);
    
	/**
	 * @Author yuyangyang
	 * @Description QIMSCQA问题下拉值获取
	 * @Date  2020/9/7  14:02
	 * @Param 
	 * @return 
	 */
	List<String> getDropdownValue(String parameter);
    
	/**
	 * @Author yuyangyang
	 * @Description 查询QIMSCQA问题列表
	 * @Date  2020/9/7  14:02
	 * @Param 
	 * @return 
	 */
	List<QimsCqaInfo> getQimsCqaInfoList(QimsCqaSearch qimsCqaSearch);
    
	/**
	 * @Author yuyangyang
	 * @Description 查询QIMSCQA问题列表总数
	 * @Date  2020/9/7  14:02
	 * @Param 
	 * @return 
	 */
	Long getQimsCqaInfoListCount(QimsCqaSearch qimsCqaSearch);
    
	/**
	 * @Author yuyangyang
	 * @Description 导出QIMSCQA问题列表
	 * @Date  2020/9/7  14:02
	 * @Param 
	 * @return 
	 */
	void exportQimsCqaInfoList(HttpServletResponse response, HttpServletRequest request, QimsCqaSearch qimsCqaSearch);
    
	/**
	 * @Author yuyangyang
	 * @Description 获取QIMSCQA问题检验详情信息
	 * @Date  2020/9/7  14:02
	 * @Param 
	 * @return 
	 */
	Map<String, Object> getQimsCqaInfo(QueryDetailedInfoVo bean) throws Exception;

	/**
	 * @Author yuyangyang
	 * @Description 查询QIMSCQA问题列表表头
	 * @Date  2020/9/7  14:02
	 * @Param
	 * @return
	 */
	Map<String, String> getTitle(HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description CQA流程中人，法，环的下拉值获取
	 * @Date  2020/9/7  14:02
	 * @Param
	 * @return
	 */
	List<String> getDropValue(String parameter);

	/**
	 * @Author yuyangyang
	 * @Description 获取上级领导
	 * @Date  2020/9/7  14:02
	 * @Param
	 * @return
	 */
	String getSuperior(String name);

	/**
	 * @Author yuyangyang
	 * @Description 导出CQA问题8D报告
	 * @Date  2020/9/7  14:03
	 * @Param
	 * @return
	 */
	void exportCqa8dInfo(HttpServletResponse response,String id) throws Exception;

}