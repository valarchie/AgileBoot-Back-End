package com.agileboot.infrastructure.web.domain.permission;

import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.permission.checker.AllDataPermissionChecker;
import com.agileboot.infrastructure.web.domain.permission.checker.CustomDataPermissionChecker;
import com.agileboot.infrastructure.web.domain.permission.checker.DefaultDataPermissionChecker;
import com.agileboot.infrastructure.web.domain.permission.checker.DeptTreeDataPermissionChecker;
import com.agileboot.infrastructure.web.domain.permission.checker.OnlySelfDataPermissionChecker;
import com.agileboot.infrastructure.web.domain.permission.checker.SingleDeptDataPermissionChecker;
import com.agileboot.orm.common.enums.DataScopeEnum;
import com.agileboot.orm.system.service.ISysDeptService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 数据权限检测器工厂
 * @author valarchie
 */
@Component
public class DataPermissionCheckerFactory {
    private static AbstractDataPermissionChecker allChecker;
    private static AbstractDataPermissionChecker customChecker;
    private static AbstractDataPermissionChecker singleDeptChecker;
    private static AbstractDataPermissionChecker deptTreeChecker;
    private static AbstractDataPermissionChecker onlySelfChecker;
    private static AbstractDataPermissionChecker defaultSelfChecker;


    @PostConstruct
    public void initAllChecker() {
        ISysDeptService deptService = SpringUtil.getBean(ISysDeptService.class);

        allChecker = new AllDataPermissionChecker();
        customChecker = new CustomDataPermissionChecker(deptService);
        singleDeptChecker = new SingleDeptDataPermissionChecker(deptService);
        deptTreeChecker = new DeptTreeDataPermissionChecker(deptService);
        onlySelfChecker = new OnlySelfDataPermissionChecker(deptService);
        defaultSelfChecker = new DefaultDataPermissionChecker();
    }


    public static AbstractDataPermissionChecker getChecker(LoginUser loginUser) {
        if (loginUser == null) {
            return deptTreeChecker;
        }

        DataScopeEnum dataScope = loginUser.getRoleInfo().getDataScope();
        switch (dataScope) {
            case ALL:
                return allChecker;
            case CUSTOM_DEFINE:
                return customChecker;
            case SINGLE_DEPT:
                return singleDeptChecker;
            case DEPT_TREE:
                return deptTreeChecker;
            case ONLY_SELF:
                return onlySelfChecker;
            default:
                return defaultSelfChecker;
        }
    }

}
