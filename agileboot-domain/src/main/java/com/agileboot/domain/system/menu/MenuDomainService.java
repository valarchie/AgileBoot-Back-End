package com.agileboot.domain.system.menu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.TreeSelectedDTO;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.service.ISysMenuService;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class MenuDomainService {

    @Autowired
    private ISysMenuService menuService;


    public List<MenuDTO> getMenuList(MenuQuery query) {
        List<SysMenuEntity> list = menuService.list(query.toQueryWrapper());
        return list.stream().map(MenuDTO::new).collect(Collectors.toList());
    }

    public MenuDTO getMenuInfo(Long menuId) {
        SysMenuEntity byId = menuService.getById(menuId);
        return new MenuDTO(byId);
    }

    public List<Tree<Long>> getDropdownList(LoginUser loginUser) {
        List<SysMenuEntity> menuEntityList =
            loginUser.isAdmin() ? menuService.list() : menuService.selectMenuListByUserId(loginUser.getUserId());

        return buildMenuTreeSelect(menuEntityList);
    }


    public TreeSelectedDTO getRoleDropdownList(LoginUser loginUser, Long roleId) {
        List<SysMenuEntity> menus = loginUser.isAdmin() ?
            menuService.list() : menuService.selectMenuListByUserId(loginUser.getUserId());

        TreeSelectedDTO tree = new TreeSelectedDTO();
        tree.setMenus(buildMenuTreeSelect(menus));
        tree.setCheckedKeys(menuService.selectMenuListByRoleId(roleId));

        return tree;
    }


    public void addMenu(AddMenuCommand addCommand, LoginUser loginUser) {
        MenuModel model = addCommand.toModel();

        model.checkMenuNameUnique(menuService);
        model.checkExternalLink();

        model.logCreator(loginUser);

        model.insert();
    }

    public void updateMenu(UpdateMenuCommand updateCommand, LoginUser loginUser) {
        MenuModel model = updateCommand.toModel();
        model.checkMenuNameUnique(menuService);
        model.checkExternalLink();
        model.checkParentId();

        model.logUpdater(loginUser);

        model.updateById();
    }


    public void remove(Long menuId) {
        MenuModel menuModel = getMenuModel(menuId);

        menuModel.checkHasChildMenus(menuService);
        menuModel.checkMenuAlreadyAssignToRole(menuService);

        menuModel.deleteById();
    }


    public MenuModel getMenuModel(Long menuId) {
        SysMenuEntity byId = menuService.getById(menuId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, menuId, "菜单");
        }
        return new MenuModel(byId);
    }



    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<Tree<Long>> buildMenuTreeSelect(List<SysMenuEntity> menus) {
        TreeNodeConfig config = new TreeNodeConfig();
        //默认为id可以不设置
        config.setIdKey("menuId");
        return TreeUtil.build(menus, 0L, config, (menu, tree) -> {
            // 也可以使用 tree.setId(dept.getId());等一些默认值
            tree.setId(menu.getMenuId());
            tree.setParentId(menu.getParentId());
            tree.putExtra("label", menu.getMenuName());
        });
    }


    public List<Tree<Long>> buildMenuEntityTree(Long userId) {

        List<SysMenuEntity> menus = null;
        if (AuthenticationUtils.isAdmin(userId)) {
            menus = menuService.listMenuListWithoutButton();
        } else {
            menus = menuService.listMenuListWithoutButtonByUserId(userId);
        }

        TreeNodeConfig config = new TreeNodeConfig();
        //默认为id可以不设置
        config.setIdKey("menuId");

        return TreeUtil.build(menus, 0L, config, (menu, tree) -> {
            // 也可以使用 tree.setId(dept.getId());等一些默认值
            tree.setId(menu.getMenuId());
            tree.setParentId(menu.getParentId());
            tree.putExtra("entity", menu);
        });

    }


    public List<RouterVo> buildRouterTree(List<Tree<Long>> trees) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        if (CollUtil.isNotEmpty(trees)) {
            for (Tree<Long> tree : trees) {
                RouterVo routerVo = null;

                Object entity = tree.get("entity");

                if (entity != null) {
                    RouterModel model = new RouterModel();
                    BeanUtil.copyProperties(entity, model, true);

                    routerVo = model.produceDefaultRouterVO();

                    if(model.isMultipleLevelMenu(tree)) {
                        routerVo = model.produceDirectoryRouterVO(buildRouterTree(tree.getChildren()));
                    }

                    if(model.isSingleLevelMenu()) {
                        routerVo = model.produceMenuFrameRouterVO();
                    }

                    if(model.getParentId() == 0L && model.isInnerLink()) {
                        routerVo = model.produceInnerLinkRouterVO();
                    }

                    routers.add(routerVo);
                }

            }
        }

        return routers;
    }


    public List<RouterVo> getRouterTree(Long userId) {
        List<Tree<Long>> trees = buildMenuEntityTree(userId);
        return buildRouterTree(trees);
    }




}
