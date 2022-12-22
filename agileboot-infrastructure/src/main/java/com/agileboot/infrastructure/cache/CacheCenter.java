package com.agileboot.infrastructure.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.infrastructure.cache.guava.AbstractGuavaCacheTemplate;
import com.agileboot.infrastructure.cache.guava.GuavaCacheService;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.cache.redis.RedisCacheTemplate;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.system.entity.SysDeptEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 缓存中心  提供全局访问点
 * @author valarchie
 */
@Component
public class CacheCenter {

    public static AbstractGuavaCacheTemplate<String> configCache;

    public static AbstractGuavaCacheTemplate<SysDeptEntity> deptCache;

    public static RedisCacheTemplate<String> captchaCache;

    public static RedisCacheTemplate<LoginUser> loginUserCache;

    public static RedisCacheTemplate<SysUserEntity> userCache;

    @PostConstruct
    public void init() {
        GuavaCacheService guavaCache = SpringUtil.getBean(GuavaCacheService.class);
        RedisCacheService redisCache = SpringUtil.getBean(RedisCacheService.class);

        configCache = guavaCache.configCache;
        deptCache = guavaCache.deptCache;

        captchaCache = redisCache.captchaCache;
        loginUserCache = redisCache.loginUserCache;
        userCache = redisCache.userCache;
    }

}
