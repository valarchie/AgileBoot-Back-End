package com.agileboot.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.role.command.AddRoleCommand;
import com.agileboot.domain.system.role.command.UpdateRoleCommand;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.domain.system.role.db.SysRoleEntity;
import com.agileboot.domain.system.role.db.SysRoleMenuEntity;
import com.agileboot.domain.system.role.db.SysRoleMenuService;
import com.agileboot.domain.system.role.db.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleModel extends SysRoleEntity {

    private List<Long> menuIds;

    private List<Long> deptIds;

    private SysRoleService roleService;
    private SysRoleMenuService roleMenuService;

    public RoleModel(SysRoleService roleService, SysRoleMenuService roleMenuService) {
        this.roleService = roleService;
        this.roleMenuService = roleMenuService;
    }

    public RoleModel(SysRoleEntity entity, SysRoleService roleService, SysRoleMenuService roleMenuService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.roleService = roleService;
        this.roleMenuService = roleMenuService;
    }

    public void loadAddCommand(AddRoleCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "roleId");
        }
    }

    public void loadUpdateCommand(UpdateRoleCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }

    public void checkRoleNameUnique() {
        if (roleService.isRoleNameDuplicated(getRoleId(), getRoleName())) {
            throw new ApiException(ErrorCode.Business.ROLE_NAME_IS_NOT_UNIQUE, getRoleName());
        }
    }

    public void checkRoleCanBeDelete() {
        if (roleService.isAssignedToUsers(getRoleId())) {
            throw new ApiException(Business.ROLE_ALREADY_ASSIGN_TO_USER, getRoleName());
        }
    }

    public void checkRoleKeyUnique() {
        if (roleService.isRoleKeyDuplicated(getRoleId(), getRoleKey())) {
            throw new ApiException(ErrorCode.Business.ROLE_KEY_IS_NOT_UNIQUE, getRoleKey());
        }
    }

    public void checkRoleAvailable() {
        if (StatusEnum.DISABLE.getValue().equals(getStatus())) {
            throw new ApiException(Business.ROLE_IS_NOT_AVAILABLE, getRoleName());
        }
    }

    public void generateDeptIdSet() {
        if (deptIds == null) {
            setDeptIdSet("");
            return;
        }

        if (deptIds.size() > new HashSet<>(deptIds).size()) {
            throw new ApiException(ErrorCode.Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT);
        }

        String deptIdSet = StrUtil.join(",", deptIds);
        setDeptIdSet(deptIdSet);
    }



    @Override
    public boolean insert() {
        super.insert();
        return saveMenus();
    }

    @Override
    public boolean updateById() {
        // 清空之前的角色菜单关联
        cleanOldMenus();
        saveMenus();
        return super.updateById();
    }

    @Override
    public boolean deleteById() {
        // 清空之前的角色菜单关联
        cleanOldMenus();
        return super.deleteById();
    }


    private void cleanOldMenus() {
        LambdaQueryWrapper<SysRoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenuEntity::getRoleId, getRoleId());

        roleMenuService.remove(queryWrapper);
    }

    private boolean saveMenus() {
        List<SysRoleMenuEntity> list = new ArrayList<>();
        if (getMenuIds() != null) {
            for (Long menuId : getMenuIds()) {
                SysRoleMenuEntity rm = new SysRoleMenuEntity();
                rm.setRoleId(getRoleId());
                rm.setMenuId(menuId);
                list.add(rm);
            }
            return roleMenuService.saveBatch(list);
        }
        return false;
    }

}
