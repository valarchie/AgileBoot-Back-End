package com.agileboot.api.controller;

import com.agileboot.common.core.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调度日志操作处理
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return "暂无订单";
    }


}
