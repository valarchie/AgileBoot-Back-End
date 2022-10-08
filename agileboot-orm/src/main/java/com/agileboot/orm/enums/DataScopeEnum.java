package com.agileboot.orm.enums;

import com.agileboot.orm.enums.interfaces.BasicEnum;

/**
 * 对应sys_role表的data_scope字段
 * @author valarchie
 */
public enum DataScopeEnum implements BasicEnum<Integer> {

    /**
     * 数据权限范围
     */
    ALL(1, "所有数据权限"),
    SELF_DEFINE(2, "自定义数据权限"),
    CURRENT_DEPT(3, "本部门数据权限"),
    CURRENT_DEPT_AND_CHILDREN_DEPT(4, "本部门以及子孙部门数据权限"),
    ONLY_SELF(5, "仅本人数据权限");

    private final int value;
    private final String description;

    DataScopeEnum(int value, String description) {
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

    public String getDescription() {
        return description;
    }

}
