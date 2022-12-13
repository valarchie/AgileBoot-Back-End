package com.agileboot.orm.common.enums;

import com.agileboot.orm.common.CssTag;
import com.agileboot.orm.common.annotations.Dictionary;
import com.agileboot.orm.common.interfaces.DictionaryEnum;

/**
 * 对应sys_user的sex字段
 *
 * @author valarchie
 */
@Dictionary(name = "sys_user_sex")
public enum GenderEnum implements DictionaryEnum<Integer> {

    /**
     * 用户性别
     */
    MALE(1, "男", CssTag.NONE),
    FEMALE(2, "女", CssTag.NONE),
    UNKNOWN(0, "未知", CssTag.NONE);

    private final int value;
    private final String description;
    private final String cssTag;

    GenderEnum(int value, String description, String cssTag) {
        this.value = value;
        this.description = description;
        this.cssTag = cssTag;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String cssTag() {
        return cssTag;
    }

}
