package com.agileboot.orm.service;

import com.agileboot.orm.entity.SysPostEntity;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.query.AbstractPageQuery;
import com.agileboot.orm.result.SearchUserDO;
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

    boolean checkDeptExistUser(Long deptId);

    boolean checkExistUserLinkToRole(Long roleId);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    boolean checkUserNameUnique(String userName);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userId
     * @return 结果
     */
    SysRoleEntity getRoleOfUser(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userId
     * @return 结果
     */
    SysPostEntity getPostOfUser(Long userId);

    /**
     * 校验手机号码是否唯一
     * @return 结果
     */
    boolean checkPhoneUnique(String phone, Long userId);

    /**
     * 校验email是否唯一
     * @return 结果
     */
    boolean checkEmailUnique(String email, Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserEntity getUserByUserName(String userName);

    Page<SysUserEntity> selectAllocatedList(AbstractPageQuery query);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param query 查询参数
     * @return 用户信息集合信息
     */
    Page<SysUserEntity>  selectUnallocatedList(AbstractPageQuery query);

    /**
     * 根据条件分页查询用户列表
     *
     * @param query 查询参数
     * @return 用户信息集合信息
     */
    Page<SearchUserDO> selectUserList(AbstractPageQuery query);



}
