package com.agileboot.domain.system.role;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysRoleMenuEntity;
import com.agileboot.orm.service.ISysRoleMenuService;
import com.agileboot.orm.service.ISysRoleService;
import com.agileboot.orm.service.ISysUserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleModel extends SysRoleEntity {

    public RoleModel(SysRoleEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity,this);
        }
    }


    private List<Long> menuIds;

    private List<Long> deptIds;

    public void checkRoleNameUnique(ISysRoleService roleService) {
        if (roleService.checkRoleNameUnique(getRoleId(), getRoleName())) {
            throw new ApiException(ErrorCode.Business.ROLE_NAME_IS_NOT_UNIQUE, getRoleName());
        }
    }

    public void checkRoleCanBeDelete(ISysUserService userService) {
        if (userService.checkExistUserLinkToRole(getRoleId())) {
            throw new ApiException(ErrorCode.Business.ROLE_NAME_IS_NOT_UNIQUE, getRoleName());
        }
    }

    public void checkRoleKeyUnique(ISysRoleService roleService) {
        if (roleService.checkRoleKeyUnique(getRoleId(), getRoleKey())) {
            throw new ApiException(ErrorCode.Business.ROLE_KEY_IS_NOT_UNIQUE, getRoleKey());
        }
    }

    public void generateDeptIdSet() {
        if (deptIds == null) {
            setDeptIdSet("");
        }

        if (deptIds.size() > new HashSet<>(deptIds).size()) {
            throw new ApiException(ErrorCode.Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT);
        }

        String deptIdSet = StrUtil.join(",", deptIds);
        setDeptIdSet(deptIdSet);
    }



    public void insert(ISysRoleMenuService roleMenuService) {
        this.insert();
        saveMenus(roleMenuService);
    }

    public void updateById(ISysRoleMenuService roleMenuService) {
        this.updateById();
        // 清空之前的角色菜单关联
        roleMenuService.getBaseMapper().deleteByMap(Collections.singletonMap("role_id", getRoleId()));
        saveMenus(roleMenuService);
    }

    public void deleteById(ISysRoleMenuService roleMenuService) {
        this.deleteById();
        // 清空之前的角色菜单关联
        roleMenuService.getBaseMapper().deleteByMap(Collections.singletonMap("role_id", getRoleId()));
    }


    public void saveMenus(ISysRoleMenuService roleMenuService) {
        List<SysRoleMenuEntity> list = new ArrayList<>();
        if (getMenuIds() != null) {
            for (Long menuId : getMenuIds()) {
                SysRoleMenuEntity rm = new SysRoleMenuEntity();
                rm.setRoleId(getRoleId());
                rm.setMenuId(menuId);
                list.add(rm);
            }
            roleMenuService.saveBatch(list);
        }
    }

}
