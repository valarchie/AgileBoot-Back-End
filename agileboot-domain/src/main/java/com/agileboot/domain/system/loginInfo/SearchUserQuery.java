package com.agileboot.domain.system.loginInfo;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.query.AbstractPageQuery;
import com.agileboot.orm.result.SearchUserDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

@Data
public class SearchUserQuery extends AbstractPageQuery {

    private Long userId;
    private String username;
    private Integer status;
    private String phoneNumber;
    private Long deptId;

    @Override
    public QueryWrapper<SearchUserDO> toQueryWrapper() {
        QueryWrapper<SearchUserDO> queryWrapper = new QueryWrapper<>();

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

        this.addTimeCondition(queryWrapper, "u.create_time");

        return queryWrapper;
    }
}
