package com.agileboot.common.core.base;

import lombok.Data;

@Data
public class BaseUser {

    private Long userId;
    private String username;
    private Long deptId;
    private Long roleId;

}
