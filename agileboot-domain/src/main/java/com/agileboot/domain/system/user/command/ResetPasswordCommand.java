package com.agileboot.domain.system.user.command;

import lombok.Data;

@Data
public class ResetPasswordCommand {

    private Long userId;
    private String password;

}
