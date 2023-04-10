package com.agileboot.domain.system.monitor.dto;

import com.agileboot.domain.common.cache.CacheCenter;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.system.entity.SysDeptEntity;
import lombok.Data;

/**
 * 当前在线会话
 *
 * @author ruoyi
 */
@Data
public class OnlineUserInfo {

    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private Long loginTime;


    public OnlineUserInfo(LoginUser user) {
        if (user == null) {
            return;
        }
        this.setTokenId(user.getCachedKey());
        this.tokenId = user.getCachedKey();
        this.userName = user.getUsername();
        this.ipaddr = user.getLoginInfo().getIpAddress();
        this.loginLocation = user.getLoginInfo().getLocation();
        this.browser = user.getLoginInfo().getBrowser();
        this.os = user.getLoginInfo().getOperationSystem();
        this.loginTime = user.getLoginTime();

        SysDeptEntity deptEntity = CacheCenter.deptCache.get(user.getDeptId() + "");

        if (deptEntity != null) {
            this.deptName = deptEntity.getDeptName();
        }
    }

}
