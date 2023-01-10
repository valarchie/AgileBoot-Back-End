package com.agileboot.domain.system.menu.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.system.entity.SysMenuEntity;
import com.agileboot.orm.system.service.ISysMenuService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 菜单模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class MenuModelFactory {

    @NonNull
    private ISysMenuService menuService;

    public MenuModel loadById(Long menuId) {
        SysMenuEntity byId = menuService.getById(menuId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, menuId, "菜单");
        }
        return new MenuModel(byId, menuService);
    }

    public MenuModel create() {
        return new MenuModel(menuService);
    }


}
