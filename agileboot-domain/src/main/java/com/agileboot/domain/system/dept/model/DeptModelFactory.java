package com.agileboot.domain.system.dept.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.dept.command.AddDeptCommand;
import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.service.ISysDeptService;

/**
 * 部门模型工厂
 * @author valarchie
 */
public class DeptModelFactory {

    public static DeptModel loadFromDb(Long deptId, ISysDeptService deptService) {
        SysDeptEntity byId = deptService.getById(deptId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, deptId, "部门");
        }
        return new DeptModel(byId);
    }

    public static DeptModel loadFromAddCommand(AddDeptCommand addCommand, DeptModel model) {
        model.setParentId(addCommand.getParentId());
        model.setAncestors(addCommand.getAncestors());
        model.setDeptName(addCommand.getDeptName());
        model.setOrderNum(addCommand.getOrderNum());
        model.setLeaderName(addCommand.getLeaderName());
        model.setPhone(addCommand.getPhone());
        model.setEmail(addCommand.getEmail());
        return model;
    }



}
