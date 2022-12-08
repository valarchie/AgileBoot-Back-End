package com.agileboot.domain.system.user.command;

import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UpdateUserCommand extends AddUserCommand {

    private Long userId;

}
