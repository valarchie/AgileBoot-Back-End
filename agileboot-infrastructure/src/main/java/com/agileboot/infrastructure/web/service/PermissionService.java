package com.agileboot.infrastructure.web.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.infrastructure.cache.guava.GuavaCacheService;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.login.Role;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.enums.DataScopeEnum;
import com.agileboot.orm.service.ISysDeptService;
import com.agileboot.orm.service.ISysUserService;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruoyi valarchie
 */
@Service("ss")
public class PermissionService {

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private GuavaCacheService guavaCacheService;

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";


    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPerm(String permission) {
        if (StrUtil.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        if (loginUser == null || CollUtil.isEmpty(loginUser.getMenuPermissions())) {
            return false;
        }
        return hasPermissions(loginUser.getMenuPermissions(), permission);
    }


    /**
     * 通过userId 校验当前用户 对 目标用户是否有操作权限
     * @param userId
     * @return
     */
    public boolean checkDataScopeWithUserId(Long userId) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();

        if (loginUser == null) {
            return false;
        }
        Role role = guavaCacheService.roleCache.get(loginUser.getRoleId() + "");
        SysUserEntity targetUser = userService.getById(userId);

        return checkDataScope(loginUser, role, targetUser.getDeptId(), userId);
    }

    /**
     * 通过userId 校验当前用户 对 目标用户是否有操作权限
     * @param userIds
     * @return
     */
    public boolean checkDataScopeWithUserIds(List<Long> userIds) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();

        if (loginUser == null) {
            return false;
        }
        Role role = guavaCacheService.roleCache.get(loginUser.getRoleId() + "");

        if (CollUtil.isNotEmpty(userIds)) {
            for (Long userId : userIds) {
                SysUserEntity targetUser = userService.getById(userId);
                boolean checkResult = checkDataScope(loginUser, role, targetUser.getDeptId(), userId);
                if (!checkResult) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkDataScopeWithDeptId(Long deptId) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        if (loginUser == null) {
            return false;
        }
        Role role = guavaCacheService.roleCache.get(loginUser.getRoleId() + "");

        return checkDataScope(loginUser, role, deptId, null);
    }

    public boolean checkDataScope(LoginUser loginUser, Role role, Long targetDeptId, Long targetUserId) {

        if (targetDeptId == null && role.getDataScope() != DataScopeEnum.ALL) {
            return false;
        }

        if(role.getDataScope() == DataScopeEnum.ALL) {
            return true;
        }

        if (role.getDataScope() == DataScopeEnum.SELF_DEFINE &&
            CollUtil.safeContains(role.getDeptIdSet(), targetDeptId)) {
            return true;
        }

        if(role.getDataScope() == DataScopeEnum.CURRENT_DEPT && Objects.equals(loginUser.getDeptId(), targetDeptId)) {
            return true;
        }

        if (role.getDataScope() == DataScopeEnum.CURRENT_DEPT_AND_CHILDREN_DEPT &&
            deptService.isChildOfTargetDeptId(loginUser.getDeptId(), targetDeptId)) {
            return true;
        }

        if (role.getDataScope() == DataScopeEnum.ONLY_SELF
            && targetUserId != null
            && Objects.equals(loginUser.getUserId(), targetUserId)) {
            return true;
        }

        return false;
    }






    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StrUtil.trim(permission));
    }
}
