package com.agileboot.domain.system.post.db;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysPostService extends IService<SysPostEntity> {

    /**
     * 校验岗位名称
     * @param postId 职位Id
     * @param postName 职位名称
     * @return 结果
     */
    boolean isPostNameDuplicated(Long postId, String postName);

    /**
     * 校验岗位编码
     * @param postId 职位id
     * @param postCode 职位代码
     * @return 结果
     */
    boolean isPostCodeDuplicated(Long postId, String postCode);


    /**
     * 检测职位是否分配给用户
     *
     * @param postId 职位id
     * @return 校验结果
     */
    boolean isAssignedToUsers(Long postId);

}
