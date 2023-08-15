package com.agileboot.domain.system.role.db;

import com.agileboot.domain.system.menu.db.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 根据角色ID查询对应的菜单权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Select("SELECT m.* "
        + "FROM sys_menu m "
        + " LEFT JOIN sys_role_menu rm ON m.menu_id = rm.menu_id "
        + " LEFT JOIN sys_role r ON r.role_id = rm.role_id "
        + "WHERE m.status = 1 AND m.deleted = 0 "
        + " AND r.status = 1 AND r.deleted = 0 "
        + " AND r.role_id = #{roleId}")
    List<SysMenuEntity> getMenuListByRoleId(Long roleId);

}
