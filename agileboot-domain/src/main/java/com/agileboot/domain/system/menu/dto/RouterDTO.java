package com.agileboot.domain.system.menu.dto;

import com.agileboot.common.utils.jackson.JacksonUtil;
import com.agileboot.domain.system.menu.model.RouterModel;
import com.agileboot.orm.system.entity.SysMenuEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 动态路由信息
 *
 * @author valarchie
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class RouterDTO {

    public RouterDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.name = entity.getRouteName();
            this.path = entity.getPath();
            this.component = entity.getComponent();
            this.rank = entity.getRank();
            if (JacksonUtil.isJson(entity.getMetaInfo())) {
                this.meta = JacksonUtil.from(entity.getMetaInfo(), MetaDTO.class);
            } else {
                this.meta = new MetaDTO();
            }
            this.meta.setAuths(Lists.newArrayList(entity.getPermission()));
        }
    }

    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由路径地址
     */
    private String path;

    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 一级菜单排序值（排序仅支持一级菜单）
     */
    private Integer rank;


    /**
     * 其他元素
     */
    private MetaDTO meta;

    /**
     * 子路由
     */
    private List<RouterDTO> children;

}
