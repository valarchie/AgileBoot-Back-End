package com.agileboot.domain.system.log.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_login_info")
@ApiModel(value = "SysLoginInfoEntity对象", description = "系统访问记录")
public class SysLoginInfoEntity extends Model<SysLoginInfoEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("访问ID")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    @ApiModelProperty("用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("登录IP地址")
    @TableField("ip_address")
    private String ipAddress;

    @ApiModelProperty("登录地点")
    @TableField("login_location")
    private String loginLocation;

    @ApiModelProperty("浏览器类型")
    @TableField("browser")
    private String browser;

    @ApiModelProperty("操作系统")
    @TableField("operation_system")
    private String operationSystem;

    @ApiModelProperty("登录状态（1成功 0失败）")
    @TableField("`status`")
    private Integer status;

    @ApiModelProperty("提示消息")
    @TableField("msg")
    private String msg;

    @ApiModelProperty("访问时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


    @Override
    public Serializable pkVal() {
        return this.infoId;
    }

}
