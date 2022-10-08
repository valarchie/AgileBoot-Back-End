package com.agileboot.domain.system.config;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ConfigUpdateCommand {

    @NotNull
    @Positive
    private Long configId;
    @NotNull
    @NotEmpty
    private String configValue;

}
