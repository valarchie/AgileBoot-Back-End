package com.agileboot.domain.system.menu.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.service.ISysMenuService;

/**
 * 菜单模型工厂
 * @author valarchie
 */
public class MenuModelFactory {

    public static MenuModel loadFromDb(Long menuId, ISysMenuService menuService) {
        SysMenuEntity byId = menuService.getById(menuId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, menuId, "菜单");
        }
        return new MenuModel(byId);
    }

}
