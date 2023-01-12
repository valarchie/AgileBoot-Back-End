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
 * 菜单权限表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_menu")
@Schema(description = "菜单权限表")
public class SysMenuEntity extends BaseEntity<SysMenuEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @Schema(description = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @Schema(description = "父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @Schema(description = "路由地址")
    @TableField("path")
    private String path;

    @Schema(description = "组件路径")
    @TableField("component")
    private String component;

    @Schema(description = "路由参数")
    @TableField("`query`")
    private String query;

    @Schema(description = "是否为外链（1是 0否）")
    @TableField("is_external")
    private Boolean isExternal;

    @Schema(description = "是否缓存（1缓存 0不缓存）")
    @TableField("is_cache")
    private Boolean isCache;

    @Schema(description = "菜单类型（M=1目录 C=2菜单 F=3按钮）")
    @TableField("menu_type")
    private Integer menuType;

    @Schema(description = "菜单状态（1显示 0隐藏）")
    @TableField("is_visible")
    private Boolean isVisible;

    @Schema(description = "菜单状态（0正常 1停用）")
    @TableField("`status`")
    private Integer status;

    @Schema(description = "权限标识")
    @TableField("perms")
    private String perms;

    @Schema(description = "菜单图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
