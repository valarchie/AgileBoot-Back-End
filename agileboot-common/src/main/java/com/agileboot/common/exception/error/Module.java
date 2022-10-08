package com.agileboot.common.exception.error;

/**
 * 系统内的模块
 */
public enum Module {

    /**
     * 普通模块
     */
    COMMON(0),

    /**
     * 权限模块
     */
    PERMISSION(1),

    LOGIN(2),

    DB(3),

    UPLOAD(4),

    USER(5),

    CONFIG(6),

    POST(7),

    DEPT(8),

    MENU(9),

    ROLE(10),


    ;


    private final int code;

    Module(int code) { this.code = code * 100; }

    public int code() {return code; }

}
