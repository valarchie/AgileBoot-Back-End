package com.agileboot.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.role.command.AddRoleCommand;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.service.ISysRoleService;

/**
 * 角色模型工厂
 */
public class RoleModelFactory {

    public static RoleModel loadFromDb(Long roleId, ISysRoleService roleService) {

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
