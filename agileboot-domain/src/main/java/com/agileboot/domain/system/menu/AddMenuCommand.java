package com.agileboot.domain.system.menu;

import cn.hutool.core.bean.BeanUtil;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AddMenuCommand {

    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    private Long parentId;

    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;

    private Integer isExternal;

    private Integer isCache;

    private Integer menuType;

    private Integer isVisible;

    private Integer status;

    private String query;

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    private String icon;

    public MenuModel toModel() {
        MenuModel model = new MenuModel();
        BeanUtil.copyProperties(this, model);
        return model;
    }

}
