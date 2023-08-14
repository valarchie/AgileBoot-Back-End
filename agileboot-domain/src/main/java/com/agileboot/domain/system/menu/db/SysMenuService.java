package com.agileboot.domain.system.menu.db;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuEntity> getMenuListByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param menuId   菜单id
     * @param parentId 父级菜单id
     * @return 校验结果
     */
    boolean isMenuNameDuplicated(String menuName, Long menuId, Long parentId);

    /**
     * 是否存在菜单子节点
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildrenMenu(Long menuId);

    /**
     * 查询菜单是否存在角色
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean isMenuAssignToRoles(Long menuId);



}
