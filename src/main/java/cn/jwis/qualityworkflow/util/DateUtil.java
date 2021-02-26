package cn.jwis.qualityworkflow.util;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.enums.DateType;
import cn.jwis.qualityworkflow.modules.qims.dao.BlackDashBoardMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DateUtil extends BaseClass {

	@Autowired
	BlackDashBoardMapper blackDashBoardMapper;

	public static Log log = LogFactory.getLog(DateUtil.class);
	private static SimpleDateFormat numberMonth = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MMM", Locale.UK);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	static DecimalFormat df = new DecimalFormat("0.0");
	static DecimalFormat df2= new DecimalFormat("0.00");

	/**
	 * 计算时间差 单位为保留 小时 保留一位小数点
	 * @param beforeDate 旧的时间
	 * @param date
	 * @return
	 */
	public static String getDate(Date beforeDate, Date date) {
		if (beforeDate == null || date == null) {
			return null;
		}
		long nh = 1000 * 60 * 60;
		long diff = date.getTime() - beforeDate.getTime();
		double hour = (double)diff / nh;
		String hourFormat = df.format(hour) + "H";
		return hourFormat;
	}

	/**
	 * 将 x天x时x分 解析成 毫秒
	 * 
	 * @param time
	 * @return
	 */
	public static long parseStringDate(String time) {
		long millSecond = 0L;
		if (time == null) {
			return millSecond;
		}
		if (time.contains("天")) {
			String[] split = time.split("天");
			long day = 1000 * 24 * 60 * 60;
			millSecond = Integer.valueOf(split[0]) * day;
		}
		if (time.contains("小时")) {
			String[] split = time.split("分钟");
			long hour = 1000 * 60 * 60;
			millSecond += Integer.valueOf(split[0]) * hour;
		}
		if (time.contains("分钟")) {
			String[] split = time.split("分钟");
			long min = 1000 * 60;
			millSecond += Integer.valueOf(split[0]) * min;
		}
		return millSecond;
	}

	// 用处理月份,将数字转成英文 2019-01 -> 2019-Jan
	public static String parseMonth(String month) {
		String monthPattern = "^\\d{4}-\\d{2}$";
		// 月份则处理
		if (month.matches(monthPattern)) {
			try {
				Date date = numberMonth.parse(month);
				month = monthFormat.format(date);
			} catch (ParseException e) {
				log.info("parse number month to English month fail");
				return month;
			}
		}
		return month;
	}

	// 负责转化
	public static String getYear(String date) {
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return String.valueOf(dateTime.getYear());
	}

	public static String getMonth(String date) {
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return String.valueOf(dateTime.getMonthValue());
	}

	public static Date parseStringtoDate(String time) {
		SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 判断 传入时间 为 2019-10-01 还是 2019-10-01 08:00:00
		boolean flag = (time.length() == 10) ? true : false;
		Date date = null;
		try {
			if (flag) {
				date = dateFormat.parse(time);
			} else {
				date = timeDateFormat.parse(time);
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	public static Set<String> getBetweenDates(int interval, String start, String end) {
		Date startDate = parseStringtoDate(start);
		Date endDate = parseStringtoDate(end);
		return getBetweenDates(interval, startDate, endDate);
	}

	public static synchronized Set<String> getBetweenRuleDateDay(int interval, String start, String end) {
		long startTime = System.currentTimeMillis();
		Date startDate = parseStringtoDate(start);
		Date endDate = parseStringtoDate(end);
		if (interval == 0 || interval == 1) {
			return getBetweenRuleDateHour(interval, startDate, endDate);
		}
		log.info("获取时间区间耗时:" + (System.currentTimeMillis() - startTime));
		return getBetweenRuleDateDay(interval, startDate, endDate);
	}

	/**
	 * 获取两个日期之间的日期
	 *
	 * @param start 开始日期
	 * @param end   结束日期
	 * @return (周,月,年)日期集合
	 */
	public static synchronized Set<String> getBetweenDates(int interval, Date start, Date end) {
		List<Date> list = new ArrayList<Date>();
		int calendar = Calendar.DAY_OF_YEAR;
		list.add(start);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(calendar, 7);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			Date time = tempStart.getTime();
			list.add(time);
			tempStart.add(calendar, 7);
		}
		list.add(end);
		Set<String> result = new LinkedHashSet<>();
		for (Date date : list) {
			if (interval == 1) {
				String dateFormat = "yyyy-MM-dd";
				DateFormat format1 = new SimpleDateFormat(dateFormat);
				String format = format1.format(date);
				LocalDate localDate = LocalDate.parse(format);
				WeekFields weekFields = WeekFields.ISO;
				int week = localDate.get(weekFields.weekOfWeekBasedYear());
				String weekStr = String.valueOf(week);
				if (weekStr.length() == 1) {
					weekStr = "0" + weekStr;
				}
				int year = localDate.get(weekFields.weekBasedYear());
				result.add(year + "-W" + weekStr);
			} else {
				if (interval == 2) {
					String dateFormat = "yyyy-MM";
					DateFormat format = new SimpleDateFormat(dateFormat);
					String month = format.format(date);
					result.add(month);
				} else if (interval == 3) {
					String dateFormat = "yyyy";
					DateFormat format = new SimpleDateFormat(dateFormat);
					String year = format.format(date);
					result.add(year);
				}
			}
		}
		return result;
	}

	/**
	 * 获取两个日期之间的日期（日）集合
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static synchronized Set<String> getBetweenDateDay(Date start, Date end) {
		List<Date> list = new ArrayList<Date>();
		int calendar = Calendar.DAY_OF_YEAR;
		list.add(start);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(calendar, 1);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			Date time = tempStart.getTime();
			list.add(time);
			tempStart.add(calendar, 1);
		}
		list.add(end);
		Set<String> result = new LinkedHashSet<>();
		for (Date date : list) {
			String dateFormat = "yyyy-MM-dd";
			DateFormat format = new SimpleDateFormat(dateFormat);
			String day = format.format(date);
			result.add(day);

		}
		return result;
	}

	public static List<String> getMonthBetweenDate(String start, String end) {
		Date startDate = parseStringtoDate(start);
		Date endDate = parseStringtoDate(end);
		List<String> result = new ArrayList<>();
		//格式化为年月
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		//关于日期的处理 最新的使用Calendar
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(startDate);
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(endDate);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		Calendar curr = min;
		//在java中经常使用while 循环
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}
	/**
	 * 传入时间进行不同的分组（日，周，月，年，季度） 2：班次 3：日， 4：周， 5：月， 6,季度， 7:半年 8:年
	 * 
	 * @param interval
	 * @param start
	 * @param end
	 * @return
	 */
	public static synchronized Set<String> getBetweenRuleDateDay(int interval, Date start, Date end) {
		// 将开始时间结束时间减去8小时
		JSONObject dateJson = reduce8Hour(start, end);
		start = dateJson.getDate("start");
		end = dateJson.getDate("end");
		List<Date> list = new ArrayList<Date>();
		int calendar = Calendar.DAY_OF_YEAR;
		list.add(start);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(calendar, 1);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			Date time = tempStart.getTime();
			list.add(time);
			tempStart.add(calendar, 1);
		}
		list.add(end);
		Set<String> result = new LinkedHashSet<>();
		for (Date date : list) {
			if (interval == 4) {
				String dateFormat = "yyyy-MM-dd";
				DateFormat format1 = new SimpleDateFormat(dateFormat);
				String format = format1.format(date);
				LocalDate localDate = LocalDate.parse(format);

				int week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
				String weekStr = String.valueOf(week);
				if (weekStr.length() == 1) {
					weekStr = "0" + weekStr;
				}
				int year = localDate.get(IsoFields.WEEK_BASED_YEAR);
				result.add(year + "-WK" + weekStr);
			} else if (interval == 2) {
				String dateFormat = "yyyy-MM-dd";
				DateFormat sdf = new SimpleDateFormat(dateFormat);
				String format = sdf.format(date);
				result.add(format + "-" + "Day");
				result.add(format + "-" + "Night");
			} else {
				String dateFormat = "yyyy-MM-dd";
				if (interval == 5) {
					dateFormat = "MMM-yy";
				}
				String format = null;
				if (interval == 6) {
					format = getQuarter(date);
				} else if (interval == 7) {
					String quarter = getQuarter(date).substring(6, 7);
					String year = "2H";
					if ("1".equals(quarter) || "2".equals(quarter)) {
						year = "1H";
					}
					format = getQuarter(date).substring(0, 5) + year;
				} else if (interval == 8) {
					format = getQuarter(date).substring(0, 4);
				} else {
					DateFormat format1 = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
					format = format1.format(date);
				}
				result.add(format);
			}
		}
		return result;
	}

	public static synchronized Set<String> getBetweenRuleDateDay2(int interval, Date start, Date end) {
		List<Date> list = new ArrayList<Date>();
		int calendar = Calendar.DAY_OF_YEAR;
		list.add(start);
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(calendar, 1);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			Date time = tempStart.getTime();
			list.add(time);
			tempStart.add(calendar, 1);
		}
		list.add(end);
		Set<String> result = new LinkedHashSet<>();
		for (Date date : list) {
			if (interval == 4) {
				String dateFormat = "yyyy-MM-dd";
				DateFormat format1 = new SimpleDateFormat(dateFormat);
				String format = format1.format(date);
				LocalDate localDate = LocalDate.parse(format);

				int week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
				String weekStr = String.valueOf(week);
				if (weekStr.length() == 1) {
					weekStr = "0" + weekStr;
				}
				int year = localDate.get(IsoFields.WEEK_BASED_YEAR);
				result.add(year + "-WK" + weekStr);
			} else if (interval == 2) {
				String dateFormat = "yyyy-MM-dd";
				DateFormat sdf = new SimpleDateFormat(dateFormat);
				String format = sdf.format(date);
				result.add(format + "-" + "Day");
				result.add(format + "-" + "Night");
			} else {
				String dateFormat = "yyyy-MM-dd";
				if (interval == 5) {
					dateFormat = "MMM-yy";
				}
				String format = null;
				if (interval == 6) {
					format = getQuarter(date);
				} else if (interval == 7) {
					String quarter = getQuarter(date).substring(6, 7);
					String year = "2H";
					if ("1".equals(quarter) || "2".equals(quarter)) {
						year = "1H";
					}
					format = getQuarter(date).substring(0, 5) + year;
				} else if (interval == 8) {
					format = getQuarter(date).substring(0, 4);
				} else {
					DateFormat format1 = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
					format = format1.format(date);
				}
				result.add(format);
			}
		}
		return result;
	}

	/**
	 * 传入时间进行不同的分组（半小时，时间） 0:半小时 1:小时
	 * 
	 * @param interval
	 * @param start
	 * @param end
	 * @return
	 */
	public static synchronized Set<String> getBetweenRuleDateHour(int interval, Date start, Date end) {
		List<Date> list = new ArrayList<Date>();
		// 将开始时间分秒变成00:00 将结束加一个小时把分秒变成00:00
		JSONObject dateJson = reduceHour(start, end);
		start = dateJson.getDate("start");
		end = dateJson.getDate("end");
		int calendar = Calendar.MINUTE;
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(calendar, 30);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			Date time = tempStart.getTime();
			list.add(time);
			tempStart.add(calendar, 30);
		}
		// 把最后一个日期加入到集合中
		list.add(tempEnd.getTime());
		Set<String> result = new TreeSet<String>();
		String format = null;
		for (Date date : list) {
			if (interval == 1) {
				String dateFormat = "yyyy-MM-dd HH:00";
				DateFormat format1 = new SimpleDateFormat(dateFormat);
				format = format1.format(date);
			} else {
				String dateFormat = "yyyy-MM-dd HH:mm";
				DateFormat format1 = new SimpleDateFormat(dateFormat);
				format = format1.format(date);
			}
			result.add(format);
		}
		return result;
	}

	/**
	 * FTY和FPY的时间区间
	 * 
	 * @param interval
	 * @param start
	 * @param end
	 * @return
	 */
	public static synchronized Set<String> getBetweenDate(int interval, Date start, Date end) {
		Set<String> betweenDates = new TreeSet<String>();
		if (interval == 0 || interval == 1) {
			betweenDates = getBetweenRuleDateHour(interval, start, end);
		} else {
			betweenDates = getBetweenRuleDateDay(interval, start, end);
		}
		return betweenDates;
	}

	/**
	 * FTY和FPY的时间区间
	 * 
	 * @param interval
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<String> getBetweenDate(int interval, String start, String end) {
		Date startDate = parseStringtoDate(start);
		Date endDate = parseStringtoDate(end);
		return getBetweenDate(interval, startDate, endDate);
	}

	/**
	 * 获取规则中的季度
	 */
	public static String getQuarter(Date date) {
		String result = "";
		int calendar = Calendar.MONTH;
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		temp.add(calendar, -3);
		Date date1 = temp.getTime();
		DateFormat format1 = new SimpleDateFormat("yyyy");
		String year = format1.format(date1);
		DateFormat format2 = new SimpleDateFormat("MM");
		String month = format2.format(date1);
		result = year + "-Q" + String.valueOf(((Integer.valueOf(month) + 2) / 3));
		return result;
	}

	/**
	 * 将传入的开始时间和结束时间减去8个小时
	 */
	public static JSONObject reduce8Hour(Date start, Date end) {
		JSONObject result = new JSONObject();
		int calendar = Calendar.HOUR_OF_DAY;
		Calendar temp = Calendar.getInstance();
		temp.setTime(start);
		temp.add(calendar, -8);
		result.put("start", temp.getTime());
		temp.setTime(end);
		temp.add(calendar, -8);
		result.put("end", temp.getTime());
		return result;
	}

	/**
	 * 将传入的开始时间时分处理和结束时间加上一个小时
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static JSONObject reduceHour(Date start, Date end) {
		JSONObject result = new JSONObject();
		int calendar = Calendar.HOUR_OF_DAY;
		Calendar temp = Calendar.getInstance();
		temp.setTime(start);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		result.put("start", temp.getTime());
		temp.setTime(end);
		temp.add(calendar, 1);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		result.put("end", temp.getTime());
		return result;
	}

	/**
	 * 获取当前时间的年月日小时
	 */
	public static String getEndTime(String endTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(endTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,7);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		endTime = df.format(calendar.getTime());
		return endTime;
	}

	/**
	 * 获取当前时间前一天的08:59:59
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String getStartTimeBefore(String endTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(endTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		endTime = df.format(calendar.getTime());
		return endTime;
	}
	public static String getStartTime(String endTime) throws ParseException {
		String startTime = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(endTime));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		if (df.parse(endTime).before(calendar.getTime())) {
			calendar.add(Calendar.MONTH, -1);
		}
		startTime = df.format(calendar.getTime());
		return startTime;
	}


	/**
	 * 传入年份和周数得到年份和月份 2019-WK49 变成2019-12
	 */
	public static String weekToMonth(String week) {
		Map<String, Integer> map = new HashMap<>();
		List<String> dateList = new ArrayList<>();
		String month = null;
		Integer year = Integer.valueOf(week.substring(0, 4));
		Integer week1 = Integer.valueOf(week.substring(7, 9));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week1);
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		for (int i = 0; i < 7; i++) {
			dateList.add(dateFormat.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_WEEK, -1);
		}
		for (String date : dateList) {
			String flag = date.substring(0, 5) + Integer.valueOf(date.substring(5, 7)) + "月";
			if (map.get(flag) != null) {
				map.put(flag, map.get(flag).intValue() + 1);
			} else {
				map.put(flag, 1);
			}
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
		month = list.get(0).getKey();
		return month;
	}

	public static String weekToMonth2(String week) {
		String month = null;
		Integer year = Integer.valueOf(week.substring(0, 4));
		Integer week1 = Integer.valueOf(week.substring(7, 9));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week1);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		month = dateFormat.format(calendar.getTime());
		month = month.substring(0, 5) + Integer.valueOf(month.substring(5, 7));
		return month;
	}

	/**
	 * 传入时间和周期获取不同的目标值KEY
	 */
	public static String getTargetFlag(String date, int cycle) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String targetFlag = null;
		// 周期为月以下
		if (cycle == 0 || cycle == 1 || cycle == 2 || cycle == 3 || cycle == 4 || cycle == 5) {
			targetFlag = date.substring(0, 5) + Integer.valueOf(date.substring(5, 7)) + "月";
		}
		// 周期为季度
		else if (cycle == 6) {
			targetFlag = getQuarter(sdf.parse(date));
		}
		// 周期为半年
		else if (cycle == 7) {
			String temp = getQuarter(sdf.parse(date));
			String quarter = temp.substring(6, 7);
			String year = "2H";
			if ("1".equals(quarter) || "2".equals(quarter)) {
				year = "1H";
			}
			targetFlag = temp.substring(0, 5) + year;
		}
		// 周期为年
		else {
			targetFlag = getQuarter(sdf.parse(date)).substring(0, 5) + "Y";
		}
		return targetFlag;
	}

	/**
	 * 获取当前 yyyy-mm-dd HH:mm:ss 时间
	 */
	public static String getCurrentDateTime() {
		String localDateTime = LocalDateTime.now().format(formatter);
		return localDateTime;
	}

	/**
	 * 获取当前月1日 08:00:00 时间字符串
	 *  如果 时间是 每月 1号8点前。 则需要将当前月设置为上一个月的 1号8点  这里存在一种特殊情形
	 *  如果 是 时间是 1月 1号 8点前 还需要 对此做特殊处理为上年的 12月 1号 8点
	 */
	public static String getCurrentMonthTime() {
		LocalDateTime now = LocalDateTime.now();
		String localDateTime = null;
		// 如果 时间是 每月 1号8点前。 则需要将当前月设置为上一个月的 1号8点
		if (now.getDayOfMonth() == 1 && now.getHour() < 8) {
			int month = now.getMonth().getValue();
			// 如果时间是 1月 1号 8点前 还需要 对此做特殊处理为上年的 12月 1号 8点
			// 其他月份 则选择 月份 减 1
			if (month == 1) {
				localDateTime = LocalDateTime.of(now.getYear() - 1, Month.of(12), 1, 8, 0, 0).format(formatter);
			} else {
				localDateTime = LocalDateTime.of(now.getYear() , Month.of(month - 1), 1, 8, 0, 0).format(formatter);
			}
		} else {
			//非特殊情形
			localDateTime = LocalDateTime.of(now.getYear() , now.getMonth(), 1, 8, 0, 0).format(formatter);
		}
		return localDateTime;
	}



	public static String getResult(String startTime, String endTime, int cycle) throws ParseException {
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(startTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		addDate(calendar, cycle);
		if (calendar.getTime().before(sdf.parse(endTime))) {
			result = getResultString(cycle);
		}
		return result;
	}

	/**
	 * 不同的周期维度选择时间的不同规则
	 */
	public static void addDate(Calendar calendar, int cycle) {
		switch (cycle) {
		case 0:
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case 1:
			calendar.add(Calendar.DAY_OF_MONTH, 2);
			break;
		case 2:
			calendar.add(Calendar.MONTH, 1);
			break;
		case 3:
			calendar.add(Calendar.MONTH, 2);
			break;
		case 4:
			calendar.add(Calendar.YEAR, 1);
			break;
		case 5:
			calendar.add(Calendar.YEAR, 2);
			break;
		case 6:
			calendar.add(Calendar.YEAR, 2);
			break;
		case 7:
			calendar.add(Calendar.YEAR, 2);
			break;
		case 8:
			calendar.add(Calendar.YEAR, 5);
			break;
			default:
		}
	}

	public static String getResultString(int cycle) {
		String result = null;
		switch (cycle) {
		case 0:
			result = "半小时维度时间选择区间不能超过一天";
			break;
		case 1:
			result = "小时维度时间选择区间不能超过两天";
			break;
		case 2:
			result = "班次维度时间选择区间不能超过一个月";
			break;
		case 3:
			result = "天维度时间选择区间不能超过两个月";
			break;
		case 4:
			result = "周维度时间选择区间不能超过一年";
			break;
		case 5:
			result = "月维度时间选择区间不能超过两年";
			break;
		case 6:
			result = "季度维度时间选择区间不能超过两年";
			break;
		case 7:
			result = "半年维度时间选择区间不能超过两年";
			break;
		case 8:
			result = "年维度时间选择区间不能超过五年";
			break;
			default:
				result =null;
				break;
		}
		return result;
	}

	/**
	    *@description  日期加一天
	    *@author 许锦标
	    *@date 2020/4/24 15:57
	    *@email jinbiao.xu@jwis.cn
	 */
	public static String plusOneDay(String date) {
		String result = null;
		if (StringUtils.isNotEmpty(date)) {
			LocalDate localDate = LocalDate.parse(date, localDateFormat).plusDays(1);
			result = localDateFormat.format(localDate);
		}
		return result;
	}


	public static Set<String> getDetectionMonth(String startTime,String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM");
		Set<String> dateList = new TreeSet<>();
		List<Date> list = new ArrayList<Date>();
		Date start = sdf.parse(startTime);
		Date end = sdf.parse(endTime);
		int calendar = Calendar.DAY_OF_YEAR;
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			Date time = tempStart.getTime();
			list.add(time);
			tempStart.add(calendar, 1);
		}
		// 把最后一个日期加入到集合中
		list.add(tempEnd.getTime());
		for (Date date:list) {
			String a = 	sdf2.format(date);
			dateList.add(a);
		}
		return dateList;
	}

	public static  String getDetectionMonth2(String time){
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM");
		try {
			result =  sdf2.format(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  result;
	}

	/**
	 * 根据不同的DateType 去获取日期的标识
	 */
	public  static  String getDateTypeTime(DateType dateType, String field) throws Exception{
		if (dateType == null) {
			throw new RuntimeException("DataType 不能为空");
		}
		String tempTime = null;
		switch (dateType) {
			case DAY:
				tempTime = "DATE_FORMAT(" + field + ",'%Y-%m-%d')";
				break;
			case WEEK:
				tempTime = "CONCAT(DATE_FORMAT(" + field + ",'%x'),\"-WK\",DATE_FORMAT(" + field + ",'%v'))";
				break;
			case MONTH:
				tempTime =  "DATE_FORMAT(" + field + ",'%Y-%m')";
				break;
			case YEAR:
				tempTime =  "DATE_FORMAT(" + field + ",'%Y')";
				break;
			default:
				tempTime = "DATE_FORMAT(" + field + ",'%Y-%m-%d')";
				break;
		}
		return tempTime;
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取TL的数据
	 * @Date  2020/5/25  18:05
	 * @Param
	 * @return
	 */
	public static  String getTl(Date startDate,Date endDate,List<String> holidayList,List<String> workOvertimeList){
		String result =null;
		//获取除去周末的开始时间
        if (!isWorkDay(startDate,holidayList,workOvertimeList)){
        	startDate = getMonDay(startDate,holidayList,workOvertimeList);
		}
		//获取除去周末的结束时间
		if (!isWorkDay(endDate,holidayList,workOvertimeList)){
            endDate = getFriDay(endDate,holidayList,workOvertimeList);
		}
		if (startDate.after(endDate)){
			return "0";
		}
		//获取正确的开始时间，结束时间中间存在的非工作日天数
		int i = getWeekendSum(startDate,endDate,holidayList,workOvertimeList);
		//获取时间差距
		long l = endDate.getTime() - startDate.getTime() - i * 24 * 60 * 60 * 1000;
		//获取小时
		BigDecimal sum = new BigDecimal(l);
		BigDecimal temp = new BigDecimal(60*60*1000);
		result = sum.divide(temp,2,BigDecimal.ROUND_HALF_DOWN).toString();
		return result;
	}

    /**
     * @Author yuyangyang
     * @Description 判断是否是工作日
     * @Date  2020/5/28  10:53
     * @Param
     * @return
     */
	public static Boolean isWorkDay(Date date,List<String> vocations,List<String> overtimes ){
		Boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);
		//判断传入的日期是周几
		int a = dayOfWeek(dateString);
		//判断如果日期在补班数据中就是工作日
		if (overtimes.contains(dateString)){
			flag = true;
		}
		else {
			//date是周一到周五而且不在放假表中
			if (!vocations.contains(dateString) && a <= 5){
				flag = true;
			}
		}
		return flag;
	}
    /**
     * @Author yuyangyang
     * @Description 判断日期是周几
     * @Date  2020/5/28  10:56
     * @Param
     * @return
     */
	public static int dayOfWeek(String date){
		String[][] strArray = {{"MONDAY", "1"}, {"TUESDAY", "2"}, {"WEDNESDAY", "3"}, {"THURSDAY", "4"}, {"FRIDAY", "5"}, {"SATURDAY", "6"}, {"SUNDAY", "7"}};
		LocalDate currentDate = LocalDate.parse(date);
		String k = String.valueOf(currentDate.getDayOfWeek());
		for (int i = 0; i < strArray.length; i++) {
			if (k.equals(strArray[i][0])) {
				k = strArray[i][1];
				break;
			}
		}
		return  Integer.parseInt(k);
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取下周一的0点
	 * @Date  2020/5/25  18:53
	 * @Param
	 * @return
	 */
	public static Date getMonDay(Date date,List<String> holidayList,List<String> workOvertimeList){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int calendarTemp  = Calendar.DAY_OF_YEAR;
		while (!isWorkDay(date,holidayList,workOvertimeList)){
			calendar.add(calendarTemp,1);
			date = calendar.getTime();
		}
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return  calendar.getTime();
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取当周的周五的最后一秒
	 * @Date  2020/5/25  18:54
	 * @Param
	 * @return
	 */
	public static Date getFriDay(Date date,List<String> holidayList,List<String> workOvertimeList){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int calendarTemp  = Calendar.DAY_OF_YEAR;
		while (!isWorkDay(date,holidayList,workOvertimeList)){
			calendar.add(calendarTemp,-1);
			date = calendar.getTime();
		}
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		return  calendar.getTime();
	}

	public static int getWeekendSum(Date startDate,Date endDate,List<String> holidayList,List<String> workOvertimeList){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		int calendar = Calendar.DAY_OF_YEAR;
		int i =0;
		while (startCalendar.before(endCalendar)) {
			startCalendar.add(calendar, 1);
			Date date = startCalendar.getTime();
			if (!isWorkDay(date,holidayList,workOvertimeList) && startCalendar.before(endCalendar)){
				i++;
			}
		}
		return i;
	}
    /**
     * @Author yuyangyang
     * @Description 判断是否超期
     * @Date  2020/5/29  16:12
     * @Param
     * @return
     */
	public static boolean isOverdue(String date, Integer temp) throws ParseException {
		boolean flag = false;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = simpleDateFormat.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.HOUR_OF_DAY,temp);
		Date time = calendar.getTime();
		if (time.before(new Date())){
			flag = true;
		}
		return flag;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取当前日期
	 * @Date  2020/6/20  15:07
	 * @Param
	 * @return
	 */
	public static String getCurrentDate(){
		 String result = null;
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 result = sdf.format(date);
		 return  result;
	}

	/**
	 * @Author yuyangyang
	 * @Description //TODO
	 * @Date  2020/7/31  14:56
	 * @Param
	 * @return
	 */
	public static  String formatDate(Date date) {
		SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null) {
			String str = timeDateFormat.format(date);
			return str;
		}
		return null;
	}

	/**
	 * 传入时间和周期获取不同的目标值KEY
	 */
	public static String getEsdTargetFlag(String date, int cycle,String key) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String targetFlag = null;
		// 周期为月以下
		if (cycle == 3) {
			targetFlag = date.substring(0, 5) + Integer.valueOf(date.substring(5, 7))+"-"+key;
		} else if (cycle == 4) {
			targetFlag = weekToMonth2(date)+"-"+key;
		} else {
			date = sdf1.format(sdf.parse(date));
			targetFlag = date.substring(0, 5) + Integer.valueOf(date.substring(5, 7)) +"-"+ key;
		}
		return targetFlag;
	}
}
