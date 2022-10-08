package com.agileboot.orm.service.impl;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysPostEntity;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.mapper.SysUserMapper;
import com.agileboot.orm.query.AbstractPageQuery;
import com.agileboot.orm.result.SearchUserDO;
import com.agileboot.orm.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {


    @Override
    public boolean checkDeptExistUser(Long deptId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean checkExistUserLinkToRole(Long roleId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean checkUserNameUnique(String username) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.baseMapper.exists(queryWrapper);
    }


    @Override
    public SysRoleEntity getRoleOfUser(Long userId) {
        List<SysRoleEntity> list = baseMapper.selectRolesByUserId(userId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public SysPostEntity getPostOfUser(Long userId) {
        List<SysPostEntity> list = baseMapper.selectPostsByUserId(userId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean checkPhoneUnique(String phone, Long userId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(userId != null, "user_id", userId)
            .eq("phone_number", phone);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean checkEmailUnique(String email, Long userId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(userId != null, "user_id", userId)
            .eq("email", email);
        return baseMapper.exists(queryWrapper);
    }


    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        Set<String> permissionStrList = baseMapper.selectMenuPermsByUserId(userId);
        Set<String> singlePermsSet = new HashSet<>();
        for (String perm : permissionStrList) {
            if (StrUtil.isNotEmpty(perm)) {
                singlePermsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return singlePermsSet;
    }

    @Override
    public SysUserEntity getUserByUserName(String userName) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return this.getOne(queryWrapper);
    }

    @Override
    public Page<SysUserEntity> selectAllocatedList(AbstractPageQuery query) {
        Page<SysUserEntity> page = query.toPage();
        List<SysUserEntity> list = baseMapper.selectRoleAssignedUserList(page, query.toQueryWrapper());
        page.setRecords(list);
        return page;
    }

    @Override
    public Page<SysUserEntity> selectUnallocatedList(AbstractPageQuery query) {
        Page<SysUserEntity> page = query.toPage();
        List<SysUserEntity> list = baseMapper.selectRoleUnassignedUserList(page, query.toQueryWrapper());
        page.setRecords(list);
        return page;
    }

    @Override
    public Page<SearchUserDO> selectUserList(AbstractPageQuery query) {
        Page page = query.toPage();
        List<SearchUserDO> searchUserDOS = baseMapper.selectUserList(page, query.toQueryWrapper());
        page.setRecords(searchUserDOS);
        return page;
    }


}
