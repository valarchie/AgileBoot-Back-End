package com.agileboot.domain.system.notice.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 通知公告表 Mapper 接口
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysNoticeMapper extends BaseMapper<SysNoticeEntity> {

    /**
     * 根据条件分页查询角色关联的用户列表
     *
     * @param page         分页对象
     * @param queryWrapper 条件选择器
     * @return 分页处理后的公告列表
     */
    @Select("SELECT n.* "
        + "FROM sys_notice n "
        + "LEFT JOIN sys_user u ON n.creator_id = u.user_id"
        + " ${ew.customSqlSegment}")
    Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page,
        @Param(Constants.WRAPPER) Wrapper<SysNoticeEntity> queryWrapper);

}
