package com.example.topic03pp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    @Bean
    public Docket api() {

        List<SecurityScheme> schemeList = new ArrayList<>();

        schemeList.add(new BasicAuth("Basic"));
        schemeList.add(new ApiKey("api_key", "Authorization", "header"));


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.topic03pp.controllers.restcontrollers"))
//                .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo());
//                .securityContexts()
//                .securitySchemes(schemeList);
//                .apiInfo();
    }


    public ApiInfo apiInfo() {


        List<VendorExtension> vendorExtensions = new ArrayList<>();
        vendorExtensions.add(null);
        Contact contact = new Contact("BMS-Test", "", "phang.ratanak12@gmail.com");
        ApiInfo apiInformation = new ApiInfo(
                "BMS-Api",
                "BMS Api Documentation",
                "Version 1.swagger-ui.swagger-ui",
                "Term of Service",
                contact,
                "License: Copy Righted",
                "http://www.khmeracademy.org",
                vendorExtensions
        );
        return apiInformation;

    }


    //With OAuth2
    /*@Bean
    SecurityConfiguration securityConfiguration() {
        return new SecurityConfiguration(
                null,
                null,
                null,
                null,
                null,
                null,
                true
        );
    }*/

}
