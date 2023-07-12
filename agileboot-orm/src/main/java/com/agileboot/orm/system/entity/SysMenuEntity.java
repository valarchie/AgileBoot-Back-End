package com.agileboot.orm.system.entity;

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
 * 菜单权限表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "SysMenuEntity对象", description = "菜单权限表")
public class SysMenuEntity extends BaseEntity<SysMenuEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty("菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("路由名称")
    @TableField("route_name")
    private String routeName;

    @ApiModelProperty("重定向地址")
    @TableField("redirect")
    private String redirect;

    @ApiModelProperty("父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    @TableField("`rank`")
    private Integer rank;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("路由参数")
    @TableField("`query`")
    private String query;

    @ApiModelProperty("路由元信息主要由前端处理")
    @TableField("meta_info")
    private String metaInfo;

    @ApiModelProperty("是否按钮")
    @TableField("is_button")
    private Boolean isButton;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    @TableField("`status`")
    private Integer status;

    @ApiModelProperty("权限标识")
    @TableField("permission")
    private String permission;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
