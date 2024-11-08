package com.xwh.user.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("测试").description("测试").termsOfServiceUrl("地址待定").contact(new Contact("", "", "")).version("1.0").build();
    }

    @Bean
    public Docket createRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).groupName("测试").select().apis(RequestHandlerSelectors.basePackage("com.xwh.user.controller")).paths(PathSelectors.any()).build();
    }
}
