package com.agileboot.domain.common.dto;

import com.agileboot.domain.system.user.dto.UserDTO;
import com.agileboot.orm.common.result.DictionaryData;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserPermissionDTO {

    private UserDTO user;
    private String roleKey;
    private Set<String> permissions;
    private Map<String, List<DictionaryData>> dictTypes;

}
