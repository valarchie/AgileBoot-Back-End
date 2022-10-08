package com.agileboot.domain.system.user.command;

import lombok.Data;

@Data
public class ChangeStatusCommand {

    private Long userId;
    private String status;

}
