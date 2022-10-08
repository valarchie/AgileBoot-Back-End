package com.agileboot.infrastructure.cache.aop;

import cn.hutool.core.util.StrUtil;
import com.google.common.cache.CacheBuilder;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

//@Component
public class GuavaCacheBean implements Cache {

    /**
     * 缓存仓库
     */
    private com.google.common.cache.Cache<Object, Object> storage;

    @PostConstruct
    private void init() {
        storage = CacheBuilder.newBuilder()
            // 设置缓存的容量为100
            .maximumSize(100)
            // 设置初始容量为16
            .initialCapacity(16)
            // 设置过期时间为写入缓存后10分钟过期
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            .build();
    }

    @Override
    public String getName() {
        return CacheNameConstants.GUAVA;
    }



    @Override
    public ValueWrapper get(Object key) {
        if (Objects.isNull(key)) {
            return null;
        }
        Object ifPresent = storage.getIfPresent(key.toString());
        return Objects.isNull(ifPresent) ? null : new SimpleValueWrapper(ifPresent);
    }

    @Override
    public void put(Object key, Object value) {
        if (StrUtil.isEmpty((CharSequence) key)) {
            return;
        }
        storage.put(key, value);
    }

    @Override
    public void evict(Object key) {
        if (key == null) {
            return;
        }

        storage.invalidate(key);
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
    public void clear() {

    }

    @Override
    public Object getNativeCache() {
        return null;
    }
}
