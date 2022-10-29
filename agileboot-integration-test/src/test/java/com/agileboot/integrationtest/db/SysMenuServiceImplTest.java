package com.agileboot.integrationtest.db;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.integrationtest.IntegrationTestApplication;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.enums.MenuTypeEnum;
import com.agileboot.orm.service.ISysMenuService;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysMenuServiceImplTest {

    @Autowired
    ISysMenuService menuService;

    @Test
    @Rollback
    void testGetMenuListByUserId() {
        List<SysMenuEntity> menusMissingLastMenu = menuService.getMenuListByUserId(2L);
        List<SysMenuEntity> allMenus = menuService.list();

        Assertions.assertEquals(allMenus.size(), menusMissingLastMenu.size() + 1);
    }

    @Test
    @Rollback
    void testGetMenuIdsByRoleId() {
        List<Long> menusMissingLastMenu = menuService.getMenuIdsByRoleId(2L);
        List<SysMenuEntity> allMenus = menuService.list();

        Assertions.assertEquals(allMenus.size(), menusMissingLastMenu.size() + 1);
    }

    @Test
    @Rollback
    void testIsMenuNameUnique() {
        boolean addWithSame = menuService.isMenuNameUnique("用户管理", null, 1L);
        boolean updateWithSame = menuService.isMenuNameUnique("用户管理", 5L, 1L);
        boolean addWithoutSame = menuService.isMenuNameUnique("用户管理", null, 2L);

        Assertions.assertFalse(addWithSame);
        Assertions.assertTrue(updateWithSame);
        Assertions.assertTrue(addWithoutSame);
    }

    @Test
    @Rollback
    void testHasChildrenMenus() {
        boolean hasChildrenMenu = menuService.hasChildrenMenu(5L);
        boolean hasNotChildrenMenu = menuService.hasChildrenMenu(20L);

        Assertions.assertTrue(hasChildrenMenu);
        Assertions.assertFalse(hasNotChildrenMenu);
    }

    @Test
    @Rollback
    void testIsMenuAssignToRole() {
        List<SysMenuEntity> allMenus = menuService.list();

        boolean isAssignToRole = menuService.isMenuAssignToRoles(CollUtil.getFirst(allMenus).getMenuId());
        // role2 默认不给最后一个权限 所以最后一个菜单无权限
        boolean isNotAssignToRole = menuService.isMenuAssignToRoles(CollUtil.getLast(allMenus).getMenuId());

        Assertions.assertFalse(isNotAssignToRole);
        Assertions.assertTrue(isAssignToRole);
    }



}
