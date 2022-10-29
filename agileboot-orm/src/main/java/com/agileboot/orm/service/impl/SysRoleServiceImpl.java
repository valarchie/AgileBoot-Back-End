package com.agileboot.orm.service.impl;

import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.mapper.SysRoleMapper;
import com.agileboot.orm.mapper.SysUserMapper;
import com.agileboot.orm.service.ISysRoleMenuService;
import com.agileboot.orm.service.ISysRoleService;
import com.agileboot.orm.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public boolean isRoleNameUnique(Long roleId, String roleName) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_name", roleName);
        return !this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRoleKeyUnique(Long roleId, String roleKey) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_key", roleKey);
        return !this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isAssignedToUsers(Long roleId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return userMapper.exists(queryWrapper);
    }


}
