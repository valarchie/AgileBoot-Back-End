package com.agileboot.domain.system.menu;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.query.AbstractQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Arrays;
import lombok.Data;

@Data
public class MenuQuery extends AbstractQuery {

    private String menuName;
    private Boolean isVisible;
    private Integer status;


    @Override
    public QueryWrapper<SysMenuEntity> toQueryWrapper() {

        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(menuName), columnName("menu_name"), menuName)
            .eq(isVisible!=null, "is_visible", isVisible)
            .eq(status!=null, "status", status);

        queryWrapper.orderBy(true, true, Arrays.asList("parent_id", "order_num"));
        return queryWrapper;
    }
}
