package com.agileboot.domain.system.role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UpdateRoleCommand extends AddRoleCommand{

    @NotNull
    @PositiveOrZero
    private Long roleId;

    @Override
    public RoleModel toModel() {
        RoleModel roleModel = super.toModel();
        roleModel.setRoleId(this.roleId);
        return roleModel;
    }

}
