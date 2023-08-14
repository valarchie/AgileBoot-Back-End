package com.agileboot.domain.system.post.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

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
@ApiModel(value = "SysPostEntity对象", description = "岗位信息表")
public class SysPostEntity extends BaseEntity<SysPostEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("岗位ID")
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    @ApiModelProperty("岗位编码")
    @TableField("post_code")
    private String postCode;

    @ApiModelProperty("岗位名称")
    @TableField("post_name")
    private String postName;

    @ApiModelProperty("显示顺序")
    @TableField("post_sort")
    private Integer postSort;

    @ApiModelProperty("状态（1正常 0停用）")
    @TableField("`status`")
    private Integer status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.postId;
    }

}
