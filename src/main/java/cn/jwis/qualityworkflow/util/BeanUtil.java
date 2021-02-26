package cn.jwis.qualityworkflow.util;

import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-08 17:25
 * @since 0.1.0
 **/

public class BeanUtil {

	private static SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @description: 将Map对象通过反射机制转换成Bean对象
	 * 目前只支持
	 * @author: xujinbiao
	 * @date: 2020/5/8 17:40
	 * @param mapResult:
	 * @param clazz:
	 * @return: java.lang.Object
	 **/
	public static Object mapToBean(JSONObject mapResult, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		if (mapResult != null && mapResult.size() > 0) {
			for (Map.Entry<String, Object> entry : mapResult.entrySet()) {
				// 属性名
				String propertyName = entry.getKey();
				Object value = entry.getValue();
				// get set 方法需要按照规则命名
				String setMethodName = "set"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				// 获取属性字段
				Field field = getClassField(clazz, propertyName);
				if (field == null) {
					continue;
				}
				// 查看属性的类型
				Class<?> fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				try {
					clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * @description: 将Object类型的值，转换成bean对象属性里对应的类型值
	 * 目前只支持 Long, Integer, Double, Float, Boolean, Date 类型
	 * @author: xujinbiao
	 * @date: 2020/5/8 17:45
	 * @param value:
	 * @param fieldTypeClass:
	 * @return: java.lang.Object
	 **/
	private static Object convertValType(Object value, Class<?> fieldTypeClass) throws Exception{
		Object retVal = null;
		if(Long.class.getName().equals(fieldTypeClass.getName())
				|| long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if(Integer.class.getName().equals(fieldTypeClass.getName())
				|| int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if(Float.class.getName().equals(fieldTypeClass.getName())
				|| float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if (Boolean.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Boolean.parseBoolean(value.toString());
		}
		else if(Double.class.getName().equals(fieldTypeClass.getName())
				|| double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else if (Date.class.getName().equals(fieldTypeClass.getName())){
			String str = value.toString();
			if (str.length() == 10) {
				retVal = dateFormat.parse(str);
			} else  {
				retVal = timeDateFormat.parse(str);
			}
		} else {
			retVal = value;
		}
		return retVal;
	}

	/**
	 * @description: 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
	 * @author: xujinbiao
	 * @date: 2020/5/8 17:42
	 * @param clazz:
	 * @param fieldName:
	 * @return: java.lang.reflect.Field
	 **/
	private static Field getClassField(Class<?> clazz, String fieldName) {
		if (Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		// 递归父类 属性
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null) {
			return getClassField(superClass, fieldName);
		}
		return null;
	}

	/**
	 * @Author yuyangyang
	 * @Description 转换成历史记录的保存
	 * @Date  2020/5/13  16:13
	 * @Param
	 * @return
	 */
	public static CreateHistoryProcessVo generateHistoryProcessRecord(JSONObject jsonObject, String type, String templateKey, String workflowBusinessid, String workflowNode) {
		CreateHistoryProcessVo historyProcess = new CreateHistoryProcessVo();
		historyProcess.setTemplateKey(templateKey);
		historyProcess.setWorkflowBusinessid(workflowBusinessid);
		historyProcess.setWorkflowNode(workflowNode);
		historyProcess.setType(type);
		historyProcess.setContent(jsonObject);
		String department = jsonObject.getString("department");
		historyProcess.setDepartment(department);
		return historyProcess;
	}
    /**
     * @Author yuyangyang
     * @Description 增加结束日期的日期，添加用户
     * @Date  2020/5/28  14:40
     * @Param
     * @return
     */
	public  static <T> void addDay(T bean) throws Exception {
		String name = getCurrentUserName();
		Method setAssignee = bean.getClass().getMethod("setAssignee",String.class);
		setAssignee.invoke(bean,name);
		Method getEndTime  = bean.getClass().getMethod("getEndTime");
		Method setEndTime  = bean.getClass().getMethod("setEndTime",String.class);
		String endTime = (String) getEndTime.invoke(bean);
		if (StringUtils.isNotEmpty(endTime)) {
			endTime = DateUtil.plusOneDay(endTime);
			setEndTime.invoke(bean,endTime);
		}
	}

   /**
    * @Author yuyangyang
    * @Description 页码转换问题
    * @Date  2020/5/28  14:41
    * @Param
    * @return
    */
	public static<T> void pageChange(T bean) throws  Exception{
		Method getPage  = bean.getClass().getMethod("getPage");
		Method setPage  = bean.getClass().getMethod("setPage",Integer.class);
		Method getSize  = bean.getClass().getMethod("getSize");
		Integer page = (Integer) getPage.invoke(bean);
		Integer size = (Integer) getSize.invoke(bean);
		if (page != 0 && page != null && size != 0 && size != null){
			page = (page-1)*size;
		    setPage.invoke(bean,page);
		}
	}
	/**
	 * @Author yuyangyang
	 * @Description 根据数据库规则转换数据(导出-数据库字段转换实际字段)
	 * @Date  2020/7/20  16:02
	 * @Param
	 * @return
	 */
	public static List<JSONObject> transformationJson(List<JSONObject> list,List<JSONObject> parameterList){
		JSONObject mapping = new JSONObject();
		Set<String> set = new HashSet<>();
		for (JSONObject json : parameterList){
			String parameter = json.getString("parameter");
			String key = parameter +"-"+json.getString("db_data");
			String value = json.getString("transfer_data");
			mapping.put(key,value);
			set.add(parameter);
		}
		for (JSONObject temp : list){
			for (String key:set){
				String string = temp.getString(key);
				if (StringUtils.isNotEmpty(string)){
					String str = key+"-"+string;
					String string1 = mapping.getString(str);
					if(StringUtils.isNotEmpty(string1)){
						temp.put(key,string1);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @Author yuyangyang
	 * @Description 根据数据库规则转换数据(导入-实际字段转换数据库字段)
	 * @Date  2020/7/20  16:11
	 * @Param
	 * @return
	 */
	public static List<JSONObject> transformationJson2(List<JSONObject> list,List<JSONObject> parameterList){
		JSONObject mapping = new JSONObject();
		Set<String> set = new HashSet<>();
		for (JSONObject json : parameterList){
			String parameter = json.getString("parameter");
			String key = parameter +"-"+json.getString("transfer_data");
			String value = json.getString("db_data");
			mapping.put(key,value);
			set.add(parameter);
		}
		for (JSONObject temp : list){
			for (String key:set){
				String string = temp.getString(key);
				if (StringUtils.isNotEmpty(string)){
					String str = key+"-"+string;
					String string1 = mapping.getString(str);
					if(StringUtils.isNotEmpty(string1)){
						temp.put(key,string1);
					}
				}
			}
		}
		return list;
	}
}
