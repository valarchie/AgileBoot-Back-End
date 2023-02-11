package com.agileboot.domain.system.operationlog.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.system.entity.SysOperationLogEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationLogQuery extends AbstractPageQuery<SysOperationLogEntity> {

    private String businessType;
    private String status;
    private String username;
    private String requestModule;


    @Override
    public QueryWrapper<SysOperationLogEntity> toQueryWrapper() {
        QueryWrapper<SysOperationLogEntity> queryWrapper = new QueryWrapper<SysOperationLogEntity>()
            .like(businessType!=null, "business_type", businessType)
            .eq(status != null, "status", status)
            .like(StrUtil.isNotEmpty(username), "username", username)
            .like(StrUtil.isNotEmpty(requestModule), "request_module", requestModule);

        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper, "operation_time");

        return queryWrapper;
    }
}
