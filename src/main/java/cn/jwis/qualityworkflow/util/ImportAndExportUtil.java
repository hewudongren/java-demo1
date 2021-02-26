package cn.jwis.qualityworkflow.util;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;

import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;



/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-25 14:25
 * @since 0.1.0
 **/
@Component
public class ImportAndExportUtil {


	@Resource
	CommonMapper commonRepo;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;



	public void importWithCheck(String table,  String validationParams,
								HttpServletRequest request, List<String> title, List<JSONObject> list) throws Exception {
		String language = request.getHeader("Language");
		try {
			Preconditions.checkArgument(CollectionUtils.isNotEmpty(list), "请勿导入空表");
			transferData(list, table, language, true);
			// 去除导入文件中 校验参数的内容
			String[] array = validationParams.split(",");
			Map<String, List<JSONObject>> map = JSONObjectUtil.groupMutipleType(list, array);
			Set<String> keySet = map.keySet();
			List<String> dbValue = commonRepo.getValidationParamKey(Arrays.asList(validationParams), table);
			keySet.removeAll(dbValue);
			title.add("id");
			title.add("creator");
			List<List<String>> saveList = new ArrayList<>();
			for (Map.Entry<String, List<JSONObject>> entry : map.entrySet()) {
				// 如果 表格中的数据检验参数 在数据库中不存在
				if (keySet.contains(entry.getKey())) {
					// 随机获取 表格中重复检验参数的第一个
					JSONObject object = entry.getValue().get(0);
					List<String> objectList = new ArrayList<>();
					// 遍历增加属性
					for (int i = 0; i < title.size() - 2; i++) {
						String value = (String) object.get(title.get(i));
						objectList.add(value);
					}
					// objectList不为空
					if (CollectionUtils.isNotEmpty(objectList)) {
						// 增加 id 和用户名
						String id = String.valueOf(idGeneratorRunner.nextId());
						String userName = UserUtil.getCurrentUserName();
						objectList.add(id);
						objectList.add(userName);
						saveList.add(objectList);
					}
				}
			}
			if (CollectionUtils.isNotEmpty(saveList)) {
				int length = saveList.size();
				if (length < 2000) {
					commonRepo.batchInsert(title, saveList, table);
				} else {
					int size = length / 2000;
					for (int i = 0; i <= size; i++) {
						int end = (i + 1) * 2000;
						if (end > length) {
							end = length;
						}
						List<List<String>> lists = saveList.subList(i * 2000, end);
						commonRepo.batchInsert(title, lists, table);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("数据插入数据库时发生错误");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public  void export(HttpServletResponse response, HttpServletRequest request, List<JSONObject> list, String tableName) throws Exception {
		String camelTableName = JSONObjectUtil.underlineToCamel(tableName);
		// 获取中文表头
		Field cnTitle = Title.class.getDeclaredField(camelTableName + "Excel");
		String[] title = (String[])cnTitle.get(Title.class);
		// 表名
		Field table = Title.class.getDeclaredField(camelTableName + "Table");
		String fileName = (String)table.get(Title.class);
		// 获取英文表头
		Field usTitle = Title.class.getDeclaredField(camelTableName + "DbCamel");
		String[] parameterName = (String[])usTitle.get(Title.class);
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			// 获取中文表头
			title = parameterName;
		}
		Workbook workbook = ExcelUtil.exporSimple(list, title, parameterName);
		ExcelUtil.outputWorkbook(response, workbook, fileName);
	}


	/**
	 * 导入导出时 根剧 数据库配置表（import_export_parameter_mapping），进行数据转换
	 *
	 * @param list          待处理的数据
	 * @param table         表名
	 * @param language      语言
	 * @param transferOrder true代表 正转 意思是从 手填字段 转换为数据库字段 比如 是否热门机型 是 转为 数据存储中的 1
	 *                      一般导入时使用 false代表 逆转 意思是从 数据库字段 转换为手填字段 比如 是否热门机型 数据库中的1
	 *                      转为 显示时的是 一般导出时使用
	 */
	public  void transferData(List<JSONObject> list, String table, String language, boolean transferOrder)
			throws Exception {
		// 如果为null 或者size 为 0 直接返回
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		//获取需要转换的字段
		List<JSONObject> transferParameter = commonRepo.getTransferParameter(table);

		Map<String, List<JSONObject>> parameterMap = JSONObjectUtil.groupMutipleType(transferParameter, "parameter");
		Set<String> set = parameterMap.keySet();
		// 如果 set size为 0, 则说明不需要进行转换
		if (set.size() == 0) {
			return;
		}
		list.stream().forEach(d -> {
			for (String key : set) {
				// 如果为null,则继续
				String value = d.getString(key);
				if (value == null) {
					continue;
				}
				List<JSONObject> jsonObjects = parameterMap.get(key);
				// 正转
				if (transferOrder) {
					// 这里拦截出来必然只有一个
					List<JSONObject> objects = jsonObjects.stream()
							.filter(c -> value.equals(c.getString("transfer_data"))).collect(Collectors.toList());
					if (CollectionUtils.isNotEmpty(objects)) {
						String dbData = objects.get(0).getString("db_data");
						d.put(key, dbData);
					}
				} else {
					// 这里拦截出来必然只有一个
					List<JSONObject> objects = jsonObjects.stream().filter(c -> value.equals(c.getString("db_data")))
							.collect(Collectors.toList());
					if (CollectionUtils.isNotEmpty(objects)) {
						String dbData = objects.get(0).getString("transfer_data");
						d.put(key, dbData);
					}
				}
			}
		});
	}
}
