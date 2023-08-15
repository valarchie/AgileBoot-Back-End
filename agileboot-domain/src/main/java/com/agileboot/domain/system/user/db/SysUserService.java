package com.agileboot.domain.system.user.db;

import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.role.db.SysRoleEntity;
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
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 检测号码是否唯一
     *
     * @param phone  电话号码
     * @param userId 用户id
     * @return 校验结果
     */
    boolean isPhoneDuplicated(String phone, Long userId);

    /**
     * 检测邮箱是否唯一
     * @param email 邮箱
     * @param userId 用户id
     * @return 校验结果
     */
    boolean isEmailDuplicated(String email, Long userId);

    /**
     * 检测用户名是否
     * @param userName 用户名
     * @return 校验结果
     */
    boolean isUserNameDuplicated(String userName);

    /**
     * 获取用户的角色
     * @param userId 用户id
     * @return 用户角色
     */
    SysRoleEntity getRoleOfUser(Long userId);

    /**
     * 获取用户的岗位
     * @param userId 用户id
     * @return 用户岗位
     */
    SysPostEntity getPostOfUser(Long userId);

    /**
     * 获取用户的权限列表
     * @param userId 用户id
     * @return 用户菜单权限列表
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
