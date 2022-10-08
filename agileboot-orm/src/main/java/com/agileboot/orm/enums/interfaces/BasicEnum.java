package com.agileboot.orm.enums.interfaces;

/**
 * @author valarchie
 * 普通的枚举 接口
 * @param <T>
 */
public interface BasicEnum<T>{


    /**
     * @return 获取枚举的值
     */
    T getValue();

    /**
     *
     * @return 获取枚举的描述
     */
    String description();


}
