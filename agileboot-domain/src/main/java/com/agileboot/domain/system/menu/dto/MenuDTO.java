package com.agileboot.domain.system.menu.dto;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.utils.jackson.JacksonUtil;
import com.agileboot.orm.common.enums.MenuTypeEnum;
import com.agileboot.orm.common.enums.StatusEnum;
import com.agileboot.orm.common.util.BasicEnumUtil;
import com.agileboot.orm.system.entity.SysMenuEntity;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class MenuDTO {

    public MenuDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.id = entity.getMenuId();
            this.parentId = entity.getParentId();
            this.menuName = entity.getMenuName();
            this.routerName = entity.getRouterName();
            this.path = entity.getPath();
            this.status = entity.getStatus();
            this.isButton = entity.getIsButton();
            this.statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());

            if (!entity.getIsButton()) {
                this.menuType = entity.getMenuType();
                this.menuTypeStr = BasicEnumUtil.getDescriptionByValue(MenuTypeEnum.class, entity.getMenuType());
            } else {
                this.menuType = 0;
            }

            if (StrUtil.isNotEmpty(entity.getMetaInfo()) && JacksonUtil.isJson(entity.getMetaInfo())) {
                this.rank = JacksonUtil.from(entity.getMetaInfo(), MetaDTO.class).getRank();
            }
            this.createTime = entity.getCreateTime();
        }
    }

    // 设置成id和parentId 便于前端处理树级结构
    private Long id;

    private Long parentId;

    private String menuName;

    private String routerName;

    private String path;

    private Integer rank;

    private Integer menuType;

    private String menuTypeStr;

    private Boolean isButton;

    private Integer status;

    private String statusStr;

    private Date createTime;



}
