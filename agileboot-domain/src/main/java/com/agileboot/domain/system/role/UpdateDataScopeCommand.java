package com.agileboot.domain.system.role;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateDataScopeCommand {

    @NotNull
    @Positive
    private Long roleId;

    @NotNull
    @NotEmpty
    private List<Long> deptIds;

    private Integer dataScope;


}
