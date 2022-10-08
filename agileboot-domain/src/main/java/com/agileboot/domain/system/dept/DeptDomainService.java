package com.agileboot.domain.system.dept;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.system.TreeSelectedDTO;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.service.ISysDeptService;
import com.agileboot.orm.service.ISysRoleService;
import com.agileboot.orm.service.ISysUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author valarchie
 */
@SuppressWarnings("AlibabaTransactionMustHaveRollback")
@Service
public class DeptDomainService {

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService userService;

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

    public TreeSelectedDTO getDeptTreeForRole(Long roleId) {
        List<Long> checkedKeys = new ArrayList<>();
        SysRoleEntity role = roleService.getById(roleId);
        if (role != null && StrUtil.isNotEmpty(role.getDeptIdSet())) {
            checkedKeys = StrUtil.split(role.getDeptIdSet(), ",")
                .stream().map(Long::new).collect(Collectors.toList());
        }

        TreeSelectedDTO selectedDTO = new TreeSelectedDTO();
        selectedDTO.setDepts(getDeptTree());
        selectedDTO.setCheckedKeys(checkedKeys);

        return selectedDTO;
    }


    @Transactional
    public void addDept(AddDeptCommand addCommand, LoginUser loginUser) {
        DeptModel deptModel = addCommand.toModel();
        if (deptService.checkDeptNameUnique(deptModel.getDeptName(), null, deptModel.getParentId())) {
            throw new ApiException(ErrorCode.Business.DEPT_NAME_IS_NOT_UNIQUE, deptModel.getDeptName());
        }

        deptModel.generateAncestors(deptService);
        deptModel.logCreator(loginUser);

        deptModel.insert();
    }

    @Transactional
    public void updateDept(UpdateDeptCommand updateCommand, LoginUser loginUser) {
        // TODO 需要再调整一下
        getDeptModel(updateCommand.getDeptId());

        DeptModel deptModel = updateCommand.toModel();
        if (deptService.checkDeptNameUnique(deptModel.getDeptName(), deptModel.getDeptId(), deptModel.getParentId())) {
            throw new ApiException(ErrorCode.Business.DEPT_NAME_IS_NOT_UNIQUE, deptModel.getDeptName());
        }

        deptModel.checkParentId();
        deptModel.checkStatusAllowChange(deptService);
        deptModel.generateAncestors(deptService);

        deptModel.logUpdater(loginUser);

        deptModel.updateById();
    }

    @Transactional
    public void removeDept(Long deptId) {
        DeptModel deptModel = getDeptModel(deptId);

        deptModel.checkExistChildDept(deptService);
        deptModel.checkExistLinkedUsers(userService);

        deptService.removeById(deptId);
    }

    public DeptModel getDeptModel(Long id) {
        SysDeptEntity byId = deptService.getById(id);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, id, "参数配置");
        }

        return new DeptModel(byId);
    }

}
