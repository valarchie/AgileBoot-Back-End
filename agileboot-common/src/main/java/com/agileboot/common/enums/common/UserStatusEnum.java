package com.agileboot.common.enums.common;

import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;
import com.agileboot.common.enums.DictionaryEnum;

/**
 * 对应sys_user的status字段
 * @author valarchie
 */
@Dictionary(name = "sysUser.status")
public enum UserStatusEnum implements DictionaryEnum<Integer> {

    /**
     * 用户账户状态
     */
    NORMAL(1, "正常", CssTag.PRIMARY),
    DISABLED(2, "禁用", CssTag.DANGER),
    FROZEN(3, "冻结", CssTag.WARNING);

    private final int value;
    private final String description;

    private final String cssTag;

    UserStatusEnum(int value, String description, String cssTag) {
        this.value = value;
        this.description = description;
        this.cssTag = cssTag;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return this.description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String cssTag() {
        return null;
    }
}
