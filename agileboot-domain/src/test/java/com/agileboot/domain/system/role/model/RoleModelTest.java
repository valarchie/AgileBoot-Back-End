package com.agileboot.domain.system.role.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.hutool.core.collection.ListUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.role.db.SysRoleMenuService;
import com.agileboot.domain.system.role.db.SysRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoleModelTest {

    private final SysRoleService roleService = mock(SysRoleService.class);

    private final SysRoleMenuService roleMenuService = mock(SysRoleMenuService.class);

    private final RoleModelFactory roleModelFactory = new RoleModelFactory(roleService, roleMenuService);

    private static final long ROLE_ID = 1L;


    @Test
    void testCheckRoleNameUnique() {
        RoleModel roleWithSameName = roleModelFactory.create();
        roleWithSameName.setRoleId(ROLE_ID);
        roleWithSameName.setRoleName("role 1");
        RoleModel roleWithNewName = roleModelFactory.create();
        roleWithNewName.setRoleId(ROLE_ID);
        roleWithNewName.setRoleName("role 2");

        when(roleService.isRoleNameDuplicated(ROLE_ID, "role 1")).thenReturn(true);
        when(roleService.isRoleNameDuplicated(ROLE_ID, "role 2")).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, roleWithSameName::checkRoleNameUnique);
        Assertions.assertEquals(Business.ROLE_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(roleWithNewName::checkRoleNameUnique);

    }

    @Test
    void testCheckRoleCanBeDelete() {
        RoleModel roleModel = roleModelFactory.create();
        roleModel.setRoleId(ROLE_ID);

        when(roleService.isAssignedToUsers(ROLE_ID)).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, roleModel::checkRoleCanBeDelete);
        Assertions.assertEquals(Business.ROLE_ALREADY_ASSIGN_TO_USER, exception.getErrorCode());
    }

    @Test
    void testCheckRoleKeyUnique() {
        RoleModel roleWithSameKey = roleModelFactory.create();
        roleWithSameKey.setRoleId(ROLE_ID);
        roleWithSameKey.setRoleKey("key 1");
        RoleModel roleWithNewKey = roleModelFactory.create();
        roleWithNewKey.setRoleId(ROLE_ID);
        roleWithNewKey.setRoleKey("key 2");

        when(roleService.isRoleKeyDuplicated(ROLE_ID, "key 1")).thenReturn(true);
        when(roleService.isRoleKeyDuplicated(ROLE_ID, "key 2")).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, roleWithSameKey::checkRoleKeyUnique);
        Assertions.assertEquals(Business.ROLE_KEY_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(roleWithNewKey::checkRoleKeyUnique);
    }

    @Test
    void testGenerateDeptIdSetWhenNull() {
        RoleModel roleModel = roleModelFactory.create();
        roleModel.setDeptIds(null);
        roleModel.generateDeptIdSet();

        Assertions.assertEquals("", roleModel.getDeptIdSet());
    }


    @Test
    void testGenerateDeptIdSetWhenDuplicated() {
        RoleModel roleModel = roleModelFactory.create();
        roleModel.setDeptIds(ListUtil.of(1L,1L,2L,3L));

        ApiException exception = assertThrows(ApiException.class, roleModel::generateDeptIdSet);

        Assertions.assertEquals(Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT, exception.getErrorCode());
    }

    @Test
    void testGenerateDeptIdSetWhenSuccessful() {
        RoleModel roleModel = roleModelFactory.create();
        roleModel.setDeptIds(ListUtil.of(1L,2L,3L));
        roleModel.generateDeptIdSet();

        Assertions.assertEquals("1,2,3", roleModel.getDeptIdSet());
    }




}
