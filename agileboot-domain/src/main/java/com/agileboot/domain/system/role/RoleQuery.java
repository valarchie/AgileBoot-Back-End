package com.agileboot.domain.system.role;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQuery extends AbstractPageQuery {


    private String roleName;

    private String roleKey;

    private String status;


    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(status != null, "status", status)
            .eq(StrUtil.isNotEmpty(roleKey), "role_key", roleKey)
            .like(StrUtil.isNotEmpty(roleName), "role_name", roleName);

        this.addTimeCondition(queryWrapper, "create_time");

        return queryWrapper;
    }
}
