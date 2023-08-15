package com.agileboot.domain.system.menu.dto;

import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class TransitionDTO {

    // 当前页面动画，这里是第一种模式，比如 name: "fade" 更具体看后面链接
    // https://cn.vuejs.org/api/built-in-components.html#transition
    private String name;
    // 当前页面进场动画，这里是第二种模式，比如 enterTransition: "animate__fadeInLeft"
    private String enterTransition;
    // 当前页面离场动画，这里是第二种模式，比如 leaveTransition: "animate__fadeOutRight"
    private String leaveTransition;

}
