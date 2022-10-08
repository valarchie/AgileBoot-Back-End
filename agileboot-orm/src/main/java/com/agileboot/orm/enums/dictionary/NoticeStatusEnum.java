package com.agileboot.orm.enums.dictionary;

import com.agileboot.orm.enums.interfaces.DictionaryEnum;

/**
 * 对应sys_notice的 status字段
 * @author valarchie
 */
public enum NoticeStatusEnum implements DictionaryEnum<Integer> {

    /**
     * 通知状态
     */
    OPEN(1, "正常", CssTag.PRIMARY),
    CLOSE(0, "关闭", CssTag.DANGER);

    private final int value;
    private final String description;
    private final String cssTag;

    NoticeStatusEnum(int value, String description, String cssTag) {
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

    public static NoticeStatusEnum getDefault() {
        return OPEN;
    }

    public static String getDictName() {
        return "sys_notice_status";
    }
}
