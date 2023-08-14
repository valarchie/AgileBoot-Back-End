package com.agileboot.domain.system.notice.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.domain.system.notice.command.NoticeAddCommand;
import com.agileboot.domain.system.notice.command.NoticeUpdateCommand;
import com.agileboot.common.enums.common.NoticeTypeEnum;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.domain.system.notice.db.SysNoticeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NoticeModel extends SysNoticeEntity {

    public NoticeModel(SysNoticeEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public void loadAddCommand(NoticeAddCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "noticeId");
        }
    }

    public void loadUpdateCommand(NoticeUpdateCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }

    public void checkFields() {
        BasicEnumUtil.fromValue(NoticeTypeEnum.class, getNoticeType());
        BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
    }

}
