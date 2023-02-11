package com.agileboot.domain.system.config.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.system.entity.SysConfigEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(name = "配置查询参数")
public class ConfigQuery extends AbstractPageQuery<SysConfigEntity> {

    @Schema(name = "配置名称")
    private String configName;

    @Schema(name = "配置key")
    private String configKey;

    @Schema(name = "是否允许更改配置")
    private Boolean isAllowChange;


    @Override
    public QueryWrapper<SysConfigEntity> toQueryWrapper() {
        QueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<SysConfigEntity>()
        .like(StrUtil.isNotEmpty(configName), "config_name", configName)
        .eq(StrUtil.isNotEmpty(configKey), "config_key", configKey)
        .eq(isAllowChange != null, "is_allow_change", isAllowChange);

        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper, "create_time");

        return queryWrapper;
    }
}
