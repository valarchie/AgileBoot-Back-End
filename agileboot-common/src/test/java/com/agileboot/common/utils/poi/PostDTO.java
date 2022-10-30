package com.agileboot.common.utils.poi;

import com.agileboot.common.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class PostDTO {

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
