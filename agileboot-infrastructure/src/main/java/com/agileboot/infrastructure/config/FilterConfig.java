package com.agileboot.infrastructure.config;

import com.agileboot.infrastructure.filter.GlobalExceptionFilter;
import com.agileboot.infrastructure.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter配置
 * @author valarchie
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterRegistrationBean() {
        FilterRegistrationBean<TestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("testFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<GlobalExceptionFilter> exceptionFilterRegistrationBean() {
        FilterRegistrationBean<GlobalExceptionFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new GlobalExceptionFilter());
        registration.addUrlPatterns("/*");
        registration.setName("exceptionFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        return registration;
    }

}
