package com.agileboot.domain.system.role.command;

import com.agileboot.common.annotation.ExcelColumn;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class AddRoleCommand {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String roleName;

    /**
     * 角色权限
     */
    @ExcelColumn(name = "角色权限")
    @NotBlank(message = "权限字符不能为空")
    @Size(max = 100, message = "权限字符长度不能超过100个字符")
    private String roleKey;

    /**
     * 角色排序
     */
    @ExcelColumn(name = "角色排序")
    @NotNull(message = "显示顺序不能为空")
    private Integer roleSort;


    private String remark;


    @ExcelColumn(name = "数据范围")
    private String dataScope;

    @PositiveOrZero
    private String status;

    @NotNull
    private List<Long> menuIds;

}
