package com.agileboot.admin.customize.service.permission.model.checker;

import com.agileboot.infrastructure.web.domain.login.WebLoginUser;
import com.agileboot.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.orm.system.service.ISysDeptService;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AllDataPermissionChecker extends AbstractDataPermissionChecker {

    private ISysDeptService deptService;


    @Override
    public boolean check(WebLoginUser loginUser, DataCondition condition) {
        return true;
    }
}
