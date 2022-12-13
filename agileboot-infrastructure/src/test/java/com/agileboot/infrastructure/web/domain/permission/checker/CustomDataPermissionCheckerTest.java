package com.agileboot.infrastructure.web.domain.permission.checker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.login.RoleInfo;
import com.agileboot.infrastructure.web.domain.permission.DataCondition;
import com.agileboot.orm.system.service.ISysDeptService;
import org.apache.commons.collections4.SetUtils;
import org.junit.jupiter.api.Test;

class CustomDataPermissionCheckerTest {

    private final ISysDeptService deptService = mock(ISysDeptService.class);

    @Test
    void testCheckWhenParameterNull() {
            CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

            boolean check1 = customChecker.check(null, null);
            boolean check2 = customChecker.check(new LoginUser(), null);
            boolean check3 = customChecker.check(null, new DataCondition());

            assertFalse(check1);
            assertFalse(check2);
            assertFalse(check3);
    }

    @Test
    void testCheckWhenTargetDeptIdNull() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        boolean check = customChecker.check(new LoginUser(), new DataCondition(null, 1L));

        assertFalse(check);
    }


    @Test
    void testCheckWhenRoleIsNull() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        boolean check = customChecker.check(new LoginUser(), new DataCondition(1L, 1L));

        assertFalse(check);
    }


    @Test
    void testCheckWhenNotContainTargetDeptId() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        LoginUser loginUser = new LoginUser();
        loginUser.setRoleInfo(new RoleInfo());
        loginUser.getRoleInfo().setDeptIdSet(SetUtils.hashSet(2L));
        boolean check = customChecker.check(new LoginUser(), new DataCondition(1L, 1L));

        assertFalse(check);
    }


    @Test
    void testCheckWhenContainTargetDeptId() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);
        LoginUser loginUser = new LoginUser();
        loginUser.setRoleInfo(new RoleInfo());
        loginUser.getRoleInfo().setDeptIdSet(SetUtils.hashSet(1L));

        boolean check = customChecker.check(new LoginUser(), new DataCondition(1L, 1L));

        assertFalse(check);
    }


}
