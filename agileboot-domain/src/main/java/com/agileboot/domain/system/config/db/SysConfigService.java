package com.agileboot.domain.system.config.db;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-09
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 通过key获取配置
     *
     * @param key 配置对应的key
     * @return 配置
     */
    String getConfigValueByKey(String key);

}
