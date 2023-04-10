package com.agileboot.infrastructure.web.domain.login;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.ip.IpRegionUtil;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.bitwalker.useragentutils.UserAgent;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户身份权限
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long userId;

    /**
     * 用户唯一标识，缓存的key
     */
    private String cachedKey;

    private boolean isAdmin;

    private String username;

    private String password;

    /**
     * 登录信息
     */
    private final LoginInfo loginInfo = new LoginInfo();

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 当超过这个时间 则触发刷新缓存时间
     */
    private Long autoRefreshCacheTime;


    public LoginUser(Long userId, Boolean isAdmin, String username, String password) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
    }

    public RoleInfo getRoleInfo() {
        return SpringUtil.getBean(RedisCacheService.class).roleModelInfoCache.getObjectById(getRoleId());
    }

    public Long getRoleId() {
        if (isAdmin()) {
            return RoleInfo.ADMIN_ROLE_ID;
        } else {
            return SpringUtil.getBean(RedisCacheService.class).userCache.getObjectById(userId).getRoleId();
        }
    }

    public Long getDeptId() {
        return SpringUtil.getBean(RedisCacheService.class).userCache.getObjectById(userId).getDeptId();
    }

    /**
     * 设置用户代理信息
     *
     */
    public void fillUserAgent() {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletHolderUtil.getRequest().getHeader("User-Agent"));
        String ip = ServletUtil.getClientIP(ServletHolderUtil.getRequest());

        this.getLoginInfo().setIpAddress(ip);
        this.getLoginInfo().setLocation(IpRegionUtil.getBriefLocationByIp(ip));
        this.getLoginInfo().setBrowser(userAgent.getBrowser().getName());
        this.getLoginInfo().setOperationSystem(userAgent.getOperatingSystem().getName());
    }


    @Override
    public String getUsername() {
        return this.username;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 账户是否未过期,过期无法验证
     * 未实现此功能
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     * 未实现此功能
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * 未实现此功能
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     * 未实现此功能
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ListUtil.empty();
    }


}
