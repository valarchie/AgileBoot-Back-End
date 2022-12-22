package com.agileboot.infrastructure.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器模板代码
 * @author valarchie
 */
public class RepeatReadRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest currentRequest = (HttpServletRequest) request;
        RepeatedlyRequestWrapper wrappedRequest = new RepeatedlyRequestWrapper(currentRequest);
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void destroy() {

    }
}
