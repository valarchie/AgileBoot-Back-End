package com.agileboot.domain.system.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.domain.system.loginInfo.SearchUserQuery;
import com.agileboot.domain.system.post.PostDTO;
import com.agileboot.domain.system.role.RoleDTO;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.domain.system.user.command.ChangeStatusCommand;
import com.agileboot.domain.system.user.command.ResetPasswordCommand;
import com.agileboot.domain.system.user.command.UpdateProfileCommand;
import com.agileboot.domain.system.user.command.UpdateUserAvatarCommand;
import com.agileboot.domain.system.user.command.UpdateUserCommand;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.service.TokenService;
import com.agileboot.orm.entity.SysPostEntity;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.entity.SysUserEntity;
import com.agileboot.orm.result.SearchUserDO;
import com.agileboot.orm.service.ISysPostService;
import com.agileboot.orm.service.ISysRoleService;
import com.agileboot.orm.service.ISysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDomainService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCacheService redisCacheService;



    public PageDTO getUserList(SearchUserQuery query) {
        Page<SearchUserDO> searchUserDOPage = userService.selectUserList(query);
        List<UserDTO> userDTOList = searchUserDOPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO(userDTOList, searchUserDOPage.getTotal());
    }

    public UserProfileDTO getUserProfile(Long userId) {

        SysUserEntity userEntity = userService.getById(userId);
        SysPostEntity postEntity = userService.getPostOfUser(userId);
        SysRoleEntity roleEntity = userService.getRoleOfUser(userId);

        return new UserProfileDTO(userEntity, postEntity, roleEntity);
    }


    public void updateUserProfile(UpdateProfileCommand command, LoginUser loginUser) {
        UserModel userModel = getUserModel(command.getUserId());
        command.updateModel(userModel);

        userModel.checkPhoneNumberIsUnique(userService);
        userModel.checkEmailIsUnique(userService);
        userModel.logUpdater(loginUser);

        userModel.updateById();

        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public UserDetailDTO getUserDetailInfo(Long userId) {
        SysUserEntity userEntity = userService.getById(userId);
        UserDetailDTO detailDTO = new UserDetailDTO();

        List<RoleDTO> roleDTOs = roleService.list().stream().map(RoleDTO::new).collect(Collectors.toList());
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

    public void addUser(LoginUser loginUser, AddUserCommand command) {
        UserModel model = command.toModel();

        model.checkUsernameIsUnique(userService);
        model.checkPhoneNumberIsUnique(userService);
        model.checkEmailIsUnique(userService);

        model.logCreator(loginUser);

        model.insert();
    }

    public void updateUser(LoginUser loginUser, UpdateUserCommand command) {
        UserModel model = command.toModel();


        model.checkPhoneNumberIsUnique(userService);
        model.checkEmailIsUnique(userService);
        model.checkCanBeModify(loginUser);

        model.logUpdater(loginUser);

        model.updateById();

        redisCacheService.userCache.delete(model.getUserId());
    }

    @Transactional
    public void deleteUsers(LoginUser loginUser, BulkOperationCommand<Long> command) {
        for (Long id : command.getIds()) {
            UserModel userModel = getUserModel(id);
            userModel.checkCanBeDelete(loginUser);
            userModel.deleteById();
        }
    }

    public void updateUserPassword(LoginUser loginUser, UpdateUserPasswordCommand command) {
        UserModel userModel = getUserModel(command.getUserId());
        userModel.modifyPassword(command);
        userModel.updateById();

        loginUser.setEntity(userModel);

        userModel.logUpdater(loginUser);

        tokenService.setLoginUser(loginUser);
        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public void resetUserPassword(LoginUser loginUser, ResetPasswordCommand command) {
        UserModel userModel = getUserModel(command.getUserId());
        userModel.checkCanBeModify(loginUser);
        userModel.resetPassword(command.getPassword());

        userModel.logUpdater(loginUser);

        userModel.updateById();
        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public void changeUserStatus(LoginUser loginUser, ChangeStatusCommand command) {
        UserModel userModel = getUserModel(command.getUserId());
        userModel.setStatus(Convert.toInt(command.getStatus()));

        userModel.checkCanBeModify(loginUser);
        userModel.logUpdater(loginUser);

        userModel.updateById();
        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public void updateUserAvatar(LoginUser loginUser, UpdateUserAvatarCommand command) {
        UserModel userModel = getUserModel(command.getUserId());
        userModel.setAvatar(command.getAvatar());

        userModel.logUpdater(loginUser);
        userModel.updateById();
        tokenService.setLoginUser(loginUser);
        redisCacheService.userCache.delete(userModel.getUserId());
    }

    public UserInfoDTO getUserWithRole(Long userId) {
        UserModel userModel = getUserModel(userId);
        UserDTO userDTO = new UserDTO(userModel);

        SysRoleEntity roleEntity = roleService.getById(userModel.getRoleId());
        RoleDTO roleDTO = new RoleDTO(roleEntity);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUser(userDTO);
        userInfoDTO.setRole(roleDTO);
        return userInfoDTO;
    }

    public UserModel getUserModel(Long userId) {
        SysUserEntity byId = userService.getById(userId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, userId, "用户");
        }

        UserModel userModel = new UserModel();
        BeanUtil.copyProperties(byId, userModel);
        return userModel;
    }



}
