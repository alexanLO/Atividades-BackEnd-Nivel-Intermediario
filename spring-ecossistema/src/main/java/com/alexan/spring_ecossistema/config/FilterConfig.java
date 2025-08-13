package com.alexan.spring_ecossistema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexan.spring_ecossistema.filter.AuthenticationFilter;

@Configuration
public class FilterConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registrationBean() {

        FilterRegistrationBean<AuthenticationFilter> register = new FilterRegistrationBean<>();
        register.setFilter(authenticationFilter);
        register.addUrlPatterns("/**");
        return register;
    }
}
