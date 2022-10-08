package com.agileboot.orm.enums.dictionary;

import com.agileboot.orm.enums.interfaces.DictionaryEnum;

/**
 * 对应sys_operation_log的status字段
 * @author valarchie
 */
public enum OperationStatusEnum implements DictionaryEnum<Integer> {

    /**
     * 操作状态
     */
    SUCCESS(1, "成功", CssTag.PRIMARY),
    FAIL(0, "失败", CssTag.DANGER);

    private final int value;
    private final String description;
    private final String cssTag;

    OperationStatusEnum(int value, String description, String cssTag) {
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

    public static String getDictName() {
        return "sys_common_status";
    }

}
