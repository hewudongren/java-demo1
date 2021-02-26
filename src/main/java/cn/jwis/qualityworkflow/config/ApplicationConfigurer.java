package cn.jwis.qualityworkflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.jwis.qualityworkflow.interceptor.SessionInterceptor;

@Configuration
public class ApplicationConfigurer implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
}
