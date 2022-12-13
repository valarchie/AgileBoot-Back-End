package com.agileboot.infrastructure.web.domain.permission;

import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.system.service.ISysDeptService;
import lombok.Data;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
public abstract class AbstractDataPermissionChecker {

    private ISysDeptService deptService;

    /**
     * 检测当前用户对于 给定条件的数据 是否有权限
     * @param loginUser
     * @param condition
     * @return
     */
    public abstract boolean check(LoginUser loginUser, DataCondition condition);

}
