package com.agileboot.domain.system.menu.db;

import com.agileboot.domain.system.role.db.SysRoleMenuEntity;
import com.agileboot.domain.system.role.db.SysRoleMenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    private final SysRoleMenuMapper roleMenuMapper;


    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return this.baseMapper.selectMenuIdsByRoleId(roleId);
    }

    @Override
    public boolean isMenuNameDuplicated(String menuName, Long menuId, Long parentId) {
        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_name", menuName)
            .ne(menuId != null, "menu_id", menuId)
            .eq(parentId != null, "parent_id", parentId);
        return this.baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean hasChildrenMenu(Long menuId) {
        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", menuId);
        return baseMapper.exists(queryWrapper);
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean isMenuAssignToRoles(Long menuId) {
        QueryWrapper<SysRoleMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuId);
        return roleMenuMapper.exists(queryWrapper);
    }



    @Override
    public List<SysMenuEntity> getMenuListByUserId(Long userId) {
        return baseMapper.selectMenuListByUserId(userId);
    }


}
