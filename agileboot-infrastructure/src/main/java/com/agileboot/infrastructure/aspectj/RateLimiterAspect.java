package com.agileboot.infrastructure.aspectj;

import com.agileboot.infrastructure.annotations.RateLimit;
import com.agileboot.infrastructure.web.domain.ratelimit.MapRateLimitChecker;
import com.agileboot.infrastructure.web.domain.ratelimit.RedisRateLimitChecker;
import java.lang.reflect.Method;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * 限流切面处理
 *
 * @author valarchie
 */
@Aspect
@Component
@Slf4j
@ConditionalOnExpression("'${agileboot.embedded.redis}' != 'true'")
@RequiredArgsConstructor
public class RateLimiterAspect {

    @NonNull
    private RedisRateLimitChecker redisRateLimitChecker;

    @NonNull
    private MapRateLimitChecker mapRateLimitChecker;


    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimit rateLimiter) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        log.info("当前限流方法:" + method.toGenericString());

        switch (rateLimiter.cacheType()) {
            case REDIS:
                redisRateLimitChecker.check(rateLimiter);
                break;
            case Map:
                mapRateLimitChecker.check(rateLimiter);
                return;
            default:
                redisRateLimitChecker.check(rateLimiter);
        }

    }

}
