package com.agileboot.orm.enums.dictionary;

import com.agileboot.orm.enums.interfaces.DictionaryEnum;

/**
 * 系统内代表是与否的枚举
 * @author valarchie
 */
public enum CommonAnswerEnum implements DictionaryEnum<Integer> {
    /**
     * 是与否
     */
    YES(1, "是", CssTag.PRIMARY),
    NO(0, "否", CssTag.DANGER);

    private final int value;
    private final String description;
    private final String cssTag;

    CommonAnswerEnum(int value, String description, String cssTag) {
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

    public static CommonAnswerEnum getDefault() {
        return YES;
    }

    public static String getDictName() {
        return "sys_yes_no";
    }


}
