package com.agileboot.domain.system.dept.db;

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
 * 部门表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_dept")
@ApiModel(value = "SysDeptEntity对象", description = "部门表")
public class SysDeptEntity extends BaseEntity<SysDeptEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门id")
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @ApiModelProperty("父部门id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("祖级列表")
    @TableField("ancestors")
    private String ancestors;

    @ApiModelProperty("部门名称")
    @TableField("dept_name")
    private String deptName;

    @ApiModelProperty("显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @TableField("leader_id")
    private Long leaderId;

    @ApiModelProperty("负责人")
    @TableField("leader_name")
    private String leaderName;

    @ApiModelProperty("联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("部门状态（0正常 1停用）")
    @TableField("`status`")
    private Integer status;


    @Override
    public Serializable pkVal() {
        return this.deptId;
    }

}
