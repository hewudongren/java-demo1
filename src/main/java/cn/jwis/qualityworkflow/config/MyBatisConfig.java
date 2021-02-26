/**
 * 
 */
package cn.jwis.qualityworkflow.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: MyBatisConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2019年10月9日
 */

@EnableTransactionManagement
@Configuration
public class MyBatisConfig {

	@Resource(name = "myRoutingDataSource")
	private DataSource myRoutingDataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(myRoutingDataSource);
	}
}
