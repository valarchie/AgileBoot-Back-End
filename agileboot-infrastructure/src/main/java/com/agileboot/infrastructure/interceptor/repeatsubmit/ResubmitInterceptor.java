package com.agileboot.infrastructure.interceptor.repeatsubmit;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.jackson.JacksonUtil;
import com.agileboot.infrastructure.annotations.Resubmit;
import com.agileboot.infrastructure.cache.RedisUtil;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 重复提交拦截器 如果涉及前后端加解密的话  也可以通过继承RequestBodyAdvice来实现
 *
 * @author valarchie
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ResubmitInterceptor implements HandlerInterceptor {

    public static final String NO_LOGIN = "Anonymous";
    public static final String RESUBMIT_REDIS_KEY = "resubmit:{}:{}:{}";

    @NonNull
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Resubmit resubmitAnno = handlerMethod.getMethodAnnotation(Resubmit.class);

            /**  @see com.agileboot.infrastructure.filter.RepeatReadRequestFilter#doFilter */
            if (resubmitAnno != null) {
                // 仅获取Body中的参数，不获取url中的参数，因为重复提交都是使用POST请求中的body
                String currentRequest = JacksonUtil.to(ServletUtil.getBody(request));

                log.info("请求重复提交拦截，当前url:{}, 当前参数：{}", request.getRequestURI(), currentRequest);

                String redisKey = generateResubmitRedisKey(handlerMethod);
                String preRequest = redisUtil.getCacheObject(redisKey);
                if (preRequest != null) {
                    boolean isSameRequest = Objects.equals(currentRequest, preRequest);

                    if (isSameRequest) {
                        throw new ApiException(ErrorCode.Client.COMMON_REQUEST_RESUBMIT);
                    }
                }
                redisUtil.setCacheObject(redisKey, currentRequest, resubmitAnno.interval(), TimeUnit.SECONDS);
            }

        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    public String generateResubmitRedisKey(HandlerMethod handlerMethod) {
        String username;

        try {
            LoginUser loginUser = AuthenticationUtils.getLoginUser();
            username = loginUser.getUsername();
        } catch (Exception e) {
            username = NO_LOGIN;
        }

        return StrUtil.format(RESUBMIT_REDIS_KEY,
            handlerMethod.getMethod().getDeclaringClass().getName(),
            handlerMethod.getMethod().getName(),
            username);
    }


}
