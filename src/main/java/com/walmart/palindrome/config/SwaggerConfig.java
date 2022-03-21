package com.walmart.palindrome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.walmart.palindrome.controller"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Product", "Services to get products"))
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Palindrome Challenge API",
                "Palindrome Challenge API",
                "1.0.0-rc",
                "",
                new Contact("Leandro Cocchi", "", "leandro.cocchi@hotmail.com"),
                "",
                "",
                Collections.emptyList()
        );
    }
}
