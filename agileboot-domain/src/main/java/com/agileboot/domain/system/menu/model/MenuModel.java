package com.agileboot.domain.system.menu.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.service.ISysMenuService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MenuModel extends SysMenuEntity {

    public MenuModel(SysMenuEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }


    public void checkMenuNameUnique(ISysMenuService menuService) {
        if (!menuService.isMenuNameUnique(getMenuName(), getMenuId(), getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_NAME_IS_NOT_UNIQUE);
        }
    }


    public void checkExternalLink() {
        if (getIsExternal() && !HttpUtil.isHttp(getPath()) && !HttpUtil.isHttps(getPath())) {
            throw new ApiException(ErrorCode.Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP);
        }
    }


    public void checkParentId() {
        if (getMenuId().equals(getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_PARENT_ID_NOT_ALLOW_SELF);
        }
    }

    public void checkHasChildMenus(ISysMenuService menuService) {
        if (menuService.hasChildrenMenu(getMenuId())) {
            throw new ApiException(ErrorCode.Business.MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE);
        }
    }

    public void checkMenuAlreadyAssignToRole(ISysMenuService menuService) {
        if (menuService.isMenuAssignToRoles(getMenuId())) {
            throw new ApiException(ErrorCode.Business.MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE);
        }
    }


}
