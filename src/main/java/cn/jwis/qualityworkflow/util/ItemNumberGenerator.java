package cn.jwis.qualityworkflow.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description  项目编号生成器， 返回20200131001 日期 + 3位流水号  流水号每天都是从001开始
 * @create 2020-04-29 11:30
 * @since 0.1.0
 **/
public class ItemNumberGenerator {
	// 上一个ID 生成日期
	private  volatile LocalDate lastNumberGenerateDate = LocalDate.now();
	private  volatile int id = 0;
	private  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	public  String getItemNumber() {
		LocalDate today = LocalDate.now();
		int lastNumber = id;
		int id = getId(today, lastNumber);
		String itemNumber = getItemNumber(today, id);
		return itemNumber;
	}
	/**
	 * @description: 根据日期返回id，流水号每天都是从1开始
	 * @author: xujinbiao
	 * @date: 2020/4/29 13:53
	 * @param today:
	 * @param lastNumber:
	 * @return: int
	 **/
	private  synchronized int getId(LocalDate today, int lastNumber) {
		// 如果 当前日期晚于 上一个号码的生成日期 说明要将日期重置 , id 也要重置为1
		if (today.isAfter(lastNumberGenerateDate)) {
			id = 1;
			lastNumberGenerateDate = today;
			return id;
		}
		// 针对 并发线程 修改了日期， 导致 出现了 today 早于 lastNumberGenerateDate， 则返回 lastNumber + 1
		else if (today.isBefore(lastNumberGenerateDate)) {
			lastNumber++;
			return lastNumber;
		}
		// 如果日期相等 说明一切正常 只需 将id ++ 返回即可
		id ++;
		return id;
	}
	/**
	 * @description: 根据日期和id生成 项目编号
	 * @author: xujinbiao
	 * @date: 2020/4/29 14:19
	 * @param date:
	 * @param id:
	 * @return: java.lang.String
	 **/
	private  String getItemNumber(LocalDate date, int id) {
		String format = dateFormatter.format(date);
		// 如果id 小于100则需要进行补0
		if (id < 10) {
			return format + "00" + id;
		} else if (id >= 10 && id< 100) {
			return format + "0" + id;
		}
		return format + id;
	}
}
