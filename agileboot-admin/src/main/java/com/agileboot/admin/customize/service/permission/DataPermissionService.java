package com.agileboot.admin.customize.service.permission;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.agileboot.admin.customize.service.permission.model.DataCondition;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.WebLoginUser;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysUserService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 数据权限校验服务
 * @author valarchie
 */
@Service("dataScope")
@RequiredArgsConstructor
public class DataPermissionService {

    @NonNull
    private ISysUserService userService;

    /**
     * 通过userId 校验当前用户 对 目标用户是否有操作权限
     *
     * @param userId 用户id
     * @return 检验结果
     */
    public boolean checkUserId(Long userId) {
        WebLoginUser loginUser = AuthenticationUtils.getLoginUser();
        SysUserEntity targetUser = userService.getById(userId);
        if (targetUser == null) {
            return true;
        }
        return checkDataScope(loginUser, targetUser.getDeptId(), userId);
    }

    /**
     * 通过userId 校验当前用户 对 目标用户是否有操作权限
     * @param userIds 用户id列表
     * @return 校验结果
     */
    public boolean checkUserIds(List<Long> userIds) {
        if (CollUtil.isNotEmpty(userIds)) {
            for (Long userId : userIds) {
                boolean checkResult = checkUserId(userId);
                if (!checkResult) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDeptId(Long deptId) {
        WebLoginUser loginUser = AuthenticationUtils.getLoginUser();
        return checkDataScope(loginUser, deptId, null);
    }


    public boolean checkDataScope(WebLoginUser loginUser, Long targetDeptId, Long targetUserId) {
        DataCondition dataCondition = DataCondition.builder().targetDeptId(targetDeptId).targetUserId(targetUserId).build();
        AbstractDataPermissionChecker checker = DataPermissionCheckerFactory.getChecker(loginUser);
        return checker.check(loginUser, dataCondition);
    }



}
