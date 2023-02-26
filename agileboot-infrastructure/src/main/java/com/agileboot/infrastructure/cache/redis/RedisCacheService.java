package com.agileboot.infrastructure.cache.redis;

import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.infrastructure.cache.RedisUtil;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.login.RoleInfo;
import com.agileboot.infrastructure.web.service.UserDetailsServiceImpl;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysUserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class RedisCacheService {

    @NonNull
    private RedisUtil redisUtil;

    public RedisCacheTemplate<String> captchaCache;
    public RedisCacheTemplate<LoginUser> loginUserCache;
    public RedisCacheTemplate<SysUserEntity> userCache;
    public RedisCacheTemplate<RoleInfo> roleInfoCache;

    @PostConstruct
    public void init() {

        captchaCache = new RedisCacheTemplate<>(redisUtil, CacheKeyEnum.CAPTCHAT);

        loginUserCache = new RedisCacheTemplate<>(redisUtil, CacheKeyEnum.LOGIN_USER_KEY);

        userCache = new RedisCacheTemplate<SysUserEntity>(redisUtil, CacheKeyEnum.USER_ENTITY_KEY) {
            @Override
            public SysUserEntity getObjectFromDb(Object id) {
                ISysUserService userService = SpringUtil.getBean(ISysUserService.class);
                return userService.getById((Serializable) id);
            }
        };

        roleInfoCache = new RedisCacheTemplate<RoleInfo>(redisUtil, CacheKeyEnum.ROLE_INFO_KEY) {
            @Override
            public RoleInfo getObjectFromDb(Object id) {
                UserDetailsServiceImpl userDetailsService = SpringUtil.getBean(UserDetailsServiceImpl.class);
                return userDetailsService.getRoleInfo((Long) id);
            }

        };

    }


}
