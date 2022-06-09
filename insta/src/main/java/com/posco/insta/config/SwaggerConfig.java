package com.posco.insta.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.OAS_30 ).select().apis(RequestHandlerSelectors.basePackage("com.posco.insta.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Insta").description("Insta api docs").version("1.0").build();
    }
}
