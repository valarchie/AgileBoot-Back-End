package com.agileboot.infrastructure.cache.redis;

import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.infrastructure.cache.RedisUtil;
import com.agileboot.infrastructure.interceptor.repeatSubmit.RepeatRequest;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.service.ISysUserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
public class RedisCacheService {

    @Autowired
    private RedisUtil redisUtil;

    public RedisCacheTemplate<String> captchaCache;
    public RedisCacheTemplate<LoginUser> loginUserCache;
    public RedisCacheTemplate<RepeatRequest> repeatSubmitCache;
    public RedisCacheTemplate<SysUserEntity> userCache;

    @PostConstruct
    public void init() {

        captchaCache = new RedisCacheTemplate<>(redisUtil, CacheKeyEnum.CAPTCHAT);

        loginUserCache = new RedisCacheTemplate<>(redisUtil, CacheKeyEnum.LOGIN_USER_KEY);

        repeatSubmitCache = new RedisCacheTemplate<>(redisUtil, CacheKeyEnum.REPEAT_SUBMIT_KEY);

        userCache = new RedisCacheTemplate(redisUtil, CacheKeyEnum.USER_ENTITY_KEY) {
            @Override
            public Object getObjectFromDb(Object id) {
                ISysUserService userService = SpringUtil.getBean(ISysUserService.class);
                return userService.getById((Serializable) id);
            }
        };

    }


}
