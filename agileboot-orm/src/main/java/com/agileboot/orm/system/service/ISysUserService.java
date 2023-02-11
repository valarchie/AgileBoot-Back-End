package com.agileboot.orm.system.service;

import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.system.entity.SysPostEntity;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.result.SearchUserDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface ISysUserService extends IService<SysUserEntity> {

    /**
     * 检测号码是否唯一
     * @param phone
     * @param userId
     * @return
     */
    boolean isPhoneDuplicated(String phone, Long userId);

    /**
     * 检测邮箱是否唯一
     * @param email
     * @param userId
     * @return
     */
    boolean isEmailDuplicated(String email, Long userId);

    /**
     * 检测用户名是否
     * @param userName
     * @return
     */
    boolean isUserNameDuplicated(String userName);

    /**
     * 获取用户的角色
     * @param userId
     * @return
     */
    SysRoleEntity getRoleOfUser(Long userId);

    /**
     * 获取用户的岗位
     * @param userId
     * @return
     */
    SysPostEntity getPostOfUser(Long userId);

    /**
     * 获取用户的权限列表
     * @param userId
     * @return
     */
    Set<String> getMenuPermissionsForUser(Long userId);


    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserEntity getUserByUserName(String userName);


    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param query 查询参数
     * @return 用户信息集合信息
     */
    Page<SysUserEntity> getUserListByRole(AbstractPageQuery<SysUserEntity> query);

    /**
     * 根据条件分页查询用户列表
     *
     * @param query 查询参数
     * @return 用户信息集合信息
     */
    Page<SearchUserDO> getUserList(AbstractPageQuery<SearchUserDO> query);


}
