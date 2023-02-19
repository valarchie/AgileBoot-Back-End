package com.agileboot.domain.system.menu.dto;

import cn.hutool.core.util.BooleanUtil;
import com.agileboot.orm.common.enums.StatusEnum;
import com.agileboot.orm.common.util.BasicEnumUtil;
import com.agileboot.orm.system.entity.SysMenuEntity;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class MenuDTO {

    public MenuDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.menuId = entity.getMenuId();
            this.parentId = entity.getParentId();
            this.menuName = entity.getMenuName();
            this.menuType = entity.getMenuType();
            this.icon = entity.getIcon();
            this.orderNum = entity.getOrderNum();
            this.component = entity.getComponent();
            this.perms = entity.getPerms();
            this.path = entity.getPath();
            this.status = entity.getStatus();
            this.statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());
            this.createTime = entity.getCreateTime();
            this.isExternal = BooleanUtil.toInt(entity.getIsExternal());
            this.isCache = BooleanUtil.toInt(entity.getIsCache());
            this.isVisible = BooleanUtil.toInt(entity.getIsVisible());
            this.query = entity.getQuery();
        }
    }

    private Long menuId;
    private Long parentId;
    private Integer menuType;
    private String menuName;
    private String icon;
    private Integer orderNum;
    private String component;
    private String path;
    private String perms;
    private Integer status;
    private String statusStr;
    private Date createTime;
    private Integer isExternal;
    private Integer isCache;
    private Integer isVisible;
    private String query;

}
