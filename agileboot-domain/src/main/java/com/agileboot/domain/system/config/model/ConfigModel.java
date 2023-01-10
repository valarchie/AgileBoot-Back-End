package com.agileboot.domain.system.config.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.config.command.ConfigUpdateCommand;
import com.agileboot.orm.system.entity.SysConfigEntity;
import com.agileboot.orm.system.service.ISysConfigService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class ConfigModel extends SysConfigEntity {

    private ISysConfigService configService;

    private Set<String> configOptionSet;

    public ConfigModel(ISysConfigService configService) {
        this.configService = configService;
    }

    public ConfigModel(SysConfigEntity entity, ISysConfigService configService) {
        BeanUtil.copyProperties(entity, this);

        List<String> options =
            JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                String.class) : ListUtil.empty();

        this.configOptionSet = new HashSet<>(options);

        this.configService = configService;
    }

    public void loadUpdateCommand(ConfigUpdateCommand updateCommand) {
        this.setConfigValue(updateCommand.getConfigValue());
    }


    public void checkCanBeModify() {
        if (StrUtil.isBlank(getConfigValue())) {
            throw new ApiException(ErrorCode.Business.CONFIG_VALUE_IS_NOT_ALLOW_TO_EMPTY);
        }

        if (!configOptionSet.isEmpty() && !configOptionSet.contains(getConfigValue())) {
            throw new ApiException(ErrorCode.Business.CONFIG_VALUE_IS_NOT_IN_OPTIONS);
        }
    }

}
