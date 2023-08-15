package com.agileboot.domain.system.dept.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.dept.command.AddDeptCommand;
import com.agileboot.domain.system.dept.command.UpdateDeptCommand;
import com.agileboot.common.enums.common.StatusEnum;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.dept.db.SysDeptService;
import java.util.Objects;

/**
 * @author valarchie
 */
public class DeptModel extends SysDeptEntity {

    private final SysDeptService deptService;

    public DeptModel(SysDeptService deptService) {
        this.deptService = deptService;
    }

    public DeptModel(SysDeptEntity entity, SysDeptService deptService) {
        if (entity != null) {
            // 如果大数据量的话  可以用MapStruct优化
            BeanUtil.copyProperties(entity, this);
        }
        this.deptService = deptService;
    }

    public void loadAddCommand(AddDeptCommand addCommand) {
        this.setParentId(addCommand.getParentId());
        this.setDeptName(addCommand.getDeptName());
        this.setOrderNum(addCommand.getOrderNum());
        this.setLeaderName(addCommand.getLeaderName());
        this.setPhone(addCommand.getPhone());
        this.setEmail(addCommand.getEmail());
        this.setStatus(addCommand.getStatus());
    }

    public void loadUpdateCommand(UpdateDeptCommand updateCommand) {
        loadAddCommand(updateCommand);
        setStatus(Convert.toInt(updateCommand.getStatus(), 0));
    }

    public void checkDeptNameUnique() {
        if (deptService.isDeptNameDuplicated(getDeptName(), getDeptId(), getParentId())) {
            throw new ApiException(ErrorCode.Business.DEPT_NAME_IS_NOT_UNIQUE, getDeptName());
        }
    }

    public void checkParentIdConflict() {
        if (Objects.equals(getParentId(), getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF);
        }
    }

    public void checkHasChildDept() {
        if (deptService.hasChildrenDept(getDeptId(), null)) {
            throw new ApiException(ErrorCode.Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE);
        }
    }

    public void checkDeptAssignedToUsers() {
        if (deptService.isDeptAssignedToUsers(getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE);
        }
    }

    public void generateAncestors() {
        if (getParentId() == 0) {
            setAncestors(getParentId().toString());
            return;
        }

        SysDeptEntity parentDept = deptService.getById(getParentId());

        if (parentDept == null || StatusEnum.DISABLE.equals(
            BasicEnumUtil.fromValue(StatusEnum.class, parentDept.getStatus()))) {
            throw new ApiException(ErrorCode.Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED);
        }

        setAncestors(parentDept.getAncestors() + "," + getParentId());
    }


    /**
     * DDD 有些阻抗  如果为了追求性能的话  还是得通过 数据库的方式来判断
     */
    public void checkStatusAllowChange() {
        if (StatusEnum.DISABLE.getValue().equals(getStatus()) &&
            deptService.hasChildrenDept(getDeptId(), true)) {
            throw new ApiException(ErrorCode.Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE);
        }

    }

}
