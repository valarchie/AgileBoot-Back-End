package com.agileboot.infrastructure.web.domain.ratelimit;

/**
 * 限流key
 * @author valarchie
 */
public class RateLimitKey {

    public static final String PREFIX = "Rate-Limit:";

    public static final String LOGIN_CAPTCHA_KEY = PREFIX + "Login-Captcha:";

    public static final String TEST_KEY = PREFIX + "Test:";

    private RateLimitKey() {
    }
}
