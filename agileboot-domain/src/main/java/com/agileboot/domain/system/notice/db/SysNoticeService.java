package com.agileboot.domain.system.notice.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 通知公告表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysNoticeService extends IService<SysNoticeEntity> {

    /**
     * 获取公告列表
     *
     * @param page         页码对象
     * @param queryWrapper 查询对象
     * @return 分页处理后的公告列表
     */
    Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page,
        @Param(Constants.WRAPPER) Wrapper<SysNoticeEntity> queryWrapper);

}
