package com.agileboot.infrastructure.filter;

import cn.hutool.core.util.StrUtil;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * 给每一个线程分配一个uuid 以便日志可以可以精准查询一条请求的所有日志
 * 过滤器
 * @author valarchie
 */
@AllArgsConstructor
@Slf4j
public class TraceIdFilter implements Filter {

    private String requestIdKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // 生成UUID并存储在MDC中  然后在日志中打印出来
            String uuid = UUID.randomUUID().toString();
            if (StrUtil.isNotEmpty(requestIdKey)) {
                MDC.put(requestIdKey, uuid);
                if (request instanceof HttpServletRequest) {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    // 把RequestId放到response的header中去方便追踪
                    httpResponse.setHeader(requestIdKey, uuid);
                }
            } else {
                // 如果requestIdKey为空的话  说明配置有问题
                log.error("traceRequestIdKey 为null, 请检查项目yml中traceRequestIdKey的配置是否正确。");
            }

            // 继续处理请求
            chain.doFilter(request, response);
        } finally {
            // 清除MDC中的UUID
            removeRequestIdSafely(requestIdKey);
        }
    }

    public void removeRequestIdSafely(String requestIdKey) {
        try {
            MDC.remove(requestIdKey);
        } catch (Exception e) {
            log.error("traceRequestIdKey 为null, 请检查项目yml中traceRequestIdKey的配置是否正确。", e);
        }
    }

}
