package cn.jwis.qualityworkflow.util;

import static cn.jwis.qualityworkflow.constants.Constants.FILLVALUE;
import static cn.jwis.qualityworkflow.constants.Constants.MODEL;
import static cn.jwis.qualityworkflow.constants.Constants.TIMEDATA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * Created by yu Created date 2019/8/21 11:21
 */
public class JSONObjectUtil {


	private static SerializeConfig mapping = new SerializeConfig();


	/**
	 * @description: 将类转换为JSONObject
	 * @author: xujinbiao
	 * @date: 2020/4/29 10:18
	 * @param list:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	public static List<JSONObject> toJSONObjectList(List list) {
		List<JSONObject> result = new ArrayList<>(list.size());
		for (Object object : list) {
			if (object instanceof String) {
				result.add(JSONObject.parseObject((String) object));
			}
			String str = JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
			result.add(JSONObject.parseObject(str));
		}
		return result;
	}

	/**
	 * @description: 将类转换为JSONObject
	 * @author: xujinbiao
	 * @date: 2020/4/29 10:18
	 * @param object:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	public static JSONObject toJSONObject(Object object) {
		if (object instanceof String) {
			return JSONObject.parseObject((String)object);
		}
		String str = JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
		return JSONObject.parseObject(str);
	}

	/**
	 * @description: 使用自定义的时间格式 转换JSONObject
	 * @author: xujinbiao
	 * @date: 2020/4/29 10:26
	 * @param dateFormat:
	 * @param object:
	 * @return: com.alibaba.fastjson.JSONObject
	 **/
	public static JSONObject toJSONObject(String dateFormat, Object object) {
		if (object instanceof String) {
			return JSONObject.parseObject((String)object);
		}
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		String str = JSON.toJSONString(object, mapping);
		return JSONObject.parseObject(str);
	}
	/**
	 * 驼峰转下划线
	 *
	 * @param str
	 * @return
	 */
	public static final Logger log = LoggerFactory.getLogger(JSONObjectUtil.class);

	public static String camelToUnderline(String str) {
		if (str == null || str.trim().isEmpty()) {
			return "";
		}
		int len = str.length();
		StringBuilder sb = new StringBuilder(len);
		sb.append(str.substring(0, 1).toLowerCase());
		for (int i = 1; i < len; i++) {
			char c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append("_");
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static JSONObject camelToUnderlineKey(JSONObject temp){
		JSONObject result = new JSONObject();
		Set<String> keySet = temp.keySet();
		for (String key : keySet) {
			result.put(camelToUnderline(key),temp.get(key));
		}
		return result;
	}

	/**
	 * @description: 下划线转驼峰
	 * @author: xujinbiao
	 * @date: 2020/5/29 16:36
	 * @param str:
	 * @return: java.lang.String
	 **/
	public static String underlineToCamel(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '_') {
				i++;
				sb.append(Character.toUpperCase(str.charAt(i)));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	/**
	 * 将JSON对象按照key和value分成多个的JSON对象
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static List<JSONObject> separate(JSONObject jsonObject) {
		List<JSONObject> result = new ArrayList<>();
		jsonObject.keySet().forEach((key) -> {
			JSONObject object = new JSONObject();
			// 避免传输过来的key是驼峰状态
			object.put("key", camelToUnderline(key));
			object.put("value", (("").equals(jsonObject.get(key))) ? null : jsonObject.get(key));
			result.add(object);
		});
		return result;
	}



	/**
	 * 将JSON对象 获取 key 和 Value value 不是 List值
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static List<JSONObject> separateCondition(JSONObject jsonObject) {
		List<JSONObject> result = new ArrayList<>();
		for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
			if (!(entry.getValue() instanceof List)) {
				JSONObject object = new JSONObject();
				// 避免传输过来的key是驼峰状态
				object.put("key", camelToUnderline(entry.getKey()));
				object.put("value", (("").equals(entry.getValue())) ? null : entry.getValue());
				result.add(object);
			}
		}
		return result;
	}

	/**
	 * 将JSON对象 获取 key 和 Value value 是 List值
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static List<JSONObject> separateCollection(JSONObject jsonObject) {
		List<JSONObject> result = new ArrayList<>();
		for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
			if (entry.getValue() instanceof List) {
				JSONObject object = new JSONObject();
				// 避免传输过来的key是驼峰状态
				object.put("key", camelToUnderline(entry.getKey()));
				object.put("value", entry.getValue());
				result.add(object);
			}
		}
		return result;
	}

//目前已废弃
	public static JSONObject separateJSONObject2(JSONObject object, boolean flag) {
		JSONObject jsonObject = new JSONObject();
		if (object != null) {
			for (Map.Entry<String, Object> entry : object.entrySet()) {
				if (entry.getValue() instanceof List && flag) {
					jsonObject.put(entry.getKey(), entry.getValue());
				} else if (!(entry.getValue() instanceof List) && !flag) {
					jsonObject.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return jsonObject;
	}

	/**
	 * 挑选出传入Object中为 key:Value ,如果flag为true挑选值为array的值， 如果为false挑选值为object的值
	 * 
	 * @param object
	 * @param flag   true 挑选array值, false挑选 object值
	 * @return
	 */
	public static JSONObject separateJSONObject(JSONObject object, boolean flag) {
		JSONObject jsonObject = new JSONObject();
		if (object != null) {
			for (Map.Entry<String, Object> entry : object.entrySet()) {
				String key = entry.getKey();
				if ("model".equals(entry.getKey())) {
					key = MODEL;
				}
				String value = (String) entry.getValue();
				if (value.contains(",") && flag) {
					String[] array = value.split(",");
					jsonObject.put(key, Arrays.asList(array));
				} else if (!(value.contains(",")) && !flag) {
					// 如果是""， 说明是用户没传值，期望获得全选, 则不将此加入
					if (!"".equals(value)) {
						jsonObject.put(key, value);
					}
				}
			}
		}
		return jsonObject;
	}

	/**
	 * 对List<JSONObejct> 根据传入sortKey排序
	 * 
	 * @param list
	 * @return
	 */
	public static List<JSONObject> sortJSONObejct(List<JSONObject> list, String sortKey) {
		// 周可以按照字符串排序即可
		Collections.sort(list, new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				String val1 = o1.getString(sortKey);
				String val2 = o2.getString(sortKey);
				return val1.compareTo(val2);
			}
		});
		return list;
	}

	// 将List<Map> 转出List<JSONObject>
	public static synchronized List<JSONObject> parseMapToJsonObejct(List<Map<String, Object>> data) {
		List<JSONObject> trentChart = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(data)) {
			data.stream().forEach(d -> {
				JSONObject object = new JSONObject(d);
				trentChart.add(object);
			});
		}
		return trentChart;
	}

	/**
	 * 追加缺失的时间区间数据
	 * 
	 * @param betweenDates 追加时间区间
	 * @param resultList   待追加数据
	 * @param groupTypes   分类的字段 也就是纵轴的字段
	 * @return
	 */
	public static synchronized JSONObject paddingData(Set<String> betweenDates, List<JSONObject> resultList,
			String... groupTypes) {
		long start = System.currentTimeMillis();
		Set<String> template = null;
		// 去除groupType为null的JSONObejct
		Iterator<JSONObject> iterator = resultList.iterator();
		while (iterator.hasNext()) {
			JSONObject next = iterator.next();
			for (String groupType : groupTypes) {
				String value = next.getString(groupType);
				if (value == null) {
					iterator.remove();
					continue;
				}
			}
		}
		// 获取追加模板
		if (CollectionUtils.isNotEmpty(resultList)) {
			JSONObject object = resultList.get(0);
			template = object.keySet();
		}
		// 得到一个根据 传入键 分类的map
		Map<String, List<JSONObject>> map = new ConcurrentHashMap<>();
		// 如果是time的话 说明按照时间分类， 需要取得Total数据
		if ("time".equals(groupTypes[0]) && resultList.size() > 0) {
			map.put("Total", resultList);
		} else {
			map = new ConcurrentSkipListMap<>(Comparator.naturalOrder());
			map.putAll(groupMutipleType(resultList, groupTypes));
		}
		// 插入空白的时间
		JSONObject data = new JSONObject(true);
		for (Map.Entry<String, List<JSONObject>> entry : map.entrySet()) {
			List<JSONObject> value = entry.getValue().stream().filter(d -> d.getString("time") != null)
					.collect(Collectors.toList());
			JSONArray array = new JSONArray();
			for (String date : betweenDates) {
				// 如果包含当前的时间
				List<JSONObject> objectList = value.stream().filter(j -> j.getString("time").equals(date))
						.collect(Collectors.toList());
				if (objectList != null && objectList.size() == 1) {
					JSONObject jsonObject = objectList.get(0);
					array.add(jsonObject);
				} else {
					JSONObject jsonObject = new JSONObject();
					if (template != null) {
						for (String str : template) {
							jsonObject.put(str, FILLVALUE);
						}
					}
					jsonObject.put("time", date);
					array.add(jsonObject);
				}
			}
			data.put(entry.getKey(), array);
		}
		JSONObject result = new JSONObject();
		result.put(TIMEDATA, data);
		result.put("time", betweenDates);
		log.info("拼接缺失数据耗时:" + (System.currentTimeMillis() - start));
		return result;
	}

	/**
	 * 多维度分类 数据
	 * 
	 * @param list       需要被分类的list
	 * @param groupTypes 需要分类的维度
	 * @return
	 */
	public static Map<String, List<JSONObject>> groupMutipleType(List<JSONObject> list, String... groupTypes) {
		Map<String, List<JSONObject>> map = new ConcurrentHashMap<>();
		list.stream().forEach(d -> {
			StringBuffer key = new StringBuffer("");
			// 将要分类的数据 拼成一个key
			int len = groupTypes.length;
			for (int i = 0; i < len; i++) {
				String value = Optional.ofNullable(d.getString(groupTypes[i])).orElse("NULL");
				key.append(value);
				if (i < len - 1) {
					key.append("-");
				}
			}
			// 看看map中 是否存在这个key，存在则将其放入对应的list中，
			// 不存在则新建一个list，将数据放进去，再将这个key 和 list放进去
			List<JSONObject> groupList = map.get(key.toString());
			if (groupList == null) {
				groupList = new ArrayList<>();
				groupList.add(d);
				map.put(key.toString(), groupList);
			} else {
				groupList.add(d);
			}
		});
		return map;
	}

	/**
	 * 根据时间按照业务逻辑得到日期
	 */
	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -8);
		date = cal.getTime();
		temp = sdf.format(date);
		return temp;
	}

	/**
	 * 根据时间按照业务逻辑得到白夜班
	 */
	public static String getShift(Date date) {
		String temp = "夜班";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -8);
		date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int result = Integer.valueOf(sdf.format(date));
		if (result < 12) {
			temp = "白班";
		}
		return temp;
	}

	/**
	 * map 转 JSONObejct
	 */
	public static JSONObject mapToJSON(Map<String, Object> map, String dateString) {
		JSONObject jsonObject = new JSONObject();
		for (String str : map.keySet()) {
			if (("warningKey").equals(str) || ("targetKey").equals(str)) {
				continue;
			}
			jsonObject.put(str, map.get(str));
		}
		return jsonObject;
	}

	/**
	 * 是否超出预警
	 */
	public static boolean isWarning(String target, String fpy, Map<String, String> targetMap) {
		String target1 = targetMap.get(target);
		if (StringUtils.isEmpty(target1) || StringUtils.isEmpty(fpy) || "-".equals(fpy)) {
			return false;
		} else {
			if (Double.valueOf(fpy) >= Double.valueOf(target1)) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 *
	 */
	/**
	 * map 转 JSONObejct
	 */
	public static JSONObject mapToJSON2(Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject();
		for (String str : map.keySet()) {
			if (("stationKey").equals(str) || ("targetKey").equals(str) || ("modelKey").equals(str)
					|| ("abscissa").equals(str) || ("totalKey").equals(str)) {
				continue;
			}
			jsonObject.put(str, map.get(str));
		}
		return jsonObject;
	}

	public static String getValue(JSONObject jsonObject, String key) {
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object object = jsonObject.get(key);
		if (object != null) {
			if (object instanceof Date) {
				result = sdf.format(object);
			} else {
				result = object.toString();
			}
		}
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description JSON数组转换成LIST<String>
	 * @Date  2020/5/15  15:46
	 * @Param
	 * @return
	 */
	public static List<String> jsonArrayToList(JSONArray jsonArray){
		List<String> list = new ArrayList<>();
		if (jsonArray != null && jsonArray.size() != 0){
			String jsonStr = JSONObject.toJSONString(jsonArray);
			list = JSONObject.parseArray(jsonStr, String.class);
		}
		return  list;
	}
	/**
	 * @Author yuyangyang
	 * @Description JSON数组转换成LIST<JSONObject>
	 * @Date  2020/5/15  15:46
	 * @Param
	 * @return
	 */
	public static List<JSONObject> jsonArrayToList2(JSONArray jsonArray){
		List<JSONObject> list = new ArrayList<>();
		if (jsonArray != null && jsonArray.size() != 0) {
			String jsonStr = JSONObject.toJSONString(jsonArray);
			list = JSONObject.parseArray(jsonStr, JSONObject.class);
		}
		return  list;
	}
	/**
	 * @Author yuyangyang
	 * @Description 将List<JSONObject>根据对应的key拼接成一个JSONObject
	 * @Date  2020/8/4  9:18
	 * @Param
	 * @return
	 */
	public static JSONObject formatByKey(List<JSONObject> list,List<String> keyList,String valueKey){
		JSONObject result = new JSONObject();
		for (JSONObject temp:list) {
			StringBuffer keyBuffer = new StringBuffer();
			for (String key:keyList) {
				String string = temp.getString(key);
				keyBuffer.append(string);
			}
			String string = temp.getString(valueKey);
			result.put(keyBuffer.toString(),string);
		}
		return result;
	}

}
