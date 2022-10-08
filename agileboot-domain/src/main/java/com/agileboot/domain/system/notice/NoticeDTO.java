package com.agileboot.domain.system.notice;

import com.agileboot.orm.entity.SysNoticeEntity;
import java.util.Date;
import lombok.Data;

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
            this.creatorName = entity.getCreatorName();
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
