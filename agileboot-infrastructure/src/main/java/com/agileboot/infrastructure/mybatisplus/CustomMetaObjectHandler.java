package com.agileboot.infrastructure.mybatisplus;

import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sun.org.apache.xpath.internal.objects.XNull;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * Mybatis Plus允许在插入或者更新的时候
 * 自定义设定值
 * @author valarchie
 */
@Component
@Slf4j
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);

        this.strictInsertFill(metaObject, "creatorId", this::getUserIdSafely, Long.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);

        Long newUpdaterId = getUserIdSafely();
//        Long oldUpdaterId = (Long) metaObject.getValue("updaterId");
//        Long updaterId = newUpdaterId == null ? oldUpdaterId : null;

//        this.setFieldValByName("updaterId", newUpdaterId, metaObject);
        this.strictUpdateFill(metaObject, "updaterId", Long.class, newUpdaterId);
    }

    public Long getUserIdSafely() {
        Long userId = null;
        try {
            LoginUser loginUser = AuthenticationUtils.getLoginUser();
            userId = loginUser.getUserId();
        } catch (Exception e) {
            log.info("can not find user in current thread.");
        }
        return userId;
    }



}
