package com.agileboot.common.exception.error;

/**
 * 系统内的模块
 * @author valarchie
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

    /**
     * 登录模块
     */
    LOGIN(2),

    /**
     * 数据库模块
     */
    DB(3),

    /**
     * 上传
     */
    UPLOAD(4),

    /**
     * 用户
     */
    USER(5),

    /**
     * 配置
     */
    CONFIG(6),

    /**
     * 职位
     */
    POST(7),

    /**
     * 部门
     */
    DEPT(8),

    /**
     * 菜单
     */
    MENU(9),

    /**
     * 角色
     */
    ROLE(10),

    ;


    private final int code;

    Module(int code) { this.code = code * 100; }

    public int code() {return code; }

}
