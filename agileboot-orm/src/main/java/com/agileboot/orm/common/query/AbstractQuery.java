package com.agileboot.orm.common.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.utils.time.DatePickUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public abstract class AbstractQuery<T> {

    protected String orderByColumn;

    protected String isAsc;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endTime;

    private static final String ASC = "ascending";
    private static final String DESC = "descending";

    /**
     * 生成query conditions
     * @return
     */
    public abstract QueryWrapper<T> toQueryWrapper();

    public void addSortCondition(QueryWrapper<T> queryWrapper) {
        if(queryWrapper != null) {
            boolean sortDirection = convertSortDirection();
            queryWrapper.orderBy(StrUtil.isNotBlank(orderByColumn), sortDirection,
                StrUtil.toUnderlineCase(orderByColumn));
        }
    }

    public void addTimeCondition(QueryWrapper<T> queryWrapper, String fieldName) {
        if (queryWrapper != null) {
            queryWrapper
                .ge(beginTime != null, fieldName, DatePickUtil.getBeginOfTheDay(beginTime))
                .le(endTime != null, fieldName, DatePickUtil.getEndOfTheDay(endTime));
        }
    }

    public boolean convertSortDirection() {
        boolean orderDirection = true;
        if (StrUtil.isNotEmpty(isAsc)) {
            if (ASC.equals(isAsc)) {
                orderDirection = true;
            } else if (DESC.equals(isAsc)) {
                orderDirection = false;
            }
        }
        return orderDirection;
    }

}
