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
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.service.TokenService;
import com.agileboot.infrastructure.web.service.UserDetailsServiceImpl;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysRoleService;
import com.agileboot.orm.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class RoleApplicationService {

    @NonNull
    private RoleModelFactory roleModelFactory;

    @NonNull
    private UserModelFactory userModelFactory;

    @NonNull
    private ISysRoleService roleService;

    @NonNull
    private ISysUserService userService;

    @NonNull
    private TokenService tokenService;

    @NonNull
    private UserDetailsServiceImpl userDetailsService;

    public PageDTO<RoleDTO> getRoleList(RoleQuery query) {
        Page<SysRoleEntity> page = roleService.page(query.toPage(), query.toQueryWrapper());
        List<RoleDTO> records = page.getRecords().stream().map(RoleDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public RoleDTO getRoleInfo(Long roleId) {
        SysRoleEntity byId = roleService.getById(roleId);
        return new RoleDTO(byId);
    }


    public void addRole(AddRoleCommand addCommand) {
        RoleModel roleModel = roleModelFactory.create();
        roleModel.loadAddCommand(addCommand);

        roleModel.checkRoleNameUnique();
        roleModel.checkRoleKeyUnique();

        roleModel.insert();
    }

    public void deleteRoleByBulk(List<Long> roleIds) {
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                RoleModel roleModel = roleModelFactory.loadById(roleId);

                roleModel.checkRoleCanBeDelete();

                roleModel.deleteById();
            }
        }
    }


    public void updateRole(UpdateRoleCommand updateCommand, LoginUser loginUser) {
        RoleModel roleModel = roleModelFactory.loadById(updateCommand.getRoleId());
        roleModel.loadUpdateCommand(updateCommand);

        roleModel.checkRoleKeyUnique();
        roleModel.checkRoleNameUnique();

        roleModel.updateById();

        if (loginUser.isAdmin()) {
            // TODO 如何实时刷新所有改动到的user的权限
            loginUser.getRoleInfo().setMenuPermissions(userDetailsService.getMenuPermissions(loginUser.getUserId()));
            tokenService.setLoginUser(loginUser);
        }
    }

    public void updateStatus(UpdateStatusCommand command) {
        RoleModel roleModel = roleModelFactory.loadById(command.getRoleId());

        roleModel.setStatus(command.getStatus());

        roleModel.updateById();
    }

    public void updateDataScope(UpdateDataScopeCommand command) {
        RoleModel roleModel = roleModelFactory.loadById(command.getRoleId());

        roleModel.setDeptIds(command.getDeptIds());
        roleModel.setDataScope(command.getDataScope());
        roleModel.generateDeptIdSet();

        roleModel.updateById();
    }


    public PageDTO<UserDTO> getAllocatedUserList(AllocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO<>(dtoList, page.getTotal());
    }

    public PageDTO<UserDTO> getUnallocatedUserList(UnallocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO<>(dtoList, page.getTotal());
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

        RoleModel roleModel = roleModelFactory.loadById(roleId);
        roleModel.checkRoleAvailable();

        for (Long userId : userIds) {
            UserModel user = userModelFactory.loadById(userId);
            user.setRoleId(roleId);
            user.updateById();
        }
    }


}
