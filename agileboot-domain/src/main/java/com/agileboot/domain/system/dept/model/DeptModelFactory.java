package com.agileboot.domain.system.dept.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.dept.command.AddDeptCommand;
import com.agileboot.orm.system.entity.SysDeptEntity;
import com.agileboot.orm.system.service.ISysDeptService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class DeptModelFactory {

    @NonNull
    private ISysDeptService deptService;

    public DeptModel loadById(Long deptId) {
        SysDeptEntity byId = deptService.getById(deptId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, deptId, "部门");
        }
        return new DeptModel(byId, deptService);
    }

    public DeptModel create() {
        return new DeptModel(deptService);
    }

    public DeptModel loadFromAddCommand(AddDeptCommand addCommand, DeptModel model) {
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
