package com.agileboot.domain.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysUserService;

/**
 * 用户模型工厂
 * @author valarchie
 */
public class UserModelFactory {

    public static UserModel loadFromDb(Long userId, ISysUserService userService) {
        SysUserEntity byId = userService.getById(userId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, userId, "用户");
        }
        return new UserModel(byId);
    }

    public static UserModel loadFromAddCommand(AddUserCommand command, UserModel model) {
        if (command != null && model != null) {
            BeanUtil.copyProperties(command, model);
        }
        return model;
    }

}
