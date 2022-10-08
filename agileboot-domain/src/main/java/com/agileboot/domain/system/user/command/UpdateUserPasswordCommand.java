package com.agileboot.domain.system.user.command;

import lombok.Data;

@Data
public class UpdateUserPasswordCommand {

    private Long userId;
    private String newPassword;
    private String oldPassword;

}
