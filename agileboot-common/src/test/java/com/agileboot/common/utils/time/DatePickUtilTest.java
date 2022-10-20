package com.agileboot.common.utils.time;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatePickUtilTest {

    @Test
    void testGetBeginOfTheDay() {
        Date beginOfTheDay = DatePickUtil.getBeginOfTheDay(new Date());

        Calendar instance = Calendar.getInstance();
        instance.setTime(beginOfTheDay);

        Assertions.assertEquals(0, instance.get(Calendar.HOUR));
        Assertions.assertEquals(0, instance.get(Calendar.MINUTE));
        Assertions.assertEquals(0, instance.get(Calendar.SECOND));
    }

    @Test
    void testGetBeginOfTheDayWhenNull() {
        Assertions.assertDoesNotThrow(() -> DatePickUtil.getBeginOfTheDay(null)
        );
    }

    @Test
    void testGetEndOfTheDay() {
        Date endOfTheDay = DatePickUtil.getEndOfTheDay(new Date());

        Calendar instance = Calendar.getInstance();
        instance.setTime(endOfTheDay);

        Assertions.assertEquals(23, instance.get(Calendar.HOUR_OF_DAY));
        Assertions.assertEquals(59, instance.get(Calendar.MINUTE));
        Assertions.assertEquals(59, instance.get(Calendar.SECOND));
    }

    @Test
    void testGetEndOfTheDayWhenNull() {
        Assertions.assertDoesNotThrow(() -> DatePickUtil.getEndOfTheDay(null)
        );
    }

}
