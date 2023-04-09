package com.agileboot.domain.system.dept;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.agileboot.domain.system.dept.command.AddDeptCommand;
import com.agileboot.domain.system.dept.command.UpdateDeptCommand;
import com.agileboot.domain.system.dept.dto.DeptDTO;
import com.agileboot.domain.system.dept.model.DeptModel;
import com.agileboot.domain.system.dept.model.DeptModelFactory;
import com.agileboot.domain.system.dept.query.DeptQuery;
import com.agileboot.orm.system.entity.SysDeptEntity;
import com.agileboot.orm.system.service.ISysDeptService;
import com.agileboot.orm.system.service.ISysRoleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门服务
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class DeptApplicationService {

    @NonNull
    private ISysDeptService deptService;

    @NonNull
    private ISysRoleService roleService;

    @NonNull
    private DeptModelFactory deptModelFactory;


    public List<DeptDTO> getDeptList(DeptQuery query) {
        List<SysDeptEntity> list = deptService.list(query.toQueryWrapper());
        return list.stream().map(DeptDTO::new).collect(Collectors.toList());
    }

    public DeptDTO getDeptInfo(Long id) {
        SysDeptEntity byId = deptService.getById(id);
        return new DeptDTO(byId);
    }

    public List<Tree<Long>> getDeptTree() {
        List<SysDeptEntity> list = deptService.list();

        return TreeUtil.build(list, 0L, (dept, tree) -> {
            tree.setId(dept.getDeptId());
            tree.setParentId(dept.getParentId());
            tree.putExtra("label", dept.getDeptName());
        });
    }


    public void addDept(AddDeptCommand addCommand) {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.loadAddCommand(addCommand);

        deptModel.checkDeptNameUnique();
        deptModel.generateAncestors();

        deptModel.insert();
    }

    public void updateDept(UpdateDeptCommand updateCommand) {
        DeptModel deptModel = deptModelFactory.loadById(updateCommand.getDeptId());
        deptModel.loadUpdateCommand(updateCommand);

        deptModel.checkDeptNameUnique();
        deptModel.checkParentIdConflict();
        deptModel.checkStatusAllowChange();
        deptModel.generateAncestors();

        deptModel.updateById();
    }

    public void removeDept(Long deptId) {
        DeptModel deptModel = deptModelFactory.loadById(deptId);

        deptModel.checkHasChildDept();
        deptModel.checkDeptAssignedToUsers();

        deptModel.deleteById();
    }



}
