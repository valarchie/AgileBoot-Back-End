package com.agileboot.domain.system.post;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.orm.entity.SysPostEntity;
import com.agileboot.orm.enums.dictionary.CommonStatusEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import lombok.Data;

@Data
public class PostDTO {

    public PostDTO(SysPostEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
            statusStr = BasicEnumUtil.getDescriptionByValue(CommonStatusEnum.class, entity.getStatus());
        }
    }

    @ExcelColumn(name = "岗位ID")
    private Long postId;


    @ExcelColumn(name = "岗位编码")
    private String postCode;

    @ExcelColumn(name = "岗位名称")
    private String postName;


    @ExcelColumn(name = "岗位排序")
    private String postSort;

    @ExcelColumn(name = "备注")
    private String remark;

    private String status;

    @ExcelColumn(name = "状态")
    private String statusStr;

}
