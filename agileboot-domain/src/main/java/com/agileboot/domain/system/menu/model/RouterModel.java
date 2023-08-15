package com.agileboot.domain.system.menu.model;

import com.agileboot.domain.system.menu.db.SysMenuEntity;

/**
 * @author valarchie
 */
public class RouterModel extends SysMenuEntity {

//    public RouterDTO produceMultipleLevelMenuRouterVO(List<RouterDTO> children) {
//        RouterDTO router = produceDefaultRouterVO();
//
//        if (CollUtil.isNotEmpty(children) && Objects.equals(MenuTypeEnum.DIRECTORY.getValue(), getMenuType())) {
//            router.setAlwaysShow(true);
//            router.setRedirect("noRedirect");
//            router.setChildren(children);
//        }
//
//        return router;
//    }
//
//
//    public RouterDTO produceSingleLevelMenuRouterVO() {
//        RouterDTO router = produceDefaultRouterVO();
//
//        router.setMeta(null);
//        List<RouterDTO> childrenList = new ArrayList<>();
//        RouterDTO children = new RouterDTO();
//        children.setPath(getPath());
//        children.setComponent(getComponent());
//        children.setName(StrUtil.upperFirst(getPath()));
//        children.setMeta(new MetaDTO(getMenuName(), getIcon(), !getIsCache(), getPath()));
//        children.setQuery(getQuery());
//        childrenList.add(children);
//        router.setChildren(childrenList);
//
//        return router;
//    }
//
//
//    public RouterDTO produceInnerLinkRouterVO() {
//
//        RouterDTO router = produceDefaultRouterVO();
//
//        router.setMeta(new MetaDTO(getMenuName(), getIcon()));
//        router.setPath("/");
//        List<RouterDTO> childrenList = new ArrayList<>();
//        RouterDTO children = new RouterDTO();
//        String routerPath = trimHttpPrefixForPath(getPath());
//        children.setPath(routerPath);
//        children.setComponent(MenuComponentEnum.INNER_LINK.description());
//        children.setName(StrUtil.upperFirst(routerPath));
//        children.setMeta(new MetaDTO(getMenuName(), getIcon(), getPath()));
//        childrenList.add(children);
//        router.setChildren(childrenList);
//
//        return router;
//    }
//
//    public RouterDTO produceDefaultRouterVO() {
//        RouterDTO router = new RouterDTO();
//        router.setHidden(!getIsVisible());
//        router.setName(calculateRouteName());
//        router.setPath(calculateRouterPath());
//        router.setComponent(calculateComponentType());
//        router.setQuery(getQuery());
//        router.setMeta(new MetaDTO(getMenuName(), getIcon(), !getIsCache(), getPath()));
//        return router;
//    }
//
//
//    /**
//     * 获取路由名称
//     * @return 路由名称
//     */
//    public String calculateRouteName() {
//        String routerName = StrUtil.upperFirst(getPath());
//        // 非外链并且是一级目录（类型为目录）
//        if (isSingleLevelMenu()) {
//            routerName = StrUtil.EMPTY;
//        }
//        return routerName;
//    }
//
//
//    /**
//     * 是否为单个一级菜单
//     *
//     * @return 结果
//     */
//    public boolean isSingleLevelMenu() {
//        return isTopLevel() && MenuTypeEnum.MENU.getValue().equals(getMenuType()) && !getIsExternal();
//    }
//
//    /**
//     * 是否为顶级内部链接菜单
//     *
//     * @return 结果
//     */
//    public boolean isTopInnerLink() {
//        return isTopLevel() && isInnerLink();
//    }
//
//
//    /**
//     * 是否为多级菜单
//     *
//     * @return 结果
//     */
//    public boolean isMultipleLevelMenu(Tree<Long> tree) {
//        return MenuTypeEnum.DIRECTORY.getValue().equals(getMenuType()) && tree.hasChild();
//    }
//
//
//    /**
//     * 获取路由地址
//     * @return 路由地址
//     */
//    public String calculateRouterPath() {
//        String routerPath = getPath();
//        // 内链打开外网方式
//        if (!isTopLevel() && isInnerLink()) {
//            routerPath = trimHttpPrefixForPath(routerPath);
//        }
//        // 非外链并且是一级目录（类型为目录）
//        if (isTopLevel() && Objects.equals(MenuTypeEnum.DIRECTORY.getValue(), getMenuType()) && !getIsExternal()) {
//            routerPath = "/" + getPath();
//        // 非外链并且是一级目录（类型为菜单）
//        } else if (isSingleLevelMenu()) {
//            routerPath = "/";
//        }
//        return routerPath;
//    }
//
//    /**
//     * 是否为内链组件
//     *
//     * @return 结果
//     */
//    public boolean isInnerLink() {
//        return !getIsExternal() && (HttpUtil.isHttp(getPath()) || HttpUtil.isHttps(getPath()));
//    }
//
//    /**
//     * 是否顶层目录或者菜单
//     *
//     * @return 结果
//     */
//    public boolean isTopLevel() {
//        return Objects.equals(getParentId(), 0L);
//    }
//
//
//    /**
//     * 内链域名特殊字符替换
//     */
//    public String trimHttpPrefixForPath(String path) {
//        if (HttpUtil.isHttp(path)) {
//            return StrUtil.stripIgnoreCase(path, Constants.HTTP, "");
//        }
//        if (HttpUtil.isHttps(path)) {
//            return StrUtil.stripIgnoreCase(path, Constants.HTTPS, "");
//        }
//        return path;
//    }
//
//    /**
//     * 获取组件信息
//     *
//     * @return 组件信息
//     */
//    public String calculateComponentType() {
//        String component = MenuComponentEnum.LAYOUT.description();
//        if (StrUtil.isNotEmpty(getComponent()) && !isSingleLevelMenu()) {
//            component = getComponent();
//        } else if (isInnerLinkView()) {
//            component = MenuComponentEnum.INNER_LINK.description();
//        } else if (isParentView()) {
//            component = MenuComponentEnum.PARENT_VIEW.description();
//        }
//        return component;
//    }
//
//    /**
//     * 是否为inner_link_view组件
//     *
//     * @return 结果
//     */
//    public boolean isInnerLinkView() {
//        return StrUtil.isEmpty(getComponent()) && !isTopLevel() && isInnerLink();
//    }
//
//
//    /**
//     * 是否为parent_view组件
//     *
//     * @return 结果
//     */
//    public boolean isParentView() {
//        return StrUtil.isEmpty(getComponent()) && !isTopLevel() &&
//            MenuTypeEnum.DIRECTORY.getValue().equals(getMenuType());
//    }


}
