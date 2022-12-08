package com.agileboot.domain.system.role.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.hutool.core.collection.ListUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.orm.service.ISysRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoleModelTest {

    private final ISysRoleService roleService = mock(ISysRoleService.class);

    private static final long ROLE_ID = 1L;


    @Test
    void testCheckRoleNameUnique() {
        RoleModel roleWithSameName = new RoleModel();
        roleWithSameName.setRoleId(ROLE_ID);
        roleWithSameName.setRoleName("role 1");
        RoleModel roleWithNewName = new RoleModel();
        roleWithNewName.setRoleId(ROLE_ID);
        roleWithNewName.setRoleName("role 2");

        when(roleService.isRoleNameDuplicated(eq(ROLE_ID), eq("role 1"))).thenReturn(true);
        when(roleService.isRoleNameDuplicated(eq(ROLE_ID), eq("role 2"))).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class,
            () -> roleWithSameName.checkRoleNameUnique(roleService));
        Assertions.assertEquals(Business.ROLE_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(() -> roleWithNewName.checkRoleNameUnique(roleService));

    }

    @Test
    void testCheckRoleCanBeDelete() {
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(ROLE_ID);
        roleModel.checkRoleCanBeDelete(roleService);

        when(roleService.isAssignedToUsers(eq(ROLE_ID))).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, () -> roleModel.checkRoleCanBeDelete(roleService));
        Assertions.assertEquals(Business.ROLE_ALREADY_ASSIGN_TO_USER, exception.getErrorCode());
    }

    @Test
    void testCheckRoleKeyUnique() {
        RoleModel roleWithSameKey = new RoleModel();
        roleWithSameKey.setRoleId(ROLE_ID);
        roleWithSameKey.setRoleKey("key 1");
        RoleModel roleWithNewKey = new RoleModel();
        roleWithNewKey.setRoleId(ROLE_ID);
        roleWithNewKey.setRoleKey("key 2");

        when(roleService.isRoleKeyDuplicated(eq(ROLE_ID), eq("key 1"))).thenReturn(true);
        when(roleService.isRoleKeyDuplicated(eq(ROLE_ID), eq("key 2"))).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class,
            () -> roleWithSameKey.checkRoleKeyUnique(roleService));
        Assertions.assertEquals(Business.ROLE_KEY_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(() -> roleWithNewKey.checkRoleKeyUnique(roleService));
    }

    @Test
    void testGenerateDeptIdSetWhenNull() {
        RoleModel roleModel = new RoleModel();
        roleModel.setDeptIds(null);
        roleModel.generateDeptIdSet();

        Assertions.assertEquals("", roleModel.getDeptIdSet());
    }


    @Test
    void testGenerateDeptIdSetWhenDuplicated() {
        RoleModel roleModel = new RoleModel();
        roleModel.setDeptIds(ListUtil.of(1L,1L,2L,3L));

        ApiException exception = assertThrows(ApiException.class, roleModel::generateDeptIdSet);

        Assertions.assertEquals(Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT, exception.getErrorCode());
    }

    @Test
    void testGenerateDeptIdSetWhenSuccessful() {
        RoleModel roleModel = new RoleModel();
        roleModel.setDeptIds(ListUtil.of(1L,2L,3L));
        roleModel.generateDeptIdSet();

        Assertions.assertEquals("1,2,3", roleModel.getDeptIdSet());
    }




}
