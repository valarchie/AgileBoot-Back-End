package com.agileboot.domain.common.command;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class BulkOperationCommand<T> {

    public BulkOperationCommand(List<T> idList) {
        if (CollUtil.isEmpty(idList)) {
            throw new ApiException(ErrorCode.Business.BULK_DELETE_IDS_IS_INVALID);
        }
        // 移除重复元素
        this.ids = new HashSet<>(idList);
    }

    private Collection<T> ids;

}
