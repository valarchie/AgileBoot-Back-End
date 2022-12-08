package com.agileboot.domain.system.user.dto;

import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.role.dto.RoleDTO;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserDetailDTO {

    private UserDTO user;

    /**
     * 返回所有role
     */
    private List<RoleDTO> roles;

    /**
     * 返回所有posts
     */
    private List<PostDTO> posts;

    private Long postId;

    private Long roleId;

    private Set<String> permissions;

}
