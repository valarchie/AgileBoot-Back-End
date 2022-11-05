package com.agileboot.domain.system.config;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.system.config.command.ConfigUpdateCommand;
import com.agileboot.domain.system.config.dto.ConfigDTO;
import com.agileboot.domain.system.config.model.ConfigModel;
import com.agileboot.domain.system.config.model.ConfigModelFactory;
import com.agileboot.domain.system.config.query.ConfigQuery;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysConfigEntity;
import com.agileboot.orm.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class ConfigApplicationService {

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

    public void updateConfig(ConfigUpdateCommand updateCommand, LoginUser loginUser) {
        ConfigModel configModel = ConfigModelFactory.loadFromUpdateCommand(updateCommand, configService);

        configModel.checkCanBeModify();

        configModel.updateById();
    }


}
