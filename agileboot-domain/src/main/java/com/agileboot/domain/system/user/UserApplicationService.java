package com.agileboot.domain.system.user;

import cn.hutool.core.convert.Convert;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.role.dto.RoleDTO;
import com.agileboot.domain.system.role.model.RoleModel;
import com.agileboot.domain.system.role.model.RoleModelFactory;
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
import com.agileboot.domain.system.user.model.UserModelFactory;
import com.agileboot.domain.system.user.query.SearchUserQuery;
import com.agileboot.infrastructure.cache.CacheCenter;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.service.TokenService;
import com.agileboot.orm.system.entity.SysPostEntity;
import com.agileboot.orm.system.entity.SysRoleEntity;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.result.SearchUserDO;
import com.agileboot.orm.system.service.ISysPostService;
import com.agileboot.orm.system.service.ISysRoleMenuService;
import com.agileboot.orm.system.service.ISysRoleService;
import com.agileboot.orm.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class UserApplicationService {

    @NonNull
    private ISysUserService userService;

    @NonNull
    private ISysRoleService roleService;

    @NonNull
    private ISysPostService postService;

    @NonNull
    private UserModelFactory userModelFactory;

    @NonNull
    private TokenService tokenService;

    @NonNull
    private RoleModelFactory roleModelFactory;

    @NonNull
    private BCryptPasswordEncoder encoder;


    public PageDTO<UserDTO> getUserList(SearchUserQuery query) {
        Page<SearchUserDO> userPage = userService.getUserList(query);
        List<UserDTO> userDTOList = userPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO<>(userDTOList, userPage.getTotal());
    }

    public UserProfileDTO getUserProfile(Long userId) {

        SysUserEntity userEntity = userService.getById(userId);
        SysPostEntity postEntity = userService.getPostOfUser(userId);
        SysRoleEntity roleEntity = userService.getRoleOfUser(userId);

        return new UserProfileDTO(userEntity, postEntity, roleEntity);
    }


    public void updateUserProfile(UpdateProfileCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());
        userModel.loadUpdateProfileCommand(command);

        userModel.checkPhoneNumberIsUnique();
        userModel.checkEmailIsUnique();

        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public UserDetailDTO getUserDetailInfo(Long userId) {
        SysUserEntity userEntity = userService.getById(userId);
        UserDetailDTO detailDTO = new UserDetailDTO();

        QueryWrapper<SysRoleEntity> roleQuery = new QueryWrapper<>();
        roleQuery.orderByAsc("role_sort");
        List<RoleDTO> roleDtoList = roleService.list(roleQuery).stream().map(RoleDTO::new).collect(Collectors.toList());
        List<PostDTO> postDtoList = postService.list().stream().map(PostDTO::new).collect(Collectors.toList());
        detailDTO.setRoles(roleDtoList);
        detailDTO.setPosts(postDtoList);

        if (userEntity != null) {
            detailDTO.setUser(new UserDTO(userEntity));
            detailDTO.setRoleId(userEntity.getRoleId());
            detailDTO.setPostId(userEntity.getPostId());
        }
        return detailDTO;
    }

    public void addUser(AddUserCommand command) {
        command.setPassword(encoder.encode(command.getPassword()));
        UserModel model = userModelFactory.create();
        model.loadAddUserCommand(command);

        model.checkUsernameIsUnique();
        model.checkPhoneNumberIsUnique();
        model.checkEmailIsUnique();

        model.insert();
    }

    public void updateUser(UpdateUserCommand command) {
        UserModel model = userModelFactory.loadById(command.getUserId());
        model.loadUpdateUserCommand(command);

        model.checkPhoneNumberIsUnique();
        model.checkEmailIsUnique();
        model.updateById();

        CacheCenter.userCache.delete(model.getUserId());
    }

    public void deleteUsers(LoginUser loginUser, BulkOperationCommand<Long> command) {
        for (Long id : command.getIds()) {
            UserModel userModel = userModelFactory.loadById(id);
            userModel.checkCanBeDelete(loginUser);
            userModel.deleteById();
        }
    }

    public void updatePasswordBySelf(LoginUser loginUser, UpdateUserPasswordCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());
        userModel.modifyPassword(command);
        userModel.updateById();

        loginUser.setEntity(userModel);

        tokenService.setLoginUser(loginUser);
        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public void resetUserPassword(ResetPasswordCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());

        userModel.resetPassword(command.getPassword());
        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public void changeUserStatus(ChangeStatusCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());

        userModel.setStatus(Convert.toInt(command.getStatus()));
        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public void updateUserAvatar(LoginUser loginUser, UpdateUserAvatarCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());

        userModel.setAvatar(command.getAvatar());
        userModel.updateById();

        tokenService.setLoginUser(loginUser);

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public UserInfoDTO getUserWithRole(Long userId) {
        UserModel userModel = userModelFactory.loadById(userId);
        RoleModel roleModel = roleModelFactory.loadById(userModel.getRoleId());

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUser(new UserDTO(userModel));
        userInfoDTO.setRole(new RoleDTO(roleModel));
        return userInfoDTO;
    }


}
