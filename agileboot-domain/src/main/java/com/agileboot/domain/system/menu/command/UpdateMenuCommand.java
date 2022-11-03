package com.agileboot.domain.system.menu.command;

import com.agileboot.domain.system.menu.model.MenuModel;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMenuCommand extends AddMenuCommand {

    @NotNull
    private Long menuId;

}
