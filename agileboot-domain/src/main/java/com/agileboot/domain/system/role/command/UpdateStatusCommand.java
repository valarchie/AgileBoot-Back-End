package com.agileboot.domain.system.role.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateStatusCommand {

    private Long roleId;

    private Integer status;

}
