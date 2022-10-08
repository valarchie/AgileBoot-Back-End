package com.agileboot.domain.system.user.command;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.domain.system.user.UserModel;
import lombok.Data;

@Data
public class AddUserCommand {

    @ExcelColumn(name = "部门ID")
    private Long deptId;

    @ExcelColumn(name = "用户名")
    private String username;

    @ExcelColumn(name = "昵称")
    private String nickName;

    @ExcelColumn(name = "邮件")
    private String email;

    @ExcelColumn(name = "电话号码")
    private String phoneNumber;

    @ExcelColumn(name = "性别")
    private Integer sex;

    @ExcelColumn(name = "头像")
    private String avatar;

    @ExcelColumn(name = "密码")
    private String password;

    @ExcelColumn(name = "状态")
    private String status;

    @ExcelColumn(name = "角色ID")
    private Long roleId;

    @ExcelColumn(name = "职位ID")
    private Long postId;

    @ExcelColumn(name = "备注")
    private String remark;


    public UserModel toModel() {
        UserModel model = new UserModel();

        model.setDeptId(deptId);
        model.setUsername(username);
        model.setNickName(nickName);
        model.setEmail(email);
        model.setPhoneNumber(phoneNumber);
        model.setSex(sex);
        if (StrUtil.isNotEmpty(password)) {
            model.setPassword(password);
        }
        model.setStatus(Convert.toInt(status));

        model.setRoleId(roleId);
        model.setPostId(postId);
        model.setRemark(remark);

        return model;
    }

}
