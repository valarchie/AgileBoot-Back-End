package com.agileboot.domain.system.monitor;

import cn.hutool.core.util.StrUtil;
import com.agileboot.domain.common.cache.CacheCenter;
import com.agileboot.domain.system.monitor.dto.OnlineUserInfo;
import com.agileboot.domain.system.monitor.dto.RedisCacheInfoDTO;
import com.agileboot.domain.system.monitor.dto.RedisCacheInfoDTO.CommonStatusDTO;
import com.agileboot.domain.system.monitor.dto.ServerInfo;
import com.agileboot.infrastructure.cache.RedisUtil;
import com.agileboot.infrastructure.cache.redis.CacheKeyEnum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class MonitorApplicationService {

    @NonNull
    private RedisTemplate redisTemplate;

    @NonNull
    private RedisUtil redisUtil;


    public RedisCacheInfoDTO getRedisCacheInfo() {

        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::info);
        Properties commandStats = (Properties) redisTemplate.execute(
            (RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::dbSize);

        if(commandStats == null) {
            throw new RuntimeException("找不到对应的redis信息。");
        }

        RedisCacheInfoDTO cacheInfo = new RedisCacheInfoDTO();

        cacheInfo.setInfo(info);
        cacheInfo.setDbSize(dbSize);
        cacheInfo.setCommandStats(new ArrayList<>());

        commandStats.stringPropertyNames().forEach(key -> {
            String property = commandStats.getProperty(key);

            RedisCacheInfoDTO.CommonStatusDTO commonStatus = new CommonStatusDTO();
            commonStatus.setName(StrUtil.removePrefix(key, "cmdstat_"));
            commonStatus.setValue(StrUtil.subBetween(property, "calls=", ",usec"));

            cacheInfo.getCommandStats().add(commonStatus);
        });

        return cacheInfo;
    }

    public List<OnlineUserInfo> getOnlineUserList(String userName, String ipaddr) {
        Collection<String> keys = redisUtil.keys(CacheKeyEnum.LOGIN_USER_KEY.key() + "*");

        Stream<OnlineUserInfo> onlineUserStream = keys.stream().map(o ->
                    CacheCenter.loginUserCache.getObjectOnlyInCacheByKey(o))
            .filter(Objects::nonNull).map(OnlineUserInfo::new);

        List<OnlineUserInfo> filteredOnlineUsers = onlineUserStream
            .filter(o ->
                StrUtil.isEmpty(userName) || userName.equals(o.getUserName())
            ).filter( o ->
                StrUtil.isEmpty(ipaddr) || ipaddr.equals(o.getIpaddr())
            ).collect(Collectors.toList());

        Collections.reverse(filteredOnlineUsers);
        return filteredOnlineUsers;
    }

    public ServerInfo getServerInfo() {
        return ServerInfo.fillInfo();
    }


}
