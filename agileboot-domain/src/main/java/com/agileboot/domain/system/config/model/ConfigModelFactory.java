package com.agileboot.domain.system.config.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.config.command.ConfigUpdateCommand;
import com.agileboot.orm.entity.SysConfigEntity;
import com.agileboot.orm.service.ISysConfigService;

/**
 * 配置模型工厂
 */
public class ConfigModelFactory {

    public static ConfigModel loadFromDb(Long configId, ISysConfigService configService) {
        SysConfigEntity byId = configService.getById(configId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, configId, "参数配置");
        }
        return new ConfigModel(byId);
    }

    public static ConfigModel loadFromUpdateCommand(ConfigUpdateCommand updateCommand, ISysConfigService configService) {
        ConfigModel configModel = loadFromDb(updateCommand.getConfigId(), configService);
        configModel.setConfigValue(updateCommand.getConfigValue());
        return configModel;
    }


}
