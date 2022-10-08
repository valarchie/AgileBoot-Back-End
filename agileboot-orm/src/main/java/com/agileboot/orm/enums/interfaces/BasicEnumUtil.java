package com.agileboot.orm.enums.interfaces;

import cn.hutool.core.convert.Convert;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import java.util.Objects;

public class BasicEnumUtil {

    public static String UNKNOWN = "未知";

    public static <E extends Enum<E>> E fromValueSafely(Class<E> enumClass, Object value) {
        E target = null;

        for (Object enumConstant : enumClass.getEnumConstants()) {
            BasicEnum basicEnum = (BasicEnum) enumConstant;
            if (Objects.equals(basicEnum.getValue(), value)) {
                target = (E) basicEnum;
            }
        }

        return target;
    }

    public static <E extends Enum<E>> E fromValue(Class<E> enumClass, Object value) {
        E target = null;

        for (Object enumConstant : enumClass.getEnumConstants()) {
            BasicEnum basicEnum = (BasicEnum) enumConstant;
            if (Objects.equals(basicEnum.getValue(), value)) {
                target = (E) basicEnum;
            }
        }

        if (target == null) {
            throw new ApiException(ErrorCode.Internal.GET_ENUM_FAILED, enumClass.getSimpleName());
        }

        return target;
    }

    public static <E extends Enum<E>> String getDescriptionByBool(Class<E> enumClass, Boolean bool) {
        Integer value = Convert.toInt(bool, 0);
        return getDescriptionByValue(enumClass, value);
    }

    public static <E extends Enum<E>> String getDescriptionByValue(Class<E> enumClass, Object value) {
        E basicEnum = fromValueSafely(enumClass, value);
        if (basicEnum != null) {
            return ((BasicEnum) basicEnum).description();
        }
        return UNKNOWN;
    }

}
