package com.agileboot.infrastructure.web.domain.login;

import lombok.Data;

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

}
