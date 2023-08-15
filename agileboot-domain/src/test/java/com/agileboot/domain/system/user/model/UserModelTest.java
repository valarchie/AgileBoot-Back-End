package com.agileboot.domain.system.user.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.dept.model.DeptModelFactory;
import com.agileboot.domain.system.post.model.PostModelFactory;
import com.agileboot.domain.system.role.model.RoleModelFactory;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.infrastructure.user.AuthenticationUtils;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.domain.system.user.db.SysUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserModelTest {

    private final SysUserService userService = mock(SysUserService.class);
    private final PostModelFactory postModelFactory = mock(PostModelFactory.class);
    private final DeptModelFactory deptModelFactory = mock(DeptModelFactory.class);
    private final RoleModelFactory roleModelFactory = mock(RoleModelFactory.class);

    private final UserModelFactory userModelFactory = new UserModelFactory(userService, postModelFactory,
        deptModelFactory, roleModelFactory);

    private static final long USER_ID = 1L;
    private static final long ADMIN_USER_ID = 1L;

    @Test
    void testCheckUsernameIsUnique() {
        UserModel userWithSameName = userModelFactory.create();
        userWithSameName.setUserId(USER_ID);
        userWithSameName.setUsername("user 1");
        UserModel userWithNewName = userModelFactory.create();
        userWithNewName.setUserId(USER_ID);
        userWithNewName.setUsername("user 2");

        when(userService.isUserNameDuplicated("user 1")).thenReturn(true);
        when(userService.isUserNameDuplicated("user 2")).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, userWithSameName::checkUsernameIsUnique);
        Assertions.assertEquals(Business.USER_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(userWithNewName::checkUsernameIsUnique);
    }

    @Test
    void testCheckPhoneNumberIsUnique() {
        UserModel userWithSameNumber = userModelFactory.create();
        userWithSameNumber.setUserId(USER_ID);
        userWithSameNumber.setPhoneNumber("111111");
        UserModel userWithNewNumber = userModelFactory.create();
        userWithNewNumber.setUserId(USER_ID);
        userWithNewNumber.setPhoneNumber("222222");

        when(userService.isPhoneDuplicated("111111", USER_ID)).thenReturn(true);
        when(userService.isPhoneDuplicated("222222", USER_ID)).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, userWithSameNumber::checkPhoneNumberIsUnique);
        Assertions.assertEquals(Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(userWithNewNumber::checkPhoneNumberIsUnique);
    }

    @Test
    void testCheckEmailIsUnique() {
        UserModel userWithSameEmail = userModelFactory.create();
        userWithSameEmail.setUserId(USER_ID);
        userWithSameEmail.setEmail("1@1.com");
        UserModel userWithNewNumber = userModelFactory.create();
        userWithNewNumber.setUserId(USER_ID);
        userWithNewNumber.setEmail("2@2.com");

        when(userService.isEmailDuplicated("1@1.com", USER_ID)).thenReturn(true);
        when(userService.isEmailDuplicated("2@2.com", USER_ID)).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, userWithSameEmail::checkEmailIsUnique);
        Assertions.assertEquals(Business.USER_EMAIL_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(userWithNewNumber::checkPhoneNumberIsUnique);
    }

    @Test
    void testCheckCanBeDeleteWhenDeleteItself() {
        UserModel userModel = userModelFactory.create();
        userModel.setUserId(USER_ID);
        SystemLoginUser loginUser = new SystemLoginUser();
        loginUser.setUserId(USER_ID);

        ApiException exception = assertThrows(ApiException.class, () -> userModel.checkCanBeDelete(loginUser));

        Assertions.assertEquals(Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE, exception.getErrorCode());
    }

    @Test
    void testCheckCanBeDeleteWhenDeleteAdmin() {
        UserModel userModel = userModelFactory.create();
        long adminId = 1L;
        userModel.setUserId(adminId);
        SystemLoginUser loginUser = new SystemLoginUser();
        loginUser.setUserId(2L);

        ApiException exception = assertThrows(ApiException.class, () -> userModel.checkCanBeDelete(loginUser));

        Assertions.assertEquals(Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE, exception.getErrorCode());
    }


    @Test
    void testCheckCanBeDeleteWhenSuccessful() {
        UserModel userModel = userModelFactory.create();
        userModel.setUserId(2L);
        SystemLoginUser loginUser = new SystemLoginUser();
        loginUser.setUserId(ADMIN_USER_ID);

        Assertions.assertDoesNotThrow(() -> userModel.checkCanBeDelete(loginUser));
    }

    @Test
    void testModifyPasswordWhenSuccessful() {
        UserModel userModel = userModelFactory.create();
        userModel.setPassword("$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");
        UpdateUserPasswordCommand passwordCommand = new UpdateUserPasswordCommand();
        passwordCommand.setOldPassword("admin123");
        String newPassword = "admin456";
        passwordCommand.setNewPassword(newPassword);

        userModel.modifyPassword(passwordCommand);

        Assertions.assertTrue(AuthenticationUtils.matchesPassword(newPassword, userModel.getPassword()));
    }

    @Test
    void testModifyPasswordWhenPasswordWrong() {
        UserModel userModel = userModelFactory.create();
        userModel.setPassword("$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");
        UpdateUserPasswordCommand passwordCommand = new UpdateUserPasswordCommand();
        passwordCommand.setOldPassword("admin999");
        String newPassword = "admin456";
        passwordCommand.setNewPassword(newPassword);

        ApiException exception = assertThrows(ApiException.class, () -> userModel.modifyPassword(passwordCommand));
        Assertions.assertEquals(Business.USER_PASSWORD_IS_NOT_CORRECT, exception.getErrorCode());
    }

    @Test
    void testModifyPasswordWhenOldNewPasswordSame() {
        UserModel userModel = userModelFactory.create();
        userModel.setPassword("$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");
        UpdateUserPasswordCommand passwordCommand = new UpdateUserPasswordCommand();
        passwordCommand.setOldPassword("admin123");
        String newPassword = "admin123";
        passwordCommand.setNewPassword(newPassword);

        ApiException exception = assertThrows(ApiException.class, () -> userModel.modifyPassword(passwordCommand));
        Assertions.assertEquals(Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD, exception.getErrorCode());
    }

    @Test
    void testResetPassword() {
        UserModel userModel = userModelFactory.create();
        userModel.resetPassword("admin456");

        Assertions.assertTrue(AuthenticationUtils.matchesPassword("admin456", userModel.getPassword()));
    }
}
