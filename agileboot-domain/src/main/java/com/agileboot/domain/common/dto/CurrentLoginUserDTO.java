package com.agileboot.domain.common.dto;

import com.agileboot.domain.system.user.dto.UserDTO;
import java.util.Set;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class CurrentLoginUserDTO {

    private UserDTO userInfo;
    private String roleKey;
    private Set<String> permissions;


}
