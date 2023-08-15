package com.agileboot.domain.system.menu.dto;

import com.agileboot.common.utils.jackson.JacksonUtil;
import com.agileboot.domain.system.menu.db.SysMenuEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 动态路由信息
 * 必须加上@JsonInclude(Include.NON_NULL)的注解  否则传null值给Vue动态路由渲染时会出错
 * @author valarchie
 */
@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class RouterDTO {

    public RouterDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.name = entity.getRouterName();
            this.path = entity.getPath();
            // 暂时不需要component
//            this.component = entity.getComponent();
//            this.rank = entity.getRank();
//            this.redirect = entity.getRedirect();
            if (JacksonUtil.isJson(entity.getMetaInfo())) {
                this.meta = JacksonUtil.from(entity.getMetaInfo(), MetaDTO.class);
            } else {
                this.meta = new MetaDTO();
            }
            this.meta.setAuths(Lists.newArrayList(entity.getPermission()));
        }
    }

    /**
     * 路由名字  根据前端的要求   必须唯一
     * 并按照前端项目的推荐  这个Name最好和组件的Name一样  使用菜单表中的router_name
     * TODO 这里后端需要加校验
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
