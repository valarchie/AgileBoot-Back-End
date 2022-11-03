package com.agileboot.domain.system.notice.command;

import cn.hutool.core.convert.Convert;
import com.agileboot.domain.system.notice.command.NoticeAddCommand;
import com.agileboot.domain.system.notice.model.NoticeModel;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class NoticeUpdateCommand extends NoticeAddCommand {

    @NotNull
    @Positive
    protected Long noticeId;


    @Override
    public NoticeModel toModel() {
        NoticeModel noticeModel = super.toModel();
        noticeModel.setNoticeId(Convert.toInt(noticeId));
        return noticeModel;
    }

}
