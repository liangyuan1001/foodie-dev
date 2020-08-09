package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //http:localhost:8080/swagger-ui.html
    //http:localhost:8080/dco.html
    //
    //配置swagger2核心docket
    @Bean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.imooc.controller")).paths(PathSelectors.any()).build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("天天吃货 电商平台接口api")
                .contact(new Contact("imooc","www.imooc.com","abc@imooc.com"))
                .description("专为天天吃货提供的接口api文档").version("1.0.1")
                .termsOfServiceUrl("http://www.imooc.com").build();
    }


}
