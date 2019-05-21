package com.dcits.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * API文档工具类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.dcits.storage.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("苏州卡航手持端API文档")
				.description("提供苏州卡航手持端所有方法接口")
				.termsOfServiceUrl("")
				.version("1.0")
				.build();
	}
}
