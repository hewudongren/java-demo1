package cn.jwis.qualityworkflow;

import cn.jwis.configration.clientUtil.ConfigCenterHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args)throws  Exception {
		SpringApplication springApplication = new SpringApplication(Application.class);
		Properties properties = new Properties();
		InputStream is = Application.class.getClassLoader().getResourceAsStream("appName.properties");
		properties.load(is);
		String appName = properties.getProperty("appName");
		Map<String, Object> defaultMap = new HashMap<>();
		// 项目的名称AppName
		ConfigCenterHelper.getConfig(defaultMap, appName);// 调用read_Config.jar包内读取配置的方法
		springApplication.setDefaultProperties(defaultMap);// 将配置加载到启动项中
		springApplication.run(args);
		System.out.println("么么哒");
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);// 单位为ms
		factory.setConnectTimeout(5000);// 单位为ms
		return factory;
	}
}
