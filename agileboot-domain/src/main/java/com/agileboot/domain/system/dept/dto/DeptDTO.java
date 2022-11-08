package com.agileboot.domain.system.dept.dto;

import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.enums.dictionary.StatusEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import java.util.Date;
import lombok.Data;

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
            this.status = entity.getStatus() + "";
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

    private String status;

    private String statusStr;

    private Date createTime;

}
