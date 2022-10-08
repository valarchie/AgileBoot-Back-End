package com.agileboot.orm.query;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.utils.time.DatePicker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;
import java.util.Map;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public abstract class AbstractQuery {

    protected String orderByColumn;

    protected String isAsc;

    private Date beginTime;

    private Date endTime;

    private static final String ASC = "ascending";
    private static final String DESC = "descending";



    /**
     * 比如原本的字段是  user_id 可以改成  u.user_id  以适应不同表的查询
     */
    protected Map<String, String> filedOverride = MapUtil.empty();

    /**
     * 生成query conditions
     * @return
     */
    public abstract QueryWrapper toQueryWrapper();

    /**
     * 如果有特殊的表名.前缀  就使用特殊的表名前缀
     * @param columnName
     * @return
     */
    public String columnName(String columnName) {
        if (filedOverride.get(columnName) != null) {
            return filedOverride.get(columnName);
        }
        return columnName;
    }

    public void addSortCondition(QueryWrapper<?> queryWrapper) {
        if(queryWrapper != null) {
            queryWrapper.orderBy(StrUtil.isNotBlank(orderByColumn), convertSortDirection(),
                StrUtil.toUnderlineCase(orderByColumn));

        }
    }

    @SuppressWarnings("unchecked")
    public void addTimeCondition(QueryWrapper queryWrapper, String fieldName) {
        if(queryWrapper!=null) {
            queryWrapper
                .ge(beginTime != null, fieldName, DatePicker.getBeginOfTheDay(beginTime))
                .le(endTime != null, fieldName, DatePicker.getEndOfTheDay(endTime));
        }
    }


    public boolean convertSortDirection() {
        boolean orderDirection = true;
        if (StrUtil.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if (ASC.equals(isAsc)) {
                orderDirection = true;
            } else if (DESC.equals(isAsc)) {
                orderDirection = false;
            }
        }
        return orderDirection;
    }


}
