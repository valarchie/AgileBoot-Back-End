package com.agileboot.infrastructure.web.service;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.login.RoleInfo;
import com.agileboot.orm.common.enums.UserStatusEnum;
import com.agileboot.orm.system.entity.SysMenuEntity;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.entity.SysRoleMenuEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysMenuService;
import com.agileboot.orm.system.service.ISysRoleMenuService;
import com.agileboot.orm.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义加载用户信息通过用户名
 * 用于SpringSecurity 登录流程
 * @see com.agileboot.infrastructure.config.SecurityConfig#filterChain(HttpSecurity)
 * @author valarchie
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @NonNull
    private ISysUserService userService;

    @NonNull
    private ISysRoleMenuService roleMenuService;

    @NonNull
    private ISysMenuService menuService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity userEntity = userService.getUserByUserName(username);
        if (userEntity == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new ApiException(ErrorCode.Business.USER_NON_EXIST, username);
        }
        if (!Objects.equals(UserStatusEnum.NORMAL.getValue(), userEntity.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ApiException(ErrorCode.Business.USER_IS_DISABLE, username);
        }

        SysRoleEntity roleEntity = userService.getRoleOfUser(userEntity.getUserId());
        RoleInfo roleInfo = new RoleInfo(
            roleEntity,
            getRoleKey(userEntity.getUserId()),
            getMenuPermissions(userEntity.getUserId()),
            getMenuIds(userEntity.getUserId(), userEntity.getRoleId())
        );

        return new LoginUser(userEntity, roleInfo);
    }

    /**
     * 获取角色数据权限
     * @param userId 用户信息
     * @return 角色权限信息
     */
    public String getRoleKey(Long userId) {
        // 管理员拥有所有权限
        if (LoginUser.isAdmin(userId)) {
            return "admin";
        }

        SysRoleEntity roleEntity = userService.getRoleOfUser(userId);
        return roleEntity == null ? "" : roleEntity.getRoleKey();
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermissions(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginUser.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(userService.getMenuPermissionsForUser(userId));
        }
        return perms;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户信息
     * @return 菜单权限信息
     */
    public Set<Long> getMenuIds(Long userId, Long roleId) {
        // 管理员拥有所有菜单
        if (LoginUser.isAdmin(userId)) {
            LambdaQueryWrapper<SysMenuEntity> menuQuery = Wrappers.lambdaQuery();
            menuQuery.select(SysMenuEntity::getMenuId);
            List<SysMenuEntity> menuList = menuService.list(menuQuery);

            return menuList.stream().map(SysMenuEntity::getMenuId).collect(Collectors.toSet());
        } else {
            LambdaQueryWrapper<SysRoleMenuEntity> menuQuery = Wrappers.lambdaQuery();
            menuQuery.select(SysRoleMenuEntity::getMenuId).eq(SysRoleMenuEntity::getRoleId, roleId);
            List<SysRoleMenuEntity> menuList = roleMenuService.list(menuQuery);

            return menuList.stream().map(SysRoleMenuEntity::getMenuId).collect(Collectors.toSet());
        }
    }


}
