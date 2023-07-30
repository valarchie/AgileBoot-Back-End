package com.agileboot.admin.customize.service.permission.model.checker;

import com.agileboot.infrastructure.web.domain.login.WebLoginUser;
import com.agileboot.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.orm.system.service.ISysDeptService;
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
public class OnlySelfDataPermissionChecker extends AbstractDataPermissionChecker {

    private ISysDeptService deptService;

    @Override
    public boolean check(WebLoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getUserId() == null || condition.getTargetUserId() == null) {
            return false;
        }

        Long currentUserId = loginUser.getUserId();
        Long targetUserId = condition.getTargetUserId();
        return Objects.equals(currentUserId, targetUserId);
    }

}
