package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.util.BeanUtil.pageChange;
import static cn.jwis.qualityworkflow.util.FormatUtil.stringToList;
import static cn.jwis.qualityworkflow.util.Title.EsdAudiMainDB;
import static cn.jwis.qualityworkflow.util.Title.EsdAudiMainExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdAudiMainExcelUS;
import static cn.jwis.qualityworkflow.util.Title.EsdAudiSecondaryDB;
import static cn.jwis.qualityworkflow.util.Title.EsdAudiSecondaryExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditMainDB;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditMainExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdAuditMainExcelUS;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAudiMainSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListSecondary;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSave;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSecondary;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdAuditMainMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdAuditMainService;
import cn.jwis.qualityworkflow.util.EmailService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ThreadUtil;
import cn.jwis.qualityworkflow.util.Title;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/1 16:33
 */
@Service
public class EsdAuditMainServiceImpl extends BaseClass implements EsdAuditMainService {

	@Autowired
	EsdAuditMainMapper esdAuditMainMapper;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	EmailService emailService;

	@Autowired
	private TemplateEngine templateEngine;


	@Override
	public List<String> getDropdownValue(String parameter) {
		List<String> dropdownValue = esdAuditMainMapper.getDropdownValue(parameter);
		return dropdownValue;
	}

	@Override
	public List<EsdAuditMain> getEsdAudiMainList(EsdAudiMainSearch esdAudiMainSearch) throws Exception {
		pageChange(esdAudiMainSearch);
		List<EsdAuditMain> esdAudiMainList = esdAuditMainMapper.getEsdAudiMainList(esdAudiMainSearch);
		return esdAudiMainList;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request,EsdAudiMainDB,EsdAudiMainExcel,EsdAudiMainExcelUS);
	}

	@Override
	public void exportEsdAdministration(HttpServletResponse response) {
		ExcelUtil.setResponseHeader(response, "EsdAuditTemplate.xlsx");
		Workbook workbook = ExcelUtil.exporSimple(null,EsdAudiSecondaryExcel,EsdAudiSecondaryDB);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void saveEsdAudit(EsdAuditSave esdAuditSave) {
		//判断是否是编辑
		String oidId = esdAuditSave.getId();
		//获取当前信息
		String newId = String.valueOf(idGeneratorRunner.nextId());
		String auditType = esdAuditSave.getAuditType();
		String currentUserName = getCurrentUserName();
		String documentName = esdAuditSave.getDocumentName();
		String documentNumber = esdAuditSave.getDocumentNumber();
		String remark = esdAuditSave.getRemark();
		//新增
		if (StringUtils.isEmpty(oidId)){
            EsdAuditMain esdAuditMain = new EsdAuditMain();
			esdAuditMain.setId(newId);
			esdAuditMain.setAuditType(auditType);
			esdAuditMain.setCreator(currentUserName);
			esdAuditMain.setDocumentName(documentName);
			esdAuditMain.setDocumentNumber(documentNumber);
			esdAuditMain.setRemark(remark);
			esdAuditMainMapper.saveEsdAuditMain(esdAuditMain);
			List<EsdAuditSecondary> data = esdAuditSave.getData();
			saveEsdAuditSecondary(data,newId);
		}
		//编辑
		else {
			EsdAuditMain esdAuditMain = new EsdAuditMain();
			esdAuditMain.setId(oidId);
			esdAuditMain.setRemark(remark);
			esdAuditMain.setUpdateDate(new Date());
			esdAuditMainMapper.updateEsdAuditMain(esdAuditMain);
			esdAuditMainMapper.removeEsdAuditSecondary(oidId);
			List<EsdAuditSecondary> data = esdAuditSave.getData();
			saveEsdAuditSecondary(data,oidId);
		}
	}

	@Override
	public EsdAuditSave getEsdAuditDetails(String id) {
		EsdAuditSave esdAuditSave = new EsdAuditSave();
		EsdAuditMain esdAuditMain = esdAuditMainMapper.getEsdAuditMainById(id);
		List<EsdAuditSecondary> list = esdAuditMainMapper.getEsdAuditSecondaryById(id);
		esdAuditSave.setId(esdAuditMain.getId());
		esdAuditSave.setAuditType(esdAuditMain.getAuditType());
		esdAuditSave.setDocumentName(esdAuditMain.getDocumentName());
		esdAuditSave.setDocumentNumber(esdAuditMain.getDocumentNumber());
		esdAuditSave.setRemark(esdAuditMain.getRemark());
		esdAuditSave.setData(list);
		return  esdAuditSave;
	}

	@Override
	public List<String> getDownValueList(String parameter) {
		return esdAuditMainMapper.getDownValueList(parameter);
	}

	@Override
	public List<EsdAuditListMain> getEsdAuditList(EsdAuditSearch esdAuditSearch) throws Exception {
		pageChange(esdAuditSearch);
		List<EsdAuditListMain> list = esdAuditMainMapper.getEsdAuditList(esdAuditSearch);
		List<EsdAuditListMain> result = new ArrayList<>();
		for (EsdAuditListMain esd:list) {
			result.add(transformation(esd));
		}
		return result;
	}

	@Override
	public Map<String, String> getAuditTitle(HttpServletRequest request) {
		return Title.getTitle(request,EsdAuditMainDB,EsdAuditMainExcel,EsdAuditMainExcelUS);
	}

	@Override
	public void exportEsdAuditList(HttpServletResponse response, HttpServletRequest request, EsdAuditSearch esdAuditSearch) {
		String language = request.getHeader("Language");
		esdAuditSearch.setPage(null);
		esdAuditSearch.setSize(null);
		List<EsdAuditListMain> list = esdAuditMainMapper.getEsdAuditList(esdAuditSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdAuditListMain esdAuditListMain:list) {
			String auditType = esdAuditListMain.getAuditType();
			if ("month".equals(auditType)){
				esdAuditListMain.setAuditType("月度");
			}else if ("year".equals(auditType)){
				esdAuditListMain.setAuditType("年度");
			}
			JSONObject object = JSONObjectUtil.toJSONObject(esdAuditListMain);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,"EsdAudit.xlsx");
		String[] title = EsdAuditMainExcel;
		if ("en-US".equals(language)){
			title = EsdAuditMainExcelUS;
		}
		String[] strings = EsdAuditMainDB;
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getDocumentNameList(String parameter) {
		return esdAuditMainMapper.getDocumentNameList(parameter);
	}

	@Override
	public List<String> getDocumentNumberListByName(String documentName) {
		return esdAuditMainMapper.getDocumentNumberListByName(documentName);
	}

	@Override
	public List<EsdAuditSecondary> getDetailsByNumber(String documentNumber) {
		return esdAuditMainMapper.getDetailsByNumber(documentNumber);
	}

	@Override
	@Transactional
	public void saveEsdAuditList(JSONObject jsonObject) {
		String mainId = String.valueOf(idGeneratorRunner.nextId());
		//获取主表的信息
		EsdAuditListMain esdAuditListMain = new EsdAuditListMain();
		String auditType = jsonObject.getString("auditType");
		String documentNumber = jsonObject.getString("documentNumber");
		Date auditDate = jsonObject.getDate("auditDate");
		String frequency = jsonObject.getString("frequency");
		String line = jsonObject.getString("line");
		String auditer = jsonObject.getString("auditer");
		String documentName = jsonObject.getString("documentName");
		esdAuditListMain.setAuditType(auditType);
		esdAuditListMain.setDocumentNumber(documentNumber);
		esdAuditListMain.setAuditDate(auditDate);
		esdAuditListMain.setFrequency(frequency);
		esdAuditListMain.setLine(line);
		esdAuditListMain.setAuditer(auditer);
		esdAuditListMain.setDocumentName(documentName);
		esdAuditListMain.setId(mainId);
		esdAuditListMain.setCreator(getCurrentUserName());
		//通过副表的信息获取稽核数量，合格数，合格率
		List<JSONObject> data = JSONObjectUtil.jsonArrayToList2(jsonObject.getJSONArray("data"));
		Integer auditNumber = data.size();
		Integer qualifiedNumber = 0;
		for (JSONObject json : data) {
			String id = String.valueOf(idGeneratorRunner.nextId());
			json.put("main_id",mainId);
			json.put("id",id);
			String creator = getCurrentUserName();
			json.put("creator",creator);
			String finalResult = json.getString("finalResult");
			String inspectionResults = json.getString("inspectionResults");
			String flag  = "1";
			String billsFlag = "1";
			if ("NG".equals(inspectionResults)){
				billsFlag = "0";
				flag = "0";
			}
			if ("OK".equals(finalResult)){
				flag = "1";
			}
			if("NG".equals(inspectionResults) && !"OK".equals(finalResult)){
				qualifiedNumber++;
			}
			json.put("flag",flag);
			json.put("billsFlag",billsFlag);
			List<JSONObject> list = JSONObjectUtil.separate(json);
			commonMapper.saveDate(list,"esd_audit_list_secondary");
			String warranty = json.getString("warranty");
			if (isNotNull(warranty) && !"FE报修".equals(warranty)){
				ThreadUtil.getThreadPool().execute(() -> {
					sendEmailTohandler(warranty,json,auditDate,auditer,line,creator);
				});
			}
		}
		esdAuditListMain.setQualifiedNumber(auditNumber-qualifiedNumber);
		esdAuditListMain.setAuditNumber(auditNumber);
		esdAuditListMain.setQualifiedRate(getPassRate(auditNumber-qualifiedNumber,auditNumber));
		esdAuditMainMapper.saveEsdAuditList(esdAuditListMain);
	}

	@Override
	public JSONObject getEsdAuditListDetails(String id) {
		JSONObject jsonObject = new JSONObject();
		EsdAuditListMain esdAuditListById = esdAuditMainMapper.getEsdAuditListById(id);
		List<EsdAuditListSecondary> esdAuditListSecondaryById = esdAuditMainMapper.getEsdAuditListSecondaryById(id);
		Boolean enableHandleCurrentNode = false;
		String currentUserName = getCurrentUserName();
		String creator = esdAuditListById.getCreator();
		if (currentUserName.equals(creator)){
			enableHandleCurrentNode = true;
		}
		jsonObject.put("main",esdAuditListById);
		jsonObject.put("data",esdAuditListSecondaryById);
		jsonObject.put("enableHandleCurrentNode",enableHandleCurrentNode);
		return jsonObject;
	}

	@Override
	public void updateEsdAuditListSecondary(EsdAuditListSecondary esdAuditListSecondary) {
		String mainId = esdAuditListSecondary.getMainId();
		String finalResult = esdAuditListSecondary.getFinalResult();
		String flag = esdAuditListSecondary.getFlag();
		if ("OK".equals(finalResult)){
			flag = "1";
			esdAuditListSecondary.setFlag(flag);
		}
		esdAuditMainMapper.updateEsdAuditListSecondary(esdAuditListSecondary);
		//如果修改副表中数据的最终结果，需要修改主表中的合格数量和合格率
		if ("OK".equals(finalResult)){
			EsdAuditListMain esdAuditListById = esdAuditMainMapper.getEsdAuditListById(mainId);
			Integer qualifiedNumber = esdAuditListById.getQualifiedNumber();
			Integer auditNumber = esdAuditListById.getAuditNumber();
			qualifiedNumber++;
			EsdAuditListMain esdAuditListMain = new EsdAuditListMain();
			esdAuditListMain.setId(mainId);
			esdAuditListMain.setQualifiedNumber(qualifiedNumber);
			esdAuditListMain.setQualifiedRate(getPassRate(qualifiedNumber,auditNumber));
			esdAuditMainMapper.updateEsdAuditListMain(esdAuditListMain);
		}
	}

	@Override
	public void removeEsdAudit(String id) {
		esdAuditMainMapper.removeEsdAuditSecondary(id);
		esdAuditMainMapper.removeEsdAudit(id);

	}

	@Override
	public void removeEsdAuditList(String id) {
		esdAuditMainMapper.removeEsdAuditListSecondary(id);
		esdAuditMainMapper.removeEsdAuditList(id);
	}

	@Override
	public JSONObject getEsdAuditListSecondary(String id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject result = new JSONObject();
		EsdAuditListSecondary esdAuditListSecondary = esdAuditMainMapper.getEsdAuditListSecondary(id);
		String mainId = esdAuditListSecondary.getMainId();
		result = JSONObjectUtil.toJSONObject(esdAuditListSecondary);
		EsdAuditListMain esdAuditListMain = esdAuditMainMapper.getEsdAuditListById(mainId);
		result.put("auditType",esdAuditListMain.getAuditType());
		result.put("auditDate",sdf.format(esdAuditListMain.getAuditDate()));
		result.put("frequency",esdAuditListMain.getFrequency());
		result.put("line",esdAuditListMain.getLine());
		return result;
	}

	private  void saveEsdAuditSecondary(List<EsdAuditSecondary> list,String id){
		for (EsdAuditSecondary esd : list) {
			String temp = String.valueOf(idGeneratorRunner.nextId());
			esd.setId(temp);
			esd.setMainId(id);
			esdAuditMainMapper.saveEsdAuditSecondary(esd);
		}
	}


	/**
	 * @Author yuyangyang
	 * @Description 获取合格率
	 * @Date  2020/7/2  17:35
	 * @Param
	 * @return
	 */
	private Float getPassRate(Integer pass,Integer total){
		BigDecimal passBig = BigDecimal.valueOf(Double.valueOf(pass));
		BigDecimal totalBig = BigDecimal.valueOf(Double.valueOf(total));
		BigDecimal	passRate = passBig.multiply(BigDecimal.valueOf(100)).divide(totalBig,2,BigDecimal.ROUND_HALF_DOWN);
		return passRate.floatValue();
	}

	private EsdAuditListMain transformation(EsdAuditListMain esdAuditListMain){
		String auditType = esdAuditListMain.getAuditType();
		if ("month".equals(auditType)){
			esdAuditListMain.setAuditType("月度");
		}else if ("year".equals(auditType)){
			esdAuditListMain.setAuditType("年度");
		}
		return esdAuditListMain;
	}

	/**
	 * @Author yuyangyang
	 * @Description 选择非FE的维修方式下发邮件
	 * @Date  2020/8/6  11:16
	 * @Param
	 * @return
	 */
	private  void sendEmailTohandler(String isFe,JSONObject jsonObject,Date auditDate,String auditer,String line,String creator){
		// 生成内容
		String subject = "ESD稽核不良通知";
		String content = getHandlerMailContent(isFe,subject,jsonObject,auditDate,auditer,line,creator);
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
	public String getHandlerMailContent(String handleMehhod,String theme,JSONObject jsonObject,Date auditDate,String auditer,String line,String creator) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Context context = new Context();
		context.setVariable("theme", theme);
		context.setVariable("handleMehhod", handleMehhod);
		context.setVariable("submitter",creator);
		context.setVariable("detectionDate",sdf2.format(auditDate));
		context.setVariable("detectionDateName","稽核日期");
		context.setVariable("surveyor",auditer);
		context.setVariable("surveyorName","稽核人");
		context.setVariable("number",line);
		context.setVariable("numberName","区域/线体");
		context.setVariable("isQualified",jsonObject.getString("inspectionResults"));
		context.setVariable("item",jsonObject.getString("auditObject"));
		context.setVariable("itemName","稽核对象");
		context.setVariable("handlerDate",sdf.format(new Date()));
		String template = templateEngine.process("EsdEmailTemplate", context);
		return template;
	}
}