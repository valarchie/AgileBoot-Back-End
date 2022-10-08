package com.agileboot.common.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * Entity基类
 *
 * @author valarchie
 */
@Data
public class BaseEntity<T extends Model<?>> extends Model<T> {

    @ApiModelProperty("创建者ID")
    @TableField("creator_id")
    private Long creatorId;

    @ApiModelProperty("创建者")
    @TableField("creator_name")
    private String creatorName;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新者ID")
    @TableField("updater_id")
    private Long updaterId;

    @ApiModelProperty("更新者")
    @TableField("updater_name")
    private String updaterName;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    public void logCreator(BaseUser user) {
        if (user != null) {
            this.creatorId = user.getUserId();
            this.creatorName = user.getUsername();
        }
    }

    public void logUpdater(BaseUser user) {
        if (user != null) {
            this.updaterId = user.getUserId();
            this.updaterName = user.getUsername();
        }
    }

}
