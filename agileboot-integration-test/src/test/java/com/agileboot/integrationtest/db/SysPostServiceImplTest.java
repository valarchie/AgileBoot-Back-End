package com.agileboot.integrationtest.db;

import com.agileboot.integrationtest.IntegrationTestApplication;
import com.agileboot.orm.service.ISysDeptService;
import com.agileboot.orm.service.ISysPostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysPostServiceImplTest {

    @Autowired
    ISysPostService postService;

    @Test
    @Rollback
    void testIsPostNameUnique() {
        boolean addWithSame = postService.isPostNameUnique(null, "董事长");
        boolean updateWithSame = postService.isPostNameUnique(1L, "董事长");
        boolean addWithoutSame = postService.isPostNameUnique(null, "董事长1");

        Assertions.assertFalse(addWithSame);
        Assertions.assertTrue(updateWithSame);
        Assertions.assertTrue(addWithoutSame);
    }


    @Test
    @Rollback
    void testIsPostCodeUnique() {
        boolean addWithSame = postService.isPostCodeUnique(null, "ceo");
        boolean updateWithSame = postService.isPostCodeUnique(1L, "ceo");
        boolean addWithoutSame = postService.isPostCodeUnique(null, "ceo1");

        Assertions.assertFalse(addWithSame);
        Assertions.assertTrue(updateWithSame);
        Assertions.assertTrue(addWithoutSame);
    }


    @Test
    @Rollback
    void testIsAssignedToUsers() {
        boolean assignedPost = postService.isAssignedToUsers(1L);
        boolean unassignedPost = postService.isAssignedToUsers(3L);

        Assertions.assertTrue(assignedPost);
        Assertions.assertFalse(unassignedPost);
    }


}
