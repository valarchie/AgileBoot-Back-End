package com.agileboot.domain.system.post.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.orm.system.entity.SysPostEntity;
import com.agileboot.orm.system.service.ISysPostService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class PostModelFactory {

    @NonNull
    private ISysPostService postService;

    public PostModel loadById(Long postId) {
        SysPostEntity byId = postService.getById(postId);
        if (byId == null) {
            throw new ApiException(Business.OBJECT_NOT_FOUND, postId, "职位");
        }
        return new PostModel(byId, postService);
    }

    public PostModel create() {
        return new PostModel(postService);
    }

}
