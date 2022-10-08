package com.agileboot.domain.system.notice;

import cn.hutool.core.convert.Convert;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class NoticeUpdateCommand extends NoticeAddCommand{

    @NotNull
    @Positive
    protected String noticeId;


    @Override
    public NoticeModel toModel() {
        NoticeModel noticeModel = super.toModel();
        noticeModel.setNoticeId(Convert.toInt(noticeId));
        return noticeModel;
    }

}
