package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.Title.esdSpeciaManageDb;
import static cn.jwis.qualityworkflow.util.Title.esdSpeciaManageExcel;
import static cn.jwis.qualityworkflow.util.Title.esdSpeciaManageExcelName;
import static cn.jwis.qualityworkflow.util.Title.esdSpeciaManageExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpeciaManageSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialManageInfo;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdSpecialManageInfoMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdSpecialManageInfoService;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;
import cn.jwis.qualityworkflow.util.Title;
import cn.jwis.qualityworkflow.util.UserServer;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/6/16 16:33
 */
@Service
public class EsdSpecialManageInfoServiceImpl implements EsdSpecialManageInfoService {
	@Autowired
	EsdSpecialManageInfoMapper esdSpecialManageInfoMapper;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	EmailService emailService;

	@Autowired
	TemplateEngine templateEngine;

	@Autowired
	UserServer userServer;
	@Override
	public List<String> getDropdownValue(String parameter) {
		return esdSpecialManageInfoMapper.getDropdownValue(parameter);
	}

	@Override
	public List<JSONObject> findSpecialManageInfo(EsdSpeciaManageSearch esdSpeciaManageSearch) {
		try {
			//页码转换
			pageChange(esdSpeciaManageSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<EsdSpecialManageInfo> list = esdSpecialManageInfoMapper.findSpecialManageInfo(esdSpeciaManageSearch);
		List<JSONObject> result = new ArrayList<>();
		for (EsdSpecialManageInfo esd:list) {
			JSONObject temp = new JSONObject();
			boolean flag = getFlag(esd);
            temp = JSONObjectUtil.toJSONObject(esd);
            temp.put("enableHandleCurrentNode",flag);
            result.add(temp);
		}
		return result;
	}

	@Override
	public Long findSpecialManageCount(EsdSpeciaManageSearch esdSpeciaManageSearch) {
		Long count = esdSpecialManageInfoMapper.findSpecialManageCount(esdSpeciaManageSearch);
		return count;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request,esdSpeciaManageDb,esdSpeciaManageExcel,esdSpeciaManageExcelUS);
	}

	@Override
	public void exportSpecialManageInfo(EsdSpeciaManageSearch bean, HttpServletResponse response, HttpServletRequest request) {
		String userItemInfos = userServer.getUserItemInfos("ESD-业务管理员");
		bean.setAssignee(userItemInfos);
		String language = request.getHeader("Language");
		//导出将页码的限制去除
		bean.setPage(null);
		bean.setSize(null);
		List<EsdSpecialManageInfo> list = esdSpecialManageInfoMapper.findSpecialManageInfo(bean);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdSpecialManageInfo esdSpecialManageInfo:list) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdSpecialManageInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,esdSpeciaManageExcelName);
		String[] title = esdSpeciaManageExcel;
		if ("en-US".equals(language)){
			title = esdSpeciaManageExcelUS;
		}
		String[] strings = esdSpeciaManageDb;
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getModelList() {
		return esdSpecialManageInfoMapper.getModelList();
	}

	@Override
	public JSONObject getRateAndSum(JSONObject bean) {
		JSONObject result = new JSONObject();
		String sampleName = bean.getString("sampleName");
		//获取比率和数量
		result = esdSpecialManageInfoMapper.getRate(sampleName);
        return result;
	}

	@Override
	public Map<String, Object> findTableNameBySampleName(String sampleName) {
		Map<String,Object> titleMap = new LinkedHashMap<>();
		List<JSONObject> list = esdSpecialManageInfoMapper.findTableNameBySampleName(sampleName);
		titleMap.put("appearance","外观");
		for (int i = 0; i < list.size(); i++) {
			titleMap.put("item"+i,list.get(i));
		}
		titleMap.put("is_qualified","是/否合格");
		titleMap.put("remark","备注");
		return titleMap;
	}

	@Override
	public void saveEsdSpecialManageInfo(EsdSpecialManageInfo esdSpecialManageInfo) {
		//获取创建人信息
		esdSpecialManageInfo.setCreator(getCurrentUserName());
		//获取副表的信息
		List<JSONObject> list = esdSpecialManageInfo.getData();
		String id = String.valueOf(idGeneratorRunner.nextId());
		esdSpecialManageInfo.setId(id);
		//获取抽样数量
		Integer samplingQty = esdSpecialManageInfo.getSamplingQty();
		int qualifiedQty = 0;
		//副表信息保存
		for (JSONObject jsonObject :list){
			String isQualified = jsonObject.getString("is_qualified");
			String oid = String.valueOf(idGeneratorRunner.nextId());
			jsonObject.put("id",oid);
			jsonObject.put("esd_id",id);
			jsonObject.put("item",jsonObject.getJSONObject("item").toJSONString());
			List<JSONObject> list1 = JSONObjectUtil.separate(jsonObject);
			if ("OK".equals(isQualified)){
				qualifiedQty++;
			}
			commonMapper.saveDate(list1,"esd_special_detail_info");
		}
		//添加合格数，合格率，最终结果
		esdSpecialManageInfo.setQualifiedQty(qualifiedQty);
		//获取合格率的百分比
		esdSpecialManageInfo.setAcceptanceRate(getPassRate(qualifiedQty,samplingQty));
		String finalRsult = "NG";
		if (qualifiedQty == samplingQty){
			finalRsult = "OK";
		}
		esdSpecialManageInfo.setFinalResult(finalRsult);
		esdSpecialManageInfoMapper.insert(esdSpecialManageInfo);
		//下发邮件
		ThreadUtil.getThreadPool().execute(() -> {
			sendEsdAcceptEmail(esdSpecialManageInfo);
		});
	}

	@Override
	public void handleEsdSpecialManageInfo(EsdSpecialManageInfo esdSpecialManageInfo) {
		if ("特采".equals(esdSpecialManageInfo.getHandlingMethod())){
			esdSpecialManageInfoMapper.updateByPrimaryKey2(esdSpecialManageInfo);
		}else {
			esdSpecialManageInfoMapper.updateByPrimaryKey(esdSpecialManageInfo);
		}
	}

	@Override
	public JSONObject findEsdSpecialManageDetail(String id) {
		JSONObject result = new JSONObject();
		EsdSpecialManageInfo esdSpecialManageDetail = esdSpecialManageInfoMapper.findEsdSpecialManageDetail(id);
		Boolean flag = getFlag(esdSpecialManageDetail);
		//获取样品名
		String sampleName = esdSpecialManageDetail.getSampleName();
		//获取表头
		Map<String, Object> title = findTableNameBySampleName(sampleName);
		List<JSONObject> detailData = esdSpecialManageInfoMapper.getDetailData(id);
		List<JSONObject> data = new ArrayList<>();
		for (JSONObject jsonObject : detailData){
			JSONObject item = JSONObject.parseObject(jsonObject.getString("item"));
			Set<String> keySet = item.keySet();
			for (String str : keySet){
				jsonObject.put(str,item.getString(str));
			}
			jsonObject.remove("item");
			data.add(jsonObject);
		}
		esdSpecialManageDetail.setData(data);
		result.put("data",esdSpecialManageDetail);
		result.put("title",title);
		result.put("enableHandleCurrentNode",flag);
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取合格率
	 * @Date  2020/5/18  10:52
	 * @Param
	 * @return
	 */
	private Float getPassRate(Integer pass,Integer total){
		BigDecimal passBig = BigDecimal.valueOf(Double.valueOf(pass));
		BigDecimal totalBig = BigDecimal.valueOf(Double.valueOf(total));
		BigDecimal	passRate = passBig.multiply(BigDecimal.valueOf(100)).divide(totalBig,2,BigDecimal.ROUND_HALF_DOWN);
		return passRate.floatValue();
	}
    /**
     * @Author yuyangyang
     * @Description 判定当前用户是否能够处理这个来料检验单
     * @Date  2020/7/14  11:06
     * @Param
     * @return
     */
    private boolean getFlag(EsdSpecialManageInfo esdSpecialManageDetail){
    	boolean flag = false;
    	boolean flag1 = false;
    	boolean flag2 = false;
    	boolean flag3 = false;
    	if (getCurrentUserName().equals(esdSpecialManageDetail.getDemandProposer())){
			flag1 = true;
		}
		if ("NG".equals(esdSpecialManageDetail.getFinalResult())){
			 flag2 = true;
		}
		if (StringUtils.isEmpty(esdSpecialManageDetail.getHandlingMethod())){
			 flag3 = true;
		}
		if (flag1 && flag2 && flag3){
			flag = true;
		}
		return flag;
	}

	/**
	 * @Author yuyangyang
	 * @Description 下发邮件
	 * @Date  2020/8/20  17:04
	 * @Param 
	 * @return 
	 */
	public  void sendEsdAcceptEmail( EsdSpecialManageInfo esdSpecialManageInfo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 生成内容
		String content = getHandlerMailContent(esdSpecialManageInfo);
		String demandProposer = esdSpecialManageInfo.getDemandProposer();
		String supplier = esdSpecialManageInfo.getSupplier();
		String sampleName = esdSpecialManageInfo.getSampleName();
		String finalResult = esdSpecialManageInfo.getFinalResult();
		Date inspectionDate = esdSpecialManageInfo.getInspectionDate();
		String cqeHandlerMail = emailService.getHandlerMail(demandProposer);
		String subject = "ESD来料检验结果通知！" + String.join("+", supplier, sampleName,finalResult,sdf.format(inspectionDate));
		// 发送邮件
		if (StringUtils.isNotEmpty(cqeHandlerMail)) {
			emailService.sendHtmlMail(cqeHandlerMail, null, subject, content);
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取邮件的具体信息
	 * @Date  2020/7/28  13:49
	 * @Param
	 * @return
	 */
	public String getHandlerMailContent(EsdSpecialManageInfo esdSpecialManageInfo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Context context = new Context();
		context.setVariable("supplier", esdSpecialManageInfo.getSupplier());
		context.setVariable("sampleName", esdSpecialManageInfo.getSampleName());
		context.setVariable("testResults", esdSpecialManageInfo.getFinalResult());
		context.setVariable("inspectionDate", sdf.format(esdSpecialManageInfo.getInspectionDate()));
		context.setVariable("submitter",esdSpecialManageInfo.getCreator());
		context.setVariable("model",esdSpecialManageInfo.getModel());
		context.setVariable("specificationModel",esdSpecialManageInfo.getSpecificationModel());
		context.setVariable("deliveredQty",esdSpecialManageInfo.getDeliveredQty());
		context.setVariable("demandDepartment",esdSpecialManageInfo.getDemandDepartment());
		context.setVariable("demandProposer",esdSpecialManageInfo.getDemandProposer());
		context.setVariable("demandDate",sdf.format(esdSpecialManageInfo.getDemandDate()));
		context.setVariable("checker",esdSpecialManageInfo.getChecker());
		context.setVariable("finalResult",esdSpecialManageInfo.getFinalResult());
		context.setVariable("handlerDate",sdf.format(new Date()));
		String template = templateEngine.process("EsdAcceptEmailTemplate", context);
		return template;
	}
}