package com.agileboot.admin.controller.system;


import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.domain.system.loginInfo.LoginInfoDTO;
import com.agileboot.domain.system.loginInfo.LoginInfoDomainService;
import com.agileboot.domain.system.loginInfo.LoginInfoQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统访问记录
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/loginInfo")
@Validated
public class SysLoginInfoController extends BaseController {

    @Autowired
    private LoginInfoDomainService loginInfoDomainService;

    @PreAuthorize("@ss.hasPerm('monitor:logininfor:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(LoginInfoQuery query) {
        PageDTO pageDTO = loginInfoDomainService.getLoginInfoList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "登录日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPerm('monitor:logininfor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, LoginInfoQuery query) {
        PageDTO pageDTO = loginInfoDomainService.getLoginInfoList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), LoginInfoDTO.class, response);
    }

    @PreAuthorize("@ss.hasPerm('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public ResponseDTO remove(@PathVariable @NotNull @NotEmpty List<Long> infoIds) {
        loginInfoDomainService.deleteLoginInfo(new BulkOperationCommand<>(infoIds));
        return ResponseDTO.ok();
    }

    @PreAuthorize("@ss.hasPerm('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public ResponseDTO clean() {
        return ResponseDTO.fail(ErrorCode.Business.UNSUPPORTED_OPERATION);
    }
}
