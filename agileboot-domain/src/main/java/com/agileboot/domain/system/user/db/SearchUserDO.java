package com.agileboot.domain.system.user.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 如果Entity的字段和复杂查询不匹配时   自定义类来接收
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchUserDO extends SysUserEntity {

    private String deptName;
    private String deptLeader;

}
