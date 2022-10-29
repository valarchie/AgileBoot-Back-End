package com.agileboot.integrationtest.db;

import com.agileboot.integrationtest.IntegrationTestApplication;
import com.agileboot.orm.service.ISysDeptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysDeptServiceImplTest {

    @Autowired
    ISysDeptService deptService;

    @Test
    @Rollback
    void testIsDeptNameUnique() {
        boolean addWithSame = deptService.isDeptNameUnique("AgileBoot科技", null, null);
        boolean updateWithSame = deptService.isDeptNameUnique("AgileBoot科技", 1L, null);
        boolean addSameInParent = deptService.isDeptNameUnique("深圳总公司", null, 1L);

        Assertions.assertFalse(addWithSame);
        Assertions.assertTrue(updateWithSame);
        Assertions.assertFalse(addSameInParent);
    }


    @Test
    @Rollback
    void testHasChildDept() {
        boolean hasChild = deptService.hasChildrenDept(3L, null);
        boolean hasDisableChild = deptService.hasChildrenDept(3L, false);

        Assertions.assertTrue(hasChild);
        Assertions.assertTrue(hasDisableChild);
    }


    @Test
    @Rollback
    void testIsChildOfTheDept() {
        boolean isIndirectChild = deptService.isChildOfTheDept(1L, 10L);
        boolean isDirectChild = deptService.isChildOfTheDept(3L, 10L);
        boolean isNotChild = deptService.isChildOfTheDept(5L, 10L);

        Assertions.assertTrue(isIndirectChild);
        Assertions.assertTrue(isDirectChild);
        Assertions.assertFalse(isNotChild);
    }


    @Test
    @Rollback
    void testIsDeptAssignedToUsers() {
        boolean notAssigned = deptService.isDeptAssignedToUsers(1L);
        boolean isAssigned = deptService.isDeptAssignedToUsers(4L);

        Assertions.assertFalse(notAssigned);
        Assertions.assertTrue(isAssigned);
    }


}
