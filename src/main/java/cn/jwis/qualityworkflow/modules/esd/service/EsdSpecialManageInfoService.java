package cn.jwis.qualityworkflow.modules.esd.service;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpeciaManageSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialManageInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/16 16:33
 */
@Service
public interface EsdSpecialManageInfoService {
	/**
	 * @Author yuyangyang
	 * @Description 来料检验管理界面下拉值接口
	 * @Date  2020/6/16  18:51
	 * @Param
	 * @return
	 */
	List<String> getDropdownValue(String parameter);

    /**
     * @Author yuyangyang
     * @Description 来料检验管理界面查询接口
     * @Date  2020/6/17  10:53
     * @Param
     * @return
     */
	List<JSONObject> findSpecialManageInfo(EsdSpeciaManageSearch esdSpeciaManageSearch);


	/**
	 * @Author yuyangyang
	 * @Description 来料检验管理界面查询接口总数
	 * @Date  2020/6/17  10:57
	 * @Param
	 * @return
	 */
	Long findSpecialManageCount(EsdSpeciaManageSearch esdSpeciaManageSearch);

	/**
	 * @Author yuyangyang
	 * @Description 来料检验管理界面表头信息
	 * @Date  2020/6/17  11:01
	 * @Param
	 * @return
	 */
	Map<String,String> getTitle(HttpServletRequest request);

	/**
	 * @Author yuyangyang
	 * @Description 导出ESD来料检验单管理
	 * @Date  2020/6/17  13:52
	 * @Param
	 * @return
	 */
	void exportSpecialManageInfo(EsdSpeciaManageSearch bean, HttpServletResponse response, HttpServletRequest request);

    /**
     * @Author yuyangyang
     * @Description 来料检验新增界面机型下拉值接口
     * @Date  2020/6/17  14:17
     * @Param
     * @return
     */
	List<String> getModelList();

	/**
	 * @Author yuyangyang
	 * @Description 通过样品名获取比率和抽样数量
	 * @Date  2020/6/17  14:54
	 * @Param
	 * @return
	 */
	JSONObject getRateAndSum(JSONObject bean);

	/**
	 * @Author yuyangyang
	 * @Description 通过选择的样品名获取表头的信息(带出范围信息)
	 * @Date  2020/6/17  15:20
	 * @Param
	 * @return
	 */
	Map<String, Object> findTableNameBySampleName(String sampleName);

    /**
     * @Author yuyangyang
     * @Description 保存ESD来料检验单信息
     * @Date  2020/6/17  15:26
     * @Param
     * @return
     */
	void  saveEsdSpecialManageInfo(EsdSpecialManageInfo esdSpecialManageInfo);

	/**
	 * @Author yuyangyang
	 * @Description 处理ESD来料检验单信息
	 * @Date  2020/6/17  16:24
	 * @Param
	 * @return
	 */
	void handleEsdSpecialManageInfo(EsdSpecialManageInfo esdSpecialManageInfo);
	/**
	 * @Author yuyangyang
	 * @Description 通过id获取ESD来料检验单详情信息
	 * @Date  2020/6/17  16:42
	 * @Param
	 * @return
	 */
	JSONObject findEsdSpecialManageDetail(String id);
}