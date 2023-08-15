package com.agileboot.common.enums.common;

import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;
import com.agileboot.common.enums.DictionaryEnum;

/**
 * 用户状态
 * @author valarchie
 */
// TODO 表记得改成LoginLog
@Dictionary(name = "sysLoginLog.status")
public enum LoginStatusEnum implements DictionaryEnum<Integer> {
    /**
     * status of user
     */
    LOGIN_SUCCESS(1, "登录成功", CssTag.SUCCESS),
    LOGOUT(2, "退出成功", CssTag.INFO),
    REGISTER(3, "注册", CssTag.PRIMARY),
    LOGIN_FAIL(0, "登录失败", CssTag.DANGER);

    private final int value;
    private final String msg;
    private final String cssTag;

    LoginStatusEnum(int status, String msg, String cssTag) {
        this.value = status;
        this.msg = msg;
        this.cssTag = cssTag;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return msg;
    }

    @Override
    public String cssTag() {
        return cssTag;
    }
}
