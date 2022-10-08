package com.agileboot.orm.service.impl;

import com.agileboot.orm.entity.SysLoginInfoEntity;
import com.agileboot.orm.mapper.SysLoginInfoMapper;
import com.agileboot.orm.service.ISysLoginInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-07-10
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfoEntity> implements
    ISysLoginInfoService {

}
