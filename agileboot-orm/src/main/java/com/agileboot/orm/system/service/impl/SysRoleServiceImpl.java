package com.agileboot.orm.system.service.impl;

import com.agileboot.orm.system.entity.SysMenuEntity;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.mapper.SysRoleMapper;
import com.agileboot.orm.system.mapper.SysUserMapper;
import com.agileboot.orm.system.service.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

    @NonNull
    private SysUserMapper userMapper;

    @Override
    public boolean isRoleNameDuplicated(Long roleId, String roleName) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_name", roleName);
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRoleKeyDuplicated(Long roleId, String roleKey) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_key", roleKey);
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isAssignedToUsers(Long roleId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return userMapper.exists(queryWrapper);
    }

    @Override
    public List<SysMenuEntity> getMenuListByRoleId(Long roleId) {
        return baseMapper.getMenuListByRoleId(roleId);
    }


}
