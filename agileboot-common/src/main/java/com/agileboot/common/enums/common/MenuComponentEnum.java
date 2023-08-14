package com.agileboot.common.enums.common;

import com.agileboot.common.enums.BasicEnum;

/**
 *
 * @author valarchie
 */
@Deprecated
public enum MenuComponentEnum implements BasicEnum<Integer> {

    /**
     * 菜单组件类型
     */
    LAYOUT(1,"Layout"),
    PARENT_VIEW(2,"ParentView"),
    INNER_LINK(3,"InnerLink");

    private final int value;
    private final String description;

    MenuComponentEnum(int value, String description) {
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
