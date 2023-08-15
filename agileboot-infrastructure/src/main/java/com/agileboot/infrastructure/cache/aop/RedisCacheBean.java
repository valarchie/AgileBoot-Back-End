package com.agileboot.infrastructure.cache.aop;

import cn.hutool.core.util.StrUtil;
import com.agileboot.infrastructure.cache.RedisUtil;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * @author valarchie
 */
//@Component
@RequiredArgsConstructor
public class RedisCacheBean implements Cache {

    /**
     * 缓存仓库
     */
    private final RedisUtil redisUtil;

    @Override
    public String getName() {
        return CacheNameConstants.REDIS;
    }


    @Override
    public void put(Object key, Object value) {
        if (StrUtil.isNotEmpty((CharSequence) key)) {
            redisUtil.setCacheObject((String) key, value);
        }
    }

    @Override
    public void evict(Object key) {
        if (StrUtil.isNotEmpty((CharSequence) key)) {
            redisUtil.deleteObject((String) key);
        }
    }

    @Override
    public ValueWrapper get(Object key) {
        return key == null ? null : new SimpleValueWrapper(redisUtil.getCacheObject((String) key));
    }


    /*-----------------------暂时不用实现的方法-----------------*/


    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public void clear() {

    }
}
