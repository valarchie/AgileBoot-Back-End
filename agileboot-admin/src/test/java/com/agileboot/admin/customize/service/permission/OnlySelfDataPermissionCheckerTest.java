package com.agileboot.admin.customize.service.permission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.agileboot.admin.customize.service.permission.model.checker.OnlySelfDataPermissionChecker;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.domain.system.dept.db.SysDeptService;
import org.junit.jupiter.api.Test;

class OnlySelfDataPermissionCheckerTest {

    private final SysDeptService deptService = mock(SysDeptService.class);

    @Test
    void testCheckWhenParameterNull() {
        OnlySelfDataPermissionChecker checker = new OnlySelfDataPermissionChecker(deptService);

        boolean check1 = checker.check(null, null);
        boolean check2 = checker.check(new SystemLoginUser(), null);
        boolean check3 = checker.check(null, new DataCondition());
        boolean check4 = checker.check(new SystemLoginUser(), new DataCondition());

        assertFalse(check1);
        assertFalse(check2);
        assertFalse(check3);
        assertFalse(check4);
    }

    @Test
    void testCheckWhenSameUserId() {
        OnlySelfDataPermissionChecker checker = new OnlySelfDataPermissionChecker(deptService);
        SystemLoginUser loginUser = new SystemLoginUser();
        loginUser.setUserId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetUserId(1L);

        boolean check = checker.check(loginUser, dataCondition);

        assertTrue(check);
    }


    @Test
    void testCheckWhenDifferentUserId() {
        OnlySelfDataPermissionChecker checker = new OnlySelfDataPermissionChecker(deptService);
        SystemLoginUser loginUser = new SystemLoginUser();
        loginUser.setUserId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetDeptId(2L);

        boolean check = checker.check(loginUser, dataCondition);

        assertFalse(check);
    }

}
