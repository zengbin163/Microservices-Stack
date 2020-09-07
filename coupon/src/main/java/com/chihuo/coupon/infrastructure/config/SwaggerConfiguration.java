package com.chihuo.coupon.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	
	private static final String SWAGGER_TITLE = "接口文档";
	private static final String SWAGGER_DESCRIPTION = 
			"律师端、用户端接口详细文档"
			+ "<br>1.生产环境地址为： http://m.famiaowang.com:8300 "
			+ "<br>2.测试环境地址为： http://mtest.famiaowang.com:8300 "
			+ "<br>3.图片上传生产环境地址为： http://static.famiaowang.com:8400 "
			+ "<br>4.图片上传测试环境地址为： http://statest.famiaowang.com:8400 ";

	@Value("${project.version}")
	private String version;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enableUrlTemplating(true).select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(SWAGGER_TITLE).description(SWAGGER_DESCRIPTION).version(version).build();
	}

}
