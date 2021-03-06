package com.example.dictionary.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Value("${spring.profiles.active}")
    private String active;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试1")
                .enable(!StringUtils.equals(active, "prod"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(path-> StringUtils.startsWithIgnoreCase(path, "/data/user"))
                .build()
                .apiInfo(this.apiInfo());
    }

    @Bean
    public Docket apiTest(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试2")
                .enable(!StringUtils.equals(active, "prod"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(path-> StringUtils.startsWithIgnoreCase(path, "/data/test"))
                .build()
                .apiInfo(this.apiInfo());
    }

    @Bean
    public Docket apiTool(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("工具类")
                .enable(!StringUtils.equals(active, "prod"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(path-> StringUtils.startsWithIgnoreCase(path, "/tool"))
                .build()
                .apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("dictionary")
                .description("dictionary")
                .version("1.0.0")
                .build();
    }
}
