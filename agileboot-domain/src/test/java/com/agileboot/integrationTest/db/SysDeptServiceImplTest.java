package com.agileboot.integrationTest.db;

import com.agileboot.integrationTest.IntegrationTestApplication;
import com.agileboot.domain.system.dept.db.SysDeptService;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysDeptServiceImplTest {

    @Resource
    SysDeptService deptService;

    @Test
    @Rollback
    void testIsDeptNameDuplicated() {
        boolean addWithSame = deptService.isDeptNameDuplicated("AgileBoot科技", null, null);
        boolean updateWithSame = deptService.isDeptNameDuplicated("AgileBoot科技", 1L, null);
        boolean addSameInParent = deptService.isDeptNameDuplicated("深圳总公司", null, 1L);

        Assertions.assertTrue(addWithSame);
        Assertions.assertFalse(updateWithSame);
        Assertions.assertTrue(addSameInParent);
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
