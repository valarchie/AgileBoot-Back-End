package com.agileboot.domain.system.notice.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class NoticeAddCommand {

    @NotBlank(message = "公告标题不能为空")
    @Size(max = 50, message = "公告标题不能超过50个字符")
    protected String noticeTitle;

    protected String noticeType;

    @NotBlank
    protected String noticeContent;

    protected String status;

}
