package com.agileboot.admin.controller.system;

import com.agileboot.common.constant.Constants.UploadSubDir;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.file.FileUploadUtils;
import com.agileboot.domain.common.dto.UploadFileDTO;
import com.agileboot.domain.system.user.UserApplicationService;
import com.agileboot.domain.system.user.dto.UserProfileDTO;
import com.agileboot.domain.system.user.command.UpdateProfileCommand;
import com.agileboot.domain.system.user.command.UpdateUserAvatarCommand;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.orm.enums.dictionary.BusinessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * 个人信息
     */
    @GetMapping
    public ResponseDTO<?> profile() {
        LoginUser user = AuthenticationUtils.getLoginUser();
        UserProfileDTO userProfile = userApplicationService.getUserProfile(user.getUserId());
        return ResponseDTO.ok(userProfile);
    }

    /**
     * 修改用户
     */
    @AccessLog(title = "个人信息", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<?> updateProfile(@RequestBody UpdateProfileCommand command) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        command.setUserId(loginUser.getUserId());
        userApplicationService.updateUserProfile(command);
        return ResponseDTO.ok();
    }

    /**
     * 重置密码
     */
    @AccessLog(title = "个人信息", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/password")
    public ResponseDTO<?> updatePassword(@RequestBody UpdateUserPasswordCommand command) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        command.setUserId(loginUser.getUserId());
        userApplicationService.updatePasswordBySelf(loginUser, command);
        return ResponseDTO.ok();
    }

    /**
     * 头像上传
     */
    @AccessLog(title = "用户头像", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/avatar")
    public ResponseDTO<?> avatar(@RequestParam("avatarfile") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException(ErrorCode.Business.USER_UPLOAD_FILE_FAILED);
        }
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        String avatarUrl = FileUploadUtils.upload(UploadSubDir.AVATAR_PATH, file);

        userApplicationService.updateUserAvatar(loginUser, new UpdateUserAvatarCommand(loginUser.getUserId(), avatarUrl));
        return ResponseDTO.ok(new UploadFileDTO(avatarUrl));
    }
}
