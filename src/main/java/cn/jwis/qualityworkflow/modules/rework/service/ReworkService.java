package cn.jwis.qualityworkflow.modules.rework.service;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryPieChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkApplyVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkInfoVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryTrentChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkMoStatus;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-15 15:07
 * @since 0.1.0
 **/
public interface ReworkService {
	/**
	 *
	 * @Description: 获取返工申请列表信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	PageInfo<JSONObject> getApplyInfo(QueryReworkApplyVo bean) throws Exception;

	/**
	 * @description: 导出返工申请记录
	 * @author: xujinbiao
	 * @date: 2020/4/28 16:27
	 * @param response:
	 * @param request:
	 * @param vo:
	 * @return: void
	 **/
	void exportApplyInfo(HttpServletResponse response, HttpServletRequest request, QueryReworkApplyVo vo) throws Exception;

	/**
	 * @description: 根据参数获取下拉值接口
	 * @author: xujinbiao
	 * @date: 2020/4/30 10:59
	 * @param parameter:
	 * @return: java.util.List<java.lang.String>
	 **/
	List<Object> getPullDownValue(String parameter) throws Exception;


	/**
	 *
	 * @Description: 流程处理，审批
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	void confirm(ConfirmVo bean) throws Exception;

	/**
	 *
	 * @Description: 获取详情信息
	 * @author longjun
	 * @date 2018年8月31日
	 * @param bean
	 * @return
	 */
	Map<String, Object> getDetailednessInfo(QueryDetailedInfoVo bean) throws Exception;

	/**
	 * @description: 下载返工单信息模板
	 * @author: xujinbiao
	 * @date: 2020/5/19 14:09
	 * @param response:
	 * @param request:
	 * @return: void
	 **/
	void exportReworkTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception;

	/**
	 * @description: 获取返工信息列表
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:14
	 * @param bean:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	PageInfo<ReworkInfo> getReworkList(@RequestBody QueryReworkInfoVo bean) throws Exception;

	/**
	 * @description: 导入返工信息
	 * @author: xujinbiao
	 * @date: 2020/5/19 16:24
	 * @param file:
	 * @param request:
	 * @return: void
	 **/
	void importReworkInfo(MultipartFile file, HttpServletRequest request) throws Exception;

	/**
	 * @description: 导出Rework列表
	 * @author: xujinbiao
	 * @date: 2020/5/19 17:44
	 * @param response:
	 * @param request:
	 * @param bean:
	 * @return: void
	 **/
	void exportReworkList(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryReworkInfoVo bean) throws Exception;

	/**
	 * @description: 获取返工数量趋势图
	 * @author: xujinbiao
	 * @date: 2020/5/19 17:45
	 * @param vo:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	JSONObject getReworkQuantityChart(QueryTrentChartVo vo) throws Exception;

	/**
	 * @description: 获取返工数量饼图
	 * @author: xujinbiao
	 * @date: 2020/5/19 17:53
	 * @param vo:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	JSONObject getPieChart(QueryPieChartVo vo) throws Exception;

	/**
	 * @description: 根据返工MO(排程号) 导出 维修明细表数据(t_maintenance_detail )数据
	 * @author: xujinbiao
	 * @date: 2020/5/18 11:14
	 * @param reworkMO: 返工MO(排程号)
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	void exportMaintenanceDetail(HttpServletResponse response, HttpServletRequest request, String reworkMo)throws Exception;

	/**
	 * 解冻对应的Mo单据
	 * @param id
	 * @throws Exception
	 */
	void thawReworkMo(String id) throws Exception;

	/**
	 * 根据reworkId  获取 ReworkMoStatus列表
	 * @param reworkId
	 * @return
	 * @throws Exception
	 */
	List<ReworkMoStatus> getReworkMoStatusList(String reworkId) throws Exception;
}
