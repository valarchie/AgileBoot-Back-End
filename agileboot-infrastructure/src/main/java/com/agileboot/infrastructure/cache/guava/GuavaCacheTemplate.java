package com.agileboot.infrastructure.cache.guava;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Ticker;
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
public abstract class GuavaCacheTemplate<T> {

    private final LoadingCache<String, Optional<T>> guavaCache = CacheBuilder.newBuilder()
        // 基于容量回收。缓存的最大数量。超过就取MAXIMUM_CAPACITY = 1 << 30。依靠LRU队列recencyQueue来进行容量淘汰
        .maximumSize(1024)
        // 基于容量回收。但这是统计占用内存大小，maximumWeight与maximumSize不能同时使用。设置最大总权重
//        .maximumWeight(1000)
        // 设置权重（可当成每个缓存占用的大小）
//        .weigher((o, o2) -> 5)
        // 软弱引用（引用强度顺序：强软弱虚）
        // -- 弱引用key
//        .weakKeys()
        // -- 弱引用value
//        .weakValues()
        // -- 软引用value
//        .softValues()
        // 过期失效回收
        // -- 没读写访问下，超过5秒会失效(非自动失效，需有任意get put方法才会扫描过期失效数据)
//        .expireAfterAccess(5L, TimeUnit.MINUTES)
        // -- 没写访问下，超过5秒会失效(非自动失效，需有任意put get方法才会扫描过期失效数据)
//        .expireAfterWrite(5L, TimeUnit.MINUTES)
        // 没写访问下，超过5秒会失效(非自动失效，需有任意put get方法才会扫描过期失效数据。但区别是会开一个异步线程进行刷新，刷新过程中访问返回旧数据)
        .refreshAfterWrite(5L, TimeUnit.MINUTES)
        // 移除监听事件
        .removalListener(removal -> {
            // 可做一些删除后动作，比如上报删除数据用于统计
            log.info("触发删除动作，删除的key={}, value={}", removal.getKey(), removal.getValue());
        })
        // 并行等级。决定segment数量的参数，concurrencyLevel与maxWeight共同决定
        .concurrencyLevel(16)
        // 开启缓存统计。比如命中次数、未命中次数等
        .recordStats()
        // 所有segment的初始总容量大小
        .initialCapacity(512)
        // 用于测试，可任意改变当前时间。参考：https://www.geek-share.com/detail/2689756248.html
        .ticker(new Ticker() {
            @Override
            public long read() {
                return 0;
            }
        })
        .build(new CacheLoader<String, Optional<T>>() {
            @Override
            public Optional<T> load(String key) {
                T cacheObject = getObjectFromDb(key);
                log.debug("find the local guava cache of key: {}  is {}", key, cacheObject);
                return Optional.ofNullable(cacheObject);
            }
        });



    public T get(String key) {
        try {
            if (StrUtil.isEmpty(key)) {
                return null;
            }

            Optional<T> optional = guavaCache.get(key);
            return optional.orElse(null);
        } catch (ExecutionException e) {
            log.error("get cache object from guava cache failed.");
            e.printStackTrace();
            return null;
        }
    }


    public void invalidate(String key) {
        if (StrUtil.isEmpty(key)) {
            return;
        }

        guavaCache.invalidate(key);
    }

    public void invalidateAll() {
        guavaCache.invalidateAll();
    }

    /**
     * 从数据库加载数据
     * @param id
     * @return
     */
    public abstract T getObjectFromDb(Object id);

}
