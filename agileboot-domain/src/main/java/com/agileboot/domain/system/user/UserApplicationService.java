package com.agileboot.domain.system.user;

import cn.hutool.core.convert.Convert;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.role.model.RoleModel;
import com.agileboot.domain.system.role.model.RoleModelFactory;
import com.agileboot.domain.system.user.model.UserModelFactory;
import com.agileboot.domain.system.user.query.SearchUserQuery;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.role.dto.RoleDTO;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.domain.system.user.command.ChangeStatusCommand;
import com.agileboot.domain.system.user.command.ResetPasswordCommand;
import com.agileboot.domain.system.user.command.UpdateProfileCommand;
import com.agileboot.domain.system.user.command.UpdateUserAvatarCommand;
import com.agileboot.domain.system.user.command.UpdateUserCommand;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.domain.system.user.dto.UserDTO;
import com.agileboot.domain.system.user.dto.UserDetailDTO;
import com.agileboot.domain.system.user.dto.UserInfoDTO;
import com.agileboot.domain.system.user.dto.UserProfileDTO;
import com.agileboot.domain.system.user.model.UserModel;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.service.TokenService;
import com.agileboot.orm.entity.SysPostEntity;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.result.SearchUserDO;
import com.agileboot.orm.service.ISysPostService;
import com.agileboot.orm.service.ISysRoleMenuService;
import com.agileboot.orm.service.ISysRoleService;
import com.agileboot.orm.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class UserApplicationService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysRoleMenuService roleMenuService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCacheService redisCacheService;



    public PageDTO getUserList(SearchUserQuery query) {
        Page<SearchUserDO> searchUserDOPage = userService.getUserList(query);
        List<UserDTO> userDTOList = searchUserDOPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO(userDTOList, searchUserDOPage.getTotal());
    }

    public UserProfileDTO getUserProfile(Long userId) {

        SysUserEntity userEntity = userService.getById(userId);
        SysPostEntity postEntity = userService.getPostOfUser(userId);
        SysRoleEntity roleEntity = userService.getRoleOfUser(userId);

        return new UserProfileDTO(userEntity, postEntity, roleEntity);
    }


    public void updateUserProfile(UpdateProfileCommand command) {
        UserModel userModel = UserModelFactory.loadFromDb(command.getUserId(), userService);
        userModel.loadUpdateProfileCommand(command);

        userModel.checkPhoneNumberIsUnique(userService);
        userModel.checkEmailIsUnique(userService);

        userModel.updateById();

        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public UserDetailDTO getUserDetailInfo(Long userId) {
        SysUserEntity userEntity = userService.getById(userId);
        UserDetailDTO detailDTO = new UserDetailDTO();

        QueryWrapper<SysRoleEntity> roleQuery = new QueryWrapper<>();
        roleQuery.orderByAsc("role_sort");
        List<RoleDTO> roleDTOs = roleService.list(roleQuery).stream().map(RoleDTO::new).collect(Collectors.toList());
        List<PostDTO> postDTOs = postService.list().stream().map(PostDTO::new).collect(Collectors.toList());
        detailDTO.setRoles(roleDTOs);
        detailDTO.setPosts(postDTOs);

        if (userEntity != null) {
            detailDTO.setUser(new UserDTO(userEntity));
            detailDTO.setRoleId(userEntity.getRoleId());
            detailDTO.setPostId(userEntity.getPostId());
        }
        return detailDTO;
    }

    public void addUser(AddUserCommand command) {
        UserModel model = UserModelFactory.loadFromAddCommand(command, new UserModel());

        model.checkUsernameIsUnique(userService);
        model.checkPhoneNumberIsUnique(userService);
        model.checkEmailIsUnique(userService);

        model.insert();
    }

    public void updateUser(UpdateUserCommand command) {
        UserModel model = UserModelFactory.loadFromDb(command.getUserId(), userService);
        model.loadUpdateUserCommand(command);

        model.checkPhoneNumberIsUnique(userService);
        model.checkEmailIsUnique(userService);
        model.updateById();

        redisCacheService.userCache.delete(model.getUserId());
    }

    public void deleteUsers(LoginUser loginUser, BulkOperationCommand<Long> command) {
        for (Long id : command.getIds()) {
            UserModel userModel = UserModelFactory.loadFromDb(id, userService);
            userModel.checkCanBeDelete(loginUser);
            userModel.deleteById();
        }
    }

    public void updatePasswordBySelf(LoginUser loginUser, UpdateUserPasswordCommand command) {
        UserModel userModel = UserModelFactory.loadFromDb(command.getUserId(), userService);
        userModel.modifyPassword(command);
        userModel.updateById();

        loginUser.setEntity(userModel);

        tokenService.setLoginUser(loginUser);
        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public void resetUserPassword(ResetPasswordCommand command) {
        UserModel userModel = UserModelFactory.loadFromDb(command.getUserId(), userService);

        userModel.resetPassword(command.getPassword());
        userModel.updateById();

        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public void changeUserStatus(ChangeStatusCommand command) {
        UserModel userModel = UserModelFactory.loadFromDb(command.getUserId(), userService);

        userModel.setStatus(Convert.toInt(command.getStatus()));
        userModel.updateById();

        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public void updateUserAvatar(LoginUser loginUser, UpdateUserAvatarCommand command) {
        UserModel userModel = UserModelFactory.loadFromDb(command.getUserId(), userService);
        userModel.setAvatar(command.getAvatar());
        userModel.updateById();

        tokenService.setLoginUser(loginUser);
        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public UserInfoDTO getUserWithRole(Long userId) {
        UserModel userModel = UserModelFactory.loadFromDb(userId, userService);
        RoleModel roleModel = RoleModelFactory.loadFromDb(userModel.getRoleId(), roleService, roleMenuService);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUser(new UserDTO(userModel));
        userInfoDTO.setRole(new RoleDTO(roleModel));
        return userInfoDTO;
    }


}
