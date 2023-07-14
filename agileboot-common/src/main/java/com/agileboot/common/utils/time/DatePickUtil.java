package com.agileboot.common.utils.time;

import cn.hutool.core.date.DateUtil;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author valarchie
 */
@Slf4j
public class DatePickUtil {

    private DatePickUtil() {
    }

    /**
     * 安全地获取日期的一天开始时间, date为null 则返回null DateUtil.beginOfDay(date) 如果传null 会NPE
     *
     * @param date 当前日期
     * @return 日期的一天开始时间
     */
    public static Date getBeginOfTheDay(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return DateUtil.beginOfDay(date);
        } catch (Exception e) {
            log.error("pick begin of the day failed, due to: ", e);
        }
        return null;
    }

    /**
     * 安全地获取日期的一天结束时间, date为null 则返回null。 避免NPE
     *  DateUtil.endOfDay(date) 如果传null 会NPE
     * @param date 23:59:59
     * @return 日期的一天结束时间
     */
    public static Date getEndOfTheDay(Date date) {
        if (date == null) {
            return null;
        }

        try {
            return DateUtil.endOfDay(date);
        } catch (Exception e) {
            log.error("pick end of the day failed, due to: ", e);
        }
        return null;
    }



}
