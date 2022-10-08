package com.agileboot.domain.system.user;

import com.agileboot.domain.system.role.RoleDTO;
import lombok.Data;

@Data
public class UserInfoDTO {

    private UserDTO user;
    private RoleDTO role;

}
