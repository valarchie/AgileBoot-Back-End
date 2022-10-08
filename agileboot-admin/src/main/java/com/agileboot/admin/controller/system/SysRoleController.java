package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.system.role.AddRoleCommand;
import com.agileboot.domain.system.role.AllocatedRoleQuery;
import com.agileboot.domain.system.role.RoleDTO;
import com.agileboot.domain.system.role.RoleDomainService;
import com.agileboot.domain.system.role.RoleQuery;
import com.agileboot.domain.system.role.UnallocatedRoleQuery;
import com.agileboot.domain.system.role.UpdateDataScopeCommand;
import com.agileboot.domain.system.role.UpdateRoleCommand;
import com.agileboot.domain.system.role.UpdateStatusCommand;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * 角色信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/role")
@Validated
public class SysRoleController extends BaseController {

    @Autowired
    private RoleDomainService roleDomainService;

    @PreAuthorize("@ss.hasPerm('system:role:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(RoleQuery query) {
        PageDTO pageDTO = roleDomainService.getRoleList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "角色管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPerm('system:role:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, RoleQuery query) {
        PageDTO pageDTO = roleDomainService.getRoleList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), RoleDTO.class, response);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public ResponseDTO getInfo(@PathVariable @NotNull Long roleId) {
        RoleDTO roleInfo = roleDomainService.getRoleInfo(roleId);
        return ResponseDTO.ok(roleInfo);
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPerm('system:role:add')")
    @AccessLog(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseDTO add(@RequestBody AddRoleCommand addCommand) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        roleDomainService.addRole(addCommand, loginUser);
        return ResponseDTO.ok();
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPerm('system:role:remove')")
    @AccessLog(title = "角色管理", businessType = BusinessType.INSERT)
    @DeleteMapping(value = "/{roleId}")
    public ResponseDTO remove(@PathVariable("roleId")List<Long> roleIds) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        roleDomainService.deleteRoleByBulk(roleIds, loginUser);
        return ResponseDTO.ok();
    }

    /**
     * 修改保存角色
     */
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseDTO edit(@Validated @RequestBody UpdateRoleCommand updateCommand) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        roleDomainService.updateRole(updateCommand, loginUser);
        return ResponseDTO.ok();
    }

    /**
     * 修改保存数据权限
     */
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/{roleId}/dataScope")
    public ResponseDTO dataScope(@PathVariable("roleId")Long roleId, @RequestBody UpdateDataScopeCommand command) {
        command.setRoleId(roleId);

        roleDomainService.updateDataScope(command);
        return ResponseDTO.ok();
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/{roleId}/status")
    public ResponseDTO changeStatus(@PathVariable("roleId")Long roleId, @RequestBody UpdateStatusCommand command) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        command.setRoleId(roleId);

        roleDomainService.updateStatus(command, loginUser);
        return ResponseDTO.ok();
    }


    /**
     * 查询已分配用户角色列表
     */
    @PreAuthorize("@ss.hasPerm('system:role:list')")
    @GetMapping("/{roleId}/allocated/list")
    public ResponseDTO<PageDTO> allocatedUserList(@PathVariable("roleId")Long roleId, AllocatedRoleQuery query) {
        query.setRoleId(roleId);
        PageDTO page = roleDomainService.getAllocatedUserList(query);
        return ResponseDTO.ok(page);
    }

    /**
     * 查询未分配用户角色列表
     */
    @PreAuthorize("@ss.hasPerm('system:role:list')")
    @GetMapping("/{roleId}/unallocated/list")
    public ResponseDTO<PageDTO> unallocatedUserList(@PathVariable("roleId")Long roleId, UnallocatedRoleQuery query) {
        query.setRoleId(roleId);
        PageDTO page = roleDomainService.getUnallocatedUserList(query);
        return ResponseDTO.ok(page);
    }

    /**
     * 取消授权用户
     */
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessType.GRANT)
    @DeleteMapping("/{roleId}/user/grant")
    public ResponseDTO deleteRoleOfUser(@PathVariable("roleId")Long roleId, @RequestBody Long userId) {
        roleDomainService.deleteRoleOfUser(userId);
        return ResponseDTO.ok();
    }

    /**
     * 批量取消授权用户
     */
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessType.GRANT)
    @DeleteMapping("/users/{userIds}/grant/bulk")
    public ResponseDTO deleteRoleOfUserByBulk(@PathVariable("userIds") List<Long> userIds) {
        roleDomainService.deleteRoleOfUserByBulk(userIds);
        return ResponseDTO.ok();
    }

    /**
     * 批量选择用户授权
     */
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/{roleId}/users/{userIds}/grant/bulk")
    public ResponseDTO addRoleForUserByBulk(@PathVariable("roleId") Long roleId,
        @PathVariable("userIds") List<Long> userIds) {
        roleDomainService.addRoleOfUserByBulk(roleId, userIds);
        return ResponseDTO.ok();
    }
}
