package com.agileboot.infrastructure.web.domain.login;

import com.agileboot.common.core.base.BaseUser;
import com.agileboot.orm.entity.SysUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Set;
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
public class LoginUser extends BaseUser implements UserDetails {

    private static final long serialVersionUID = 1L;

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


    private LoginInfo loginInfo = new LoginInfo();

    /**
     * 权限列表
     */
    private Set<String> menuPermissions;

    private String roleKey;

    private SysUserEntity entity;


    public LoginUser(Set<String> permissions) {
        this.menuPermissions = permissions;
    }

    public LoginUser(SysUserEntity entity, String roleKey, Set<String> permissions) {
        setUsername(entity.getUsername());
        setUserId(entity.getUserId());
        setDeptId(entity.getDeptId());
        setRoleId(entity.getRoleId());
        this.menuPermissions = permissions;
        this.roleKey = roleKey;
        this.entity = entity;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return entity.getPassword();
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

    /**
     * 是否为管理员
     * @return 结果
     */
    public boolean isAdmin() {
        return isAdmin(getUserId());
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

}
