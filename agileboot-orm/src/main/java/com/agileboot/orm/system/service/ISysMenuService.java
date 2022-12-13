package com.agileboot.orm.system.service;

import com.agileboot.orm.system.entity.SysMenuEntity;
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
public interface ISysMenuService extends IService<SysMenuEntity> {

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
     * @param menuName
     * @param menuId
     * @param parentId
     * @return
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
