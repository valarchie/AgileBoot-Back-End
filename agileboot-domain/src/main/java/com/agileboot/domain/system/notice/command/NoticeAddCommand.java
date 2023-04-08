package com.agileboot.domain.system.notice.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
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

    /**
     * 想要支持富文本的话, 避免Xss过滤的话， 请加上@JsonDeserialize(using = StringDeserializer.class) 注解
     */
    @NotBlank
    @JsonDeserialize(using = StringDeserializer.class)
    protected String noticeContent;

    protected String status;

}
