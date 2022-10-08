package com.agileboot.domain.system.config;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysConfigEntity;
import com.agileboot.orm.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfigQuery extends AbstractPageQuery {

    private String configName;

    private String configKey;

    private Boolean isAllowChange;


    @SuppressWarnings("rawtypes")
    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysConfigEntity> sysNoticeWrapper = new QueryWrapper<>();
        sysNoticeWrapper.like(StrUtil.isNotEmpty(configName), "config_name", configName);
        sysNoticeWrapper.eq(StrUtil.isNotEmpty(configKey), "config_key", configKey);
        sysNoticeWrapper.eq(isAllowChange != null, "is_allow_change", isAllowChange);

        addSortCondition(sysNoticeWrapper);
        addTimeCondition(sysNoticeWrapper, "create_time");

        return sysNoticeWrapper;
    }
}
