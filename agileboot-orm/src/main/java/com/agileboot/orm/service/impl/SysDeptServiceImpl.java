package com.agileboot.orm.service.impl;

import com.agileboot.orm.entity.SysDeptEntity;
import com.agileboot.orm.mapper.SysDeptMapper;
import com.agileboot.orm.service.ISysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {


    @Override
    public boolean checkDeptNameUnique(String deptName, Long deptId, Long parentId) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", deptName)
            .ne(deptId != null, "dept_id", deptId)
            .eq(parentId != null, "parent_id", parentId);

        SysDeptEntity one = this.getOne(queryWrapper);
        return one != null;
    }


    @Override
    public boolean existChildrenDeptById(Long deptId, Boolean enabled) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq( "dept_id", deptId)
            .eq(enabled != null, "status", 1)
            .apply( "FIND_IN_SET (dept_id , ancestors)");
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isChildOfTargetDeptId(Long ancestorId, Long childId) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("dept_id = '" + childId + "' or FIND_IN_SET ( " + ancestorId + " , ancestors)");
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean hasChildDeptById(Long deptId) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deptId != null, "parent_id", deptId);
        return baseMapper.exists(queryWrapper);
    }


}
