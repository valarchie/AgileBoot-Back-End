package com.agileboot.domain.system.role;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.system.role.command.AddRoleCommand;
import com.agileboot.domain.system.role.command.UpdateDataScopeCommand;
import com.agileboot.domain.system.role.command.UpdateRoleCommand;
import com.agileboot.domain.system.role.command.UpdateStatusCommand;
import com.agileboot.domain.system.role.dto.RoleDTO;
import com.agileboot.domain.system.role.model.RoleModel;
import com.agileboot.domain.system.role.model.RoleModelFactory;
import com.agileboot.domain.system.role.query.AllocatedRoleQuery;
import com.agileboot.domain.system.role.query.RoleQuery;
import com.agileboot.domain.system.role.query.UnallocatedRoleQuery;
import com.agileboot.domain.system.user.dto.UserDTO;
import com.agileboot.domain.system.user.model.UserModel;
import com.agileboot.domain.system.user.model.UserModelFactory;
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

/**
 * @author valarchie
 */
@Service
public class RoleApplicationService {

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
        SysRoleEntity byId = RoleModelFactory.loadFromDb(roleId, roleService, roleMenuService);
        return new RoleDTO(byId);
    }


    public void addRole(AddRoleCommand addCommand, LoginUser loginUser) {
        RoleModel roleModel = RoleModelFactory.loadFromAddCommand(addCommand, new RoleModel());

        roleModel.checkRoleNameUnique(roleService);
        roleModel.checkRoleKeyUnique(roleService);

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
        RoleModel roleModel = RoleModelFactory.loadFromDb(roleId, roleService, roleMenuService);

        roleModel.checkRoleCanBeDelete(roleService);

        roleModel.deleteById(roleMenuService);
    }


    public void updateRole(UpdateRoleCommand updateCommand, LoginUser loginUser) {
        RoleModel roleModel = RoleModelFactory.loadFromDb(updateCommand.getRoleId(), roleService, roleMenuService);
        roleModel.loadFromUpdateCommand(updateCommand);

        roleModel.checkRoleKeyUnique(roleService);
        roleModel.checkRoleNameUnique(roleService);

        roleModel.updateById(roleMenuService);

        if (loginUser.isAdmin()) {
            loginUser.getRoleInfo().setMenuPermissions(userDetailsService.getMenuPermissions(loginUser.getUserId()));
            tokenService.setLoginUser(loginUser);
        }
    }

    public void updateStatus(UpdateStatusCommand command, LoginUser loginUser) {
        RoleModel roleModel = RoleModelFactory.loadFromDb(command.getRoleId(), roleService, roleMenuService);
        roleModel.setStatus(command.getStatus());
        roleModel.setUpdaterId(loginUser.getUserId());
//        roleModel.setUpdaterName(loginUser.getUsername());
        roleModel.updateById();
    }

    public void updateDataScope(UpdateDataScopeCommand command) {
        RoleModel roleModel = RoleModelFactory.loadFromDb(command.getRoleId(), roleService, roleMenuService);
        roleModel.setDeptIds(command.getDeptIds());
        roleModel.setDataScope(command.getDataScope());

        roleModel.generateDeptIdSet();
        roleModel.updateById();

        CacheCenter.roleCache.invalidate(command.getRoleId() + "");

    }


    public PageDTO getAllocatedUserList(AllocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO(dtoList, page.getTotal());
    }

    public PageDTO getUnallocatedUserList(UnallocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO(dtoList, page.getTotal());
    }


    public void deleteRoleOfUser(Long userId) {
        UserModel user = UserModelFactory.loadFromDb(userId, userService);
        user.setRoleId(null);
        user.updateById();
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
            UserModel user = UserModelFactory.loadFromDb(userId, userService);
            user.setRoleId(roleId);
            user.updateById();
        }
    }


}
