/**
 * 
 */
package cn.jwis.qualityworkflow.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jwis.qualityworkflow.enums.DBTypeEnum;

/**
 * @ClassName: DBContextHolder
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2019年10月9日
 */

public class DBContextHolder {
	private static final ThreadLocal<DBTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

	public static final Logger logger = LoggerFactory.getLogger(DBContextHolder.class);

	public static void set(DBTypeEnum dbType) {
		try {
			CONTEXT_HOLDER.set(dbType);
		}catch (Exception e){
			CONTEXT_HOLDER.remove();
		}
	}

	public static DBTypeEnum get() {
		return CONTEXT_HOLDER.get();
	}

	public static void master() {
		set(DBTypeEnum.MASTER);
	}

	public static void slave() {
		set(DBTypeEnum.SLAVE);
	}
}
