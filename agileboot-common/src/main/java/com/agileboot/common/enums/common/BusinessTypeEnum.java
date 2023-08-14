package com.agileboot.common.enums.common;

import com.agileboot.common.enums.dictionary.CssTag;
import com.agileboot.common.enums.dictionary.Dictionary;
import com.agileboot.common.enums.DictionaryEnum;

/**
 * 对应sys_operation_log的business_type
 *
 * @author valarchie
 */
@Dictionary(name = "sysOperationLog.businessType")
public enum BusinessTypeEnum implements DictionaryEnum<Integer> {

    /**
     * 操作类型
     */
    OTHER(0, "其他操作", CssTag.INFO),
    ADD(1, "添加", CssTag.PRIMARY),
    MODIFY(2, "修改", CssTag.PRIMARY),
    DELETE(3, "删除", CssTag.DANGER),
    GRANT(4, "授权", CssTag.PRIMARY),
    EXPORT(5, "导出", CssTag.WARNING),
    IMPORT(6, "导入", CssTag.WARNING),
    FORCE_LOGOUT(7, "强退", CssTag.DANGER),
    CLEAN(8, "清空", CssTag.DANGER),
    ;

    private final int value;
    private final String description;
    private final String cssTag;

    BusinessTypeEnum(int value, String description, String cssTag) {
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
