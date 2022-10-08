package com.agileboot.common.utils.time;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author valarchie
 */
@Slf4j
public class DatePicker {

    public static Date getBeginOfTheDay(Object date) {
        if (date == null) {
            return null;
        }
        try {
            DateTime parse = DateUtil.parse(date.toString());
            return DateUtil.beginOfDay(parse);
        } catch (Exception e) {
            log.error("pick begin of day failed, due to: ", e);
        }
        return null;
    }

    public static Date getEndOfTheDay(Object date) {
        if (date == null) {
            return null;
        }
        try {
            DateTime parse = DateUtil.parse(date.toString());
            return DateUtil.endOfDay(parse);
        } catch (Exception e) {
            log.error("pick end of day failed, due to: ", e);
        }
        return null;
    }



}
