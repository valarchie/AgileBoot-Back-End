package com.agileboot.domain.system.post.dto;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.domain.system.post.db.SysPostEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {

    public PostDTO(SysPostEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
            statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());
        }
    }

    @ExcelColumn(name = "岗位ID")
    private Long postId;


    @ExcelColumn(name = "岗位编码")
    private String postCode;

    @ExcelColumn(name = "岗位名称")
    private String postName;


    @ExcelColumn(name = "岗位排序")
    private Integer postSort;

    @ExcelColumn(name = "备注")
    private String remark;

    private Integer status;

    @ExcelColumn(name = "状态")
    private String statusStr;

    private Date createTime;

}
