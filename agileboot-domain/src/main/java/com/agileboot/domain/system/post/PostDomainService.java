package com.agileboot.domain.system.post;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.common.BulkOperationCommand;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.entity.SysPostEntity;
import com.agileboot.orm.service.ISysPostService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class PostDomainService {

    @Autowired
    private ISysPostService postService;

    public PageDTO getPostList(PostQuery query) {
        Page<SysPostEntity> page = postService.page(query.toPage(), query.toQueryWrapper());
        List<PostDTO> records = page.getRecords().stream().map(PostDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public PostDTO getPostInfo(Long postId) {
        SysPostEntity byId = postService.getById(postId);
        return new PostDTO(byId);
    }

    public void addPost(AddPostCommand addCommand, LoginUser loginUser) {
        PostModel postModel = addCommand.toModel();

        // check这种全局唯一性的判断 不适合放在 model领域类当中， 所以放在db service中  比较合适
        if (postService.checkPostNameUnique(null, postModel.getPostName())) {
            throw new ApiException(ErrorCode.Business.POST_NAME_IS_NOT_UNIQUE, postModel.getPostName());
        }

        if (postService.checkPostCodeUnique(null, postModel.getPostCode())) {
            throw new ApiException(ErrorCode.Business.POST_CODE_IS_NOT_UNIQUE, postModel.getPostCode());
        }

        postModel.logCreator(loginUser);

        postModel.insert();
    }

    public void updatePost(UpdatePostCommand updateCommand, LoginUser loginUser) {
        PostModel postModel = updateCommand.toModel();

        // check这种全局唯一性的判断 不适合放在 model领域类当中， 所以放在db service中  比较合适
        if (postService.checkPostNameUnique(postModel.getPostId(), postModel.getPostName())) {
            throw new ApiException(ErrorCode.Business.POST_NAME_IS_NOT_UNIQUE, postModel.getPostName());
        }

        if (postService.checkPostCodeUnique(postModel.getPostId(), postModel.getPostCode())) {
            throw new ApiException(ErrorCode.Business.POST_CODE_IS_NOT_UNIQUE, postModel.getPostCode());
        }

        postModel.logUpdater(loginUser);

        postModel.updateById();
    }


    public void deletePost(BulkOperationCommand<Long> deleteCommand) {
        for (Long id : deleteCommand.getIds()) {
            PostModel postModel = getPostModel(id);
            postModel.checkCanBeDelete(postService);
        }

        postService.removeBatchByIds(deleteCommand.getIds());
    }


    public PostModel getPostModel(Long id) {
        SysPostEntity byId = postService.getById(id);

        if (byId == null) {
            throw new ApiException(Business.OBJECT_NOT_FOUND, id, "职位");
        }

        return new PostModel(byId);
    }


}
