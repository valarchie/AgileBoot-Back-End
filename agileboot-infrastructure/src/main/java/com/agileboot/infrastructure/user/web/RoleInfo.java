package com.agileboot.infrastructure.user.web;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.SetUtils;

/**
 * @author valarchie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleInfo {

    public static final RoleInfo EMPTY_ROLE = new RoleInfo();
    public static final long ADMIN_ROLE_ID = -1;
    public static final String ADMIN_ROLE_KEY = "admin";
    public static final String ALL_PERMISSIONS = "*:*:*";

    public static final Set<String> ADMIN_PERMISSIONS = SetUtils.hashSet(ALL_PERMISSIONS);


    public RoleInfo(Long roleId, String roleKey, DataScopeEnum dataScope, Set<Long> deptIdSet,
        Set<String> menuPermissions, Set<Long> menuIds) {
        this.roleId = roleId;
        this.roleKey = roleKey;
        this.dataScope = dataScope;
        this.deptIdSet = deptIdSet;
        this.menuPermissions = menuPermissions != null ? menuPermissions : SetUtils.emptySet();
        this.menuIds = menuIds != null ? menuIds : SetUtils.emptySet();
    }


    private Long roleId;
    private String roleName;
    private DataScopeEnum dataScope;
    private Set<Long> deptIdSet;
    private String roleKey;
    private Set<String> menuPermissions;
    private Set<Long> menuIds;

}
