package com.agileboot.integrationtest.db;

import com.agileboot.integrationtest.IntegrationTestApplication;
import com.agileboot.orm.service.ISysRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysRoleServiceImplTest {

    @Autowired
    ISysRoleService roleService;


    @Test
    @Rollback
    void testIsRoleNameUnique() {
        boolean addWithSame = roleService.isRoleNameUnique(null, "超级管理员");
        boolean updateWithSame = roleService.isRoleNameUnique(1L, "超级管理员");
        boolean addWithoutSame = roleService.isRoleNameUnique(null, "超级管理员1");

        Assertions.assertFalse(addWithSame);
        Assertions.assertTrue(updateWithSame);
        Assertions.assertTrue(addWithoutSame);
    }


    @Test
    @Rollback
    void testIsRoleCodeUnique() {
        boolean addWithSame = roleService.isRoleKeyUnique(null, "admin");
        boolean updateWithSame = roleService.isRoleKeyUnique(1L, "admin");
        boolean addWithoutSame = roleService.isRoleKeyUnique(null, "admin1");

        Assertions.assertFalse(addWithSame);
        Assertions.assertTrue(updateWithSame);
        Assertions.assertTrue(addWithoutSame);
    }



    @Test
    @Rollback
    void testIsAssignedToUsers() {
        boolean assignedRole = roleService.isAssignedToUsers(1L);
        boolean unassignedRole = roleService.isAssignedToUsers(3L);

        Assertions.assertTrue(assignedRole);
        Assertions.assertFalse(unassignedRole);
    }

}
