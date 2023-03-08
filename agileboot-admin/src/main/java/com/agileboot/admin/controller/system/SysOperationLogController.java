package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.operationlog.OperationLogApplicationService;
import com.agileboot.domain.system.operationlog.dto.OperationLogDTO;
import com.agileboot.domain.system.operationlog.query.OperationLogQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.orm.common.enums.BusinessTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志记录
 *
 * @author valarchie
 */
@Tag(name = "操作日志API", description = "操作日志相关接口")
@RestController
@RequestMapping("/operationLog")
@RequiredArgsConstructor
public class SysOperationLogController extends BaseController {

    @NonNull
    private OperationLogApplicationService operationLogApplicationService;

    @Operation(summary = "操作日志列表")
    @PreAuthorize("@permission.has('monitor:operlog:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<OperationLogDTO>> list(OperationLogQuery query) {
        PageDTO<OperationLogDTO> pageDTO = operationLogApplicationService.getOperationLogList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @Operation(summary = "操作日志导出")
    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:operlog:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OperationLogQuery query) {
        PageDTO<OperationLogDTO> pageDTO = operationLogApplicationService.getOperationLogList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), OperationLogDTO.class, response);
    }

    @Operation(summary = "删除操作日志")
    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("@permission.has('monitor:operlog:remove')")
    @DeleteMapping("/{operationIds}")
    public ResponseDTO<Void> remove(@PathVariable List<Long> operationIds) {
        operationLogApplicationService.deleteOperationLog(new BulkOperationCommand<>(operationIds));
        return ResponseDTO.ok();
    }

    @Operation(summary = "清空操作日志", description = "暂未支持")
    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.CLEAN)
    @PreAuthorize("@permission.has('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public ResponseDTO<Void> clean() {
        return ResponseDTO.fail(ErrorCode.Business.UNSUPPORTED_OPERATION);
    }
}
