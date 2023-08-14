package com.agileboot.common.query;


import com.agileboot.common.core.page.AbstractQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractQueryTest {

    private AbstractQuery query;

    @BeforeEach
    public void getNewQuery() {
        query = new AbstractQuery<Object>() {
            @Override
            public QueryWrapper addQueryCondition() {
                return new QueryWrapper();
            }
        };
    }


    @Test
    void addTimeConditionWithNull() {
        query.setTimeRangeColumn("loginTime");
        QueryWrapper<Object> queryWrapper = query.toQueryWrapper();

        String targetSql = queryWrapper.getTargetSql();
        Assertions.assertEquals("", targetSql);
    }


    @Test
    void addTimeConditionWithBothValue() {
        query.setBeginTime(new Date());
        query.setEndTime(new Date());
        query.setTimeRangeColumn("loginTime");

        QueryWrapper<Object> queryWrapper = query.toQueryWrapper();

        String targetSql = queryWrapper.getTargetSql();
        Assertions.assertEquals("(login_time >= ? AND login_time <= ?)", targetSql);
    }


    @Test
    void addTimeConditionWithBeginValueOnly() {
        query.setBeginTime(new Date());
        query.setTimeRangeColumn("loginTime");

        QueryWrapper<Object> queryWrapper = query.toQueryWrapper();

        String targetSql = queryWrapper.getTargetSql();
        Assertions.assertEquals("(login_time >= ?)", targetSql);
    }



    @Test
    void testConvertSortDirection() {
        query.setOrderDirection("ascending");
        Assertions.assertTrue(query.convertSortDirection());

        query.setOrderDirection("descending");
        Assertions.assertFalse(query.convertSortDirection());

        query.setOrderDirection("");
        Assertions.assertNull(query.convertSortDirection());

        query.setOrderDirection(null);
        Assertions.assertNull(query.convertSortDirection());
    }




}
