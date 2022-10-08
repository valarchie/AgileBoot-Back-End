package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.poi.CustomExcelUtil;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.domain.system.operationLog.OperationLogDTO;
import com.agileboot.domain.system.operationLog.OperationLogDomainService;
import com.agileboot.domain.system.operationLog.OperationLogQuery;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.orm.enums.BusinessType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/operationLog")
public class SysOperationLogController extends BaseController {

    @Autowired
    private OperationLogDomainService operationLogDomainService;

    @PreAuthorize("@ss.hasPerm('monitor:operlog:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(OperationLogQuery query, HttpServletRequest request) {
        PageDTO pageDTO = operationLogDomainService.getOperationLogList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "操作日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPerm('monitor:operlog:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OperationLogQuery query) {
        PageDTO pageDTO = operationLogDomainService.getOperationLogList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), OperationLogDTO.class, response);
    }

    @AccessLog(title = "操作日志", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPerm('monitor:operlog:remove')")
    @DeleteMapping("/{operationIds}")
    public ResponseDTO remove(@PathVariable List<Long> operationIds) {
        operationLogDomainService.deleteOperationLog(new BulkOperationCommand<>(operationIds));
        return ResponseDTO.ok();
    }

    @AccessLog(title = "操作日志", businessType = BusinessType.CLEAN)
    @PreAuthorize("@ss.hasPerm('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public ResponseDTO clean() {
        return ResponseDTO.fail(ErrorCode.Business.UNSUPPORTED_OPERATION);
    }
}
