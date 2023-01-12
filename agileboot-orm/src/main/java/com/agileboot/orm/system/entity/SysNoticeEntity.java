package com.agileboot.orm.system.entity;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_notice")
@Schema(description = "通知公告表")
public class SysNoticeEntity extends BaseEntity<SysNoticeEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "公告ID")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    @Schema(description = "公告标题")
    @TableField("notice_title")
    private String noticeTitle;

    @Schema(description = "公告类型（1通知 2公告）")
    @TableField("notice_type")
    private Integer noticeType;

    @Schema(description = "公告内容")
    @TableField("notice_content")
    private String noticeContent;

    @Schema(description = "公告状态（1正常 0关闭）")
    @TableField("`status`")
    private Integer status;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.noticeId;
    }

}
