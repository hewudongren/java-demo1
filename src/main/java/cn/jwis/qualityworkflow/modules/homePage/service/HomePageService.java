package cn.jwis.qualityworkflow.modules.homePage.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/6 15:51
 */
@Service
public interface HomePageService {
    /**
     * @Author yuyangyang
     * @Description 首页获取整体流程的处理，超期信息接口
     * @Date  2020/7/7  14:38
     * @Param
     * @return
     */
	JSONObject  getAllProcessStatus(JSONObject bean) throws Exception;

	/**
	 * @Author yuyangyang
	 * @Description 首页获取不同流程未关闭的流程信息
	 * @Date  2020/7/7  15:59
	 * @Param
	 * @return
	 */
	JSONObject getProcessStatusByName(String name, HttpServletRequest request);
	/**
	 * @Author yuyangyang
	 * @Description 获取R2的总体KPI报表信息
	 * @Date  2020/7/10  17:40
	 * @Param
	 * @return
	 */
	JSONObject getKpiReport(String modelCategory) throws ParseException;
}