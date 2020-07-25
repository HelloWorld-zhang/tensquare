package com.tensquare.friend.ApplicationConfig;

import com.tensquare.user.filter.JwtFilter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter).
        addPathPatterns("/**").
        excludePathPatterns("/**/login");
    }
}
