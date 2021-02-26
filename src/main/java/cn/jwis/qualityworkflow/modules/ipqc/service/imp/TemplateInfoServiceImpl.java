package cn.jwis.qualityworkflow.modules.ipqc.service.imp;

import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditTemplateDetailInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditTemplateDetailInfoExcel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditTemplateDetailInfoTable;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditTemplateInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditTemplateInfoExcel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.AuditTemplateInfoTable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateDetail;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryTemplateInfoBean;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditTemplateInfoMapper;
import cn.jwis.qualityworkflow.modules.ipqc.service.TemplateInfoService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

@Service
public class TemplateInfoServiceImpl implements TemplateInfoService {

	@Autowired
	AuditTemplateInfoMapper auditTemplateInfoMapper;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Override
	public List<AuditTemplateInfo> getAuditTemplateList(QueryTemplateInfoBean bean) {
		bean.setAssignee(getCurrentUserName());
		List<AuditTemplateInfo> resultList = null;
		if (bean.getPageNum() != null && bean.getPageSize() != null) {
			bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
		}
		resultList = auditTemplateInfoMapper.getAuditTemplateList(bean);
		return resultList;
	}

	@Override
	public Long getAuditTemplateCount(QueryTemplateInfoBean bean) {
		bean.setAssignee(getCurrentUserName());
		Long count = auditTemplateInfoMapper.getAuditTemplateCount(bean);
		return count;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertAuditTemplateList(AuditTemplateInfo bean) {
		List<String> list = new ArrayList<String>();
		if (bean.getId() != null && bean.getId() != "") {
			bean.setUpdatePerson(getCurrentUserName());
			if (bean.getVersion() != "" && bean.getVersion() != null) {
				String version = bean.getVersion();
				int num = Integer.parseInt(version.substring(3, 4));
				bean.setVersion("V1." + String.valueOf(num + 1));

			}
			auditTemplateInfoMapper.updateAuditTemplateInfo(bean);
		} else {
			bean.setId(String.valueOf(idGeneratorRunner.nextId()));
			bean.setCreateDate(new Date());
			bean.setCreator(getCurrentUserName());
			bean.setVersion("V1.0");
			auditTemplateInfoMapper.insertAuditTemplateInfo(bean);
		}
		List<AuditTemplateDetail> jsonArray = bean.getTemplateDetail();
		List<String> arrAyList = auditTemplateInfoMapper.getAuditTemplateDetailId(bean.getId());
		for (int i = 0; i < jsonArray.size(); i++) {
			AuditTemplateDetail auditTemplateDetail = jsonArray.get(i);
			if (auditTemplateDetail.getId() != null && auditTemplateDetail.getId() != "") {
				auditTemplateInfoMapper.updateAuditTemplateDetail(auditTemplateDetail);
				for (int j = 0; j < arrAyList.size(); j++) {
					if (arrAyList.get(j).equals(auditTemplateDetail.getId())) {
						arrAyList.remove(j);
					}
					list = arrAyList;
				}
			} else {
				auditTemplateDetail.setId(String.valueOf(idGeneratorRunner.nextId()));
				auditTemplateDetail.setAuditTemplateId(bean.getId());
				auditTemplateDetail.setCreateDate(new Date());
				auditTemplateInfoMapper.insertAuditTemplateDetail(auditTemplateDetail);
			}
		}
		if (list.size() > 0) {
			auditTemplateInfoMapper.deleteAuditTemplateDetail(arrAyList);
		}

	}

	@Override
	public JSONObject getAuditTemplateInfoById(JSONObject jsonObject) {
		AuditTemplateInfo auditTemplateInfo = auditTemplateInfoMapper.getAuditTemplateInfoById(jsonObject);
		Object obj = auditTemplateInfo;
		JSONObject Object = JSONObjectUtil.toJSONObject(obj);
		if (Object != null) {
			List<AuditTemplateDetail> list = auditTemplateInfoMapper.getTemplateDetailList(jsonObject);
			for (int i = 0; i < list.size(); i++) {
				AuditTemplateDetail auditTemplateDetail = list.get(i);
				auditTemplateDetail.setSave(true);
			}
			Object.put("templateDetail", list);
		}
		return Object;
	}

	@Override
	public void deleteAuditTemplateInfoById(JSONObject jsonObject) {
		String id = jsonObject.get("AuditTemplateInfoId").toString();
		if (id != null && id != "") {
			auditTemplateInfoMapper.deleteAuditTemplateInfo(id);
		}
	}

	@Override
	public List<AuditListInfo> getListInfo(JSONObject jsonObject) {
		String id = jsonObject.get("AuditTemplateInfoId").toString();
		List<AuditListInfo> list = null;
		if (id != null && id != "") {
			list = auditTemplateInfoMapper.getListInfo(id);
		}
		return list;
	}

	@Override
	public void exportAuditTemplateInfo(HttpServletResponse response, HttpServletRequest request,
			QueryTemplateInfoBean bean) {
		List<AuditTemplateInfo> list = auditTemplateInfoMapper.exportAuditTemplateInfo(bean);
		// 将List<AuditTemplateInfo> 转换为 List<JSONObject>
		List<JSONObject> jsonObjects = new ArrayList<>(list.size());
		list.stream().forEach(d -> {
			JSONObject object = JSONObjectUtil.toJSONObject(d);
			jsonObjects.add(object);
		});
		String[] title = AuditTemplateInfoExcel;
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			title = AuditTemplateInfoDbCamel;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, AuditTemplateInfoDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, AuditTemplateInfoTable);
	}

	@Override
	public JSONObject getPullDownList() {
		List<AuditTemplateInfo> list = auditTemplateInfoMapper.getPullDownList();
		JSONObject jsonObject = new JSONObject();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			AuditTemplateInfo auditTemplateInfo = list.get(i);
			if (auditTemplateInfo.getCreator() != null && auditTemplateInfo.getCreator() != ""
					&& !list1.contains(auditTemplateInfo.getCreator())) {
				list1.add(auditTemplateInfo.getCreator());
			}
			if (auditTemplateInfo.getModel() != null && auditTemplateInfo.getModel() != ""
					&& !list2.contains(auditTemplateInfo.getModel())) {
				list2.add(auditTemplateInfo.getModel());
			}
		}
		jsonObject.put("creatorList", list1);
		jsonObject.put("modelList", list2);
		return jsonObject;

	}

	@Override
	public void downLoadAuditTemplate(HttpServletResponse response, HttpServletRequest request,
			List<AuditTemplateDetail> list) {
		// 将List<AuditTemplateInfo> 转换为 List<JSONObject>
		List<JSONObject> jsonObjects = new ArrayList<>(list.size());
		list.stream().forEach(d -> {
			JSONObject object = JSONObjectUtil.toJSONObject(d);
			jsonObjects.add(object);
		});
		String[] title = AuditTemplateDetailInfoExcel;
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			title = AuditTemplateDetailInfoDbCamel;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, AuditTemplateDetailInfoDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, AuditTemplateDetailInfoTable);
	}

	@Override
	public JSONObject getTemplateNameIdList(JSONObject jsonObject) {
		List<JSONObject> list = auditTemplateInfoMapper.getTemplateNameIdList(jsonObject);
		JSONObject object = new JSONObject();
		object.put("TemplateList", list);
		return object;
	}

	@Override
	public List<JSONObject> importAuditTemplateList(MultipartFile file, HttpServletRequest request) throws Exception {
		File file1 = null;
		String language = request.getHeader("Language");
		file1 = FileUtil.multipartFileToFile(file);
		ArrayList<String> title = new ArrayList<>(Arrays.asList(AuditTemplateDetailInfoDbCamel));
		String[] strings = AuditTemplateDetailInfoExcel;
		if ("en-US".equals(language)) {
			strings = AuditTemplateDetailInfoDbCamel;
		}

		List<JSONObject> list = ExcelUtil.readExcel(file1, title, strings);
		return list;
	}

	private String getCurrentUserName() {
		UserInfo currentUser = SessionHelper.getCurrentUser();
		String currentUserName = currentUser.getAccount();
		return currentUserName;
	}

}
