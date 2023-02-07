package com.agileboot.orm.common.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.validation.constraints.Max;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public abstract class AbstractPageQuery<T> extends AbstractQuery<T> {

    public static final int MAX_PAGE_NUM = 200;
    public static final int MAX_PAGE_SIZE = 500;

    @Max(MAX_PAGE_NUM)
    protected Integer pageNum = 1;
    @Max(MAX_PAGE_SIZE)
    protected Integer pageSize = 10;

    public Page<T> toPage() {
        return new Page<>(pageNum, pageSize);
    }

}
