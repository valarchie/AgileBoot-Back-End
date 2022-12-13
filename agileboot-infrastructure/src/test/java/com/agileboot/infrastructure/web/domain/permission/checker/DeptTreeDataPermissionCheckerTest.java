package com.agileboot.infrastructure.web.domain.permission.checker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.permission.DataCondition;
import com.agileboot.orm.system.service.ISysDeptService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeptTreeDataPermissionCheckerTest {

    private final ISysDeptService deptService = mock(ISysDeptService.class);

    @Test
    void testCheckWhenParameterNull() {
        DeptTreeDataPermissionChecker checker = new DeptTreeDataPermissionChecker(deptService);

        boolean check1 = checker.check(null, null);
        boolean check2 = checker.check(new LoginUser(), null);
        boolean check3 = checker.check(null, new DataCondition());
        boolean check4 = checker.check(new LoginUser(), new DataCondition());

        assertFalse(check1);
        assertFalse(check2);
        assertFalse(check3);
        assertFalse(check4);
    }


    @Test
    void testCheckWhenIsChildOfDept() {
        DeptTreeDataPermissionChecker checker = new DeptTreeDataPermissionChecker(deptService);

        Mockito.when(deptService.isChildOfTheDept(any(), any())).thenReturn(true);
        LoginUser loginUser = new LoginUser();
        loginUser.setDeptId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetDeptId(2L);

        boolean check = checker.check(loginUser, dataCondition);

        assertTrue(check);
    }


    @Test
    void testCheckWhenIsSameDept() {
        DeptTreeDataPermissionChecker checker = new DeptTreeDataPermissionChecker(deptService);

        Mockito.when(deptService.isChildOfTheDept(any(), any())).thenReturn(false);
        LoginUser loginUser = new LoginUser();
        loginUser.setDeptId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetDeptId(1L);

        boolean check = checker.check(loginUser, dataCondition);

        assertTrue(check);
    }


    @Test
    void testCheckWhenFailed() {
        DeptTreeDataPermissionChecker checker = new DeptTreeDataPermissionChecker(deptService);

        Mockito.when(deptService.isChildOfTheDept(any(), any())).thenReturn(false);
        LoginUser loginUser = new LoginUser();
        loginUser.setDeptId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetDeptId(2L);

        boolean check = checker.check(loginUser, dataCondition);

        assertFalse(check);
    }




}
