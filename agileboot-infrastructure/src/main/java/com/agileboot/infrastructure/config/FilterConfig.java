package com.agileboot.infrastructure.config;

import com.agileboot.infrastructure.exception.GlobalExceptionFilter;
import com.agileboot.infrastructure.filter.TestFilter;
import com.agileboot.infrastructure.filter.TraceIdFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Filter配置
 * @author valarchie
 */
@Configuration
public class FilterConfig {

    // TODO 后续统一到一个properties 类中比较好
    @Value("${agileboot.traceRequestIdKey}")
    private String requestIdKey;


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
    public FilterRegistrationBean<TraceIdFilter> traceIdFilterRegistrationBean() {
        FilterRegistrationBean<TraceIdFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TraceIdFilter(requestIdKey));
        registration.addUrlPatterns("/*");
        registration.setName("traceIdFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<GlobalExceptionFilter> exceptionFilterRegistrationBean() {
        FilterRegistrationBean<GlobalExceptionFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new GlobalExceptionFilter());
        registration.addUrlPatterns("/*");
        registration.setName("exceptionFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOriginPattern("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 有效期 1800秒
        config.setMaxAge(1800L);
        // 添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // 返回新的CorsFilter
        return new CorsFilter(source);
    }


}
