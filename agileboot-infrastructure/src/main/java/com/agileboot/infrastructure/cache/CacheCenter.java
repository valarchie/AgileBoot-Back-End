package com.agileboot.infrastructure.cache;

import com.agileboot.infrastructure.cache.guava.GuavaCacheService;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存中心  提供全局访问点
 */
@Component
public class CacheCenter {

    public static GuavaCacheService guavaCache;

    public static RedisCacheService redisCache;

    @Autowired
    public void setGuavaCache(GuavaCacheService guavaCache) {
        CacheCenter.guavaCache = guavaCache;
    }

    @Autowired
    public void setRedisCache(RedisCacheService redisCache) {
        CacheCenter.redisCache = redisCache;
    }

}
