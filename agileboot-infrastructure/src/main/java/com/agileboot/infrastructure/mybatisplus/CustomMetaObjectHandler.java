package com.agileboot.infrastructure.mybatisplus;

import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
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
