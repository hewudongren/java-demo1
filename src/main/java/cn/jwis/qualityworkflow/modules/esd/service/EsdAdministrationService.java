package cn.jwis.qualityworkflow.modules.esd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInfo;

/**
 * @Description ESD检验单管理
 * @Author yuyangyang
 * @Date 2020/5/15 10:46
 */
@Service
public interface EsdAdministrationService {

    /**
     * @Author yuyangyang
     * @Description ESD检验单管理样品名称下拉值接口
     * @Date  2020/5/15  14:36
     * @Param
     * @return
     */
	List<String> getDropdownValue(String parameter);
	/**
	 * @Author yuyangyang
	 * @Description ESD检验单管理查询接口
	 * @Date  2020/5/15  15:33
	 * @Param
	 * @return
	 */
	ResultBean findEsdAdministration(JSONObject jsonObject);
    /**
     * @Author yuyangyang
     * @Description 导出ESD检验单管理
     * @Date  2020/5/15  17:17
     * @Param
     * @return
     */
	void exportEsdAdministration(JSONObject jsonObject, HttpServletResponse response);
	/**
	 * @Author yuyangyang
	 * @Description 新增页面通过样品名获取表格表头
	 * @Date  2020/5/15  17:19
	 * @Param
	 * @return
	 */
	Map<String,Object> findTableNameBySampleName(String sampleName);

    /**
     * @Author yuyangyang
     * @Description 保存ESD检验单信息
     * @Date  2020/5/15  18:04
     * @Param
     * @return
     */
	void saveEsdAdministrationInfo(JSONObject jsonObject);

	/**
	 * @Author yuyangyang
	 * @Description 提示是否继续添加
	 * @Date  2020/9/7  13:45
	 * @Param
	 * @return
	 */
	Integer isAdd(String sampleName,String detectionDate);

	/**
	 * @Author yuyangyang
	 * @Description 通过ID进行追加页面
	 * @Date  2020/9/7  13:51
	 * @Param
	 * @return
	 */
	EsdInfo getEsdInfoById(String id);

	/**
	 * @Author yuyangyang
	 * @Description 通过ID进入详情页面
	 * @Date  2020/9/7  13:52
	 * @Param
	 * @return
	 */
	ResultBean getEsdDetailInfoByEsdId(JSONObject jsonObject);

	/**
	 * @Author yuyangyang
	 * @Description esd复检
	 * @Date  2020/9/7  13:52
	 * @Param
	 * @return
	 */
	void esdRecheck(JSONObject jsonObject);

	/**
	 * @Author yuyangyang
	 * @Description 通过ID进入ESD复检页面
	 * @Date  2020/9/7  13:52
	 * @Param
	 * @return
	 */
	JSONObject getEsdDetailInfoById(String id);

	/**
	 * @Author yuyangyang
	 * @Description FE维修接口
	 * @Date  2020/9/7  13:52
	 * @Param
	 * @return
	 */
	void feMaintain(JSONObject jsonObject);

}