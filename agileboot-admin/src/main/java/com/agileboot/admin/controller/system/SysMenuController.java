package com.agileboot.admin.controller.system;

import cn.hutool.core.lang.tree.Tree;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.domain.system.TreeSelectedDTO;
import com.agileboot.domain.system.menu.AddMenuCommand;
import com.agileboot.domain.system.menu.MenuDTO;
import com.agileboot.domain.system.menu.MenuDomainService;
import com.agileboot.domain.system.menu.MenuQuery;
import com.agileboot.domain.system.menu.UpdateMenuCommand;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/menu")
@Validated
public class SysMenuController extends BaseController {

    @Autowired
    MenuDomainService menuDomainService;

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@ss.hasPerm('system:menu:list')")
    @GetMapping("/list")
    public ResponseDTO<List> list(MenuQuery query) {
        List<MenuDTO> menuList = menuDomainService.getMenuList(query);
        return ResponseDTO.ok(menuList);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public ResponseDTO<MenuDTO> getInfo(@PathVariable @NotNull @PositiveOrZero Long menuId) {
        MenuDTO menu = menuDomainService.getMenuInfo(menuId);
        return ResponseDTO.ok(menu);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/dropdownList")
    public ResponseDTO dropdownList() {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        List<Tree<Long>> dropdownList = menuDomainService.getDropdownList(loginUser);
        return ResponseDTO.ok(dropdownList);
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    public ResponseDTO roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        TreeSelectedDTO roleDropdownList = menuDomainService.getRoleDropdownList(loginUser, roleId);
        return ResponseDTO.ok(roleDropdownList);
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@ss.hasPerm('system:menu:add')")
    @AccessLog(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseDTO add(@RequestBody AddMenuCommand addCommand) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        menuDomainService.addMenu(addCommand, loginUser);
        return ResponseDTO.ok();
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@ss.hasPerm('system:menu:edit')")
    @AccessLog(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseDTO edit(@RequestBody UpdateMenuCommand updateCommand) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        menuDomainService.updateMenu(updateCommand, loginUser);
        return ResponseDTO.ok();
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPerm('system:menu:remove')")
    @AccessLog(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public ResponseDTO remove(@PathVariable("menuId") Long menuId) {
        menuDomainService.remove(menuId);
        return ResponseDTO.ok();
    }


}
