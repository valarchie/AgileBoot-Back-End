package com.agileboot.domain.system.post;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.system.post.command.AddPostCommand;
import com.agileboot.domain.system.post.command.UpdatePostCommand;
import com.agileboot.domain.system.post.dto.PostDTO;
import com.agileboot.domain.system.post.model.PostModel;
import com.agileboot.domain.system.post.model.PostModelFactory;
import com.agileboot.domain.system.post.query.PostQuery;
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
public class PostApplicationService {

    @Autowired
    private ISysPostService postService;

    public PageDTO getPostList(PostQuery query) {
        Page<SysPostEntity> page = postService.page(query.toPage(), query.toQueryWrapper());
        List<PostDTO> records = page.getRecords().stream().map(PostDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public PostDTO getPostInfo(Long postId) {
        SysPostEntity byId = PostModelFactory.loadFromDb(postId, postService);
        return new PostDTO(byId);
    }

    public void addPost(AddPostCommand addCommand, LoginUser loginUser) {
        PostModel postModel = PostModelFactory.loadFromAddCommand(addCommand, new PostModel());

        postModel.checkPostNameUnique(postService);
        postModel.checkPostCodeUnique(postService);
        postModel.logCreator(loginUser);

        postModel.insert();
    }

    public void updatePost(UpdatePostCommand updateCommand, LoginUser loginUser) {
        PostModel postModel = PostModelFactory.loadFromDb(updateCommand.getPostId(), postService);
        postModel.loadFromUpdateCommand(updateCommand);

        postModel.checkPostNameUnique(postService);
        postModel.checkPostCodeUnique(postService);
        postModel.logUpdater(loginUser);

        postModel.updateById();
    }


    public void deletePost(BulkOperationCommand<Long> deleteCommand) {
        for (Long id : deleteCommand.getIds()) {
            PostModel postModel = PostModelFactory.loadFromDb(id, postService);
            postModel.checkCanBeDelete(postService);
        }

        postService.removeBatchByIds(deleteCommand.getIds());
    }



}
