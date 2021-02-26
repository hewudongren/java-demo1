package cn.jwis.qualityworkflow.modules.apqp.service;

import java.util.Date;
import java.util.List;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpPfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpQcChartRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreatePfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateQcChartRiskItem;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-30 14:27
 * @since 0.1.0
 **/
public interface RiskItemService {

	/**
	 * @description: 新增Dvt1高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/29 17:55
	 * @param vo:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	void insertDvt1RiskList(CreateDvt1RiskItem vo) throws Exception;



	/**
	 * @description: 获取Dvt1上一代机型高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/30 16:12
	 * @param previousProduct:
	 * @param engPhase:
	 * @return: java.util.List<cn.jwis.qualityworkflow.modules.apqp.bean.ApqpRiskItem>
	 **/
	List<ApqpDvt1RiskItem> getDvt1PreviousModelRiskList(String previousProduct, String engPhase, Date updateTime) throws Exception;

	/**
	 * @description: 新增PFMEA高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/29 17:55
	 * @param vo:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	void insertPfmeaRiskList(CreatePfmeaRiskItem vo) throws Exception;



	/**
	 * @description: 获取Dvt1上一代机型高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/30 16:12
	 * @param previousProduct:
	 * @param engPhase:
	 * @return: java.util.List<cn.jwis.qualityworkflow.modules.apqp.bean.ApqpRiskItem>
	 **/
	List<ApqpPfmeaRiskItem> getPfmeaPreviousModelRiskList(String previousProduct, String engPhase, Date updateTime) throws Exception;

	/**
	 * @description: 新增Dvt1高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/29 17:55
	 * @param vo:
	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
	 **/
	void insertQcChartRiskList(CreateQcChartRiskItem vo) throws Exception;



	/**
	 * @description: 获取Dvt1上一代机型高风险项
	 * @author: xujinbiao
	 * @date: 2020/5/30 16:12
	 * @param previousProduct:
	 * @param engPhase:
	 * @return: java.util.List<cn.jwis.qualityworkflow.modules.apqp.bean.ApqpRiskItem>
	 **/
	List<ApqpQcChartRiskItem> getQcChartPreviousModelRiskList(String previousProduct, String engPhase, Date updateTime) throws Exception;

}
