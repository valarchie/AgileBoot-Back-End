package com.agileboot.orm.mapper;

import com.agileboot.orm.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 根据用户查询出所有菜单
     * @param userId
     * @return
     */
    @Select("SELECT DISTINCT m.* "
        + "FROM sys_menu m "
        + " LEFT JOIN sys_role_menu rm ON m.menu_id = rm.menu_id "
        + " LEFT JOIN sys_user u ON rm.role_id = u.role_id "
        + "WHERE u.user_id = #{userId} "
        + " AND m.status = 1 "
        + " AND m.deleted = 0 "
        + "ORDER BY m.parent_id, m.order_num")
    List<SysMenuEntity> selectMenuListByUserId(@Param("userId")Long userId);


    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Select("SELECT DISTINCT m.menu_id "
        + "FROM sys_menu m "
        + " LEFT JOIN sys_role_menu rm ON m.menu_id = rm.menu_id "
        + "WHERE rm.role_id = #{roleId} "
        + " AND m.deleted = 0 "
        + "GROUP BY m.menu_id "
        + "ORDER BY m.parent_id, m.order_num")
    List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId);

}
