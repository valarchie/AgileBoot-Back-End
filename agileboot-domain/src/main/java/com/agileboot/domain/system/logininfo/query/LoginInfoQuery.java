package com.agileboot.domain.system.logininfo.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.system.entity.SysLoginInfoEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginInfoQuery extends AbstractPageQuery<SysLoginInfoEntity> {

    private String ipaddr;
    private String status;
    private String username;


    @Override
    public QueryWrapper<SysLoginInfoEntity> toQueryWrapper() {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<SysLoginInfoEntity>()
            .like(StrUtil.isNotEmpty(ipaddr), "ip_address", ipaddr)
            .eq(StrUtil.isNotEmpty(status), "status", status)
            .like(StrUtil.isNotEmpty(username), "username", username);

        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper, "login_time");

        return queryWrapper;
    }
}
