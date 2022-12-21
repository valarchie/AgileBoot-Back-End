package com.agileboot.infrastructure.web.domain.ratelimit;

import com.agileboot.infrastructure.annotations.RateLimit;

public abstract class AbstractRateLimitChecker {

    public abstract void check(RateLimit rateLimiter);

}
