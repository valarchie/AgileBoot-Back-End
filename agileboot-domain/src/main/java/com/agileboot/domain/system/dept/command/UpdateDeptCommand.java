package com.agileboot.domain.system.dept.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UpdateDeptCommand extends AddDeptCommand {

    @NotNull
    @PositiveOrZero
    private Long deptId;

    @PositiveOrZero
    private String status;


}
