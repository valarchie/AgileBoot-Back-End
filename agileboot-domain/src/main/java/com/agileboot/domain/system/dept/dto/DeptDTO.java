package com.agileboot.domain.system.dept.dto;

import com.agileboot.orm.common.enums.StatusEnum;
import com.agileboot.orm.common.util.BasicEnumUtil;
import com.agileboot.orm.system.entity.SysDeptEntity;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class DeptDTO {

    public DeptDTO(SysDeptEntity entity) {
        if (entity != null) {
            this.deptId = entity.getDeptId();
            this.parentId = entity.getParentId();
            this.deptName = entity.getDeptName();
            this.orderNum = entity.getOrderNum();
            this.leaderName = entity.getLeaderName();
            this.email = entity.getEmail();
            this.phone = entity.getPhone();
            this.status = entity.getStatus();
            this.createTime = entity.getCreateTime();
            this.statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());
        }
    }


    private Long deptId;

    private Long parentId;

    private String deptName;

    private Integer orderNum;

    private String leaderName;

    private String phone;

    private String email;

    private Integer status;

    private String statusStr;

    private Date createTime;

}
