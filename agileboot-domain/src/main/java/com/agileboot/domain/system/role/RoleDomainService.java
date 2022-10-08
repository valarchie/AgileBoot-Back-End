package com.agileboot.domain.system.role;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.user.UserDTO;
import com.agileboot.infrastructure.cache.CacheCenter;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.service.TokenService;
import com.agileboot.infrastructure.web.service.UserDetailsServiceImpl;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.service.ISysRoleMenuService;
import com.agileboot.orm.service.ISysRoleService;
import com.agileboot.orm.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDomainService {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysRoleMenuService roleMenuService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public PageDTO getRoleList(RoleQuery query) {
        Page<SysRoleEntity> page = roleService.page(query.toPage(), query.toQueryWrapper());
        List<RoleDTO> records = page.getRecords().stream().map(RoleDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public RoleDTO getRoleInfo(Long roleId) {
        SysRoleEntity byId = roleService.getById(roleId);
        return new RoleDTO(byId);
    }


    public void addRole(AddRoleCommand addCommand, LoginUser loginUser) {
        RoleModel roleModel = addCommand.toModel();

        roleModel.checkRoleNameUnique(roleService);
        roleModel.checkRoleKeyUnique(roleService);

        roleModel.logCreator(loginUser);

        roleModel.insert(roleMenuService);
    }

    public void deleteRoleByBulk(List<Long> roleIds, LoginUser loginUser) {
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                deleteRole(roleId, loginUser);
            }
        }
    }

    public void deleteRole(Long roleId, LoginUser loginUser) {
        RoleModel roleModel = getRoleModel(roleId);

        roleModel.checkRoleNameUnique(roleService);

        roleModel.logUpdater(loginUser);

        roleModel.deleteById(roleMenuService);
    }


    public void updateRole(UpdateRoleCommand updateCommand, LoginUser loginUser) {
        SysRoleEntity byId = roleService.getById(updateCommand.getRoleId());

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, updateCommand.getRoleId(), "角色");
        }

        RoleModel roleModel = updateCommand.toModel();
        roleModel.checkRoleKeyUnique(roleService);
        roleModel.checkRoleNameUnique(roleService);

        roleModel.logUpdater(loginUser);

        roleModel.updateById(roleMenuService);

        if (loginUser.isAdmin()) {
            loginUser.setMenuPermissions(userDetailsService.getMenuPermissions(loginUser.getUserId()));
            tokenService.setLoginUser(loginUser);
        }
    }


    public RoleModel getRoleModel(Long roleId) {
        SysRoleEntity byId = roleService.getById(roleId);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, roleId, "角色");
        }

        return new RoleModel(byId);
    }

    public void updateStatus(UpdateStatusCommand command, LoginUser loginUser) {
        RoleModel roleModel = getRoleModel(command.getRoleId());
        roleModel.setStatus(command.getStatus());
        roleModel.setUpdaterId(loginUser.getUserId());
        roleModel.setUpdaterName(loginUser.getUsername());
        roleModel.updateById();
    }

    public void updateDataScope(UpdateDataScopeCommand command) {
        RoleModel roleModel = getRoleModel(command.getRoleId());
        roleModel.setDeptIds(command.getDeptIds());
        roleModel.setDataScope(command.getDataScope());

        roleModel.generateDeptIdSet();
        roleModel.updateById();

        CacheCenter.guavaCache.roleCache.invalidate(command.getRoleId() + "");

    }


    public PageDTO getAllocatedUserList(AllocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.selectAllocatedList(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO(dtoList, page.getTotal());
    }

    public PageDTO getUnallocatedUserList(UnallocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.selectUnallocatedList(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO(dtoList, page.getTotal());
    }


    public void deleteRoleOfUser(Long userId) {
        SysUserEntity user = userService.getById(userId);
        if (user != null) {
            user.setRoleId(null);
            user.updateById();
        }
    }

    public void deleteRoleOfUserByBulk(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return;
        }

        for (Long userId : userIds) {
            LambdaUpdateWrapper<SysUserEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(SysUserEntity::getRoleId, null).eq(SysUserEntity::getUserId, userId);

            userService.update(updateWrapper);
        }
    }

    public void addRoleOfUserByBulk(Long roleId, List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return;
        }

        for (Long userId : userIds) {
            SysUserEntity user = userService.getById(userId);
            user.setRoleId(roleId);
            user.updateById();
        }
    }


}
