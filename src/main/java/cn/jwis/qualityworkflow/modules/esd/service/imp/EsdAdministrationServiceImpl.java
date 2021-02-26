package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.util.DateUtil.getDetectionMonth;
import static cn.jwis.qualityworkflow.util.DateUtil.getDetectionMonth2;
import static cn.jwis.qualityworkflow.util.FormatUtil.stringToList;
import static cn.jwis.qualityworkflow.util.JSONObjectUtil.jsonArrayToList;
import static cn.jwis.qualityworkflow.util.Title.EsdInfoDB;
import static cn.jwis.qualityworkflow.util.Title.EsdInfoExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdInfoExcelName;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInfo;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAdministrationMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdAdministrationService;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;

/**
 * @Description ESD检验单管理
 * @Author yuyangyang
 * @Date 2020/5/15 10:47
 */
@Service
public class EsdAdministrationServiceImpl extends BaseClass implements EsdAdministrationService {

	@Autowired
	EsdAdministrationMapper esdAdministrationMapper;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	EmailService emailService;

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${fe.maintain.url}")
	private String wsdlUrl;

	public static final Logger logger = LoggerFactory.getLogger(EsdAdministrationServiceImpl.class);



	@Override
	public List<String> getDropdownValue(String parameter) {
		List <String> result = esdAdministrationMapper.getDropdownValue(parameter);
		return result;
	}

	@Override
	public ResultBean findEsdAdministration(JSONObject jsonObject) {
        Integer page = jsonObject.getInteger("page");
        Integer page1 = jsonObject.getInteger("page");
        Integer size = jsonObject.getInteger("size");
		List<String> list = null;
		if (page != null && size != null){
			page = (page -1)*size;
		}
		JSONArray jsonArray = jsonObject.getJSONArray("sampleNames");
        if (isNotNull(jsonArray)){
			list = jsonArrayToList(jsonArray);
		}
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
		Set<String> dateList = null;
        //通过开始时间和结束时间获取到月份
		try {
			dateList = getDetectionMonth(startTime,endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<EsdInfo> result  = esdAdministrationMapper.findEsdAdministration(list,dateList,page,size);
		if (isNotNull(result)){
			return ResultBean.pagination(esdAdministrationMapper.findEsdAdministrationCount(list,dateList), page1, size,result);
		}else {
			return new ResultBean();
		}
	}

	@Override
	public void exportEsdAdministration(JSONObject jsonObject, HttpServletResponse response) {
		List<String> list = null;
		JSONArray jsonArray = jsonObject.getJSONArray("sampleNames");
		if (isNotNull(jsonArray)){
			list = jsonArrayToList(jsonArray);
		}
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		Set<String> dateList = null;
		try {
			dateList = getDetectionMonth(startTime,endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<JSONObject> jsonObjects = new ArrayList<>();
		List<EsdInfo> result  = esdAdministrationMapper.findEsdAdministration(list,dateList,null,null);
		for (EsdInfo esdInfo:result) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,EsdInfoExcelName);
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,EsdInfoExcel,EsdInfoDB);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> findTableNameBySampleName(String sampleName) {
		Map<String,Object> titleMap = new LinkedHashMap<>();
		List<JSONObject> list = esdAdministrationMapper.findTableNameBySampleName(sampleName);
		titleMap.put("number","编号/区域");
		for (int i = 0; i < list.size(); i++) {
			titleMap.put("item"+i,list.get(i));
		}
		titleMap.put("isQualified","是/否合格");
		titleMap.put("isFe","是否维修");
		titleMap.put("remark","备注");
		return titleMap;
	}

	@Override
	@Transactional
	public void saveEsdAdministrationInfo(JSONObject jsonObject) {
		//获取副表的信息
		JSONArray arrays = jsonObject.getJSONArray("data");
		List<JSONObject> list = JSONObjectUtil.jsonArrayToList2(arrays);
		//获取样品名
		String sampleName = jsonObject.getString("sampleName");
		//获取检验日期
		String detectionDate = jsonObject.getString("detectionDate");
		//获取检验月份
		String detectionMonth = getDetectionMonth2(detectionDate);
		//获取创建人信息
		String surveyor = jsonObject.getString("surveyor");
		//获取环境温湿度信息
		String environmentTemperature = jsonObject.getString("environmentTemperature");
		String environmentHumidity = jsonObject.getString("environmentHumidity");
		//通过样品名和检验月份判断是否是第一次创建主表数据
		EsdInfo esdInfo = esdAdministrationMapper.getEsdBySampleAndMonth(sampleName,detectionMonth);
		if (isNotNull(esdInfo)){
            //主表已经存在相同检验单
			Long ngCount = esdAdministrationMapper.getNgCount(sampleName, detectionMonth);
			Long ngDealCount = esdAdministrationMapper.getNgDealCount(sampleName, detectionMonth);
			saveRepeatedlyEsdInfo(esdInfo,list,detectionDate,surveyor,environmentTemperature,environmentHumidity,ngCount,ngDealCount);
		}else{
			//该月第一次创建检验单
			saveFirstEsdInfo(sampleName,list,detectionMonth,detectionDate,surveyor,environmentTemperature,environmentHumidity);
		}
	}

	@Override
	public Integer isAdd(String sampleName, String detectionDate) {
		String detectionMonth = getDetectionMonth2(detectionDate);
		//获取抽样数
		Integer samplingQty = esdAdministrationMapper.getSamplingQty(sampleName);
		//获取检测数
		Long detectionQty =esdAdministrationMapper.getCountBySampleAndMonth(sampleName,detectionMonth);
		Integer result = samplingQty - detectionQty.intValue();
		return result;

	}

	@Override
	public EsdInfo getEsdInfoById(String id) {
		return esdAdministrationMapper.getEsdInfoById(id);
	}

	@Override
	public ResultBean getEsdDetailInfoByEsdId(JSONObject jsonObject) {
		String id = jsonObject.getString("id");
		EsdInfo esdInfoById = esdAdministrationMapper.getEsdInfoById(id);
		List<String> isPass = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("isPass"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		Integer page = jsonObject.getInteger("page");
		Integer page1 = jsonObject.getInteger("page");
		Integer size = jsonObject.getInteger("size");
		Map<String,String> titleMap = new LinkedHashMap<>();
		if (page != null && size != null){
			page = (page -1)*size;
		}
		List<JSONObject> list =  esdAdministrationMapper.getEsdDetailInfoByEsdId(id,isPass,startTime,endTime,page,size);
		String sampleName = esdInfoById.getSampleName();
		getEsdDetailTitle(sampleName,titleMap);
		//将数据整合成一个JSONObject
		getTrueList(list);
		if (isNotNull(list)){
			return ResultBean.pagination(esdAdministrationMapper.getEsdDetailCountByEsdId(id,isPass,startTime,endTime), page1, size,list,titleMap);
		}else {
			return ResultBean.pagination(0L,page1,size,list,titleMap);
		}
	}

	@Override
	public void esdRecheck(JSONObject jsonObject) {
		String id = jsonObject.getString("id");
		String esdId = jsonObject.getString("esd_id");
		String sampleName = jsonObject.getString("sample_name");
		String detectionMonth = jsonObject.getString("detection_month");
		JSONObject item = new JSONObject();
		Set<String> keySet = new HashSet<>();
	    keySet.addAll(jsonObject.keySet());
		for (String key:keySet) {
			if (key.contains("item")){
				item.put(key,jsonObject.getString(key));
				jsonObject.remove(key);
			}
		}
		jsonObject.put("item",item.toJSONString());
		List<JSONObject> list = JSONObjectUtil.separate(jsonObject);
		commonMapper.update(list,"esd_detail_info",id);
		//重新计算最终合格率
		Integer detectionQty = esdAdministrationMapper.getDetectionQtyById(esdId);
		Integer qualifiedQty = esdAdministrationMapper.getQualifiedQty(esdId);
		Float passRate = getPassRate(qualifiedQty, detectionQty);
		Integer unQualifiedQty = detectionQty - qualifiedQty;
		//重新计算不合格处置率
		Long ngCount = esdAdministrationMapper.getNgCount(sampleName, detectionMonth);
		Long ngDealCount = esdAdministrationMapper.getNgDealCount(sampleName, detectionMonth);
		Float rate = getPassRate(ngDealCount.intValue(), ngCount.intValue());
		//通过ID去修改主表的合格数和最终合格率，不合格数，不合格处置率
		esdAdministrationMapper.updatePassByPrimaryKey(esdId,qualifiedQty,passRate,unQualifiedQty,rate);
	}

	@Override
	public JSONObject getEsdDetailInfoById(String id) {
		JSONObject result = new JSONObject();
		Map<String,String> title = new LinkedHashMap<>();
		JSONObject  jsonObject= esdAdministrationMapper.getEsdDetailInfoById(id);
		JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("item"));
		String sampleName = jsonObject.getString("sample_name");
		Set<String> keySet = jsonObject1.keySet();
		for (String str : keySet){
			jsonObject.put(str,jsonObject1.getString(str));
		}
		jsonObject.remove("item");
		getEsdAgainTitle(sampleName,title);
		result.put("data",jsonObject);
		result.put("title",title);
		return result;
	}
    /**
     * @Author yuyangyang
     * @Description FE维修接口
     * @Date  2020/5/19  10:52
     * @Param
     * @return
     */
	@Override
	public void feMaintain(JSONObject jsonObject) {
		//保存到记录表
		JSONObject result = new JSONObject();
		result.put("id",String.valueOf(idGeneratorRunner.nextId()));
		result.put("creator",getCurrentUserName());
		result.put("data",jsonObject.toJSONString());
		List<JSONObject> list = JSONObjectUtil.separate(result);
		commonMapper.saveDate(list,"record_info");
		//通过WebService调用FE接口
		String tTaskCategory = jsonObject.getString("tTaskCategory");
		String lineBody = jsonObject.getString("lineBody");
		String model = jsonObject.getString("model");
		String repairType = jsonObject.getString("repairType");
		String deviceName = jsonObject.getString("deviceName");
		String tVerificationState = jsonObject.getString("tVerificationState");
		String tDescriptionMaintenance = jsonObject.getString("tDescriptionMaintenance");
		try {
			webServiceSend(tTaskCategory,lineBody,model,repairType,deviceName,tVerificationState,tDescriptionMaintenance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * @Author yuyangyang
     * @Description 获取合格率
     * @Date  2020/5/18  10:52
     * @Param
     * @return
     */
	private Float getPassRate(Integer pass,Integer total){
		if (total == 0 || isNull(total)){
			return  0F;
		}
		BigDecimal passBig = BigDecimal.valueOf(Double.valueOf(pass));
		BigDecimal totalBig = BigDecimal.valueOf(Double.valueOf(total));
	    BigDecimal	passRate = passBig.multiply(BigDecimal.valueOf(100)).divide(totalBig,2,BigDecimal.ROUND_HALF_DOWN);
        return passRate.floatValue();
	}
    /**
     * @Author yuyangyang
     * @Description 第一次创建新维度检验单信息
     * @Date  2020/5/18  13:55
     * @Param
     * @return
     */
	private void saveFirstEsdInfo(String sampleName,List<JSONObject> list,String detectionMonth,String detectionDate,String surveyor,String environmentTemperature,String environmentHumidity){
		//获取主表ID
		String id =  String.valueOf(idGeneratorRunner.nextId());
		//获取抽样数
		Integer samplingQty = esdAdministrationMapper.getSamplingQty(sampleName);
		if (isNull(samplingQty)){
			throw new BaseException("请先维护该样品的抽样数量");
		}
		//获取检测数
		Integer detectionQty = list.size();
		//获取合格数
		Integer qualifiedQty = 0;
		//获取不合格数和第一次不合格数
		Integer unqualifiedQty = 0;
		for (JSONObject json:list) {
			if ("OK".equals(json.getString("isQualified"))){
				qualifiedQty++;
			}else {
				unqualifiedQty++;
			}
		}
		//计算合格率
		Float acceptanceRate = getPassRate(qualifiedQty,detectionQty);
		//获取终检合格率
		Float finalAcceptanceRate = acceptanceRate;
		//获取状态
		String status = "Ongoing";
		if (samplingQty <= detectionQty){
			status = "Close";
		}
		EsdInfo esdInfo = new EsdInfo();
		esdInfo.setId(id);
		esdInfo.setSampleName(sampleName);
		esdInfo.setDetectionMonth(detectionMonth);
		esdInfo.setSamplingQty(samplingQty);
		esdInfo.setDetectionQty(detectionQty);
		esdInfo.setQualifiedQty(qualifiedQty);
		esdInfo.setAcceptanceRate(acceptanceRate);
		esdInfo.setFinalAcceptanceRate(finalAcceptanceRate);
		esdInfo.setStatus(status);
		esdInfo.setUnqualifiedQty(unqualifiedQty);
		esdInfo.setNonconformingDisposalRate(0f);
		esdAdministrationMapper.insertEsdInfo(esdInfo);
		saveEsdDetailInfo(list,id,detectionDate,detectionMonth,surveyor,environmentTemperature,environmentHumidity,sampleName);
	}

    /**
     * @Author yuyangyang
     * @Description 多次创建相同维度的检验单
     * @Date  2020/5/18  13:56
     * @Param
     * @return
     */
	private void saveRepeatedlyEsdInfo(EsdInfo esdInfo,List<JSONObject> list,String detectionDate,String surveyor,String environmentTemperature,String environmentHumidity,Long ngCount,Long ngDealCount){
		String id = esdInfo.getId();
		String detectionMonth = esdInfo.getDetectionMonth();
		String sampleName = esdInfo.getSampleName();
		//修改ESD主表的信息(检测数，合格数，合格率，不合格数,最终合格率，状态)
		//获取抽检数
		Integer samplingQty = esdInfo.getSamplingQty();
		//获取检测数
		Integer detectionQty = list.size();
		detectionQty = esdInfo.getDetectionQty()+detectionQty;
		//获取合格数
		Integer qualifiedQty = esdInfo.getQualifiedQty();
		Integer unqualifiedQty = esdInfo.getUnqualifiedQty();
		Integer qualifiedQty2 = esdAdministrationMapper.getQualifiedQty2(id);
		Integer temp =0;
		for (JSONObject json:list) {
			if ("OK".equals(json.getString("isQualified"))){
				qualifiedQty++;
				qualifiedQty2++;
			}else {
				unqualifiedQty++;
				temp++;
			}
		}
		//计算合格率
		Float acceptanceRate = getPassRate(qualifiedQty2,detectionQty);
		//获取终检合格率
		Float finalAcceptanceRate = getPassRate(qualifiedQty,detectionQty);
		String status = "Ongoing";
		if (samplingQty <= detectionQty){
			status = "Close";
		}
		//获取不合格处置率
		Float passRate = getPassRate(ngDealCount.intValue(), ngCount.intValue()+temp);
		esdInfo.setUnqualifiedQty(unqualifiedQty);
		esdInfo.setSamplingQty(samplingQty);
		esdInfo.setDetectionQty(detectionQty);
		esdInfo.setQualifiedQty(qualifiedQty);
		esdInfo.setAcceptanceRate(acceptanceRate);
		esdInfo.setFinalAcceptanceRate(finalAcceptanceRate);
		esdInfo.setStatus(status);
		esdInfo.setNonconformingDisposalRate(passRate);
		esdAdministrationMapper.updateEsdInfoByPrimaryKey(esdInfo);
		//新增ESD详情表的信息
		saveEsdDetailInfo(list,id,detectionDate,detectionMonth,surveyor,environmentTemperature,environmentHumidity,sampleName);
	}
    /**
     * @Author yuyangyang
     * @Description 保存详情表信息
     * @Date  2020/5/18  14:16
     * @Param
     * @return
     */
	private void saveEsdDetailInfo(List<JSONObject> list,String id,String detectionDate,String detectionMonth,String surveyor,String environmentTemperature,String environmentHumidity,String sampleName){
		//向详情表中添加信息
		for (JSONObject json:list) {
			String item = json.getString("label");
			json.put("sample_name",sampleName);
			json.put("esd_id",id);
			json.put("detection_date",detectionDate);
			json.put("detection_month",detectionMonth);
			json.put("surveyor",surveyor);
			json.put("environment_temperature",environmentTemperature);
			json.put("environment_humidity",environmentHumidity);
			json.put("item",json.getJSONObject("item").toJSONString());
			json.put("id",String.valueOf(idGeneratorRunner.nextId()));
			if ("OK".equals(json.getString("isQualified"))){
				json.put("final_result","OK");
			}
			//判断是否要发邮件
			String isFe = json.getString("isFe");
			String creator = getCurrentUserName();
		    if (isNotNull(isFe) && !"FE报修".equals(isFe)){
				ThreadUtil.getThreadPool().execute(() -> {
					sendEmailTohandler(isFe,json,item,creator);
				});
			}
			json.remove("label");
			List<JSONObject> list1 = JSONObjectUtil.separate(json);
			commonMapper.saveDate(list1,"esd_detail_info");
		}
	}
	/**
	 * @Author yuyangyang
	 * @Description 将数据转换成合适展示数据
	 * @Date  2020/5/18  17:47
	 * @Param
	 * @return
	 */
	private void getTrueList(List<JSONObject> list){
		for (JSONObject jsonObject :list){
		    JSONObject itemJson = JSONObject.parseObject(jsonObject.getString("item"));
			Set<String> keySet = itemJson.keySet();
			for (String str:keySet){
				jsonObject.put(str,itemJson.getString(str));
			}
			jsonObject.remove("item");
		}
	}
    /**
     * @Author yuyangyang
     * @Description 详情表表头
     * @Date  2020/5/18  17:42
     * @Param
     * @return
     */
	private void getEsdDetailTitle(String sampleName,Map<String,String> titleMap){
		List<String> titleList = esdAdministrationMapper.findNameBySampleName(sampleName);
		titleMap.put("sample_name","样品名");
		titleMap.put("detection_month","检验月份");
		titleMap.put("detection_date","检验日期");
		titleMap.put("environment_temperature","环境温度");
		titleMap.put("environment_humidity","环境湿度");
		titleMap.put("surveyor","检测人");
		titleMap.put("number","编号/区域");
		for (int i = 0; i < titleList.size(); i++) {
			titleMap.put("item"+i,titleList.get(i));
		}
		titleMap.put("is_qualified","是/否合格");
		titleMap.put("is_fe","是否维修");
		titleMap.put("final_result","最终结果");
		titleMap.put("remark","备注");
	}


    /**
     * @Author yuyangyang
     * @Description 通过WebService的方式调用FE接口
     * @Date  2020/5/19  10:45
     * @Param
     * @return
     */
	private void webServiceSend(String tTaskCategory,String lineBody,String model,String repairType,
								String deviceName,String tVerificationState,String tDescriptionMaintenance) throws IOException {
		logger.info("------------开始连接------------");
		URL url = new URL(wsdlUrl);
		// 第二步：打开一个通向服务地址的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		logger.info("------------连接打开------------");
		// 第三步：设置参数
		// 3.1发送方式设置：POST必须大写
		connection.setRequestMethod("POST");
		// 3.2设置数据格式：content-type
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		connection.setRequestProperty("Content-Length","668");
		connection.setRequestProperty("SOAPAction","http://tempuri.org/Submit_declaration");
		connection.setRequestProperty("Host","10.124.149.29");
		// 3.3设置输入输出，因为默认新创建的connection没有读写权限，
		connection.setDoInput(true);
		connection.setDoOutput(true);
		//密码认证的方式
//		String auth = wsdlUserName + ":" + wsdlPassword;
//		byte[] rel = Base64.getEncoder().encode(auth.getBytes());
//		String res = new String(rel);
//		// 设置认证属性
//		connection.setRequestProperty("Authorization", "Basic " + res);
		//传参方式XML的拼接
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
				"  <soap:Body>\n" + "    <Submit_declaration xmlns=\"http://tempuri.org/\">\n" +
				"      <t_Task_category>"+tTaskCategory+"</t_Task_category>\n" + "      <Line_body>"+lineBody+"</Line_body>\n" +
				"      <t_model>"+model+"</t_model>\n" + "      <repair_type>"+repairType+"</repair_type>\n" +
				"      <Device_name>"+deviceName+"</Device_name>\n" + "      <t_Verification_state>"+tVerificationState+"</t_Verification_state>\n"
				+ "      <t_Description_maintenance>"+tDescriptionMaintenance+"</t_Description_maintenance>\n"
				+ "    </Submit_declaration>\n" + "  </soap:Body>\n" + "</soap:Envelope>";
		logger.info("logger------------传递参数------------" + xml);
		OutputStream os = connection.getOutputStream();
		os.write(xml.getBytes());
		// 第五步：接收服务端响应，打印
		int responseCode = connection.getResponseCode();
		String responseMessage = connection.getResponseMessage();
		logger.info("------------- responseCode:" + responseCode + "responseMessage:" + responseMessage);
		if (200 == responseCode) {// 表示服务端响应成功
			BufferedReader bufr = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 设置文件输入流的编码，和url的编码一致
			String lines = "";
			String result = "";
			while ((lines = bufr.readLine()) != null) {
				result += lines;
			}
			logger.info("----------response xml--------" + result);
			logger.info("--------------服务调用成功------------");
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取ESD复检的表头信息
	 * @Date  2020/7/13  18:17
	 * @Param
	 * @return
	 */
	private void getEsdAgainTitle(String sampleName,Map<String,String> titleMap){
		List<String> titleList = esdAdministrationMapper.findNameBySampleName(sampleName);
		titleMap.put("number","编号/区域");
		for (int i = 0; i < titleList.size(); i++) {
			titleMap.put("item"+i,titleList.get(i));
		}
		titleMap.put("is_qualified","是/否合格");
		titleMap.put("is_fe","是否维修");
		titleMap.put("final_result","最终结果");
		titleMap.put("remark","备注");
	}

	/**
	 * @Author yuyangyang
	 * @Description 选择非FE的维修方式下发邮件
	 * @Date  2020/8/6  11:16
	 * @Param
	 * @return
	 */
	private  void sendEmailTohandler(String isFe,JSONObject jsonObject,String item,String creator){
		// 生成内容
		String subject = "ESD周期性检验不良通知";
		String content = getHandlerMailContent(isFe,subject,jsonObject,item,creator);
		String cqeHandlerMail = commonMapper.getEsdEmailList(isFe);
		if(isNotNull(cqeHandlerMail)){
			List<String> list = stringToList(cqeHandlerMail);
			String cc =  commonMapper.getEsdEmailCcList(isFe);
			List<String> ccList = stringToList(cc);
			String[] listArray  = list.toArray(new String[list.size()]);
			String[] ccArray  = ccList.toArray(new String[ccList.size()]);
			// 发送邮件
			emailService.sendHtmlMail(listArray, ccArray, subject, content);
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取邮件的具体信息
	 * @Date  2020/7/28  13:49
	 * @Param
	 * @return
	 */
	public String getHandlerMailContent(String handleMehhod,String theme,JSONObject jsonObject,String item,String creator) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Context context = new Context();
		String sampleName = jsonObject.getString("sample_name");
		context.setVariable("theme", theme);
		context.setVariable("handleMehhod", handleMehhod);
		context.setVariable("submitter", creator);
		context.setVariable("sampleName",sampleName);
		context.setVariable("sampleNameTemp","样品名:");
		context.setVariable("detectionDate",jsonObject.getString("detection_date"));
		context.setVariable("detectionDateName","检验日期");
		context.setVariable("surveyor",jsonObject.getString("surveyor"));
		context.setVariable("surveyorName","检验人");
		context.setVariable("number",jsonObject.getString("number"));
		context.setVariable("numberName","编号/区域");
		context.setVariable("isQualified",jsonObject.getString("isQualified"));
		context.setVariable("item",item);
		context.setVariable("itemName","检验项目");
		context.setVariable("handlerDate",sdf.format(new Date()));
		String template = templateEngine.process("EsdEmailTemplate", context);
		return template;
	}
}