package com.agileboot.infrastructure.web.service;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.enums.UserStatusEnum;
import com.agileboot.orm.service.ISysUserService;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi valarchie
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;


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
        String roleKey = getRoleKey(userEntity.getUserId());
        Set<String> menuPermissions = getMenuPermissions(userEntity.getUserId());
//        SysRoleEntity roleById = roleService.getById(userEntity.getRoleId());
//
//        Role role = new Role();
//        if(roleById != null) {
//            role = new Role(roleById);
//        }

        return new LoginUser(userEntity, roleKey, menuPermissions);
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
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (LoginUser.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(userService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }



}
