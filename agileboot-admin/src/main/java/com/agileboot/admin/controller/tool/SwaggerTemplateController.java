package com.agileboot.admin.controller.tool;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.exception.error.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
 @RequestMapping("/test/user")
/**
 * swagger 用户测试方法
 *
 * @author valarchie
 */
@Api("用户信息管理")
public class SwaggerTemplateController extends BaseController {

    private final static Map<Integer, UserEntity> USER_ENTITY_MAP = new LinkedHashMap<>();

    static {
        USER_ENTITY_MAP.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        USER_ENTITY_MAP.put(2, new UserEntity(2, "agileBoot", "admin123", "15666666666"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public ResponseDTO<List<UserEntity>> userList() {
        List<UserEntity> userList = new ArrayList<>(USER_ENTITY_MAP.values());
        return ResponseDTO.ok(userList);
    }

    @ApiOperation("获取用户详细")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path",
        dataTypeClass = Integer.class)
    @GetMapping("/{userId}")
    public ResponseDTO<UserEntity> getUser(@PathVariable Integer userId) {
        if (!USER_ENTITY_MAP.isEmpty() && USER_ENTITY_MAP.containsKey(userId)) {
            return ResponseDTO.ok(USER_ENTITY_MAP.get(userId));
        } else {
            return ResponseDTO.fail(ErrorCode.Business.USER_NON_EXIST);
        }
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "mobile", value = "用户手机", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/save")
    public ResponseDTO<String> save(UserEntity user) {
        if (user == null || user.getUserId() == null) {
            return ResponseDTO.fail("用户ID不能为空");
        }
        USER_ENTITY_MAP.put(user.getUserId(), user);
        return ResponseDTO.ok();
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public ResponseDTO<String> update(@RequestBody UserEntity user) {
        if (user == null || user.getUserId() == null) {
            return ResponseDTO.fail("用户ID不能为空");
        }
        if (USER_ENTITY_MAP.isEmpty() || !USER_ENTITY_MAP.containsKey(user.getUserId())) {
            return ResponseDTO.fail("用户不存在");
        }
        USER_ENTITY_MAP.remove(user.getUserId());
        USER_ENTITY_MAP.put(user.getUserId(), user);
        return ResponseDTO.ok();
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path",
        dataTypeClass = Integer.class)
    @DeleteMapping("/{userId}")
    public ResponseDTO<String> delete(@PathVariable Integer userId) {
        if (!USER_ENTITY_MAP.isEmpty() && USER_ENTITY_MAP.containsKey(userId)) {
            USER_ENTITY_MAP.remove(userId);
            return ResponseDTO.ok();
        } else {
            return ResponseDTO.fail("用户不存在");
        }
    }

    @ApiModel(value = "UserEntity", description = "用户实体")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserEntity {

        @ApiModelProperty("用户ID")
        private Integer userId;

        @ApiModelProperty("用户名称")
        private String username;

        @ApiModelProperty("用户密码")
        private String password;

        @ApiModelProperty("用户手机")
        private String mobile;
    }
}

