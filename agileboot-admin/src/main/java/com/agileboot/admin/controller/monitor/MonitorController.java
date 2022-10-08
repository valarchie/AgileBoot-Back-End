package com.agileboot.admin.controller.monitor;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.system.monitor.MonitorDomainService;
import com.agileboot.domain.system.monitor.dto.RedisCacheInfoDTO;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.web.domain.OnlineUser;
import com.agileboot.infrastructure.web.domain.server.ServerInfo;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    @Autowired
    private MonitorDomainService monitorDomainService;

    @Autowired
    private RedisCacheService redisCacheService;

    @PreAuthorize("@ss.hasPerm('monitor:cache:list')")
    @GetMapping("/cacheInfo")
    public ResponseDTO<RedisCacheInfoDTO> getRedisCacheInfo() {
        RedisCacheInfoDTO redisCacheInfo = monitorDomainService.getRedisCacheInfo();
        return ResponseDTO.ok(redisCacheInfo);
    }


    @PreAuthorize("@ss.hasPerm('monitor:server:list')")
    @GetMapping("/serverInfo")
    public ResponseDTO<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = monitorDomainService.getServerInfo();
        return ResponseDTO.ok(serverInfo);
    }

    /**
     * 获取在线用户列表
     * @param ipaddr
     * @param userName
     * @return
     */
    @PreAuthorize("@ss.hasPerm('monitor:online:list')")
    @GetMapping("/onlineUser/list")
    public ResponseDTO<PageDTO> list(String ipaddr, String userName) {
        List<OnlineUser> onlineUserList = monitorDomainService.getOnlineUserList(userName, ipaddr);
        return ResponseDTO.ok(new PageDTO(onlineUserList));
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@ss.hasPerm('monitor:online:forceLogout')")
    @AccessLog(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/onlineUser/{tokenId}")
    public ResponseDTO<Object> forceLogout(@PathVariable String tokenId) {
        redisCacheService.loginUserCache.delete(tokenId);
        return ResponseDTO.ok();
    }


}
