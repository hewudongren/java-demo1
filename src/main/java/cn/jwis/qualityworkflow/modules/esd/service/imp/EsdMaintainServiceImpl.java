package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.util.Title.EsdInStanInfoDb;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanInfoDb2;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanInfoExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanInfoExcel2;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanInfoName;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanTemplateDb;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanTemplateDb2;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanTemplateExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdInStanTemplateExcel2;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoDb;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoDb2;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoExcel2;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoName;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoTemplateDb;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoTemplateDb2;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoTemplateExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdSamplingLevelInfoTemplateExcel2;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSamplingLevelInfo;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdMaintainMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdMaintainService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;

/**
 * @Description Esd维护页面的接口
 * @Author yuyangyang
 * @Date 2020/5/9 11:31
 */
@Service
public class EsdMaintainServiceImpl extends BaseClass implements EsdMaintainService  {

	@Autowired
	EsdMaintainMapper esdMaintainMapper;
	@Autowired
	IDGeneratorRunner idGeneratorRunner;
	@Autowired
	CommonMapper commonMapper;

	@Override
	public void savaEsdInStanInfo(EsdInStanInfo esdInStanInfo) {
		String creater = getCurrentUserName();
		String id = String.valueOf(idGeneratorRunner.nextId());
		esdInStanInfo.setId(id);
		esdInStanInfo.setCreator(creater);
		esdMaintainMapper.savaEsdInStanInfo(esdInStanInfo);
	}

	@Override
	public void updateEsdInStanInfo(EsdInStanInfo esdInStanInfo) {
		esdMaintainMapper.updateEsdInStanInfo(esdInStanInfo);
	}

	@Override
	public void removeEsdInStanInfo(String id) {
		esdMaintainMapper.removeEsdInStanInfo(id);
	}

	@Override
	public List<EsdInStanInfo> findEsdInStanInfo(EsdInStanSearch esdInStanSearch) {
		int page = esdInStanSearch.getPage();
		int size = esdInStanSearch.getSize();
		page = (page -1)*size;
		esdInStanSearch.setPage(page);
		return esdMaintainMapper.findEsdInStanInfo(esdInStanSearch);
	}

	@Override
	public void exportEsdInStanInfoTemplate(HttpServletResponse response,String category,String fileName) {
		ExcelUtil.setResponseHeader(response,fileName);
		String[] Title = EsdInStanTemplateExcel2;
		if ("cycle".equals(category)){
			Title = EsdInStanTemplateExcel;
		}
		Workbook workbook = ExcelUtil.exporSimple(null,Title,null);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportEsdInStanInfo(EsdInStanSearch esdInStanSearch, HttpServletResponse response) {
		String category = esdInStanSearch.getCategory();
		//导出将页码的限制去除
		esdInStanSearch.setPage(null);
		esdInStanSearch.setSize(null);
		List<EsdInStanInfo> esdInStanInfoList= esdMaintainMapper.findEsdInStanInfo(esdInStanSearch);
        List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdInStanInfo esdInStanInfo:esdInStanInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdInStanInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,EsdInStanInfoName);
		String[] Title = EsdInStanInfoExcel2;
		String[] strings = EsdInStanInfoDb2;
		if ("cycle".equals(category)){
			Title = EsdInStanInfoExcel;
			strings = EsdInStanInfoDb;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,Title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public Boolean importEsdInStanInfoWithId(MultipartFile file, HttpServletRequest request,String category) {
		Boolean flag = false;
		File file1 = null;
		try {
			file1 = FileUtil.multipartFileToFile(file);
			List<String> title = new ArrayList<>();
			String[] strings = EsdInStanTemplateExcel2;
			title = Arrays.asList(EsdInStanTemplateDb2);
			if ("cycle".equals(category)){
				title = Arrays.asList(EsdInStanTemplateDb);
				strings = EsdInStanTemplateExcel;
			}

			List<JSONObject> list = ExcelUtil.readExcel(file1, title,strings);
			if (isNull(list)) {
				throw new BaseException("请勿导入空表");
			}
			for (JSONObject temp : list) {
				temp.put("id", idGeneratorRunner.nextId());
				temp.put("creator",getCurrentUserName() );
				temp.put("category",category);
				List<JSONObject> list1 = JSONObjectUtil.separate(temp);
				commonMapper.saveDate(list1, "esd_inspection_standard_info");
				flag = true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		} finally {
			FileUtil.deleteDir(file1);
		}
		return flag;
	}

	@Override
	public void savaEsdSamplingLevelInfo(EsdSamplingLevelInfo esdSamplingLevelInfo) {
		String creater = getCurrentUserName();
		String id = String.valueOf(idGeneratorRunner.nextId());
		esdSamplingLevelInfo.setId(id);
		esdSamplingLevelInfo.setCreator(creater);
		esdMaintainMapper.savaEsdSamplingLevelInfo(esdSamplingLevelInfo);
	}

	@Override
	public void updateEsdSamplingLevelInfo(EsdSamplingLevelInfo esdSamplingLevelInfo) {
		esdMaintainMapper.updateEsdSamplingLevelInfo(esdSamplingLevelInfo);
	}

	@Override
	public void removeEsdSamplingLevelInfo(String id) {
		esdMaintainMapper.removeEsdSamplingLevelInfo(id);
	}

	@Override
	public List<EsdSamplingLevelInfo> findEsdSamplingLevelInfo(EsdInStanSearch esdInStanSearch) {
		int page = esdInStanSearch.getPage();
		int size = esdInStanSearch.getSize();
		page = (page -1)*size;
		esdInStanSearch.setPage(page);
		return esdMaintainMapper.findEsdSamplingLevelInfo(esdInStanSearch);
	}

	@Override
	public void exportEsdSamplingLevelInfoTemplate(HttpServletResponse response,String category,String fileName) {
		ExcelUtil.setResponseHeader(response,fileName);
		String[] Title = EsdSamplingLevelInfoTemplateExcel2;
		if ("cycle".equals(category)){
			Title = EsdSamplingLevelInfoTemplateExcel;
		}
		Workbook workbook = ExcelUtil.exporSimple(null,Title,null);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportEsdSamplingLevelInfo(EsdInStanSearch esdInStanSearch, HttpServletResponse response) {
		String category = esdInStanSearch.getCategory();
		//导出将页码的限制去除
		esdInStanSearch.setPage(null);
		esdInStanSearch.setSize(null);
		List<EsdSamplingLevelInfo> esdInStanInfoList= esdMaintainMapper.findEsdSamplingLevelInfo(esdInStanSearch);
		List<JSONObject> jsonObjects = new ArrayList<>();
		for (EsdSamplingLevelInfo esdSamplingLevelInfo:esdInStanInfoList) {
			JSONObject object = JSONObjectUtil.toJSONObject(esdSamplingLevelInfo);
			jsonObjects.add(object);
		}
		ExcelUtil.setResponseHeader(response,EsdSamplingLevelInfoName);
		String[] Title = EsdSamplingLevelInfoExcel2;
		String[] strings = EsdSamplingLevelInfoDb2;
		if ("cycle".equals(category)){
			Title = EsdSamplingLevelInfoExcel;
			strings = EsdSamplingLevelInfoDb;
		}
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects,Title,strings);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public Boolean importEsdSamplingLevelInfoWithId(MultipartFile file, HttpServletRequest request,String category) {
		Boolean flag = false;
		File file1 = null;
		try {
			file1 = FileUtil.multipartFileToFile(file);
			List<String> title = new ArrayList<>();
			title = Arrays.asList(EsdSamplingLevelInfoTemplateDb2);
			String[] strings = EsdSamplingLevelInfoTemplateExcel2;
			if ("cycle".equals(category)){
				title = Arrays.asList(EsdSamplingLevelInfoTemplateDb);
				strings = EsdSamplingLevelInfoTemplateExcel;
			}
			List<JSONObject> list = ExcelUtil.readExcel(file1, title,strings);
			if (isNull(list)) {
				throw new BaseException("请勿导入空表");
			}
			for (JSONObject temp : list) {
				temp.put("id", idGeneratorRunner.nextId());
				temp.put("creator",getCurrentUserName() );
				temp.put("category",category);
				List<JSONObject> list1 = JSONObjectUtil.separate(temp);
				commonMapper.saveDate(list1, "esd_sampling_level_info");
				flag = true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		} finally {
			FileUtil.deleteDir(file1);
		}
		return flag;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request, String category) {
		Map<String,String> title = new LinkedHashMap<>();
		if ("cycle".equals(category)){
			title = Title.getTitle(request,EsdInStanInfoDb,EsdInStanInfoExcel,EsdInStanInfoDb);
		}else {
			title = Title.getTitle(request,EsdInStanInfoDb2,EsdInStanInfoExcel2,EsdInStanInfoDb2);
		}
		return title;
	}

	@Override
	public Map<String, String> getTitle2(HttpServletRequest request, String category) {
		Map<String,String> title = new LinkedHashMap<>();
		if ("cycle".equals(category)){
			title = Title.getTitle(request,EsdSamplingLevelInfoDb,EsdSamplingLevelInfoExcel,EsdSamplingLevelInfoDb);
		}else {
			title = Title.getTitle(request,EsdSamplingLevelInfoDb2,EsdSamplingLevelInfoExcel2,EsdSamplingLevelInfoDb2);
		}
		return title;
	}


}