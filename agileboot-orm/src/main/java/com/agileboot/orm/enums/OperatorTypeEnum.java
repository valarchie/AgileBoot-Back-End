package com.agileboot.orm.enums;

import com.agileboot.orm.enums.interfaces.BasicEnum;

/**
 * Http Method
 */
public enum OperatorTypeEnum implements BasicEnum<Integer> {

    /**
     * 菜单类型
     */
    OTHER(1, "其他"),
    WEB(2, "Web用户"),
    MOBILE(3, "手机端用户");

    private final int value;
    private final String description;

    OperatorTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }


}
