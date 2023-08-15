package com.agileboot.admin.customize.service.permission.model.checker;

import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.domain.system.dept.db.SysDeptService;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AllDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;


    @Override
    public boolean check(SystemLoginUser loginUser, DataCondition condition) {
        return true;
    }
}
