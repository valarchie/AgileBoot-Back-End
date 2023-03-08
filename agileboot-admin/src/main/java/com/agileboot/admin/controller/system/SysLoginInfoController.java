package com.agileboot.admin.controller.system;


import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.logininfo.LoginInfoApplicationService;
import com.agileboot.domain.system.logininfo.dto.LoginInfoDTO;
import com.agileboot.domain.system.logininfo.query.LoginInfoQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.orm.common.enums.BusinessTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "登录日志API", description = "登录日志相关API")
@RestController
@RequestMapping("/loginInfo")
@Validated
@RequiredArgsConstructor
public class SysLoginInfoController extends BaseController {

    @NonNull
    private LoginInfoApplicationService loginInfoApplicationService;

    @Operation(summary = "日志列表")
    @PreAuthorize("@permission.has('monitor:logininfor:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<LoginInfoDTO>> list(LoginInfoQuery query) {
        PageDTO<LoginInfoDTO> pageDTO = loginInfoApplicationService.getLoginInfoList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @Operation(summary = "日志列表导出", description = "将登录日志导出到excel")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:logininfor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, LoginInfoQuery query) {
        PageDTO<LoginInfoDTO> pageDTO = loginInfoApplicationService.getLoginInfoList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), LoginInfoDTO.class, response);
    }

    @Operation(summary = "删除登录日志")
    @PreAuthorize("@permission.has('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{infoIds}")
    public ResponseDTO<Void> remove(@PathVariable @NotNull @NotEmpty List<Long> infoIds) {
        loginInfoApplicationService.deleteLoginInfo(new BulkOperationCommand<>(infoIds));
        return ResponseDTO.ok();
    }

    @Operation(summary = "清空登录日志", description = "暂时不支持")
    @PreAuthorize("@permission.has('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.CLEAN)
    @DeleteMapping("/clean")
    public ResponseDTO<Void> clean() {
        return ResponseDTO.fail(ErrorCode.Business.UNSUPPORTED_OPERATION);
    }
}
