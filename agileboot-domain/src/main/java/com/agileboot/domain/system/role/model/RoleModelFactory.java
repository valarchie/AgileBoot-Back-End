package com.agileboot.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.role.command.AddRoleCommand;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.service.ISysRoleMenuService;
import com.agileboot.orm.system.service.ISysRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 角色模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class RoleModelFactory {

    @NonNull
    private ISysRoleService roleService;

    @NonNull
    private ISysRoleMenuService roleMenuService;

    public RoleModel loadById(Long roleId) {
        SysRoleEntity byId = roleService.getById(roleId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, roleId, "角色");
        }
        return new RoleModel(byId, roleService, roleMenuService);
    }

    public RoleModel create() {
        return new RoleModel(roleService, roleMenuService);
    }


}
