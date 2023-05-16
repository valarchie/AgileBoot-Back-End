package com.agileboot.infrastructure.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解防止表单重复提交
 * 仅生效于有RequestBody注解的参数  因为使用RequestBodyAdvice来实现
 * @author valarchie
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unrepeatable {

    /**
     * 间隔时间(s)，小于此时间视为重复提交
     */
    int interval() default 5;

}
