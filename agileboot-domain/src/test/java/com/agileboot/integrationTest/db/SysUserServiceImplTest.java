package com.agileboot.integrationTest.db;

import com.agileboot.domain.system.role.query.AllocatedRoleQuery;
import com.agileboot.domain.system.role.query.UnallocatedRoleQuery;
import com.agileboot.domain.system.user.query.SearchUserQuery;
import com.agileboot.integrationTest.IntegrationTestApplication;
import com.agileboot.domain.system.menu.db.SysMenuEntity;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.role.db.SysRoleEntity;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.agileboot.domain.system.user.db.SearchUserDO;
import com.agileboot.domain.system.menu.db.SysMenuService;
import com.agileboot.domain.system.user.db.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysUserServiceImplTest {

    @Resource
    SysUserService userService;
    @Resource
    SysMenuService menuService;

    @Test
    @Rollback
    void testIsUserNameDuplicated() {
        boolean isDuplicated = userService.isUserNameDuplicated("admin");

        Assertions.assertTrue(isDuplicated);
    }


    @Test
    @Rollback
    void testIsPhoneDuplicated() {
        boolean addWithSame = userService.isPhoneDuplicated("15888888889", null);
        boolean updateWithSame = userService.isPhoneDuplicated("15888888889", 1L);
        boolean addWithoutSame = userService.isPhoneDuplicated("15888888899", null);

        Assertions.assertTrue(addWithSame);
        Assertions.assertFalse(updateWithSame);
        Assertions.assertFalse(addWithoutSame);
    }


    @Test
    @Rollback
    void testIsEmailDuplicated() {
        boolean addWithSame = userService.isEmailDuplicated("agileboot@163.com", null);
        boolean updateWithSame = userService.isEmailDuplicated("agileboot@163.com", 1L);
        boolean addWithoutSame = userService.isEmailDuplicated("agileboot@164.com", null);

        Assertions.assertTrue(addWithSame);
        Assertions.assertFalse(updateWithSame);
        Assertions.assertFalse(addWithoutSame);
    }

    @Test
    @Rollback
    void testGetRoleOfUser() {
        SysRoleEntity roleOfUser = userService.getRoleOfUser(1L);

        Assertions.assertEquals(1L, roleOfUser.getRoleId());
        Assertions.assertEquals("超级管理员", roleOfUser.getRoleName());
        Assertions.assertEquals("admin", roleOfUser.getRoleKey());
    }

    @Test
    @Rollback
    void testGetPostOfUser() {
        SysPostEntity postOfUser = userService.getPostOfUser(1L);

        Assertions.assertEquals(1L, postOfUser.getPostId());
        Assertions.assertEquals("董事长", postOfUser.getPostName());
        Assertions.assertEquals("ceo", postOfUser.getPostCode());
    }

    @Test
    @Rollback
    void testGetMenuPermissionsForUsers() {
        Set<String> permissionByUser = userService.getMenuPermissionsForUser(2L);
        List<SysMenuEntity> allMenus = menuService.list();
        Set<String> allPermissions = allMenus.stream().map(SysMenuEntity::getPermission).collect(Collectors.toSet());

        Assertions.assertEquals(allPermissions.size() - 1, permissionByUser.size());
    }

    @Test
    @Rollback
    void testGetUserByUserName() {
        SysUserEntity admin = userService.getUserByUserName("admin");

        Assertions.assertEquals(1L, admin.getUserId());
        Assertions.assertEquals(1L, admin.getRoleId());
        Assertions.assertEquals(1L, admin.getPostId());
    }

    @Test
    @Rollback
    void testGetRoleAssignedUserList() {
        AllocatedRoleQuery allocatedRoleQuery = new AllocatedRoleQuery();
        allocatedRoleQuery.setRoleId(1L);
        allocatedRoleQuery.setUsername("admin");

        Page<SysUserEntity> roleAssignedPage = userService.getUserListByRole(allocatedRoleQuery);

        Assertions.assertEquals(1, roleAssignedPage.getTotal());
        Assertions.assertEquals("admin", roleAssignedPage.getRecords().get(0).getUsername());
    }

    @Test
    @Rollback
    void testGetRoleUnassignedUserList() {
        UnallocatedRoleQuery unallocatedRoleQuery = new UnallocatedRoleQuery();
        unallocatedRoleQuery.setRoleId(3L);
        unallocatedRoleQuery.setUsername("ag2");

        Page<SysUserEntity> roleAssignedPage = userService.getUserListByRole(unallocatedRoleQuery);

        Assertions.assertEquals(1, roleAssignedPage.getTotal());
        Assertions.assertEquals("ag2", roleAssignedPage.getRecords().get(0).getUsername());
    }

    @Test
    @Rollback
    void testGetUserList() {
        SearchUserQuery<SearchUserDO> searchUserQuery = new SearchUserQuery<>();
        searchUserQuery.setUsername("a");

        Page<SearchUserDO> userList = userService.getUserList(searchUserQuery);

        Assertions.assertEquals(3, userList.getTotal());
        Assertions.assertEquals("admin", userList.getRecords().get(0).getUsername());
    }


}
