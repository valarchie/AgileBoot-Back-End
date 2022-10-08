package com.agileboot.domain.system.loginInfo;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysLoginInfoEntity;
import com.agileboot.orm.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginInfoQuery extends AbstractPageQuery {

    private String ipaddr;
    private String status;
    private String username;


    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StrUtil.isNotEmpty(ipaddr), "ip_address", ipaddr)
            .eq(StrUtil.isNotEmpty(status), "status", status)
            .like(StrUtil.isNotEmpty(username), "username", username);

        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper, "login_time");

        return queryWrapper;
    }
}
