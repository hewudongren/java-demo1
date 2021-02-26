package cn.jwis.qualityworkflow.modules.linequalify.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.linequalify.bean.ESDTemplateInfo;
import cn.jwis.qualityworkflow.modules.linequalify.dao.ESDTemplateMapper;
import cn.jwis.qualityworkflow.modules.linequalify.service.ESDTemplateService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

@Service
public class ESDTemplateServiceImpl extends BaseClass implements ESDTemplateService {
	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	ESDTemplateMapper esdTemplateMapper;

	@Override
	@Transactional
	public void saveEsdTemplates(List<ESDTemplateInfo> beans) {
		esdTemplateMapper.deleteEsdTemplate();
		// 保存数据
		for (ESDTemplateInfo esdTemplateInfo : beans) {
			esdTemplateInfo.setId(String.valueOf(idGeneratorRunner.nextId()));
			esdTemplateMapper.saveEsdTemplate(esdTemplateInfo);
		}
	}

	@Override
	public List<ESDTemplateInfo> getEsdTemplateList() {
		return esdTemplateMapper.getEsdTemplateList();
	}

	@Override
	public void exportTemplate(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		ExcelUtil.setResponseHeader(response, "ESDLineTemplate.xlsx");
		String[] title = new String[] { "项目", "功能/模块", "稽核类别", "具体要求" };
		String[] strings = new String[] { "item", "module", "audit_category", "specific_requirements" };
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void exportEsdTemplateList(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) {
		List<ESDTemplateInfo> esdTemplateInfoList = esdTemplateMapper.getEsdTemplateList();
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		for (ESDTemplateInfo esdTemplateInfo : esdTemplateInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdTemplateInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response, "ESD线体认证模板.xlsx");
		String[] title = new String[] { "项目", "功能/模块", "稽核类别", "具体要求" };
		String[] strings = new String[] { "item", "module", "auditCategory", "specificRequirements" };
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}