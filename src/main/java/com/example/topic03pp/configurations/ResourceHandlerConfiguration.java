package com.example.topic03pp.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:/bms.properties")
public class ResourceHandlerConfiguration implements WebMvcConfigurer {

    @Value("${file.server.path}")
    private String SERVER_PATH;

    @Value("${file.client.path}")
    private String CLIENT_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


        registry.addResourceHandler(CLIENT_PATH + "**").addResourceLocations("file:" + SERVER_PATH);

        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");

        registry.addResourceHandler("/swagger-v2.1.3/**").addResourceLocations("classpath:/static/swagger-v2.1.3/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui").setViewName("/swagger-ui/index");

        registry.addViewController("/swagger-v2").setViewName("/swagger-ui-v2.1.3/index");
        registry.addViewController("/").setViewName("/swagger-ui-v2.1.3/index");

        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/accessdenied").setViewName("accessdeny");
    }

    //if we add this function no need CorsFilterConfiguration file

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "PUT", "POST", "OPTIONS", "DELETE");
    }
}
