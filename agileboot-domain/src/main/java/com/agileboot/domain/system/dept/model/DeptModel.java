package com.agileboot.domain.system.dept.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.enums.dictionary.CommonStatusEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import com.agileboot.orm.service.ISysDeptService;
import java.util.Objects;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class DeptModel extends SysDeptEntity {

    public DeptModel(SysDeptEntity entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }

    public void checkDeptNameUnique(ISysDeptService deptService) {
        if (deptService.isDeptNameDuplicated(getDeptName(), getDeptId(), getParentId())) {
            throw new ApiException(ErrorCode.Business.DEPT_NAME_IS_NOT_UNIQUE, getDeptName());
        }
    }

    public void checkParentIdConflict() {
        if (Objects.equals(getParentId(), getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF);
        }
    }

    public void checkHasChildDept(ISysDeptService deptService) {
        if (deptService.hasChildrenDept(getDeptId(), null)) {
            throw new ApiException(ErrorCode.Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE);
        }
    }

    public void checkDeptAssignedToUsers(ISysDeptService deptService) {
        if (deptService.isDeptAssignedToUsers(getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE);
        }
    }

    public void generateAncestors(ISysDeptService deptService) {
        if (getParentId() == 0) {
            setAncestors(getParentId().toString());
            return;
        }

        SysDeptEntity parentDept = deptService.getById(getParentId());

        if (parentDept == null || CommonStatusEnum.DISABLE.equals(
            BasicEnumUtil.fromValue(CommonStatusEnum.class, parentDept.getStatus()))) {
            throw new ApiException(ErrorCode.Business.DEPT_PARENT_DEPT_NO_EXIST_OR_DISABLED);
        }

        setAncestors(parentDept.getAncestors() + "," + getParentId());
    }


    /**
     * DDD 有些阻抗  如果为了追求性能的话  还是得通过 数据库的方式来判断
     * @param deptService
     */
    public void checkStatusAllowChange(ISysDeptService deptService) {
        if (CommonStatusEnum.DISABLE.getValue().equals(getStatus()) &&
            deptService.hasChildrenDept(getDeptId(), true)) {
            throw new ApiException(ErrorCode.Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE);
        }

    }

}
