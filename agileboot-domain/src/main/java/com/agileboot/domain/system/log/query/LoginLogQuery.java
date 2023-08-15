package com.agileboot.domain.system.log.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.system.log.db.SysLoginInfoEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginLogQuery extends AbstractPageQuery<SysLoginInfoEntity> {

    private String ipAddress;
    private String status;
    private String username;


    @Override
    public QueryWrapper<SysLoginInfoEntity> addQueryCondition() {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<SysLoginInfoEntity>()
            .like(StrUtil.isNotEmpty(ipAddress), "ip_address", ipAddress)
            .eq(StrUtil.isNotEmpty(status), "status", status)
            .like(StrUtil.isNotEmpty(username), "username", username);

        addSortCondition(queryWrapper);

        // 可以手动设置  也可以由前端传回
//        this.timeRangeColumn = "login_time";
//        addTimeCondition(queryWrapper, "login_time");

        return queryWrapper;
    }
}
