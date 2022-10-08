package com.agileboot.infrastructure.web.domain.login;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.entity.SysRoleEntity;
import com.agileboot.orm.enums.DataScopeEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    public Role(SysRoleEntity entity) {
        if (entity != null) {
            this.roleId = entity.getRoleId();
            this.roleName = entity.getRoleName();
            this.dataScope = BasicEnumUtil.fromValue(DataScopeEnum.class, entity.getDataScope());

            if(StrUtil.isNotEmpty(entity.getDeptIdSet())) {
                this.deptIdSet = StrUtil.split(entity.getDeptIdSet(), ",").stream()
                    .map(Convert::toLong).collect( Collectors.toSet());
            }
        }
    }

    private Long roleId;
    private String roleName;
    private DataScopeEnum dataScope;
    private Set<Long> deptIdSet;

}
