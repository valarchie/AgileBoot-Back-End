package com.agileboot.orm.service.impl;

import com.agileboot.orm.entity.SysRoleMenuEntity;
import com.agileboot.orm.mapper.SysRoleMenuMapper;
import com.agileboot.orm.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements
    ISysRoleMenuService {

}
