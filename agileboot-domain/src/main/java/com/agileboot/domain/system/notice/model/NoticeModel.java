package com.agileboot.domain.system.notice.model;

import com.agileboot.orm.entity.SysNoticeEntity;
import com.agileboot.orm.enums.dictionary.CommonStatusEnum;
import com.agileboot.orm.enums.dictionary.NoticeTypeEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import lombok.Data;

@Data
public class NoticeModel extends SysNoticeEntity {

    public void checkFields() {
        BasicEnumUtil.fromValue(NoticeTypeEnum.class, getNoticeType());
        BasicEnumUtil.fromValue(CommonStatusEnum.class, getStatus());
    }

}
