package com.example.Expense.Management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ApiVersionConfig implements WebMvcConfigurer {
    public static final String API_VERSION = "/v1";
}

