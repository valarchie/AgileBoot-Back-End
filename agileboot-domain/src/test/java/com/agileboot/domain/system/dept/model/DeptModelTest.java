package com.agileboot.domain.system.dept.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.service.ISysDeptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

class DeptModelTest {

    private static final Long DEPT_ID = 1L;
    private static final Long PARENT_ID = 2L;

    private final ISysDeptService deptService = mock(ISysDeptService.class);

    @Test
    void testCheckDeptNameUnique() {
        DeptModel deptModel = new DeptModel();
        deptModel.setDeptName("dept 1");
        when(
            deptService.isDeptNameDuplicated(ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.any())).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, () -> deptModel.checkDeptNameUnique(deptService));
        Assertions.assertEquals(Business.DEPT_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
    }


    @Test
    void testCheckParentIdConflict() {
        DeptModel deptModel = new DeptModel();
        Long sameId = 1L;
        deptModel.setDeptId(sameId);
        deptModel.setParentId(sameId);

        ApiException exception = assertThrows(ApiException.class, deptModel::checkParentIdConflict);

        Assertions.assertEquals(Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF, exception.getErrorCode());
    }


    @Test
    void testCheckHasChildDept() {
        DeptModel deptModel = new DeptModel();
        deptModel.setDeptId(DEPT_ID);
        when(deptService.hasChildrenDept(eq(DEPT_ID), eq(null))).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, () -> deptModel.checkHasChildDept(deptService));

        Assertions.assertEquals(Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE, exception.getErrorCode());
    }


    @Test
    void testCheckDeptAssignedToUsers() {
        DeptModel deptModel = new DeptModel();
        deptModel.setDeptId(DEPT_ID);
        when(deptService.isDeptAssignedToUsers(eq(DEPT_ID))).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class,
            () -> deptModel.checkDeptAssignedToUsers(deptService));

        Assertions.assertEquals(Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE, exception.getErrorCode());
    }

    @Test
    void testGenerateAncestorsWhenParentIdZero() {
        DeptModel deptModel = new DeptModel();
        deptModel.setParentId(0L);

        deptModel.generateAncestors(deptService);

        Assertions.assertEquals(deptModel.getAncestors(), deptModel.getParentId().toString());
    }

    @Test
    void testGenerateAncestorsWhenParentDeptNotExist() {
        DeptModel deptModel = new DeptModel();
        deptModel.setParentId(PARENT_ID);
        when(deptService.getById(eq(PARENT_ID))).thenReturn(null);

        ApiException exception = assertThrows(ApiException.class, () -> deptModel.generateAncestors(deptService));

        Assertions.assertEquals(Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED, exception.getErrorCode());
    }

    @Test
    void testGenerateAncestorsWhenParentDeptDisabled() {
        DeptModel deptModel = new DeptModel();
        deptModel.setParentId(PARENT_ID);
        SysDeptEntity parentDept = new SysDeptEntity();
        parentDept.setStatus(0);
        when(deptService.getById(eq(PARENT_ID))).thenReturn(parentDept);

        ApiException exception = assertThrows(ApiException.class, () -> deptModel.generateAncestors(deptService));

        Assertions.assertEquals(Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED, exception.getErrorCode());
    }


    @Test
    void testGenerateAncestorsSuccessful() {
        DeptModel deptModel = new DeptModel();
        deptModel.setParentId(PARENT_ID);
        SysDeptEntity parentDept = new SysDeptEntity();
        parentDept.setStatus(1);
        parentDept.setAncestors("1,100");
        when(deptService.getById(eq(PARENT_ID))).thenReturn(parentDept);

        deptModel.generateAncestors(deptService);

        Assertions.assertEquals("1,100,2", deptModel.getAncestors());
    }


    @Test
    void testCheckStatusAllowChangeWhenDisableButHasChildDept() {
        DeptModel deptModel = new DeptModel();
        deptModel.setDeptId(DEPT_ID);
        deptModel.setStatus(0);
        when(deptService.hasChildrenDept(eq(DEPT_ID), eq(true))).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, () -> deptModel.checkStatusAllowChange(deptService));

        Assertions.assertEquals(Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE, exception.getErrorCode());
    }


    @Test
    void testCheckStatusAllowChangeWhenDisableButNoChildDept() {
        DeptModel deptModel = new DeptModel();
        deptModel.setDeptId(DEPT_ID);
        deptModel.setStatus(0);
        when(deptService.hasChildrenDept(eq(DEPT_ID), eq(true))).thenReturn(false);

        Assertions.assertDoesNotThrow(()->deptModel.checkStatusAllowChange(deptService));
    }


    @Test
    void testCheckStatusAllowChangeWhenEnableAndHasChildDept() {
        DeptModel deptModel = new DeptModel();
        deptModel.setDeptId(DEPT_ID);
        deptModel.setStatus(1);
        when(deptService.hasChildrenDept(eq(DEPT_ID), eq(true))).thenReturn(true);

        Assertions.assertDoesNotThrow(()->deptModel.checkStatusAllowChange(deptService));
    }

}
