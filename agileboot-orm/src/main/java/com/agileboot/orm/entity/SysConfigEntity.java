package com.agileboot.orm.entity;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_config")
@ApiModel(value = "SysConfigEntity对象", description = "参数配置表")
public class SysConfigEntity extends BaseEntity<SysConfigEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("参数主键")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    @ApiModelProperty("配置名称")
    @TableField("config_name")
    private String configName;

    @ApiModelProperty("配置键名")
    @TableField("config_key")
    private String configKey;

    @ApiModelProperty("可选的选项")
    @TableField("config_options")
    private String configOptions;

    @ApiModelProperty("配置值")
    @TableField("config_value")
    private String configValue;

    @ApiModelProperty("是否允许修改")
    @TableField("is_allow_change")
    private Boolean isAllowChange;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.configId;
    }

}
