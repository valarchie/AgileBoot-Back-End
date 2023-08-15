package com.agileboot.admin.customize.service.permission.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 * 供 DataPermissionChecker使用的 数据条件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataCondition {

    private Long targetDeptId;
    private Long targetUserId;

}
