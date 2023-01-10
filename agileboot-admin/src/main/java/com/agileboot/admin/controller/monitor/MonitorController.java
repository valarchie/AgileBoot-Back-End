package com.agileboot.admin.controller.monitor;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.system.monitor.MonitorApplicationService;
import com.agileboot.domain.system.monitor.dto.OnlineUserInfo;
import com.agileboot.domain.system.monitor.dto.RedisCacheInfoDTO;
import com.agileboot.domain.system.monitor.dto.ServerInfo;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.orm.common.enums.BusinessTypeEnum;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存监控
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitorController extends BaseController {

    @NonNull
    private MonitorApplicationService monitorApplicationService;
    @NonNull
    private RedisCacheService redisCacheService;


    @PreAuthorize("@permission.has('monitor:cache:list')")
    @GetMapping("/cacheInfo")
    public ResponseDTO<RedisCacheInfoDTO> getRedisCacheInfo() {
        RedisCacheInfoDTO redisCacheInfo = monitorApplicationService.getRedisCacheInfo();
        return ResponseDTO.ok(redisCacheInfo);
    }


    @PreAuthorize("@permission.has('monitor:server:list')")
    @GetMapping("/serverInfo")
    public ResponseDTO<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = monitorApplicationService.getServerInfo();
        return ResponseDTO.ok(serverInfo);
    }

    /**
     * 获取在线用户列表
     * @param ipaddr
     * @param userName
     * @return
     */
    @PreAuthorize("@permission.has('monitor:online:list')")
    @GetMapping("/onlineUser/list")
    public ResponseDTO<PageDTO> list(String ipaddr, String userName) {
        List<OnlineUserInfo> onlineUserList = monitorApplicationService.getOnlineUserList(userName, ipaddr);
        return ResponseDTO.ok(new PageDTO<>(onlineUserList));
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@permission.has('monitor:online:forceLogout')")
    @AccessLog(title = "在线用户", businessType = BusinessTypeEnum.FORCE_LOGOUT)
    @DeleteMapping("/onlineUser/{tokenId}")
    public ResponseDTO<Object> forceLogout(@PathVariable String tokenId) {
        redisCacheService.loginUserCache.delete(tokenId);
        return ResponseDTO.ok();
    }


}
