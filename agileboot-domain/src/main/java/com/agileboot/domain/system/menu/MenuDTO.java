package com.agileboot.domain.system.menu;

import cn.hutool.core.util.BooleanUtil;
import com.agileboot.orm.entity.SysMenuEntity;
import com.agileboot.orm.enums.dictionary.CommonStatusEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import java.util.Date;
import lombok.Data;

@Data
public class MenuDTO {

    public MenuDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.menuId = entity.getMenuId();
            this.parentId = entity.getParentId();
            this.menuName = entity.getMenuName();
            this.menuType = entity.getMenuType() + "";
            this.icon = entity.getIcon();
            this.orderNum = entity.getOrderNum() + "";
            this.component = entity.getComponent();
            this.perms = entity.getPerms();
            this.path = entity.getPath();
            this.status = entity.getStatus() + "";
            this.statusStr = BasicEnumUtil.getDescriptionByValue(CommonStatusEnum.class, entity.getStatus());
            this.createTime = entity.getCreateTime();
            this.isExternal = BooleanUtil.toInt(entity.getIsExternal()) + "";
            this.isCache = BooleanUtil.toInt(entity.getIsCache()) + "";
            this.isVisible = BooleanUtil.toInt(entity.getIsVisible()) + "";
            this.query = entity.getQuery();
        }
    }

    private Long menuId;
    private Long parentId;
    private String menuType;
    private String menuName;
    private String icon;
    private String orderNum;
    private String component;
    private String path;
    private String perms;
    private String status;
    private String statusStr;
    private Date createTime;
    private String isExternal;
    private String isCache;
    private String isVisible;
    private String query;

}
