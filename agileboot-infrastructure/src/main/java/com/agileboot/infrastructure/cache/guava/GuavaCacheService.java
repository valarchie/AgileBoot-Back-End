package com.agileboot.infrastructure.cache.guava;


import com.agileboot.infrastructure.web.domain.login.Role;
import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.service.ISysConfigService;
import com.agileboot.orm.service.ISysDeptService;
import com.agileboot.orm.service.ISysRoleService;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@Slf4j
public class GuavaCacheService {

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysRoleService roleService;

    public GuavaCacheTemplate<String> configCache = new GuavaCacheTemplate<String>() {
        @Override
        public String getObjectFromDb(Object id) {
            return configService.getConfigValueByKey(id.toString());
        }
    };

    public GuavaCacheTemplate<SysDeptEntity> deptCache = new GuavaCacheTemplate<SysDeptEntity>() {
        @Override
        public SysDeptEntity getObjectFromDb(Object id) {
            return deptService.getById(id.toString());
        }
    };


    public GuavaCacheTemplate<Role> roleCache = new GuavaCacheTemplate<Role>() {
        @Override
        public Role getObjectFromDb(Object id) {
            SysRoleEntity byId = roleService.getById((Serializable) id);
            if (byId != null) {
                return new Role(byId);
            }
            return null;
        }
    };






}
