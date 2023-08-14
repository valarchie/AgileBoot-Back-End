package com.agileboot.domain.system.post.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.post.db.SysPostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PostModelTest {

    private final SysPostService postService = mock(SysPostService.class);

    private final PostModelFactory postModelFactory = new PostModelFactory(postService);

    private static final long POST_ID = 1L;

    @AfterEach
    public void clean() {
        Mockito.reset(postService);
    }

    @Test
    void testCheckCanBeDeleteWhenFailed() {
        PostModel postModel = postModelFactory.create();
        postModel.setPostId(POST_ID);

        when(postService.isAssignedToUsers(POST_ID)).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, postModel::checkCanBeDelete);
        Assertions.assertEquals(Business.POST_ALREADY_ASSIGNED_TO_USER_CAN_NOT_BE_DELETED, exception.getErrorCode());
    }

    @Test
    void testCheckCanBeDeleteWhenSuccessful() {
        PostModel postModel = postModelFactory.create();
        postModel.setPostId(POST_ID);

        when(postService.isAssignedToUsers(POST_ID)).thenReturn(true);

        ApiException exception = assertThrows(ApiException.class, postModel::checkCanBeDelete);
        Assertions.assertEquals(Business.POST_ALREADY_ASSIGNED_TO_USER_CAN_NOT_BE_DELETED, exception.getErrorCode());
    }


    @Test
    void testCheckPostNameUnique() {
        PostModel postWithSameName = postModelFactory.create();
        postWithSameName.setPostId(POST_ID);
        postWithSameName.setPostName("post 1");
        PostModel postWithNewName = postModelFactory.create();
        postWithNewName.setPostName("post 2");
        postWithNewName.setPostId(POST_ID);

        when(postService.isPostNameDuplicated(POST_ID, eq("post 1"))).thenReturn(true);
        when(postService.isPostNameDuplicated(POST_ID, eq("post 2"))).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, postWithSameName::checkPostNameUnique);
        Assertions.assertEquals(Business.POST_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(postWithNewName::checkPostNameUnique);
    }

    @Test
    void testCheckPostCodeUnique() {
        PostModel postWithSameCode = postModelFactory.create();
        postWithSameCode.setPostId(POST_ID);
        postWithSameCode.setPostCode("code 1");
        PostModel postWithNewCode = postModelFactory.create();
        postWithNewCode.setPostId(POST_ID);
        postWithNewCode.setPostCode("code 2");

        when(postService.isPostCodeDuplicated(POST_ID, "code 1")).thenReturn(true);
        when(postService.isPostCodeDuplicated(POST_ID, "code 2")).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class, postWithSameCode::checkPostCodeUnique);
        Assertions.assertEquals(Business.POST_CODE_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(postWithNewCode::checkPostCodeUnique);
    }

}
