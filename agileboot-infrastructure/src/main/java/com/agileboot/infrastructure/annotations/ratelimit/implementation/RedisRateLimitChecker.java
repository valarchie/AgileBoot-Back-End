package com.agileboot.infrastructure.annotations.ratelimit.implementation;

import cn.hutool.core.collection.ListUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.annotations.ratelimit.RateLimit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RedisRateLimitChecker extends AbstractRateLimitChecker{

    private final RedisTemplate<Object, Object> redisTemplate;

    private final RedisScript<Long> limitScript = new DefaultRedisScript<>(limitScriptText(), Long.class);

    @Override
    public void check(RateLimit rateLimiter) {
        int maxCount = rateLimiter.maxCount();
        String combineKey = rateLimiter.limitType().generateCombinedKey(rateLimiter);

        Long currentCount;
        try {
            currentCount = redisTemplate.execute(limitScript, ListUtil.of(combineKey), maxCount, rateLimiter.time());
            log.info("限制请求:{}, 当前请求次数:{}, 缓存key:{}", combineKey, currentCount, rateLimiter.key());
        } catch (Exception e) {
            throw new RuntimeException("redis限流器异常，请确保redis启动正常");
        }

        if (currentCount == null) {
            throw new RuntimeException("redis限流器异常，请稍后再试");
        }

        if (currentCount.intValue() > maxCount) {
            throw new ApiException(ErrorCode.Client.COMMON_REQUEST_TOO_OFTEN);
        }

    }

    /**
     * 限流脚本
     */
    private static String limitScriptText() {
        return "local key = KEYS[1]\n" +
            "local count = tonumber(ARGV[1])\n" +
            "local time = tonumber(ARGV[2])\n" +
            "local current = redis.call('get', key);\n" +
            "if current and tonumber(current) > count then\n" +
            "    return tonumber(current);\n" +
            "end\n" +
            "current = redis.call('incr', key)\n" +
            "if tonumber(current) == 1 then\n" +
            "    redis.call('expire', key, time)\n" +
            "end\n" +
            "return tonumber(current);";
    }

}
