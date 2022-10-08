package com.agileboot.orm.enums.dictionary;

import com.agileboot.orm.enums.interfaces.DictionaryEnum;

/**
 * 对应sys_notice的 notice_type字段
 * @author valarchie
 */
public enum NoticeTypeEnum implements DictionaryEnum<Integer> {

    /**
     * 通知类型
     */
    NOTIFICATION(1, "通知", CssTag.WARNING),
    ANNOUNCEMENT(2, "公告", CssTag.SUCCESS);

    private final int value;
    private final String description;
    private final String cssTag;

    NoticeTypeEnum(int value, String description, String cssTag) {
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

    public static NoticeTypeEnum getDefault() {
        return NOTIFICATION;
    }

    public static String getDictName() {
        return "sys_notice_type";
    }
}
