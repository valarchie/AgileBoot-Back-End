package com.agileboot.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.role.command.AddRoleCommand;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysRoleMenuEntity;
import com.agileboot.orm.service.ISysRoleMenuService;
import com.agileboot.orm.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色模型工厂
 */
public class RoleModelFactory {

    public static RoleModel loadFromDb(Long roleId, ISysRoleService roleService, ISysRoleMenuService roleMenuService) {

        SysRoleEntity byId = roleService.getById(roleId);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, roleId, "角色");
        }

//        LambdaQueryChainWrapper<SysRoleMenuEntity> menuQuery = roleMenuService.lambdaQuery()
//            .select(SysRoleMenuEntity::getMenuId)
//            .eq(SysRoleMenuEntity::getRoleId, roleId);
//        List<SysRoleMenuEntity> list = roleMenuService.list(menuQuery);
//        List<Long> menuIds = list.stream().map(SysRoleMenuEntity::getMenuId).collect(Collectors.toList());

        return new RoleModel(byId);
    }

    public static RoleModel loadFromAddCommand(AddRoleCommand addCommand, RoleModel model) {
        if (addCommand != null && model != null) {
            BeanUtil.copyProperties(addCommand, model);
        }
        return model;
    }


}
