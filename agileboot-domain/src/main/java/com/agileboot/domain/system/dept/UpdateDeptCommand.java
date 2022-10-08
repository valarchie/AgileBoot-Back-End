package com.agileboot.domain.system.dept;

import cn.hutool.core.convert.Convert;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UpdateDeptCommand extends AddDeptCommand{

    @NotNull
    @PositiveOrZero
    private Long deptId;

    @PositiveOrZero
    private String status;

    @Override
    public DeptModel toModel() {
        DeptModel deptModel = super.toModel();
        deptModel.setDeptId(this.deptId);
        deptModel.setStatus(Convert.toInt(status, 0));
        return deptModel;
    }

}
