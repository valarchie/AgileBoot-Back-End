package com.agileboot.domain.system.operationLog;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.orm.entity.SysOperationLogEntity;
import com.agileboot.orm.service.ISysOperationLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class OperationLogDomainService {

    @Autowired
    private ISysOperationLogService operationLogService;

    public PageDTO getOperationLogList(OperationLogQuery query) {
        Page<SysOperationLogEntity> page = operationLogService.page(query.toPage(), query.toQueryWrapper());
        List<OperationLogDTO> records = page.getRecords().stream().map(OperationLogDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public void deleteOperationLog(BulkOperationCommand<Long> deleteCommand) {
        operationLogService.removeBatchByIds(deleteCommand.getIds());
    }

}
