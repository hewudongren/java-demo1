package cn.jwis.qualityworkflow.modules.qims.service;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemSearch;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/25 17:14
 */
@Service
public interface GrayProblemService {
    /**
     * @Author yuyangyang
     * @Description 保存灰色问题
     * @Date  2020/5/25  19:46
     * @Param
     * @return
     */
	void saveGrayProblem(GrayProblemInfo grayProblemInfo);
    /**
     * @Author yuyangyang
     * @Description 提交灰色问题
     * @Date  2020/5/25  19:47
     * @Param
     * @return
     */
	void confirmGrayProblem(GrayProblemInfo grayProblemInfo);

	/**
	 * @Author yuyangyang
	 * @Description 暂存灰色问题
	 * @Date  2020/5/25  20:12
	 * @Param
	 * @return
	 */
	void temporaryGrayProblem(JSONObject grayProblemInfo,String type);

	/**
	 * @Author yuyangyang
	 * @Description 获取灰色问题查询下拉值
	 * @Date  2020/5/26  9:59
	 * @Param
	 * @return
	 */
	List<String> getDropdownValue(String parameter);
    /**
     * @Author yuyangyang
     * @Description 查询灰色问题列表
     * @Date  2020/5/26  10:40
     * @Param
     * @return
     */
	ResultBean getGrayProblemList(GrayProblemSearch grayProblemSearch,HttpServletRequest request);
	/**
	 * @Author yuyangyang
	 * @Description 导出灰色问题信息
	 * @Date  2020/5/26  13:53
	 * @Param
	 * @return
	 */
	void exportGrayProblemList(GrayProblemSearch grayProblemSearch, HttpServletRequest request, HttpServletResponse response);
    /**
     * @Author yuyangyang
     * @Description 获取灰色问题详情信息
     * @Date  2020/5/26  13:55
     * @Param
     * @return
     */
	JSONObject getGrayProblemDetails(String id);
}