package com.agileboot.orm.system.service.impl;

import com.agileboot.orm.system.entity.SysRoleMenuEntity;
import com.agileboot.orm.system.mapper.SysRoleMenuMapper;
import com.agileboot.orm.system.service.ISysRoleMenuService;
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
