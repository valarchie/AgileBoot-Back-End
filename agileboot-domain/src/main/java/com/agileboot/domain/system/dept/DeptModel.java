package com.agileboot.domain.system.dept;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.enums.dictionary.CommonStatusEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import com.agileboot.orm.service.ISysDeptService;
import com.agileboot.orm.service.ISysUserService;
import java.util.Objects;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeptModel extends SysDeptEntity {

    public DeptModel(SysDeptEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }


    public void checkParentId() {
        if (Objects.equals(getParentId(), getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF);
        }
    }


    public void checkExistChildDept(ISysDeptService deptService) {
        if (deptService.hasChildDeptById(getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE);
        }
    }

    public void checkExistLinkedUsers(ISysUserService userService) {
        if (userService.checkDeptExistUser(getDeptId())) {
            throw new ApiException(ErrorCode.Business.DEPT_EXIST_LINK_USER_NOT_ALLOW_DELETE);
        }
    }

    public void generateAncestors(ISysDeptService deptService) {
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
            deptService.existChildrenDeptById(getDeptId(), true)) {
            throw new ApiException(ErrorCode.Business.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE);
        }

    }

}
