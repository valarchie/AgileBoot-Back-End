package com.agileboot.domain.system.role.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_role_menu")
@ApiModel(value = "SysRoleMenuXEntity对象", description = "角色和菜单关联表")
public class SysRoleMenuEntity extends Model<SysRoleMenuEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty("菜单ID")
    @TableField("menu_id")
    private Long menuId;


    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
