package com.agileboot.domain.system.role;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class AllocatedRoleQuery extends AbstractPageQuery {

    private Long roleId;
    private String username;
    private String phoneNumber;

    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("r.role_id", roleId).like(StrUtil.isNotEmpty(username),"u.username", username)
            .like(StrUtil.isNotEmpty(phoneNumber), "u.phone_number", phoneNumber);

        return queryWrapper;
    }
}
