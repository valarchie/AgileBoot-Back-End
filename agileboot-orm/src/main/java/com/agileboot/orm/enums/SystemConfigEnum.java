package com.agileboot.orm.enums;

import com.agileboot.orm.enums.interfaces.BasicEnum;

/**
 * 系统配置
 * @author valarchie
 * 对应 sys_config表的config_key字段
 */
public enum SystemConfigEnum implements BasicEnum<String> {

    /**
     * 菜单类型
     */
    SKIN_THEME("sys.index.skinName", "系统皮肤主题"),
    INIT_PASSWORD("sys.user.initPassword", "初始密码"),
    SIDE_BAR_THEME("sys.index.sideTheme", "侧边栏开关"),
    CAPTCHA("sys.account.captchaOnOff", "验证码开关"),
    REGISTER("sys.account.registerUser", "注册开放功能");

    private final String value;
    private final String description;

    SystemConfigEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }


}
