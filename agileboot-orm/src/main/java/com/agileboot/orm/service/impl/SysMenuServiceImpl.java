package com.agileboot.orm.service.impl;

import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.entity.SysRoleMenuEntity;
import com.agileboot.orm.enums.MenuTypeEnum;
import com.agileboot.orm.mapper.SysMenuMapper;
import com.agileboot.orm.mapper.SysRoleMapper;
import com.agileboot.orm.mapper.SysRoleMenuMapper;
import com.agileboot.orm.service.ISysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;


    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return this.baseMapper.selectMenuListByRoleId(roleId);
    }

    @Override
    public boolean checkMenuNameUnique(String menuName, Long menuId, Long parentId) {
        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_name", menuName)
            .ne(menuId!=null, "menu_id", menuId)
            .eq(parentId != null, "parent_id", parentId);
        return this.baseMapper.exists(queryWrapper);
    }



    @Override
    public boolean hasChildByMenuId(Long menuId) {
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
    public boolean checkMenuExistRole(Long menuId) {
        QueryWrapper<SysRoleMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuId);
        return roleMenuMapper.exists(queryWrapper);
    }

    @Override
    public List<SysMenuEntity> listMenuListWithoutButtonByUserId(Long userId) {
        List<SysMenuEntity> sysMenuList = this.baseMapper.selectMenuListByUserId(userId);

        if (sysMenuList == null) {
            return null;
        }

        return sysMenuList.stream()
            .filter(menu -> !Objects.equals(menu.getMenuType(), MenuTypeEnum.BUTTON.getValue()))
            .collect(Collectors.toList());
    }

    @Override
    public List<SysMenuEntity> listMenuListWithoutButton() {
        List<SysMenuEntity> sysMenuList = this.list();

        if (sysMenuList == null) {
            return null;
        }

        return sysMenuList.stream()
            .filter(menu -> !MenuTypeEnum.BUTTON.getValue().equals(menu.getMenuType()))
            .collect(Collectors.toList());
    }

    @Override
    public List<SysMenuEntity> selectMenuListByUserId(Long userId) {
        return baseMapper.selectMenuListByUserId(userId);
    }





}
