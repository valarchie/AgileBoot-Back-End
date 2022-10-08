package com.agileboot.domain.system.notice;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysNoticeEntity;
import com.agileboot.orm.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NoticeQuery extends AbstractPageQuery {

    private String noticeType;

    private String noticeTitle;

    private String creatorName;


    @SuppressWarnings("rawtypes")
    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysNoticeEntity> sysNoticeWrapper = new QueryWrapper<>();
        sysNoticeWrapper.like(StrUtil.isNotEmpty(noticeTitle), "notice_title", noticeTitle)
            .eq(noticeType != null, "notice_type", noticeType)
            .like(StrUtil.isNotEmpty(creatorName), "creator_name", creatorName);

        return sysNoticeWrapper;
    }
}
