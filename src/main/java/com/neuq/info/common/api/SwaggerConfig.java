package com.neuq.info.common.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.neuq.info.web"})
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
//		System.out.println("http://localhost:8080" + pathMapping + "/swagger-ui.html");
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("NEUQ-INFO后台服务API")
				.description("NEUQ校园服务平台")
				.termsOfServiceUrl("https://info.hhml.online")
				.contact("李航")
				.version("1.0").build();
	}
}
