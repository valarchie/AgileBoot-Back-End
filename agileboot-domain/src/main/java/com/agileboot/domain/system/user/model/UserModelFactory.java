package com.agileboot.domain.system.user.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.dept.model.DeptModelFactory;
import com.agileboot.domain.system.post.model.PostModelFactory;
import com.agileboot.domain.system.role.model.RoleModelFactory;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.agileboot.domain.system.user.db.SysUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class UserModelFactory {

    @NonNull
    private SysUserService userService;

    @NonNull
    private PostModelFactory postModelFactory;

    @NonNull
    private DeptModelFactory deptModelFactory;

    @NonNull
    private RoleModelFactory roleModelFactory;

    public UserModel loadById(Long userId) {
        SysUserEntity byId = userService.getById(userId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, userId, "用户");
        }
        return new UserModel(byId, userService, postModelFactory, deptModelFactory, roleModelFactory);
    }

    public UserModel create() {
        return new UserModel(userService, postModelFactory, deptModelFactory, roleModelFactory);
    }

}
