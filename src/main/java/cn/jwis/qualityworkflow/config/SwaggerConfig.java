package cn.jwis.qualityworkflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@SuppressWarnings("unchecked")
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(true).select()
				.apis(RequestHandlerSelectors.basePackage("cn.jwis"))
				.paths(Predicates.or(PathSelectors.regex("/.*")))// 过滤的接口
				.build().apiInfo(metaData());

	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Micro Service REST APIs", "Provide the APIs manual for JWI Qcould Platform",
				"1.0", "Terms of service", new Contact("JWI Team", "http://www.jwis.cn", "kiweenhu@jwis.cn"), "", ""
		/*
		 * "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0"
		 */ );
		return apiInfo;
	}
}