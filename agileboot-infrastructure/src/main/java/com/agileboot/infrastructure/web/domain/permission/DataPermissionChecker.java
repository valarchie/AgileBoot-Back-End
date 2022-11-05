package com.agileboot.infrastructure.web.domain.permission;

import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.service.ISysDeptService;
import lombok.Data;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
public abstract class DataPermissionChecker {

    private ISysDeptService deptService;

    public abstract boolean check(LoginUser loginUser, DataCondition condition);

}
