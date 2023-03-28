package com.agileboot.domain.system.dept.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.common.query.AbstractQuery;
import com.agileboot.orm.system.entity.SysDeptEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DeptQuery extends AbstractQuery<SysDeptEntity> {

    private Long deptId;

    private Integer status;

    private Long parentId;

    private String deptName;

    @Override
    public QueryWrapper<SysDeptEntity> toQueryWrapper() {

        return new QueryWrapper<SysDeptEntity>()
            .eq(status != null, "status", status)
            .eq(parentId != null, "parent_id", parentId)
            .like(StrUtil.isNotEmpty(deptName), "dept_name", deptName);
//            .and(deptId != null && isExcludeCurrentDept, o ->
//                o.ne("dept_id", deptId)
//                    .or()
//                    .apply("FIND_IN_SET (dept_id , ancestors)")
//            );
    }
}
