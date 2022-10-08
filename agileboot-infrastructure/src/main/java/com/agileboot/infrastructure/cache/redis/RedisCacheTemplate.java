package com.agileboot.infrastructure.cache.redis;

import com.agileboot.infrastructure.cache.RedisUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * 缓存接口实现类
 */
@Slf4j
public class RedisCacheTemplate<T> {

    private final RedisUtil redisUtil;
    private final CacheKeyEnum redisRedisEnum;
    private final LoadingCache<String, Optional<T>> guavaCache = CacheBuilder.newBuilder()
        // 基于容量回收。缓存的最大数量。超过就取MAXIMUM_CAPACITY = 1 << 30。依靠LRU队列recencyQueue来进行容量淘汰
        .maximumSize(1024)
        .softValues()
        // 没写访问下，超过5秒会失效(非自动失效，需有任意put get方法才会扫描过期失效数据。
        // 但区别是会开一个异步线程进行刷新，刷新过程中访问返回旧数据)
        .refreshAfterWrite(30L, TimeUnit.MINUTES)
        // 并行等级。决定segment数量的参数，concurrencyLevel与maxWeight共同决定
        .concurrencyLevel(64)
        // 所有segment的初始总容量大小
        .initialCapacity(128)
        .build(new CacheLoader<String, Optional<T>>() {
            @Override
            public Optional<T> load(String cachedKey) {
                T cacheObject = redisUtil.getCacheObject(cachedKey);
                log.debug("find the redis cache of key: {} is {}", cachedKey, cacheObject);
                return Optional.ofNullable(cacheObject);
            }
        });

    public RedisCacheTemplate(RedisUtil redisUtil, CacheKeyEnum redisRedisEnum) {
        this.redisUtil = redisUtil;
        this.redisRedisEnum = redisRedisEnum;
    }

    /**
     * 从缓存中获取对象   如果获取不到的话  从DB层面获取
     * @param id
     * @return
     */
    public T getObjectById(Object id) {
        String cachedKey = generateKey(id);
        try {
            Optional<T> optional = guavaCache.get(cachedKey);
            log.debug("find the guava cache of key: {}", cachedKey);

            if (!optional.isPresent()) {
                T objectFromDb = getObjectFromDb(id);
                set(id, objectFromDb);
                return objectFromDb;
            }

            return optional.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从缓存中获取 对象， 即使找不到的话 也不从DB中找
     * @param id
     * @return
     */
    public T getCachedObjectById(Object id) {
        String cachedKey = generateKey(id);
        try {
            Optional<T> optional = guavaCache.get(cachedKey);
            log.debug("find the guava cache of key: {}", cachedKey);
            return optional.orElse(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从缓存中获取 对象， 即使找不到的话 也不从DB中找
     * @param cachedKey 直接通过redis的key来搜索
     * @return
     */
    public T getCachedObjectByKey(String cachedKey) {
        try {
            Optional<T> optional = guavaCache.get(cachedKey);
            log.debug("find the guava cache of key: {}", cachedKey);
            return optional.orElse(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void set(Object id, T obj) {
        redisUtil.setCacheObject(generateKey(id), obj, redisRedisEnum.expiration(), redisRedisEnum.timeUnit());
        guavaCache.refresh(generateKey(id));
    }

    public void delete(Object id) {
        redisUtil.deleteObject(generateKey(id));
        guavaCache.refresh(generateKey(id));
    }

    public void refresh(Object id) {
        redisUtil.expire(generateKey(id), redisRedisEnum.expiration(), redisRedisEnum.timeUnit());
        guavaCache.refresh(generateKey(id));
    }

    public String generateKey(Object id) {
        return redisRedisEnum.key() + id;
    }

    public T getObjectFromDb(Object id) {
        return null;
    }

}
