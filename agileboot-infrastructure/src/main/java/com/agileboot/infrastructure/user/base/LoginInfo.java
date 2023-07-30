package com.agileboot.infrastructure.user.base;

import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class LoginInfo {

    /**
     * 登录IP地址
     */
    private String ipAddress;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String operationSystem;

    /**
     * 登录时间
     */
    private Long loginTime;

}
