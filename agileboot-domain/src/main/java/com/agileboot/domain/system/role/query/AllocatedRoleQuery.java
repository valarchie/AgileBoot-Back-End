package com.agileboot.domain.system.role.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class AllocatedRoleQuery extends AbstractPageQuery<SysUserEntity> {

    private Long roleId;
    private String username;
    private String phoneNumber;

    @Override
    public QueryWrapper<SysUserEntity> toQueryWrapper() {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("r.role_id", roleId)
            .like(StrUtil.isNotEmpty(username), "u.username", username)
            .like(StrUtil.isNotEmpty(phoneNumber), "u.phone_number", phoneNumber);

        return queryWrapper;
    }

}
