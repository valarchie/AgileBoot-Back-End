package com.agileboot.domain.common;

import cn.hutool.core.collection.CollUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import java.util.List;
import lombok.Data;

@Data
public class BulkOperationCommand<T> {

    public BulkOperationCommand(List<T> idList) {
        if (CollUtil.isEmpty(idList)) {
            throw new ApiException(ErrorCode.Business.BULK_DELETE_IDS_IS_INVALID);
        }

        this.ids = idList;
    }

    private List<T> ids;

}
