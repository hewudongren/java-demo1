/**
 * 
 */
package cn.jwis.qualityworkflow.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import cn.jwis.qualityworkflow.enums.DBTypeEnum;

/**
 * @ClassName: MyRoutingDataSource
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2019年10月9日
 */

public class MyRoutingDataSource extends AbstractRoutingDataSource {

	public static final Logger logger = LoggerFactory.getLogger(MyRoutingDataSource.class);

	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		DBTypeEnum dbTypeEnum = DBContextHolder.get();
		return dbTypeEnum;
	}
}