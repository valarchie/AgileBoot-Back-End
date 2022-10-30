package com.agileboot.domain.system.dept.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.orm.service.ISysDeptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

class DeptModelTest {

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



}
