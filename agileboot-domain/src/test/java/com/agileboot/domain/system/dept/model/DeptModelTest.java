package com.agileboot.domain.system.dept.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.dept.db.SysDeptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

class DeptModelTest {

    private static final Long DEPT_ID = 1L;
    private static final Long PARENT_ID = 2L;

    private final SysDeptService deptService = mock(SysDeptService.class);

    private final DeptModelFactory deptModelFactory = new DeptModelFactory(deptService);

    @Test
    void testCheckDeptNameUnique() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setDeptName("dept 1");
        when(
            deptService.isDeptNameDuplicated(ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.any())).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, deptModel::checkDeptNameUnique);
        Assertions.assertEquals(Business.DEPT_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
    }


    @Test
    void testCheckParentIdConflict() {
        DeptModel deptModel = deptModelFactory.create();
        Long sameId = 1L;
        deptModel.setDeptId(sameId);
        deptModel.setParentId(sameId);

        ApiException exception = assertThrows(ApiException.class, deptModel::checkParentIdConflict);

        Assertions.assertEquals(Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF, exception.getErrorCode());
    }


    @Test
    void testCheckHasChildDept() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setDeptId(DEPT_ID);
        when(deptService.hasChildrenDept((DEPT_ID), eq(null))).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, deptModel::checkHasChildDept);

        Assertions.assertEquals(Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE, exception.getErrorCode());
    }


    @Test
    void testCheckDeptAssignedToUsers() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setDeptId(DEPT_ID);
        when(deptService.isDeptAssignedToUsers(DEPT_ID)).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class,
            deptModel::checkDeptAssignedToUsers);

        Assertions.assertEquals(Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE, exception.getErrorCode());
    }

    @Test
    void testGenerateAncestorsWhenParentIdZero() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setParentId(0L);

        deptModel.generateAncestors();

        Assertions.assertEquals(deptModel.getAncestors(), deptModel.getParentId().toString());
    }

    @Test
    void testGenerateAncestorsWhenParentDeptNotExist() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setParentId(PARENT_ID);
        when(deptService.getById(PARENT_ID)).thenReturn(null);

        ApiException exception = assertThrows(ApiException.class, deptModel::generateAncestors);

        Assertions.assertEquals(Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED, exception.getErrorCode());
    }

    @Test
    void testGenerateAncestorsWhenParentDeptDisabled() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setParentId(PARENT_ID);
        SysDeptEntity parentDept = new SysDeptEntity();
        parentDept.setStatus(0);

        when(deptService.getById(PARENT_ID)).thenReturn(parentDept);

        ApiException exception = assertThrows(ApiException.class, deptModel::generateAncestors);
        Assertions.assertEquals(Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED, exception.getErrorCode());
    }


    @Test
    void testGenerateAncestorsSuccessful() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setParentId(PARENT_ID);
        SysDeptEntity parentDept = new SysDeptEntity();
        parentDept.setStatus(1);
        parentDept.setAncestors("1,100");
        when(deptService.getById(PARENT_ID)).thenReturn(parentDept);
        deptModel.generateAncestors();

        Assertions.assertEquals("1,100,2", deptModel.getAncestors());
    }


    @Test
    void testCheckStatusAllowChangeWhenDisableButHasChildDept() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setDeptId(DEPT_ID);
        deptModel.setStatus(0);
        when(deptService.hasChildrenDept(DEPT_ID, true)).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, deptModel::checkStatusAllowChange);

        Assertions.assertEquals(Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE, exception.getErrorCode());
    }


    @Test
    void testCheckStatusAllowChangeWhenDisableButNoChildDept() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setDeptId(DEPT_ID);
        deptModel.setStatus(0);
        when(deptService.hasChildrenDept(DEPT_ID, true)).thenReturn(false);

        Assertions.assertDoesNotThrow(deptModel::checkStatusAllowChange);
    }


    @Test
    void testCheckStatusAllowChangeWhenEnableAndHasChildDept() {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.setDeptId(DEPT_ID);
        deptModel.setStatus(1);
        when(deptService.hasChildrenDept(DEPT_ID, true)).thenReturn(true);
        Assertions.assertDoesNotThrow(deptModel::checkStatusAllowChange);
    }

}
