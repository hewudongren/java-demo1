package cn.jwis.qualityworkflow.modules.qims.util;

import static cn.jwis.qualityworkflow.util.DateUtil.getTl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/2 14:25
 */
public class QimsUtil {

	/**
	 * @Author yuyangyang
	 * @Description SQL中查询出来的结果封装
	 * @Date  2020/5/29  17:32
	 * @Param
	 * @return
	 */
	public static JSONObject getMap(List<JSONObject> list, String key, String value){
		JSONObject result = new JSONObject();
		for (JSONObject json:list) {
			result.put(json.getString(key),json.getIntValue(value));
		}
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description SQL中查询出来的结果封装(关闭与未关闭数量柱状图)
	 * @Date  2020/5/29  18:17
	 * @Param
	 * @return
	 */
	public static JSONObject getMap1(List<JSONObject> list,String key,String value,String typeValue){
		JSONObject result = new JSONObject();
		for (JSONObject json:list) {
			String type = json.getString("type");
			if (typeValue.equals(type)){
				result.put(json.getString(key),json.getIntValue(value));
			}
		}
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description 黑色问题看板关闭率和及时关闭率趋势图
	 * @Date  2020/5/29  17:36
	 * @Param
	 * @return
	 */
	public static JSONObject getChartData(List<JSONObject> dashBoardCloseSum, List<JSONObject> dashBoardCloseMolecule, Set<String> betweenRuleDateDay){
		JSONObject dashBoardCloseSumMap = getMap(dashBoardCloseSum,"abscissa","sum");
		Set<String> keySet = dashBoardCloseSumMap.keySet();
		JSONObject dashBoardCloseMoleculeMap = getMap(dashBoardCloseMolecule,"abscissa","sum");
		JSONObject dashBoardCloseMoleculeMap2 = getMap(dashBoardCloseMolecule,"abscissa","molecule");
		JSONObject chartData = new JSONObject();
		JSONObject chartData1 = new JSONObject();
		JSONObject chartData2 = new JSONObject();
		for (String date:betweenRuleDateDay) {
			chartData1.put(date,"-");
			chartData2.put(date,"-");
			if (keySet.contains(date) ){
				Integer integer1 = dashBoardCloseMoleculeMap.getInteger(date);
				Integer integer2 = dashBoardCloseMoleculeMap2.getInteger(date);
				Integer integer = dashBoardCloseSumMap.getInteger(date);
				chartData1.put(date,getRate(integer1,integer));
				chartData2.put(date,getRate(integer2, integer));
			}
		}
		chartData.put("closureRate",chartData1);
		chartData.put("timelyClosingRate",chartData2);
		return chartData;
	}
	/**
	 * @Author yuyangyang
	 * @Description 关闭率和及时关闭率的添加
	 * @Date  2020/7/22  11:06
	 * @Param
	 * @return
	 */
	public static JSONObject getTargetDate(JSONObject chartData,Set<String> betweenRuleDateDay,String target,String target2){
		if (StringUtils.isEmpty(target)){
			target = "-";
		}
		if (StringUtils.isEmpty(target2)){
			target2 = "-";
		}
		JSONObject closureJson =  new JSONObject();
		JSONObject timelyClosingJson = new JSONObject();
		for (String str:betweenRuleDateDay) {
              closureJson.put(str,target);
			  timelyClosingJson.put(str,target2);
		}
		chartData.put("closureTarget",closureJson);
		chartData.put("timelyClosingTarget",timelyClosingJson);
		return chartData;
	}
	/**
	 * @Author yuyangyang
	 * @Description 黑色问题看板关闭与未关闭数量柱状图封装
	 * @Date  2020/5/29  18:14
	 * @Param
	 * @return
	 */
	public static JSONObject getChartData(List<JSONObject> dashBoardNotClose,Set<String> betweenRuleDateDay){
		JSONObject result = new JSONObject();
		JSONObject closeMap = getMap1(dashBoardNotClose, "abscissa", "sum", "Close");
		Set<String> closeSet = closeMap.keySet();
		JSONObject openMap = getMap1(dashBoardNotClose, "abscissa", "sum", "Processing");
		Set<String> openSet = openMap.keySet();
		JSONObject chartData1 = new JSONObject();
		JSONObject chartData2 = new JSONObject();
		Integer a = 0;
		for (String date:betweenRuleDateDay) {
			chartData1.put(date,0);
			if (closeSet.contains(date) ){
				Integer integer1 = closeMap.getInteger(date);
				chartData1.put(date,integer1);
			}
			if (openSet.contains(date)){
				Integer integer1 = openMap.getInteger(date);
				a = a+integer1;
			}
			chartData2.put(date,a);
		}
		result.put("closeDate",chartData1);
		result.put("processingDate",chartData2);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 除法百分比计算
	 * @Date  2020/5/30  17:24
	 * @Param
	 * @return
	 */
	public static Float getRate(Integer failuresNumber, Integer inspectionQty){
		if (failuresNumber == null || failuresNumber == 0 ){
			return  0F;
		}else if (inspectionQty == null || inspectionQty == 0){
			return  null;
		}
		BigDecimal a = new BigDecimal(failuresNumber);
		BigDecimal b = new BigDecimal(inspectionQty);
		Float result = (a.multiply(new BigDecimal("100")).divide(b,2,BigDecimal.ROUND_HALF_DOWN)).floatValue();
		return result;
	}
   /**
    * @Author yuyangyang
    * @Description 详情分页的处理
    * @Date  2020/6/2  16:16
    * @Param
    * @return
    */
	public  static List<JSONObject> getData(List<JSONObject> blackDetails,Integer page,Integer size,int temp ) {
		Integer pageNum = 0;
		Integer sizeNum = 0;
		List<JSONObject> result = new ArrayList<>();
		if ( page != null&& size != null){
				pageNum = (page - 1) * size;
				sizeNum = page * size;
				if (sizeNum > temp) {
					sizeNum = temp;
				}
				result = blackDetails.subList(pageNum, sizeNum);
		}
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 判断是否符合标准
	 * @Date  2020/6/1  10:54
	 * @Param
	 * @return
	 */
	public static boolean isAdd(Float tl,Float standard){
		boolean flag = false;
		if (tl <= standard){
			flag =true;
		}
		return flag;
	}
	/**
	 * @Author yuyangyang
	 * @Description 累加记录
	 * @Date  2020/6/1  11:42
	 * @Param
	 * @return
	 */
	public static JSONObject accumulation(Integer totalOfCa, Integer ca, Date createDate, Date updateDate, Float standardOfCh, Integer passOfCa, Integer pca,List<String> holidayList,List<String> workOvertimeList){
		JSONObject jsonObject = new JSONObject();
		totalOfCa++;
		ca++;
		String tl = getTl(createDate,updateDate,holidayList,workOvertimeList);
		Float aFloat = Float.valueOf(tl);
		if (isAdd(aFloat,standardOfCh)){
			passOfCa++;
			pca++;
		}
		jsonObject.put("totalOfSum",totalOfCa);
		jsonObject.put("sum",ca);
		jsonObject.put("totalOfPass",passOfCa);
		jsonObject.put("pass",pca);
		return jsonObject;
	}
	/**
	 * @Author yuyangyang
	 * @Description 不及时处理柏拉图的封装方式
	 * @Date  2020/7/27  13:50
	 * @Param 
	 * @return 
	 */
	public static  void getBolaData(JSONObject result,List<JSONObject> list){
		Set<String> abscissaSet = new LinkedHashSet<>();
		JSONObject chartData = new JSONObject();
		Integer total = 0;
		for (JSONObject temp:list) {
			Integer sum = temp.getInteger("sum");
			total = total + sum;
		}
		Integer mo = 0;
		for (JSONObject temp:list) {
			JSONObject jsonObject = new JSONObject();
			String abscissa = temp.getString("abscissa");
			Integer sum = temp.getInteger("sum");
			abscissaSet.add(abscissa);
			jsonObject.put("sum",sum);
			mo = mo+sum;
			jsonObject.put("rate",getRate(mo,total));
			chartData.put(abscissa,jsonObject);
		}
		result.put("abscissa",abscissaSet);
		result.put("chartData",chartData);
		result.put("total",total);
	}
}