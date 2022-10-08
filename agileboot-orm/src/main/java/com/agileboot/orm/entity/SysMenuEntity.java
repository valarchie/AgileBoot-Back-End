package com.agileboot.orm.entity;

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

    @ApiModelProperty("父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("路由参数")
    @TableField("`query`")
    private String query;

    @ApiModelProperty("是否为外链（1是 0否）")
    @TableField("is_external")
    private Boolean isExternal;

    @ApiModelProperty("是否缓存（1缓存 0不缓存）")
    @TableField("is_cache")
    private Boolean isCache;

    @ApiModelProperty("菜单类型（M=1目录 C=2菜单 F=3按钮）")
    @TableField("menu_type")
    private Integer menuType;

    @ApiModelProperty("菜单状态（1显示 0隐藏）")
    @TableField("is_visible")
    private Boolean isVisible;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    @TableField("`status`")
    private Integer status;

    @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty("菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
