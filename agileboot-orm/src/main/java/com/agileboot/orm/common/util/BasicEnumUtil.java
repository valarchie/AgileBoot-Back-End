package com.agileboot.orm.common.util;

import cn.hutool.core.convert.Convert;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.common.interfaces.BasicEnum;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author valarchie
 */
@UtilityClass
public class BasicEnumUtil {

    public static final String UNKNOWN = "未知";

    @Nullable
    public static <T, E extends Enum<E> & BasicEnum<T>> E fromValueSafely(@NotNull Class<E> enumClass, T value) {
        E target = null;

        for (E enumConstant : enumClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.getValue(), value)) {
                target = enumConstant;
            }
        }

        return target;
    }

    @NotNull
    public static <T, E extends Enum<E> & BasicEnum<T>> E fromValue(@NotNull Class<E> enumClass, T value) {
        E target = fromValueSafely(enumClass, value);

        if (target == null) {
            throw new ApiException(ErrorCode.Internal.GET_ENUM_FAILED, enumClass.getSimpleName());
        }

        return target;
    }

    @NotNull
    public static <E extends Enum<E> & BasicEnum<Integer>> String getDescriptionByBool(
            @NotNull Class<E> enumClass, Boolean bool) {
        Integer value = Convert.toInt(bool, 0);
        return getDescriptionByValue(enumClass, value);
    }

    @NotNull
    public static <T, E extends Enum<E> & BasicEnum<T>> String getDescriptionByValue(
            @NotNull Class<E> enumClass, T value) {
        E basicEnum = fromValueSafely(enumClass, value);
        if (basicEnum != null) {
            return basicEnum.description();
        }
        return UNKNOWN;
    }

}
