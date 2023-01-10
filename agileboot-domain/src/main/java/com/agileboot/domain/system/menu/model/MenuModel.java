package com.agileboot.domain.system.menu.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.menu.command.AddMenuCommand;
import com.agileboot.domain.system.menu.command.UpdateMenuCommand;
import com.agileboot.orm.system.entity.SysMenuEntity;
import com.agileboot.orm.system.service.ISysMenuService;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@NoArgsConstructor
public class MenuModel extends SysMenuEntity {

    private ISysMenuService menuService;

    public MenuModel(ISysMenuService menuService) {
        this.menuService = menuService;
    }

    public MenuModel(SysMenuEntity entity, ISysMenuService menuService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.menuService = menuService;
    }

    public void loadAddCommand(AddMenuCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "menuId");
        }
    }

    public void loadUpdateCommand(UpdateMenuCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }


    public void checkMenuNameUnique() {
        if (menuService.isMenuNameDuplicated(getMenuName(), getMenuId(), getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkExternalLink() {
        if (getIsExternal() && !HttpUtil.isHttp(getPath()) && !HttpUtil.isHttps(getPath())) {
            throw new ApiException(ErrorCode.Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP);
        }
    }


    public void checkParentIdConflict() {
        if (getMenuId().equals(getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_PARENT_ID_NOT_ALLOW_SELF);
        }
    }


    public void checkHasChildMenus() {
        if (menuService.hasChildrenMenu(getMenuId())) {
            throw new ApiException(ErrorCode.Business.MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE);
        }
    }


    public void checkMenuAlreadyAssignToRole() {
        if (menuService.isMenuAssignToRoles(getMenuId())) {
            throw new ApiException(ErrorCode.Business.MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE);
        }
    }


}
