package cn.jwis.qualityworkflow.util;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class FormatUtil {
	static DecimalFormat df = new DecimalFormat("0.00");
	static {
		df.setRoundingMode(HALF_UP);
	}

	/**
	 * 除法 默认返回两位小数点
	 * 
	 * @param molecule
	 * @param denominator
	 * @return
	 */
	public static String divisionFormat(String molecule, String denominator) {
		// 如果分母为空返回填充值
		if (denominator == null) {
			return "-";
		}
		// 如果分母不为空， 分子为null 返回 0.00
		if (molecule == null && denominator != null) {
			return "0.00";
		}
		return divisionFormat(Long.parseLong(molecule), Long.parseLong(denominator));
	}

	public static String divisionFormat(long molecule, long denominator) {
		String result = null;
		if (denominator == 0) {
			result = "0.00";
		} else {
			result = decimalFormat((float) molecule / denominator * 100);

		}
		return result;
	}

	public static String divisionFormat2(String molecule, String denominator) {
		return divisionFormat2(Long.parseLong(molecule), Long.parseLong(denominator));
	}

	public static String divisionFormat2(long molecule, long denominator) {
		String result = null;
		if (denominator == 0) {
			result = "-";
		} else {
			result = decimalFormat((1 - (float) molecule / denominator) * 100);

		}
		return result;
	}

	public static String divisionFormatCqa(String molecule, String denominator) {
		String result = null;
		if ("0.0".equals(denominator)) {
			result = "-";
		} else {
			BigDecimal moleculeD = new BigDecimal(molecule);
			BigDecimal denominatorD = new BigDecimal(denominator);
			BigDecimal d = new BigDecimal("1000000");
			BigDecimal temp = moleculeD.multiply(d).divide(denominatorD, 0, BigDecimal.ROUND_HALF_UP);
			result = String.valueOf(temp);
		}
		return result;
	}

	/**
	 * 小数点格式化，四舍五入保留小数点后两位
	 * 
	 * @param number 数据
	 * @return 格式化后的小数
	 */
	public static String decimalFormat(Object number) {
		return df.format(number);
	}

	/**
	 * 根据不同的周期去获取日期的标识
	 */
	public static String dateFormat(int cycle, String field) {
		String temp = null;
		if (cycle == 0) {
			temp = "DATE_FORMAT(concat( date(DATE_ADD(" + field + ",INTERVAL 30 MINUTE)), ' ', HOUR (DATE_ADD(" + field
					+ ",INTERVAL 30 MINUTE)), ':', floor( MINUTE (DATE_ADD(" + field
					+ ",INTERVAL 30 MINUTE)) / 30 ) * 30 ),'%Y-%m-%d %H:%i')";
		} else if (cycle == 1) {
			temp = "DATE_FORMAT(DATE_ADD(" + field + ",INTERVAL 30 MINUTE),'%Y-%m-%d %H:00')";
		} else if (cycle == 2) {
			temp = "CONCAT(DATE_FORMAT(" + field + ",'%Y-%m-%d'),\"-\",if(business_classes='白班','Day','Night'))";
		} else if (cycle == 3) {
			temp = "DATE_FORMAT(" + field + ",'%Y-%m-%d')";
		} else if (cycle == 4) {
			temp = "CONCAT(DATE_FORMAT(" + field + ",'%x'),\"-WK\",DATE_FORMAT(" + field + ",'%v'))";
		} else if (cycle == 5) {
			temp = "DATE_FORMAT(" + field + ",'%b-%y')";
		} else if (cycle == 6) {
			temp = "CONCAT(YEAR(DATE_SUB(" + field + ",INTERVAL 3 MONTH)),\"-Q\",QUARTER(DATE_SUB(" + field
					+ ",INTERVAL 3 MONTH)))";
		} else if (cycle == 7) {
			temp = "CONCAT(YEAR(DATE_SUB(" + field + ",INTERVAL 3 MONTH)),\"-\",if(QUARTER(DATE_SUB(" + field
					+ ",INTERVAL 3 MONTH))<=2,\"1H\",\"2H\"))";
		} else {
			temp = "DATE_FORMAT(DATE_SUB(" + field + ",INTERVAL 3 MONTH),'%Y')";
		}
		return temp;
	}

	/**
	 * 根据不同的周期去获取目标值的KEY值
	 */
	public static String dateToTarget(int cycle) {
		String temp = null;
		if (cycle == 0 || cycle == 1 || cycle == 2 || cycle == 3) {
			temp = "CONCAT(year(a.abscissa),'-',month(a.abscissa),'月')";
		} else if (cycle == 4) {
			temp = "CONCAT(year(a.abscissa),'-',month(a.abscissa),'月')";
		} else if (cycle == 5) {
			temp = "CONCAT(year(STR_TO_DATE(a.abscissa,'%b-%y')),'-',month(STR_TO_DATE(a.abscissa,'%b-%y')),'月')";
		} else if (cycle == 6) {
			temp = "a.abscissa";
		} else if (cycle == 7) {
			temp = "a.abscissa";
		} else {
			temp = "CONCAT(a.abscissa,'-','Y')";
		}
		return temp;
	}

	public static List<String> stringToList(String key) {
		List<String> list = new ArrayList<>();
		if (StringUtils.isNotEmpty(key)) {
			list = Arrays.asList(key.split(";"));
		}
		return list;
	}
	/**
	 * @Author yuyangyang
	 * @Description List转字符串
	 * @Date  2020/6/2  10:56
	 * @Param
	 * @return
	 */
	public static String listToString(List<String> list) {
		String result = new String();
		if (CollectionUtils.isNotEmpty(list)) {
			result = StringUtils.join(list,",");
		}else {
			result = "All";
		}
		return result;
	}

	/**
	 * 判断值是否FPY,FTY达标
	 * 
	 * @return
	 */
	public static String isQualified(String target, String fpy) {
		if (StringUtils.isEmpty(fpy) || ("-").equals(fpy)) {
			return "2";
		} else if (StringUtils.isEmpty(target)) {
			return "0";
		} else {
			Double a = Double.valueOf(target);
			Double b = Double.valueOf(fpy);
			if (b >= a) {
				return "0";
			} else {
				return "1";
			}
		}
	}

	/**
	 * 累加int类型
	 */
	public static String add(String a, String b) {
		if (StringUtils.isEmpty(a)) {
			return b;
		} else if (StringUtils.isEmpty(b)) {
			return a;
		}
		int temp = Integer.valueOf(a) + Integer.valueOf(b);
		String result = String.valueOf(temp);
		return result;
	}

	/**
	 * 累加小数类型
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String addDouble(String a, String b) {
		if (StringUtils.isEmpty(a) || "-".equals(a)) {
			return b;
		} else if (StringUtils.isEmpty(b) || "-".equals(b)) {
			return a;
		}
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		BigDecimal temp = b1.add(b2);
		String result = String.valueOf(temp);
		return result;
	}

	/**
	 * 添加百分号
	 */
	public static String addPercent(String str) {
		if (StringUtils.isNotEmpty(str) && !"-".equals(str)) {
			str = str + "%";
		}
		return str;
	}
	public  static Map<String,String> getTitle(String[] dblist, String[] excellist){
		Map<String,String> titles = new LinkedHashMap<>();
		for (int i = 0; i < dblist.length ; i++) {
			titles.put(dblist[i],excellist[i]);
		}
		return titles;
	}

}
