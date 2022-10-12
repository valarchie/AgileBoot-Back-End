package com.agileboot.domain.system.user.dto;

import com.agileboot.domain.system.role.dto.RoleDTO;
import lombok.Data;

@Data
public class UserInfoDTO {

    private UserDTO user;
    private RoleDTO role;

}
