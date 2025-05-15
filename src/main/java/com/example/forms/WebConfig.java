package com.example.forms;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Proofs/**")  // Use /** to serve all files inside the folder
                .addResourceLocations("file:C:\\\\Users\\\\Bashid\\\\Documents\\\\workspace-sts\\\\chitfunds_management_system\\\\src\\\\main\\\\resources\\\\static\\\\Proofs");
    }
}
