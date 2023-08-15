package com.agileboot.admin.customize.service.permission.model.checker;

import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.domain.system.dept.db.SysDeptService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptTreeDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;

    @Override
    public boolean check(SystemLoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getDeptId() == null || condition.getTargetDeptId() == null) {
            return false;
        }

        Long currentDeptId = loginUser.getDeptId();
        Long targetDeptId = condition.getTargetDeptId();

        boolean isContainsTargetDept = deptService.isChildOfTheDept(loginUser.getDeptId(), targetDeptId);
        boolean isSameDept = Objects.equals(currentDeptId, targetDeptId);

        return isContainsTargetDept || isSameDept;
    }

}
