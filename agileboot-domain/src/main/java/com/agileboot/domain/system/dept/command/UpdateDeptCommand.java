package com.agileboot.domain.system.dept.command;

import cn.hutool.core.convert.Convert;
import com.agileboot.domain.system.dept.command.AddDeptCommand;
import com.agileboot.domain.system.dept.model.DeptModel;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UpdateDeptCommand extends AddDeptCommand {

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
