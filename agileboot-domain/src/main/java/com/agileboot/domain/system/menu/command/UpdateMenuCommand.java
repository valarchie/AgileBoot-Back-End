package com.agileboot.domain.system.menu.command;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UpdateMenuCommand extends AddMenuCommand {

    @NotNull
    private Long menuId;

}
