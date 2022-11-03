package com.agileboot.domain.system.user.command;

import lombok.Data;

@Data
public class UpdateProfileCommand {

    private Long userId;

    private Integer sex;
    private String nickName;
    private String phoneNumber;
    private String email;

}
