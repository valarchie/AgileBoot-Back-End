package com.agileboot.orm.enums;

import com.agileboot.orm.enums.interfaces.BasicEnum;

/**
 * @author valarchie
 * 对应 sys_menu表的menu_type字段
 */
public enum MenuTypeEnum implements BasicEnum<Integer> {

    /**
     * 菜单类型
     */
    DIRECTORY(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private final int value;
    private final String description;

    MenuTypeEnum(int value, String description) {
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
