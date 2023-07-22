package com.agileboot.domain.system.menu.command;

import com.agileboot.domain.system.menu.dto.MetaDTO;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class AddMenuCommand {

    private Long parentId;
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;
    /**
     * 路由名称 必须唯一
     */
    private String routerName;

    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    private Integer status;
    private Integer menuType;

    private Boolean isButton;

    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    private String permission;

    private MetaDTO meta;

}
