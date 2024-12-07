package com.example.Recruitment.config;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ApiVersionConfig implements WebMvcConfigurer {
    public static final String API_VERSION = "/v1";
}
