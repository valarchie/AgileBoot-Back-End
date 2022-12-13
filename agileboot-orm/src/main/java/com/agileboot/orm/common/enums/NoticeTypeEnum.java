package com.agileboot.orm.common.enums;

import com.agileboot.orm.common.CssTag;
import com.agileboot.orm.common.annotations.Dictionary;
import com.agileboot.orm.common.interfaces.DictionaryEnum;

/**
 * 对应sys_notice的 notice_type字段
 * @author valarchie
 */
@Dictionary(name = "sys_notice_type")
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

}
