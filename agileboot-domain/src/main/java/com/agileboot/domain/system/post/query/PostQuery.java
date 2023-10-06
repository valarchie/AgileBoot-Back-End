package com.agileboot.domain.system.post.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQuery extends AbstractPageQuery<SysPostEntity> {

    private String postCode;
    private String postName;
    private Integer status;

    @Override
    public QueryWrapper<SysPostEntity> addQueryCondition() {
        QueryWrapper<SysPostEntity> queryWrapper = new QueryWrapper<SysPostEntity>()
            .eq(status != null, "status", status)
            .eq(StrUtil.isNotEmpty(postCode), "post_code", postCode)
            .like(StrUtil.isNotEmpty(postName), "post_name", postName);
        // 当前端没有选择排序字段时，则使用post_sort字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("post_sort");
        }
        this.setTimeRangeColumn("create_time");

        return queryWrapper;
    }
}
