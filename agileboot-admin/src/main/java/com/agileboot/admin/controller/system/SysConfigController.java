package com.agileboot.admin.controller.system;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.system.config.ConfigDTO;
import com.agileboot.domain.system.config.ConfigDomainService;
import com.agileboot.domain.system.config.ConfigQuery;
import com.agileboot.domain.system.config.ConfigUpdateCommand;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.cache.guava.GuavaCacheService;
import com.agileboot.infrastructure.cache.map.MapCache;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.enums.BusinessType;
import com.agileboot.orm.result.DictionaryData;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数配置 信息操作处理
 * @author valarchie
 */
@RestController
@RequestMapping("/system/config")
@Validated
public class SysConfigController extends BaseController {

    @Autowired
    private ConfigDomainService configDomainService;

    @Autowired
    private GuavaCacheService guavaCacheService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@ss.hasPerm('system:config:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(ConfigQuery query) {
        PageDTO page = configDomainService.getConfigList(query);
        return ResponseDTO.ok(page);
    }

    /**
     * 根据字典类型查询字典数据信息
     * 换成用Enum
     */
    @GetMapping(value = "/dict/{dictType}")
    public ResponseDTO<List<DictionaryData>> dictType(@PathVariable String dictType) {
        List<DictionaryData> dictionaryData = MapCache.dictionaryCache().get(dictType);
        return ResponseDTO.ok(dictionaryData);
    }


    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:config:query')")
    @GetMapping(value = "/{configId}")
    public ResponseDTO<ConfigDTO> getInfo(@NotNull @Positive @PathVariable Long configId) {
        ConfigDTO config = configDomainService.getConfigInfo(configId);
        return ResponseDTO.ok(config);
    }


    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPerm('system:config:edit')")
    @AccessLog(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseDTO edit(@RequestBody ConfigUpdateCommand config) {
        configDomainService.updateConfig(config, AuthenticationUtils.getLoginUser());
        return ResponseDTO.ok();
    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("@ss.hasPerm('system:config:remove')")
    @AccessLog(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public ResponseDTO<?> refreshCache() {
        guavaCacheService.configCache.invalidateAll();
        return ResponseDTO.ok();
    }
}
