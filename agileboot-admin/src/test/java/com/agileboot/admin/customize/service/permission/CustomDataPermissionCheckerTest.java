package com.agileboot.admin.customize.service.permission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.agileboot.admin.customize.service.permission.model.checker.CustomDataPermissionChecker;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.infrastructure.user.web.RoleInfo;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.domain.system.dept.db.SysDeptService;
import org.apache.commons.collections4.SetUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomDataPermissionCheckerTest {

    private final SysDeptService deptService = mock(SysDeptService.class);
    public SystemLoginUser loginUser = mock(SystemLoginUser.class);

    @BeforeEach
    public void mockBefore() {
        when(loginUser.getRoleInfo()).thenReturn(RoleInfo.EMPTY_ROLE);
    }

    @Test
    void testCheckWhenParameterNull() {
            CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

            boolean check1 = customChecker.check(null, null);
            boolean check2 = customChecker.check(loginUser, null);
            boolean check3 = customChecker.check(null, new DataCondition());

            assertFalse(check1);
            assertFalse(check2);
            assertFalse(check3);
    }

    @Test
    void testCheckWhenTargetDeptIdNull() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        boolean check = customChecker.check(loginUser, new DataCondition(null, 1L));

        assertFalse(check);
    }


    @Test
    void testCheckWhenRoleIsNull() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        when(loginUser.getRoleInfo()).thenReturn(null);
        boolean check = customChecker.check(loginUser, new DataCondition(1L, 1L));

        assertFalse(check);
    }


    @Test
    void testCheckWhenNotContainTargetDeptId() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        loginUser.getRoleInfo().setDeptIdSet(SetUtils.hashSet(2L));
        boolean check = customChecker.check(loginUser, new DataCondition(1L, 1L));

        assertFalse(check);
    }


    @Test
    void testCheckWhenContainTargetDeptId() {
        CustomDataPermissionChecker customChecker = new CustomDataPermissionChecker(deptService);

        loginUser.getRoleInfo().setDeptIdSet(SetUtils.hashSet(1L));
        boolean check = customChecker.check(loginUser, new DataCondition(1L, 1L));

        assertTrue(check);
    }


}
