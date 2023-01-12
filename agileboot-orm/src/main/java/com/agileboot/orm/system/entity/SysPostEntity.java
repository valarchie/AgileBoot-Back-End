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
 * 岗位信息表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_post")
@Schema(description = "岗位信息表")
public class SysPostEntity extends BaseEntity<SysPostEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "岗位ID")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    @Schema(description = "岗位编码")
    @TableField("post_code")
    private String postCode;

    @Schema(description = "岗位名称")
    @TableField("post_name")
    private String postName;

    @Schema(description = "显示顺序")
    @TableField("post_sort")
    private Integer postSort;

    @Schema(description = "状态（1正常 0停用）")
    @TableField("`status`")
    private Integer status;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.postId;
    }

}
