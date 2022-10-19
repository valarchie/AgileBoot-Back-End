package com.agileboot.domain.system.menu.dto;

import cn.hutool.http.HttpUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由显示信息
 *
 * @author ruoyi
 */
@Data
@NoArgsConstructor
public class MetaDTO {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private Boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public MetaDTO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }


    public MetaDTO(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        if (HttpUtil.isHttp(link)) {
            this.link = link;
        }
    }

    public MetaDTO(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (HttpUtil.isHttp(link)) {
            this.link = link;
        }
    }

}
