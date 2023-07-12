package com.agileboot.orm.common.enums;

import com.agileboot.orm.common.annotations.Dictionary;

/**
 * 对应sys_user的status字段
 * @author valarchie
 */
@Dictionary(name = "sysUser.status")
public enum UserStatusEnum {

    /**
     * 用户账户状态
     */
    NORMAL(1, "正常"),
    DISABLED(2, "禁用"),
    FROZEN(3, "冻结");

    private final int value;
    private final String description;

    UserStatusEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
