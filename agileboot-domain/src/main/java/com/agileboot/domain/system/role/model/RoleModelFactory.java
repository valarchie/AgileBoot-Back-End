package com.agileboot.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.role.command.AddRoleCommand;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.service.ISysRoleMenuService;
import com.agileboot.orm.system.service.ISysRoleService;

/**
 * 角色模型工厂
 * @author valarchie
 */
public class RoleModelFactory {

    // TODO roleMenuService 看看重构完要不要删掉
    public static RoleModel loadFromDb(Long roleId, ISysRoleService roleService, ISysRoleMenuService roleMenuService) {

        SysRoleEntity byId = roleService.getById(roleId);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, roleId, "角色");
        }

        return new RoleModel(byId);
    }

    public static RoleModel loadFromAddCommand(AddRoleCommand addCommand, RoleModel model) {
        if (addCommand != null && model != null) {
            BeanUtil.copyProperties(addCommand, model);
        }
        return model;
    }


}
