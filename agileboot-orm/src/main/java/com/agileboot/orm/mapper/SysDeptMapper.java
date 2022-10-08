package com.agileboot.orm.mapper;

import com.agileboot.orm.entity.SysDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Select("SELECT d.dept_id "
        + "FROM sys_dept d "
        + " LEFT JOIN sys_role r ON d.dept_id = r.dept_id "
        + "WHERE r.role_id = #{roleId} and d.deleted = 0 "
        + "ORDER BY d.parent_id, d.order_num")
    List<Long> selectDeptListByRoleId(Long roleId);



}
