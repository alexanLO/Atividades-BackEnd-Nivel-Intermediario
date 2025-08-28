package com.alexan.spring_ecossistema.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexan.spring_ecossistema.filter.AuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registrationBean() {

        FilterRegistrationBean<AuthenticationFilter> register = new FilterRegistrationBean<>();
        register.setFilter(authenticationFilter);
        register.addUrlPatterns("/**");
        return register;
    }
}
