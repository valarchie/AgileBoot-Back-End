package com.agileboot.domain.system.config;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysConfigEntity;
import com.agileboot.orm.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author valarchie
 */
@Service
public class ConfigDomainService {

    @Autowired
    private ISysConfigService configService;

    public PageDTO getConfigList(ConfigQuery query) {
        Page<SysConfigEntity> page = configService.page(query.toPage(), query.toQueryWrapper());
        List<ConfigDTO> records = page.getRecords().stream().map(ConfigDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }


    public ConfigDTO getConfigInfo(Long id) {
        SysConfigEntity byId = configService.getById(id);
        return new ConfigDTO(byId);
    }

    @Transactional
    public void updateConfig(ConfigUpdateCommand updateCommand, LoginUser loginUser) {
        ConfigModel configModel = getConfigModel(updateCommand.getConfigId());

        configModel.setConfigValue(updateCommand.getConfigValue());
        configModel.checkCanBeEdit();

        configModel.logUpdater(loginUser);
        configModel.updateById();
    }

    public ConfigModel getConfigModel(Long id) {
        SysConfigEntity byId = configService.getById(id);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, id, "参数配置");
        }

        return new ConfigModel(byId);
    }

}
