package com.agileboot.domain.system.notice;

import cn.hutool.core.convert.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class NoticeAddCommand {

    @NotBlank(message = "公告标题不能为空")
    @Size(max = 50, message = "公告标题不能超过50个字符")
    protected String noticeTitle;

    protected String noticeType;

    @NotBlank
    protected String noticeContent;

    protected String status;

    public NoticeModel toModel() {
        NoticeModel model = new NoticeModel();

        model.setNoticeTitle(noticeTitle);
        model.setNoticeType(Convert.toInt(noticeType));
        model.setNoticeContent(noticeContent);
        model.setStatus(Convert.toInt(status));

        return model;
    }

}
