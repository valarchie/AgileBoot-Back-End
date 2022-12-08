package com.agileboot.domain.system.role.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UpdateRoleCommand extends AddRoleCommand {

    @NotNull
    @PositiveOrZero
    private Long roleId;

}
