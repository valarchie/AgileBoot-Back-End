package com.agileboot.domain.system.menu.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.common.utils.jackson.JacksonUtil;
import com.agileboot.domain.system.menu.command.AddMenuCommand;
import com.agileboot.domain.system.menu.command.UpdateMenuCommand;
import com.agileboot.common.enums.common.MenuTypeEnum;
import com.agileboot.domain.system.menu.db.SysMenuEntity;
import com.agileboot.domain.system.menu.db.SysMenuService;
import java.util.Objects;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@NoArgsConstructor
public class MenuModel extends SysMenuEntity {

    private SysMenuService menuService;

    public MenuModel(SysMenuService menuService) {
        this.menuService = menuService;
    }

    public MenuModel(SysMenuEntity entity, SysMenuService menuService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.menuService = menuService;
    }

    public void loadAddCommand(AddMenuCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "menuId");

            String metaInfo = JacksonUtil.to(command.getMeta());
            this.setMetaInfo(metaInfo);
        }
    }

    public void loadUpdateCommand(UpdateMenuCommand command) {
        if (command != null) {
            if (!Objects.equals(this.getMenuType(), command.getMenuType()) && !this.getIsButton()) {
                throw new ApiException(Business.MENU_CAN_NOT_CHANGE_MENU_TYPE);
            }
            loadAddCommand(command);
        }
    }


    public void checkMenuNameUnique() {
        if (menuService.isMenuNameDuplicated(getMenuName(), getMenuId(), getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_NAME_IS_NOT_UNIQUE);
        }
    }

    /**
     * Iframe和外链跳转类型  不允许添加按钮
     */
    public void checkAddButtonInIframeOrOutLink() {
        SysMenuEntity parentMenu = menuService.getById(getParentId());

        if (parentMenu != null && getIsButton() && (
            Objects.equals(parentMenu.getMenuType(), MenuTypeEnum.IFRAME.getValue())
                || Objects.equals(parentMenu.getMenuType(),MenuTypeEnum.OUTSIDE_LINK_REDIRECT.getValue())
        )) {
            throw new ApiException(Business.MENU_NOT_ALLOWED_TO_CREATE_BUTTON_ON_IFRAME_OR_OUT_LINK);
        }
    }

    /**
     * 只允许在目录菜单类型底下 添加子菜单
     */
    public void checkAddMenuNotInCatalog() {
        SysMenuEntity parentMenu = menuService.getById(getParentId());

        if (parentMenu != null && !getIsButton() && (
            !Objects.equals(parentMenu.getMenuType(), MenuTypeEnum.CATALOG.getValue())
        )) {
            throw new ApiException(Business.MENU_ONLY_ALLOWED_TO_CREATE_SUB_MENU_IN_CATALOG);
        }
    }


    public void checkExternalLink() {
//        if (getIsExternal() && !HttpUtil.isHttp(getPath()) && !HttpUtil.isHttps(getPath())) {
//            throw new ApiException(ErrorCode.Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP);
//        }
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
