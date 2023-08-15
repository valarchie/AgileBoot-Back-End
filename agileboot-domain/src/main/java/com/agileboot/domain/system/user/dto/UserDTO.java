package com.agileboot.domain.system.user.dto;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.annotation.ExcelColumn;
import com.agileboot.common.annotation.ExcelSheet;
import com.agileboot.domain.common.cache.CacheCenter;
import com.agileboot.domain.system.dept.db.SysDeptEntity;
import com.agileboot.domain.system.post.db.SysPostEntity;
import com.agileboot.domain.system.role.db.SysRoleEntity;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.agileboot.domain.system.user.db.SearchUserDO;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@ExcelSheet(name = "用户列表")
@Data
public class UserDTO {

    public UserDTO(SysUserEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);

            SysDeptEntity dept = CacheCenter.deptCache.get(entity.getDeptId() + "");
            if (dept != null) {
                this.deptName = dept.getDeptName();
            }

            SysUserEntity creator = CacheCenter.userCache.getObjectById(entity.getCreatorId());
            if (creator != null) {
                this.creatorName = creator.getUsername();
            }

            if (entity.getRoleId() != null) {
                SysRoleEntity roleEntity = CacheCenter.roleCache.getObjectById(entity.getRoleId());
                this.roleName = roleEntity != null ? roleEntity.getRoleName() : "";
            }

            if (entity.getPostId() != null) {
                SysPostEntity post = CacheCenter.postCache.getObjectById(entity.getRoleId());
                this.postName = post != null ? post.getPostName() : "";
            }

        }
    }

    public UserDTO(SearchUserDO entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);

            if (entity.getRoleId() != null) {
                SysRoleEntity roleEntity = CacheCenter.roleCache.getObjectById(entity.getRoleId());
                this.roleName = roleEntity != null ? roleEntity.getRoleName() : "";
            }
        }
    }


    @ExcelColumn(name = "用户ID")
    private Long userId;

    @ExcelColumn(name = "职位ID")
    private Long postId;

    @ExcelColumn(name = "职位名称")
    private String postName;

    @ExcelColumn(name = "角色ID")
    private Long roleId;

    @ExcelColumn(name = "角色名称")
    private String roleName;

    @ExcelColumn(name = "部门ID")
    private Long deptId;

    @ExcelColumn(name = "部门名称")
    private String deptName;

    @ExcelColumn(name = "用户名")
    private String username;

    @ExcelColumn(name = "用户昵称")
    private String nickname;

    @ExcelColumn(name = "用户类型")
    private Integer userType;

    @ExcelColumn(name = "邮件")
    private String email;

    @ExcelColumn(name = "号码")
    private String phoneNumber;

    @ExcelColumn(name = "性别")
    private Integer sex;

    @ExcelColumn(name = "用户头像")
    private String avatar;

    @ExcelColumn(name = "状态")
    private Integer status;

    @ExcelColumn(name = "IP")
    private String loginIp;

    @ExcelColumn(name = "登录时间")
    private Date loginDate;

    @ExcelColumn(name = "创建者ID")
    private Long creatorId;

    @ExcelColumn(name = "创建者")
    private String creatorName;

    @ExcelColumn(name = "创建时间")
    private Date createTime;

    @ExcelColumn(name = "修改者ID")
    private Long updaterId;

    @ExcelColumn(name = "修改者")
    private String updaterName;

    @ExcelColumn(name = "修改时间")
    private Date updateTime;

    @ExcelColumn(name = "备注")
    private String remark;

}
