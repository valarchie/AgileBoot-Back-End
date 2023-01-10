package com.agileboot.domain.system.config.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.system.entity.SysConfigEntity;
import com.agileboot.orm.system.service.ISysConfigService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 配置模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class ConfigModelFactory {

    @NonNull
    private ISysConfigService configService;

    public ConfigModel loadById(Long configId) {
        SysConfigEntity byId = configService.getById(configId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, configId, "参数配置");
        }
        return new ConfigModel(byId, configService);
    }

    public ConfigModel create() {
        return new ConfigModel(configService);
    }


}
