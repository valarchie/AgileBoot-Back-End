package com.agileboot.admin.controller.system;

import cn.hutool.core.collection.ListUtil;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.user.UserApplicationService;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.domain.system.user.command.ChangeStatusCommand;
import com.agileboot.domain.system.user.command.ResetPasswordCommand;
import com.agileboot.domain.system.user.command.UpdateUserCommand;
import com.agileboot.domain.system.user.dto.UserDTO;
import com.agileboot.domain.system.user.dto.UserDetailDTO;
import com.agileboot.domain.system.user.dto.UserInfoDTO;
import com.agileboot.domain.system.user.query.SearchUserQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.enums.dictionary.BusinessTypeEnum;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    @NonNull
    private UserApplicationService userApplicationService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@permission.has('system:user:list') AND @dataScope.checkDeptId(#query.deptId)")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(SearchUserQuery query) {
        PageDTO page = userApplicationService.getUserList(query);
        return ResponseDTO.ok(page);
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:user:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SearchUserQuery query) {
        PageDTO userList = userApplicationService.getUserList(query);
        CustomExcelUtil.writeToResponse(userList.getRows(), UserDTO.class, response);
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("@permission.has('system:user:import')")
    @PostMapping("/importData")
    public ResponseDTO<?> importData(MultipartFile file) {
        List<?> commands = CustomExcelUtil.readFromRequest(AddUserCommand.class, file);

        for (Object command : commands) {
            AddUserCommand addUserCommand = (AddUserCommand) command;
            userApplicationService.addUser(addUserCommand);
        }
        return ResponseDTO.ok();
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        CustomExcelUtil.writeToResponse(ListUtil.toList(new AddUserCommand()), AddUserCommand.class, response);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public ResponseDTO<UserDetailDTO> getUserDetailInfo(@PathVariable(value = "userId", required = false) Long userId) {
        UserDetailDTO userDetailInfo = userApplicationService.getUserDetailInfo(userId);
        return ResponseDTO.ok(userDetailInfo);
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@permission.has('system:user:add') AND @dataScope.checkDeptId(#command.deptId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<?> add(@Validated @RequestBody AddUserCommand command) {
        userApplicationService.addUser(command);
        return ResponseDTO.ok();
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@permission.has('system:user:edit') AND @dataScope.checkUserId(#command.userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<?> edit(@Validated @RequestBody UpdateUserCommand command) {
        userApplicationService.updateUser(command);
        return ResponseDTO.ok();
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@permission.has('system:user:remove') AND @dataScope.checkUserIds(#userIds)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{userIds}")
    public ResponseDTO<?> remove(@PathVariable List<Long> userIds) {
        BulkOperationCommand<Long> bulkDeleteCommand = new BulkOperationCommand<>(userIds);
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        userApplicationService.deleteUsers(loginUser, bulkDeleteCommand);
        return ResponseDTO.ok();
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@permission.has('system:user:resetPwd') AND @dataScope.checkUserId(#userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}/password/reset")
    public ResponseDTO<?> resetPassword(@PathVariable Long userId, @RequestBody ResetPasswordCommand command) {
        command.setUserId(userId);
        userApplicationService.resetUserPassword(command);
        return ResponseDTO.ok();
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@permission.has('system:user:edit') AND @dataScope.checkUserId(#command.userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}/status")
    public ResponseDTO<?> changeStatus(@PathVariable Long userId, @RequestBody ChangeStatusCommand command) {
        command.setUserId(userId);
        userApplicationService.changeUserStatus(command);
        return ResponseDTO.ok();
    }

    /**
     * 根据用户编号获取授权角色
     */
    @PreAuthorize("@permission.has('system:user:query')")
    @GetMapping("/{userId}/role")
    public ResponseDTO<UserInfoDTO> getRoleOfUser(@PathVariable("userId") Long userId) {
        UserInfoDTO userWithRole = userApplicationService.getUserWithRole(userId);
        return ResponseDTO.ok(userWithRole);
    }



}
