package cn.jwis.qualityworkflow.modules.apqp.service.imp;

import static cn.jwis.qualityworkflow.util.Title.APQPModelInfoDB;
import static cn.jwis.qualityworkflow.util.Title.APQPModelInfoDbCamel;
import static cn.jwis.qualityworkflow.util.Title.APQPModelInfoExcel;
import static cn.jwis.qualityworkflow.util.Title.APQPModelTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenance;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenanceExample;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocumentExample;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelCascadeVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelListVo;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpModelMaintenanceMapper;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpSubdocumentMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.ModelMaintenanceService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.ImportAndExportUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import cn.jwis.qualityworkflow.util.UserUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-22 17:49
 * @since 0.1.0
 **/
@Service
public class ModelMaintenanceServiceImpl implements ModelMaintenanceService {

	@Autowired
	ApqpModelMaintenanceMapper modelDao;

	@Resource
	ApqpSubdocumentMapper subdocumentDao;

	@Autowired
	ReflectUtil reflectUtil;

	@Autowired
	ImportAndExportUtil importAndExportUtil;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;


	@Override
	public PageInfo<ApqpModelMaintenance> getInfoList(QueryModelListVo bean) throws Exception {
		reflectUtil.preHandledPageBean(bean);
		Page<ApqpModelMaintenance> list = modelDao.getList(bean);
		PageInfo<ApqpModelMaintenance> page = new PageInfo<>(list);
		return page;
	}

	/**
	 * @description:  新增接口， 需要校验机型唯一性
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:56
	 * @param bean:
	 * @return: void
	 **/
	@Override
	public void insert(ApqpModelMaintenance bean) throws Exception {
		boolean unique = checkUniqueModelName(bean.getModel());
		if (!unique) {
			long id = idGeneratorRunner.nextId();
			bean.setId(String.valueOf(id));
			String currentUserName = UserUtil.getCurrentUserName();
			bean.setCreator(currentUserName);
			modelDao.insert(bean);
		} else {
			throw new RuntimeException("机型名称重复");
		}
	}

	@Override
	public void update(ApqpModelMaintenance bean) throws Exception {
		boolean unique = checkUniqueModelName(bean.getModel());
		ApqpModelMaintenance model = modelDao.selectByPrimaryKey(bean.getId());
		// 机型名称 是否 与相同id的名称 重复
		boolean sameName = model.getModel().equals(bean.getModel());
		if (!unique || sameName) {
			modelDao.updateByPrimaryKeySelective(bean);
		} else {
			throw new RuntimeException("机型名称重复");
		}
	}

	@Override
	public void delete(String id) throws Exception {
		if (id != null) {
			// 判定机型是否被子单据使用
			ApqpSubdocumentExample example = new ApqpSubdocumentExample();
			int count = subdocumentDao.countByExample(example);
			if (count == 0) {
				int num = modelDao.deleteByPrimaryKey(id);
				if (num != 1) {
					throw new RuntimeException("删除失败");
				}
			} else {
				throw new RuntimeException("删除失败,该机型已被单据使用");
			}
		}
	}


	@Override
	public List<Object> getPullDownValue(String parameter) throws Exception{
		String underlineParameter = JSONObjectUtil.camelToUnderline(parameter);
		List<Object> pullDownValue = modelDao.getPullDownValue(underlineParameter);
		return pullDownValue;
	}

	@Override
	public void exportTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] title = new String[APQPModelInfoExcel.length - 3];
		// 默认中文
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			System.arraycopy(APQPModelInfoDbCamel, 0, title, 0, APQPModelInfoDbCamel.length - 3);
		} else {
			System.arraycopy(APQPModelInfoExcel, 0, title, 0, APQPModelInfoExcel.length - 3);
		}
		Workbook workbook = ExcelUtil.exporSimple(null, title, null);
		ExcelUtil.outputWorkbook(response, workbook, APQPModelTemplate);
	}

	@Override
	public void importWithCheck(MultipartFile file, HttpServletRequest request) throws Exception {
		String language = request.getHeader("Language");
		// 获取数据库对应的属性
		List<String> title = new ArrayList<>(APQPModelInfoDB.length - 3);
		for (int i = 0; i < APQPModelInfoDB.length - 3; i++) {
			title.add(APQPModelInfoDB[i]);
		}
		// 或取
		String[] titles = new String[APQPModelInfoDB.length - 3];
		if (("en-US").equals(language)) {
			System.arraycopy(APQPModelInfoDbCamel, 0, titles, 0, APQPModelInfoDbCamel.length - 3);
		} else {
			System.arraycopy(APQPModelInfoExcel, 0, titles, 0, APQPModelInfoExcel.length - 3);
		}
		File file1 = null;
		try  {
			file1 = FileUtil.multipartFileToFile(file);
			List<JSONObject> list = ExcelUtil.readExcel(file1, title, titles);
			importAndExportUtil.importWithCheck("apqp_model_maintenance", "model", request, title, list);
		}
		finally {
			if (file1 != null) {
				FileUtil.deleteDir(file1);
			}
		}
	}

	@Override
	public String getPreviousProduct(String model) throws Exception {
		String previousProduct = modelDao.getPreviousProduct(model);
		return previousProduct;
	}

	@Override
	public List<String> getCascadeValue(QueryModelCascadeVo bean) throws Exception {
		String parameter = bean.getParamter();
		// paramter不为null 且防止SQL注入
		if (StringUtils.isNotEmpty(parameter) && !parameter.contains(";")) {
			String value = JSONObjectUtil.camelToUnderline(parameter);
			bean.setParamter(value);
			List<String> cascadeValue = modelDao.getCascadeValue(bean);
			return cascadeValue;
		}
		return null;
	}

	/**
	 * @description: 校验机型名称唯一性
	 * @author: xujinbiao
	 * @date: 2020/5/22 17:58
	 * @return: boolean
	 **/
	private boolean checkUniqueModelName(String name) {
		ApqpModelMaintenanceExample example = new ApqpModelMaintenanceExample();
		ApqpModelMaintenanceExample.Criteria criteria = example.createCriteria();
		if (name != null) {
			criteria.andModelEqualTo(name);
		}
		int num = modelDao.countByExample(example);
		if (num == 0) {
			return false;
		}
		return true;
	}

}
