package com.agileboot.domain.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.domain.system.user.command.UpdateProfileCommand;
import com.agileboot.domain.system.user.command.UpdateUserCommand;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysUserService;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class UserModel extends SysUserEntity {

    private ISysUserService userService;

    public UserModel(SysUserEntity entity, ISysUserService userService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.userService = userService;
    }

    public UserModel(ISysUserService userService) {
        this.userService = userService;
    }

    public void loadAddUserCommand(AddUserCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "userId");
        }
    }


    public void loadUpdateUserCommand(UpdateUserCommand command) {
        if (command != null) {
            loadAddUserCommand(command);
        }
    }

    public void loadUpdateProfileCommand(UpdateProfileCommand command) {
        if (command != null) {
            this.setSex(command.getSex());
            this.setNickName(command.getNickName());
            this.setPhoneNumber(command.getPhoneNumber());
            this.setEmail(command.getEmail());
        }
    }


    public void checkUsernameIsUnique() {
        if (userService.isUserNameDuplicated(getUsername())) {
            throw new ApiException(ErrorCode.Business.USER_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkPhoneNumberIsUnique() {
        if (StrUtil.isNotEmpty(getPhoneNumber()) && userService.isPhoneDuplicated(getPhoneNumber(),
            getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE);
        }
    }

    public void checkEmailIsUnique() {
        if (StrUtil.isNotEmpty(getEmail()) && userService.isEmailDuplicated(getEmail(), getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_EMAIL_IS_NOT_UNIQUE);
        }
    }

    public void checkCanBeDelete(LoginUser loginUser) {
        if (Objects.equals(getUserId(), loginUser.getUserId())
            || LoginUser.isAdmin(getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE);
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

    @Override
    public boolean updateById() {
        if (LoginUser.isAdmin(this.getUserId())) {
            throw new ApiException(Business.USER_ADMIN_CAN_NOT_BE_MODIFY);
        }

       return super.updateById();
    }

}
