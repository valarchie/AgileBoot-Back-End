package com.agileboot.common.core.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class PageDTO {
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    public PageDTO(List<?> list) {
        this.rows = list;
        this.total = (long) list.size();
    }

    public PageDTO(Page page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

    public PageDTO(List<?> list, Long count) {
        this.rows = list;
        this.total = count;
    }

}
