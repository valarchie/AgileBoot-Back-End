package com.agileboot.infrastructure.web.domain.permission.checker;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.login.RoleInfo;
import com.agileboot.infrastructure.web.domain.permission.DataCondition;
import com.agileboot.infrastructure.web.domain.permission.DataPermissionChecker;
import com.agileboot.orm.service.ISysDeptService;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDataPermissionChecker extends DataPermissionChecker {

    private ISysDeptService deptService;


    @Override
    public boolean check(LoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }
        Set<Long> deptIdSet = loginUser.getRoleInfo().getDeptIdSet();
        Long targetDeptId = condition.getTargetDeptId();

        return condition.getTargetDeptId() != null && CollUtil.safeContains(deptIdSet, targetDeptId);
    }
}
