package com.agileboot.domain.system.dept.query;

import com.agileboot.common.core.page.AbstractQuery;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
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

    private Long parentId;


    @Override
    public QueryWrapper<SysDeptEntity> addQueryCondition() {
        // TODO parentId 这个似乎没使用
        return new QueryWrapper<SysDeptEntity>()
//            .eq(status != null, "status", status)
            .eq(parentId != null, "parent_id", parentId);
//            .like(StrUtil.isNotEmpty(deptName), "dept_name", deptName);
//            .and(deptId != null && isExcludeCurrentDept, o ->
//                o.ne("dept_id", deptId)
//                    .or()
//                    .apply("FIND_IN_SET (dept_id , ancestors)")
//            );
    }
}
