package com.agileboot.infrastructure.web.domain.login;

import com.agileboot.infrastructure.cache.CacheCenter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    private boolean isAdmin;

    /**
     * 登录信息
     */
    private LoginInfo loginInfo = new LoginInfo();


    public LoginUser(Long userId, Boolean isAdmin) {
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public RoleInfo getRoleInfo() {
        return CacheCenter.roleModelInfoCache.getObjectById(getRoleId());
    }

    public Long getRoleId() {
        if (isAdmin()) {
            return RoleInfo.ADMIN_ROLE_ID;
        } else {
            return CacheCenter.userCache.getObjectById(userId).getRoleId();
        }
    }

    public Long getDeptId() {
        return CacheCenter.userCache.getObjectById(userId).getDeptId();
    }

    @Override
    public String getUsername() {
        return CacheCenter.userCache.getObjectById(userId).getUsername();
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return CacheCenter.userCache.getObjectById(userId).getPassword();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


}
