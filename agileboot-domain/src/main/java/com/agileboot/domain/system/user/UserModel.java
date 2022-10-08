package com.agileboot.domain.system.user;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.service.ISysUserService;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel extends SysUserEntity {

    public void checkUsernameIsUnique(ISysUserService userService) {
        if (userService.checkUserNameUnique(getUsername())) {
            throw new ApiException(ErrorCode.Business.USER_NAME_IS_NOT_UNIQUE);
        }
    }


    public void checkPhoneNumberIsUnique(ISysUserService userService) {
        if (StrUtil.isNotEmpty(getPhoneNumber()) && userService.checkPhoneUnique(getPhoneNumber(),
            getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE);
        }
    }

    public void checkEmailIsUnique(ISysUserService userService) {
        if (StrUtil.isNotEmpty(getEmail()) && userService.checkEmailUnique(getEmail(), getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_EMAIL_IS_NOT_UNIQUE);
        }
    }

    public void checkCanBeDelete(LoginUser loginUser) {
        if (Objects.equals(getUserId(), loginUser.getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE);
        }
    }

    public void checkCanBeModify(LoginUser loginUser) {
        if (LoginUser.isAdmin(this.getUserId()) && !loginUser.isAdmin()) {
            throw new ApiException(Business.UNSUPPORTED_OPERATION);
        }
    }


    public void modifyPassword(UpdateUserPasswordCommand command) {
        if (!AuthenticationUtils.matchesPassword(command.getOldPassword(), getPassword())) {
            throw new ApiException(ErrorCode.Business.USER_PASSWORD_IS_NOT_CORRECT);
        }

        if (AuthenticationUtils.matchesPassword(command.getNewPassword(), getPassword())) {
            throw new ApiException(ErrorCode.Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD);
        }
        setPassword(AuthenticationUtils.encryptPassword(command.getNewPassword()));
    }

    public void resetPassword(String newPassword) {
        setPassword(AuthenticationUtils.encryptPassword(newPassword));
    }



}
