package com.agileboot.orm.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_operation_log")
@Schema(description = "操作日志记录")
public class SysOperationLogEntity extends Model<SysOperationLogEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "日志主键")
    @TableId(value = "operation_id", type = IdType.AUTO)
    private Long operationId;

    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    @TableField("business_type")
    private Integer businessType;

    @Schema(description = "请求方式")
    @TableField("request_method")
    private Integer requestMethod;

    @Schema(description = "请求模块")
    @TableField("request_module")
    private String requestModule;

    @Schema(description = "请求URL")
    @TableField("request_url")
    private String requestUrl;

    @Schema(description = "调用方法")
    @TableField("called_method")
    private String calledMethod;

    @Schema(description = "操作类别（0其它 1后台用户 2手机端用户）")
    @TableField("operator_type")
    private Integer operatorType;

    @Schema(description = "用户ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "操作人员")
    @TableField("username")
    private String username;

    @Schema(description = "操作人员ip")
    @TableField("operator_ip")
    private String operatorIp;

    @Schema(description = "操作地点")
    @TableField("operator_location")
    private String operatorLocation;

    @Schema(description = "部门ID")
    @TableField("dept_id")
    private Long deptId;

    @Schema(description = "部门名称")
    @TableField("dept_name")
    private String deptName;

    @Schema(description = "请求参数")
    @TableField("operation_param")
    private String operationParam;

    @Schema(description = "返回参数")
    @TableField("operation_result")
    private String operationResult;

    @Schema(description = "操作状态（1正常 0异常）")
    @TableField("`status`")
    private Integer status;

    @Schema(description = "错误消息")
    @TableField("error_stack")
    private String errorStack;

    @Schema(description = "操作时间")
    @TableField("operation_time")
    private Date operationTime;

    @Schema(description = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


    @Override
    public Serializable pkVal() {
        return this.operationId;
    }

}
