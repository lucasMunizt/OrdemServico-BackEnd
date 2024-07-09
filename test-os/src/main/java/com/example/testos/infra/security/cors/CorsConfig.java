package com.example.testos.infra.security.cors;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                . allowedHeaders("*")   //.allowedHeaders("*")
                .allowCredentials(true)//.allowCredentials(true)
                .maxAge(3600);//.maxAge(3600);
    }
}
