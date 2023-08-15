package com.agileboot.domain.system.user.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 当出现复用Query的情况，我们需要把泛型加到类本身，通过传入类型 来进行复用
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchUserQuery<T> extends AbstractPageQuery<T> {

    protected Long userId;
    protected String username;
    protected Integer status;
    protected String phoneNumber;
    protected Long deptId;

    @Override
    public QueryWrapper<T> addQueryCondition() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StrUtil.isNotEmpty(username), "username", username)
            .like(StrUtil.isNotEmpty(phoneNumber), "u.phone_number", phoneNumber)
            .eq(userId != null, "u.user_id", userId)
            .eq(status != null, "u.status", status)
            .eq("u.deleted", 0)
            .and(deptId != null, o ->
                o.eq("u.dept_id", deptId)
                    .or()
                    .apply("u.dept_id IN ( SELECT t.dept_id FROM sys_dept t WHERE find_in_set(" + deptId
                        + ", ancestors))"));

        // 设置排序字段
        this.timeRangeColumn = "u.create_time";

        return queryWrapper;
    }
}
