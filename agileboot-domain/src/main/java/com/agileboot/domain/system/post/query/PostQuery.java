package com.agileboot.domain.system.post.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.orm.system.entity.SysPostEntity;
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

        return queryWrapper;
    }
}
