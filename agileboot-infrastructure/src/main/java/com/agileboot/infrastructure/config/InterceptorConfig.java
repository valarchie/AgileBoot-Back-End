package com.agileboot.infrastructure.config;

import com.agileboot.infrastructure.interceptor.repeatsubmit.ResubmitInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Filter配置
 * @author valarchie
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    @NonNull
    private ResubmitInterceptor resubmitInterceptor;

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resubmitInterceptor).addPathPatterns("/**");
    }

}
