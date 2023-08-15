package com.agileboot.domain.system.dept.db;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysDeptService extends IService<SysDeptEntity> {


    /**
     * 检测部门名称是否一致
     *
     * @param deptName 部门名称
     * @param deptId   部门id
     * @param parentId 父级部门id
     * @return 校验结果
     */
    boolean isDeptNameDuplicated(String deptName, Long deptId, Long parentId);

    /**
     * 检测部门底下是否还有正在使用中的子部门
     * @param deptId 部门id
     * @param enabled 部门是否开启
     * @return 结果
     */
    boolean hasChildrenDept(Long deptId, Boolean enabled);

    /**
     * 是否是目标部门的子部门
     * @param parentId 目标部门id
     * @param childId 子部门id
     * @return 校验结果
     */
    boolean isChildOfTheDept(Long parentId, Long childId);

    /**
     * 检测该部门是否已有用户使用
     * @param deptId 部门id
     * @return 校验结果
     */
    boolean isDeptAssignedToUsers(Long deptId);


}
