package com.agileboot.domain.system.notice.dto;

import com.agileboot.infrastructure.cache.CacheCenter;
import com.agileboot.orm.entity.SysNoticeEntity;
import com.agileboot.orm.entity.SysUserEntity;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class NoticeDTO {

    public NoticeDTO(SysNoticeEntity entity) {
        if (entity != null) {
            this.noticeId = entity.getNoticeId() + "";
            this.noticeTitle = entity.getNoticeTitle() + "";
            this.noticeType = entity.getNoticeType() + "";
            this.noticeContent = entity.getNoticeContent();
            this.status = entity.getStatus() + "";
            this.createTime = entity.getCreateTime();

            SysUserEntity cacheUser = CacheCenter.userCache.getObjectById(entity.getCreatorId());
            if (cacheUser != null) {
                this.creatorName = cacheUser.getUsername();
            }
        }
    }

    private String noticeId;

    private String noticeTitle;

    private String noticeType;

    private String noticeContent;

    private String status;

    private Date createTime;

    private String creatorName;

}
