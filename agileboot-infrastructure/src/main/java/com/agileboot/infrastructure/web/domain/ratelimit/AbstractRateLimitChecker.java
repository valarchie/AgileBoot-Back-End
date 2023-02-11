package com.agileboot.infrastructure.web.domain.ratelimit;

import com.agileboot.infrastructure.annotations.RateLimit;

/**
 * @author valarchie
 */
public abstract class AbstractRateLimitChecker {

    /**
     * 检查是否超出限流
     * @param rateLimiter
     */
    public abstract void check(RateLimit rateLimiter);

}
