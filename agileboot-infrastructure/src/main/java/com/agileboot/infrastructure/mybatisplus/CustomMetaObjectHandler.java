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

    public static final String CREATE_TIME_FIELD = "createTime";
    public static final String CREATOR_ID_FIELD = "creatorId";

    public static final String UPDATE_TIME_FIELD = "updateTime";
    public static final String UPDATER_ID_FIELD = "updaterId";


    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(CREATE_TIME_FIELD)) {
            this.setFieldValByName(CREATE_TIME_FIELD, new Date(), metaObject);
        }

        if (metaObject.hasSetter(CREATOR_ID_FIELD)) {
            this.strictInsertFill(metaObject, CREATOR_ID_FIELD, Long.class, getUserIdSafely());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME_FIELD)) {
            this.setFieldValByName(UPDATE_TIME_FIELD, new Date(), metaObject);
        }

        if (metaObject.hasSetter(UPDATER_ID_FIELD)) {
            this.strictUpdateFill(metaObject, UPDATER_ID_FIELD, Long.class, getUserIdSafely());
        }
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
