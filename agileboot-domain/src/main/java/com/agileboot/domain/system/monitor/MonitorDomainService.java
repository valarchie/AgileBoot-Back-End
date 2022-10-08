package com.agileboot.domain.system.monitor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.domain.system.monitor.dto.RedisCacheInfoDTO;
import com.agileboot.domain.system.monitor.dto.RedisCacheInfoDTO.CommonStatusDTO;
import com.agileboot.infrastructure.cache.RedisUtil;
import com.agileboot.infrastructure.cache.guava.GuavaCacheService;
import com.agileboot.infrastructure.cache.redis.CacheKeyEnum;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.web.domain.OnlineUser;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.server.ServerInfo;
import com.agileboot.orm.entity.SysDeptEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MonitorDomainService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisCacheService redisCacheService;


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

    public List<OnlineUser> getOnlineUserList(String userName, String ipaddr) {
        Collection<String> keys = redisUtil.keys(CacheKeyEnum.LOGIN_USER_KEY.key() + "*");

        List<OnlineUser> allOnlineUsers = keys.stream().map(
                o -> mapLoginUserToUserOnline(redisCacheService.loginUserCache.getCachedObjectByKey(o)))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        List<OnlineUser> filteredOnlineUsers = allOnlineUsers.stream()
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


    /**
     * 设置在线用户信息
     *
     * @param user 用户信息
     * @return 在线用户
     */
    public OnlineUser mapLoginUserToUserOnline(LoginUser user) {
        if (user == null) {
            return null;
        }
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setTokenId(user.getToken());
        onlineUser.setUserName(user.getUsername());
        onlineUser.setIpaddr(user.getLoginInfo().getIpAddress());
        onlineUser.setLoginLocation(user.getLoginInfo().getLocation());
        onlineUser.setBrowser(user.getLoginInfo().getBrowser());
        onlineUser.setOs(user.getLoginInfo().getOperationSystem());
        onlineUser.setLoginTime(user.getLoginTime());

        GuavaCacheService cacheService = SpringUtil.getBean(GuavaCacheService.class);

        SysDeptEntity deptEntity = cacheService.deptCache.get(user.getDeptId() + "");

        if (deptEntity != null) {
            onlineUser.setDeptName(deptEntity.getDeptName());
        }

        return onlineUser;
    }
}
