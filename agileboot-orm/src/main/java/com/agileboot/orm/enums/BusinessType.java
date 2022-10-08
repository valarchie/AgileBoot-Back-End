package com.agileboot.orm.enums;

import com.agileboot.orm.enums.interfaces.BasicEnum;

/**
 * 业务操作类型
 *
 * @author valarchie
 */
public enum BusinessType implements BasicEnum<Integer> {
    /**
     * 其它
     */
    OTHER(0, "其他操作"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 授权
     */
    GRANT(4, "授权"),

    /**
     * 导出
     */
    EXPORT(5, "导出"),

    /**
     * 导入
     */
    IMPORT(6, "导入"),

    /**
     * 强退
     */
    FORCE(7,"强退"),

    /**
     * 清空数据
     */
    CLEAN(8, "清空"),
    ;

    private final int value;
    private final String description;

    BusinessType(int value, String description) {
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
