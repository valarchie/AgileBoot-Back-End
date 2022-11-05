package com.agileboot.domain.system.menu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.agileboot.domain.common.dto.TreeSelectedDTO;
import com.agileboot.domain.system.menu.command.AddMenuCommand;
import com.agileboot.domain.system.menu.command.UpdateMenuCommand;
import com.agileboot.domain.system.menu.dto.MenuDTO;
import com.agileboot.domain.system.menu.dto.RouterDTO;
import com.agileboot.domain.system.menu.model.MenuModel;
import com.agileboot.domain.system.menu.model.MenuModelFactory;
import com.agileboot.domain.system.menu.model.RouterModel;
import com.agileboot.domain.system.menu.query.MenuQuery;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.enums.MenuTypeEnum;
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
public class MenuApplicationService {

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
            loginUser.isAdmin() ? menuService.list() : menuService.getMenuListByUserId(loginUser.getUserId());

        return buildMenuTreeSelect(menuEntityList);
    }


    public TreeSelectedDTO getRoleDropdownList(LoginUser loginUser, Long roleId) {
        List<SysMenuEntity> menus = loginUser.isAdmin() ?
            menuService.list() : menuService.getMenuListByUserId(loginUser.getUserId());

        TreeSelectedDTO tree = new TreeSelectedDTO();
        tree.setMenus(buildMenuTreeSelect(menus));
        tree.setCheckedKeys(menuService.getMenuIdsByRoleId(roleId));

        return tree;
    }


    public void addMenu(AddMenuCommand addCommand) {
        MenuModel model = MenuModelFactory.loadFromAddCommand(addCommand, new MenuModel());

        model.checkMenuNameUnique(menuService);
        model.checkExternalLink();

        model.insert();
    }

    public void updateMenu(UpdateMenuCommand updateCommand) {
        MenuModel model = MenuModelFactory.loadFromDb(updateCommand.getMenuId(), menuService);
        model.loadUpdateCommand(updateCommand);

        model.checkMenuNameUnique(menuService);
        model.checkExternalLink();
        model.checkParentIdConflict();

        model.updateById();
    }


    public void remove(Long menuId) {
        MenuModel menuModel = MenuModelFactory.loadFromDb(menuId, menuService);

        menuModel.checkHasChildMenus(menuService);
        menuModel.checkMenuAlreadyAssignToRole(menuService);

        menuModel.deleteById();
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
        List<SysMenuEntity> allMenus;
        if (LoginUser.isAdmin(userId)) {
            allMenus = menuService.list();
        } else {
            allMenus = menuService.getMenuListByUserId(userId);
        }

        List<SysMenuEntity> noButtonMenus = allMenus.stream()
            .filter(menu -> !MenuTypeEnum.BUTTON.getValue().equals(menu.getMenuType()))
            .collect(Collectors.toList());

        TreeNodeConfig config = new TreeNodeConfig();
        //默认为id可以不设置
        config.setIdKey("menuId");

        return TreeUtil.build(noButtonMenus, 0L, config, (menu, tree) -> {
            // 也可以使用 tree.setId(dept.getId());等一些默认值
            tree.setId(menu.getMenuId());
            tree.setParentId(menu.getParentId());
            tree.putExtra("entity", menu);
        });

    }


    public List<RouterDTO> buildRouterTree(List<Tree<Long>> trees) {
        List<RouterDTO> routers = new LinkedList<>();
        if (CollUtil.isNotEmpty(trees)) {
            for (Tree<Long> tree : trees) {
                RouterDTO routerDTO;

                Object entity = tree.get("entity");

                if (entity != null) {
                    RouterModel model = new RouterModel();
                    BeanUtil.copyProperties(entity, model);

                    routerDTO = model.produceDefaultRouterVO();

                    if(model.isMultipleLevelMenu(tree)) {
                        routerDTO = model.produceDirectoryRouterVO(buildRouterTree(tree.getChildren()));
                    }

                    if(model.isSingleLevelMenu()) {
                        routerDTO = model.produceMenuFrameRouterVO();
                    }

                    if(model.getParentId() == 0L && model.isInnerLink()) {
                        routerDTO = model.produceInnerLinkRouterVO();
                    }

                    routers.add(routerDTO);
                }

            }
        }

        return routers;
    }


    public List<RouterDTO> getRouterTree(Long userId) {
        List<Tree<Long>> trees = buildMenuEntityTree(userId);
        return buildRouterTree(trees);
    }

}
