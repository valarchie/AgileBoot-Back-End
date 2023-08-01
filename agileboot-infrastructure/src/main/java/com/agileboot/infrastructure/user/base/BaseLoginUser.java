package com.agileboot.infrastructure.user.base;

import cn.hutool.extra.servlet.ServletUtil;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.ip.IpRegionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.bitwalker.useragentutils.UserAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户身份权限
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class BaseLoginUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    protected Long userId;

    /**
     * 用户唯一标识，缓存的key
     */
    protected String cachedKey;

    protected String username;

    protected String password;

    protected List<GrantedAuthority> authorities = new ArrayList<>();
    /**
     * 登录信息
     */
    protected final LoginInfo loginInfo = new LoginInfo();


    public BaseLoginUser(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * 设置用户代理信息
     *
     */
    public void fillLoginInfo() {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletHolderUtil.getRequest().getHeader("User-Agent"));
        String ip = ServletUtil.getClientIP(ServletHolderUtil.getRequest());

        this.getLoginInfo().setIpAddress(ip);
        this.getLoginInfo().setLocation(IpRegionUtil.getBriefLocationByIp(ip));
        this.getLoginInfo().setBrowser(userAgent.getBrowser().getName());
        this.getLoginInfo().setOperationSystem(userAgent.getOperatingSystem().getName());
        this.getLoginInfo().setLoginTime(System.currentTimeMillis());
    }

    public void grantAppPermission(String appName) {
        authorities.add(new SimpleGrantedAuthority(appName));
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
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


}
