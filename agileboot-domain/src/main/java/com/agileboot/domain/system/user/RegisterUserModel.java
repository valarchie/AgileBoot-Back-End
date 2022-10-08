package com.agileboot.domain.system.user;

import com.agileboot.orm.entity.SysUserEntity;
import lombok.Data;

@Data
public class RegisterUserModel extends SysUserEntity {

    private String code;
    private String uuid;

}
